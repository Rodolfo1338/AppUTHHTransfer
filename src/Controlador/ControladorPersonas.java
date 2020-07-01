/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOPersonas;
import Modelo.VO.VOPersonas;
import Vista.FrmPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorPersonas implements ActionListener{
    
    FrmPersonas vistapersona;
    DAOPersonas modeloDAO;
    VOPersonas modeloVO;

    public ControladorPersonas(FrmPersonas vistapersona) {
        this.vistapersona = vistapersona;
        
        
        if(modeloDAO==null) modeloDAO= new DAOPersonas();
        llenagrid();
        iniciar();
                   
        
        this.vistapersona.btnnuevo.addActionListener(this);
        this.vistapersona.btnactualizar.addActionListener(this);
        this.vistapersona.btneliminar.addActionListener(this);
        this.vistapersona.btnseleccionar.addActionListener(this);
        this.vistapersona.btnlimpiar.addActionListener(this);
                
        
        
       
        iniciar();
        
    }
    
    public void iniciar(){
        vistapersona.setTitle("Personas");
        vistapersona.setLocationRelativeTo(null);
        vistapersona.txtclave.enable(false);
        
    }
    
    private void llenagrid(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistapersona.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String nombre = contenedorDeRegistro[1].toString();
            String apellidop = contenedorDeRegistro[2].toString();
            String apellidom = contenedorDeRegistro[3].toString();
            String RFC = contenedorDeRegistro[4].toString();
            String direccion = contenedorDeRegistro[5].toString();
            
            modeloDeTabla.addRow(new Object[]{id, nombre,apellidop,apellidom,RFC,direccion});
        }
        vistapersona.datos.setModel(modeloDeTabla);
    }
    
   
    
    private void terminator(){
        vistapersona.txtclave.setText("");
        vistapersona.txtNombre.setText("");
        vistapersona.txtApaterno.setText("");
        vistapersona.txtamaterno.setText("");
        vistapersona.txtRFC.setText("");
        vistapersona.txtdireccion.setText("");
               
    }
    
    private void Insertar(){
        modeloVO= new VOPersonas();
        modeloVO.setNombre(vistapersona.txtNombre.getText());
        modeloVO.setApat(vistapersona.txtApaterno.getText());
        modeloVO.setAmat(vistapersona.txtamaterno.getText());
        modeloVO.setRfc(vistapersona.txtRFC.getText());
        modeloVO.setDireccion(vistapersona.txtdireccion.getText());
        
        if(modeloDAO.NewPersona(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    
    private void Eliminar(){
        try {
            modeloVO = new VOPersonas();
            int row = vistapersona.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistapersona.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistapersona, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeletePersona(modeloVO) != 0) {
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
    private void Seleccionar(){
        int row= vistapersona.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistapersona.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String nombre=model.getValueAt(row, 1).toString();
        String apat=model.getValueAt(row, 2).toString();
        String amat=model.getValueAt(row, 3).toString();
        String rfc=model.getValueAt(row, 4).toString();
        String direccion=model.getValueAt(row, 5).toString();
        
        vistapersona.txtclave.setText(String.valueOf(clave));
        vistapersona.txtNombre.setText(nombre);
        vistapersona.txtApaterno.setText(apat);
        vistapersona.txtamaterno.setText(amat);
        vistapersona.txtRFC.setText(rfc);
        vistapersona.txtdireccion.setText(direccion);
        
        
    }
    private void Modificar(){
        try {
            modeloVO = new VOPersonas();
            modeloVO.setClave(Integer.parseInt(vistapersona.txtclave.getText()));
            modeloVO.setNombre(vistapersona.txtNombre.getText());
            modeloVO.setApat(vistapersona.txtApaterno.getText());
            modeloVO.setAmat(vistapersona.txtamaterno.getText());
            modeloVO.setRfc(vistapersona.txtRFC.getText());
            modeloVO.setDireccion(vistapersona.txtdireccion.getText());
            if (modeloDAO.UpdatePersona(modeloVO) != 0) {
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
        if(e.getSource()==vistapersona.btnnuevo){
           Insertar();
            llenagrid();
        }
        if(e.getSource()==vistapersona.btneliminar){
            Eliminar();
             llenagrid();
        }
        if(e.getSource()==vistapersona.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistapersona.btnlimpiar){
            terminator();
        }
        if(e.getSource()==vistapersona.btnactualizar){
            Modificar();
            llenagrid();
        }
    }
    
}
