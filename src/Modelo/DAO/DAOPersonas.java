package Modelo.DAO;

import Modelo.VO.VOPersonas;
import Modelo.clsConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;


public class DAOPersonas{
    
//    FrmPersonas vista= new FrmPersonas();
//        ControladorPersonas controlador= new ControladorPersonas(vista);
    
    clsConexion cn = clsConexion.GetConnection();

  
    //traer datos 2
    public ArrayList<Object[]> consultar(){
        
        ArrayList<Object[]> datos = new ArrayList<Object[]>();
        Object[] informacion;
        try {
            PreparedStatement Instruccion;
            ResultSet resultado = null;
            Instruccion = cn.conectar().prepareStatement("SELECT * FROM tblpersonas");
            resultado = Instruccion.executeQuery();
            
            while (resultado.next()) {
                String clave = resultado.getString("intidpersona");
                String nombre = resultado.getString("vchnombre");
                String apellidop = resultado.getString("vchapp");
                String apellidom = resultado.getString("vchapm");
                String RFC = resultado.getString("vchrfc");
                String direccion = resultado.getString("vchdireccion");
                
                informacion = new Object[]{clave,nombre,apellidop,apellidom,RFC,direccion};
                datos.add(informacion);                
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return datos;
    }
    
    //Insertar
    
    public int NewPersona(VOPersonas x)
    {
        int r=0;
        String Consulta="insert into tblpersonas(vchnombre,vchrfc,vchapp,vchapm,vchdireccion) values ('"+x.getNombre()+"','"+x.getRfc()+"','"+x.getApat()+"','"+x.getAmat()+"','"+x.getDireccion()+"');";
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
     public int UpdatePersona(VOPersonas x)
    {
        int r=0;
        String Consulta="UPDATE tblpersonas SET vchnombre='"+x.getNombre()+"', vchapp='"+x.getApat()+"',vchapm='"+x.getAmat()+"',vchrfc='"+x.getRfc()+"',vchdireccion='"+x.getDireccion()+"' WHERE intidpersona='"+x.getClave()+"'";
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
     public int DeletePersona(VOPersonas x)
    {
        int r=0;
        String Consulta="DELETE FROM tblpersonas WHERE intidpersona ='"+x.getClave()+"'";
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
