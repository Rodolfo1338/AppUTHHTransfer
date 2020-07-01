/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.FrmPersonas;

/**
 *
 * @author Rodol
 */
public class ModeloMdi {
    FrmPersonas persona=null;
    
    public void iniciarFrmpersonas(){
        
        if(persona==null){
            persona=new FrmPersonas();
        }
        persona.setVisible(true);
        
    }
}
