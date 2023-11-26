/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Excepciones.DAOException;
import Modelo.Producto;
import java.util.List;

/**
 *
 * @author eunic
 */
public interface IProductoDAO {
    List<Producto> obtenerStock() throws DAOException;
}
