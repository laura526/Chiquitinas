/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.ControladorClientes;
import Controlador.ControladorOrdenCompra;
import Controlador.ControladorProductos;
import Controlador.ControladorSolicitud;
import Controlador.ControladorUsuarios;
import Controlador.ControladorReportes;
import Modelo.Clientes;
import Modelo.OrdenCompra;
import Modelo.OrdenCompraDAO;
import Modelo.Productos;
import Modelo.UsuariosDAO;
import Modelo.Usuarios;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amont
 */
public class frmMenu extends javax.swing.JFrame {

    /**
     * Creates new form frmMenu
     */
    public frmMenu() {
        initComponents();
        setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        menuBarra = new javax.swing.JMenuBar();
        manuPersonal = new javax.swing.JMenu();
        subMenuManejoUsuarios = new javax.swing.JMenuItem();
        menuManejo = new javax.swing.JMenu();
        subMenuIngresarCliente = new javax.swing.JMenuItem();
        subMenuIngresoProductos = new javax.swing.JMenuItem();
        subMenuGenerarSolicitud = new javax.swing.JMenuItem();
        subMenuIngresarOrden = new javax.swing.JMenuItem();
        subMenuGenerarReportes = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/ManuPrincipal.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, -1, -1));

        manuPersonal.setText("Personal");

        subMenuManejoUsuarios.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuManejoUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/users.png"))); // NOI18N
        subMenuManejoUsuarios.setText("Manejo de Usuarios");
        subMenuManejoUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuManejoUsuariosActionPerformed(evt);
            }
        });
        manuPersonal.add(subMenuManejoUsuarios);

        menuBarra.add(manuPersonal);

        menuManejo.setText("Manejo");

        subMenuIngresarCliente.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuIngresarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/folder.png"))); // NOI18N
        subMenuIngresarCliente.setText("Ingresar Cliente");
        subMenuIngresarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuIngresarClienteActionPerformed(evt);
            }
        });
        menuManejo.add(subMenuIngresarCliente);

        subMenuIngresoProductos.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuIngresoProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/folder.png"))); // NOI18N
        subMenuIngresoProductos.setText("Agregar Producto");
        subMenuIngresoProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuIngresoProductosActionPerformed(evt);
            }
        });
        menuManejo.add(subMenuIngresoProductos);

        subMenuGenerarSolicitud.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuGenerarSolicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/folder.png"))); // NOI18N
        subMenuGenerarSolicitud.setText("Generar Solicitud");
        subMenuGenerarSolicitud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuGenerarSolicitudActionPerformed(evt);
            }
        });
        menuManejo.add(subMenuGenerarSolicitud);

        subMenuIngresarOrden.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuIngresarOrden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/folder.png"))); // NOI18N
        subMenuIngresarOrden.setText("Ingresar Orden");
        subMenuIngresarOrden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuIngresarOrdenActionPerformed(evt);
            }
        });
        menuManejo.add(subMenuIngresarOrden);

        subMenuGenerarReportes.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        subMenuGenerarReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ADICIONALES/folder.png"))); // NOI18N
        subMenuGenerarReportes.setText("Generar Reportes");
        subMenuGenerarReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subMenuGenerarReportesActionPerformed(evt);
            }
        });
        menuManejo.add(subMenuGenerarReportes);

        menuBarra.add(menuManejo);

        setJMenuBar(menuBarra);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void subMenuManejoUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuManejoUsuariosActionPerformed
        

        
        Usuarios mod = new Usuarios();
        UsuariosDAO modC = new UsuariosDAO();
        frmUsuarios frm = new frmUsuarios();
        ControladorUsuarios ctrl = new ControladorUsuarios(mod, modC, frm);
        ctrl.iniciar();
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
      
     
 
    }//GEN-LAST:event_subMenuManejoUsuariosActionPerformed

    private void subMenuIngresoProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuIngresoProductosActionPerformed
        
        frmProductos frm = new frmProductos();
        ControladorProductos ctrl = new ControladorProductos(frm);
        ctrl.iniciar();
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
    
   
    }//GEN-LAST:event_subMenuIngresoProductosActionPerformed

    private void subMenuGenerarSolicitudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuGenerarSolicitudActionPerformed
 
        FrmSolicitud frm = new FrmSolicitud();
        ControladorSolicitud ctrl = new ControladorSolicitud(frm);
        ctrl.iniciar();
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
       
          
    }//GEN-LAST:event_subMenuGenerarSolicitudActionPerformed

    private void subMenuIngresarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuIngresarClienteActionPerformed

        frmCliente frm = new frmCliente();
        ControladorClientes ctrl = new ControladorClientes(frm);
        ctrl.iniciar();
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
       
      
    }//GEN-LAST:event_subMenuIngresarClienteActionPerformed

    private void subMenuIngresarOrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuIngresarOrdenActionPerformed

        OrdenCompra mod = new OrdenCompra();
        OrdenCompraDAO dao = new OrdenCompraDAO();
        frmOrdenes frm = new frmOrdenes();
        Clientes modCli =new Clientes();
        Productos modPro =new Productos();
        ControladorOrdenCompra ctrl =new ControladorOrdenCompra(mod, dao, frm, modCli, modPro);
        ctrl.iniciar();
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
        
          
    }//GEN-LAST:event_subMenuIngresarOrdenActionPerformed

    private void subMenuGenerarReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subMenuGenerarReportesActionPerformed

        frmReportes frm = new frmReportes();
        ControladorReportes cntrl = new ControladorReportes(frm);
        try {
            cntrl.iniciar();
        } catch (SQLException ex) {
            Logger.getLogger(frmMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        frm.setVisible(true);
        this.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_subMenuGenerarReportesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu manuPersonal;
    private javax.swing.JMenuBar menuBarra;
    private javax.swing.JMenu menuManejo;
    private javax.swing.JMenuItem subMenuGenerarReportes;
    private javax.swing.JMenuItem subMenuGenerarSolicitud;
    private javax.swing.JMenuItem subMenuIngresarCliente;
    private javax.swing.JMenuItem subMenuIngresarOrden;
    private javax.swing.JMenuItem subMenuIngresoProductos;
    private javax.swing.JMenuItem subMenuManejoUsuarios;
    // End of variables declaration//GEN-END:variables
}