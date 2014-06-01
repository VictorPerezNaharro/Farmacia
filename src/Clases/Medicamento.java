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
public class Medicamento {
    
    private String nombre;
    private int cantidad;
    private ArrayList<PActivo> PrincipiosActivos = new ArrayList<>();
    private double precio;

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public ArrayList<PActivo> getPrincipiosActivos() {
        return PrincipiosActivos;
    }

    public Medicamento(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    void añadirPActivo (PActivo p){
        PrincipiosActivos.add(p);
    }
    
    void añadirPActivo (String nombre, int cantidad){
        PActivo p =  new PActivo(nombre, cantidad);
        PrincipiosActivos.add(p);
    }

}
