/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Modelo.VO.VOAbonarcuenta;
import Modelo.VO.VOCuentas;
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
public class DAOAbonarcuenta {
    
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
    
    public String buscartitularcuenta(String matricula){
         String titular="";
         String query="select CONCAT(c.vchnombre,' ',c.vchapp,' ',c.vchapm) as nombre from tblcuentas a left join tblalumnos b on a.idalumno=b.intidalumno left join tblpersonas c on b.intidpersona=c.intidpersona where a.vchidcuenta='"+matricula+"'";
         try {
            Statement smt;
            smt= cn.conectar().createStatement();
            ResultSet rs= smt.executeQuery(query);
            if(rs.next()){
                titular=rs.getString("nombre");
            }
        } catch (Exception e) {
        }
        return titular;
    }
    
    public int NewAbono(VOAbonarcuenta x)
    {
        int r=0;
        String Consulta="update tblcuentas set fltsaldo=((select fltsaldo from tblcuentas where vchidcuenta='"+x.getNumcuenta()+"')+"+x.getImporte()+") where vchidcuenta='"+x.getNumcuenta()+"'";
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
