package Main;

import Clases.ControlCaducidad;
import Clases.Medicamento;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DAM1
 */
public class Ejecuta {

    public static void main(String[] args) {
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        
        GregorianCalendar g = new GregorianCalendar();
        
        Medicamento m = new Medicamento("aspirina", 5.5); //CREACION MEDICAMENTO
        g.set(2015, 6, 1); //SE PONE FECHA DE CADUCIDAD ALTA AL NUEVO LOTE
        m.AgregarLote(g, 5); //SE LE AGREGA UN LOTE DE 5 UNIDADES CON ESA FECHA DE CADUCIDAD
        
        GregorianCalendar g2 = new GregorianCalendar();
        g2.set(2013, 6, 1); //IGUAL PERO CADUCADO
        m.AgregarLote(g2, 5); //SE AÑADE AL MISMO MEDICAMENTO DE ANTES COMO OTRO LOTE
        
        medicamentos.add(m); //SE AÑADE ESE MEDICAMENTE CON LOS 2 LOTES AL ARRAY
        
        ControlCaducidad c = new ControlCaducidad(); // SE CREA EL CONTROLADOR DE CADUCIDAD
        System.out.println("PRIMERA PASADA CONTROLADOR CADUCIDAD");
        c.ElminarCaducados(medicamentos); // SE PASA EL CONTROL
        System.out.println("SEGUNDA PASADA CONTROLADOR CADUCIDAD");
        c.ElminarCaducados(medicamentos); // SE VUELVE A PASAR PARA COMPROBAR QUE SOLO QUEDA EL LOTE BUENO
    }
    
}
