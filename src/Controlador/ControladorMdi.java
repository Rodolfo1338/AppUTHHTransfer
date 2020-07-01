/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloMdi;
import Vista.FrmInicio;
import Vista.FrmPersonas;
import Vista.FrmServicios;
import Vista.FrmTiposcuentas;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Rodol
 */
public class ControladorMdi implements ActionListener{
    
    FrmInicio vistamdi;
    
    FrmPersonas formulariopersonas;
    ControladorPersonas controladorpersonas;
    
    FrmServicios formularioservicios;
    ControladorServicios controladorservicios;
    
    FrmTiposcuentas formulariotipocuentas;
    ControladorTipoCuentas controladortipocuentas;

    public ControladorMdi(FrmInicio vistamdi) {
        this.vistamdi = vistamdi;
        
        
        this.vistamdi.btnpersonas.addActionListener(this);
        this.vistamdi.btnservicios.addActionListener(this);
        this.vistamdi.btntipocuentas.addActionListener(this);
    }
    
    public void iniciar(){
        vistamdi.setTitle("Pantalla Principal");
        vistamdi.setLocationRelativeTo(null);
        vistamdi.setExtendedState(MAXIMIZED_BOTH);
    }
    


    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==vistamdi.btnpersonas){
            IniciarPersonas();
            
        }
        if(e.getSource()==vistamdi.btnservicios){
            IniciarServicios();
        }
        if(e.getSource()==vistamdi.btntipocuentas){
            IniciarTipoCuentas();
        }
            
    }
    
    private void IniciarPersonas(){
        if(formulariopersonas==null || formulariopersonas.isShowing()==false){
            formulariopersonas= new FrmPersonas();
            controladorpersonas = new ControladorPersonas(formulariopersonas);
            formulariopersonas.toFront();
            formulariopersonas.show();
        }
    }
    
    private void IniciarServicios(){
        if(formularioservicios==null || formularioservicios.isShowing()==false){
            formularioservicios=new FrmServicios();
            controladorservicios= new ControladorServicios(formularioservicios);
            formularioservicios.toFront();
            formularioservicios.show();
        }
    }
    private void IniciarTipoCuentas(){
        if(formulariotipocuentas==null || formulariotipocuentas.isShowing()==false){
            formulariotipocuentas= new FrmTiposcuentas();
            controladortipocuentas= new ControladorTipoCuentas(formulariotipocuentas);
            formulariotipocuentas.toFront();
            formulariotipocuentas.show();
        }
    }
    
}
