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
public class Camara implements Serializable{
    private int consumo;
    private int tipo;
    private int costo;
    private int espacioXVer;

    public Camara(int consumo, int tipo, int costo) {
        this.consumo = consumo;
        this.tipo = tipo;
        this.costo = costo;
        this.espacioXVer = tipo;
    }

    public Camara(int tipo) {
        this.espacioXVer = tipo;
        this.tipo = tipo;
        
        if (tipo == 1){
            consumo = 50;
            costo = 2;
        }
        else if (tipo == 2){
            consumo = 70;
            costo = 3;
        }
        else if (tipo == 3){
            consumo = 100;
            costo = 5;
        }
        else{
            consumo = 20;
            costo = 1;
        } 
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getEspacioXVer() {
        return espacioXVer;
    }

    public void setEspacioXVer(int espacioXVer) {
        this.espacioXVer = espacioXVer;
    }
    
    
}
