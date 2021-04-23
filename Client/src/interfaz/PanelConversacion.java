package interfaz;

import controlador.ControladorClient;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author admin
 */
public class PanelConversacion {

    private ControladorClient ctrl;
    private PanelEnviar pnlEnviar;
    public VBox area;
    public GridPane grid;
    private int fila = 0;
    public ScrollPane scroll;

    public PanelConversacion(ControladorClient ctrl, PanelEnviar pnlEnviar) {
        this.ctrl = ctrl;
        this.pnlEnviar = pnlEnviar;

        area = new VBox();
        scroll = new ScrollPane();
        grid = new GridPane();
        scroll.setContent(grid);

        ColumnConstraints cc = new ColumnConstraints();
        cc.setHalignment(HPos.CENTER);
        cc.setPercentWidth(45.);
        grid.getColumnConstraints().add(cc);
        grid.getStyleClass().add("root");

        ColumnConstraints cc1 = new ColumnConstraints();
        cc1.setHalignment(HPos.CENTER);
        cc1.setPercentWidth(45.);
        grid.getColumnConstraints().add(cc1);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

    }
    
 

    public void mostrarMensaje(String mensaje, String origen) {

        Label label = new Label(mensaje);

        label.setWrapText(true);
        label.setPrefWidth(145.);
        if (origen.equals("E")) {
            label.setAlignment(Pos.CENTER_RIGHT);
            label.getStyleClass().add("enviado");
            grid.add(label, 1, fila);

        } else {
            label.setAlignment(Pos.CENTER_LEFT);
            label.getStyleClass().add("recibido");
            grid.add(label, 0, fila);
        }
        fila++;
    }

}
