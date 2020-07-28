
package Controlador.FacadeImplement;

import Controlador.Facade.Abonar;
import Controlador.Facade.BuscarTitularCuenta;

public class Facade {
    
    private Abonar servicioabonar;
    private BuscarTitularCuenta serviciobuscartitular;

    public Facade() {
        servicioabonar= new Abonar();
        serviciobuscartitular= new BuscarTitularCuenta();
    }
    
    public String Titular(String matricula){
        return serviciobuscartitular.TitularCuenta(matricula);
    }
    
    public void Abonar(String cuenta, int importe){
        servicioabonar.AbonarCuenta(cuenta, importe);
    }
    
    
    
    
    
}
