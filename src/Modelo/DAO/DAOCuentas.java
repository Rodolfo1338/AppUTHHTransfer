/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOCuentas;
import Modelo.clsConexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Rodol
 */
public class DAOCuentas {
  
    clsConexion cn= clsConexion.GetConnection();
    
    
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("select a.vchidcuenta,CONCAT(c.vchnombre,' ',c.vchapp,' ',c.vchapm) as nombre,a.fltsaldo from tblcuentas a left join tblalumnos b on a.idalumno=b.intidalumno left join tblpersonas c on b.intidpersona=c.intidpersona");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String cuenta = resultado.getString("vchidcuenta");
                String alumno = resultado.getString("nombre");
                String saldo = resultado.getString("fltsaldo");
                
                
                
                informacion = new Object[]{cuenta,alumno,saldo};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    public DefaultComboBoxModel comboalumnos(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccione");
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("select a.intidalumno,a.vchmatricula,CONCAT(b.vchnombre,' ',b.vchapp,' ',b.vchapm) as vchnombre from tblalumnos a left join tblpersonas b on a.intidpersona=b.intidpersona");
            resultado = Instruccion.executeQuery();
            while(resultado.next()){
                modelo.addElement(resultado.getString(3));
            }
        } catch (Exception e) {
        }
        return modelo;
    }
    public DefaultComboBoxModel combotipocuentas(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccione");
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("select * from tbltipo_cuentas");
            resultado = Instruccion.executeQuery();
            while(resultado.next()){
                modelo.addElement(resultado.getString(2));
            }
        } catch (Exception e) {
        }
        return modelo;
    }
    
    public int obteneridalumnos(String nombre){
        int id=0;
        String query="select  a.intidalumno from tblalumnos a left join tblpersonas b on a.intidpersona=b.intidpersona where (CONCAT(b.vchnombre,' ',b.vchapp,' ',b.vchapm)='"+nombre+"');";
        try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                id=Integer.parseInt(rs.getString("intidalumno"));
            }
        } catch (Exception e) {
        }
        return id;
    }
    public String obtenermatricula(String nombre){
        String id="";
        String query="select  a.vchmatricula from tblalumnos a left join tblpersonas b on a.intidpersona=b.intidpersona where (CONCAT(b.vchnombre,' ',b.vchapp,' ',b.vchapm)='"+nombre+"');";
        try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                id=rs.getString("vchmatricula");
            }
        } catch (Exception e) {
        }
        return id;
    }
    public int obteneridtipocuentas(String nombre){
        int id=0;
        String query="select intidtipo_cuenta from tbltipo_cuentas where vchtipo_cuenta='"+nombre+"';";
        try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                id=Integer.parseInt(rs.getString("intidtipo_cuenta"));
            }
        } catch (Exception e) {
        }
        return id;
    }
    
    public int NewCuenta(VOCuentas x)
    {
        int r=0;
        String Consulta="insert into tblcuentas values('"+x.getClave()+"',"+x.getAlumno()+","+x.getTipocuenta()+","+x.getSaldo()+");";
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
    public int DeleteCarrera(VOCuentas x)
    {
        int r=0;
        String Consulta="delete from tblcuentas where vchidcuenta='"+x.getClave()+"';";
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
