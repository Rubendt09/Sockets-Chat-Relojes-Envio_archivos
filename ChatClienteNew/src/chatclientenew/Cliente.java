package chatclientenew;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Cliente extends Thread {

    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private final VentanaC ventana;
    private String identificador;
    private boolean escuchando;
    private final String host;
    private final int puerto;
    private TimeZone clienteTimeZone;


    /**
     * Constructor de la clase cliente.
     *
     * @param ventana
     * @param host
     * @param puerto
     * @param nombre
     */
    Cliente(VentanaC ventana, String host, Integer puerto, String nombre) {
        this.ventana = ventana;
        this.host = host;
        this.puerto = puerto;
        this.identificador = nombre;
        escuchando = true;
        clienteTimeZone = TimeZone.getDefault();
        this.start();
    }

    /**
     * Método run del hilo de comunicación del lado del cliente.
     */
    @Override
    public void run() {
        try {
            socket = new Socket(host, puerto);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Conexion exitosa!!!!");
            this.enviarSolicitudConexion(identificador);
            this.escuchar();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(ventana, "Conexión rehusada, servidor desconocido,\n"
                    + "puede que haya ingresado una ip incorrecta\n"
                    + "o que el servidor no este corriendo.\n"
                    + "Esta aplicación se cerrará.");
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ventana, "Conexión rehusada, error de Entrada/Salida,\n"
                    + "puede que haya ingresado una ip o un puerto\n"
                    + "incorrecto, o que el servidor no este corriendo.\n"
                    + "Esta aplicación se cerrará.");
            System.exit(0);
        }

    }

    /**
     * Método que cierra el socket y los streams de comunicación.
     */
    public void desconectar() {
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();
            escuchando = false;
        } catch (Exception e) {
            System.err.println("Error al cerrar los elementos de comunicación del cliente.");
        }
    }

    /**
     * Método que envia un determinado mensaje hacia el servidor.
     *
     * @param cliente_receptor
     * @param mensaje
     */
    public void enviarMensaje(String cliente_receptor, String mensaje) {
        // Obtener la hora actual en la zona horaria local del cliente
        //Date horaActual = new Date();
        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        //sdf.setTimeZone(clienteTimeZone);
        //String horaLocal = sdf.format(horaActual);

        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("MENSAJE");
        //cliente emisor
        lista.add(identificador);
        //cliente receptor
        lista.add(cliente_receptor);
        //mensaje que se desea transmitir
        lista.add(mensaje);
        // Agregar la hora local a la lista de mensajes
        //lista.add(horaLocal);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    /*
     * Método que escucha constantemente lo que el servidor dice.
     */
    public void escuchar() {
        try {
            while (escuchando) {
                Object aux = objectInputStream.readObject();
                if (aux != null) {
                    if (aux instanceof LinkedList) {
                        //Si se recibe una LinkedList entonces se procesa
                        ejecutar((LinkedList<String>) aux);
                    } else {
                        System.err.println("Se recibió un Objeto desconocido a través del socket");
                    }
                } else {
                    System.err.println("Se recibió un null a través del socket");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "La comunicación con el servidor se ha\n"
                    + "perdido, este chat tendrá que finalizar.\n"
                    + "Esta aplicación se cerrará.");
            System.exit(0);
        }
    }

    /**
     * Método que ejecuta una serie de instruccines dependiendo del mensaje que el cliente reciba del servidor.
     *
     * @param lista
     */
    public void ejecutar(LinkedList<String> lista) {
        // 0 - El primer elemento de la lista es siempre el tipo
        String tipo = lista.get(0);
        switch (tipo) {
            case "CONEXION_ACEPTADA":
                // 1      - Identificador propio del nuevo usuario
                // 2 .. n - Identificadores de los clientes conectados actualmente
                identificador = lista.get(1);
                ventana.sesionIniciada(identificador);
                for (int i = 2; i < lista.size(); i++) {
                    ventana.addContacto(lista.get(i));
                }
                break;
            case "NUEVO_USUARIO_CONECTADO":
                // 1      - Identificador propio del cliente que se acaba de conectar
                ventana.addContacto(lista.get(1));
                break;
            case "USUARIO_DESCONECTADO":
                // 1      - Identificador propio del cliente que se acaba de conectar
                ventana.eliminarContacto(lista.get(1));
                break;
            case "MENSAJE":
                // 1      - Cliente emisor
                // 2      - Cliente receptor
                // 3      - Mensaje
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeZone(clienteTimeZone);
                Date horaActual = calendar.getTime();


                // Formatea la hora agregada en el formato deseado
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                sdf.setTimeZone(clienteTimeZone);
                String horaLocal = sdf.format(horaActual);
                ventana.addMensaje(lista.get(1), lista.get(3) + " - " + horaLocal);
                break;
            default:
                break;
        }
    }

    /**
     * Al conectarse el cliente debe solicitar al servidor que lo agregue a la
     * lista de clientes, para ello se ejecuta este método.
     *
     * @param identificador
     */
    private void enviarSolicitudConexion(String identificador) {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("SOLICITUD_CONEXION");
        //cliente solicitante
        lista.add(identificador);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    /**
     * Cuando se cierra una ventana cliente, se debe notificar al servidor que el
     * cliente se ha desconectado para que lo elimine de la lista de clientes y
     * todos los clientes lo eliminen de su lista de contactos.
     */
    void confirmarDesconexion() {
        LinkedList<String> lista = new LinkedList<>();
        //tipo
        lista.add("SOLICITUD_DESCONEXION");
        //cliente solicitante
        lista.add(identificador);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }

    /**
     * Método que retorna el identificador del cliente que es único dentro del chat.
     *
     * @return
     */
    String getIdentificador() {
        return identificador;
    }
}