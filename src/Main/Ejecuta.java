package Main;

import Clases.ControlCaducidad;
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
            System.out.println("1. Dar de alta un medicamento");
            System.out.println("2. Busqueda de medicamentos y principios activos");
            System.out.println("3. Venta de medicamento");
            System.out.println("4. Borrado de medicamentos");
            System.out.println("5. Salir");
            opcion=s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:{
                    
                }
                case 2:{
                    System.out.println("1. Buscar por medicamento");
                    System.out.println("2. Buscar por principio activo");
                    int opBus=s.nextInt();
                    if(opBus==1){
                        System.out.println("Nombre de medicamneto");
                        String busMedicamneto=s.nextLine();
                        ArrayList<Medicamento> resultados=stock.buscarMedicamento(busMedicamneto);
                        for (Medicamento medicamento : resultados) {
                            System.out.println(medicamento);
                        }
                    }
                    else{
                        System.out.println("Nombre del principio activo");
                        String busPa=s.nextLine();
                        ArrayList<PActivo> resultados=stock.buscarPActivo(busPa);
                        for (PActivo pActivo : resultados) {
                            System.out.println(pActivo); 
                        }
                    }
                    
                }
                case 3:{
                    
                }
                case 4:{
                    
                }
                default:
                    System.out.println("Opcion no disponible");
            }
            
        }while(opcion!=5);
    }
    
}
