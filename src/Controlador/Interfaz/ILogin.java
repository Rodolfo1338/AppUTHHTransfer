/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz;

import Modelo.Login;

/**
 *
 * @author Rodol
 */
public interface ILogin {
    
    public int acceder(Login login);
    
    public int tipousuario();
    
    public String buscapreguntaseguridad(Login login);
    
    public boolean respuestapreguntaseguridad(Login login);
    
    public int nuevapsw(Login login,String user);
    
    

}
