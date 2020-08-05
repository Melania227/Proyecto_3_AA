/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USUARIO
 */
public class Fabrica implements Serializable{
    private ArrayList <Robot> poblacion;
    private int cantidadDeIndividuos;
    private int indiceMutacion;
    
    public Fabrica(int cantidadDeIndividuos, Terreno terreno) {
        Random rand = new Random();
        this.indiceMutacion = rand.nextInt(56);
        this.poblacion = new ArrayList ();
        this.cantidadDeIndividuos = cantidadDeIndividuos;
        for (int i = 0; i < cantidadDeIndividuos; i++) {
            this.poblacion.add(new Robot(terreno));
        }
    }
    
    public Fabrica(int cantidadDeIndividuos, int indiceMutacion) {
        this.indiceMutacion = indiceMutacion;
        this.poblacion = new ArrayList ();
        this.cantidadDeIndividuos = cantidadDeIndividuos;
    }

    public Fabrica(ArrayList<Robot> poblacion) {
        this.poblacion = poblacion;
    }

    public ArrayList<Robot> getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(ArrayList<Robot> poblacion) {
        this.poblacion = poblacion;
    }

    public int getCantidadDeIndividuos() {
        return cantidadDeIndividuos;
    }

    public void setCantidadDeIndividuos(int cantidadDeIndividuos) {
        this.cantidadDeIndividuos = cantidadDeIndividuos;
    }
    
    public void mutacion(){
        cruceGenesPrint();
        Random rand = new Random();
        int i = rand.nextInt(this.cantidadDeIndividuos);
        int j = rand.nextInt(56);
        //System.out.println("CAMBIA: " + this.indiceMutacion);
        for (int m = 0; m < this.indiceMutacion; m++) {
            if (this.poblacion.get(i).getGenes().getChain().get(j) == 1){
                this.poblacion.get(i).getGenes().getChain().set(j, 0);
            }
            else{
                this.poblacion.get(i).getGenes().getChain().set(j, 1);
            }
            //System.out.println("INDICE: "+ i + ", " + j);
            i = rand.nextInt(this.cantidadDeIndividuos);
            j = rand.nextInt(56);
        }
        
        cruceGenesPrint();
    }

    public void cruceGenesPrint() {
        String generacionGenes = "GENERACION: ";
        for (Robot robot : poblacion) {
            generacionGenes += robot.getGenes().getChain();
            generacionGenes += "\n";
        }
        generacionGenes += "----------------------------";
        System.out.println(generacionGenes);
    }
    
    public Fabrica getNuevaGeneracion (){
        Fabrica nuevaGeneracion = new Fabrica(this.cantidadDeIndividuos, this.indiceMutacion);
        ArrayList<Double> calificacionesNormalizadas = getAdaptabilidad();
       
        ArrayList<Robot> seleccionRobots = new ArrayList ();
        Random rand = new Random();
        for (int i = 0; i < this.poblacion.size(); i++) {
            double indice = rand.nextDouble();
            System.out.println("INDICE " + indice);
            seleccionRobots.add(getRobotSeleccionado(calificacionesNormalizadas, indice));
        }

        for (int i = 0; i < seleccionRobots.size(); i++) {
            nuevaGeneracion.poblacion.add( (Robot) Clonador.deepCopy(seleccionRobots.get(i)));
        }
        
        return nuevaGeneracion;
    }
    
    public ArrayList<Double> getAdaptabilidad(){
        ArrayList<Double> calificaciones = new ArrayList();
        double total = 0.0;
        for (int i = 0; i < this.poblacion.size(); i++) {
            if (this.poblacion.get(i).isFinalizado()){
                calificaciones.add(100.0);
            }
            else {
                calificaciones.add(10.0);
            }
            total += calificaciones.get(i);
        }
        System.out.println("total "+total);
        for (int i = 0; i < calificaciones.size(); i++) {
            calificaciones.set(i, (double)((calificaciones.get(i)*100)/total));
            System.out.println("CAL: " +  calificaciones.get(i));
        }
        return calificaciones;
    }
    
    public Robot getRobotSeleccionado(ArrayList<Double> calificacionesNormalizadas, double indice){
        double extremoInicio = 0;
        double extremoFinal = 0;
        for (int i = 0; i < calificacionesNormalizadas.size(); i++) {
            extremoFinal+=calificacionesNormalizadas.get(i);
            if (indice>extremoInicio && indice<=extremoFinal){
                return this.poblacion.get(i);
            }
            extremoInicio+=calificacionesNormalizadas.get(i);
        }
        return null;
    }
    
    public void cruceEntreIndividuosGen (){
        for (int i = 0; i < this.poblacion.size(); i=i+2) {
            this.poblacion.get(i).cruceEntreRobots(this.poblacion.get(i+1));
        }
    }
}