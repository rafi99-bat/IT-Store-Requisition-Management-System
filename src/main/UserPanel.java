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
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import message.MessageDialog;

/**
 *
 * @author Rafeed
 */
public class UserPanel extends javax.swing.JFrame {

    private DrawerController drawer;
    private static String branchName;
    private CardLayout cardLayout;

    //private PlaceOrderForm placeOrder;
    //private ViewOrderForm viewOrder;
    //private ViewProductForm viewProduct;
    /**
     * Creates new form AdminPanel
     */
    public UserPanel() {
        initComponents();
    }

    public UserPanel(String name) {
        this();
        setBranchName(name);
        placeOrder = new PlaceOrderForm();
        viewOrder = new ViewOrderForm();
        viewProduct = new ViewProductForm();
        pendingOrders = new PendingOrdersForm();
        titleBar.init(this);
        drawer = Drawer.newDrawer(this)
                .header(new UserHeader(name))
                .space(5)
                .enableScroll(true)
                .addChild(new DrawerItem("Place Order").build())
                .addChild(new DrawerItem("View Orders").build())
                .addChild(new DrawerItem("View Products").build())
                .addChild(new DrawerItem("Pending Orders").build())
                .addFooter(new DrawerItem("Sign out").icon(new ImageIcon(getClass().getResource("/icon/exit.png"))).build())
                .event(new EventDrawer() {
                    @Override
                    public void selected(int i, DrawerItem di) {
                        switch (i) {
                            case 0:
                                showForm(placeOrder);
                                break;
                            case 1:
                                showForm(viewOrder);
                                break;
                            case 2:
                                showForm(viewProduct);
                                break;
                            case 3:
                                showForm(pendingOrders);
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
    
    public final void setBranchName(String name) {
        branchName = name;
    }
    
    public static String getBranchName() {
        return branchName;
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
        placeOrder = new main.PlaceOrderForm();
        viewOrder = new main.ViewOrderForm();
        viewProduct = new main.ViewProductForm();
        pendingOrders = new main.PendingOrdersForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 640));
        setSize(new java.awt.Dimension(1000, 640));

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

        mainMenu.setBackground(new java.awt.Color(255, 255, 255));
        mainMenu.setOpaque(false);
        mainMenu.setLayout(new java.awt.CardLayout());
        mainMenu.add(placeOrder, "card2");

        javax.swing.GroupLayout viewOrderLayout = new javax.swing.GroupLayout(viewOrder);
        viewOrder.setLayout(viewOrderLayout);
        viewOrderLayout.setHorizontalGroup(
            viewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
        );
        viewOrderLayout.setVerticalGroup(
            viewOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        mainMenu.add(viewOrder, "card3");

        javax.swing.GroupLayout viewProductLayout = new javax.swing.GroupLayout(viewProduct);
        viewProduct.setLayout(viewProductLayout);
        viewProductLayout.setHorizontalGroup(
            viewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 979, Short.MAX_VALUE)
        );
        viewProductLayout.setVerticalGroup(
            viewProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        mainMenu.add(viewProduct, "card4");
        mainMenu.add(pendingOrders, "card5");

        jLayeredPane1.setLayer(titleBar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(mainMenu, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(titleBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addComponent(titleBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
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
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UserPanel f = new UserPanel();
                f.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JButton menuButton;
    private main.PendingOrdersForm pendingOrders;
    private main.PlaceOrderForm placeOrder;
    private titlebar.SimpleTitleBar titleBar;
    private main.ViewOrderForm viewOrder;
    private main.ViewProductForm viewProduct;
    // End of variables declaration//GEN-END:variables
}
