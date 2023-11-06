/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.acscategorias1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 * FXML Controller class
 *
 * @author eunic
 */
public class GraficaController implements Initializable {

    @FXML
    private NumberAxis producto;
    @FXML
    private CategoryAxis categoria;
    @FXML
    private BarChart<?, ?> categorias;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series set1 = new XYChart.Series<>();

        set1.setName("2023");
        set1.getData().add(new XYChart.Data("Medicamento", 500));
        set1.getData().add(new XYChart.Data("Dermatologicos", 200));


        categorias.getData().addAll(set1);
        
        
        
        }

    }
