/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;


import Modelo.VO.VOPreguntas;
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
public class DAOPreguntas {
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblpreguntas");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidpregunta");
                String pregunta = resultado.getString("vchpregunta");
                
                
                
                informacion = new Object[]{clave,pregunta};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
      //Insertar
    
    public int NuevaPregunta(VOPreguntas x)
    {
        int r=0;
        String Consulta="insert into tblpreguntas(vchpregunta) values ('"+x.getPregunta()+"');";
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
     public int ActualizarPregunta(VOPreguntas x)
    {
        int r=0;
        String Consulta="update tblpreguntas set vchpregunta='"+x.getPregunta()+"' where intidpregunta="+x.getClave()+";";
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
    public int BorrarPregunta(VOPreguntas x)
    {
        int r=0;
        String Consulta="delete from tblpreguntas where intidpregunta="+x.getClave()+";";
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
