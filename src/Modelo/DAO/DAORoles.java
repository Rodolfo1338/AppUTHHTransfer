
package Modelo.DAO;

import Modelo.VO.VORoles;
import Modelo.clsConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Rodol
 */
public class DAORoles {
    
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblroles");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidrol");
                String Rol = resultado.getString("vchrol");
                
                
                
                informacion = new Object[]{clave,Rol};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    //Insertar
    
    public int NewRol(VORoles x)
    {
        int r=0;
        String Consulta="insert into tblroles(vchrol) values ('"+x.getRol()+"');";
        try {
             Statement stm=(Statement)cn.conectar().createStatement();
             r=stm.executeUpdate(Consulta);
         }catch (Exception e){
             System.out.println(e);
         }finally{
             
            return r; 
         }
        
    }
    
    //Modificar
     public int UpdateRol(VORoles x)
    {
        int r=0;
        String Consulta="update tblroles set vchrol='"+x.getRol()+"' where intidrol="+x.getClave()+";";
        try {
             Statement stm=(Statement)cn.conectar().createStatement();
             r=stm.executeUpdate(Consulta);
         }catch (Exception e){
             System.out.println(e);
         }finally{
             
            return r; 
         }
        
    }
      //Eliminar
    public int DeleteRol(VORoles x)
    {
        int r=0;
        String Consulta="delete from tblroles where intidrol="+x.getClave()+";";
        try {
             Statement stm=(Statement)cn.conectar().createStatement();
             r=stm.executeUpdate(Consulta);
         }catch (Exception e){
             System.out.println(e);
         }finally{
             
            return r; 
         }
        
    }
     
    
    
}
