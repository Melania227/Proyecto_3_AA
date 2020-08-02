/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3_robots;

import Model.Fabrica;
import Model.Robot;
import Model.Terreno;

/**
 *
 * @author USUARIO
 */
public class Proyecto3_Robots {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Terreno t = new Terreno (5);
        Robot r = new Robot (t);
        t.imprimirTerreno();
        for (int i = 0; i < 1000; i++) {
            r.moverEnTerreno();
        }
        int [][] tableroTemp = new int [t.getSizeTerreno()][t.getSizeTerreno()];
        tableroTemp[r.getPos()[0]][r.getPos()[1]] = 7;
        String strBoard = "";
        for (int i = 0; i < tableroTemp.length; i++) {
            String line = "";
            for (int j = 0; j < tableroTemp[0].length; j++) {
                line+=tableroTemp[i][j]+" ";
            }
            strBoard+=line+"\n";
        }
        System.out.println(strBoard);
    }
    
}
