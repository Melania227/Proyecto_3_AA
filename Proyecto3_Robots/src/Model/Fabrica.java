
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * CLASE FABRICA
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
    private Terreno terreno;
    private int numGeneracion;
    
    public Fabrica(int cantidadDeIndividuos, Terreno terreno) {
        Random rand = new Random();
        this.indiceMutacion = rand.nextInt(56);
        this.poblacion = new ArrayList ();
        this.cantidadDeIndividuos = cantidadDeIndividuos;
        this.numGeneracion = 0;
        for (int i = 0; i < cantidadDeIndividuos; i++) {
            this.poblacion.add(new Robot(terreno,i,this.numGeneracion));
        }
        this.terreno = terreno;
    }
    
    public Fabrica(int cantidadDeIndividuos, int indiceMutacion, Terreno terreno) {
        this.indiceMutacion = indiceMutacion;
        this.poblacion = new ArrayList ();
        this.cantidadDeIndividuos = cantidadDeIndividuos;
        this.terreno = terreno;
    }

    public int getIndiceMutacion() {
        return indiceMutacion;
    }

    public void setIndiceMutacion(int indiceMutacion) {
        this.indiceMutacion = indiceMutacion;
    }

    public Terreno getTerreno() {
        return terreno;
    }

    public void setTerreno(Terreno terreno) {
        this.terreno = terreno;
    }

    public int getNumGeneracion() {
        return numGeneracion;
    }

    public void setNumGeneracion(int numGeneracion) {
        this.numGeneracion = numGeneracion;
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
       for (int i = 0; i < this.poblacion.size(); i++) {
            this.poblacion.get(i).setMutacionIndices(new ArrayList());
        }
        Random rand = new Random();
        int i = rand.nextInt(this.cantidadDeIndividuos);
        int j = rand.nextInt(56);
        for (int m = 0; m < this.indiceMutacion; m++) {
            
            if (this.poblacion.get(i).getGenes().getChain().get(j) == 1){
                this.poblacion.get(i).getGenes().getChain().set(j, 0);
            }
            else{
                this.poblacion.get(i).getGenes().getChain().set(j, 1);
            }
            this.poblacion.get(i).getMutacionIndices().add(j);
            System.out.println(this.poblacion.get(i).getNumGeneracion()+" "+this.poblacion.get(i).getNumRobotEnPoblación());
            i = rand.nextInt(this.cantidadDeIndividuos);
            j = rand.nextInt(56);
            
        }
        
        
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
        Fabrica nuevaGeneracion = new Fabrica(this.cantidadDeIndividuos, this.indiceMutacion, this.terreno);
        ArrayList<Double> calificacionesNormalizadas = getAdaptabilidad();
       
        ArrayList<Robot> seleccionRobots = new ArrayList ();
        Random rand = new Random();
        for (int i = 0; i < this.poblacion.size(); i++) {
            double indice = rand.nextDouble()*100;
            seleccionRobots.add(getRobotSeleccionado(calificacionesNormalizadas, indice));
        }
        
        for (int i = 0; i < seleccionRobots.size(); i++) {
            Robot nuevoRobot = (Robot) Clonador.deepCopy(seleccionRobots.get(i));
            nuevoRobot.setNumRobotEnPoblación(i);
            nuevoRobot.setNumGeneracion(this.numGeneracion+1);
            nuevoRobot.setDatosPadre1(this.numGeneracion, seleccionRobots.get(i).getNumRobotEnPoblación());
//            System.out.println("Datos del padre: " + this.numGeneracion + ", " + i);
            nuevaGeneracion.poblacion.add(nuevoRobot);
        }
        
        return nuevaGeneracion;
    }
    
    public ArrayList<Double> getAdaptabilidad(){
        ArrayList<Double> calificaciones = new ArrayList();
        double total = 0.0;
        int casillasFaltantes;
        double numGanancia;
        double hardware;
        for (int i = 0; i < this.poblacion.size(); i++) {
            hardware = 0;
            hardware += this.poblacion.get(i).getBateria().getCostoAdaptabilidad()/10;
            hardware += this.poblacion.get(i).getCamara().getCostoAdaptabilidad()/10;
            hardware += this.poblacion.get(i).getMotor().getCostoAdaptabilidad()/10;
            hardware += this.poblacion.get(i).getBateria().getCarga();
            hardware += hardware/100;
            casillasFaltantes = 0;
            casillasFaltantes+=(this.poblacion.get(i).getPos()[0]);
            casillasFaltantes+=(19-this.poblacion.get(i).getPos()[1]);
            numGanancia = 38 - casillasFaltantes; 
            numGanancia = numGanancia/1000;
            numGanancia+=hardware;
            numGanancia = (numGanancia+1)*10;
            calificaciones.add(numGanancia);
            this.poblacion.get(i).setPuntajeAdaptabilidad(numGanancia);
            //System.out.println("PUNTUACIÓN: " + numGanancia);
            total += calificaciones.get(i);
        }
        for (int i = 0; i < calificaciones.size(); i++) {
            calificaciones.set(i, (double)((calificaciones.get(i)*100)/total));
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
//            System.out.println("i+1 " + this.poblacion.get(i+1).getDatosPadre1()[0] + ", " + this.poblacion.get(i+1).getDatosPadre1()[1]);
//            System.out.println("i " + this.poblacion.get(i).getDatosPadre1()[0]+ ", " + this.poblacion.get(i).getDatosPadre1()[1]);
            this.poblacion.get(i).setDatosPadre2(this.poblacion.get(i+1).getDatosPadre1());
            this.poblacion.get(i+1).setDatosPadre2(this.poblacion.get(i).getDatosPadre1());
//            System.out.println("Datos del padre 2 (i): " + "MIJO: " + i +" "+ + this.poblacion.get(i).getDatosPadre1()[0] + ", " + this.poblacion.get(i).getDatosPadre1()[1]);
//            System.out.println("Datos del padre 2 (i+1): " + "MIJO: " + i +" "+ this.poblacion.get(i+1).getDatosPadre1()[0] + ", " + this.poblacion.get(i+1).getDatosPadre1()[1]);
//            System.out.println("i+1 2 " + this.poblacion.get(i+1).getDatosPadre2()[0] + ", " + this.poblacion.get(i+1).getDatosPadre2()[1]);
//            System.out.println("i 2 " + this.poblacion.get(i).getDatosPadre2()[0]+ ", " + this.poblacion.get(i).getDatosPadre2()[1]);
            
        }
    }
    
    public void getNuevasCaracteristicas(){
        int [] posInicial= new int [2];
        posInicial[0]=this.terreno.getSizeTerreno()-1;
        posInicial[1]=0;
        for (int i = 0; i < this.poblacion.size(); i++) {
            this.poblacion.get(i).setBateria(this.poblacion.get(i).getBateriaByGenes());
            this.poblacion.get(i).setCamara(this.poblacion.get(i).getCamaraByGenes());
            this.poblacion.get(i).setMotor(this.poblacion.get(i).getMotorByGenes());
            this.poblacion.get(i).setPos(posInicial[0], posInicial[1]);
            this.poblacion.get(i).getBateria().resetCarga();
            this.poblacion.get(i).setFinalizado(false);
            this.poblacion.get(i).setCasillasVisitadas(new ArrayList());
            this.poblacion.get(i).setPosiblesMovimientos(new ArrayList());
            this.poblacion.get(i).setPuntajeAdaptabilidad(0);
        }
    }
}