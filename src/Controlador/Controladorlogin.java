/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Interfaz.ILogin;
import Controlador.Proxy.LoginProxy;
import Modelo.Login;
import Vista.FRMLogin;
import Vista.FRMNuevacontraseña;
import Vista.FRMPreguntas;
import Vista.FRMRecuperacontraseña;
import Vista.FrmInicio;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodol
 */
public class Controladorlogin implements ActionListener{

    FRMLogin vistalogin;
    FrmInicio vistaMDI;
    FRMRecuperacontraseña vistarecuperarcontraseña;
    ControladorBuscaPregunta controladorbuscapregunta;
    
    
    Login modelologin;
    String usuario;

    public Controladorlogin(FRMLogin vistalogin,FrmInicio vistaMDI) {
        this.vistalogin = vistalogin;
        this.vistaMDI=vistaMDI;
        if(modelologin==null)modelologin= new Login();
        this.vistalogin.btnaceptar.addActionListener(this);
        this.vistalogin.btncancelar.addActionListener(this);
        this.vistalogin.btnrecuperapsw.addActionListener(this);
        vistarecuperarcontraseña= new FRMRecuperacontraseña();
        vistarecuperarcontraseña.btnbuscapregunta.addActionListener(this);
        
        iniciar();
    }
    private void iniciar() {
        vistalogin.setTitle("Login");
        vistalogin.setLocationRelativeTo(null);
        vistalogin.toFront();
    }
    
    private void terminator(){
        vistalogin.txtuser.setText("");
        vistalogin.txtpassword.setText("");
    }
    
    
    private void Acceder(){
        
        modelologin= new Login();
        usuario=vistalogin.txtuser.getText();
        String psw= new String(vistalogin.txtpassword.getPassword());
        modelologin.setUsuario(usuario);
        modelologin.setPsw(psw);
        ILogin proxy= new LoginProxy();
        int r=proxy.acceder(modelologin);
        int tipousuario=proxy.tipousuario();
        if(r==1){
            JOptionPane.showMessageDialog(vistalogin, "Accedio");
            vistaMDI.item.setText("Usuario: "+usuario);
            Cancelar();
            
            if(tipousuario==1){
                Accesoroot();
            }
            if(tipousuario==4){
                AccesoTutor();
            }
            if(tipousuario==6){
                AccesoAdministracion();
            }
            
               
        }else{
            JOptionPane.showMessageDialog(vistalogin, "Acceso Denegado");
            terminator();
        }
        
    }
    
    private void Cancelar(){
        vistalogin.dispose();
               
    }
    
    private void AccesoAdministracion(){
        vistaMDI.menuadministracion.setEnabled(true);
        vistaMDI.menuestadocuentas.setEnabled(true);
        
    }
    
    private void AccesoTutor(){
        vistaMDI.menudocentes.setEnabled(true);
    }
    private void Accesoroot(){
        AccesoAdministracion();
        AccesoTutor();
    }
    
    private void Iniciarrecuperarcontraseña(){
        if(vistarecuperarcontraseña==null || vistarecuperarcontraseña.isShowing()==false){
            vistarecuperarcontraseña= new FRMRecuperacontraseña();
            controladorbuscapregunta= new ControladorBuscaPregunta(vistarecuperarcontraseña);
            vistarecuperarcontraseña.toFront();
            vistarecuperarcontraseña.show();
            vistarecuperarcontraseña.setLocationRelativeTo(null);
        }
    }
    
    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistalogin.btnaceptar){
           Acceder();
        }
        if(e.getSource()==vistalogin.btncancelar){
            Cancelar();
        }
        if(e.getSource()==vistalogin.btnrecuperapsw){
            Iniciarrecuperarcontraseña();
        }
        
    }
    

    
    
}
