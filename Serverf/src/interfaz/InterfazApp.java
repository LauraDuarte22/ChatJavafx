/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorServer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.swing.JOptionPane;

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
    private ControladorServer ctrl;
    private Scene scene1;
    private PanelExcepciones pnlExcepciones;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Server");
        BorderPane root = new BorderPane();
        
        ctrl = new ControladorServer();
        
        ctrl.connect();
        
        pnlConversacion = new PanelConversacion(ctrl);
        pnlExcepciones = new PanelExcepciones();
        root.setPadding(new Insets(5, 5, 5, 5));
        root.setTop(pnlConversacion.label);
        root.setCenter(pnlConversacion.textArea);
        FlowPane buttons = new FlowPane();
        buttons.getChildren().addAll(pnlExcepciones.label, pnlExcepciones.textArea);
        root.setBottom(buttons);
        
        scene1 = new Scene(root, 300, 500);
        scene1.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        
        primaryStage.setScene(scene1);
        primaryStage.setOnCloseRequest((WindowEvent e) -> {
            Platform.exit();
            System.exit(0);
        });
        ctrl.conectar(pnlConversacion, pnlExcepciones);
        primaryStage.show();
        primaryStage.setResizable(false);
        
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
