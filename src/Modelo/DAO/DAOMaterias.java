/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOMaterias;
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
public class DAOMaterias {
    
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblmaterias");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidmateria");
                String materia = resultado.getString("vchmateria");
                
                
                
                informacion = new Object[]{clave,materia};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
     //Insertar
    
    public int NewMateria(VOMaterias x)
    {
        int r=0;
        String Consulta="insert into tblmaterias(vchmateria) values ('"+x.getMateria()+"');";
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
     public int UpdateMaterias(VOMaterias x)
    {
        int r=0;
        String Consulta="update tblmaterias set vchmateria='"+x.getMateria()+"' where intidmateria="+x.getClave()+";";
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
    public int DeleteMaterias(VOMaterias x)
    {
        int r=0;
        String Consulta="delete from tblmaterias where intidmateria="+x.getClave()+";";
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
