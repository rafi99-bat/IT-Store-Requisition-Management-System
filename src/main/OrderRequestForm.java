/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import button.Button;
import cellAdmin.TableActionCellRender;
import cellAdmin.TableActionCellEditor;
import cellAdmin.TableActionEvent;
import combo_suggestion.ComboBoxSuggestion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;

/**
 *
 * @author Rafeed
 */
public class OrderRequestForm extends javax.swing.JPanel {

    /**
     * Creates new form OrderRequestForm
     */
    public OrderRequestForm() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableActionEvent event = (int row) -> {
            JRadioButton pendingBtn = new JRadioButton("Hold", true);
            JRadioButton acceptBtn = new JRadioButton("Accept");
            JRadioButton declineButton = new JRadioButton("Decline");
            ButtonGroup bgroup = new ButtonGroup();
            bgroup.add(pendingBtn);
            bgroup.add(acceptBtn);
            bgroup.add(declineButton);
            JLabel label = new JLabel("What do want to do with this order?");
            label.setFont(new Font("SansSerif", Font.BOLD, 14));
            final JComponent[] inputs = new JComponent[]{
                label,
                pendingBtn,
                acceptBtn,
                declineButton};
            int result = JOptionPane.showConfirmDialog(null, inputs, "Respond Request", JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                if (acceptBtn.isSelected()) {
                    try {
                        int pQuantity, oQuantity;
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
                        String query1 = "SELECT Quantity FROM Product WHERE ModelName = '" + jTable1.getModel().getValueAt(row, 1).toString() + "'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(query1);
                        if (rs.next()) {
                            pQuantity = Integer.parseInt(rs.getString("Quantity"));
                            String query2 = "SELECT Quantity FROM ActiveUserRequest WHERE OrderID = '" + jTable1.getModel().getValueAt(row, 0).toString() + "'";
                            st = connection.createStatement();
                            rs = st.executeQuery(query2);
                            if (rs.next()) {
                                oQuantity = Integer.parseInt(rs.getString("Quantity"));
                                if (oQuantity < pQuantity) {
                                    String query = "UPDATE ActiveUserRequest SET Status = 'accepted' WHERE OrderID = ?";
                                    PreparedStatement pst = connection.prepareStatement(query);
                                    pst.setString(1, jTable1.getModel().getValueAt(row, 0).toString());
                                    pst.executeUpdate();
                                    pQuantity = pQuantity - oQuantity;
                                    String query4 = "UPDATE Product SET Quantity = ? WHERE ModelName = ?";
                                    pst = connection.prepareStatement(query4);
                                    pst.setString(1, Integer.toString(pQuantity));
                                    pst.setString(2, jTable1.getModel().getValueAt(row, 1).toString());
                                    pst.executeUpdate();
                                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                                    model.setRowCount(0);
                                    try {
                                        String query3 = "SELECT * FROM ActiveUserRequest WHERE Status = 'pending'";
                                        st = connection.createStatement();
                                        rs = st.executeQuery(query3);
                                        Object[] row1 = new Object[5];
                                        while (rs.next()) {
                                            row1[0] = rs.getString("OrderID");
                                            row1[1] = rs.getString("Product");
                                            row1[2] = rs.getString("Quantity");
                                            row1[3] = rs.getString("Date");
                                            row1[4] = rs.getString("Branch");
                                            model.addRow(row1);
                                        }
                                    } catch (SQLException ex) {
                                        Logger.getLogger(OrderRequestForm.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Could not accept request. Product stock is lower than requested.", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(OrderRequestForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (declineButton.isSelected()) {
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
                        String query = "UPDATE ActiveUserRequest SET Status = 'declined' WHERE OrderID = ?";
                        PreparedStatement pst = connection.prepareStatement(query);
                        pst.setString(1, jTable1.getModel().getValueAt(row, 0).toString());
                        pst.executeUpdate();
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(OrderRequestForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0);
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
                        String query3 = "SELECT * FROM ActiveUserRequest WHERE Status = 'pending'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(query3);
                        Object[] row1 = new Object[5];
                        while (rs.next()) {
                            row1[0] = rs.getString("OrderID");
                            row1[1] = rs.getString("Product");
                            row1[2] = rs.getString("Quantity");
                            row1[3] = rs.getString("Date");
                            row1[4] = rs.getString("Branch");
                            model.addRow(row1);
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(OrderRequestForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
            String query1 = "SELECT * FROM ActiveUserRequest WHERE Status = 'pending'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            Object[] row = new Object[5];
            while (rs.next()) {
                row[0] = rs.getString("OrderID");
                row[1] = rs.getString("Product");
                row[2] = rs.getString("Quantity");
                row[3] = rs.getString("Date");
                row[4] = rs.getString("Branch");
                model.addRow(row);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OrderRequestForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTable1.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        jTable1.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "OrderID", "Product Name", "Quantity", "Date", "Branch", "Action"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
