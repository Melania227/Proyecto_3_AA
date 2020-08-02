/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USUARIO
 */
public class Fabrica {
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
        cruceGenes();
        Random rand = new Random();
        int i = rand.nextInt(this.cantidadDeIndividuos);
        int j = rand.nextInt(56);
        System.out.println("CAMBIA: " + this.indiceMutacion);
        for (int m = 0; m < this.indiceMutacion; m++) {
            if (this.poblacion.get(i).getGenes().getChain().get(j) == 1){
                this.poblacion.get(i).getGenes().getChain().set(j, 0);
            }
            else{
                this.poblacion.get(i).getGenes().getChain().set(j, 1);
            }
            System.out.println("INDICE: "+ i + ", " + j);
            i = rand.nextInt(this.cantidadDeIndividuos);
            j = rand.nextInt(56);
        }
        
        cruceGenes();
    }

    public void cruceGenes() {
        String generacionGenes = "GENERACION: ";
        for (Robot robot : poblacion) {
            generacionGenes += robot.getGenes().getChain();
            generacionGenes += "\n";
        }
        generacionGenes += "----------------------------";
        System.out.println(generacionGenes);
    }
    
     
}
