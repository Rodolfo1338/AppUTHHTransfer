/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Proxy;

import Controlador.Interfaz.ILogin;
import Controlador.Interfaz.Implementacion.LoginImplementacion;
import Modelo.Login;

/**
 *
 * @author Rodol
 */
public class LoginProxy implements ILogin{

    private LoginImplementacion claseReal;
    @Override
    public int acceder(Login login) {
        if(claseReal==null){
            claseReal=new LoginImplementacion();
            return claseReal.acceder(login);
        }
        else{
            return claseReal.acceder(login);
        }
    }

    @Override
    public int tipousuario() {
         if(claseReal==null){
            claseReal=new LoginImplementacion();
            return claseReal.tipousuario();
        }
        else{
            return claseReal.tipousuario();
        }
    }

    @Override
    public String buscapreguntaseguridad(Login login) {
        if(claseReal==null){
            claseReal= new LoginImplementacion();
            return claseReal.buscapreguntaseguridad(login);
        }
        else{
             return claseReal.buscapreguntaseguridad(login);
        }
    }

    @Override
    public boolean respuestapreguntaseguridad(Login login) {
        if(claseReal==null){
            claseReal= new LoginImplementacion();
            return claseReal.respuestapreguntaseguridad(login);
        }
        else{
             return claseReal.respuestapreguntaseguridad(login);
        }
    }

    @Override
    public int nuevapsw(Login login,String user) {
        if(claseReal==null){
            claseReal= new LoginImplementacion();
            return claseReal.nuevapsw(login,user);
        }
        else{
             return claseReal.nuevapsw(login,user);
        }
    }
    
}
