/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Robot;
import Model.TipoTerreno;
import View.RobotsInfo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author apaol
 */
public abstract class RobotController implements ActionListener{
    Robot robot;
    RobotsInfo ventana;
    int numero;
    int gen;
    
    public RobotController(Robot robot, int numero, int gen){
        this.robot = robot;
        this.ventana = new RobotsInfo();
        this.numero = numero;
        this.gen = gen;
        _init_componentes();
    }

    private void _init_componentes() {
        this.ventana.getPredecesor_1().addActionListener(this);
        this.ventana.getPredecesor_2().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(this.ventana.getPredecesor_1())){
        }
        if(event.getSource().equals(this.ventana.getPredecesor_1())){
            
        }
        
    }

    void start() {
        //Titulo
        this.ventana.setTitle("Robot #"+this.numero+" - "+"Generación #"+this.gen);
        //Estado
        if (this.robot.isFinalizado()){ 
            this.ventana.getEstado_Txt().setText("Finalizado");
        }
        else{
            this.ventana.getEstado_Txt().setText("Inconcluso");
        }
        
        //Bateria
        this.ventana.getBateria_1().setText(String.valueOf(this.robot.getBateria().getSize()));
        this.ventana.getBateria_2().setText(String.valueOf(this.robot.getBateria().getCarga()));
        this.ventana.getBateria_3().setText(String.valueOf(this.robot.getBateria().getCosto()));
        
        //Cámara
        this.ventana.getCam_1().setText(String.valueOf(this.robot.getCamara().getTipo()));
        this.ventana.getCam_2().setText(String.valueOf(this.robot.getCamara().getConsumo()));
        this.ventana.getCam_3().setText(String.valueOf(this.robot.getCamara().getCosto()));
        
        //Motor
        this.ventana.getMotor_1().setText(String.valueOf(this.robot.getMotor().getTipo()));
        this.ventana.getMotor_2().setText(String.valueOf(this.robot.getMotor().getConsumo()));
        this.ventana.getMotor_3().setText(String.valueOf(this.robot.getMotor().getCosto()));
        
        //GENES
        ArrayList<Integer> lista = this.robot.getGenes().getChain();
        for(int num : lista){
            JLabel jLabel4 = new javax.swing.JLabel();
            jLabel4.setFont(new Font("Lucida Fax", Font.BOLD, 18));
            jLabel4.setForeground (Color.white);
            jLabel4.setText(String.valueOf(num));
            this.ventana.getGenes_TXT().add(jLabel4);
        }
        
       //MATRIZ
        JPanel grid = this.ventana.getGrid_Panel();
        boolean bandera = false;
        for (int i = 0; i < this.robot.getTerreno().getSizeTerreno(); i++) {
            for (int j = 0; j < this.robot.getTerreno().getSizeTerreno(); j++) {
                JLabel jLabel3 = new javax.swing.JLabel();
                TipoTerreno t =this.robot.getTerreno().getMatrizTerreno()[i][j];
                
                if(t == TipoTerreno.BLOQUEADO){
                    jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\4_T.png"));
                }
                else{
                    bandera = false;
                    for (int n = 0; n < this.robot.getCasillasVisitadas().size(); n++) {
                        if (this.robot.getCasillasVisitadas().get(n)[0] == i && this.robot.getCasillasVisitadas().get(n)[1] == j){
                            bandera = true;
                            //System.out.println(this.robot.getCasillasVisitadas().get(n)[0]);
                        }
                    }
                    if(null != t)
                        switch (t) {
                        case NORMAL:
                            if(bandera) jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\1_T - copia.png"));
                            else jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\1_T.png"));
                            break;
                        case MODERADO:
                            if(bandera) jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\2_T - copia.png"));
                            else jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\2_T.png"));
                            break;
                        case DIFICIL:
                            if(bandera) jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\3_T - copia.png"));
                            else jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\apaol\\OneDrive\\Documentos\\Analisis De Algoritmos\\Proyecto_3_AA\\Proyecto3_Robots\\src\\Images\\3_T.png"));
                            break;
                        default:
                            break;
                    }
                }
                grid.add(jLabel3);
            }
        }
        this.ventana.setVisible(true);
    }
    
    
}
