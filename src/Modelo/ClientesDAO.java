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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP ZBOOK 17 G2 i7
 */
public class ClientesDAO extends Conexion {

    PreparedStatement ps;
    ResultSet rs;
    Connection con = conexion();

    public List listarClientes() {
        List<Clientes> datos = new ArrayList<>();
        String SQL = "SELECT * FROM clientes;";
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Clientes c = new Clientes();
                c.setId(rs.getInt(1));
                c.setNombre(rs.getString(2));
                c.setDireccion(rs.getString(3));
                c.setTelefono(rs.getString(4));
                datos.add(c);
            }
        } catch (Exception e) {
        }
        return datos;
    }

    public int agregarCliente(Clientes cli) {
        int r = 0;
        String SQL = "INSERT INTO clientes (id,nombre,direccion,telefono) VALUES (?,?,?,?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setInt(1, cli.getId());
            ps.setString(2, cli.getNombre());
            ps.setString(3, cli.getDireccion());
            ps.setString(4, cli.getTelefono());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el cliente " + e.getMessage());
        }
        return r;
    }

    public int actualizarCliente(Clientes cli) {
        int r = 0;
        String SQL = "UPDATE clientes SET nombre=?,direccion=?,telefono=? WHERE id=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, cli.getNombre());
            ps.setString(2, cli.getDireccion());
            ps.setString(3, cli.getTelefono());
            ps.setInt(4, cli.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente " + e.getMessage());
        }
        return r;
    }

    public int eliminarCliente(int id) {
        int r = 0;
        String SQL = "DELETE FROM clientes WHERE id=" + id;
        try {
            ps = con.prepareStatement(SQL);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public void buscarClientes(JTable table, String filtro) {
        String[] titulos = {"id", "nombre", "direccion", "telefono"};
        String[] registros = new String[4];
        String SQL = "SELECT * FROM clientes WHERE nombre LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("direccion");
                registros[3] = rs.getString("telefono");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar el cliente " + e.getMessage());
        }

    }

}
