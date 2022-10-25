package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuariosDAO extends Conexion {

    /*
        ------------ REGISTRO DE USUARIOS---------------
     */
    public boolean registrar(Usuarios usr) {

        PreparedStatement ps = null;
        Connection con = conexion();

        String sql = "INSERT INTO usuarios (usuario, pass, tipoUsuario) VALUES(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPass());
            ps.setString(3, usr.getTipoUsuario());
            ps.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            }
        }
    }

    /*
        ------------ MODIFICADO DE USUARIOS---------------
     */
    public boolean modificar(Usuarios usr) {

        PreparedStatement ps = null;
        Connection con = conexion();

        String sql = "UPDATE usuarios SET usuario=?, pass=?, tipoUsuario=? WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPass());
            ps.setString(3, usr.getTipoUsuario());
            ps.setInt(4, usr.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            }
        }
    }

    /*
        ------------ ELIMINADO DE USUARIOS---------------
     */
    public boolean eliminar(Usuarios usr) {

        PreparedStatement ps = null;
        Connection con = conexion();

        String sql = "DELETE FROM usuarios WHERE id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, usr.getId());
            ps.execute();
            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
            }
        }
    }

    /*
        ------------ BUSQUEDA DE USUARIOS---------------
     */
    public boolean buscar(Usuarios usr) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = conexion();

        String sql = "SELECT * FROM usuarios WHERE usuario=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            rs = ps.executeQuery();

            if (rs.next()) {
                usr.setId(Integer.parseInt(rs.getString("id")));
                usr.setUsuario(rs.getString("usuario"));
                usr.setPass(rs.getString("pass"));
                usr.setTipoUsuario(rs.getString("tipoUsuario"));
                return true;
            }

            return false;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de base de datos: \n" + e);
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
