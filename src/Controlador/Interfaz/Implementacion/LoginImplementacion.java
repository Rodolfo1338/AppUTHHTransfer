/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Interfaz.Implementacion;

import Controlador.Interfaz.ILogin;
import Modelo.Login;
import Modelo.clsConexion;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Rodol
 */
public class LoginImplementacion implements ILogin{
    
    int id;
    
    

    clsConexion cn= clsConexion.GetConnection();
    @Override
    public int acceder(Login login) {
        int r=0;
        String Consulta="select intidrol from tblusuarios where vchusuario='"+login.getUsuario()+"' AND vchpass='"+login.getPsw()+"' ";
        try {
            Statement smt;
            smt=cn.conectar().createStatement();
            ResultSet rs=smt.executeQuery(Consulta);
            
            if(rs.next()){
                id = Integer.parseInt(rs.getString("intidrol"));
                r = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public int tipousuario() {
        return id;
    }

    @Override
    public String buscapreguntaseguridad(Login login) {
        
        String consulta = "select a.vchpregunta from tblpreguntas a left join tblrespuestas b on a.intidpregunta = b.intidpregunta left join tblusuarios c on b.intidusuario=c.intidusuario where c.vchusuario='" + login.getUsuario() + "'";
        try {
            Statement smt;
            smt = cn.conectar().createStatement();
            ResultSet rs = smt.executeQuery(consulta);

            if (rs.next()) {
                return rs.getString("vchpregunta");
            }else{
                return"no";
            }
        } catch (Exception e) {e.printStackTrace();}
        
        return "no";
    }

    @Override
    public boolean respuestapreguntaseguridad(Login login) {
        String consulta="select * from tblpreguntas a left join tblrespuestas b on a.intidpregunta = b.intidpregunta left join tblusuarios c on b.intidusuario=c.intidusuario where b.vchrespuesta='"+login.getRespuesta()+"' and c.vchusuario='"+login.getUsuario()+"'";
        try {
            Statement smt;
            smt=cn.conectar().createStatement();
            ResultSet rs=smt.executeQuery(consulta);
            
            if(rs.next()){
                
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int nuevapsw(Login login,String user) {
        String consulta="update tblusuarios set vchpass='"+login.getNuevapsw()+"' where vchusuario='"+user+"'";
        int r=0;
        try {
             Statement stm=(Statement)cn.conectar().createStatement();
             r=stm.executeUpdate(consulta);
             
         }catch (Exception e){
             System.out.println(e);
         }finally{
            return r;
        }
        
    }

    
    
    
}
