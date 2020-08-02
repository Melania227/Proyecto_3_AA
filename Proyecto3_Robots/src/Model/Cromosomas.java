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
public class Cromosomas {
    private ArrayList <Integer> chain;

    public Cromosomas() {
        this.chain = new ArrayList ();
    }

    public ArrayList<Integer> getChain() {
        return chain;
    }

    public void setChain(ArrayList<Integer> chain) {
        this.chain = chain;
    }
    
    public int generarValor (int caso){
        String res = "0";
        switch (caso)
        {
            case 1:
                for(int i = 0; i < 8; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 2:
                for(int i = 8; i < 16; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 3:
                for(int i = 16; i < 24; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 4:
                for(int i = 24; i < 32; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 5:
                for(int i = 32; i < 40; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 6:
                for(int i = 40; i < 48; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            case 7:
                for(int i = 48; i < 56; i++)
                {
                    res +=String.valueOf(this.chain.get(i));
                }
                break;
            default:
                res = "0";
        }
        int resultado = Integer.parseInt(res,2);
        //System.out.println(resultado);
        return resultado;
    }
    
    public void cruce (Cromosomas cadena){
        //System.out.println(cadena.chain);
        //System.out.println(this.chain);
        Random rand = new Random();
        int numParticion = rand.nextInt(56);
        //System.out.println(numParticion);
        for (int i = 0; i < numParticion; i++) {
            int posTemp = this.chain.get(i);
            this.chain.set(i, cadena.chain.get(i));
            cadena.chain.set(i, posTemp);
        }
        //System.out.println("------------------------");
        //System.out.println(cadena.chain);
       //System.out.println(this.chain);
    }
}
