/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.Categoria;
import java.util.List;

/**
 *
 * @author eunic
 */
public interface ICategoriaDAO extends DAO<Categoria, Integer>{
    int ObtenerId(Categoria categoria);
    public List<Categoria> buscarCategoriaNombre(String nombre);
}
