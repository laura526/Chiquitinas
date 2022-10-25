/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clientes;
import Modelo.ClientesDAO;
import Vista.frmCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP ZBOOK 17 G2 i7
 */
public class ControladorClientes implements ActionListener{

    Clientes c = new Clientes();
    ClientesDAO dao = new ClientesDAO();
    frmCliente vistaClientes = new frmCliente();
    DefaultTableModel modelo = new DefaultTableModel();

   
    
    
    public ControladorClientes(frmCliente frm) {
        this.vistaClientes = frm;
        this.vistaClientes.btnActualizar.addActionListener(this);
        this.vistaClientes.btnAgregar.addActionListener(this);
        this.vistaClientes.btnEliminar.addActionListener(this);
        this.vistaClientes.btnLimpiar.addActionListener(this);
        this.vistaClientes.btnRefrescar.addActionListener(this);
        this.vistaClientes.btnBuscar.addActionListener(this);
        
        
    }

    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == vistaClientes.btnAgregar) {
            if (vistaClientes.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar una cedula");
            } else if (vistaClientes.txtNombreCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar un nombre");
            } else if (vistaClientes.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar una direccion");
            } else if (vistaClientes.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar un telefono");
            } else {
                agregarCliente();
            }
        }

        if (e.getSource() == vistaClientes.btnActualizar) {
            if (vistaClientes.txtCedula.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar una cedula");
            } else if (vistaClientes.txtNombreCliente.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar un nombre");
            } else if (vistaClientes.txtDireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar una direccion");
            } else if (vistaClientes.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaClientes, "Debe ingresar un telefono");
            } else {
                actualizarCliente();
                limpiar();
            }
        }

        if (e.getSource() == vistaClientes.btnEliminar) {
            eliminarCliente();
        }

        if (e.getSource() == vistaClientes.btnBuscar) {
            buscarCliente(vistaClientes.tblClientes, vistaClientes.txtBuscarNombre.getText());
        }

        if (e.getSource() == vistaClientes.btnLimpiar) {
            limpiar();
        }

        if (e.getSource() == vistaClientes.btnRefrescar) {
            listarClientes(vistaClientes.tblClientes);
        }

    }

        
        
    public void agregarCliente() {
        int id = Integer.parseInt(vistaClientes.txtCedula.getText());
        String nombre = vistaClientes.txtNombreCliente.getText();
        String direccion = vistaClientes.txtDireccion.getText();
        String telefono = vistaClientes.txtTelefono.getText();

        c.setId(id);
        c.setNombre(nombre);
        c.setDireccion(direccion);
        c.setTelefono(telefono);

        int r = dao.agregarCliente(c);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaClientes, "Cliente registrado con éxito");
            limpiar();
            listarClientes(vistaClientes.tblClientes);
        } else {
            JOptionPane.showMessageDialog(vistaClientes, "Error al registrar el cliente");
        }
    } 
        
        
        
    public void actualizarCliente() {
        if (vistaClientes.txtCedula.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaClientes, "Favor seleccionar un registro");
        } else {
            int id = Integer.parseInt(vistaClientes.txtCedula.getText());
            String nombre = vistaClientes.txtNombreCliente.getText();
            String direccion = vistaClientes.txtDireccion.getText();
            String telefono = vistaClientes.txtTelefono.getText();

            c.setId(id);
            c.setNombre(nombre);
            c.setDireccion(direccion);
            c.setTelefono(telefono);
            int r = dao.actualizarCliente(c);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaClientes, "Cliente actualizado con éxito");
                limpiar();
                listarClientes(vistaClientes.tblClientes);
            } else {
                JOptionPane.showMessageDialog(vistaClientes, "Error al actualizar cliente");
            }
        }

    }
       
       
       
       
     public void listarClientes(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        limpiarTabla(tabla, modelo);
        List<Clientes> lista = dao.listarClientes();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getDireccion();
            objeto[3] = lista.get(i).getTelefono();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }
        
        
    public void buscarCliente(JTable table, String filtro) {
        dao.buscarClientes(table, filtro);
    }  
        
        
        
    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }  
        
        
        
    public void eliminarCliente() {
        int fila = vistaClientes.tblClientes.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaClientes.tblClientes, "Favor seleccionar un registro de la tabla");
        } else {
            int id = Integer.parseInt(vistaClientes.tblClientes.getValueAt(fila, 0).toString());
            dao.eliminarCliente(id);
            JOptionPane.showMessageDialog(vistaClientes.tblClientes, "Cliente eliminado");
            limpiar();
            listarClientes(vistaClientes.tblClientes);
        }

    }
        
        

    public void limpiar() {
        vistaClientes.txtCedula.setText("");
        vistaClientes.txtNombreCliente.setText("");
        vistaClientes.txtDireccion.setText("");
        vistaClientes.txtTelefono.setText("");
    }    
       
    
     public void iniciar() {
        vistaClientes.setTitle("Clientes");
        vistaClientes.setLocationRelativeTo(null);
        listarClientes(vistaClientes.tblClientes);
    }


}
