/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

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

    public ControladorClient() {
        mundo = new MundoClient(this);

    }

    public void conectar(PanelConversacion pnlConversacion, PanelEnviar pnlEnviar, PanelRegistro pnlRegistro, PanelUsuarios pnlUsuarios) {
        this.pnlConversacion = pnlConversacion;
        this.pnlEnviar = pnlEnviar;
        this.pnlRegistro = pnlRegistro;
        this.pnlUsuarios = pnlUsuarios;

    }

    public void recibirMensaje(String msg) {
        pnlConversacion.mostrarMensaje(msg, "R");

    }

    public void enviarMensaje(int tipo, String msg) {
        mundo.socket(tipo, msg);
    }

    public void mostrarUsuarios(String msg) {
        pnlUsuarios.showUser(msg);

    }

    public void mensajeError(String msg) {
        pnlRegistro.errorRegistro(msg);
        pnlRegistro.errorIngreso(msg);
    }
    
}
