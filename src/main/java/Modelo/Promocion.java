/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author eunic
 */
public class Promocion {
    private int idPromocion;
    private String subcategoria;
    private String tipo;
    private String promocion;
    private int cantidadMinima;
    private String fechaInicio;
    private String fechaFin;

    public Promocion(int idPromocion, String subcategoria, String tipo, String promocion, int cantidadMinima, String fechaInicio, String fechaFin) {
        this.idPromocion = idPromocion;
        this.subcategoria = subcategoria;
        this.tipo = tipo;
        this.promocion = promocion;
        this.cantidadMinima = cantidadMinima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Promocion(String subcategoria, String tipo, String promocion, int cantidadMinima, String fechaInicio, String fechaFin) {
        this.subcategoria = subcategoria;
        this.tipo = tipo;
        this.promocion = promocion;
        this.cantidadMinima = cantidadMinima;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public int getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(int cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
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
