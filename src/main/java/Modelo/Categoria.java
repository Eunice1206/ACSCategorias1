/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import DAO.CategoriaDaoImpl;
import DAO.ICategoriaDAO;
import java.util.Objects;

/**
 *
 * @author eunic
 */
public class Categoria {
    private int id;
    private String categoria;
    private String subcategoria;
    private String descripcion;

    public Categoria(int id, String categoria, String subcategoria, String descripcion) {
        this.id = id;
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
    }

    public Categoria(String categoria, String subcategoria, String descripcion) {
        this.categoria = categoria;
        this.subcategoria = subcategoria;
        this.descripcion = descripcion;
    }
    
     public Categoria() {
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Categoria other = (Categoria) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        if (!Objects.equals(this.subcategoria, other.subcategoria)) {
            return false;
        }
        return Objects.equals(this.descripcion, other.descripcion);
    }
    
    /*public Categoria buscarCategoriaNombre(String nombreCliente) {
        ICategoriaDAO dao = new CategoriaDaoImpl();
        Categoria categoriaEncontrada = new Categoria();
        categoriaEncontrada = dao.buscarCategoriaNombre(nombreCliente);
        return categoriaEncontrada;
    }*/
}
