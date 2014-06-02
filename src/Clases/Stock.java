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
        
        ArrayList<PActivo> GbusquedaPa=new ArrayList<>();
        for (int i = 0; i < principiosActivos.size(); i++) {
            if(principiosActivos.get(i).parece(busqueda)){
                GbusquedaPa.add(principiosActivos.get(i));
            }
        }
        return GbusquedaPa;
        
        
    }
    
    public ArrayList<Medicamento> buscarMedicamento(String busqueda){
        
        ArrayList<Medicamento> GbusquedaMe=new ArrayList<>();
        for (int i = 0; i < medicamentos.size(); i++) {
            if(medicamentos.get(i).parece(busqueda)){
                GbusquedaMe.add(medicamentos.get(i));
            }
        }
        return GbusquedaMe;
        
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
