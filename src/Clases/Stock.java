/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import com.sun.xml.internal.stream.Entity;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author grester
 */
public class Stock {
    
    ArrayList<Medicamento> medicamentos = new ArrayList<>();
    ArrayList<PActivo> principiosActivos = new ArrayList<>();
    
    public ArrayList<PActivo> buscarPActivo(String busqueda){
        
        
        
    }
    
    public ArrayList<Medicamento> buscarMedicamento(String Busqueda){
        
        
        
    }
    
    public void altaMedicamento(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Formulario de alta de medicamento.");
        System.out.println("Escribe el nombre del medicamento:");
        String nombre = scan.nextLine();
        
        ArrayList<Medicamento> posibles = buscarMedicamento(nombre);
        if(posibles.size()>0){
            System.out.println("¿Querias decir...?");
            for (Medicamento medicamento : posibles) {
                System.out.println(medicamento);
            }
        }
    }
    
}
