/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.Interfaz.ILogin;
import Controlador.Proxy.LoginProxy;
import Modelo.Login;
import Vista.FRMNuevacontraseña;
import Vista.FRMRecuperacontraseña;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodol
 */
public class ControladorBuscaPregunta  implements ActionListener{
    
    FRMRecuperacontraseña vistarecuperacontrasena;
    Login modelologin;
    
    FRMNuevacontraseña vistanuevapsw;
    ControladorNuevapsw controladornuevapsw;
    
    String user;

    public ControladorBuscaPregunta(FRMRecuperacontraseña vistarecuperacontrasena) {
        this.vistarecuperacontrasena = vistarecuperacontrasena;
        if(modelologin==null)modelologin= new  Login();
        this.vistarecuperacontrasena.btnbuscapregunta.addActionListener(this);
        this.vistarecuperacontrasena.btnaceptar.addActionListener(this);
        
        iniciar();
    }
    
    private void iniciar(){
        vistarecuperacontrasena.setTitle("Recuperar Contraseña");
        vistarecuperacontrasena.setLocationRelativeTo(null);
        vistarecuperacontrasena.toFront();
    }
    public void BuscarPregunta(){
        user=vistarecuperacontrasena.txtuser.getText();
        modelologin.setUsuario(user);
        ILogin proxy= new LoginProxy();
        String pregunta=proxy.buscapreguntaseguridad(modelologin);
        if(pregunta!="no")
            vistarecuperacontrasena.lblpregunta.setText(pregunta);
        
         
        
    }
    public void Nuevapsw(){
        
        String respuesta= vistarecuperacontrasena.txtrespuesta.getText();
        modelologin.setRespuesta(respuesta);
        
        ILogin proxy = new LoginProxy();
        boolean r=proxy.respuestapreguntaseguridad(modelologin);
        if(r){
            JOptionPane.showMessageDialog(vistanuevapsw, "Respuesta Correcta");
            IniciarNuevapsw(user);
            vistarecuperacontrasena.dispose();
            
        }else{
            JOptionPane.showMessageDialog(vistanuevapsw, "Respuesta Incorrecta");
        }
        
    }
    
    private void IniciarNuevapsw(String user){
        if(vistanuevapsw==null || vistanuevapsw.isShowing()==false){
            vistanuevapsw= new FRMNuevacontraseña();
            controladornuevapsw = new ControladorNuevapsw(vistanuevapsw,user);
            vistanuevapsw.toFront();
            vistanuevapsw.show();
            vistanuevapsw.setLocationRelativeTo(null);
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==vistarecuperacontrasena.btnbuscapregunta){
            BuscarPregunta();
        }
       if(e.getSource()==vistarecuperacontrasena.btnaceptar){
           Nuevapsw();
       }
    }

    
    
}
