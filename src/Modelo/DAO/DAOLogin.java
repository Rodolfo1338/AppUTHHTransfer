
package Modelo.DAO;

import Modelo.VO.VOLogin;
import Modelo.clsConexion;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Rodol
 */
public class DAOLogin {
    
    clsConexion cn= clsConexion.GetConnection();
    
    public int  Acceder(VOLogin x){
        int r=0;
        String Consulta="select from tblusuarios where vchusuario='"+x.getUsuario()+"' AND vchpass='"+x.getContrasenia()+"' ";
        try {
            Statement smt;
            smt=cn.conectar().createStatement();
            ResultSet rs=smt.executeQuery(Consulta);
            if(rs.next()){
                r=1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
    
}
