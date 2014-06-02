/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author grester
 */
public class Medicamento {
    
    private String nombre;
    private ArrayList<PActivo> PrincipiosActivos = new ArrayList<>();
    private double precio;
    private ArrayList<Unidad> lotes = new ArrayList<Unidad>();

    public String getNombre() {
        return nombre;
    }

    public ArrayList<PActivo> getPrincipiosActivos() {
        return PrincipiosActivos;
    }

    public Medicamento(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    public void AgregarLote(GregorianCalendar caducidad, int cantidad){
        
        Unidad u = new Unidad(caducidad, cantidad);
        lotes.add(u);
        
    }
    
    void añadirPActivo (PActivo p){
        PrincipiosActivos.add(p);
    }
    
    void añadirPActivo (String nombre, int cantidad){
        PActivo p =  new PActivo(nombre, cantidad);
        PrincipiosActivos.add(p);
    }

    public ArrayList<Unidad> getLotes() {
        return lotes;
    }

    public double getPrecio() {
        return precio;
    }
    
    public String toString(){
        return nombre;
    }
    
    public boolean parece(String busqueda){
        
        if(this.nombre.contains(busqueda)||busqueda.contains(nombre)) return true;
        return false;
        
    }
    
    public int equals(Medicamento m){
        if(m.nombre.equals(nombre))return 1;
        return 0;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
