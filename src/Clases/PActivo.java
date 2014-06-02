/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.ArrayList;

/**
 *
 * @author grester
 */
public class PActivo {
    private String nombre;
    private int cantidad;
    private ArrayList<Medicamento> ComponentePara = new ArrayList<Medicamento>();

    public PActivo(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<Medicamento> getComponentePara() {
        return ComponentePara;
    }
    
    public String toString(){
        return nombre;
    }
    
    public boolean parece(String busqueda){
        
        if(this.nombre.contains(busqueda)||busqueda.contains(nombre)) return true;
        return false;
        
    }
}
