/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import drawer.Drawer;
import drawer.DrawerController;
import drawer.DrawerItem;
import drawer.EventDrawer;
import fButton.floating.FloatingButtonUI;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayer;
import message.MessageDialog;

/**
 *
 * @author Rafeed
 */
public class AdminPanel extends javax.swing.JFrame {

    private DrawerController drawer;
    private main.ProductsForm products;
    /**
     * Creates new form AdminPanel
     */
    public AdminPanel() {
        initComponents();
        products = new main.ProductsForm();
        JLayer l = new JLayer(products, new FloatingButtonUI());
        mainMenu.add(l, "card3");
        titleBar.init(this);
        drawer = Drawer.newDrawer(this)
                .header(new AdminHeader())
                .space(5)
                .enableScroll(true)
                .addChild(new DrawerItem("Order Request").build())
                .addChild(new DrawerItem("Resolved Delivery").build())
                .addChild(new DrawerItem("Unresolved Delivery").build())
                .addChild(new DrawerItem("Products").build())
                .addFooter(new DrawerItem("Sign out").icon(new ImageIcon(getClass().getResource("/icon/exit.png"))).build())
                .event(new EventDrawer() {
                    @Override
                    public void selected(int i, DrawerItem di){
                        //System.out.println(i);
                        switch(i) {
                            case 0:
                                showForm(orderRequest);
                                break;
                            case 1:
                                showForm(resolvedDelivery);
                                break;
                            case 2:
                                showForm(unresolvedDelivery);
                                break;
                            case 3:
                                showForm(l);
                                break;
                            case 4:
                                dispose();
                                Login login = new Login();
                                login.setVisible(true);
                                break;
                        }
                    }
                })
                .build();
    }
    
    private void showForm(Component c) {
        mainMenu.removeAll();
        mainMenu.repaint();
        mainMenu.revalidate();
        
        mainMenu.add(c);
        mainMenu.repaint();
        mainMenu.revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        titleBar = new titlebar.SimpleTitleBar();
        jPanel1 = new javax.swing.JPanel();
        menuButton = new javax.swing.JButton();
        mainMenu = new javax.swing.JPanel();
        orderRequest = new main.OrderRequestForm();
        resolvedDelivery = new main.ResolvedDeliveryForm();
        unresolvedDelivery = new main.UnresolvedDeliveryForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setOpaque(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        menuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/menu1.png"))); // NOI18N
        menuButton.setBorder(null);
        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        mainMenu.setLayout(new java.awt.CardLayout());
        mainMenu.add(orderRequest, "card5");
        mainMenu.add(resolvedDelivery, "card3");
        mainMenu.add(unresolvedDelivery, "card4");

        jLayeredPane1.setLayer(titleBar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(mainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        if (drawer.isShow()) {
            drawer.hide();
        } else {
            drawer.show();
        }
    }//GEN-LAST:event_menuButtonActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JButton menuButton;
    private main.OrderRequestForm orderRequest;
    private main.ResolvedDeliveryForm resolvedDelivery;
    private titlebar.SimpleTitleBar titleBar;
    private main.UnresolvedDeliveryForm unresolvedDelivery;
    // End of variables declaration//GEN-END:variables
}
