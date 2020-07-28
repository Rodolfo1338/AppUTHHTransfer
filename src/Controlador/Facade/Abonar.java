/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Facade;

import Modelo.DAO.DAOAbonarcuenta;
import Modelo.VO.VOAbonarcuenta;
import javax.swing.JOptionPane;


public class Abonar {
    VOAbonarcuenta modeloVO;
    DAOAbonarcuenta modeloDAO;
    
    public void AbonarCuenta(String numcuenta, int importe){
        modeloVO= new VOAbonarcuenta();
        modeloDAO= new DAOAbonarcuenta();
        modeloVO.setNumcuenta(numcuenta);
        modeloVO.setImporte(importe);
        if(modeloDAO.NewAbono(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Abono Realizado", "Atención", JOptionPane.INFORMATION_MESSAGE);
            
        }else{
            JOptionPane.showMessageDialog(null, "Abono Fallido", "Atención", JOptionPane.INFORMATION_MESSAGE);
            
        }
        
    }
}
