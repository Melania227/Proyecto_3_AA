/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author USUARIO
 */
public class Bateria implements Serializable{
    private int costo;
    private int size;
    private int carga;

    public Bateria(int costo, int size, int carga) {
        this.costo = costo;
        this.size = size;
        this.carga = carga;
    }
    
    public Bateria(int size) {
        this.size = size;
        
        if (size == 1){
            carga = 0;
            costo = 0;
        }
        else if (size == 2){
            carga = 0;
            costo = 0;
        }
        else if (size == 3){
            carga = 0;
            costo = 0;
        }
        else{
            carga = 0;
            costo = 0;
        } 
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        this.carga = carga;
    }
    
    
}
