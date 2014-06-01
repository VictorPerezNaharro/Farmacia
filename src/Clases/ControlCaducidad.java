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
public class ControlCaducidad {
    
    private GregorianCalendar FechaActual = new GregorianCalendar();
    
    public void ElminarCaducados (ArrayList<Medicamento> medicamentos){
        
        for (Medicamento medicamento : medicamentos) {
            System.out.println("Medicamento: " + medicamento);
            System.out.println("Numero de lotes: "+medicamento.getLotes().size());
            for (int i=0;i<medicamento.getLotes().size();i++) {
                System.out.println("Actualmente revisando lote numero: "+i);
                System.out.println(medicamento.getLotes().get(i).getCaducidad().compareTo(FechaActual));
                if(medicamento.getLotes().get(i).getCaducidad().compareTo(FechaActual)<0){
                    System.out.println("Lote retirado: " + medicamento + " " + medicamento.getLotes().get(i));
                    medicamento.getLotes().remove(medicamento.getLotes().get(i));
                    i--;
                }
            }
        }
        
    }
    
}
