/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.Conexion;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.Descuento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eunic
 */
public class DescuentoDaoImpl implements IDescuentoDAO{
    
    final String UPDATE = "UPDATE descuento SET nombreProducto=?, precio=?,descuento=?, precioDescuento=?, fechaInicio=?, fechaFin=? WHERE idDescuento=?";
    final String OBTENERTODOS = "SELECT idDescuento, nombreProducto, precio, descuento, precioDescuento, fechaInicio, fechaFin FROM descuento";
    
    
    Conexion cc = new Conexion();
    Connection conn = cc.obtenerConexion();
    @Override
    public List<Descuento> buscarDescuentoNombre(String nombre) {
         Conexion conexion = new Conexion();
        List<Descuento> descuentos = new ArrayList<>();
      
        String sql = "SELECT * FROM descuento WHERE nombreProducto like '%"+nombre+"%'"; //collate Latin1_General_CI_AI
        
        try {
            Connection conn = conexion.obtenerConexion();
            Statement sentencia = conn.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            
            while (resultado.next()) {
               Descuento descuento = new Descuento();
               descuento.setIdDescuento(resultado.getInt("idDescuento"));
               descuento.setNombreProducto(resultado.getString("nombreProducto"));
               descuento.setPrecio(resultado.getDouble("precio"));
               descuento.setDescuento(resultado.getInt("descuento"));
               descuento.setPrecioDescuento(resultado.getDouble("precioDescuento"));
               descuento.setFechaInicio(resultado.getString("fechaInicio"));
               descuento.setFechaFin(resultado.getString("fechaFin"));
               descuentos.add(descuento);
                }
                    conn.close(); 
            
            } catch (SQLException ex) {
                Logger.getLogger(DescuentoDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return descuentos;
    }

    @Override
    public void agregar(Descuento dato) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminar(Descuento dato) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void editar(Descuento dato) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, dato.getNombreProducto());
            stat.setDouble(2, dato.getPrecio());
            stat.setInt(3, dato.getDescuento());
            stat.setDouble(4, dato.getPrecioDescuento());
            stat.setString(5, dato.getFechaInicio());
            stat.setString(6, dato.getFechaFin());
            stat.setInt(7, dato.getIdDescuento());
            stat.execute();
            
            if(stat.executeUpdate()==0){
                throw new DAOException("No se actualizó la promoción");
            }
        }catch(SQLException ex){
            throw new DAOException("Error de SQL", ex);
        }finally{
            if(stat!=null){
                try{
                    stat.close();
                }catch(SQLException ex){
                    throw new DAOException("Error de SQL", ex);
                }
            }
        }
    }

    @Override
    public List<Descuento> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Descuento> descuentos = new ArrayList<>();
        try{
            stat = conn.prepareStatement(OBTENERTODOS);
            rs = stat.executeQuery();
            while(rs.next()){
                descuentos.add(convertir(rs));
            }
        }catch(SQLException ex){
            throw new DAOException("Error en SQL", ex);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch(SQLException ex){
                    new DAOException("Error en SQL", ex);
                }
            }
            if(stat != null){
                try{
                    stat.close();
                }catch(SQLException ex){
                   new DAOException("Error en SQL", ex);
                }
            }
        }
        return descuentos;
    }
    
    private Descuento convertir(ResultSet rs) throws SQLException{
        int idDescuento = rs.getInt("idDescuento");//modificacion
        String nombreProducto = rs.getString("nombreProducto");
        double precio = rs.getDouble("precio");
        int descuento = rs.getInt("descuento");
        double precioDescuento = rs.getDouble("precioDescuento");
        String fechaInicio = rs.getString("fechaInicio");
        String fechaFin = rs.getString("fechaFin");
        Descuento descuento1 = new Descuento(idDescuento, nombreProducto, precio, descuento, precioDescuento, fechaInicio, fechaFin);
        return descuento1;
    }

    @Override
    public Descuento obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}