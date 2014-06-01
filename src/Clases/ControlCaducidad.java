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
    
    void ElminarCaducados (ArrayList<Medicamento> medicamentos){
        
        for (Medicamento medicamento : medicamentos) {
            
            for (Unidad u : medicamento.getLotes()) {
                
                if(u.getCaducidad().compareTo(FechaActual)<0){
                    
                    
                    
                }
                
            }
            
        }
        
    }
    
}
