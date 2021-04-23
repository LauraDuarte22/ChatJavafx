/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import interfaz.InterfazApp;
import interfaz.PanelConversacion;
import interfaz.PanelEnviar;
import interfaz.PanelRegistro;
import interfaz.PanelUsuarios;
import mundo.MundoClient;

/**
 *
 * @author admin
 */
public class ControladorClient {

    private MundoClient mundo;
    private PanelConversacion pnlConversacion;
    private PanelEnviar pnlEnviar;
    private PanelRegistro pnlRegistro;
    private PanelUsuarios pnlUsuarios;
    private InterfazApp pnlInterfaz;

    public ControladorClient() {
        mundo = new MundoClient(this);

    }

    public void conectar(PanelConversacion pnlConversacion, PanelEnviar pnlEnviar, PanelRegistro pnlRegistro, PanelUsuarios pnlUsuarios, InterfazApp pnlInterfaz) {
        this.pnlConversacion = pnlConversacion;
        this.pnlEnviar = pnlEnviar;
        this.pnlRegistro = pnlRegistro;
        this.pnlUsuarios = pnlUsuarios;
        this.pnlInterfaz = pnlInterfaz;

    }

    public void recibirMensaje(String msg) {
        pnlConversacion.mostrarMensaje(msg, "R");

    }

    public void enviarMensaje(int tipo, String msg) {
        System.out.println("monda: "+msg);
        if (msg.split("\\*").length == 2 && tipo == 0) {
            msg += " ";
        }
        
        mundo.socket(tipo, msg);
    }

    public void mostrarUsuarios(String msg) {
        pnlUsuarios.showUser(msg);

    }

    public void mensajeError(String msg, boolean tipo) {
        if (tipo) {
            pnlRegistro.errorRegistro(msg);
        } else {
            pnlRegistro.errorIngreso(msg);
        }
    }

    public void metodoPrueba() {
        pnlInterfaz.iniciarChat();
    }

}
