/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOServicios;
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
public class DAOServicios {
    
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tbltipo_servicios");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidtipo_servicio");
                String servicio = resultado.getString("vchtipo_servicio");
                String comision = resultado.getString("comision");
                String identificador = resultado.getString("clvservicio");
                
                
                informacion = new Object[]{clave,servicio,comision,identificador};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    //Insertar
    
    public int NewServicio(VOServicios x)
    {
        int r=0;
        String Consulta="insert into tbltipo_servicios(vchtipo_servicio,comision,clvservicio) values ('"+x.getServicio()+"',"+x.getComision()+",'"+x.getIdentificador()+"');";
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
     public int UpdateServicio(VOServicios x)
    {
        int r=0;
        String Consulta="update tbltipo_servicios set vchtipo_servicio='"+x.getServicio()+"',comision="+x.getComision()+",clvservicio='"+x.getIdentificador()+"' where intidtipo_servicio="+x.getClave()+";";
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
     public int DeleteServicio(VOServicios x)
    {
        int r=0;
        String Consulta="delete from tbltipo_servicios where intidtipo_servicio="+x.getClave()+";";
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
