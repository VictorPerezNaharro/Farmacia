/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import com.teide.dam.aortiz.ioutil.OperationsIO;
import java.io.IOException;
import java.util.ArrayList;

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
            String datos = (String)cargador.read();
            System.out.println("CARGA: " + datos);
            
        
        
        }catch(IOException e){
            System.out.println("No existen datos en la base de datos.");
        }
        catch(ClassNotFoundException e){
            System.out.println("Error2: " + e);
        }
        return medicamentos;
        
    }
    
    public boolean GuardarMedicamentos(ArrayList<Medicamento> medicamentos){
        String datos="";
        try{
            for (Medicamento medicamento : medicamentos) {
                datos+=medicamento.getNombre()+"/";
                for (PActivo pa : medicamento.getPrincipiosActivos()) {
                    datos+=pa.getNombre()+"|";
                }
                datos+="/";
                for (Unidad uni : medicamento.getLotes()) {
                    datos+=uni.getCantidad()+"$"+ uni.getFabricadoString() +"$"+uni.getCaducidadString() + "|";
                }
                datos+="/;";
            }
            cargador.write(datos);
            return true;
        }catch(IOException e){
            System.out.println("Error al guardar los datos." + e);
            return false;
        }
    }
    
}
