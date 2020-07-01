/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOServicios;
import Modelo.VO.VOServicios;
import Vista.FrmServicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorServicios implements ActionListener{
    
    FrmServicios vistaservicios;
    DAOServicios modeloDAO;
    VOServicios modeloVO;

    public ControladorServicios(FrmServicios vistaservicios) {
        this.vistaservicios = vistaservicios;
        if(modeloDAO==null)modeloDAO= new DAOServicios();
        llenagrid();
        iniciar();
        
        this.vistaservicios.btnnuevo.addActionListener(this);
        this.vistaservicios.btnactualizar.addActionListener(this);
        this.vistaservicios.btneliminar.addActionListener(this);
        this.vistaservicios.btnlimpiar.addActionListener(this);
        this.vistaservicios.btnseleccionar.addActionListener(this);
                
    }
    
    public void iniciar(){
        vistaservicios.setTitle("Servicios");
        vistaservicios.setLocationRelativeTo(null);
        vistaservicios.txtclave.enable(false);
        
    }
    
     private void llenagrid(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaservicios.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String servicio = contenedorDeRegistro[1].toString();
            String comision = contenedorDeRegistro[2].toString();
            String identificador = contenedorDeRegistro[3].toString();
            
            
            modeloDeTabla.addRow(new Object[]{id,servicio,comision,identificador});
        }
        vistaservicios.datos.setModel(modeloDeTabla);
    }
    
     private void terminator(){
        vistaservicios.txtclave.setText("");
        vistaservicios.txtServicio.setText("");
        vistaservicios.txtComision.setText("");
        vistaservicios.txtidentificador.setText("");
               
    }
     
    private void Seleccionar(){
        int row= vistaservicios.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistaservicios.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String servicio=model.getValueAt(row, 1).toString();
        String identificador=model.getValueAt(row, 3).toString();
        String comision=model.getValueAt(row, 2).toString();
       
        vistaservicios.txtclave.setText(String.valueOf(clave));
        vistaservicios.txtServicio.setText(servicio);
        vistaservicios.txtidentificador.setText(identificador);
        vistaservicios.txtComision.setText(comision);
        
        
    }
     
    private void Insertar(){
        modeloVO= new VOServicios();
        modeloVO.setServicio(vistaservicios.txtServicio.getText());
        modeloVO.setIdentificador(vistaservicios.txtidentificador.getText());
        modeloVO.setComision(Float.parseFloat(vistaservicios.txtComision.getText()));
        
        if(modeloDAO.NewServicio(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    
    private void Eliminar(){
        try {
            modeloVO = new VOServicios();
            int row = vistaservicios.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistaservicios.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistaservicios, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteServicio(modeloVO) != 0) {
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
            modeloVO = new VOServicios();
            modeloVO.setClave(Integer.parseInt(vistaservicios.txtclave.getText()));
            modeloVO.setServicio(vistaservicios.txtServicio.getText());
            modeloVO.setIdentificador(vistaservicios.txtidentificador.getText());
            modeloVO.setComision(Float.parseFloat(vistaservicios.txtComision.getText()));
            if (modeloDAO.UpdateServicio(modeloVO) != 0) {
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
        if(e.getSource()==vistaservicios.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistaservicios.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistaservicios.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistaservicios.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistaservicios.btnlimpiar){
            terminator();
        }
    }
    
    
    
}
