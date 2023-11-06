/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;


import DAO.CategoriaDaoImpl;
import DAO.Promociones;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.CrearAlerta;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import Modelo.Promocion;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author eunic
 */
public class PromocionesController implements Initializable {


    @FXML
    private TableColumn<?, ?> colDescripProm;
    @FXML
    private TableColumn<?, ?> colProm;
    @FXML
    private TableView<Promocion> tablaPromociones;
    @FXML
    private Button btnAgregarProm;
    @FXML
    private Button btnElimProm;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private TextField txtPromocion;
    private ObservableList<Promocion> promociones= FXCollections.observableArrayList();
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TextField txtVigencia;
    @FXML
    private TableColumn<?, ?> colVigencia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colID.setCellValueFactory(new PropertyValueFactory("idPromocion"));
        this.colDescripProm.setCellValueFactory(new PropertyValueFactory("descripcionPromocion"));
        this.colProm.setCellValueFactory(new PropertyValueFactory("promocion"));
        this.colVigencia.setCellValueFactory(new PropertyValueFactory("vigencia"));
        
    }

    @FXML
    private void agregarPromocion(ActionEvent event) throws DAOException, IOException {
         try{
            if(validarCamposVacios()!=true){
                CrearAlerta alerta7 = new CrearAlerta();
                alerta7.Alerta("ERROR", "CAMPOS VACÍOS", "LLENE TODOS LOS CAMPOS");
            }else{
            
                String descripcionPromocion = this.txtDescripcion.getText();
                String promocion = this.txtPromocion.getText();
                String vigencia = this.txtVigencia.getText();

                Promocion promocion1 = new Promocion(descripcionPromocion, promocion, vigencia);
                Promociones dao = new Promociones();
                dao.agregar(promocion1);
        
        
                //if(!this.categorias.contains(categoria1)){
                if(validarRepetidos(descripcionPromocion, promocion)==true){
                    
                   int id = dao.ObtenerId(promocion1);
                    promociones.add(new Promocion( id,txtDescripcion.getText(),txtPromocion.getText(), txtVigencia.getText()));
                    this.tablaPromociones.setItems(promociones);
                     
                    //CrearAlerta alerta = new CrearAlerta();
                    //alerta.Alerta("INFO", "PROMOCIÓN AGREGADA", "LA PROMOCION SE AGREGÓ DE MANERA CORRECTA");
                  
                }else{
                    CrearAlerta alerta = new CrearAlerta();
                    alerta.Alerta("ERROR", "INGRESE OTRA PROMOCION", "LA PROMOCION YA EXISTE");
                }
            }
        }catch(NumberFormatException e){
            CrearAlerta alerta2 = new CrearAlerta();
            alerta2.Alerta("ERROR", "FORMATO INCORRECTO", "");
        }
    }

    @FXML
    private void eliminarPromocion(ActionEvent event) throws DAOException {
        Promocion promocion1 = this.tablaPromociones.getSelectionModel().getSelectedItem();
        
        if(promocion1 == null){
            CrearAlerta alerta4 = new CrearAlerta();
            alerta4.Alerta("ERROR", "NO SE HA SELECCIONADO LA PROMOCIÓN", "");
        }else{
            Promociones dao = new Promociones();
            dao.eliminar(promocion1);
            this.promociones.remove(promocion1);
            this.tablaPromociones.refresh();
            
            CrearAlerta alerta5 = new CrearAlerta();
            alerta5.Alerta("INFO", "PROMOCION ELIMINADA", "LA PROMOCIÓN SE ELIMINÓ CORRECTAMENTE");
            
        }
        
    }

    @FXML
    private void seleccionar(MouseEvent event) {
    }
    
    private boolean validarRepetidos( String descripcionPromocion, String promocion){
        boolean resultado = true;
        for(int i=0; i<promociones.size(); i++){
            if(promociones.get(i).getDescripcionPromocion().equalsIgnoreCase(descripcionPromocion) && promociones.get(i).getPromocion().equalsIgnoreCase(promocion)){
                resultado = false;
            }
        }
        System.out.println(resultado);
        return resultado;
    }
    
     private Boolean validarCamposVacios(){
        Boolean resultado;
        if(txtDescripcion.getText().isEmpty() || txtPromocion.getText().isEmpty()){
            resultado=false;
        }else{
            resultado = true;
        }
        return resultado;
    }

   
}
