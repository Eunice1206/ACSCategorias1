/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;

import DAO.CategoriaDaoImpl;
import DAO.DescuentoDaoImpl;
import DAO.IDescuentoDAO;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.CrearAlerta;
import Modelo.Descuento;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author eunic
 */
public class DescuentosController implements Initializable {

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Descuento> tablaDescuentos;
    @FXML
    private DatePicker dateFechaInicio;
    @FXML
    private DatePicker dateFechaFin;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtPrecioProducto;
    @FXML
    private TextField txtDescuento;
    
    List<Descuento> descuentosEncontrados;
    private ObservableList<Descuento> descuentos= FXCollections.observableArrayList(); //ArrayList de javafx para ir guardando las categorias
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colPrecio;
    @FXML
    private TableColumn<?, ?> colDescuento;
    @FXML
    private TableColumn<?, ?> colPrecioDescuento;
    @FXML
    private TableColumn<?, ?> colFechaIni;
    @FXML
    private TableColumn<?, ?> colFechaFin;
    
    public List<Descuento> desc;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAgregarPromocion;
    @FXML
    private TextField txtID;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
        } catch (DAOException ex) {
            Logger.getLogger(DescuentosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtNombreProducto.setEditable(false);
        txtPrecioProducto.setEditable(false);
        txtID.setEditable(false);

        this.colID.setCellValueFactory(new PropertyValueFactory("idDescuento"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
        this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.colDescuento.setCellValueFactory(new PropertyValueFactory("descuento"));
        this.colPrecioDescuento.setCellValueFactory(new PropertyValueFactory("precioDescuento"));
        this.colFechaIni.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
        this.colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));
        
        
        
            
    }  

    @FXML
    private void buscarDescuentos(ActionEvent event) {
        String nombreProducto = txtBuscar.getText();
        IDescuentoDAO dao = new DescuentoDaoImpl();
        descuentosEncontrados = dao.buscarDescuentoNombre(nombreProducto);

        // Limpiar la lista de categorías antes de agregar los resultados
        this.descuentos.clear();

        if (!descuentosEncontrados.isEmpty()) {
            this.descuentos.addAll(descuentosEncontrados);
         } else {
            System.out.println("No se encontraron resultados");
        }
        
        System.out.println(descuentosEncontrados);
        this.tablaDescuentos.setItems(descuentos);
    }

    @FXML
    private void seleccionar(MouseEvent event) {
        Descuento descuento1 = this.tablaDescuentos.getSelectionModel().getSelectedItem();
        
        if(descuento1 != null){
            this.txtID.setText(String.valueOf(descuento1.getIdDescuento()));
            this.txtNombreProducto.setText(descuento1.getNombreProducto());
            this.txtPrecioProducto.setText(String.valueOf(descuento1.getPrecio()));
            this.txtDescuento.setText(String.valueOf(descuento1.getDescuento()));

        }
    }

    @FXML
    private void agregarPromocion(ActionEvent event) throws DAOException {
        Descuento descuento1 = this.tablaDescuentos.getSelectionModel().getSelectedItem();
        
        if(descuento1 == null){
            CrearAlerta alerta4 = new CrearAlerta();
            alerta4.Alerta("ERROR", "NO SE HA SELECCIONADO EL DESCUENTO", "");
        }else{
             try{
        int id = Integer.parseInt(this.txtID.getText());
        String nombreProducto = this.txtNombreProducto.getText();
        double precio = Double.parseDouble(this.txtPrecioProducto.getText());
        int descuento = Integer.parseInt(this.txtDescuento.getText());

        // Calcula el precio con descuento
        double precioDescuento = precio - (precio * descuento / 100);
        descuento1.setPrecioDescuento(precioDescuento);
        // Obtener las fechas de los DatePicker y convertirlas en cadenas
            LocalDate fechaInicio = dateFechaInicio.getValue();
            LocalDate fechaFin = dateFechaFin.getValue();

            // Convierte las fechas en cadenas con un formato específico (puedes personalizar el formato)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaInicioStr = fechaInicio.format(formatter);
            String fechaFinStr = fechaFin.format(formatter);

            // Asignar las cadenas de fechas al objeto Descuento
            descuento1.setFechaInicio(fechaInicioStr);
            descuento1.setFechaFin(fechaFinStr);

            Descuento aux = new Descuento(id,nombreProducto, precio, descuento, precioDescuento, fechaInicioStr, fechaFinStr);
            DescuentoDaoImpl dao = new DescuentoDaoImpl();
            dao.editar(aux);

       

        if(!this.descuentos.contains(aux)){
            //categoria1.setId(aux.getId());
            descuento1.setNombreProducto(aux.getNombreProducto());
            descuento1.setPrecio(aux.getPrecio());
            descuento1.setDescuento(aux.getDescuento());
            descuento1.setPrecioDescuento(aux.getPrecioDescuento());
            descuento1.setFechaInicio(aux.getFechaInicio());
            descuento1.setFechaFin(aux.getFechaFin());
            

            this.tablaDescuentos.refresh();

            CrearAlerta alerta3 = new CrearAlerta();
            alerta3.Alerta("INFO", "DESCUENTO AGREGADO", "EL DESCUENTO SE AGREGÓ CORRECTAMENTE");
        }else{
            CrearAlerta alerta = new CrearAlerta();
            alerta.Alerta("ERROR", "INGRESE OTRO DESCUENTO", "LA DESCUENTO YA EXISTE");
        }

        }catch(NumberFormatException e){
            CrearAlerta alerta2 = new CrearAlerta();
            alerta2.Alerta("ERROR", "FORMATO INCORRECTO", "");
        }
        }
    }
        void init()throws DAOException{
            try {
                // Llama a tu DAO para recuperar todas las categorías desde la base de datos
                DescuentoDaoImpl dao = new DescuentoDaoImpl();
                desc = dao.obtenerTodos();

                // Crea una ObservableList para las categorías
                ObservableList<Descuento> descuentos = FXCollections.observableArrayList(desc);

                // Configura las columnas de la tabla
                this.colID.setCellValueFactory(new PropertyValueFactory("idDescuento"));
                this.colNombre.setCellValueFactory(new PropertyValueFactory("nombreProducto"));
                this.colPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
                this.colDescuento.setCellValueFactory(new PropertyValueFactory("descuento"));
                this.colPrecioDescuento.setCellValueFactory(new PropertyValueFactory("precioDescuento"));
                this.colFechaIni.setCellValueFactory(new PropertyValueFactory("fechaInicio"));
                this.colFechaFin.setCellValueFactory(new PropertyValueFactory("fechaFin"));

                // Asigna la ObservableList a la tabla
                tablaDescuentos.setItems(descuentos);
                
            } catch (DAOException e) {
                // Maneja las excepciones si ocurren al obtener las categorías de la base de datos
                e.printStackTrace();
            }
        }
    
    
    
    
    
}
