/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;

/**
 *
 * @author HP ZBOOK 17 G2 i7
 */
public class OrdenCompraDAO extends Conexion {

    PreparedStatement ps = null;
    ResultSet rs;
    Connection con = conexion();

    /*public boolean registrarOrden(OrdenCompra orden, Clientes cliente, Productos producto) {
        String SQL = "INSERT INTO ordenes (ordenCompra,cliente,fecha,producto,precioUnitario,cantidad,precioTotal) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, orden.getOrdenCompra());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, orden.getFecha());
            ps.setString(4, producto.getTitulo());
            ps.setString(5, producto.getPrecio());
            ps.setInt(6, orden.getCantidad());
            ps.setInt(7, orden.getPrecioFinal());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }*/

    public void cargarComboClientes(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Seleccionar Cliente");
        String SQL = "SELECT * FROM clientes";
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
        }
    }

    public void cargarComboProductos(JComboBox combo) {
        combo.removeAllItems();
        combo.addItem("Seleccionar Producto");
        String SQL = "SELECT * FROM productos";
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("titulo"));
            }
        } catch (SQLException e) {
        }
    }

    public void cargarPrecioProducto(JComboBox combo, JTextField text) {
        String SQL = "SELECT * FROM productos WHERE titulo='" + combo.getSelectedItem() + "'";
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            rs.next();
            text.setText(String.valueOf(rs.getString("precio")));
        } catch (SQLException e) {
        }
    }

    public boolean crearOrden(JTable table) {

        try {
            for (int i = 0; i < table.getRowCount(); i++) {
                String SQL = "INSERT INTO ordenes (ordenCompra,cliente,fecha,producto,precioUnitario,cantidad,precioTotal) VALUES (?,?,?,?,?,?,?)";
                ps = con.prepareStatement(SQL);
                ps.setInt(1, Integer.parseInt(table.getValueAt(i, 0).toString()));
                ps.setString(2, table.getValueAt(i, 1).toString());
                ps.setString(3, table.getValueAt(i, 2).toString());
                ps.setString(4, table.getValueAt(i, 3).toString());
                ps.setInt(5, Integer.parseInt(table.getValueAt(i, 4).toString()));
                ps.setInt(6, Integer.parseInt(table.getValueAt(i, 5).toString()));
                ps.setInt(7, Integer.parseInt(table.getValueAt(i, 6).toString()));
                ps.execute();
            }
            JOptionPane.showMessageDialog(null, "Orden de compra registrada correctamente");
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar orden " + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            }
        }
    }
}
