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
        private GregorianCalendar fabricado = new GregorianCalendar();
        private int cantidad;

    public Unidad(GregorianCalendar caducidad, int cantidad) {
        this.caducidad = caducidad;
        this.cantidad = cantidad;
    }

    Unidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCaducidadString() {
        return caducidad.get(GregorianCalendar.DAY_OF_MONTH) + ":" + caducidad.get(GregorianCalendar.MONTH)+":"+caducidad.get(GregorianCalendar.YEAR);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

        @Override
    public String toString(){
        
        return cantidad + " unidades. Caducan en: " + (caducidad.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + (caducidad.get(GregorianCalendar.MONTH) + 1) + "/" + caducidad.get(GregorianCalendar.YEAR);
        
    }
    
    public int compareTo(Unidad u){
        return u.getCaducidad().compareTo(this.caducidad);
    }

    public String getFabricadoString() {
        return fabricado.get(GregorianCalendar.DAY_OF_MONTH) + ":" + (fabricado.get(GregorianCalendar.MONTH)+1)+":"+fabricado.get(GregorianCalendar.YEAR);
    }

    public GregorianCalendar getCaducidad() {
        return caducidad;
    }

    public GregorianCalendar getFabricado() {
        return fabricado;
    }

    public void setFabricado(GregorianCalendar fabricado) {
        this.fabricado = fabricado;
    }
    
}
