/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.ImagenFondo.ImagenFondo;
import Modelo.ModeloMdi;
import Vista.FRMAbonarcuenta;
import Vista.FRMAsignarconceptos;
import Vista.FRMCarreras;
import Vista.FRMConceptos;
import Vista.FRMCrearcuentas;
import Vista.FRMLogin;
import Vista.FRMPreguntas;
import Vista.FrmInicio;
import Vista.FrmMaterias;
import Vista.FrmPersonas;
import Vista.FrmPrivilegios;
import Vista.FrmRoles;
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
    ImagenFondo fondo= new ImagenFondo();
    
    FrmInicio vistamdi;
    
    FrmPersonas formulariopersonas;
    ControladorPersonas controladorpersonas;
    
    FrmServicios formularioservicios;
    ControladorServicios controladorservicios;
    
    FrmTiposcuentas formulariotipocuentas;
    ControladorTipoCuentas controladortipocuentas;
    
    FrmRoles formularioroles;
    ControladorRoles controladorroles;
    
    FrmPrivilegios formularioprivilegios;
    ControladorProvilegios controladorprivilegios;
    
    FRMLogin formulariologin;
    Controladorlogin controladorlogin;
    
    FrmMaterias formulariomaterias;
    ControladorMaterias controladormaterias;
    
    FRMConceptos formularioconceptos;
    ControladorConceptos controladorconceptos;

    FRMCarreras formulariocarreras;
    ControladorCarreras controladorcarreras;
    
    FRMPreguntas formulariopreguntas;
    ControladorPreguntas controladorpreguntas;
    
    FRMAsignarconceptos formularioasignaconceptos;
    ControladorConceptosPendientes controladorconceptospendientes;
    
    FRMCrearcuentas formulariocuentas;
    ControladorCuentas controladorcuentas;
    
    FRMAbonarcuenta formularioabonar;
    ControladorAbonacuenta controladorabonar;
    
    
    
    public ControladorMdi(FrmInicio vistamdi) {
        this.vistamdi = vistamdi;
        
        
        this.vistamdi.btnpersonas.addActionListener(this);
        this.vistamdi.btnservicios.addActionListener(this);
        this.vistamdi.btntipocuentas.addActionListener(this);
        this.vistamdi.btnroles.addActionListener(this);
        this.vistamdi.btnprivilegios.addActionListener(this);
        this.vistamdi.btnlogin.addActionListener(this);
        this.vistamdi.btncerrarsesion.addActionListener(this);
        this.vistamdi.btnmaterias.addActionListener(this);
        this.vistamdi.btnconceptos.addActionListener(this);
        this.vistamdi.btncarrera.addActionListener(this);
        this.vistamdi.btnpreguntas.addActionListener(this);
        this.vistamdi.btnasignaconceptos.addActionListener(this);
        this.vistamdi.btncrearcuenta.addActionListener(this);
        this.vistamdi.btnabonarcuenta.addActionListener(this);
                
    }
    
    public void iniciar(){
        cerrar();
        vistamdi.setTitle("UTHH TRANSFER ADMIN");
        vistamdi.setIconImage(null);
        vistamdi.setLocationRelativeTo(null); 
        vistamdi.setExtendedState(MAXIMIZED_BOTH);
        vistamdi.setContentPane(fondo);
        
    }
    
    private void cerrar(){
          vistamdi.menuadministracion.setEnabled(false);
          vistamdi.menudocentes.setEnabled(false);
          vistamdi.menuestadocuentas.setEnabled(false);
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
        if(e.getSource()==vistamdi.btnroles){
            IniciarRoles();
        }
        if(e.getSource()==vistamdi.btnprivilegios){
            IniciarPrivilegios();
        }
        if(e.getSource()==vistamdi.btnlogin){
            IniciarLogin();
        }
        if(e.getSource()==vistamdi.btncerrarsesion){
            cerrar();
        }
        if(e.getSource()==vistamdi.btnmaterias){
            IniciarMaterias();
        }
        if(e.getSource()==vistamdi.btnconceptos){
            IniciarConceptos();
        }
        if(e.getSource()==vistamdi.btncarrera){
            IniciarCarreras();
        }
        if(e.getSource()==vistamdi.btnpreguntas){
            IniciarPreguntas();
        }
        if(e.getSource()==vistamdi.btnasignaconceptos){
            IniciarConeptosPendientes();
        }
        if(e.getSource()==vistamdi.btncrearcuenta){
            IniciarCuentas();
        }
        if(e.getSource()==vistamdi.btnabonarcuenta){
            IniciarAbonarCuentas();
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
    private void IniciarRoles(){
        if(formularioroles==null || formularioroles.isShowing()==false){
            formularioroles= new FrmRoles();
            controladorroles= new ControladorRoles(formularioroles);
            formularioroles.toFront();
            formularioroles.show();
        }
    }

    private void IniciarPrivilegios() {
        if(formularioprivilegios==null || formularioprivilegios.isShowing()==false){
            formularioprivilegios= new FrmPrivilegios();
            controladorprivilegios= new ControladorProvilegios(formularioprivilegios);
            formularioprivilegios.toFront();
            formularioprivilegios.show();
        }
    }
    
    private void IniciarLogin(){
        if(formulariologin==null || formulariologin.isShowing()==false){
            formulariologin= new FRMLogin();
            controladorlogin = new Controladorlogin(formulariologin,vistamdi);
            formulariologin.toFront();
            formulariologin.show();
        }
    }
    
    private void IniciarMaterias(){
        if(formulariomaterias==null || formulariomaterias.isShowing()==false){
            formulariomaterias= new FrmMaterias();
            controladormaterias = new ControladorMaterias(formulariomaterias);
            formulariomaterias.toFront();
            formulariomaterias.show();
        }
    }
    
    private void IniciarConceptos(){
        if(formularioconceptos==null || formularioconceptos.isShowing()==false){
            formularioconceptos= new FRMConceptos();
            controladorconceptos = new ControladorConceptos(formularioconceptos);
            formularioconceptos.toFront();
            formularioconceptos.show();
        }
    }
    
    private void IniciarCarreras(){
        if(formulariocarreras==null || formulariocarreras.isShowing()==false){
            formulariocarreras= new FRMCarreras();
            controladorcarreras = new ControladorCarreras(formulariocarreras);
            formulariocarreras.toFront();
            formulariocarreras.show();
        }
    }
    private void IniciarPreguntas(){
        if(formulariopreguntas==null || formulariopreguntas.isShowing()==false){
            formulariopreguntas= new FRMPreguntas();
            controladorpreguntas = new ControladorPreguntas(formulariopreguntas);
            formulariopreguntas.toFront();
            formulariopreguntas.show();
        }
    }
    private void IniciarConeptosPendientes(){
        if(formularioasignaconceptos==null || formularioasignaconceptos.isShowing()==false){
            formularioasignaconceptos= new FRMAsignarconceptos();
            
            controladorconceptospendientes = new ControladorConceptosPendientes(formularioasignaconceptos);
            formularioasignaconceptos.toFront();
            formularioasignaconceptos.show();
        }
    }
    
    private void IniciarCuentas(){
        if(formulariocuentas==null || formulariocuentas.isShowing()==false){
            formulariocuentas= new FRMCrearcuentas();
            
            controladorcuentas = new ControladorCuentas(formulariocuentas);
            formulariocuentas.toFront();
            formulariocuentas.show();
        }
    }
    private void IniciarAbonarCuentas(){
        if(formularioabonar==null || formularioabonar.isShowing()==false){
            formularioabonar= new FRMAbonarcuenta();
            
            controladorabonar = new ControladorAbonacuenta(formularioabonar);
            formularioabonar.toFront();
            formularioabonar.show();
        }
    }
}
