/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author USUARIO
 */
public class Medio {
    private Fabrica fabricaRobots;
    private Terreno terreno;

    public Medio() {
        this.fabricaRobots = new Fabrica(10);
        this.terreno = new Terreno (5);
    }
    
    public Medio(Fabrica fabricaRobots, Terreno terreno) {
        this.fabricaRobots = fabricaRobots;
        this.terreno = terreno;
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
    
    
}
