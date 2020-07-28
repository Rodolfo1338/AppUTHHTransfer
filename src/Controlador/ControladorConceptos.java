/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOConceptos;
import Modelo.VO.VOConceptos;
import Vista.FRMConceptos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorConceptos implements ActionListener{
    
    FRMConceptos vistaconceptos;
    DAOConceptos modeloDAO;
    VOConceptos modeloVO;

    public ControladorConceptos(FRMConceptos vistaconceptos) {
        this.vistaconceptos = vistaconceptos;
        if(modeloDAO==null)modeloDAO = new DAOConceptos();
        llenagrid();
        iniciar();
        
        this.vistaconceptos.btnnuevo.addActionListener(this);
        this.vistaconceptos.btnactualizar.addActionListener(this);
        this.vistaconceptos.btneliminar.addActionListener(this);
        this.vistaconceptos.btnlimpiar.addActionListener(this);
        this.vistaconceptos.btnseleccionar.addActionListener(this);
    }

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaconceptos.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String concepto = contenedorDeRegistro[1].toString();
            String costo= contenedorDeRegistro[2].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,concepto,costo});
        }
        vistaconceptos.datos.setModel(modeloDeTabla);
    }

    private void iniciar() {
        vistaconceptos.setTitle("Conceptos");
        vistaconceptos.setLocationRelativeTo(null);
        vistaconceptos.txtclave.enable(false);
    }

    private void terminator(){
        vistaconceptos.txtclave.setText("");
        vistaconceptos.txtconcepto.setText("");
        vistaconceptos.txtcosto.setText("");
              
    }
    
    private void Seleccionar(){
        int row= vistaconceptos.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistaconceptos.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String concepto=model.getValueAt(row, 1).toString();
        int costo=Integer.parseInt(model.getValueAt(row, 2).toString());
        
        vistaconceptos.txtclave.setText(String.valueOf(clave));
        vistaconceptos.txtconcepto.setText(concepto);
        vistaconceptos.txtcosto.setText(String.valueOf(costo));
        
    }
    
    private void Insertar(){
        modeloVO= new VOConceptos();
        modeloVO.setConcepto(vistaconceptos.txtconcepto.getText());
        modeloVO.setCosto(Integer.parseInt(vistaconceptos.txtcosto.getText()));
        
        
        if(modeloDAO.NewConcepto(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    
    private void Eliminar(){
        try {
            modeloVO = new VOConceptos();
            int row = vistaconceptos.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistaconceptos.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistaconceptos, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteConcepto(modeloVO) != 0) {
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
            modeloVO = new VOConceptos();
            modeloVO.setClave(Integer.parseInt(vistaconceptos.txtclave.getText()));
            modeloVO.setConcepto(vistaconceptos.txtconcepto.getText());
            modeloVO.setCosto(Integer.parseInt(vistaconceptos.txtcosto.getText()));
            
            if (modeloDAO.UpdateConcepto(modeloVO) != 0) {
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
        
        if(e.getSource()==vistaconceptos.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistaconceptos.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistaconceptos.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistaconceptos.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistaconceptos.btnlimpiar){
            terminator();
        }
    }
    
    
    
}
