/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.Conexion;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eunic
 */
public class ProductoDaoImpl implements IProductoDAO{
    
    final String OBTENERSTOCK = "SELECT nombre, stock FROM producto WHERE stock > 30";
    Conexion cc = new Conexion();
    Connection conn = cc.obtenerConexion();

    @Override
    public List<Producto> obtenerStock() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Producto> productos = new ArrayList<>();
        try {
            stat = conn.prepareStatement(OBTENERSTOCK);
            rs = stat.executeQuery();
            while (rs.next()) {
                productos.add(convertir(rs));
            }
            System.out.println(productos);
        } catch (SQLException ex) {
            throw new DAOException("Error en SQL", ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException ex) {
                    throw new DAOException("Error en SQL", ex);
                }
            }
        }
        return productos;
        
    }
    
    private Producto convertir(ResultSet rs) throws SQLException{
        String nombre = rs.getString("nombre");
        int stock = rs.getInt("stock");
        Producto producto1 = new Producto(nombre, stock);
       
        return producto1;
    }
    
}
