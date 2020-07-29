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
            default:
                res = "0";
        }
        int resultado = Integer.parseInt(res,2);
        //System.out.println(resultado);
        return resultado;
    }
}
