package Controlador;

import Modelo.DAO.DAOPrivilegios;
import Modelo.VO.VOPrivilegios;
import Vista.FrmPrivilegios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodol
 */
public class ControladorProvilegios implements ActionListener{
    
    FrmPrivilegios vistaprivilegios;
    DAOPrivilegios modeloDAO;
    VOPrivilegios modeloVO;

    public ControladorProvilegios(FrmPrivilegios vistaprivilegios) {
        this.vistaprivilegios = vistaprivilegios;
        if(modeloDAO==null)modeloDAO= new DAOPrivilegios();
        llenagrid();
        iniciar();
        
        this.vistaprivilegios.btnnuevo.addActionListener(this);
        this.vistaprivilegios.btnactualizar.addActionListener(this);
        this.vistaprivilegios.btneliminar.addActionListener(this);
        this.vistaprivilegios.btnlimpiar.addActionListener(this);
        this.vistaprivilegios.btnseleccionar.addActionListener(this);
               
               
    }
    
    private void iniciar(){
        vistaprivilegios.setTitle("Privilegios");
        vistaprivilegios.setLocationRelativeTo(null);
        vistaprivilegios.txtclave.enable(false);
    }
            
    private void llenagrid(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistaprivilegios.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String privilegio = contenedorDeRegistro[1].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,privilegio});
        }
        vistaprivilegios.datos.setModel(modeloDeTabla);
    }
    
    private void terminator(){
        vistaprivilegios.txtclave.setText("");
        vistaprivilegios.txtprivilegio.setText("");
             
    }
    
     private void Seleccionar(){
        int row= vistaprivilegios.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistaprivilegios.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String privilegio=model.getValueAt(row, 1).toString();
        
       
        vistaprivilegios.txtclave.setText(String.valueOf(clave));
        vistaprivilegios.txtprivilegio.setText(privilegio);
       
    }
    
     private void Insertar(){
        modeloVO= new VOPrivilegios();
        modeloVO.setPrivilegio(vistaprivilegios.txtprivilegio.getText());
        
        
        if(modeloDAO.NewPrivilegio(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
     
    private void Eliminar(){
        try {
            modeloVO = new VOPrivilegios();
            int row = vistaprivilegios.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistaprivilegios.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistaprivilegios, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeletePrivilegio(modeloVO) != 0) {
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
            modeloVO = new VOPrivilegios();
            modeloVO.setClave(Integer.parseInt(vistaprivilegios.txtclave.getText()));
            modeloVO.setPrivilegio(vistaprivilegios.txtprivilegio.getText());
            
            if (modeloDAO.UpdatePrivilegio(modeloVO) != 0) {
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
        
        if(e.getSource()==vistaprivilegios.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistaprivilegios.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistaprivilegios.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistaprivilegios.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistaprivilegios.btnlimpiar){
            terminator();
        }
    }
    
    
}
