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
        principiosActivos = cargador.cargarPActivos();
        System.out.println(medicamentos.size());
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
                System.out.println("Elije uno de los resultados escribiendo su numero para modificarlo o escribe 'nuevo' si no coincide con los anteriores");
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
                //menu para saber si quiere añadir lotes nuevos
                nuevoMedicamento = posibles.get(Integer.parseInt(opc)-1);
                System.out.println("¿Añadir nuevo lote de unidades al medicamento?");
                System.out.println("1- Si");
                System.out.println("Otro numero- No");
                int sino = scan.nextInt();
                scan.nextLine();
                int num=0;
                while(sino==1){
                    System.out.println("Agregar lote a " + nuevoMedicamento);
                    System.out.println("Numero de lotes");
                    num = scan.nextInt();
                    scan.nextLine();
                    GregorianCalendar nuevo = new GregorianCalendar();
                    do{
                        System.out.println("Escribe la fecha de caducidad. Formato dd/mm/aaaa. Si es incorrecta se volvera a pedir.");
                        String[] fecha = scan.nextLine().split("/");
                        nuevo.set(Integer.parseInt(fecha[2]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[0]));
                    }while(!control.Comprobarcaducidad(nuevo));
                    medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).AgregarLote(nuevo, num);
                    System.out.println("Lote de unidades añadido.");
                    System.out.println("¿Añadir otro lote de unidades al medicamento?");
                    System.out.println("1- Si");
                    System.out.println("Otro numero- No");
                    sino = scan.nextInt();
                }
                System.out.println("El precio actual es de: " + nuevoMedicamento.getPrecio());
                System.out.println("Si desea cambiar el precio escriba el nuevo, si no escriba un 0");
                double precio = scan.nextDouble();
                scan.nextLine();
                if(precio>0){
                    medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).setPrecio(precio);
                    System.out.println("Nuevo precio: " + precio);
                }
                //AGREGAR PA A CONOCIDO

                System.out.println("Para agregar Un principio activo pulsa 1, para continuar pulsa 2");
                int opcionPA=scan.nextInt();
                scan.nextLine();
                while(opcionPA!=2){
                medicamentos.get(medicamentos.indexOf(nuevoMedicamento)).añadirPActivo(añadirPActivo());
                }
                //----------------------
                return;
            }
            numero=true;
        }else{
            System.out.println("No se han encontrado posibles parecidos en el stock de medicamentos");
        }
        
        //NUEVO MEDICAMENTO
        
        System.out.println("Precio de " + nombre + ":");
        double precio = scan.nextDouble();
        System.out.println("¿Es necesaria una receta?");
        System.out.println("1- Si, es necesaria");
        System.out.println("otro numero- No es necesaria");
        boolean receta=false;
        if(scan.nextInt()==1) receta = true;
        nuevoMedicamento = new Medicamento(nombre, precio, receta);
        //menu de agrgar lotes--------------------
        System.out.println("Pulsa 1 para agregar lotes. Pulsa 2 para continuar");
        int opcionL=scan.nextInt();
        while(opcionL!=2){
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
            nuevoMedicamento.AgregarLote(nuevo, num);
             System.out.println("Pulsa 1 para agregar lotes. Pulsa 2 para continuar");
            opcionL=scan.nextInt();
        }
        //----------------------------------------
        int opcionP=1;
        do{
            if(opcionP!=1)System.out.println("Esa opcion no esta disponible");
        System.out.println("Para añadir principio activo pulsa 1, para finalizar pulsa 2.");
        opcionP=scan.nextInt();
        scan.nextLine();
        if(opcionP==1){
            nuevoMedicamento.añadirPActivo(añadirPActivo());
        }
        }while(opcionP!=2);
         medicamentos.add(nuevoMedicamento);
        System.out.println("Medicamento creado.");
        return;
    }
    
    public boolean guardarDatos(){
        
        return cargador.GuardarMedicamentos(medicamentos)&&cargador.GuardarPActivos(principiosActivos);
        
    }
    
    public PActivo añadirPActivo(){
        
        System.out.println("Nombre del principio activo:");
        System.out.println("(No se pueden usar los caracteres: - / % _ ;)");
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
                scan.nextLine();
                System.out.println("Se le a añadido el principio activo " + nuevoPActivo.getNombre());
                nuevoPActivo = new PActivo(nombre, cantidad);
                return nuevoPActivo;
            }
            numero=true;
        }else{
            System.out.println("No se han encontrado posibles parecidos en el stock de medicamentos");
        }
        
        //NUEVO PA
        
        System.out.println("Cantidad de " + nombre + " en el nuevo medicamento");
        double cantidad = scan.nextDouble();
        nuevoPActivo = new PActivo(nombre, cantidad);
        principiosActivos.add(nuevoPActivo);
        return nuevoPActivo;
        
    }
    
    public boolean comprobarDisponibilidad(Medicamento medicamento, int unidades){
        
        //medicamentos.get(medicamentos.indexOf(medicamento));
        int cont=0;
        while(unidades>0){
        if(!medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().isEmpty()){

            for (int i = 0; i < medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().size(); i++) {
            
                cont+=medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(i).getCantidad();
            
            }
        if(cont>unidades){ 
            Unidad target = caducaAntes(medicamento);
            if(unidades>=target.getCantidad()){
                unidades-=target.getCantidad();
                medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().remove(target);
            }else{
                unidades=0;
                medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().indexOf(target)).setCantidad(target.getCantidad()-unidades);
            }
            
            }else{
                System.out.println("No hay suficientes unidades. Numero de unidades: " + cont);
                return false;
            }
        }else{
            System.out.println("No hay suficientes unidades. Numero de unidades: 0");
            return false;
        }
        }
        if(unidades>0){
            System.out.println("Unidades insuficientes");
            return false;
        }else{
            return true;
        } 
    }
    
    public double calculaPrecio(Medicamento medicamento, int unidades){
        
        return medicamentos.get(medicamentos.indexOf(medicamento)).getPrecio() * unidades;
        
    }
    
    Unidad caducaAntes(Medicamento medicamento){
        
        Unidad resultado=medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(0);
        GregorianCalendar comparador = medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(0).getCaducidad();
        for (int i = 0; i < medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().size(); i++) {
            
            if(comparador.compareTo(medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(i).getCaducidad())>1){
                comparador=medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(i).getCaducidad();
                resultado=medicamentos.get(medicamentos.indexOf(medicamento)).getLotes().get(i);
            }
            
        }
        
        return resultado;
        
    }

    public ArrayList<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public ArrayList<PActivo> getPrincipiosActivos() {
        return principiosActivos;
    }
    
}
