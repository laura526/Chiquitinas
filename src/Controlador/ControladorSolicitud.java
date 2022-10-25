/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.Solicitud;
import Modelo.SolicitudDAO;
import Vista.FrmSolicitud;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nicol
 */
public class ControladorSolicitud implements ActionListener {

    Solicitud soli = new Solicitud();
    SolicitudDAO soliDAO = new SolicitudDAO();
    FrmSolicitud frmSoli = new FrmSolicitud();
    DefaultTableModel modelo = new DefaultTableModel();

    // Medio de comunicación de botones entre el frm y el modelo
    public ControladorSolicitud(FrmSolicitud frm) {
        this.frmSoli = frm;
        this.frmSoli.btnAgregar.addActionListener(this);
        this.frmSoli.btnEditar.addActionListener(this);
        this.frmSoli.btnEliminar.addActionListener(this);
        this.frmSoli.btnLimpiar.addActionListener(this);
        this.frmSoli.btnListar.addActionListener(this);
        this.frmSoli.btnBuscar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frmSoli.btnAgregar) {
            if (frmSoli.txtFecha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique la fecha");
            } else if (frmSoli.txtMaterial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el material");
            } else if (frmSoli.txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique la cantidad");
            }else if (Integer.parseInt(frmSoli.txtCantidad.getText()) <= 0) {
                JOptionPane.showMessageDialog(frmSoli, "Ingrese un número mayor a 0");
            } else if (frmSoli.txtProveedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el proveedor");
            } else if (frmSoli.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el teléfono");
            } else {
                agregarSolicitud();
            }
        }
        if (e.getSource() == frmSoli.btnEditar) {

            if (frmSoli.txtSolicitud.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Debe selecionar un registro en la tabla");
            } else if (frmSoli.txtFecha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique la fecha");
            } else if (frmSoli.txtMaterial.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el material");
            } else if (frmSoli.txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique la cantidad");
            }else if (Integer.parseInt(frmSoli.txtCantidad.getText()) <= 0) {
                JOptionPane.showMessageDialog(frmSoli, "Ingrese un número mayor a 0");
            } else if (frmSoli.txtProveedor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el proveedor");
            } else if (frmSoli.txtTelefono.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frmSoli, "Indique el teléfono");
            } else {
                actualizarSolicitud();
            }
        }
        if (e.getSource() == frmSoli.btnEliminar) {
            eliminarSolicitud();
            listarSolicitud(frmSoli.tblSolicitud);
        }
        if (e.getSource() == frmSoli.btnListar) {
            listarSolicitud(frmSoli.tblSolicitud);
        }
        if (e.getSource() == frmSoli.btnLimpiar) {
            limpiarCampos();
        }
        if (e.getSource() == frmSoli.btnBuscar) {
            filtrarTablaPorNunSolicitud(frmSoli.tblSolicitud, frmSoli.txtBuscar.getText());
            frmSoli.txtBuscar.setText("");
        }
    }

    //Agregando Solicitud
    public void agregarSolicitud() {
        String fecha = frmSoli.txtFecha.getText();
        String material = frmSoli.txtMaterial.getText();
        int cantidad = Integer.parseInt(frmSoli.txtCantidad.getText());
        String proveedor = frmSoli.txtProveedor.getText();
        String telefono = frmSoli.txtTelefono.getText();
        String comentario = frmSoli.txtComentario.getText();
        soli.setFecha(fecha);
        soli.setMaterial(material);
        soli.setCantidad(cantidad);
        soli.setProveedor(proveedor);
        soli.setTelefono(telefono);
        soli.setComentario(comentario);
        int r = soliDAO.agregarSolicitud(soli);
        if (r == 1) {
            JOptionPane.showMessageDialog(frmSoli, "Solicitud enviada con éxito");
            limpiarCampos();
            listarSolicitud(frmSoli.tblSolicitud);
        } else {
            JOptionPane.showMessageDialog(frmSoli, "Solicitud no enviada con éxito");
        }
    }

    //Listando Solicitud
    public void listarSolicitud(JTable tabla) {
        //Formato de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        limpiarTabla(tabla, modelo);

        List<Solicitud> lista = soliDAO.listarSolicitud();
        Object[] solicitud = new Object[7];

        for (int i = 0; i < lista.size(); i++) {
            solicitud[0] = lista.get(i).getNumSolicitud();
            solicitud[1] = lista.get(i).getFecha();
            solicitud[2] = lista.get(i).getMaterial();
            solicitud[3] = lista.get(i).getCantidad();
            solicitud[4] = lista.get(i).getProveedor();
            solicitud[5] = lista.get(i).getTelefono();
            solicitud[6] = lista.get(i).getComentario();
            modelo.addRow(solicitud);

        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    // Actualizando Solicitud
    public void actualizarSolicitud() {
        if (frmSoli.txtSolicitud.getText().equals("")) {
            JOptionPane.showMessageDialog(frmSoli, "Porfavor seleccione un registro en la tabla");
        } else {
            int numSolicitud = Integer.parseInt(frmSoli.txtSolicitud.getText());
            String fecha = frmSoli.txtFecha.getText();
            String material = frmSoli.txtMaterial.getText();
            int cantidad = Integer.parseInt(frmSoli.txtCantidad.getText());
            String proveedor = frmSoli.txtProveedor.getText();
            String telefono = frmSoli.txtTelefono.getText();
            String comentario = frmSoli.txtComentario.getText();
            soli.setNumSolicitud(numSolicitud);
            soli.setFecha(fecha);
            soli.setMaterial(material);
            soli.setCantidad(cantidad);
            soli.setProveedor(proveedor);
            soli.setTelefono(telefono);
            soli.setComentario(comentario);
            int r = soliDAO.editarSolicitud(soli);
            if (r == 1) {
                JOptionPane.showMessageDialog(frmSoli, "Solicitud actualizada con éxito");
                limpiarCampos();
                listarSolicitud(frmSoli.tblSolicitud);
            } else {
                JOptionPane.showMessageDialog(frmSoli, "Solicitud no actualizada con éxito");
            }
        }
    }

    // Eliminando Solicitud
    public void eliminarSolicitud() {
        int fila = frmSoli.tblSolicitud.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(frmSoli, "Debe seleccionar un registro en la tabla");
        } else {
            int numSolicitud = Integer.parseInt(frmSoli.tblSolicitud.getValueAt(fila, 0).toString());
            soliDAO.eliminarSolicitud(numSolicitud);
            JOptionPane.showMessageDialog(frmSoli, "Solicitud eliminada con éxito");
            limpiarCampos();
            listarSolicitud(frmSoli.tblSolicitud);
        }
    }

    // Filtrando Solicitud
    public void filtrarTablaPorNunSolicitud(JTable table, String filtro) {
        soliDAO.filtrarTablaPorNumSolicitud(table, filtro);
    }

    // Lipiando Tabla Solicitudes
    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    //Limpiando Campos de agregar solicitud
    public void limpiarCampos() {
        frmSoli.txtSolicitud.setText("");
        frmSoli.txtFecha.setText("");
        frmSoli.txtMaterial.setText("");
        frmSoli.txtCantidad.setText("");
        frmSoli.txtProveedor.setText("");
        frmSoli.txtTelefono.setText("");
        frmSoli.txtComentario.setText("");
        
    }

    // Mostrando Pantalla
    public void iniciar() {
        frmSoli.setTitle("Solicitudes");
        frmSoli.setLocationRelativeTo(null);
        listarSolicitud(frmSoli.tblSolicitud);
    }

}
