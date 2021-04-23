/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import controlador.ControladorClient;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author admin
 */
public class PanelUsuarios {

    protected TextField textField;
    protected Button btn;
    protected GridPane areas;
    private ControladorClient ctrl;
    public ComboBox cbUsuariosCreados;
    public Label labelPara;
    public PanelRegistro pnlRegistro;
    private ObservableList<String> users;
    private PanelConversacion pnlConversacion;

    public PanelUsuarios(ControladorClient ctrl, PanelRegistro pnlRegistro, PanelConversacion pnlConversacion) {
        this.ctrl = ctrl;
        this.pnlRegistro = pnlRegistro;
        this.pnlConversacion = pnlConversacion;
        areas = new GridPane();
        //Cuadrícula De - Para
        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.RIGHT);
        cc.setPercentWidth(10);
        areas.getColumnConstraints().add(cc);
        //Cuadrícula ComboBox
        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setHalignment(HPos.CENTER);
        cc1.setPercentWidth(50);
        areas.getColumnConstraints().add(cc1);
        // Cuadrícula botón
        ColumnConstraints cc2 = new ColumnConstraints();
        cc2.setHalignment(HPos.CENTER);
        cc2.setPercentWidth(35);
        areas.getColumnConstraints().add(cc2);

        labelPara = new Label("Para: ");

        btn = new Button();
        btn.setText("Cerrar Sesión");

        areas.add(btn, 2, 0);

        areas.add(labelPara, 0, 0);

    }

    public void updateEstado() {
        ctrl.enviarMensaje(2, pnlRegistro.user);
    }

    public void requestUser() {
        ctrl.enviarMensaje(3, pnlRegistro.user);
    }

    public void showUser(String msg) {
        String[] user = msg.split("\\*");
        users = FXCollections.observableArrayList(user);
        cbUsuariosCreados = new ComboBox(users);
        cbUsuariosCreados.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // If the condition is not met and the new value is not null: "rollback"
                if (newValue != null) {
                    if(oldValue != newValue){
                        pnlConversacion.grid.getChildren().clear();
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            verMensajes();
                            
                        }
                    });
                }
                
            }
        });
        cbUsuariosCreados.setPrefSize(150, 5);
        areas.add(cbUsuariosCreados, 1, 0);

    }

    public void verMensajes() {
        String usuario = cbUsuariosCreados.getValue().toString();
        System.out.println(usuario);
        ctrl.enviarMensaje(4, usuario + "*" + pnlRegistro.user);
    }

}
