/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Excepciones.DAOException;
import java.util.List;

/**
 *
 * @author eunic
 */
public interface DAO<T, K> {
    void agregar(T dato) throws DAOException;
    void eliminar(T dato) throws DAOException;
    void editar(T dato) throws DAOException;
    List<T> obtenerTodos() throws DAOException;
    T obtener(K id) throws DAOException;
}
