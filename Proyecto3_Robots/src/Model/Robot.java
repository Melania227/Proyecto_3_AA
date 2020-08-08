/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * EN CLASE ROBOT
 */
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author USUARIO
 */
public class Robot implements Serializable{
    private Camara camara;
    private Bateria bateria;
    private Motor motor;
    private Cromosomas genes;
    private Terreno terreno;
    private int[] pos;
    private ArrayList <int[]> cadenaMarkov;
    private ArrayList <String> posiblesMovimientos;
    private ArrayList <int[]> casillasVisitadas; 
    private boolean finalizado;
    
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
        generarCadenaMarkov();
        this.casillasVisitadas = new ArrayList();
        int [] posTemp = new int [2];
        posTemp[0]=this.pos[0];
        posTemp[1]=this.pos[1];
        this.casillasVisitadas.add(posTemp);
        this.finalizado = false;
        //System.out.println("CAMARA: " + this.camara.getTipo());
        //System.out.println("MOTOR: " + this.motor.getTipo());
    }

    public Robot(Camara camara, Bateria bateria, Motor motor) {
        this.camara = camara;
        this.bateria = bateria;
        this.motor = motor;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
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

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
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

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }
    
    public void setPos(int x, int y) {
        this.pos[0]=x;
        this.pos[1]=y;
    }

    public ArrayList<int[]> getCadenaMarkov() {
        return cadenaMarkov;
    }

    public void setCadenaMarkov(ArrayList<int[]> cadenaMarkov) {
        this.cadenaMarkov = cadenaMarkov;
    }

    public ArrayList<String> getPosiblesMovimientos() {
        return posiblesMovimientos;
    }

    public void setPosiblesMovimientos(ArrayList<String> posiblesMovimientos) {
        this.posiblesMovimientos = posiblesMovimientos;
    }

    public ArrayList<int[]> getCasillasVisitadas() {
        return casillasVisitadas;
    }

    public void setCasillasVisitadas(ArrayList<int[]> casillasVisitadas) {
        this.casillasVisitadas = casillasVisitadas;
    }
    
    
        
    public Bateria getBateriaByGenes (){
        int num = this.genes.generarValor(1);
        double porcentaje = (double)num/(double)256;
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
        int [] posTempComp = new int [2];
        posTempComp[0]=this.pos[0];
        posTempComp[1]=this.pos[1];
        if (!yaVisitado(posTempComp[0], posTempComp[1])){
            casillasVisitadas.add(this.pos);
        }
        validarMovimientoPorPosicion();
        int posX = this.cadenaMarkov.get(0)[0];
        int posY = this.cadenaMarkov.get(0)[1];
        
        TipoTerreno tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeArriba = 0.0;
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Arriba") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeArriba = porcentajesCM(tipoTerreno, 0, posX, posY);
            if (yaVisitado(posX, posY)){
                porcentajeArriba = porcentajeArriba/(porcentajeArriba+1);
            }
        }
        
        posX = this.cadenaMarkov.get(1)[0];
        posY = this.cadenaMarkov.get(1)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeAbajo = 0.0;
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Abajo") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeAbajo = porcentajesCM(tipoTerreno, 1, posX, posY);
            if (yaVisitado(posX, posY)){
                porcentajeAbajo = porcentajeAbajo/(porcentajeAbajo+1);
            }
        }
        
        posX = this.cadenaMarkov.get(2)[0];
        posY = this.cadenaMarkov.get(2)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeDerecha = 0.0;
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Derecha") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeDerecha = porcentajesCM(tipoTerreno, 2, posX, posY);
            if (yaVisitado(posX, posY)){
                porcentajeDerecha = porcentajeDerecha/(porcentajeDerecha+1);
            }
        }
       
        posX = this.cadenaMarkov.get(3)[0];
        posY = this.cadenaMarkov.get(3)[1];
        tipoTerreno = this.terreno.getMatrizTerreno()[posX][posY];
        double porcentajeIzquierda = 0.0;
        if ((this.pos[0] != posX || this.pos[1] != posY) && this.posiblesMovimientos.contains("Izquierda") && tipoTerreno != tipoTerreno.BLOQUEADO){
            porcentajeIzquierda = porcentajesCM(tipoTerreno, 3, posX, posY);
            if (yaVisitado(posX, posY)){
                porcentajeIzquierda = porcentajeIzquierda/(porcentajeIzquierda+1);
            }
        }
        
        String movimiento =  obtenerMovimiento(porcentajeArriba, porcentajeAbajo, porcentajeDerecha, porcentajeIzquierda);
        switch(movimiento){
            case "Arriba":
                return this.cadenaMarkov.get(0);
            case "Abajo":
                return this.cadenaMarkov.get(1); 
            case "Derecha":
                return this.cadenaMarkov.get(2);
            case "Izquierda":
                return this.cadenaMarkov.get(3);
            default:
                return this.pos;
        }
    }
    
    public boolean yaVisitado (int posX, int posY){
        boolean yaEstaba = false;
        for (int i = 0; i < this.casillasVisitadas.size(); i++) {
            if (this.casillasVisitadas.get(i)[0] == posX && this.casillasVisitadas.get(i)[1] == posY){
                return true;
            }
        }
        return false;
    }
    
    public String obtenerMovimiento(double porcentajeArriba, double porcentajeAbajo, double porcentajeDerecha, double porcentajeIzquierda)
    {
        double porcentajeTotal = 4.0 + porcentajeArriba + porcentajeAbajo + porcentajeDerecha + porcentajeIzquierda;
        porcentajeArriba = ((porcentajeArriba+1.0) * 100)/porcentajeTotal;
        porcentajeAbajo = ((porcentajeAbajo+1.0) * 100)/porcentajeTotal;
        porcentajeDerecha = ((porcentajeDerecha+1.0) * 100)/porcentajeTotal;
        porcentajeIzquierda = ((porcentajeIzquierda+1.0) * 100)/porcentajeTotal;
//        System.out.println("ARRIBA: " + porcentajeArriba);
//        System.out.println("ABAJO: " + porcentajeAbajo);
//        System.out.println("DERECHA: " + porcentajeDerecha);
//        System.out.println("IZQUIERDA: " + porcentajeIzquierda);
        Random rand = new Random ();
        int porcentajeRandom = rand.nextInt(100);
        if (porcentajeRandom < porcentajeArriba){
            return "Arriba";
        }
        else if (porcentajeRandom >= porcentajeArriba && porcentajeRandom < porcentajeArriba+porcentajeAbajo){
            return "Abajo";
        }
        else if (porcentajeRandom >= porcentajeArriba+porcentajeAbajo && porcentajeRandom < porcentajeArriba+porcentajeAbajo+porcentajeDerecha){
            return "Derecha";
        }
        else{
            return "Izquierda";
        }
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
                        return 80.0;
                    case MODERADO:
                        return 20.0;
                    case DIFICIL:
                        return 10.0;
                    default:
                        return 0.0;
                }
            case 2:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 80.0;
                    case MODERADO:
                        return 70.0;
                    case DIFICIL:
                        return 10.0;
                    default:
                        return 0.0;
                }
            case 3:
                switch (tipoTerreno) {
                    case NORMAL:
                        return 80.0;
                    case MODERADO:
                        return 70.0;
                    case DIFICIL:
                        return 60.0;
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
        double porcentaje1;
        double porcentaje2;
        double porcentaje3;
        int cantPorcentajesValidos;
        switch (tipoCamara) {
            case 1:
                return porcentajeMotor(tipoTerreno);
            case 2:
                tipoTerreno_1 = getTipoTerreno(numEstado, fila, columna, 1);
                porcentaje1 = porcentajeMotor(tipoTerreno);
                porcentaje2 = porcentajeMotor(tipoTerreno_1);
                cantPorcentajesValidos = getPorcentajesValidos(porcentaje1, porcentaje2, 0.0);
                return (porcentaje1 + porcentaje2)/cantPorcentajesValidos;
            case 3:
                tipoTerreno_1 = getTipoTerreno(numEstado, fila, columna, 1);
                tipoTerreno_2 = getTipoTerreno(numEstado, fila, columna, 2);
                porcentaje1 = porcentajeMotor(tipoTerreno);
                porcentaje2 = porcentajeMotor(tipoTerreno_1);
                porcentaje3 = porcentajeMotor(tipoTerreno_2);
                cantPorcentajesValidos = getPorcentajesValidos(porcentaje1, porcentaje2, porcentaje3);
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
                if ((fila-numEspacios)>=0){
                    return this.terreno.getMatrizTerreno()[fila-numEspacios][columna];
                }
                return TipoTerreno.BLOQUEADO;
            case 1:
                if ((fila+numEspacios)<=this.terreno.getSizeTerreno()-1){
                    return this.terreno.getMatrizTerreno()[fila+numEspacios][columna];
                }
                return TipoTerreno.BLOQUEADO;
            case 2:
                if ((columna+numEspacios)<=this.terreno.getSizeTerreno()-1){
                    return this.terreno.getMatrizTerreno()[fila][columna+numEspacios];
                }
                return TipoTerreno.BLOQUEADO;
            case 3:
                if ((columna-numEspacios)>=0){
                    return this.terreno.getMatrizTerreno()[fila][columna-numEspacios];
                }
                return TipoTerreno.BLOQUEADO;
            default:
                return TipoTerreno.BLOQUEADO;
        }
    }
    
    public void generarCadenaMarkov (){
        this.posiblesMovimientos = new ArrayList ();
        this.cadenaMarkov = new ArrayList ();
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
    
    public void moverEnTerreno(){
        generarCadenaMarkov();
        int[] arreglo = new int[2];
        arreglo[0] = this.pos[0];
        arreglo[1] = this.pos[1];
        switch(this.motor.getTipo()){
            case 1:
                if(this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]]== TipoTerreno.NORMAL)
                    this.pos = comportamiento();
                break;
            case 2:
                if(this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]]== TipoTerreno.NORMAL)
                    this.pos = comportamiento();
                else if(this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]]== TipoTerreno.MODERADO)
                    this.pos = comportamiento();
                break;
            case 3:
                if (this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]] != TipoTerreno.BLOQUEADO){
                    this.pos = comportamiento();
                }
                break;
            default:
                this.pos = comportamiento();
        }
        if (this.terreno.getMatrizTerreno()[this.pos[0]][this.pos[1]]== TipoTerreno.BLOQUEADO){
            this.pos[0]= arreglo[0];
            this.pos[1]= arreglo[1];
        }
        if (this.pos[0]==0 && this.pos[1]==this.terreno.getSizeTerreno()-1){
            this.finalizado = true;
        }
    }
}