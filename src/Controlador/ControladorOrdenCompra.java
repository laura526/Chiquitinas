/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Clientes;
import Modelo.OrdenCompra;
import Modelo.OrdenCompraDAO;
import Modelo.Productos;
import Vista.frmOrdenes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP ZBOOK 17 G2 i7
 */
public class ControladorOrdenCompra implements ActionListener {

    private OrdenCompra mod;
    private OrdenCompraDAO dao;
    private frmOrdenes frm;
    private Clientes modCli;
    private Productos modPro;
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorOrdenCompra() {
    }

    public ControladorOrdenCompra(OrdenCompra mod, OrdenCompraDAO dao, frmOrdenes frm, Clientes modCli, Productos modPro) {

        this.mod = mod;
        this.dao = dao;
        this.frm = frm;
        this.modCli = modCli;
        this.modPro = modPro;
        this.frm.btnCrearOrden.addActionListener(this);
        this.frm.btnAgregarOrden.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Ordenes");
        frm.setLocationRelativeTo(null);
        dao.cargarComboClientes(frm.cmbClientes);
        dao.cargarComboProductos(frm.cmbProductos);
        numeroAleatorio();
        crearTabla(frm.jTableOrdenes);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == frm.btnCrearOrden) {
            if (frm.jTableOrdenes.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(frm, "No hay productos en la orden");
            } else {
                dao.crearOrden(frm.jTableOrdenes);
            }
            limpiarTodo();
        }
        if (e.getSource() == frm.btnAgregarOrden) {
            if (frm.txtFecha.getText().equals("    -  -  ")) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar una fecha");
            } else if (frm.cmbClientes.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(frm, "Debe seleccionar un cliente");
            } else if (frm.cmbProductos.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(frm, "Debe seleccionar un Producto");
            } else if (frm.txtCantidad.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un valor mayor a cero");
            } else if (Integer.parseInt(frm.txtCantidad.getText()) <= 0) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un valor mayor a cero");
            } else {
                agregarOrden();
                Limpiar();
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiarTable();
        }
    }

    public void crearTabla(JTable tabla) {
        modelo = new DefaultTableModel();
        modelo.addColumn("NumeroOrden");
        modelo.addColumn("Cliente");
        modelo.addColumn("Fecha");
        modelo.addColumn("Producto");
        modelo.addColumn("PrecioUnitario");
        modelo.addColumn("Cantidad");
        modelo.addColumn("PrecioTotal");
        frm.jTableOrdenes.setModel(modelo);
    }

    public void numeroAleatorio() {
        int numero = (int) (Math.random() * 100000 + 1);
        frm.txtNumeroOrden.setText(String.valueOf(numero));
    }

    //Listar Orden Básicamente
    public void agregarOrden() {
        try {
            String campo[] = new String[7];
            campo[0] = frm.txtNumeroOrden.getText();
            campo[1] = (String) frm.cmbClientes.getSelectedItem();
            campo[2] = frm.txtFecha.getText();
            campo[3] = (String) frm.cmbProductos.getSelectedItem();
            campo[4] = frm.txtPrecio.getText();
            campo[5] = frm.txtCantidad.getText();
            campo[6] = frm.txtPrecioFinal.getText();
            modelo.addRow(campo);
        } catch (Exception e) {
        }
    }

    public void Limpiar() {
        frm.txtCantidad.setText("");
        frm.txtPrecio.setText("");
        frm.txtPrecioFinal.setText("");
        dao.cargarComboProductos(frm.cmbProductos);
    }

    public void limpiarTable() {
        modelo.getDataVector().removeAllElements();
        frm.jTableOrdenes.updateUI();
    }

    public void limpiarTodo() {
        limpiarTable();
        frm.txtFecha.setText("");
        frm.txtCantidad.setText("");
        frm.txtPrecio.setText("");
        frm.txtPrecioFinal.setText("");
        numeroAleatorio();
    }

}
