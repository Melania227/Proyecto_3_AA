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
    private ArrayList <String> posiblesMovimientos;
    
    public Robot (Terreno terreno){
        this.terreno = terreno;
        Random random = new Random();
        this.genes = new Cromosomas ();
        for (int i = 0; i < 56; i++){
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
        generarCadenaMarkov();//ponerlos para cada movimiento
        this.posiblesMovimientos = new ArrayList ();//ponerlos para cada movimiento
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
        TipoTerreno tipoTerreno = this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]];
        if (tipoTerreno==tipoTerreno.BLOQUEADO){
            System.out.println("ME QUEDO");
            return this.pos;
        }
        validarMovimientoPorPosicion();
        for (int i = 0; i < this.posiblesMovimientos.size(); i++) {
            System.out.println("IR A: " + this.posiblesMovimientos.get(i));
        }
        
        int posX = this.cadenaMarkov.get(0)[0];
        int posY = this.cadenaMarkov.get(0)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeArriba = 0.0;
        validarMovimientoPorTerreno(tipoTerreno, "Arriba");
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Arriba") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeArriba = porcentajesCM(tipoTerreno, 0, posX, posY);
        }
        
        posX = this.cadenaMarkov.get(1)[0];
        posY = this.cadenaMarkov.get(1)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeAbajo = 0.0;
        validarMovimientoPorTerreno(tipoTerreno, "Abajo");
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Abajo") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeAbajo = porcentajesCM(tipoTerreno, 1, posX, posY);
        }
        
        posX = this.cadenaMarkov.get(2)[0];
        posY = this.cadenaMarkov.get(2)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeDerecha = 0.0;
        validarMovimientoPorTerreno(tipoTerreno, "Derecha");
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Derecha") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeDerecha = porcentajesCM(tipoTerreno, 2, posX, posY);
        }
       
        posX = this.cadenaMarkov.get(3)[0];
        posY = this.cadenaMarkov.get(3)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeIzquierda = 0.0;
        validarMovimientoPorTerreno(tipoTerreno, "Izquierda");
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Izquierda") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeIzquierda = porcentajesCM(tipoTerreno, 3, posX, posY);
        }
        
        System.out.println("CAMARA: " + this.camara.getTipo());
        System.out.println("MOTOR: " + this.motor.getTipo());
        
        String movimiento =  porcentajeMasAlto(porcentajeArriba, porcentajeAbajo, porcentajeDerecha, porcentajeIzquierda);
        switch(movimiento){
            case "Arriba":
                System.out.println("ELIJO ARRIBA: "+ this.cadenaMarkov.get(0)[0] + ", "+ this.cadenaMarkov.get(0)[1]);
                return this.cadenaMarkov.get(0);
            case "Abajo":
                System.out.println("ELIJO ABAJO: "+ this.cadenaMarkov.get(1)[0] + ", "+ this.cadenaMarkov.get(1)[1]);
                return this.cadenaMarkov.get(1); 
            case "Derecha":
                System.out.println("ELIJO DERECHA: "+ this.cadenaMarkov.get(2)[0] + ", "+ this.cadenaMarkov.get(2)[1]);
                return this.cadenaMarkov.get(2);
            case "Izquierda":
                System.out.println("ELIJO IZQUIERDA: "+ this.cadenaMarkov.get(3)[0] + ", "+ this.cadenaMarkov.get(3)[1]);
                return this.cadenaMarkov.get(3);
            default:
                System.out.println("ME QUEDO");
                return this.pos;
        }
    }
    
    public String porcentajeMasAlto(double porcentajeArriba, double porcentajeAbajo, double porcentajeDerecha, double porcentajeIzquierda)
    {
        System.out.println("ARRIBA: " + porcentajeArriba);
        System.out.println("ABAJO: " + porcentajeAbajo);
        System.out.println("DERECHA: " + porcentajeDerecha);
        System.out.println("IZQUIERDA: " + porcentajeIzquierda);
        if (porcentajeArriba > porcentajeAbajo && porcentajeArriba > porcentajeDerecha && porcentajeArriba > porcentajeIzquierda)
            return "Arriba";
        if (porcentajeAbajo > porcentajeDerecha && porcentajeAbajo > porcentajeIzquierda)
            return "Abajo";
        if (porcentajeDerecha> porcentajeIzquierda)
            return "Derecha";
        if(porcentajeArriba == porcentajeAbajo && porcentajeAbajo == porcentajeDerecha && porcentajeDerecha == porcentajeIzquierda){
            return "Stay";
        }
        return "Izquierda";
    }
    
    public void validarMovimientoPorPosicion (){
        if (this.pos[0] == this.terreno.getSizeTerreno()-1){
            if(this.pos[1] != 0 && this.pos[1] != this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Derecha");
                this.posiblesMovimientos.add("Izquierda");
            }
            else if(this.pos[1] == 0){
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Derecha");
            }
            else if(this.pos[1] == this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Izquierda");
            }
        }
        else if (this.pos[0] == 0){            
            if(this.pos[1] != 0 && this.pos[1] != this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Derecha");
                this.posiblesMovimientos.add("Izquierda");
            }
            else if(this.pos[1] == 0){
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Derecha");
            }
            else if(this.pos[1] == this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Izquierda");
            }
        }
        else{
            if(this.pos[1] != 0 && this.pos[1] != this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Derecha");
                this.posiblesMovimientos.add("Izquierda");
            }
            else if(this.pos[1] == 0){
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Derecha");
            }
            else if(this.pos[1] == this.terreno.getSizeTerreno()-1){
                this.posiblesMovimientos.add("Arriba");
                this.posiblesMovimientos.add("Abajo");
                this.posiblesMovimientos.add("Izquierda");
            }
        }
    }
    
    public void validarMovimientoPorTerreno (TipoTerreno tipoTerreno, String dir){
        switch (this.motor.getTipo()){
            case 1:
                switch (tipoTerreno) {
                    case MODERADO:
                        if(!this.posiblesMovimientos.contains(dir)){
                            this.posiblesMovimientos.add(dir);
                        }
                    case DIFICIL:
                        if(!this.posiblesMovimientos.contains(dir)){
                            this.posiblesMovimientos.add(dir);
                        }
                }
            case 2:
                switch (tipoTerreno) {
                    case DIFICIL:
                        if(!this.posiblesMovimientos.contains(dir)){
                            this.posiblesMovimientos.add(dir);
                        }
                }
        }
    }
    
    public double porcentajesCM (TipoTerreno tipoTerreno, int numEstado, int fila, int columna){
        numEstado += 4;
        double porcentajeCadenaGenes = this.genes.generarValor(numEstado);
        double porcentajeMotor = porcentajeMotor(tipoTerreno);
        numEstado -= 4;
        double porcentajeCamara = porcentajeCamara(tipoTerreno, numEstado, fila, columna); 
        double porcentajeTotal = (porcentajeCadenaGenes + porcentajeCamara + porcentajeMotor)/3;
        return porcentajeTotal;
    }
    
    public double porcentajeMotor(TipoTerreno tipoTerreno){
        int tipoMotor = this.motor.getTipo();
        switch (tipoMotor){
            case 1:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 100.0;
                    case MODERADO:
                        return 30.0;
                    case DIFICIL:
                        return 20.0;
                    default:
                        return 0.0;
                }
            case 2:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 100.0;
                    case MODERADO:
                        return 90.0;
                    case DIFICIL:
                        return 30.0;
                    default:
                        return 0.0;
                }
            case 3:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 100.0;
                    case MODERADO:
                        return 90.0;
                    case DIFICIL:
                        return 80.0;
                    default:
                        return 0.0;
                }
            default:
                return 0.0;
        }
    }
    
    public double porcentajeCamara(TipoTerreno tipoTerreno, int numEstado, int fila, int columna){
        int tipoCamara = this.camara.getTipo();
        TipoTerreno tipoTerreno_1;
        TipoTerreno tipoTerreno_2;
        double porcentaje1 = 0.0;
        double porcentaje2 = 0.0;
        double porcentaje3 = 0.0;
        int cantPorcentajesValidos = 0;
        switch (tipoCamara) {
            case 1:
                System.out.println("Porcentaje: " + porcentajeMotor(tipoTerreno));
                return porcentajeMotor(tipoTerreno);
            case 2:
                tipoTerreno_1 = getTipoTerreno(numEstado, fila, columna, 1);
                System.out.println("NUM ESTADO: " + numEstado);
                System.out.println("TIPO DE TERRENO ES: " + tipoTerreno);
                System.out.println("TIPO DE TERRENO ES 2: " + tipoTerreno_1);
                porcentaje1 = porcentajeMotor(tipoTerreno);
                porcentaje2 = porcentajeMotor(tipoTerreno_1);
                cantPorcentajesValidos = getPorcentajesValidos(porcentaje1, porcentaje2, 0.0);
                System.out.println("Porcentaje: " + porcentaje1 + " + " + porcentaje2);
                System.out.println("Validos: " + cantPorcentajesValidos);
                return (porcentaje1 + porcentaje2)/cantPorcentajesValidos;
            case 3:
                System.out.println("NUM ESTADO: " + numEstado);
                tipoTerreno_1 = getTipoTerreno(numEstado, fila, columna, 1);
                tipoTerreno_2 = getTipoTerreno(numEstado, fila, columna, 2);
                System.out.println("TIPO DE TERRENO ES: " + tipoTerreno);
                System.out.println("TIPO DE TERRENO ES 2: " + tipoTerreno_1);
                System.out.println("TIPO DE TERRENO ES 3: " + tipoTerreno_2);
                porcentaje1 = porcentajeMotor(tipoTerreno);
                porcentaje2 = porcentajeMotor(tipoTerreno_1);
                porcentaje3 = porcentajeMotor(tipoTerreno_2);
                cantPorcentajesValidos = getPorcentajesValidos(porcentaje1, porcentaje2, porcentaje3);
                System.out.println("Porcentaje: " + porcentaje1 + " + " + porcentaje2 + " + " + porcentaje3);
                System.out.println("Validos: " + cantPorcentajesValidos);
                return (porcentaje1 + porcentaje2 + porcentaje3)/cantPorcentajesValidos;
            default:
                return 0.0;
        }
    }
    
    public int getPorcentajesValidos(double porcentaje1, double porcentaje2, double porcentaje3){
        int res = 0;
        if (porcentaje1 != 0.0){
            res++;
        }
        if (porcentaje2 != 0.0){
            res++;
        }
        if (porcentaje3 != 0.0){
            res++;
        }
        return res;
    }
    
    public TipoTerreno getTipoTerreno(int numEstado, int fila, int columna, int numEspacios){
        switch (numEstado){
            case 0:
                System.out.println("COORDENADAS0*: " + (fila-numEspacios) + ", " + columna);
                if ((fila-numEspacios)<=0){
                    System.out.println("COORDENADAS0: " + (fila-numEspacios) + ", " + columna);
                    return this.terreno.getMatrizTerreno()[fila-numEspacios][columna];
                }
                return TipoTerreno.BLOQUEADO;
            case 1:
                System.out.println("COORDENADAS1*: " + (fila+numEspacios) + ", " + columna);
                if ((fila+numEspacios)<=this.terreno.getSizeTerreno()-1){
                    System.out.println("COORDENADAS1: " + (fila+numEspacios) + ", " + columna);
                    return this.terreno.getMatrizTerreno()[fila+numEspacios][columna];
                }
                return TipoTerreno.BLOQUEADO;
            case 2:
                System.out.println("COORDENADAS2*: " + fila + ", " + (columna+numEspacios));
                if ((columna+numEspacios)<=this.terreno.getSizeTerreno()-1){
                    System.out.println("COORDENADAS2: " + fila + ", " + (columna+numEspacios));
                    return this.terreno.getMatrizTerreno()[fila][columna+numEspacios];
                }
                return TipoTerreno.BLOQUEADO;
            case 3:
                System.out.println("COORDENADAS3*: " + fila + ", " + (columna-numEspacios));
                if ((columna-numEspacios)<=0){
                    System.out.println("COORDENADAS3: " + fila + ", " + (columna-numEspacios));
                    return this.terreno.getMatrizTerreno()[fila][columna-numEspacios];
                }
                return TipoTerreno.BLOQUEADO;
            default:
                return TipoTerreno.BLOQUEADO;
        }
    }
    
    public void generarCadenaMarkov (){
        int posX = this.pos[0]; //fila
        int posY = this.pos[1]; //columna
        
        int [] estado = new int[2];
        if(posX != 0){
            estado[0] = posX-1;
            estado[1] = posY;
            this.cadenaMarkov.add(estado);
        }
        else{
            estado[0] = posX;
            estado[1] = posY;
            this.cadenaMarkov.add(estado); //se mantuvo porque no puede ir para arriba
        }
        
        int [] estado2 = new int[2];
        if (posX != this.terreno.getSizeTerreno()-1){
            estado2[0] = posX+1;
            estado2[1] = posY;
            this.cadenaMarkov.add(estado2);
        }
        else{
            estado2[0] = posX;
            estado2[1] = posY;
            this.cadenaMarkov.add(estado2); //se mantuvo porque no puede ir para abajo
        }
           
        int [] estado3 = new int[2];
        if(posY != this.terreno.getSizeTerreno()-1){
            estado3[0] = posX;
            estado3[1] = posY+1;
            this.cadenaMarkov.add(estado3);
        }
        else{
            estado3[0] = posX;
            estado3[1] = posY;
            this.cadenaMarkov.add(estado3); //se mantuvo porque no puede ir para derecha
        }
        
        int [] estado4 = new int[2];
        if(posY != 0){
            estado4[0] = posX;
            estado4[1] = posY-1;
            this.cadenaMarkov.add(estado4);
        }
        else{
            estado4[0] = posX;
            estado4[1] = posY;
            this.cadenaMarkov.add(estado4); //se mantuvo porque no puede ir para izquierda
        }
    }

}
