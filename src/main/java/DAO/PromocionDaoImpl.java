/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.Conexion;
import Excepciones.DAOException;
import Modelo.Promocion;
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
public class PromocionDaoImpl implements IPromocionDAO{
    
    final String INSERT = "INSERT INTO promocion(subcategoria, tipo, promocion, cantidadMinima, fechaInicio, FechaFin) VALUES(?,?,?,?,?,?)";
    final String OBTENERTODOS = "SELECT idPromocion, subcategoria, tipo, promocion, cantidadMinima, fechaInicio, fechaFin FROM promocion";
    final String DELETE = "DELETE FROM promocion WHERE idPromocion=?";
    
    Conexion cc = new Conexion();
    Connection conn = cc.obtenerConexion();

    @Override
    public void agregar(Promocion dato) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, dato.getSubcategoria());
            stat.setString(2, dato.getTipo());
            stat.setString(3, dato.getPromocion());
            stat.setInt(4, dato.getCantidadMinima());
            stat.setString(5, dato.getFechaInicio());
            stat.setString(6, dato.getFechaFin());
            
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
                throw new DAOException("La categoría no se borró");
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
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Promocion> promociones = new ArrayList<>();
        try{
            stat = conn.prepareStatement(OBTENERTODOS);
            rs = stat.executeQuery();
            while(rs.next()){
                promociones.add(convertir(rs));
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
                   new DAOException("Erro en SQL", ex);
                }
            }
        }
        return promociones;
    }
    
    private Promocion convertir(ResultSet rs) throws SQLException{
        int idPromocion = rs.getInt("idPromocion");//modificacion
        String subcategoria = rs.getString("subcategoria");
        String tipo = rs.getString("tipo");
        String promocion = rs.getString("promocion");
        int cantidadMinima = rs.getInt("cantidadMinima");
        String fechaInicio = rs.getString("fechaInicio");
        String fechaFin = rs.getString("fechaFin");
        
        Promocion promocion1 = new Promocion(idPromocion, subcategoria, tipo, promocion, cantidadMinima, fechaInicio, fechaFin);
        return promocion1;
    }

    @Override
    public Promocion obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
