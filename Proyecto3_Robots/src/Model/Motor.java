/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.util.ArrayList;

/**
 *
 * @author USUARIO
 */
public class Motor {
    private int tipo;
    private ArrayList <TipoTerreno> tipoTerreno;
    private int consumo;
    private int costo;

    public Motor(int tipo, ArrayList<TipoTerreno> tipoTerreno, int consumo, int costo) {
        this.tipo = tipo;
        this.tipoTerreno = tipoTerreno;
        this.consumo = consumo;
        this.costo = costo;
    }
    
    public Motor(int tipo) {
        this.tipo = tipo;
        if (tipo == 1){
            tipoTerreno.add(TipoTerreno.NORMAL);
            consumo = 0;
            costo = 0;
        }
        else if (tipo == 2){
            tipoTerreno.add(TipoTerreno.NORMAL);
            tipoTerreno.add(TipoTerreno.MODERADO);
            consumo = 0;
            costo = 0;
        }
        else if (tipo == 3){
            tipoTerreno.add(TipoTerreno.NORMAL);
            tipoTerreno.add(TipoTerreno.MODERADO);
            tipoTerreno.add(TipoTerreno.DIFICIL);
            consumo = 0;
            costo = 0;
        }
        else{
            tipoTerreno.add(TipoTerreno.NORMAL);
            consumo = 0;
            costo = 0;
        }
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public ArrayList<TipoTerreno> getTipoTerreno() {
        return tipoTerreno;
    }

    public void setTipoTerreno(ArrayList<TipoTerreno> tipoTerreno) {
        this.tipoTerreno = tipoTerreno;
    }

    public int getConsumo() {
        return consumo;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
}
