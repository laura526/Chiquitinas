/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Reportes;
import Modelo.ReporteDAO;
import Vista.frmReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ControladorReportes implements ActionListener {

    Reportes r = new Reportes();
    ReporteDAO dao = new ReporteDAO();
    frmReportes vistaReportes = new frmReportes();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorReportes(frmReportes frm) {
        this.vistaReportes = frm;
        this.vistaReportes.btnBuscar.addActionListener(this);
        this.vistaReportes.btnMostrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaReportes.btnBuscar) {
            try {
                buscarReporteNumero(vistaReportes.tblReportes, vistaReportes.txtBuscar.getText());
            } catch (SQLException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (e.getSource() == vistaReportes.btnMostrar) {
            try {
                listarReportes(vistaReportes.tblReportes);
            } catch (SQLException ex) {
                Logger.getLogger(ControladorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void listarReportes(JTable tabla) throws SQLException {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        limpiarTabla(tabla, modelo);
        List<Reportes> lista = dao.listarReportes();
        Object[] objeto = new Object[7];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getOrdenCompra();
            objeto[1] = lista.get(i).getCliente();
            objeto[2] = lista.get(i).getFecha();
            objeto[3] = lista.get(i).getProducto();
            objeto[4] = lista.get(i).getPrecioUnitario();
            objeto[5] = lista.get(i).getCantidad();
            objeto[6] = lista.get(i).getPrecioTotal();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }

    public void buscarReporteNumero(JTable table, String filtro) throws SQLException {
        dao.buscarReportesNumero(table, filtro);
    }

    //Iniciar pantalla
    public void iniciar() throws SQLException {
        vistaReportes.setTitle("Reportes");
        vistaReportes.setLocationRelativeTo(null);
        listarReportes(vistaReportes.tblReportes);
    }
}
