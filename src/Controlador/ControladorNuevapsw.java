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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodol
 */
public class ControladorNuevapsw implements ActionListener{

    FRMNuevacontraseña vistanuevacontraseña;
    Login modelologin;
    String user;

    public ControladorNuevapsw(FRMNuevacontraseña vistanuevacontraseña,String user) {
        this.vistanuevacontraseña = vistanuevacontraseña;
        this.user=user;
        if(modelologin==null)modelologin= new  Login();
        this.vistanuevacontraseña.btnaceptar.addActionListener(this);
        iniciar();
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vistanuevacontraseña.btnaceptar){
            Nuevapsw();
        }
    }

    private void iniciar() {
        vistanuevacontraseña.setTitle("Nueva Contraseña");
        vistanuevacontraseña.setLocationRelativeTo(null);
        vistanuevacontraseña.toFront();
    }
    public void Nuevapsw(){
        String psw=new String(vistanuevacontraseña.txtpsw.getPassword());
        String confpsw=new String(vistanuevacontraseña.txtpswconfirm.getPassword());
        
        if(psw.equals(confpsw)){
            modelologin.setNuevapsw(confpsw);
            ILogin proxy = new LoginProxy();
            int r = proxy.nuevapsw(modelologin,user);
            if(r==1){
                JOptionPane.showMessageDialog(vistanuevacontraseña, "Contraseña Actualizada");
                vistanuevacontraseña.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(vistanuevacontraseña, "Las Contraseñas no Coinciden!!!");
        }
    }
}
