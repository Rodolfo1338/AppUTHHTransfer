
package Controlador.Facade;

import Modelo.DAO.DAOAbonarcuenta;


public class BuscarTitularCuenta {
    
    DAOAbonarcuenta modeloDAO;
    
    public String TitularCuenta(String numcuenta){
        modeloDAO= new DAOAbonarcuenta();
        
        String r=modeloDAO.buscartitularcuenta(numcuenta);
        
        return r;
        
    }
}
