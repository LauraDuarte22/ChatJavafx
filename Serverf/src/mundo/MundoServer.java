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
import controlador.ControladorServer;
import java.sql.SQLException;
import javafx.application.Platform;

/**
 *
 * @author admin
 */
public class MundoServer {

    private final String ip = "127.0.0.1";
    private final int portSend = 5000;
    private final int portListen = 5050;
    private ControladorServer ctrl;
    private Thread hilo;
    private boolean corriendo;
    private ServerSocket client;
    private DataInputStream inObjectBuffer;
    private Socket socket;
    private MySql mySql;

    public MundoServer(ControladorServer ctrl) {
        this.ctrl = ctrl;
        mySql = new MySql();

        hilo = new Thread() {
            @Override
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
                        Platform.runLater(() -> {
                            try {
                                String msj = inObjectBuffer.readUTF();
                                System.out.println(msj);
                                char tipo = msj.charAt(0);
                                String arr[] = msj.split("\\*");
                                String msjServer = "";
                                switch (tipo) {
                                    case '0':
                                        mySql.saveMsg(arr[1], arr[2], arr[3]);
                                        msjServer = "Mensaje enviado por: " + arr[1] + " para: " + arr[2] + " msj:  " + arr[3];
                                        msj = arr[3];
                                        socket(msj);
                                        break;
                                    case '1':
                                        boolean exist = mySql.verificarUser(arr[1]);
                                        if (exist == false) {
                                            mySql.insertUser(arr[1], arr[2]);
                                        } else {
                                            mySql.updateIp(arr[1], arr[2]);
                                            mySql.updateEstado(arr[1], "TRUE");
                                        }
                                        msjServer = "Usuario ingresado: " + arr[1];
                                        break;
                                    case '2':
                                        mySql.updateEstado(arr[1], "FALSE");
                                        msjServer = "Estado de usuario " + arr[1] + " actualizado";
                                        break;
                                    case '3':
                                        String users = mySql.selectUsers(arr[1]);
                                        msj = tipo + users;
                                        msjServer = "Usuarios disponibles para enviar mensajes \n" + users.replace("*", "\n");
                                        socket(msj);
                                        break;

                                    case '4':
                                        String temp = mySql.showMsg(arr[2], arr[1]);
                                        if (!temp.equals("")) {
                                            msj = tipo + "*" + mySql.showMsg(arr[2], arr[1]);
                                            socket(msj);
                                        }
                                        mySql.borrarMensajes(arr[2], arr[1]);
                                        break;
                                    default:
                                        break;
                                }
                                ctrl.recibirMensaje(msjServer);
                                socket.close();
                            } catch (IOException | SQLException ex) {
                                Logger.getLogger(MundoServer.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                    }
                } catch (IOException ex) {
                    Logger.getLogger(MundoServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        hilo.start();
    }

    //Envía mensaje
    public void socket(String mensaje) {
        try {

            try (Socket server = new Socket(ip, portSend)) {
                DataOutputStream outBuffer = new DataOutputStream(server.getOutputStream());
                outBuffer.writeUTF(mensaje);
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
