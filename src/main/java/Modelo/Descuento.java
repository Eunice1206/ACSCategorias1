/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author eunic
 */
public class Descuento {
    private int idDescuento;
    private String nombreProducto;
    private double precio;
    private int descuento;
    private double precioDescuento;
    private String fechaInicio;
    private String fechaFin;

    public Descuento(int idDescuento, String nombreProducto, double precio, int descuento, double precioDescuento, String fechaInicio, String fechaFin) {
        this.idDescuento = idDescuento;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descuento = descuento;
        this.precioDescuento = precioDescuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Descuento(String nombreProducto, double precio, int descuento, double precioDescuento) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descuento = descuento;
        this.precioDescuento = precioDescuento;
    }
    
    


    public Descuento() {
    }

    public int getIdDescuento() {
        return idDescuento;
    }

    public void setIdDescuento(int idDescuento) {
        this.idDescuento = idDescuento;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public double getPrecioDescuento() {
        return precioDescuento;
    }

    public void setPrecioDescuento(double precioDescuento) {
        this.precioDescuento = precioDescuento;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    
    
    
}
    
