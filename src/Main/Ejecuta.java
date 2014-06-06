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
            System.out.println("1. Dar de alta o modificar un medicamento");
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
                        if(!resultados.isEmpty()){
                            System.out.println("RESULTADOS:");
                            for (Medicamento medicamento : resultados) {
                                String disponible="SI";
                                if(medicamento.getLotes().isEmpty()) disponible = "NO";
                                System.out.println(i + "- " + medicamento + ".Precio: " + medicamento.getPrecio() + ". " + disponible + " disponible");
                                System.out.println("Contine estos principios activos:");
                                for (PActivo pactivo : medicamento.getPrincipiosActivos()) {
                                        System.out.println("* " + pactivo);
                                }
                                i++;
                            }
                        }else{
                            System.out.println("No hay resultados.");
                        }
                    }
                    else{
                        System.out.println("Nombre del principio activo");
                        String busPa=s.nextLine();
                        ArrayList<PActivo> resultados=stock.buscarPActivo(busPa);
                        int i =1;
                        System.out.println("RESULTADOS:");
                        for (PActivo pActivo : resultados) {
                            System.out.println(i + "- " + pActivo);
                            System.out.println("Componente para los medicamentos: ");
                            for (Medicamento medicamento : stock.getMedicamentos()) {
                                if(medicamento.getPrincipiosActivos().contains(pActivo)){
                                    System.out.println("* " + medicamento);
                                }
                            }
                            i++;
                        }
                    }
                    System.out.println("Pulsa intro para continuar");
                    s.nextLine();
                    break;
                }
                case 3:{
                    System.out.println("Introduce el nombre del medicamento que desea comprar");
                    String nombre = s.nextLine();
                    Medicamento medicamentoAVender;
                    
                    ArrayList<Medicamento> encontrados = stock.buscarMedicamento(nombre);
                    int i =0;
                    System.out.println("Se han encontrado estas coincidencias:");
                    for (Medicamento medicamento : encontrados) {
                        i++;
                        System.out.println(i + "- " + medicamento);
                    }
                    int opc;
                    do{
                        System.out.println("Elije el numero que corresponde al medicamento que quieres vender");
                        opc = s.nextInt();
                        if(opc<encontrados.size() && opc>0){

                            medicamentoAVender = encontrados.get(i-1);
                            System.out.println("Numero de unidades que quieres comprar");
                            int uni = s.nextInt();
                            s.nextLine();
                            if(medicamentoAVender.isReceta()) {
                                System.out.println("¿Tiene receta? 1- SI. otro numero- NO.");
                                int tiene = s.nextInt();
                                if(tiene!=1){
                                    System.out.println("Es necesaria una receta.");
                                    break;
                                }
                            }
                            double precio = 0;
                            if(stock.comprobarDisponibilidad(medicamentoAVender, uni)) precio = stock.calculaPrecio(medicamentoAVender, uni);
                            System.out.println("El precio es " + precio);    
                            
                        }else{
                            System.out.println("Opcion incorrecta.");
                        }
                    }while(opc<encontrados.size() && opc>0);
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
