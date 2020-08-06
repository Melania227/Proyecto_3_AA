/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public Medio(int cantRobots, int sizeTerreno) {
        this.terreno = new Terreno (sizeTerreno);
        this.fabricaRobots = new Fabrica(cantRobots, this.terreno);
        this.historialGeneraciones = new ArrayList ();
        this.historialGeneraciones.add(this.fabricaRobots);
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
    
    public void start (){
        int llegaron = 0;
        int [] posInicial= new int [2];
        posInicial[0]=this.terreno.getSizeTerreno()-1;
        posInicial[1]=0;
        for (int i = 0; i < this.fabricaRobots.getPoblacion().size(); i++) {
            //this.fabricaRobots.getPoblacion().get(i).setFinalizado(false);
           // this.fabricaRobots.getPoblacion().get(i).setPos(posInicial[0], posInicial[1]);
           // this.fabricaRobots.getPoblacion().get(i).getBateria().resetCarga();
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
            //System.out.println("---- " + this.fabricaRobots.getPoblacion().get(i).getBateria().getCarga() + " " + this.fabricaRobots.getPoblacion().get(i).getCamara().getTipo()+ " " + this.fabricaRobots.getPoblacion().get(i).getMotor().getTipo() + " " + this.fabricaRobots.getPoblacion().get(i).getPos()[0] + ", " + this.fabricaRobots.getPoblacion().get(i).getPos()[1] + " " + this.fabricaRobots.getPoblacion().get(i).isFinalizado());
        }
        System.out.println("LLEGARON SOLO: " + llegaron);
    }
    
//    public void newGeneration (){
//        this.fabricaRobots.cruceEntreIndividuosGen();
//    }
    
}
