/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorClient;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author admin
 */
public class PanelEnviar {

    private ControladorClient ctrl;
    private PanelUsuarios pnlUsuarios;
    private PanelConversacion pnlConversacion;
    private PanelRegistro pnlRegistro;
    protected TextField textField;
    protected Button btn;

    protected ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/imagenes/send.png")));
    protected GridPane areas = new GridPane();

    public String userRecibe;
    public String userSend;

    public PanelEnviar(PanelConversacion pnlConversacion, ControladorClient ctrl, PanelUsuarios pnlUsuarios, PanelRegistro pnlRegistro) {
        this.pnlConversacion = pnlConversacion;
        this.ctrl = ctrl;
        this.pnlUsuarios = pnlUsuarios;
        this.pnlRegistro = pnlRegistro;
        areas = new GridPane();
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.CENTER);
        cc.setPercentWidth(85);
        areas.getColumnConstraints().add(cc);

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setHalignment(HPos.CENTER);
        cc1.setPercentWidth(15);
        areas.getColumnConstraints().add(cc1);
        textField = new TextField();

        btn = new Button();

        iv.setFitHeight(20);
        iv.setFitWidth(20);
        btn.setGraphic(iv);
        // action event
        btn.setOnAction(click);
        textField.setOnKeyPressed(key);

        areas.add(textField, 0, 0);
        areas.add(btn, 1, 0);

    }

    private EventHandler<ActionEvent> click = (ActionEvent e) -> {
        userSend = pnlRegistro.user;
        userRecibe = (String) pnlUsuarios.cbUsuariosCreados.getValue();
        pnlConversacion.mostrarMensaje(textField.getText(), "E");
        ctrl.enviarMensaje(0, textField.getText());
        String mensaje = userSend + "*" + userRecibe + "*" + textField.getText();
        textField.setText("");
    };

    private EventHandler<KeyEvent> key = (KeyEvent e) -> {
        if (e.getCode() == KeyCode.ENTER) {
            userSend = pnlRegistro.user;
            userRecibe = (String) pnlUsuarios.cbUsuariosCreados.getValue();
            pnlConversacion.mostrarMensaje(textField.getText(), "E");
            String mensaje = userSend + "*" + userRecibe + "*" + textField.getText();
            ctrl.enviarMensaje(0, mensaje);
            textField.setText("");
        }
    };
}
