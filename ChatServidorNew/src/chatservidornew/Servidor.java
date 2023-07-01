
package chatservidornew;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 * Clase en la que se maneja la comunicación del lado del servidor.
 */
public class Servidor extends Thread{  
    
    private ServerSocket serverSocket;
    LinkedList<HiloCliente> clientes;
    private final VentanaS ventana;
    private final String puerto;
    static int correlativo;
    
    /**
     * Constructor del servidor.
     * @param puerto
     * @param ventana 
     */
    public Servidor(String puerto, VentanaS ventana) {
        correlativo=0;
        this.puerto=puerto;
        this.ventana=ventana;
        clientes=new LinkedList<>();
        this.start();
    }
    /**
     * Método sobre el que corre el bucle infinito que tiene como función escuchar
     * permenentemente en espera de conexiones de nuevos clientes.
     */    
    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(Integer.valueOf(puerto));
            ventana.addServidorIniciado();
            while (true) {
                HiloCliente h;
                Socket socket;
                socket = serverSocket.accept();
                System.out.println("Nueva conexion entrante: "+socket);
                h=new HiloCliente(socket, this);               
                h.start();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "El servidor no se ha podido iniciar,\n"
                                                 + "puede que haya ingresado un puerto incorrecto.\n"
                                                 + "Esta aplicación se cerrará.");
            System.exit(0);
        }                
    }        
    /**
     * Ciclo que devuelve una lista con los identificadores de todos los clientes
     * conectados.
     * @return 
     */
    LinkedList<String> getUsuariosConectados() {
        LinkedList<String>usuariosConectados=new LinkedList<>();
        clientes.stream().forEach(c -> usuariosConectados.add(c.getIdentificador()));
        return usuariosConectados;
    }
    /**
     * Método que agrega una linea al log de la interfaz gráfica del servidor.
     * @param texto 
     */
    void agregarLog(String texto) {
        ventana.agregarLog(texto);
    }
}
