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
 * @author laura
 */
public class ProductosDAO extends Conexion {

    //Variables Globales
    PreparedStatement ps;
    ResultSet rs;
    Connection con = conexion();
    // Listar Productos
    public List listarProductos() {
        List<Productos> datos = new ArrayList<>();
        String SQL = "SELECT * FROM productos;";
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId(rs.getInt(1));
                p.setTitulo(rs.getString(2));
                p.setCodigo(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getString(5));
                p.setUnidadesStock(rs.getInt(6));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;

    }
    //Agregar Productos
    public int agregarProducto(Productos pro) {
        int r = 0;
        String SQL = "INSERT INTO productos (titulo,codigo,descripcion,precio,unidadesStock) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, pro.getTitulo());
            ps.setString(2, pro.getCodigo());
            ps.setString(3, pro.getDescripcion());
            ps.setString(4, pro.getPrecio());
            ps.setInt(5, pro.getUnidadesStock());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto " + e.getMessage());
        }
        return r;
    }
    //Actualizar Productos
    public int actualizarProducto(Productos pro) {
        int r = 0;
        String SQL = "UPDATE productos SET titulo=?,codigo=?,descripcion=?,precio=?,unidadesStock=? WHERE id=?";
        try {
            ps = con.prepareStatement(SQL);
            ps.setString(1, pro.getTitulo());
            ps.setString(2, pro.getCodigo());
            ps.setString(3, pro.getDescripcion());
            ps.setString(4, pro.getPrecio());
            ps.setInt(5, pro.getUnidadesStock());
            ps.setInt(6, pro.getId());
            r = ps.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar producto " + e.getMessage());
        }
        return r;
    }
    //Eliminar Productos
    public int eliminarProducto(int id) {
        int r = 0;
        String SQL = "DELETE FROM productos WHERE id=" + id;
        try {
            ps = con.prepareStatement(SQL);
            r = ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    // Buscar Productos por medio de titulos
    public void buscarProductosTitulo(JTable table, String filtro) {
        String[] titulos = {"id", "titulo", "codigo", "descripcion", "precio", "unidadesStock"};
        String[] registros = new String[6];
        String SQL = "SELECT * FROM productos WHERE titulo LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);
        try {
            ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("titulo");
                registros[2] = rs.getString("codigo");
                registros[3] = rs.getString("descripcion");
                registros[4] = rs.getString("precio");
                registros[5] = rs.getString("unidadesStock");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar producto " + e.getMessage());
        }

    }

}
