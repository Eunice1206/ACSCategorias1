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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author eunic
 */
public class MostrarPromocionesController implements Initializable {


    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colSubcategoria;
    @FXML
    private TableColumn<?, ?> colTipo;
    @FXML
    private TableColumn<?, ?> colPromocion;
    @FXML
    private TableColumn<?, ?> colCantidadMinima;
    @FXML
    private TableColumn<?, ?> colInicio;
    @FXML
    private TableColumn<?, ?> colFin;
    @FXML
    private Button btnEliminarProm;
    @FXML
    private TableView<Promocion> tablaPromociones;
    
    public List<Promocion> promo;
    private ObservableList<Promocion> promociones= FXCollections.observableArrayList(); //ArrayList de javafx para ir guardando las categorias


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
        } catch (DAOException ex) {
            Logger.getLogger(MostrarPromocionesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    void init() throws DAOException {
    try {
        // Llama a tu DAO para recuperar todas las categorías desde la base de datos
        PromocionDaoImpl dao = new PromocionDaoImpl();
        promo = dao.obtenerTodos();

        // Crea una ObservableList para las categorías
        ObservableList<Promocion> promociones = FXCollections.observableArrayList(promo);

        // Configura las columnas de la tabla
        colID.setCellValueFactory(new PropertyValueFactory("idPromocion"));
        colSubcategoria.setCellValueFactory(new PropertyValueFactory("subcategoria"));
        colTipo.setCellValueFactory(new PropertyValueFactory("tipo"));
        colPromocion.setCellValueFactory(new PropertyValueFactory("promocion"));
        colCantidadMinima.setCellValueFactory(new PropertyValueFactory("cantidadMinima"));
        colInicio.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        colFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));

        // Asigna la ObservableList a la tabla
        tablaPromociones.setItems(promociones);
    } catch (DAOException e) {
        e.printStackTrace();
    }
    
    }

    @FXML
    private void eliminarPromocion(ActionEvent event) throws DAOException {
        Promocion promocion1 = this.tablaPromociones.getSelectionModel().getSelectedItem();
        
        if(promocion1 == null){
            CrearAlerta alerta4 = new CrearAlerta();
            alerta4.Alerta("ALERTA", "NO SE HA SELECCIONADO LA PROMOCION A ELIMINAR", "");
        }else{
            PromocionDaoImpl dao = new PromocionDaoImpl();
            dao.eliminar(promocion1);
            this.promociones.remove(promocion1);
            this.tablaPromociones.refresh();
            
            CrearAlerta alerta5 = new CrearAlerta();
            alerta5.Alerta("INFO", "La promoción se ha eliminado de manera correcta", "");
            
        }
    }

   
    
}
