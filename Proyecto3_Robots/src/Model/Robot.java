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
public class Robot {
    private Camara camara;
    private Bateria bateria;
    private Motor motor;
    private Cromosomas genes;
    private Terreno terreno;
    private int[] pos;
    private ArrayList <int[]> cadenaMarkov;
    
    public Robot (Terreno terreno){
        this.terreno = terreno;
        Random random = new Random();
        this.genes = new Cromosomas ();
        for (int i = 0; i < 32; i++){
            int num = random.nextInt(2);
            this.genes.getChain().add(num);
        }
        this.pos = new int [2];
        this.pos[0] = this.terreno.getSizeTerreno()-1;
        this.pos[1] = 0;
        this.bateria = getBateriaByGenes();
        this.camara = getCamaraByGenes();
        this.motor = getMotorByGenes();
        this.cadenaMarkov = new ArrayList ();
        generarCadenaMarkov();
        //System.out.println(this.genes.getChain());
        //System.out.println(this.bateria.getSize());
        //System.out.println(this.camara.getTipo());
        //System.out.println(this.motor.getTipo());
    }

    public Robot(Camara camara, Bateria bateria, Motor motor) {
        this.camara = camara;
        this.bateria = bateria;
        this.motor = motor;
    }

    public Camara getCamara() {
        return camara;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
    }

    public Bateria getBateria() {
        return bateria;
    }

    public void setBateria(Bateria bateria) {
        this.bateria = bateria;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Cromosomas getGenes() {
        return genes;
    }

    public void setGenes(Cromosomas genes) {
        this.genes = genes;
    }
    
    public Bateria getBateriaByGenes (){
        int num = this.genes.generarValor(1);
        double porcentaje = (double)num/(double)256;
        //System.out.println(porcentaje);
        if (porcentaje <= 1 && porcentaje > 0.66){
            return new Bateria(3);
        }
        else if (porcentaje <= 0.66 && porcentaje > 0.33){
            return new Bateria(2);
        }
        else if (porcentaje <= 0.33 && porcentaje >= 0){
            return new Bateria(1);
        }
        return new Bateria(1);
    }
    
    public Camara getCamaraByGenes (){
        int num = this.genes.generarValor(2);
        double porcentaje = (double)num/(double)256;
        //System.out.println(porcentaje);
        if (porcentaje <= 1 && porcentaje > 0.66){
            return new Camara(3);
        }
        else if (porcentaje <= 0.66 && porcentaje > 0.33){
            return new Camara(2);
        }
        else if (porcentaje <= 0.33 && porcentaje >= 0){
            return new Camara(1);
        }
        return new Camara(1);
    }
    
    public Motor getMotorByGenes (){
        
        int num = this.genes.generarValor(3);
        double porcentaje = (double)num/(double)256;
        //System.out.println(porcentaje);
        if (porcentaje <= 1 && porcentaje > 0.66){
            return new Motor(3);
        }
        else if (porcentaje <= 0.66 && porcentaje > 0.33){
            return new Motor(2);
        }
        else if (porcentaje <= 0.33 && porcentaje >= 0){
            return new Motor(1);
        }
        return new Motor(1);
    }
    
    public void cruceEntreRobots(Robot robot){
        this.genes.cruce(robot.genes);
    }
    
    public int[] comportamiento ()
    {
        
        int posX = this.cadenaMarkov.get(0)[0];
        int posY = this.cadenaMarkov.get(0)[1];
        TipoTerreno tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeArriba = porcentajesCM(tipoTerreno);
       
        posX = this.cadenaMarkov.get(1)[0];
        posY = this.cadenaMarkov.get(1)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeAbajo = porcentajesCM(tipoTerreno);
        
        posX = this.cadenaMarkov.get(2)[0];
        posY = this.cadenaMarkov.get(2)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeDerecha = porcentajesCM(tipoTerreno);
       
        posX = this.cadenaMarkov.get(3)[0];
        posY = this.cadenaMarkov.get(3)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeIzquierda = porcentajesCM(tipoTerreno);
        
        String movimiento =  porcentajeMasAlto(porcentajeArriba, porcentajeAbajo, porcentajeDerecha, porcentajeIzquierda);
        switch(movimiento){
            case "Arriba":
                return this.cadenaMarkov.get(0);
            case "Abajo":
                return this.cadenaMarkov.get(1); 
            case "Derecha":
                return this.cadenaMarkov.get(2);
            case "Izquierda":
                return this.cadenaMarkov.get(3);
        
        }
        return null;
    }
    
    public String porcentajeMasAlto(double porcentajeArriba, double porcentajeAbajo, double porcentajeDerecha, double porcentajeIzquierda)
    {
        double porcentajeAltoAct = porcentajeArriba;
        if (porcentajeArriba > porcentajeAbajo && porcentajeArriba > porcentajeDerecha && porcentajeArriba > porcentajeIzquierda)
            return "Arriba";
        if (porcentajeAbajo > porcentajeDerecha && porcentajeAbajo > porcentajeIzquierda)
            return "Abajo";
        if (porcentajeDerecha> porcentajeIzquierda)
            return "Derecha";
        return "Izquierda";
    }
    
    public double porcentajesCM (TipoTerreno tipoTerreno){
        double porcentajeMotor = porcentajeMotor(tipoTerreno);
        double porcentajeCamara = porcentajeCamara();
        return 0.0;
    }
    
    public double porcentajeMotor(TipoTerreno tipoTerreno){
        int tipoMotor = this.motor.getTipo();
        switch (tipoMotor){
            case 1:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 90.0;
                    case MODERADO:
                        return 30.0;
                    case DIFICIL:
                        return 0.0;
                    default:
                        return 0.0;
                }
            case 2:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 90.0;
                    case MODERADO:
                        return 90.0;
                    case DIFICIL:
                        return 30.0;
                    default:
                        return 0.0;
                }
            case 3:
                return 100.0;
            default:
                return 0.0;
        }
    }
    
    public double porcentajeCamara(){
        int tipoCamara = this.camara.getTipo();
        switch (tipoCamara) {
            case 1:
                return 90.0;
            case 2:
                return 60.0;
            case 3:
                return 30.0;
            default:
                return 0.0;
        }
    }
    
    public void generarCadenaMarkov (){
        int posX = this.pos[0];
        int posY = this.pos[1];
        int [] estado = new int[2];
        estado[0] = posX;
        estado[1] = posY-1;
        this.cadenaMarkov.add(estado);
        estado[0] = posX;
        estado[1] = posY+1;
        this.cadenaMarkov.add(estado);
        estado[0] = posX+1;
        estado[1] = posY;
        this.cadenaMarkov.add(estado);
        estado[0] = posX-1;
        estado[1] = posY;
        this.cadenaMarkov.add(estado);
    }
    
}
