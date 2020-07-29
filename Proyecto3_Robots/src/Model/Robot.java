/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

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
    
    public Robot (){
        Random random = new Random();
        this.genes = new Cromosomas ();
        for (int i = 0; i < 24; i++){
            int num = random.nextInt(2);
            this.genes.getChain().add(num);
        }
        this.bateria = getBateriaByGenes();
        this.camara = getCamaraByGenes();
        this.motor = getMotorByGenes();
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
    
    
}
