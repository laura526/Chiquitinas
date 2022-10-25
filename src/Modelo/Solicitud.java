/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author nicol
 */
public class Solicitud {
   
    private int numSolicitud;
    private String fecha;
    private String material;
    private int cantidad;
    private String proveedor;
    private String telefono;
    private String comentario;

    // Constructores
    
    public Solicitud() {
    }

    public Solicitud(int numSolicitud, String fecha, String material, int cantidad, String proveedor, String telefono, String comentario) {
        this.numSolicitud = numSolicitud;
        this.fecha = fecha;
        this.material = material;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
        this.telefono = telefono;
        this.comentario = comentario;
    }

    // Getters y Setters

    public int getNumSolicitud() {
        return numSolicitud;
    }

    public void setNumSolicitud(int numSolicitud) {
        this.numSolicitud = numSolicitud;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
      
}
