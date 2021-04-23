/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorClient;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author admin
 */
public class InterfazApp extends Application {

    /**
     * @param args the command line arguments
     */
    /**
     * @param args the command line arguments
     */
    private PanelConversacion pnlConversacion;
    private PanelEnviar pnlEnviar;
    private PanelRegistro pnlRegistro;
    private PanelUsuarios pnlUsuarios;
    private ControladorClient ctrl;
    private Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage) throws Exception {
        ctrl = new ControladorClient();
        BorderPane root = new BorderPane();

        pnlConversacion = new PanelConversacion(ctrl, pnlEnviar);
        pnlRegistro = new PanelRegistro(ctrl);
        pnlUsuarios = new PanelUsuarios(ctrl, pnlRegistro);
        pnlEnviar = new PanelEnviar(pnlConversacion, ctrl, pnlUsuarios, pnlRegistro);
        root.setPadding(new Insets(5, 5, 5, 5));
        root.setTop(pnlUsuarios.areas);

        root.setBottom(pnlEnviar.areas);
        root.setCenter(pnlConversacion.scroll);

        ctrl.conectar(pnlConversacion, pnlEnviar, pnlRegistro, pnlUsuarios);
        scene1 = new Scene(pnlRegistro.registro);

        pnlRegistro.btnLogin.setOnAction((ActionEvent event) -> {
            pnlRegistro.insertUser(pnlRegistro.txtUserName.getText());
            if (pnlRegistro.usuarioExistente == true) {
                primaryStage.setScene(scene2);
                pnlUsuarios.requestUser();
                pnlConversacion.grid.getChildren().clear();
            }

        });

        pnlRegistro.btnRegistro.setOnAction((ActionEvent event) -> {
            pnlRegistro.registrarUsuario(pnlRegistro.txtUserName.getText());
            if (pnlRegistro.usuarioRegistrado = false) {
                primaryStage.setScene(scene2);
                pnlUsuarios.requestUser();
                pnlConversacion.grid.getChildren().clear();
            }
        });

        pnlUsuarios.btn.setOnAction((ActionEvent event) -> {
            primaryStage.setScene(scene1);
            pnlUsuarios.updateEstado();
        });

        scene2 = new Scene(root, 305, 500);
        scene2.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene1);

        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setTitle("");

        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

}
