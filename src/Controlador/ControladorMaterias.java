/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOMaterias;
import Modelo.VO.VOMaterias;
import Vista.FrmMaterias;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorMaterias implements ActionListener{
    
    FrmMaterias vistamaterias;
    DAOMaterias modeloDAO;
    VOMaterias modeloVO;

    public ControladorMaterias(FrmMaterias vistamaterias) {
        this.vistamaterias = vistamaterias;
        if(modeloDAO==null) modeloDAO= new DAOMaterias();
        llenagrid();
        iniciar();
        
        this.vistamaterias.btnnuevo.addActionListener(this);
        this.vistamaterias.btnactualizar.addActionListener(this);
        this.vistamaterias.btneliminar.addActionListener(this);
        this.vistamaterias.btnlimpiar.addActionListener(this);
        this.vistamaterias.btnseleccionar.addActionListener(this);
    
        
    }

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistamaterias.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String materia = contenedorDeRegistro[1].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,materia});
        }
        vistamaterias.datos.setModel(modeloDeTabla);
    }

    private void iniciar() {
        vistamaterias.setTitle("Materias");
        vistamaterias.setLocationRelativeTo(null);
        vistamaterias.txtclave.enable(false);
    }
    
     private void terminator(){
        vistamaterias.txtclave.setText("");
        vistamaterias.txtmateria.setText("");
              
    }

    private void Seleccionar(){
        int row= vistamaterias.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistamaterias.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String materia=model.getValueAt(row, 1).toString();
        
        vistamaterias.txtclave.setText(String.valueOf(clave));
        vistamaterias.txtmateria.setText(materia);
        
    }
    
    private void Insertar(){
        modeloVO= new VOMaterias();
        modeloVO.setMateria(vistamaterias.txtmateria.getText());
        
        
        if(modeloDAO.NewMateria(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    
    private void Eliminar(){
        try {
            modeloVO = new VOMaterias();
            int row = vistamaterias.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistamaterias.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistamaterias, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteMaterias(modeloVO) != 0) {
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
            modeloVO = new VOMaterias();
            modeloVO.setClave(Integer.parseInt(vistamaterias.txtclave.getText()));
            modeloVO.setMateria(vistamaterias.txtmateria.getText());
            
            if (modeloDAO.UpdateMaterias(modeloVO) != 0) {
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
        if(e.getSource()==vistamaterias.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistamaterias.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistamaterias.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistamaterias.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistamaterias.btnlimpiar){
            terminator();
        }
    }
    
    
    
}
