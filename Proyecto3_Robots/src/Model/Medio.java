/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * CLASE MEDIO
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Medio implements Serializable{
    private Fabrica fabricaRobots;
    private Terreno terreno;
    private ArrayList <Fabrica> historialGeneraciones;
    private int numGeneracionActual;

    public Medio(int cantRobots, int sizeTerreno) {
        this.terreno = new Terreno (sizeTerreno);
        this.fabricaRobots = new Fabrica(cantRobots, this.terreno);
        this.historialGeneraciones = new ArrayList ();
        this.numGeneracionActual = 0;
    }
    
    public Medio(Fabrica fabricaRobots, Terreno terreno) {
        this.fabricaRobots = fabricaRobots;
        this.terreno = terreno;
    }

    public ArrayList<Fabrica> getHistorialGeneraciones() {
        return historialGeneraciones;
    }

    public void setHistorialGeneraciones(ArrayList<Fabrica> historialGeneraciones) {
        this.historialGeneraciones = historialGeneraciones;
    }

    public Fabrica getFabricaRobots() {
        return fabricaRobots;
    }

    public void setFabricaRobots(Fabrica fabricaRobots) {
        this.fabricaRobots = fabricaRobots;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public int getNumGeneracionActual() {
        return numGeneracionActual;
    }

    public void setNumGeneracionActual(int numGeneracionActual) {
        this.numGeneracionActual = numGeneracionActual;
    }
    
    public void start (){
        int llegaron = 0;
        int [] posInicial= new int [2];
        posInicial[0]=this.terreno.getSizeTerreno()-1;
        posInicial[1]=0;
        for (int i = 0; i < this.fabricaRobots.getPoblacion().size(); i++) {
            this.fabricaRobots.getPoblacion().get(i).setFinalizado(false);
            this.fabricaRobots.getPoblacion().get(i).setPos(posInicial[0], posInicial[1]);
            this.fabricaRobots.getPoblacion().get(i).getBateria().resetCarga();
            this.fabricaRobots.getPoblacion().get(i).setCasillasVisitadas(new ArrayList());
            this.fabricaRobots.getPoblacion().get(i).setPosiblesMovimientos(new ArrayList());
            while(this.fabricaRobots.getPoblacion().get(i).getBateria().getCarga()>0) {
                if (this.fabricaRobots.getPoblacion().get(i).isFinalizado()){ 
                    break;
                }
                this.fabricaRobots.getPoblacion().get(i).moverEnTerreno();
                if (this.fabricaRobots.getPoblacion().get(i).isFinalizado()){ 
                    llegaron++;
                }
                this.fabricaRobots.getPoblacion().get(i).getBateria().disminuirCarga();
            }
        }
        
        this.historialGeneraciones.add(this.fabricaRobots);
       
        //System.out.println("LLEGARON SOLO: " + llegaron);
    }
    
    public void getNewGeneration (){
        //System.out.println("GENERACION: " + this.numGeneracionActual);
        Fabrica f = this.fabricaRobots.getNuevaGeneracion();
        this.numGeneracionActual = this.numGeneracionActual +1;
        f.setNumGeneracion(this.numGeneracionActual);
        f.cruceEntreIndividuosGen();
        
        f.mutacion();
        f.getNuevasCaracteristicas();
        //System.out.println("GENERACION: " + this.numGeneracionActual);
        this.fabricaRobots = f;
        //System.out.println("GENERACION F: " + this.fabricaRobots.getNumGeneracion());
        //start();
    }
    
    public void printGeneraciones (){
        for (int i = 0; i < this.historialGeneraciones.size(); i++) {
            System.out.println("GENERACIÓN #" + i);
            for (int j = 0; j < this.historialGeneraciones.get(i).getPoblacion().size(); j++) {
                System.out.println("GENES ROBOT #" + j + ": " + this.historialGeneraciones.get(i).getPoblacion().get(j).getGenes().getChain());
            }
        }
    }
}