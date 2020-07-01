/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOTipocuentas;
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
public class DAOTipocuentas {
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tbltipo_cuentas");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidtipo_cuenta");
                String tipocuenta = resultado.getString("vchtipo_cuenta");
                
                
                
                informacion = new Object[]{clave,tipocuenta};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    public int NewTipocuenta(VOTipocuentas x)
    {
        int r=0;
        String Consulta="insert into tbltipo_cuentas(vchtipo_cuenta) values ('"+x.getTipocuenta()+"');";
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
     public int UpdateTipoCuentas(VOTipocuentas x)
    {
        int r=0;
        String Consulta="update tbltipo_cuentas set vchtipo_cuenta='"+x.getTipocuenta()+"' where intidtipo_cuenta="+x.getClave()+";";
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
     public int DeleteTipoCuentas(VOTipocuentas x)
    {
        int r=0;
        String Consulta="delete from tbltipo_cuentas where intidtipo_cuenta="+x.getClave()+";";
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
