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
 * @author nicol
 */
public class SolicitudDAO extends Conexion {

    Connection con = conexion();
    PreparedStatement pst;
    ResultSet rs;

    // Agregando Solicitud
    public int agregarSolicitud(Solicitud s) {
        int r = 0;
        String sql = "INSERT INTO solicitud (fecha,material,cantidad,proveedor,telefono,comentario) VALUES(?,?,?,?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, s.getFecha());
            pst.setString(2, s.getMaterial());
            pst.setInt(3, s.getCantidad());
            pst.setString(4, s.getProveedor());
            pst.setString(5, s.getTelefono());
            pst.setString(6, s.getComentario());
            r = pst.executeUpdate();
           
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR de solicitud" + e.getMessage());
        }
        return r;
    }

    // Listando Solicitud
    public List listarSolicitud() {

        List<Solicitud> solicitud = new ArrayList<>();
        String sql = "SELECT * FROM solicitud";
        try {
            //Haciendo conexión y Preparando sentencia de query 
            pst = con.prepareStatement(sql);
            //Ejecutando sentencia de Query
            ResultSet rs = pst.executeQuery();
            //Para que se siga haciendo hasta que se acaben las columnas x eso el .next
            while (rs.next()) {
                Solicitud s = new Solicitud();
                s.setNumSolicitud(rs.getInt(1));
                s.setFecha(rs.getString(2));
                s.setMaterial(rs.getString(3));
                s.setCantidad(rs.getInt(4));
                s.setProveedor(rs.getString(5));
                s.setTelefono(rs.getString(6));
                s.setComentario(rs.getString(7));
                solicitud.add(s);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al listar" + e.getMessage());
        }

        return solicitud;
    }

    //// Editando Solicitud
    public int editarSolicitud(Solicitud s) {
        int r = 0;
        String SQL = "UPDATE solicitud SET fecha=?, material=?, cantidad=?, proveedor=?, telefono=?, comentario=? WHERE numSolicitud=?";
        try {
            pst = con.prepareStatement(SQL);
            pst.setString(1, s.getFecha());
            pst.setString(2, s.getMaterial());
            pst.setInt(3, s.getCantidad());
            pst.setString(4, s.getProveedor());
            pst.setString(5, s.getTelefono());
            pst.setString(6, s.getComentario());
            pst.setInt(7, s.getNumSolicitud());
            r = pst.executeUpdate();
            if (r == 1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR de actualización" + e.getMessage());
        }
        return r;
    }

    // Filtrando Solicitudes
    public void filtrarTablaPorNumSolicitud(JTable table, String filtro) {

        String[] titulos = {"numSolicitud", "fecha", "material", "cantidad", "proveedor", "telefono", "comentario"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM solicitud WHERE numSolicitud LIKE '%" + filtro + "%'";

        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, titulos);

        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                registros[0] = rs.getString("numSolicitud");
                registros[1] = rs.getString("fecha");
                registros[2] = rs.getString("material");
                registros[3] = rs.getString("cantidad");
                registros[4] = rs.getString("proveedor");
                registros[5] = rs.getString("telefono");
                registros[6] = rs.getString("comentario");
                model.addRow(registros);
            }
            table.setModel(model);

        } catch (SQLException e) {
            System.out.println("ERROR AL BUSCAR DATOS: " + e.getMessage());
        }
    }

    // Eliminando Solicitud
    public int eliminarSolicitud(int numSolicitud) {
        int r = 0;
        String SQL = "DELETE FROM solicitud WHERE numSolicitud=" + numSolicitud;
        try {
            pst = con.prepareStatement(SQL);
            r = pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR al eliminar" + e.getMessage());
        }
        return r;
    }

}
