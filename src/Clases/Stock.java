/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author grester
 */
public class Stock {
    
    Scanner scan = new Scanner(System.in);
    ArrayList<Medicamento> medicamentos;
    ArrayList<PActivo> principiosActivos = new ArrayList<>();
    ControlCaducidad control = new ControlCaducidad();
    InicializadorDeDatos cargador = new InicializadorDeDatos();
    
    public Stock() {
        medicamentos = cargador.cargarMedicamentos();
        control.ElminarCaducados(medicamentos);
    }
    
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
            }while((!numero)&&(!opc.equals("nuevo"))||numero&&(Integer.parseInt(opc)<1&&Integer.parseInt(opc)>posibles.size()));
            error=false;
            if(numero){
                //EN BASE DE DATOS
                nuevoMedicamento = posibles.get(Integer.parseInt(opc)-1);
                System.out.println("Agregar lote a " + nuevoMedicamento);
                System.out.println("Numero de lotes");
                int num = scan.nextInt();
                scan.nextLine();
                GregorianCalendar nuevo = new GregorianCalendar();
                do{
                    System.out.println("Escribe la fecha de caducidad. Formato dd/mm/aaaa. Si es incorrecta se volvera a pedir.");
                    String[] fecha = scan.nextLine().split("/");
                    nuevo.set(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
                }while(!control.Comprobarcaducidad(nuevo));
                System.out.println("El precio actual es de: " + nuevoMedicamento.getPrecio());
                System.out.println("Si desea cambiar el precio escriba el nuevo, si no escriba un 0");
                double precio = scan.nextDouble();
                medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).AgregarLote(nuevo, num);
                if(precio>0){
                    medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).setPrecio(precio);
                    System.out.println("Nuevo precio: " + precio);
                }
                System.out.println("Se han añadido " + num + " lotes de " + nuevoMedicamento);
                return;
            }
            numero=true;
        }else{
            System.out.println("No se han encontrado posibles parecidos en el stock de medicamentos");
        }
        
        //NUEVO MEDICAMENTO
        
        System.out.println("Precio de " + nombre + ":");
        double precio = scan.nextDouble();
        nuevoMedicamento = new Medicamento(nombre, precio);
        medicamentos.add(nuevoMedicamento);
        System.out.println("Medicamento creado. No tienes lotes agregados.");
        return;
    }
    
    public boolean guardarDatos(){
        
        return cargador.GuardarMedicamentos(medicamentos);
        
    }
    
    public PActivo añadirPActivo(){
        
        System.out.println("Nombre del principio activo:");
        String nombre = scan.nextLine();
        
        PActivo nuevoPActivo;
        ArrayList<PActivo> posibles = buscarPActivo(nombre);
        if(posibles.size()>0){
            System.out.println("¿Querias decir...?");
            int i = 1;
            for (PActivo pactivo : posibles) {
                System.out.println(i + "- " + pactivo.getNombre());
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
            }while((!numero)&&(!opc.equals("nuevo"))||numero&&(Integer.parseInt(opc)<1&&Integer.parseInt(opc)>posibles.size()));
            error=false;
            if(numero){
                //EN BASE DE DATOS
                nuevoPActivo = posibles.get(Integer.parseInt(opc)-1);
                System.out.println("Cantidad de " + nuevoPActivo.getNombre() + " que lleva el nuevo medicamento");
                double cantidad = scan.nextDouble();
                System.out.println("Se le a añadido el principio activo " + nuevoPActivo.getNombre());
                nuevoPActivo = new PActivo(nombre, cantidad);
                return nuevoPActivo;
            }
            numero=true;
        }else{
            System.out.println("No se han encontrado posibles parecidos en el stock de medicamentos");
        }
        
        //NUEVO MEDICAMENTO
        
        System.out.println("Cantidad de " + nombre + " en el nuevo medicamento");
        double cantidad = scan.nextDouble();
        nuevoPActivo = new PActivo(nombre, cantidad);
        principiosActivos.add(nuevoPActivo);
        System.out.println("Medicamento creado. No tienes lotes agregados.");
        return nuevoPActivo;
        
    }
    
}
