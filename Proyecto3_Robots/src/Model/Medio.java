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
        start();
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
        for (int i = 0; i < this.fabricaRobots.getPoblacion().size(); i++) {
            for (int j = 0; j < 1000; j++) {
                if (this.fabricaRobots.getPoblacion().get(i).isFinalizado()) break;
                this.fabricaRobots.getPoblacion().get(i).moverEnTerreno();
                if (this.fabricaRobots.getPoblacion().get(i).isFinalizado()) llegaron++;
            }
//            int [][] tableroTemp = new int [this.terreno.getSizeTerreno()][this.terreno.getSizeTerreno()];
//            tableroTemp[this.fabricaRobots.getPoblacion().get(i).getPos()[0]][this.fabricaRobots.getPoblacion().get(i).getPos()[1]] = 7;
//            String strBoard = "";
//            for (int k = 0; k < tableroTemp.length; k++) {
//                String line = "";
//                for (int l = 0; l < tableroTemp[0].length; l++) {
//                    line+=tableroTemp[k][l]+" ";
//                }
//                strBoard+=line+"\n";
//            }
//            System.out.println(strBoard);
        }
        System.out.println("LLEGARON SOLO: " + llegaron);
    }
    
}
