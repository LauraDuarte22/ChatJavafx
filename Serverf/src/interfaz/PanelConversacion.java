/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorServer;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 *
 * @author admin
 */
public class PanelConversacion {

    public TextArea textArea;
    public Label label;
    private ControladorServer ctrl;

    public PanelConversacion(ControladorServer ctrl) {
        this.ctrl = ctrl;
        label = new Label("Conversación encriptada");
        Font font = Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 20);
        label.setFont(font);
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);

    }

    public void mostrarMensaje(String mensaje) {
        textArea.appendText(mensaje + "\n");
    }

}
