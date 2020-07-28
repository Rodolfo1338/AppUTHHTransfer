/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.FacadeImplement.Facade;
import Modelo.DAO.DAOAbonarcuenta;
import Modelo.DAO.DAOCuentas;
import Modelo.VO.VOAbonarcuenta;
import Modelo.VO.VOCuentas;
import Vista.FRMAbonarcuenta;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorAbonacuenta implements ActionListener{

    FRMAbonarcuenta vistaabonacuenta;
    DAOAbonarcuenta modeloDAO;
    VOAbonarcuenta modeloVO;
    Facade cliente;

    public ControladorAbonacuenta(FRMAbonarcuenta vistaabonacuenta) {
        this.vistaabonacuenta = vistaabonacuenta;
        if(modeloDAO==null)modeloDAO= new DAOAbonarcuenta();
        if(cliente==null)cliente= new Facade();
        llenagrid();
        iniciar();
        this.vistaabonacuenta.btnbuscar.addActionListener(this);
        this.vistaabonacuenta.btnabonar.addActionListener(this);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==vistaabonacuenta.btnbuscar){
             buscartitularFacade();
         }
         if(e.getSource()==vistaabonacuenta.btnabonar){
             AbonarFacade();
             terminator();
             llenagrid();
         }
    }

    private void iniciar() {
       vistaabonacuenta.setTitle("Abonar Cuentas");
       vistaabonacuenta.setLocationRelativeTo(null);
    }

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaabonacuenta.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String cuenta = contenedorDeRegistro[0].toString();
            String alumno = contenedorDeRegistro[1].toString();
            String saldo = contenedorDeRegistro[2].toString();

            modeloDeTabla.addRow(new Object[]{cuenta, alumno,saldo});
        }
        vistaabonacuenta.datos.setModel(modeloDeTabla);
    }
    private void terminator(){
        vistaabonacuenta.txtnumcuenta.setText("");
        vistaabonacuenta.txtimporte.setText("");
        vistaabonacuenta.lbltitular.setText("");
    }
    private void buscartitular(){
        String matricula=vistaabonacuenta.txtnumcuenta.getText();
        vistaabonacuenta.lbltitular.setText(modeloDAO.buscartitularcuenta(matricula));
       
    }
    
    private void buscartitularFacade(){
        String matricula=vistaabonacuenta.txtnumcuenta.getText();
        vistaabonacuenta.lbltitular.setText(cliente.Titular(matricula));
    }
    
    private void AbonarFacade(){
        String cuenta=vistaabonacuenta.txtnumcuenta.getText();
        int importe=Integer.parseInt(vistaabonacuenta.txtimporte.getText());
        cliente.Abonar(cuenta, importe);
    }
    
    private void Insertar(){
        modeloVO = new VOAbonarcuenta();
        modeloVO.setNumcuenta(vistaabonacuenta.txtnumcuenta.getText());
        modeloVO.setImporte(Integer.parseInt(vistaabonacuenta.txtimporte.getText()));
        
        if(modeloDAO.NewAbono(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Abono Realizado", "Atención", JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Abono Fallido", "Atención", JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }
        
        
                
                
    }
    
}
