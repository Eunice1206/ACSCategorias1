/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import javafx.scene.control.Alert;

/**
 *
 * @author eunic
 */
public class CrearAlerta {
     public void Alerta(String titulo,String cabecera,String cuerpo)
    {
        Alert alerta=new Alert(Alert.AlertType.ERROR); //CREAMOS UN NUEVO OBJETO ALERT
        alerta.setTitle(titulo);//LA PROPIEDAD DE TITULO LE PASAMOS EL PARAMETRO titulo
        alerta.setHeaderText(cabecera);//LA PROPIEDAD DE CABECERA LE PASAMOS EL PARAMETRO cabecera
        alerta.setContentText(cuerpo);//LA PROPIEDAD DE CUERPO LE PASAMOS EL PARAMETRO cuerpo
        alerta.showAndWait();//MANDAMOS A LLAMAR LA FUNCION SHOW AND WAIT, LA CUAL NOS MOSTRARA LA ALERTA CON LAS NUEVAS PROPIEDADES DE LA ALERTA
    }
}
