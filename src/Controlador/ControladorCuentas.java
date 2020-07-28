/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOCuentas;
import Modelo.VO.VOCuentas;
import Vista.FRMCrearcuentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorCuentas implements ActionListener{
    
    FRMCrearcuentas vistacuentas;
    DAOCuentas modeloDAO;
    VOCuentas modeloVO;

    public ControladorCuentas(FRMCrearcuentas vistacuentas) {
        this.vistacuentas = vistacuentas;
        if(modeloDAO==null)modeloDAO= new DAOCuentas();
        llenagrid();
        iniciar();
        llenacomboalumnos();
        llenacombotipocuentas();
        
        this.vistacuentas.btnaceptar.addActionListener(this);
        this.vistacuentas.btneliminar.addActionListener(this);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==vistacuentas.btnaceptar){
             Insertar();
             llenagrid();
             terminator();
         }
         if(e.getSource()==vistacuentas.btneliminar){
             Eliminar();
             llenagrid();
         }
    }

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistacuentas.datos.getModel();
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
        vistacuentas.datos.setModel(modeloDeTabla);
    }
    private void llenacomboalumnos(){
        vistacuentas.cmbAlumnos.setModel(modeloDAO.comboalumnos());
    }
    private void llenacombotipocuentas(){
        vistacuentas.cmbTipocuenta.setModel(modeloDAO.combotipocuentas());
    }

    private void iniciar() {
        vistacuentas.setTitle("Asignacion de Cuentas");
        vistacuentas.setLocationRelativeTo(null);
        
    }
    
    private void Insertar(){
        modeloVO = new VOCuentas();
        String alumno=String.valueOf(vistacuentas.cmbAlumnos.getSelectedItem());
        String tipocuenta=String.valueOf(vistacuentas.cmbTipocuenta.getSelectedItem());
        String matricula=modeloDAO.obtenermatricula(alumno);
        int idalumno=modeloDAO.obteneridalumnos(alumno);
        int idtipocuentas=modeloDAO.obteneridtipocuentas(tipocuenta);
        float saldo=Float.parseFloat(vistacuentas.txtsaldoinicial.getText());
        
        modeloVO.setClave(matricula);
        modeloVO.setAlumno(idalumno);
        modeloVO.setTipocuenta(idtipocuentas);
        modeloVO.setSaldo(saldo);
        
        if(modeloDAO.NewCuenta(modeloVO)!=0){
             JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
        }else{
             JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.INFORMATION_MESSAGE);
        }
        
                
        
    }
    
    private void Eliminar(){
        try {
            modeloVO= new VOCuentas();
            int row=vistacuentas.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistacuentas.datos.getModel();
            String nombre = model.getValueAt(row, 0).toString();
             int n = JOptionPane.showConfirmDialog(vistacuentas, "Seguro que deseas eliminar la cuenta " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(nombre);
                if (modeloDAO.DeleteCarrera(modeloVO) != 0) {
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente", "Atención", JOptionPane.INFORMATION_MESSAGE);
                    terminator();

                } else {
                    JOptionPane.showMessageDialog(null, "Error", "Atención", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } catch (Exception e) {
        }
    }

    private void terminator() {
        vistacuentas.cmbAlumnos.setSelectedIndex(0);
        vistacuentas.cmbTipocuenta.setSelectedIndex(0);
        vistacuentas.txtsaldoinicial.setText("");
    }
         
    
}
