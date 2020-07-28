/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.VO;

/**
 *
 * @author Rodol
 */
public class VOConceptosPendientes {
    
    private int clave,concepto,idalumno,idmateria,estadoconcepto;

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public int getIdmateria() {
        return idmateria;
    }

    public void setIdmateria(int idmateria) {
        this.idmateria = idmateria;
    }

    public int getConcepto() {
        return concepto;
    }

    public void setConcepto(int concepto) {
        this.concepto = concepto;
    }

    public int getEstadoconcepto() {
        return estadoconcepto;
    }

    public void setEstadoconcepto(int estadoconcepto) {
        this.estadoconcepto = estadoconcepto;
    }
    
    
    
}
