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
    private GregorianCalendar caducidad = new GregorianCalendar();
    private int cantidad;
    private ArrayList<PActivo> PrincipiosActivos = new ArrayList<>();
    
    
    
    
}
