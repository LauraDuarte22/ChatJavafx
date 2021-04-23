/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorClient;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author admin
 */
public class PanelRegistro {

    private ControladorClient ctrl;
    public HBox hb;
    public GridPane gridPane;
    private Label lblUserName;
    public TextField txtUserName;
    public Button btnLogin;
    public BorderPane registro;
    public Button btnRegistro;
    public String user;
    public boolean usuarioExistente;
    public boolean usuarioRegistrado;
    
    public PanelRegistro(ControladorClient ctrl) {
        this.ctrl = ctrl;
        hb = new HBox();
        gridPane = new GridPane();
        registro = new BorderPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        lblUserName = new Label("Usuario");
        txtUserName = new TextField();
        btnLogin = new Button("Ingresar");
        btnRegistro = new Button("Registrarse");

        gridPane.add(lblUserName, 0, 0);
        gridPane.add(txtUserName, 1, 0);
        gridPane.add(btnLogin, 1, 1);
        gridPane.add(btnRegistro, 1, 2);
        user = txtUserName.getText();

        registro.setTop(hb);
        registro.setCenter(gridPane);

    }

    public void insertUser(String usuario) {
        usuarioExistente = true;
        ctrl.enviarMensaje(1, usuario);
        user = usuario;
        txtUserName.setText("");
    }

    public void errorRegistro(String msg) {
        usuarioExistente = false;
        Alert fail= new Alert(AlertType.ERROR);
        fail.setHeaderText(null);
        fail.setContentText(msg);
        fail.showAndWait();    
    }
    public void errorIngreso(String msg){
        usuarioRegistrado = true;
        Alert fail= new Alert(AlertType.ERROR);
        fail.setHeaderText(null);
        fail.setContentText(msg);
        fail.showAndWait();  
    }
    
    public void registrarUsuario(String usuario){
        usuarioRegistrado = false;
        ctrl.enviarMensaje(5, usuario);
        user = usuario;
        txtUserName.setText("");
    }
}
