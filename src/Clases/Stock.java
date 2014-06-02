/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import com.sun.xml.internal.stream.Entity;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
        
        Medicamento nuevoMedicamento;
        ArrayList<Medicamento> posibles = buscarMedicamento(nombre);
        if(posibles.size()>0){
            System.out.println("¿Querias decir...?");
            int i = 1;
            for (Medicamento medicamento : posibles) {
                System.out.println(i + "- " + medicamento);
                i++;
            }
            String opc="";
            boolean error=false;
            boolean numero=true;
            do{
            if(error) System.out.println("Opcion no valida.");
            System.out.println("Elije uno de los resultados escribiendo su numero o escribe 'nuevo' si no coincide con los anteriores");
            opc=scan.nextLine();
            error=true;
            try{
                Integer.parseInt(opc);
            }catch(NumberFormatException e){
                numero=false;
            }
            }while(!opc.equals("nuevo")||numero&&(Integer.parseInt(opc)>0&&Integer.parseInt(opc)<posibles.size()));
            error=false;
            
            if(numero){
                //EN BASE DE DATOS
                nuevoMedicamento = posibles.get(i-1);
                System.out.println("Agregar lote a " + nuevoMedicamento);
                System.out.println("Numero de lotes");
                int num = scan.nextInt();
                System.out.println("Escribe la fecha de caducidad. Formato dd/mm/aaaa");
                String[] fecha = scan.nextLine().split("/");
                GregorianCalendar nuevo = new GregorianCalendar();
                nuevo.set(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
                medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).AgregarLote(nuevo, num);
                System.out.println("Se han añadido " + num + " lotes de " + nuevoMedicamento);
                return;
            }
            numero=true;
        }else{
            System.out.println("No se han encontrado posibles parecidos en el stock de medicamentos");
        }
        
        
        
    }
    
}
