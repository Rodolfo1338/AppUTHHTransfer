/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pincipal;

import Controlador.ControladorMdi;
import Modelo.ModeloMdi;
import Vista.FrmInicio;

/**
 *
 * @author Rodol
 */
public class AppUthhTransfer {
    
    public static void main(String[] args) {
        
        
        FrmInicio vista= new FrmInicio();
        ControladorMdi controlador = new ControladorMdi(vista);
        
        controlador.iniciar();
        vista.setVisible(true);
    }
}
