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
        // TODO code application logic here
        /*Robot r = new Robot ();
        Robot r2 = new Robot ();
        Fabrica f = new Fabrica(10);
        f.mutacion();*/
        Terreno t = new Terreno (5);
        t.imprimirTerreno();
    }
    
}
