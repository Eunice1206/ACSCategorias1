/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import Modelo.Categoria;
import Modelo.Promocion;

/**
 *
 * @author eunic
 */
public interface IPromocion extends DAO<Promocion, Integer>{
    int ObtenerId(Promocion promocion);
}
