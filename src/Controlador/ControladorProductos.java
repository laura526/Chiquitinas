/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Productos;
import Modelo.ProductosDAO;
import Vista.frmProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author laura
 */
public class ControladorProductos implements ActionListener {

    //
    Productos p = new Productos();
    ProductosDAO dao = new ProductosDAO();
    frmProductos vistaProductos = new frmProductos();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorProductos(frmProductos frm) {
        this.vistaProductos = frm;
        this.vistaProductos.btnActualizarP.addActionListener(this);
        this.vistaProductos.btnAgregarP.addActionListener(this);
        this.vistaProductos.btnEliminar.addActionListener(this);
        this.vistaProductos.btnLimpiarP.addActionListener(this);
        this.vistaProductos.btnRefrescarP.addActionListener(this);
        this.vistaProductos.btnBuscar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Agregar Productos
        if (e.getSource() == vistaProductos.btnAgregarP) {
            if (vistaProductos.txtTituloProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar un título");
            } else if (vistaProductos.txtCodigoProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar un código");
            } else if (vistaProductos.txtPrecioProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar el precio");
            } else if (vistaProductos.txaDescripProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar la descripción del producto");
            } else if (vistaProductos.txtUnidadesProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar la cantidad de unidades");
            } else {
                agregarProducto();

            }

        }
        //Actualizar Productos
        if (e.getSource() == vistaProductos.btnActualizarP) {
            if (vistaProductos.txtTituloProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar un título");
            } else if (vistaProductos.txtCodigoProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar un código");
            } else if (vistaProductos.txtPrecioProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar el precio");
            } else if (vistaProductos.txaDescripProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar la descripción del producto");
            } else if (vistaProductos.txtUnidadesProducto.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vistaProductos, "Debe ingresar la cantidad de unidades");
            } else {
                actualizarProductos();
                limpiar();
            }

        }
        //Eliminar Productos
        if (e.getSource() == vistaProductos.btnEliminar) {
            eliminarProducto();
        }
        //Buscar Productos por titulo
        if (e.getSource() == vistaProductos.btnBuscar) {
            buscarProductoTitulo(vistaProductos.tblProductos, vistaProductos.txtBuscar.getText());
        }
        //Limpiar Campos
        if (e.getSource() == vistaProductos.btnLimpiarP) {
            limpiar();
        }
        //Refrescar tabla de datos
        if (e.getSource() == vistaProductos.btnRefrescarP) {
            listarProductos(vistaProductos.tblProductos);
        }

    }
    
    //Agregar Productos (conecxion entremodelo y vista)
    public void agregarProducto() {
        String titulo = vistaProductos.txtTituloProducto.getText();
        String codigo = vistaProductos.txtCodigoProducto.getText();
        String descripcion = vistaProductos.txaDescripProducto.getText();
        String precio = vistaProductos.txtPrecioProducto.getText();
        int unidadesStock = Integer.parseInt(vistaProductos.txtUnidadesProducto.getText());
        p.setTitulo(titulo);
        p.setCodigo(codigo);
        p.setDescripcion(descripcion);
        p.setPrecio(precio);
        p.setUnidadesStock(unidadesStock);

        int r = dao.agregarProducto(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistaProductos, "Producto registrado con éxito");
            limpiar();
            listarProductos(vistaProductos.tblProductos);
        } else {
            JOptionPane.showMessageDialog(vistaProductos, "Error al registrar producto");

        }

    }
    //Actualizar Productos (conecxion entremodelo y vista)
    public void actualizarProductos() {
        if (vistaProductos.txtIdProducto.getText().equals("")) {
            JOptionPane.showMessageDialog(vistaProductos, "Favor seleccionar un registro");
        } else {
            int id = Integer.parseInt(vistaProductos.txtIdProducto.getText());
            String titulo = vistaProductos.txtTituloProducto.getText();
            String codigo = vistaProductos.txtCodigoProducto.getText();
            String descripcion = vistaProductos.txaDescripProducto.getText();
            String precio = vistaProductos.txtPrecioProducto.getText();
            int unidadesStock = Integer.parseInt(vistaProductos.txtUnidadesProducto.getText());
            p.setId(id);
            p.setTitulo(titulo);
            p.setCodigo(codigo);
            p.setDescripcion(descripcion);
            p.setPrecio(precio);
            p.setUnidadesStock(unidadesStock);
            int r = dao.actualizarProducto(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistaProductos, "Producto actualizado con éxito");
                limpiar();
                listarProductos(vistaProductos.tblProductos);
            } else {
                JOptionPane.showMessageDialog(vistaProductos, "Error al actualizar producto");
            }
        }

    }
    //Listar Productos (conecxion entremodelo y vista)
    public void listarProductos(JTable tabla) {
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        limpiarTabla(tabla, modelo);

        List<Productos> lista = dao.listarProductos();
        Object[] objeto = new Object[6];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getTitulo();
            objeto[2] = lista.get(i).getCodigo();
            objeto[3] = lista.get(i).getDescripcion();
            objeto[4] = lista.get(i).getPrecio();
            objeto[5] = lista.get(i).getUnidadesStock();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    public void buscarProductoTitulo(JTable table, String filtro) {
        dao.buscarProductosTitulo(table, filtro);
    }

    public void limpiarTabla(JTable table, DefaultTableModel model) {
        for (int i = 0; i < table.getRowCount(); i++) {
            model.removeRow(i);
            i = i - 1;
        }
    }
    //Eliminar Productos (conecxion entremodelo y vista)
    public void eliminarProducto() {
        int fila = vistaProductos.tblProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistaProductos.tblProductos, "Favor seleccionar un registro de la tabla");
        } else {
            int id = Integer.parseInt(vistaProductos.tblProductos.getValueAt(fila, 0).toString());
            dao.eliminarProducto(id);
            JOptionPane.showMessageDialog(vistaProductos.tblProductos, "Producto eliminado");
            limpiar();
            listarProductos(vistaProductos.tblProductos);
        }

    }
    //Limpiar Campos
    public void limpiar() {
        vistaProductos.txtCodigoProducto.setText("");
        vistaProductos.txtIdProducto.setText("");
        vistaProductos.txtPrecioProducto.setText("");
        vistaProductos.txtTituloProducto.setText("");
        vistaProductos.txtUnidadesProducto.setText("");
        vistaProductos.txaDescripProducto.setText("");
    }
    //Iniciar pantalla
    public void iniciar() {
        vistaProductos.setTitle("Productos");
        vistaProductos.setLocationRelativeTo(null);
        listarProductos(vistaProductos.tblProductos);
    }
}
