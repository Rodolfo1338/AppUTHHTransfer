package Controlador;

import Modelo.DAO.DAOTipocuentas;
import Modelo.VO.VOTipocuentas;
import Vista.FrmTiposcuentas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorTipoCuentas implements ActionListener{
   
    FrmTiposcuentas vistatipocuentas;
    DAOTipocuentas modeloDAO;
    VOTipocuentas modeloVO;

    public ControladorTipoCuentas(FrmTiposcuentas vistatipocuentas) {
        this.vistatipocuentas = vistatipocuentas;
        if(modeloDAO==null)modeloDAO= new DAOTipocuentas();
        llenagrid();
        iniciar();
        
        this.vistatipocuentas.btnnuevo.addActionListener(this);
        this.vistatipocuentas.btnactualizar.addActionListener(this);
        this.vistatipocuentas.btneliminar.addActionListener(this);
        this.vistatipocuentas.btnlimpiar.addActionListener(this);
        this.vistatipocuentas.btnseleccionar.addActionListener(this);
    }
    
    public void iniciar(){
        vistatipocuentas.setTitle("Servicios");
        vistatipocuentas.setLocationRelativeTo(null);
        vistatipocuentas.txtclave.enable(false);
        
    }
    
    private void llenagrid(){
        DefaultTableModel modeloDeTabla = (DefaultTableModel) vistatipocuentas.datos.getModel();
        modeloDeTabla.setRowCount(0);
        ArrayList<Object[]> informacion = modeloDAO.consultar();
        Object[] contenedorDeRegistro;
        for (Object[] registro : informacion) {
            contenedorDeRegistro = registro;
            String id = contenedorDeRegistro[0].toString();
            String tipocuenta = contenedorDeRegistro[1].toString();
            
            
            
            modeloDeTabla.addRow(new Object[]{id,tipocuenta});
        }
        vistatipocuentas.datos.setModel(modeloDeTabla);
    }
    
    private void terminator(){
        vistatipocuentas.txtclave.setText("");
        vistatipocuentas.txttipocuenta.setText("");
               
    }
    private void Seleccionar(){
        int row= vistatipocuentas.datos.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)vistatipocuentas.datos.getModel();
        int clave=Integer.parseInt(model.getValueAt(row, 0).toString());
        String tipocuenta=model.getValueAt(row, 1).toString();
        
       
        vistatipocuentas.txtclave.setText(String.valueOf(clave));
        vistatipocuentas.txttipocuenta.setText(tipocuenta);
        
        
    }
    private void Insertar(){
        modeloVO= new VOTipocuentas();
        modeloVO.setTipocuenta(vistatipocuentas.txttipocuenta.getText());
        if(modeloDAO.NewTipocuenta(modeloVO)!=0){
            JOptionPane.showMessageDialog(null, "Insercion Exitosa","Atención",JOptionPane.INFORMATION_MESSAGE);
            terminator();
        }else{
            JOptionPane.showMessageDialog(null, "Insercion Fallida","Atención",JOptionPane.ERROR_MESSAGE);
            terminator();
        }
    }
    
    private void Eliminar(){
        try {
            modeloVO = new VOTipocuentas();
            int row = vistatipocuentas.datos.getSelectedRow();
            DefaultTableModel model = (DefaultTableModel) vistatipocuentas.datos.getModel();
            String nombre = model.getValueAt(row, 1).toString();
            int clave = Integer.parseInt(model.getValueAt(row, 0).toString());

            int n = JOptionPane.showConfirmDialog(vistatipocuentas, "Seguro que deseas eliminar a " + nombre + " ?");
            if (n == JOptionPane.OK_OPTION) {
                modeloVO.setClave(clave);
                if (modeloDAO.DeleteTipoCuentas(modeloVO) != 0) {
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
            modeloVO = new VOTipocuentas();
            modeloVO.setClave(Integer.parseInt(vistatipocuentas.txtclave.getText()));
            modeloVO.setTipocuenta(vistatipocuentas.txttipocuenta.getText());
            if (modeloDAO.UpdateTipoCuentas(modeloVO) != 0) {
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
        if(e.getSource()==vistatipocuentas.btnnuevo){
            Insertar();
            llenagrid();
        }
        if(e.getSource()==vistatipocuentas.btneliminar){
            Eliminar();
            llenagrid();
        }
        if(e.getSource()==vistatipocuentas.btnactualizar){
            Modificar();
            llenagrid();
        }
        if(e.getSource()==vistatipocuentas.btnseleccionar){
            Seleccionar();
        }
        if(e.getSource()==vistatipocuentas.btnlimpiar){
            terminator();
        }
    }
    
}
