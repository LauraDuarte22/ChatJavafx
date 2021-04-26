/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

/**
 *
 * @author mateo
 */
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
public class PanelExcepciones {

    public TextArea textArea;
    public Label label;

    public PanelExcepciones() {
        label = new Label("Excepciones");
        Font font = Font.font("verdana", FontWeight.BLACK, FontPosture.REGULAR, 20);
        label.setFont(font);
        textArea = new TextArea();
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefWidth(290);
    }

    public void mostrarError(String mensaje) {
        textArea.appendText(mensaje + "\n");
    }

}
