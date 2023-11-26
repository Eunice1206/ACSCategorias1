/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;

import ConexionBD.Conexion;
import DAO.CategoriaDaoImpl;
import DAO.ICategoriaDAO;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.CrearAlerta;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eunic
 */
public class GestionarController implements Initializable {

    
    @FXML
    private TextField txtCategoria;
    @FXML
    private TextField txtSubcategoria;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private Button btGuardar;
    @FXML
    private Button btCancelar;
    @FXML
    private TableColumn<Categoria, Integer> colID;
    @FXML
    private TableColumn<Categoria, String> colCategoria;
    @FXML
    private TableColumn<Categoria, String> colSubcategoria;
    @FXML
    private TableColumn<Categoria, String> colDescripcion;
    
    private ObservableList<Categoria> categorias= FXCollections.observableArrayList(); //ArrayList de javafx para ir guardando las categorias
    @FXML
    private TableView<Categoria> tablaCategoria;
    @FXML
    private Button btEliminar;
    @FXML
    private Button btLimpiar;
    @FXML
    private Button btModificar;
    @FXML
    private TextField txtID;
    public List<Categoria> categ;
    @FXML
    private Button btGrafica;
    @FXML
    private Button btnBuscarCategoria;
    @FXML
    private TextField txtBuscar;
    
