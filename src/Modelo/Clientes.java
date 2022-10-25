/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author HP ZBOOK 17 G2 i7
 */
public class Clientes {
    
    private int id;
    private String nombre;
    private String direccion;
    private String Telefono;

    public Clientes() {
    }

    public Clientes(int id, String nombre, String direccion, String Telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.Telefono = Telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
     
    
}
