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
        Medicamento m = new Medicamento("aspirina", 5.5);
        m.AgregarLote(g, 5);
        GregorianCalendar g2 = new GregorianCalendar();
        g2.set(2013, 6, 1);
        m.AgregarLote(g2, 5);
        medicamentos.add(m);
        ControlCaducidad c = new ControlCaducidad();
        System.out.println("PRIMERA PASADA CONTROLADOR CADUCIDAD");
        c.ElminarCaducados(medicamentos);
        System.out.println("SEGUNDA PASADA CONTROLADOR CADUCIDAD");
        c.ElminarCaducados(medicamentos);
    }
    
}
