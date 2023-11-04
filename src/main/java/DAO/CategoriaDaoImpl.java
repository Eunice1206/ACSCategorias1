/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConexionBD.Conexion;
import Excepciones.DAOException;
import Modelo.Categoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eunic
 */
public class CategoriaDaoImpl implements ICategoriaDAO{
    private ICategoriaDAO categorias = null;
    
    final String INSERT = "INSERT INTO categoria(categoria, subcategoria, descripcion) VALUES(?,?,?)";
    final String UPDATE = "UPDATE categoria SET categoria=?, subcategoria=?,descripcion=? WHERE idCategoria=?";
    final String DELETE = "DELETE FROM categoria WHERE idCategoria=?";
    final String OBTENERID = "SELECT idCategoria FROM categoria WHERE categoria=? AND subcategoria=? AND descripcion=?";
    final String OBTENERTODOS = "SELECT idCategoria, categoria, subcategoria, descripcion FROM categoria";
  
    
    Conexion cc = new Conexion();
    Connection conn = cc.obtenerConexion();
    

    @Override
    public void agregar(Categoria dato) throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs= null;
        try{
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, dato.getCategoria());
            stat.setString(2, dato.getSubcategoria());
            stat.setString(3, dato.getDescripcion());
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
    public void eliminar(Categoria dato) throws DAOException {
       PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(DELETE);
            stat.setInt(1, dato.getId());//modificacion
            System.out.println(dato.getId());
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
    public void editar(Categoria dato) throws DAOException {
        PreparedStatement stat = null;
        try{
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, dato.getCategoria());
            stat.setString(2, dato.getSubcategoria());
            stat.setString(3, dato.getDescripcion());
            stat.setInt(4, dato.getId());
            stat.execute();
            if(stat.executeUpdate()==0){
                throw new DAOException("No se actualizó la categoría");
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
    public List<Categoria> obtenerTodos() throws DAOException {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Categoria> categorias = new ArrayList<>();
        try{
            stat = conn.prepareStatement(OBTENERTODOS);
            rs = stat.executeQuery();
            while(rs.next()){
                categorias.add(convertir(rs));
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
        return categorias;
    }
    
     private Categoria convertir(ResultSet rs) throws SQLException{
        int id = rs.getInt("idCategoria");//modificacion
        String categoria = rs.getString("categoria");
        String subcategoria = rs.getString("subcategoria");
        String descripcion = rs.getString("descripcion");
        Categoria categoria1 = new Categoria(id, categoria, subcategoria, descripcion);//modificacion
        return categoria1;
    }

    @Override
    public Categoria obtener(Integer id) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int ObtenerId(Categoria categoria) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        int idCategoria = 0;
        try{
            stat = conn.prepareStatement(OBTENERID);
            stat.setString(1, categoria.getCategoria());
            stat.setString(2, categoria.getSubcategoria());
            stat.setString(3, categoria.getDescripcion());
            rs = stat.executeQuery();
            if(rs.next()){
                idCategoria = rs.getInt("idCategoria");
            }
            idCategoria = rs.getInt("idCategoria");
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
        return idCategoria;
    }

    /*public static void main(String[] args){
        int id;
        Categoria c = new Categoria("hf", "gd", "ghd");
        CategoriaDaoImpl aux = new CategoriaDaoImpl();
        id = aux.ObtenerId(c);
        System.out.println(id + "///");
    }*/
}

