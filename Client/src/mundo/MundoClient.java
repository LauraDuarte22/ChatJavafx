/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import controlador.ControladorClient;
import java.net.InetAddress;
import javafx.application.Platform;

/**
 *
 * @author admin
 */
public class MundoClient {
    
    private InetAddress ip;
    private final int portSend = 5050;
    private final int portListen = 5000;
    private ControladorClient ctrl;
    private Thread hilo;
    private boolean corriendo;
    ServerSocket client;
    DataInputStream inObjectBuffer;
    Socket socket;
    
    public MundoClient(ControladorClient ctrl) {
        this.ctrl = ctrl;
        
        hilo = new Thread() {
            public void run() {
                try {
                    client = new ServerSocket(portListen);
                    corriendo = true;
                    while (true) {
                        try {
                            Thread.sleep(100);
                            socket = client.accept();
                            inObjectBuffer = new DataInputStream(socket.getInputStream());
                            
                        } catch (InterruptedException ex) {
                        }
                        Platform.runLater(new Runnable() {
                            public void run() {
                                try {
                                    String msj = inObjectBuffer.readUTF();
                                    String vec[] = msj.split("\\*");
                                    char tipo = msj.charAt(0);
                                    if (tipo == '1') {
                                        ctrl.mensajeError(vec[1], true);
                                    }
                                    if (tipo == '5') {
                                        ctrl.mensajeError(vec[1], false);
                                    }
                                    if (tipo == '3') {
                                        ctrl.mostrarUsuarios(msj.replaceAll(Character.toString(tipo), ""));
                                    }
                                    if (tipo == '4') {
                                        for (int i = vec.length-1; i > 0; i--) {
                                            ctrl.recibirMensaje(desencriptar(vec[i]));
                                        }
                                    }
                                    if (tipo == 'A') {
                                        ctrl.metodoPrueba();
                                    }
                                    
                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(MundoClient.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MundoClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        hilo.start();
    }

    //Envía mensaje
    public void socket(int tipo, String mensaje) {
        try {
            ip = InetAddress.getLocalHost();
            try (Socket server = new Socket(ip.getHostAddress(), portSend)) {
                DataOutputStream outBuffer = new DataOutputStream(server.getOutputStream());
                if (tipo == 1 || tipo == 2 || tipo == 3 || tipo == 5) {
                    ip = InetAddress.getLocalHost();
                    mensaje = tipo + "*" + mensaje + "*" + ip.getHostAddress();
                    outBuffer.writeUTF((mensaje));
                } else if (tipo == 0) {
                    String[] msg = mensaje.split("\\*");
                    mensaje = tipo + "*" + msg[0] + "*" + msg[1] + "*" + encriptar(msg[2]);
                    outBuffer.writeUTF((mensaje));
                } else {
                    System.out.println(mensaje);
                    outBuffer.writeUTF((tipo + "*" + mensaje));
                }
            }
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "socket() : UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "socket() : IOException: " + e.getMessage());
        }
    }
    
    public String encriptar(String msg) {
        String encriptado = "";
        for (char ch : msg.toCharArray()) {
            encriptado += (char) (ch + 3);
        }
        return encriptado;
    }
    
    public String desencriptar(String msg) {
        String desencriptado = "";
        for (char ch : msg.toCharArray()) {
            desencriptado += (char) (ch - 3);
        }
        return desencriptado;
    }
}
