/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.GregorianCalendar;

/**
 *
 * @author grester
 */
public class Unidad {
    
        private GregorianCalendar caducidad;
        private int cantidad;

    public Unidad(GregorianCalendar caducidad, int cantidad) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
    }

    public GregorianCalendar getCaducidad() {
        return caducidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

        @Override
    public String toString(){
        
        return "Caduca en: " + caducidad.get(GregorianCalendar.DAY_OF_MONTH) + "/" + (caducidad.get(GregorianCalendar.MONTH) + 1) + "/" + caducidad.get(GregorianCalendar.YEAR);
        
    }
    
}
