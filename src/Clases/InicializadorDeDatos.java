/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.teide.dam.aortiz.ioutil.OperationsIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 *
 * @author DAM1
 */
public class InicializadorDeDatos {
    
    OperationsIO cargador_medicamentos = new OperationsIO("Datos_medicamentos");
    OperationsIO cargador_PActivos = new OperationsIO("Datos_PActivos");
    
    public ArrayList<Medicamento> cargarMedicamentos(){

        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        try{
            String datos = (String)cargador_medicamentos.read();
            System.out.println("CARGA: " + datos);
            
            String[] medic = datos.split(";");
            for (int i = 0; i < medic.length; i++) {
                Medicamento nuevoMedicamento;
                String[] secciones = medic[i].split("/");
                System.out.println(secciones[0]);
                if(secciones[0].length()!=0){
                    String[] d = secciones[0].split("-");
                    String nombre=d[0];
                    System.out.println(d[0] + ";" + d[1] + ";" + nombre + d.length);
                    double precio=Double.parseDouble(d[1]);
                    nuevoMedicamento = new Medicamento(nombre, precio);
                    try{
                    String[] pactivos = secciones[1].split("-");
                    for (int j = 0; j < pactivos.length; j++) {
                        try{
                        if(secciones[1].length()!=0){
                            String[] datosPA=secciones[0].split("%");
                            String nombrePA=datosPA[0];
                            double cantidadPA=Double.parseDouble(datosPA[1]);
                            PActivo nuevoPActivo = new PActivo(nombrePA, cantidadPA);
                            nuevoMedicamento.añadirPActivo(nuevoPActivo);
                        }
                        }catch(IndexOutOfBoundsException e){
                            System.out.println("Sin principio activo.");
                        }
                    }
                    }catch(IndexOutOfBoundsException e){
                        
                    }
                    
                    //LOTES
                    try{
                    String[] lotes = secciones[2].split("-");
                    for (int j = 0; j < lotes.length; j++) {
                        String[] datosLotes = lotes[j].split("_");
                        System.out.println(lotes[j] + "****" + datosLotes[0]);
                        //CANTIDAD
                        int cantidadDeLote = Integer.parseInt(datosLotes[0]);
                        //FECHA FABRICACION
                        GregorianCalendar fechaFabricacion = new GregorianCalendar();
                        String[] datosFechaFabricacion = datosLotes[1].split(":");
                        int diaF = Integer.parseInt(datosFechaFabricacion[0]);
                        int mesF = Integer.parseInt(datosFechaFabricacion[1]);
                        int añoF = Integer.parseInt(datosFechaFabricacion[2]);
                        fechaFabricacion.set(añoF, mesF, diaF);
                        //FECHA CADUCIDAD
                        GregorianCalendar fechaCaducidad = new GregorianCalendar();
                        String[] datosFechaCaducidad = datosLotes[2].split(":");
                        int diaC = Integer.parseInt(datosFechaCaducidad[0]);
                        int mesC = Integer.parseInt(datosFechaCaducidad[1]);
                        int añoC = Integer.parseInt(datosFechaCaducidad[2]);
                        fechaFabricacion.set(añoC, mesC, diaC);
                        
                        //CREAR LOTE
                        
                        Unidad nuevoLote = new Unidad(fechaCaducidad, cantidadDeLote);
                        nuevoLote.setFabricado(fechaFabricacion);
                        
                        nuevoMedicamento.AgregarLote(nuevoLote);
                    }  
                    }catch(IndexOutOfBoundsException e){
                    
                    }
                    medicamentos.add(nuevoMedicamento);
                }
            }
        
        }catch(IOException e){
            System.out.println("No existen datos en la base de datos." + e);
        }
        catch(ClassNotFoundException e){
            System.out.println("Error2: " + e);
        }
        System.out.println(medicamentos.size());
        return medicamentos;
        
    }
    
    public boolean GuardarMedicamentos(ArrayList<Medicamento> medicamentos){
        String datos="";
        try{
            for (Medicamento medicamento : medicamentos) {
                datos+=medicamento.getNombre()+"-"+medicamento.getPrecio()+"/";
                for (PActivo pa : medicamento.getPrincipiosActivos()) {
                    datos+=pa.getNombre()+ "%" + pa.getCantidad() +"-";
                }
                datos+="/";
                for (Unidad uni : medicamento.getLotes()) {
                    datos+=uni.getCantidad()+"_"+ uni.getFabricadoString() +"_"+uni.getCaducidadString() + "-";
                }
                datos+="/;";
            }
            cargador_medicamentos.write(datos);
            return true;
        }catch(IOException e){
            System.out.println("Error al guardar los datos." + e);
            return false;
        }
    }
    
}
