/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.Conexion;
import Excepciones.DAOException;
import Modelo.Categoria;
import Modelo.Promocion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eunic
 */
public class Promociones implements IPromocion{
    final String INSERT = "INSERT INTO promociones(descripcionPromocion, promocion, vigencia) VALUES(?,?,?)";
    final String DELETE = "DELETE FROM promociones WHERE idPromocion=?";
    final String OBTENERID = "SELECT idPromocion FROM promociones WHERE descripcionPromocion=? AND promocion=? AND vigencia=?";


    Conexion cc = new Conexion();
    Connection conn = cc.obtenerConexion();

    @Override
    public void agregar(Promocion dato) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, dato.getDescripcionPromocion());
            stat.setString(2, dato.getPromocion());
            stat.setString(3, dato.getVigencia());
            stat.executeUpdate();
        }catch(SQLException ex){
            throw new DAOException("Eror en sql", ex);
        }finally{
            if(rs!=null){
                try{
                    rs.close();
                }catch (SQLException ex){
                    new DAOException("Error en sql", ex);
                }
            }
            if(stat !=null){
                try {
                    stat.close();
                }catch(SQLException ex){
                    throw new DAOException("Error en sql", ex);
                }
            }
        }
    }
      

    @Override
    public void eliminar(Promocion dato) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, dato.getIdPromocion());//modificacion
            System.out.println(dato.getIdPromocion());
            if(stat.executeUpdate()== 0){
                throw new DAOException("La promocion no se borró");
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
    public void editar(Promocion dato) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Promocion> obtenerTodos() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Promocion obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public int ObtenerId(Promocion promocion) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int idPromocion = 0;
        try{
            stat = conn.prepareStatement(OBTENERID);
            stat.setString(1, promocion.getDescripcionPromocion());
            stat.setString(2, promocion.getPromocion());
            stat.setString(3, promocion.getVigencia());
            rs = stat.executeQuery();
            if(rs.next()){
                idPromocion = rs.getInt("idPromocion");
            }
            idPromocion = rs.getInt("idPromocion");
        }catch(SQLException ex){
            try {
                throw new DAOException("Error en SQL", ex);
            } catch (DAOException ex1) {
                Logger.getLogger(CategoriaDaoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
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
        return idPromocion;   
    }

   
    
    
   

 
    
}
