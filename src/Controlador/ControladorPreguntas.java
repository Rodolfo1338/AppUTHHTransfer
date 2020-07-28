/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOPreguntas;
import Modelo.VO.VOPreguntas;
import Vista.FRMPreguntas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorPreguntas implements ActionListener{
    
    FRMPreguntas vistapreguntas;
    DAOPreguntas modeloDAO;
    VOPreguntas modeloVO;

    public ControladorPreguntas(FRMPreguntas vistapreguntas) {
        this.vistapreguntas = vistapreguntas;
        if(modeloDAO==null)modeloDAO= new DAOPreguntas();
        llenagrid();
        iniciar();
        
        this.vistapreguntas.btnnuevo.addActionListener(this);
        this.vistapreguntas.btnactualizar.addActionListener(this);
        this.vistapreguntas.btneliminar.addActionListener(this);
        this.vistapreguntas.btnlimpiar.addActionListener(this);
        this.vistapreguntas.btnseleccionar.addActionListener(this);
    }
    

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistapreguntas.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String preguntas = contenedorDeRegistro[1].toString();

            modeloDeTabla.addRow(new Object[]{id, preguntas});
        }
        vistapreguntas.datos.setModel(modeloDeTabla);
    }

    private void iniciar() {
        vistapreguntas.setTitle("Preguntas de Seguridad");
        vistapreguntas.setLocationRelativeTo(null);
        vistapreguntas.txtclave.enable(false);
    }
    
    private void terminator(){
        vistapreguntas.txtclave.setText("");
        vistapreguntas.txtpregunta.setText("");
              
    }
     private void Seleccionar(){
        int row= vistapreguntas.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistapreguntas.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String pregunta=model.getValueAt(row, 1).toString();
        
        vistapreguntas.txtclave.setText(String.valueOf(clave));
        vistapreguntas.txtpregunta.setText(pregunta);
        
    }
     private void Insertar(){
        modeloVO= new VOPreguntas();
        modeloVO.setPregunta(vistapreguntas.txtpregunta.getText());
        
        
        if(modeloDAO.NuevaPregunta(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    private void Eliminar(){
        try {
            modeloVO = new VOPreguntas();
            int row = vistapreguntas.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistapreguntas.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistapreguntas, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.BorrarPregunta(modeloVO) != 0) {
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
            modeloVO = new VOPreguntas();
            modeloVO.setClave(Integer.parseInt(vistapreguntas.txtclave.getText()));
            modeloVO.setPregunta(vistapreguntas.txtpregunta.getText());
            
            if (modeloDAO.ActualizarPregunta(modeloVO) != 0) {
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
        if(e.getSource()==vistapreguntas.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistapreguntas.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistapreguntas.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistapreguntas.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistapreguntas.btnlimpiar){
            terminator();
        }
    }
}
