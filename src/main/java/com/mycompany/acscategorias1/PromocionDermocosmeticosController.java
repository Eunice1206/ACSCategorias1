/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;

import DAO.PromocionDaoImpl;
import Excepciones.DAOException;
import Modelo.CrearAlerta;
import Modelo.Promocion;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author eunic
 */
public class PromocionDermocosmeticosController implements Initializable {


    @FXML
    private ComboBox<String> cBoxSubDermocosmeticos;
    @FXML
    private TextField txtTipoDermocosmeticos;
    @FXML
    private ComboBox<String> cBoxPromocion;
    @FXML
    private TextField txtCantidadMinima;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFin;
    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnAgregar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> opciones = FXCollections.observableArrayList(
                "Capilar",
                "Corporal",
                "Facial",
                "Nutricosméticos"
        );
         
        cBoxSubDermocosmeticos.setItems(opciones);
        
        ObservableList<String> opciones1 = FXCollections.observableArrayList(
                "2x1",
                "3x2",
                "4x2"
        );
        
        cBoxPromocion.setItems(opciones1);
    }    
    

    @FXML
    private void agregarPromocion(ActionEvent event) throws DAOException {
        try{
            if(validarCamposVacios()!=true){
                CrearAlerta alerta7 = new CrearAlerta();
                alerta7.Alerta("ERROR", "CAMPOS VACÍOS", "LLENE TODOS LOS CAMPOS");
            }else{
            
                String subcategoria = this.cBoxSubDermocosmeticos.getValue();
                String tipo = this.txtTipoDermocosmeticos.getText();
                String promocion = this.cBoxPromocion.getValue();
                int cantidadMinima = Integer.parseInt(this.txtCantidadMinima.getText());
                
                LocalDate fechaInicio = dateInicio.getValue();
                LocalDate fechaFin = dateFin.getValue();
            
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String fechaInicioStr = fechaInicio.format(formatter);
                String fechaFinStr = fechaFin.format(formatter);

                Promocion promocion1 = new Promocion(subcategoria, tipo, promocion, cantidadMinima, fechaInicioStr, fechaFinStr);
                PromocionDaoImpl dao = new PromocionDaoImpl();
                dao.agregar(promocion1);
        
                    CrearAlerta alerta3 = new CrearAlerta();
                    alerta3.Alerta("INFO", "CATEGORIA AÑADIDA", "LA CATEGORIA SE AÑADIO CORRECTAMENTE");
            }
        }catch(NumberFormatException e){
            CrearAlerta alerta2 = new CrearAlerta();
            alerta2.Alerta("ERROR", "FORMATO INCORRECTO", "");
        }
    }
    
    private Boolean validarCamposVacios(){
        Boolean resultado;
        if(cBoxSubDermocosmeticos.getValue() == null || txtTipoDermocosmeticos.getText().isEmpty() || cBoxPromocion.getValue() == null || txtCantidadMinima.getText().isEmpty() || dateInicio.getValue() == null || dateFin.getValue() == null){
            resultado=false;
        }else{
            resultado = true;
        }
        return resultado;
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();

        stage.close();
    }

}
