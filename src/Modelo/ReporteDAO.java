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
 * @author Luis Piedra
 */
public class ReporteDAO extends Conexion{
    
    //Variables Globales
    PreparedStatement ps;
    ResultSet rs;
    Connection con = conexion();
    
    // Listar ordenes
    public List listarReportes() throws SQLException{
        List<Reportes> data = new ArrayList<>();
        String sql = "SELECT * FROM ordenes";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Reportes r = new Reportes();
                r.setOrdenCompra(rs.getInt(1));
                r.setCliente(rs.getString(2));
                r.setFecha(rs.getDate(3));
                r.setProducto(rs.getString(4));
                r.setPrecioUnitario(rs.getInt(5));
                r.setCantidad(rs.getInt(6));
                r.setPrecioTotal(rs.getInt(7));
                data.add(r);
            }
        } catch (Exception e) {
            
        }
        return data;
    }
    
    //Buscar productos por número de orden
    public void buscarReportesNumero(JTable table, String filtro) throws SQLException{
        String[] columnas = {"Orden Compra", "Cliente", "Fecha", "Producto", "Precio Unitario", "Cantidad", "Precio Total"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM ordenes WHERE ordenCompra LIKE '%" + filtro + "%' or cliente LIKE '%" + filtro + "%'";
        DefaultTableModel model = new DefaultTableModel();
        model = new DefaultTableModel(null, columnas);
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                registros[0] = rs.getString("ordenCompra");
                registros[1] = rs.getString("cliente");
                registros[2] = rs.getString("fecha");
                registros[3] = rs.getString("producto");
                registros[4] = rs.getString("precioUnitario");
                registros[5] = rs.getString("cantidad");
                registros[6] = rs.getString("precioUnitario");
                model.addRow(registros);
            }
            table.setModel(model);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta " + e.getMessage());
        }
        
    }
    
    
}