    List<Categoria> categoriasEncontradas;
    private boolean filaSeleccionada = false;
    
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
        } catch (DAOException ex) {
            Logger.getLogger(GestionarController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Scene scene = tablaCategoria.getScene();

        // Agregar un evento de clic a la escena para deseleccionar la fila de la tabla
      
        tablaCategoria.setRowFactory(tv -> {
            TableRow<Categoria> row = new TableRow<>();
            row.selectedProperty().addListener((obs, wasSelected, isNowSelected) -> {
                if (isNowSelected) {
                    row.setStyle("-fx-background-color: lightblue;");
                } else {
                    row.setStyle("");
                }
            });
            return row;
        });
        
        this.colID.setCellValueFactory(new PropertyValueFactory("id"));
        this.colCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        this.colSubcategoria.setCellValueFactory(new PropertyValueFactory("subcategoria"));
        this.colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        
    }    
    
    //Metodo para validar que los campos esten completamente llenos
     private Boolean validarCamposVacios(){
        Boolean resultado;
        if(txtCategoria.getText().isEmpty() || txtSubcategoria.getText().isEmpty() || txtDescripcion.getText().isEmpty()){
            resultado=false;
        }else{
            resultado = true;
        }
        return resultado;
    }
    
     /*
     Método para agregar una categoria en la tabla de la vista y también en la base de datos
     */
    @FXML
    private void agregarCategoria(ActionEvent event) throws DAOException {
        try{
            if(validarCamposVacios()!=true){
                CrearAlerta alerta7 = new CrearAlerta();
                alerta7.Alerta("ERROR", "CAMPOS VACÍOS", "LLENE TODOS LOS CAMPOS");
            }else{
            
                String categoria = this.txtCategoria.getText();
                String subcategoria = this.txtSubcategoria.getText();
                String descripcion = this.txtDescripcion.getText();

                Categoria categoria1 = new Categoria(categoria, subcategoria, descripcion);
                CategoriaDaoImpl dao = new CategoriaDaoImpl();
                dao.agregar(categoria1);
        
        
                //if(!this.categorias.contains(categoria1)){
                if(validarRepetidos(categoria, subcategoria, descripcion)==true){
                    
                    int id = dao.ObtenerId(categoria1);
                    categorias.add(new Categoria(id, txtCategoria.getText(),txtSubcategoria.getText(),txtDescripcion.getText()));
                    this.tablaCategoria.setItems(categorias);
                    
                    limpiarCamposs();
                    init();
                    
                    CrearAlerta alerta3 = new CrearAlerta();
                    alerta3.Alerta("INFO", "CATEGORIA AÑADIDA", "LA CATEGORIA SE AÑADIO CORRECTAMENTE");
                }else{
                    CrearAlerta alerta = new CrearAlerta();
                    alerta.Alerta("ERROR", "INGRESE OTRA CATEGORIA", "LA CATEGORIA YA EXISTE");
                }
            }
        }catch(NumberFormatException e){
            CrearAlerta alerta2 = new CrearAlerta();
            alerta2.Alerta("ERROR", "FORMATO INCORRECTO", "");
        }
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Categoria categoria1 = this.tablaCategoria.getSelectionModel().getSelectedItem();
        
        
        if(categoria1 != null){
            this.txtID.setText(String.valueOf(categoria1.getId()));
            this.txtCategoria.setText(categoria1.getCategoria());
            this.txtSubcategoria.setText(categoria1.getSubcategoria());
            this.txtDescripcion.setText(categoria1.getDescripcion());
        }
    }
    
    /**
     Método para eliminar una categoria en la tabla de la vista y también en la base de datos
     * @param event
     * @throws DAOException 
     */
    @FXML
    private void eliminar(ActionEvent event) throws DAOException {
        Categoria categoria1 = this.tablaCategoria.getSelectionModel().getSelectedItem();
        
        if(categoria1 == null){
            CrearAlerta alerta4 = new CrearAlerta();
            alerta4.Alerta("ERROR", "NO SE HA SELECCIONADO LA CATEGORIA", "");
        }else{
            CategoriaDaoImpl dao = new CategoriaDaoImpl();
            dao.eliminar(categoria1);
            this.categorias.remove(categoria1);
            this.tablaCategoria.refresh();
            
            CrearAlerta alerta5 = new CrearAlerta();
            alerta5.Alerta("INFO", "CATEGORÍA ELIMINADA", "LA CATEGORIA SE ELIMINÓ CORRECTAMENTE");
            
        }
    }

    /**
     *Método para modificar una categoria en la tabla de la vista y también en la base de datos
     * @param event
     * @throws DAOException 
     */
    @FXML
    private void modificarCategoria(ActionEvent event) throws DAOException {
        Categoria categoria1 = this.tablaCategoria.getSelectionModel().getSelectedItem();
        
        if(categoria1 == null){
            CrearAlerta alerta4 = new CrearAlerta();
            alerta4.Alerta("ERROR", "NO SE HA SELECCIONADO LA CATEGORIA", "");
        }else{
             try{
            
        int id = Integer.parseInt(this.txtID.getText());
        String categoria = this.txtCategoria.getText();
        String subcategoria = this.txtSubcategoria.getText();
        String descripcion = this.txtDescripcion.getText();
        
        Categoria aux = new Categoria( id, categoria, subcategoria, descripcion);
        CategoriaDaoImpl dao = new CategoriaDaoImpl();
        dao.editar(aux);
        
        if(!this.categorias.contains(aux)){
            //categoria1.setId(aux.getId());
            categoria1.setCategoria(aux.getCategoria());
            categoria1.setSubcategoria(aux.getSubcategoria());
            categoria1.setDescripcion(aux.getDescripcion());
            
            this.tablaCategoria.refresh();
            limpiarCamposs();
            
            CrearAlerta alerta3 = new CrearAlerta();
            alerta3.Alerta("INFO", "CATEGORIA MODIFICADA", "LA CATEGORIA SE MODIFICÓ CORRECTAMENTE");
        }else{
            CrearAlerta alerta = new CrearAlerta();
            alerta.Alerta("ERROR", "INGRESE OTRA CATEGORIA", "LA CATEGORIA YA EXISTE");
        }
        
        }catch(NumberFormatException e){
            CrearAlerta alerta2 = new CrearAlerta();
            alerta2.Alerta("ERROR", "FORMATO INCORRECTO", "");
        }
        }
    }
    
    @FXML
    private void cancelar(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Método para limpiar todos los cuadros de texto que hayan sido llenados
     * @param event 
     */
    @FXML
    private void limpiarCampos(ActionEvent event) {
        limpiarCamposs();
    }
    
    /**
     * Este método valida si la información de la tabla ya ha sido agregada anteriormente
     * @param categoria
     * @param subcategoria
     * @param descripcion
     * @return 
     */
    private boolean validarRepetidos(String categoria, String subcategoria, String descripcion){
        boolean resultado = true;
        for(int i=0; i<categorias.size(); i++){
            /*System.out.println(categorias.get(i).getCategoria() + "== " + categoria);
            System.out.println(categorias.get(i).getSubcategoria() + "== " + subcategoria);
            System.out.println(categorias.get(i).getDescripcion() + "== " + descripcion);*/
            if(categorias.get(i).getCategoria().equalsIgnoreCase(categoria) && categorias.get(i).getSubcategoria().equalsIgnoreCase(subcategoria) && categorias.get(i).getDescripcion().equalsIgnoreCase(descripcion)){
                resultado = false;
            }
        }
        System.out.println(resultado);
        return resultado;
    }
    
   void init() throws DAOException {
    try {
        // Llama a tu DAO para recuperar todas las categorías desde la base de datos
        CategoriaDaoImpl dao = new CategoriaDaoImpl();
        categ = dao.obtenerTodos();

        // Crea una ObservableList para las categorías
        ObservableList<Categoria> categorias = FXCollections.observableArrayList(categ);

        // Configura las columnas de la tabla
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        colSubcategoria.setCellValueFactory(new PropertyValueFactory("subcategoria"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));

        // Asigna la ObservableList a la tabla
        tablaCategoria.setItems(categorias);
    } catch (DAOException e) {
        // Maneja las excepciones si ocurren al obtener las categorías de la base de datos
        e.printStackTrace();
    }
}


      @FXML
    private void IrVentanaDescuentos(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("OpcionesPromociones.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void buscarCategoria(ActionEvent event) throws DAOException {
        
        String nombreCategoria = txtBuscar.getText();
        ICategoriaDAO dao = new CategoriaDaoImpl();
        categoriasEncontradas = dao.buscarCategoriaNombre(nombreCategoria);

        // Limpiar la lista de categorías antes de agregar los resultados
        this.categorias.clear();

        if (!categoriasEncontradas.isEmpty()) {
            this.categorias.addAll(categoriasEncontradas);
            limpiarCamposs();
         } else {
            CrearAlerta alerta9 = new CrearAlerta();
            alerta9.Alerta("INFO", "CATEGORIA NO ENCONTRADA", "No existe ninguna categoria con ese nombre");
            

            
        }
        
        System.out.println(categoriasEncontradas);
        this.tablaCategoria.setItems(categorias);
    }
    
    private void limpiarCamposs() {
        txtID.clear();
        txtCategoria.clear();
        txtSubcategoria.clear();
        txtDescripcion.clear();
    }
  
   
}
