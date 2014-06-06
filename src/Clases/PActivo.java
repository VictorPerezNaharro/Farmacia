/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author grester
 */
public class PActivo {
    private String nombre;
    private double cantidad;

    public PActivo(String nombre, double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public PActivo(String nombre) {
        this.nombre = nombre;
        this.cantidad = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public double getCantidad() {
        return cantidad;
    }
    
    public String toString(){
        return nombre;
    }
    
    public boolean parece(String busqueda){
        
        if(this.nombre.contains(busqueda)||busqueda.contains(nombre)) return true;
        return false;
        
    }
}
