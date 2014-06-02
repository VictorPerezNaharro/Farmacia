package Main;

import Clases.ControlCaducidad;
import Clases.Medicamento;
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
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        GregorianCalendar g = new GregorianCalendar();
        Scanner s = new Scanner(System.in);
        int opcion;
        do{
            System.out.println("1. Da de alta un medicamento");
            System.out.println("2. Busqueda de medicamentos");
            System.out.println("3. Venta de medicamento");
            System.out.println("4. Borrado de medicamentos");
            System.out.println("5. Salir");
            opcion=s.nextInt();
            s.nextLine();
            switch(opcion){
                case 1:{
                    
                }
                case 2:{
                    
                }
                case 3:{
                    
                }
                case 4:{
                    
                }
            }
            
        }while(opcion!=5);
    }
    
}
