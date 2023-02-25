/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import cell.TableActionCellEditor;
import cell.TableActionCellRender;
import cell.TableActionEvent;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;

/**
 *
 * @author Rafeed
 */
public class PendingOrdersForm extends javax.swing.JPanel {

    double price;

    /**
     * Creates new form OrderRequestForm
     */
    public PendingOrdersForm() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                String orderID = pendingTable.getModel().getValueAt(row, 0).toString();
                JLabel orderIDField = new JLabel(orderID);
                orderIDField.setFont(new Font("SansSerif", Font.PLAIN, 14));
                JLabel pNameField = new JLabel();
                pNameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
                pNameField.setText(pendingTable.getModel().getValueAt(row, 1).toString());
                JSpinner quantityField = new JSpinner();
                quantityField.setValue(Integer.parseInt(pendingTable.getModel().getValueAt(row, 2).toString()));
                final JComponent[] inputs = new JComponent[]{
                    new JLabel("Order ID: "),
                    orderIDField,
                    new JLabel("Product Name:"),
                    pNameField,
                    new JLabel("Change Quantity:"),
                    quantityField};
                int result = JOptionPane.showConfirmDialog(null, inputs, "Update Quantity", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    try {
                        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                        Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
                        String query = "UPDATE ActiveUserRequest SET Price = (Price / Quantity) * ?, Quantity = ? WHERE OrderID = ?";
                        PreparedStatement pst = connection.prepareStatement(query);
                        pst.setString(1, quantityField.getValue().toString());
                        pst.setString(2, quantityField.getValue().toString());
                        pst.setString(3, orderID);
                        pst.executeUpdate();
                        DefaultTableModel model = (DefaultTableModel) pendingTable.getModel();
                        model.setRowCount(0);
                        try {
                            String query1 = "SELECT * FROM ActiveUserRequest WHERE Branch = " + "'" + UserPanel.getBranchName() + "' " + "AND Status = 'pending'";
                            Statement st = connection.createStatement();
                            ResultSet rs = st.executeQuery(query1);
                            Object[] data = new Object[5];
                            while (rs.next()) {
                                data[0] = rs.getString("OrderID");
                                data[1] = rs.getString("Product");
                                data[2] = rs.getString("Quantity");
                                data[3] = rs.getString("Price");
                                data[4] = rs.getString("Date");
                                model.addRow(data);
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(PendingOrdersForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (ClassNotFoundException | SQLException ex) {
                        Logger.getLogger(PendingOrdersForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void onDelete(int row) {
                String orderID = pendingTable.getModel().getValueAt(row, 0).toString();
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
                    String query = "DELETE FROM ActiveUserRequest WHERE OrderID = ?";
                    PreparedStatement pst = connection.prepareStatement(query);
                    pst.setString(1, orderID);
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) pendingTable.getModel();
                    model.setRowCount(0);
                    try {
                        String query1 = "SELECT * FROM ActiveUserRequest WHERE Branch = " + "'" + UserPanel.getBranchName() + "' " + "AND Status = 'pending'";
                        Statement st = connection.createStatement();
                        ResultSet rs = st.executeQuery(query1);
                        Object[] data = new Object[5];
                        while (rs.next()) {
                            data[0] = rs.getString("OrderID");
                            data[1] = rs.getString("Product");
                            data[2] = rs.getString("Quantity");
                            data[3] = rs.getString("Price");
                            data[4] = rs.getString("Date");
                            model.addRow(data);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(PendingOrdersForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Logger.getLogger(PendingOrdersForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        DefaultTableModel model = (DefaultTableModel) pendingTable.getModel();
        pendingTable.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
        pendingTable.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ProjectDB;selectMethod=cursor", "sa", "123456");
            String query = "SELECT * FROM ActiveUserRequest WHERE Branch = " + "'" + UserPanel.getBranchName() + "' " + "AND Status = 'pending'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            Object[] data = new Object[5];
            while (rs.next()) {
                data[0] = rs.getString("OrderID");
                data[1] = rs.getString("Product");
                data[2] = rs.getString("Quantity");
                data[3] = rs.getString("Price");
                data[4] = rs.getString("Date");
                model.addRow(data);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PendingOrdersForm.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        pendingTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        pendingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Model Name", "Quantity", "Price", "Date", "Action"
            }
        ));
        pendingTable.setRowHeight(40);
        jScrollPane1.setViewportView(pendingTable);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable pendingTable;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
