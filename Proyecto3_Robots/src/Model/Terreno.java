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
public class Terreno {
    private TipoTerreno[][] matrizTerreno;
    private int sizeTerreno;
    
    public Terreno(int size) {
        this.sizeTerreno = size;
        this.matrizTerreno = new TipoTerreno[size][size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int numCasilla = rand.nextInt(4);
                switch (numCasilla){
                    case 0:
                        this.matrizTerreno[i][j] = TipoTerreno.NORMAL;
                    break;
                    case 1:
                        this.matrizTerreno[i][j] = TipoTerreno.DIFICIL;
                    break;
                    case 2:
                        this.matrizTerreno[i][j] = TipoTerreno.MODERADO;
                    break;
                    default:
                        this.matrizTerreno[i][j] = TipoTerreno.BLOQUEADO;
                }
            }
        }
    }
    
    public Terreno(TipoTerreno[][] matrizTerreno) {
        this.matrizTerreno = matrizTerreno;
    }

    public TipoTerreno[][] getMatrizTerreno() {
        return matrizTerreno;
    }

    public void setMatrizTerreno(TipoTerreno[][] matrizTerreno) {
        this.matrizTerreno = matrizTerreno;
    }

    public int getSizeTerreno() {
        return sizeTerreno;
    }

    public void setSizeTerreno(int sizeTerreno) {
        this.sizeTerreno = sizeTerreno;
    }
    
    
    
    public void imprimirTerreno(){
        String res = "";
        for (int i = 0; i < this.sizeTerreno; i++) {
            for (int j = 0; j < this.sizeTerreno; j++) {
                res+=this.matrizTerreno[i][j].toString();
                res+="\t";
            }
            res+="\n";
        }
        System.out.println(res);
    }
}
