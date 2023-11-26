/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author eunic
 */
public class PromocionesController implements Initializable {

    @FXML
    private Button btnMostrarPromociones;
    @FXML
    private ImageView imgMedicamentos;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void irVentanaMedicamentos(MouseEvent event) {
            try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionMedicamentos.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    

    @FXML
    private void irVentanaBotiquin(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionBotiquin.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaVitaminas(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionVitaminas.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaCPersonal(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionBelleza.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaDermocosmeticos(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionDermocosmeticos.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaBebes(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionBebes.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaSaludSex(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionSexualidad.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void irVentanaAlimentos(MouseEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("PromocionAlimentos.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @FXML
    private void mostrarPromociones(ActionEvent event) {
        try {
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("MostrarPromociones.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    
}
