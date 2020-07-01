package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class clsConexion {
    
    private static clsConexion cn;
    
    public static final String URL = "jdbc:sqlserver://localhost:57004;databaseName=bduthhtransfer;";
    public static final String USERNAME = "user=Rodolfo;";
    public static final String PASSWORD = "password=20171338;";
    
    private static Connection conexion;
    
    private clsConexion(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                conexion = DriverManager.getConnection(URL+USERNAME+PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(clsConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {
        }
    }
    
    public static clsConexion GetConnection(){
      
        if (cn == null) cn = new clsConexion();
        return cn;
    }
    
     public Connection conectar(){      
        return conexion;
    }  
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(clsConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
            
    
    
}
