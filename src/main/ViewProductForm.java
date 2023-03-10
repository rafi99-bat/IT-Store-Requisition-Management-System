/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;

/**
 *
 * @author Rafeed
 */
public class ViewProductForm extends javax.swing.JPanel {

    DBUtils conDB;
    
    /**
     * Creates new form OrderRequestForm
     */
    public ViewProductForm() {
        initComponents();
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        conDB = new DBUtils();
        conDB.connectDB();
        showProduct();
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
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getModelName();
            row[1] = list.get(i).getCategory();
            row[2] = list.get(i).getPrice();
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
        jTable1 = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Model Name", "Category", "Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private table.TableScrollButton tableScrollButton1;
    // End of variables declaration//GEN-END:variables
}
