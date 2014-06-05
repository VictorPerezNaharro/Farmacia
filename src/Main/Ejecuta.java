package Main;

import Clases.Medicamento;
import Clases.PActivo;
import Clases.Stock;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

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

        //INICIACION DE LA APLICACION
        System.out.println("Iniciando aplicacion.");
        Stock stock=new Stock();
        
        //MENU.......................................
        GregorianCalendar g = new GregorianCalendar();
        Scanner s = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("----------------------------------");
            System.out.println("/////////MENU FARMACIA///////////");
            System.out.println("1. Dar de alta un medicamento");
            System.out.println("2. Busqueda de medicamentos y principios activos");
            System.out.println("3. Venta de medicamento");
            System.out.println("4. Borrado de medicamentos");
            System.out.println("5. Guardar");
            System.out.println("6. Salir");
            opcion=s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:{
                    stock.altaMedicamento();
                    break;
                }
                case 2:{
                    System.out.println("1. Buscar por medicamento");
                    System.out.println("2. Buscar por principio activo");
                    int opBus=s.nextInt();
                    s.nextLine();
                    if(opBus==1){
                        System.out.println("Nombre de medicamento");
                        String busMedicamneto=s.nextLine();
                        ArrayList<Medicamento> resultados=stock.buscarMedicamento(busMedicamneto);
                        int i =1;
                        for (Medicamento medicamento : resultados) {
                            String disponible="SI";
                            if(medicamento.getLotes().isEmpty()) disponible = "NO";
                            System.out.println(i + "- " + medicamento + ".Precio: " + medicamento.getPrecio() + ". " + disponible + " disponible");
                            i++;
                        }
                    }
                    else{
                        System.out.println("Nombre del principio activo");
                        String busPa=s.nextLine();
                        ArrayList<PActivo> resultados=stock.buscarPActivo(busPa);
                        int i =1;
                        for (PActivo pActivo : resultados) {
                            System.out.println(i + "- " + pActivo);
                            i++;
                        }
                    }
                    break;
                }
                case 3:{
                    break;
                }
                case 4:{
                    break;
                }
                case 5:{
                    if(!stock.guardarDatos()) System.out.println("Fallo al guardar");
                    else System.out.println("Guardado correctamente");;
                    break;
                }
                case 6:{
                    System.out.println("Fin de la aplicacion.");
                    break;
                }
                default:
                    System.out.println("Opcion no disponible");
            }
            
        }while(opcion!=6);
    }
    
}
