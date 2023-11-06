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
    private String descripcionPromocion;
    private String promocion;
    private String vigencia;

    public Promocion(String descripcionPromocion, String promocion, String vigencia) {
        this.descripcionPromocion = descripcionPromocion;
        this.promocion = promocion;
        this.vigencia = vigencia;
    }

    public Promocion(int idPromocion, String descripcionPromocion, String promocion, String vigencia) {
        this.idPromocion = idPromocion;
        this.descripcionPromocion = descripcionPromocion;
        this.promocion = promocion;
        this.vigencia = vigencia;
    }

    public Promocion() {
    }

    public int getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(int idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getDescripcionPromocion() {
        return descripcionPromocion;
    }

    public void setDescripcionPromocion(String descripcionPromocion) {
        this.descripcionPromocion = descripcionPromocion;
    }

    public String getPromocion() {
        return promocion;
    }

    public void setPromocion(String promocion) {
        this.promocion = promocion;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }
    
    

    
    
    
    
    
}
