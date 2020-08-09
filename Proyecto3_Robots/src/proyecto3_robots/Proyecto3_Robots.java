/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CLASE MAIN
 */
package proyecto3_robots;

import Controller.Controller;
import Model.Fabrica;
import Model.Medio;
import Model.Robot;
import Model.Terreno;
import Model.TipoTerreno;
import View.RobotsInfo;


/**
 *
 * @author USUARIO
 */
public class Proyecto3_Robots {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Controller controller = new Controller() {};
        controller.start();
       
    }
    
}

