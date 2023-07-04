package send_docs;



import archivoscompartidos.Message;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *Clase que tiene la interfaz grafica del cliente para que se conecte con el servidor
 * @author luis
 */
public class InterfazCliente extends javax.swing.JFrame {

    Socket socket;
    Thread hilo;
    ObjectInputStream entrada;
    ObjectOutputStream salida;

    /**
     * Crea una interfaz para poder controlar el servidor
     */
    public InterfazCliente() {
        initComponents();
        visibleComponents();
    }
    
    private void visibleComponents(){
        jTextDireccion.setVisible(false);
        jTextPuerto.setVisible(false);
        jLabel6.setVisible(false);
        jLabel5.setVisible(false);
    }

    private void habilitaComponentes(boolean estado) {
        jTextDireccion.setEnabled(estado);
        jTextPuerto.setEnabled(estado);
        jTextRuta.setEnabled(!estado);
        JButtonEnviar.setEnabled(!estado);
        //jButtonExaminar.setEnabled(!estado);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleArrancar1 = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelST = new javax.swing.JLabel();
        jToggleArrancar = new javax.swing.JToggleButton();
        jLabel5 = new javax.swing.JLabel();
        jTextPuerto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelStatus = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextRuta = new javax.swing.JTextField();
        JButtonEnviar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabelRuta = new javax.swing.JLabel();

        jToggleArrancar1.setText("Conectar");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(78, 12, 19));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(214, 205, 205));
        jLabel1.setText("Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel2.setText("Conexion:");

        jLabelST.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabelST.setText("Detenido");

        jToggleArrancar.setText("Conectar");
        jToggleArrancar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleArrancarActionPerformed(evt);
            }
        });

        jLabel5.setText("Numero de puerto:");

        jTextPuerto.setText("5001");

        jPanel2.setBackground(new java.awt.Color(197, 193, 189));

        jLabelStatus.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        jLabelStatus.setText("Actualmente no se ecnuentra conectado");
        jPanel2.add(jLabelStatus);

        jLabel6.setText("IP:");

        jTextDireccion.setText("127.0.0.1");

        jLabel7.setText("Ruta del archivo");

        jTextRuta.setEnabled(false);

        JButtonEnviar.setText("Enviar");
        JButtonEnviar.setEnabled(false);
        JButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JButtonEnviarActionPerformed(evt);
            }
        });

        jLabel3.setText("Ruta: ");

        jLabelRuta.setText("C:\\Users\\ruben\\OneDrive\\Escritorio\\Sockets-Chat-Relojes-Envio_archivos\\Downloads");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(JButtonEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(35, 35, 35)
                                .addComponent(jTextRuta))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelST)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jToggleArrancar))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelST)
                    .addComponent(jLabel2)
                    .addComponent(jToggleArrancar))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelRuta, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JButtonEnviar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleArrancarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleArrancarActionPerformed
        // TODO add your handling code here:
        if (jToggleArrancar.isSelected()) {
            String url = jTextDireccion.getText();
            int puerto = Integer.parseInt(jTextPuerto.getText());
            try {
                socket = new Socket(url, puerto);
                jLabelStatus.setText("Conectado con el servidor");
                jToggleArrancar.setText("Desconectar");
                jLabelST.setText("Conectado");
                habilitaComponentes(false);
                System.out.println("Creando conexion");                
                salida = new ObjectOutputStream(socket.getOutputStream());                
                entrada = new ObjectInputStream(socket.getInputStream());
                System.out.println("Termino");
            } catch (IOException e) {
                socket = null;
                jLabelStatus.setText("No se pudo conectar con el servidor");
                jToggleArrancar.setSelected(false);
                jToggleArrancar.setText("Conectar");
                habilitaComponentes(true);
                jLabelST.setText("Desconectado");
            }
        } else {
            try {
                if (socket.isConnected()) {
                    Message archivo = new Message();
                    archivo.ruta = "liberar";
                    try {
                        salida.writeObject(archivo);
                    } catch (IOException ex) {
                        jLabelStatus.setText("Ocurrio un error al tratar de enviar peticion");
                    }
                    socket.close();
                }
                jLabelStatus.setText("Desconectado de servidor");
                jToggleArrancar.setText("Conectar");
                habilitaComponentes(true);
            } catch (IOException e) {
                jLabelStatus.setText("Ocurrio un error inesperado");
            }
        }
    }//GEN-LAST:event_jToggleArrancarActionPerformed

    private void JButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JButtonEnviarActionPerformed
        // TODO add your handling code here:
        String cad = jTextRuta.getText();
        if (!cad.isEmpty()) {
            Message archivo = new Message();
            archivo.ruta = cad;
            try {
                salida.writeObject(archivo);
                ProcesandoPeticion hiloPrueba = new ProcesandoPeticion(socket, salida, entrada, jLabelStatus,jLabelRuta.getText());
                Thread hilo = new Thread(hiloPrueba);
                hilo.start();
                jLabelStatus.setText("Procesando la peticion .... ");
                hilo.join();
            } catch (IOException ex) {
                jLabelStatus.setText("Ocurrio un error al tratar de enviar peticion");
            } catch (InterruptedException ex) {
                jLabelStatus.setText("Error al esperar el archivo");
            }
        }
    }//GEN-LAST:event_JButtonEnviarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JButtonEnviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelRuta;
    private javax.swing.JLabel jLabelST;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextDireccion;
    private javax.swing.JTextField jTextPuerto;
    private javax.swing.JTextField jTextRuta;
    private javax.swing.JToggleButton jToggleArrancar;
    private javax.swing.JToggleButton jToggleArrancar1;
    // End of variables declaration//GEN-END:variables
}
