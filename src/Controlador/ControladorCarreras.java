/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOCarreras;
import Modelo.VO.VOCarreras;
import Vista.FRMCarreras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorCarreras implements ActionListener{
    
    FRMCarreras vistacarreras;
    DAOCarreras modeloDAO;
    VOCarreras modeloVO;

    public ControladorCarreras(FRMCarreras vistacarreras) {
        this.vistacarreras = vistacarreras;
        if(modeloDAO==null)modeloDAO= new DAOCarreras();
        llenagrid();
        iniciar();
        
        this.vistacarreras.btnnuevo.addActionListener(this);
        this.vistacarreras.btnactualizar.addActionListener(this);
        this.vistacarreras.btneliminar.addActionListener(this);
        this.vistacarreras.btnlimpiar.addActionListener(this);
        this.vistacarreras.btnseleccionar.addActionListener(this);
    }
    
    

    

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistacarreras.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String carrera = contenedorDeRegistro[1].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,carrera});
        }
        vistacarreras.datos.setModel(modeloDeTabla);
    }

    private void iniciar() {
        vistacarreras.setTitle("Carreras");
        vistacarreras.setLocationRelativeTo(null);
        vistacarreras.txtclave.enable(false);
    }
    private void terminator(){
        vistacarreras.txtclave.setText("");
        vistacarreras.txtcarerra.setText("");
              
    }
    private void Seleccionar(){
        int row= vistacarreras.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistacarreras.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String carrera=model.getValueAt(row, 1).toString();
        
        vistacarreras.txtclave.setText(String.valueOf(clave));
        vistacarreras.txtcarerra.setText(carrera);
        
    }
    private void Insertar(){
        modeloVO= new VOCarreras();
        modeloVO.setCarrera(vistacarreras.txtcarerra.getText());
        
        
        if(modeloDAO.NewCarrera(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    private void Eliminar(){
        try {
            modeloVO = new VOCarreras();
            int row = vistacarreras.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistacarreras.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistacarreras, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteCarrera(modeloVO) != 0) {
                    JOptionPane.showMessageDialog(null, "Eliminado Correctamente", "Atención", JOptionPane.INFORMATION_MESSAGE);
                    terminator();

                } else {
                    JOptionPane.showMessageDialog(null, "Error", "Atención", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro!!", "Atención", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    private void Modificar(){
        try {
            modeloVO = new VOCarreras();
            modeloVO.setClave(Integer.parseInt(vistacarreras.txtclave.getText()));
            modeloVO.setCarrera(vistacarreras.txtcarerra.getText());
            
            if (modeloDAO.UpdateCarrera(modeloVO) != 0) {
                JOptionPane.showMessageDialog(null, "Modificación Exitosa", "Atención", JOptionPane.INFORMATION_MESSAGE);
                terminator();
            } else {
                JOptionPane.showMessageDialog(null, "Modificación Fallida", "Atención", JOptionPane.ERROR_MESSAGE);
                terminator();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleccione un registro!!", "Atención", JOptionPane.ERROR_MESSAGE);
        }

        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
          if(e.getSource()==vistacarreras.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistacarreras.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistacarreras.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistacarreras.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistacarreras.btnlimpiar){
            terminator();
        }
    }
    
}
