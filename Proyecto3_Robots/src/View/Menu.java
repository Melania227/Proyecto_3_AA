/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import javax.swing.JButton;
import javax.swing.JSpinner;

/**
 *
 * @author apaol
 */
public class Menu extends javax.swing.JFrame {

    int numero = 0;
    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        Number_I = new javax.swing.JSpinner();
        Iniciar_Boton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(850, 840));
        setMinimumSize(new java.awt.Dimension(850, 840));
        setResizable(false);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Number_I.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        Number_I.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(Number_I, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 440, 130, 40));

        Iniciar_Boton.setBackground(new java.awt.Color(255, 255, 255));
        Iniciar_Boton.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        Iniciar_Boton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Iniciar_Boton.setLabel("Iniciar");
        Iniciar_Boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Iniciar_BotonActionPerformed(evt);
            }
        });
        jPanel3.add(Iniciar_Boton, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 510, 130, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/1.gif"))); // NOI18N
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, 840));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 850, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 840, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Iniciar_BotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Iniciar_BotonActionPerformed
        // TODO add your handling code here:
       this.numero = (Integer) Number_I.getValue();
        
    }//GEN-LAST:event_Iniciar_BotonActionPerformed

 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Iniciar_Boton;
    private javax.swing.JSpinner Number_I;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public JButton getIniciar_Boton() {
        return Iniciar_Boton;
    }

    public JSpinner getNumber_I() {
        return Number_I;
    }
    
    
}
