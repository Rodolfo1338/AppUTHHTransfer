package Controlador;

import Modelo.DAO.DAORoles;
import Modelo.VO.VORoles;
import Vista.FrmRoles;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorRoles implements ActionListener{
    
    FrmRoles vistaroles;
    DAORoles modeloDAO;
    VORoles modeloVO;

    public ControladorRoles(FrmRoles vistaroles) {
        this.vistaroles = vistaroles;
        if(modeloDAO==null)modeloDAO= new DAORoles();
        llenagrid();
        iniciar();
        
        this.vistaroles.btnnuevo.addActionListener(this);
        this.vistaroles.btnactualizar.addActionListener(this);
        this.vistaroles.btneliminar.addActionListener(this);
        this.vistaroles.btnlimpiar.addActionListener(this);
        this.vistaroles.btnseleccionar.addActionListener(this);
    }
    public void iniciar(){
        vistaroles.setTitle("Roles");
        vistaroles.setLocationRelativeTo(null);
        vistaroles.txtclave.enable(false);
        
    }
    
    private void llenagrid(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaroles.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String rol = contenedorDeRegistro[1].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,rol});
        }
        vistaroles.datos.setModel(modeloDeTabla);
    }
    
     private void terminator(){
        vistaroles.txtclave.setText("");
        vistaroles.txtrol.setText("");
              
    }
    
    private void Seleccionar(){
        int row= vistaroles.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistaroles.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String rol=model.getValueAt(row, 1).toString();
        
        vistaroles.txtclave.setText(String.valueOf(clave));
        vistaroles.txtrol.setText(rol);
        
    }
    
    private void Insertar(){
        modeloVO= new VORoles();
        modeloVO.setRol(vistaroles.txtrol.getText());
        
        
        if(modeloDAO.NewRol(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    private void Eliminar(){
        try {
            modeloVO = new VORoles();
            int row = vistaroles.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistaroles.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistaroles, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteRol(modeloVO) != 0) {
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
            modeloVO = new VORoles();
            modeloVO.setClave(Integer.parseInt(vistaroles.txtclave.getText()));
            modeloVO.setRol(vistaroles.txtrol.getText());
            
            if (modeloDAO.UpdateRol(modeloVO) != 0) {
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
        
        if(e.getSource()==vistaroles.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistaroles.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistaroles.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistaroles.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistaroles.btnlimpiar){
            terminator();
        }
    }
    
    
    
}
