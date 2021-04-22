/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaz.PanelConversacion;
import java.sql.Connection;
import mundo.*;

/**
 *
 * @author admin
 */
public class ControladorServer {

    private MundoServer mundo;
    private PanelConversacion pnlConversacion;
    private MySql mySql;

    public ControladorServer() {
        mundo = new MundoServer(this);
        mySql = new MySql();

    }

    public void conectar(PanelConversacion pnlConversacion) {
        this.pnlConversacion = pnlConversacion;

    }

    public void recibirMensaje(String msg) {
        pnlConversacion.mostrarMensaje("Client: " + msg);

    }

    public Connection connect() {
        return mySql.connect();
    }

    public Connection getConnection() {
        return mySql.connect();
    }

}
