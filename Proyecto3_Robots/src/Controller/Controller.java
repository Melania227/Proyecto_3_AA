/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Fabrica;
import Model.Medio;
import Model.Robot;
import Model.Terreno;
import View.Menu;
import View.MenuGeneraciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author apaol
 */
public abstract class Controller implements ActionListener{
    private Menu menu;
    private MenuGeneraciones menuG;
    private Medio m;
    int generacion_;

    public Controller(){
        this.menu = new Menu();
        this.menuG = new MenuGeneraciones();
        m = null;
        _init_componentes();
    }
    
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void start() {
        this.menu.setVisible(true);
        
    }
    
    public void Menu(int cantidad){
        this.m = new Medio(cantidad, 20);
        this.m.start();
        for (int i = 1; i < 100; i++) {
            this.m.getNewGeneration();
            this.m.start();
            //this.m.variacionEntreGeneraciones();
        }
        String s = "";
        for (int i = 0; i < this.m.getHistorialGeneraciones().size(); i++) {
           // s += "Generación #" +i+":\n";
           // s += "  Cantidad de robots:" +this.m.getHistorialGeneraciones().get(i).getPoblacion().size()+":\n";
        }        
        this.menuG.getInfo_Txt().setText(s);
        int n = this.m.getHistorialGeneraciones().size()-1;
        SpinnerModel model = new SpinnerNumberModel(0, 0, n, 1);
        this.menuG.getGeneraciones().setModel(model);
        this.menuG.setVisible(true);
    }

    public void MenuG(int generacion){
        this.generacion_ = generacion;
        int n = this.m.getHistorialGeneraciones().get(generacion).getPoblacion().size()-1;
        if(n<0) n=0;
        SpinnerModel model = new SpinnerNumberModel(0, 0, n, 1);
        this.menuG.getRobot().setModel(model);
        this.menuG.getRobot().setEnabled(true);
        this.menuG.getBuscar().setEnabled(true);
        
    }
    
    private void _init_componentes() {
        this.menu.getIniciar_Boton().addActionListener(this);
        this.menuG.getBuscar().addActionListener(this);
        this.menuG.getSeleccionar().addActionListener(this);
        this.menuG.getBack_btn().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(this.menu.getIniciar_Boton())){
            Menu((Integer) this.menu.getNumber_I().getValue());
            menu.dispose();
        }
        if(event.getSource().equals(this.menuG.getSeleccionar())){
            MenuG((Integer) this.menuG.getGeneraciones().getValue());
        }
        if(event.getSource().equals(this.menuG.getBuscar())){
            int i = (Integer) this.menuG.getRobot().getValue();
            Robot robot = this.m.getHistorialGeneraciones().get(this.generacion_).getPoblacion().get(i);
            RobotController robotContr = new RobotController(robot, i, generacion_, this.m.getHistorialGeneraciones()) {};
            robotContr.start();
        }
        if(event.getSource().equals(this.menuG.getBack_btn())){
            this.m = null;
            this.menuG.dispose();
            Controller controller = new Controller() {};
            controller.start();
        }
    }
    
}
