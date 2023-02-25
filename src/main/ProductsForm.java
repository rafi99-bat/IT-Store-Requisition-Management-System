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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;

/**
 *
 * @author Rafeed
 */
public class ProductsForm extends javax.swing.JPanel {

    DBUtils conDB;

    /**
     * Creates new form ProductsForm
     */
    public ProductsForm() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                JLabel categoryField = new JLabel(productTable.getModel().getValueAt(row, 1).toString());
                categoryField.setFont(new Font("SansSerif", Font.BOLD, 14));
                JLabel mNameField = new JLabel(productTable.getModel().getValueAt(row, 0).toString());
                mNameField.setFont(new Font("SansSerif", Font.BOLD, 14));
                JTextField priceField = new JTextField(productTable.getModel().getValueAt(row, 3).toString());
                JTextField quantityField = new JTextField(productTable.getModel().getValueAt(row, 2).toString());
                final JComponent[] inputs = new JComponent[]{
                    new JLabel("Category:"),
                    categoryField,
                    new JLabel("Model Name:"),
                    mNameField,
                    new JLabel("Price:"),
                    priceField,
                    new JLabel("Quantity:"),
                    quantityField,};
                int result = JOptionPane.showConfirmDialog(null, inputs, "Update Product", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    Product p1 = new Product(mNameField.getText(), categoryField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
                    try {
                        String query = "UPDATE Product SET Price = ?, Quantity = ? WHERE ModelName = ?";
                        PreparedStatement pst = conDB.connection.prepareStatement(query);
                        pst.setString(1, Double.toString(p1.getPrice()));
                        pst.setString(2, Integer.toString(p1.getQuantity()));
                        pst.setString(3, p1.getModelName());
                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Updated Successfully");
                        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
                        model.setRowCount(0);
                        showProduct();
                    } catch (SQLException ex) {
                        Logger.getLogger(ProductsForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void onDelete(int row) {
                Product p1 = new Product(productTable.getModel().getValueAt(row, 0).toString(), productTable.getModel().getValueAt(row, 1).toString(), Integer.parseInt(productTable.getModel().getValueAt(row, 2).toString()), Double.parseDouble(productTable.getModel().getValueAt(row, 3).toString()));
                try {
                    String query = "DELETE FROM Product WHERE ModelName = ?";
                    PreparedStatement pst = conDB.connection.prepareStatement(query);
                    pst.setString(1, p1.getModelName());
                    pst.executeUpdate();
                    DefaultTableModel model = (DefaultTableModel) productTable.getModel();
                    model.setRowCount(0);
                    showProduct();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductsForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        productTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
        productTable.getColumnModel().getColumn(4).setCellEditor(new TableActionCellEditor(event));
        conDB = new DBUtils();
        conDB.connectDB();
        showProduct();
    }

    public ArrayList<Product> productList() {
        ArrayList<Product> productList = new ArrayList<>();
        try {
            String query1 = "SELECT * FROM Product";
            Statement st = conDB.connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Product p;
            while (rs.next()) {
                p = new Product(rs.getString("ModelName"), rs.getString("Category"), rs.getInt("Quantity"), rs.getDouble("Price"));
                productList.add(p);
            }
        } catch (SQLException e) {
        }
        return productList;
    }

    public void actionButton() {
        JTextField categoryField = new JTextField();
        JTextField mNameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        final JComponent[] inputs = new JComponent[]{
            new JLabel("Category:"),
            categoryField,
            new JLabel("Model Name:"),
            mNameField,
            new JLabel("Price:"),
            priceField,
            new JLabel("Quantity:"),
            quantityField,};
        int result = JOptionPane.showConfirmDialog(null, inputs, "Add A New Product", JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            Product p1 = new Product(mNameField.getText(), categoryField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
            try {
                String query = "INSERT INTO Product (ModelName, Category, Quantity, Price) values (?, ?, ?, ?)";
                PreparedStatement pst = conDB.connection.prepareStatement(query);
                pst.setString(1, p1.getModelName());
                pst.setString(2, p1.getCategory());
                pst.setString(3, Integer.toString(p1.getQuantity()));
                pst.setString(4, Double.toString(p1.getPrice()));
                pst.executeUpdate();
                DefaultTableModel model = (DefaultTableModel) productTable.getModel();
                model.setRowCount(0);
                showProduct();
            } catch (SQLException ex) {
                Logger.getLogger(ProductsForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }

    public void showProduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String query1 = "SELECT * FROM Product";
            Statement st = conDB.connection.createStatement();
            ResultSet rs = st.executeQuery(query1);
            Product p;
            while (rs.next()) {
                p = new Product(rs.getString("ModelName"), rs.getString("Category"), rs.getInt("Quantity"), rs.getDouble("Price"));
                list.add(p);
            }
        } catch (SQLException e) {
        }
        //ArrayList<Product> list = productList();
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getModelName();
            row[1] = list.get(i).getCategory();
            row[2] = list.get(i).getQuantity();
            row[3] = list.get(i).getPrice();
            model.addRow(row);
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
        productTable = new javax.swing.JTable();

        setOpaque(false);

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Model Name", "Category", "Quantity", "Price", "Action"
            }
        ));
        productTable.setRowHeight(40);
        jScrollPane1.setViewportView(productTable);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable productTable;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
