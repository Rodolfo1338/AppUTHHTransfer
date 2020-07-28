package Modelo.DAO;

import Modelo.VO.VOPrivilegios;
import Modelo.clsConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DAOPrivilegios {
   
     clsConexion cn= clsConexion.GetConnection();
     
public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblprivilegios");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidprivilegio");
                String privilegio = resultado.getString("vchprivilegio");
                
                
                
                informacion = new Object[]{clave,privilegio};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }

   //Insertar
    
    public int NewPrivilegio(VOPrivilegios x)
    {
        int r=0;
        String Consulta="insert into tblprivilegios(vchprivilegio) values ('"+x.getPrivilegio()+"');";
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
     public int UpdatePrivilegio(VOPrivilegios x)
    {
        int r=0;
        String Consulta="update tblprivilegios set vchprivilegio='"+x.getPrivilegio()+"' where intidprivilegio="+x.getClave()+";";
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
     public int DeletePrivilegio(VOPrivilegios x)
    {
        int r=0;
        String Consulta="delete from tblprivilegios where intidprivilegio="+x.getClave()+";";
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
