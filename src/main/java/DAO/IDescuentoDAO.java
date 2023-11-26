/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.Descuento;
import java.util.List;

/**
 *
 * @author eunic
 */
public interface IDescuentoDAO extends DAO<Descuento, Integer>{
    public List<Descuento> buscarDescuentoNombre(String nombre);
}
