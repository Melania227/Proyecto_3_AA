/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CLASE MAIN
 */
package proyecto3_robots;

import Model.Fabrica;
import Model.Medio;
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
        Medio m = new Medio(200, 20);
        System.out.println("Generacion original");
        m.start();
//        m.start();
//        m.start();
//        m.start();
        System.out.println("Primera Generacion");
        Fabrica f = m.getFabricaRobots().getNuevaGeneracion();
        f.cruceEntreIndividuosGen();
        f.getNuevasCaracteristicas();
        m.setFabricaRobots(f);
        m.start();
        
        for (int i = 2; i < 10; i++) {
            System.err.println(i+" generacion");
            f = m.getFabricaRobots().getNuevaGeneracion();
            f.cruceEntreIndividuosGen();
            f.getNuevasCaracteristicas();
            m.setFabricaRobots(f);
            m.start();
            
        }


    }
    
}