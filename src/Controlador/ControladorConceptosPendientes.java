/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.DAO.DAOConceptosPendientes;
import Modelo.VO.VOConceptosPendientes;
import Vista.FRMAsignarconceptos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorConceptosPendientes implements ActionListener{

    FRMAsignarconceptos vistaasignarconceptos;
    
   
    DAOConceptosPendientes modeloDAO;
    VOConceptosPendientes modeloVO;

    public ControladorConceptosPendientes(FRMAsignarconceptos vistaasignarconceptos) {
        this.vistaasignarconceptos = vistaasignarconceptos;
        if(modeloDAO==null)modeloDAO= new DAOConceptosPendientes();
        llenagrid();
        llenacomboalumnos();
        llenacomboconceptos();
        llenacombomaterias();
        iniciar();
        
        this.vistaasignarconceptos.btnaceptar.addActionListener(this);
        this.vistaasignarconceptos.btneliminar.addActionListener(this);
        
        
       
        
        
        
    }
    
    
    
   

    private void iniciar() {
        vistaasignarconceptos.setTitle("Asignacion de  Conceptos");
        vistaasignarconceptos.setLocationRelativeTo(null);
        
    }
    

     @Override
    public void actionPerformed(ActionEvent e) {
       
        if(e.getSource()==vistaasignarconceptos.btnaceptar){
            Insertar();
            llenagrid();
            terminator();
        }
        if(e.getSource()==vistaasignarconceptos.btneliminar){
            Eliminar();
            llenagrid();
        }
        
    }

    private void llenagrid() {
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaasignarconceptos.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultarconceptospendientes();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String concepto = contenedorDeRegistro[1].toString();
            String matricula = contenedorDeRegistro[2].toString();
            String nombre = contenedorDeRegistro[3].toString();
            String materia = contenedorDeRegistro[4].toString();
            String costo = contenedorDeRegistro[5].toString();
            
            
            
            
            modeloDeTabla.addRow(new Object[]{id,concepto,matricula,nombre,materia,costo});
        }
        vistaasignarconceptos.datos.setModel(modeloDeTabla);
    }
    
     private void llenacomboalumnos(){
        vistaasignarconceptos.cmbAlumno.setModel(modeloDAO.comboalumnos());
    }
     
    private void llenacomboconceptos(){
        vistaasignarconceptos.cmbConceptos.setModel(modeloDAO.comboconceptos());
    }
    private void llenacombomaterias(){
        vistaasignarconceptos.cmbMaterias.setModel(modeloDAO.combomaterias());
    }
    
    private void mensaje(){
        String nombre= String.valueOf(vistaasignarconceptos.cmbAlumno.getSelectedItem());
        JOptionPane.showMessageDialog(vistaasignarconceptos, nombre);
    }
    
    private void Insertar(){
        modeloVO= new VOConceptosPendientes();
        String nombre= String.valueOf(vistaasignarconceptos.cmbAlumno.getSelectedItem());
        String concepto=String.valueOf(vistaasignarconceptos.cmbConceptos.getSelectedItem());
        String materia= String.valueOf(vistaasignarconceptos.cmbMaterias.getSelectedItem());
        int idalumno=modeloDAO.obteneridalumnos(nombre);
        int idconcepto=modeloDAO.obteneridconceptos(concepto);
        int idmateria= modeloDAO.obteneridmaterias(materia);
        
        modeloVO.setIdalumno(idalumno);
        modeloVO.setConcepto(idconcepto);
        modeloVO.setIdmateria(idmateria);
        modeloVO.setEstadoconcepto(1);
        
        if (modeloDAO.NuevoConcepto(modeloVO) != 0) {
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);

        }else{
             JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }
    private void Eliminar(){
        try {
            modeloVO = new VOConceptosPendientes();
            int row = vistaasignarconceptos.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistaasignarconceptos.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistaasignarconceptos, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteConceptoPendiente(modeloVO) != 0) {
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

    private void terminator() {
        vistaasignarconceptos.cmbAlumno.setSelectedIndex(0);
        vistaasignarconceptos.cmbConceptos.setSelectedIndex(0);
        vistaasignarconceptos.cmbMaterias.setSelectedIndex(0);
    }
}
