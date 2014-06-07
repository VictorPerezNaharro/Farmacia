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
        //NOTAS DE BUGS
        
        /*
        -*FIXED*No se restan bien las unidades al ser compradas.
        -*FIXED*No calcula bien el precio en la compra.
        -*IDENTIFIED*posible bug en la fecha de caducidad de los lotes, parece no imprimirse/establecerse bien.
        -*IDENTIFIED*No realiza bien la busqueda de lotes por fecha de caducidad
        - LOS DOS DE ARRIBA -> debido a que no establece bien la fecha de los lotes al cargarlos
        */
        
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
                            i++;
                        }
                    }
                    System.out.println("Pulsa intro para continuar");
                    s.nextLine();
                    break;
                }
                case 3:{
                    System.out.println("Introduzca el nombre del medicamento que desea vender");
                    String nombre = s.nextLine();
                    Medicamento medicamentoAVender;
                    
                    ArrayList<Medicamento> encontrados = stock.buscarMedicamento(nombre);
                    int i =0;
                    if(encontrados.isEmpty()){
                        System.out.println("No se han enontrado coincidencias.");
                        break;
                    }else{
                        System.out.println("Se han encontrado estas coincidencias:");
                        for (Medicamento medicamento : encontrados) {
                            i++;
                            System.out.println(i + "- " + medicamento);
                        }
                    }
                    int opc;
                    do{
                        System.out.println("Elije el numero que corresponde al medicamento que quieres vender, 0 para salir");
                        opc = s.nextInt();
                        if(opc==0) break;
                        int uni;
                        if(opc<=encontrados.size() && opc>0){

                            medicamentoAVender = encontrados.get(i-1);
                            System.out.println("Numero de unidades que quieres comprar");
                            uni = s.nextInt();
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
                            if(stock.comprobarDisponibilidad(medicamentoAVender, uni)){
                                precio = stock.calculaPrecio(medicamentoAVender, uni);
                                System.out.println("El precio es " + precio); 
                            }
                        }else{
                            System.out.println("Opcion incorrecta.");
                        }
                    }while(opc<encontrados.size() && opc>0);
                    break;
                }
                case 4:{
                    System.out.println("Nombre del medicamento que se desea borrar");
                    String medBorra=s.nextLine();                     
                    ArrayList<Medicamento> posibles = stock.buscarMedicamento(medBorra);
                    if(posibles.size()>0){
                        System.out.println("¿Querias decir...?");
                        int i = 1;
                        for (Medicamento medicamento : posibles) {
                            System.out.println(i + "- " + medicamento);
                            i++;
                        }
                    }
                    int opc=0;
                    do{
                        System.out.println("Elige cual quieres borrar");
                        int opcBorra=s.nextInt();
                        s.nextLine();
                    }while(opc<1 && opc>posibles.size());
                    stock.borrarMedicamento(posibles.get(opc));
                    System.out.println("Medicamento borrado");
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
