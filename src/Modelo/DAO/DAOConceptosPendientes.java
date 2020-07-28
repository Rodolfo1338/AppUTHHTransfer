/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOConceptosPendientes;
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
public class DAOConceptosPendientes {
    
    clsConexion cn= clsConexion.GetConnection();
    
    public ArrayList<Object[]> consultarconceptospendientes(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("select a.intidconceptopendiente,b.vchconcepto,c.vchmatricula,CONCAT(e.vchnombre,' ',e.vchapp,' ',e.vchapm) as vchnombre,d.vchmateria,b.intcosto from tblconceptospendientes a left join tblconceptos b on a.intidconcepto=b.intidconcepto left join tblalumnos c on a.intidalumno=c.intidalumno left join tblmaterias d on a.intidmateria=d.intidmateria left join tblpersonas e on c.intidpersona=e.intidpersona");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidconceptopendiente");
                String concepto = resultado.getString("vchconcepto");
                String matricula = resultado.getString("vchmatricula");
                String nombre = resultado.getString("vchnombre");
                String materia = resultado.getString("vchmateria");
                String costo = resultado.getString("intcosto");
                
                
                
                informacion = new Object[]{clave,concepto,matricula,nombre,materia,costo};
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
    public DefaultComboBoxModel comboconceptos(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccione");
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblconceptos");
            resultado = Instruccion.executeQuery();
            while(resultado.next()){
                modelo.addElement(resultado.getString(2));
            }
        } catch (Exception e) {
        }
        return modelo;
    }
    public DefaultComboBoxModel combomaterias(){
        DefaultComboBoxModel modelo = new DefaultComboBoxModel();
        modelo.addElement("Seleccione");
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblmaterias");
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
    public int obteneridconceptos(String nombre){
        int id=0;
        String query="select intidconcepto from tblconceptos where vchconcepto='"+nombre+"'";
        try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                id=Integer.parseInt(rs.getString("intidconcepto"));
            }
        } catch (Exception e) {
        }
        return id;
    }
    public int obteneridmaterias(String nombre){
        int id=0;
        String query="select intidmateria from tblmaterias where vchmateria='"+nombre+"'";
        try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                id=Integer.parseInt(rs.getString("intidmateria"));
            }
        } catch (Exception e) {
        }
        return id;
    }
            
    
    public ArrayList<Object[]> consultarconceptos(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblconceptos");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidconcepto");
                String concepto = resultado.getString("vchconcepto");
                
                
                
                informacion = new Object[]{clave,concepto};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    public ArrayList<Object[]> consultarmaterias(){
        
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
    
    public ArrayList<Object[]> consultaralumnos(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("select a.intidalumno,a.vchmatricula,CONCAT(b.vchnombre,' ',b.vchapp,' ',b.vchapm) as vchnombre from tblalumnos a left join tblpersonas b on a.intidpersona=b.intidpersona");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidalumno");
                String matricula = resultado.getString("vchmatricula");
                String nombre = resultado.getString("vchnombre");
                
                
                
                informacion = new Object[]{clave,matricula,nombre};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    //Insertar
    
    public int NuevoConcepto(VOConceptosPendientes x)
    {
        int r=0;
        String Consulta="insert into tblconceptospendientes(intidconcepto,intidalumno,boolestadoconcepto,intidmateria) values("+x.getConcepto()+","+x.getIdalumno()+",1,"+x.getIdmateria()+")";
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
     public int UpdateMaterias(VOConceptosPendientes x)
    {
        int r=0;
        String Consulta="update tblconceptospendientes set intidconcepto="+x.getConcepto()+",intidalumno="+x.getIdalumno()+",boolestadoconcepto="+x.getEstadoconcepto()+",intidmateria="+x.getIdmateria()+" where intidconceptopendiente="+x.getClave()+";";
        try {
             Statement stm=(Statement)cn.conectar().createStatement();
             r=stm.executeUpdate(Consulta);
         }catch (Exception e){
             System.out.println(e);
         }finally{
             
            return r; 
         }
        
    }
     
     public int DeleteConceptoPendiente(VOConceptosPendientes x)
    {
        int r=0;
        String Consulta="delete from tblconceptospendientes where intidconceptopendiente="+x.getClave()+";";
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

