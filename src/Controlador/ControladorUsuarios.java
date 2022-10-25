package Controlador;

import Modelo.UsuariosDAO;
import Modelo.Usuarios;
import Vista.frmUsuarios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorUsuarios implements ActionListener {

    private Usuarios mod;
    private UsuariosDAO modC;
    private frmUsuarios frm;

    public ControladorUsuarios(Usuarios mod, UsuariosDAO modC, frmUsuarios frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;

        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
    }

    /*
        ------------ INICIAR LA VISTA ---------------
     */
    public void iniciar() {
        frm.setTitle("Usuarios");
        frm.setLocationRelativeTo(null);
        frm.txtId.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*
        ------------ GUARDADO DE USUARIOS---------------
         */
        if (e.getSource() == frm.btnGuardar) {
            if (frm.txtUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un Usuario");
            } else if (frm.txtPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar una contrasenna");
            } else if (frm.txtTipo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe Ingresar un tipo");
            } else {

                mod.setUsuario(frm.txtUsuario.getText());
                mod.setPass(frm.txtPass.getText());
                mod.setTipoUsuario(frm.txtTipo.getText());

                if (modC.registrar(mod)) {
                    JOptionPane.showMessageDialog(null, "Guardado =D");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "NO Guardado =/ ");
                    limpiar();
                }

            }

        }


        /*
        ------------ MODIFICADO DE USUARIOS---------------
         */
        if (e.getSource() == frm.btnModificar) {
            if (frm.txtUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un Usuario");
            } else if (frm.txtPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar una contrasenna");
            } else if (frm.txtTipo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe Ingresar un tipo");
            } else {
                mod.setId(Integer.parseInt(frm.txtId.getText()));
                mod.setUsuario(frm.txtUsuario.getText());
                mod.setPass(frm.txtPass.getText());
                mod.setTipoUsuario(frm.txtTipo.getText());

                if (modC.modificar(mod)) {
                    JOptionPane.showMessageDialog(null, "Modificado =D");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "NO Modificado =/ ");
                    limpiar();
                }
            }
        }

        /*
        ------------ ELIMINADO DE USUARIOS---------------
         */
        if (e.getSource() == frm.btnEliminar) {
            if (frm.txtUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un Usuario");
            } else if (frm.txtPass.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar una contrasenna");
            } else if (frm.txtTipo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe Ingresar un tipo");
            } else {
                mod.setId(Integer.parseInt(frm.txtId.getText()));

                if (modC.eliminar(mod)) {
                    JOptionPane.showMessageDialog(null, "Eliminado =D");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "NO fue Eliminado =/ ");
                    limpiar();
                }
            }
        }

        /*
        ------------ BUSQUEDA DE USUARIOS---------------
         */
        if (e.getSource() == frm.btnBuscar) {
            if (frm.txtUsuario.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frm, "Debe ingresar un Usuario");
            } else {
                mod.setUsuario(frm.txtUsuario.getText());

                if (modC.buscar(mod)) {

                    frm.txtId.setText(String.valueOf(mod.getId()));
                    frm.txtUsuario.setText(mod.getUsuario());
                    frm.txtPass.setText(mod.getPass());
                    frm.txtTipo.setText(mod.getTipoUsuario());

                } else {
                    JOptionPane.showMessageDialog(null, "No hay resultado =/ ");
                    limpiar();
                }
            }

            if (e.getSource()
                    == frm.btnLimpiar) {
                limpiar();
            }
        }
    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtUsuario.setText(null);
        frm.txtPass.setText(null);
        frm.txtTipo.setText(null);
    }

}
