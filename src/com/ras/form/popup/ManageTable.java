package com.ras.form.popup;

import com.ras.database.DatabaseOperation;
import com.ras.entity.Category;
import com.ras.entity.Product;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingWorker;
import raven.toast.Notifications;

public class ManageTable extends javax.swing.JPanel {


    public ManageTable() {
        initComponents();
        comboBoxModelCategory = (DefaultComboBoxModel) comboCategory.getModel();
        setCategoryComboBox();
        initializeCategoryComboBoxRenderer();
        initializeProductComboBoxRenderer();

        comboBoxModelProduct = (DefaultComboBoxModel) comboProduct.getModel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboRegion = new com.ras.swing.combobox.RComboBox();
        lbProduct = new javax.swing.JLabel();
        lbCategory = new javax.swing.JLabel();
        comboCategory = new com.ras.swing.combobox.CComboBox();
        lbAmount = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        comboProduct = new com.ras.swing.combobox.PComboBox();

        lbProduct.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbProduct.setText("Product");

        lbCategory.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbCategory.setText("Category");

        comboCategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoryActionPerformed(evt);
            }
        });

        lbAmount.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbAmount.setText("Amount");

        comboProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboProductActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCategory)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbAmount)
                        .addComponent(lbProduct)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCategory)
                    .addComponent(comboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbProduct)
                    .addComponent(comboProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbAmount)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboProductActionPerformed

    private void comboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoryActionPerformed
        setProductComboBox();
        
    }//GEN-LAST:event_comboCategoryActionPerformed
    
    
    private DefaultComboBoxModel comboBoxModelProduct;
    private DefaultComboBoxModel comboBoxModelCategory;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ras.swing.combobox.CComboBox comboCategory;
    private com.ras.swing.combobox.PComboBox comboProduct;
    private com.ras.swing.combobox.RComboBox comboRegion;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbCategory;
    private javax.swing.JLabel lbProduct;
    private javax.swing.JTextField txtAmount;
    // End of variables declaration//GEN-END:variables


    private void initializeCategoryComboBoxRenderer() {
        getComboCategory().setRenderer(new ListCellRenderer<Category>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Category> list, Category value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                if (value != null) {
                    label.setText(value.categoryName());
                }
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                label.setOpaque(true);
                return label;
            }
        });
    }
    
    
    private void initializeProductComboBoxRenderer() {
        getComboProduct().setRenderer(new ListCellRenderer<Product>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = new JLabel();
                if (value != null) {
                    label.setText(value.getProductName());
                }
                if (isSelected) {
                    label.setBackground(list.getSelectionBackground());
                    label.setForeground(list.getSelectionForeground());
                } else {
                    label.setBackground(list.getBackground());
                    label.setForeground(list.getForeground());
                }
                label.setOpaque(true);
                return label;
            }
        });
    }

    
    private void setCategoryComboBox() {
        
        try {
            SwingWorker<ArrayList<Category>, Void> worker;
            worker = new SwingWorker<ArrayList<Category>, Void>() {

                @Override
                protected ArrayList<Category> doInBackground() throws Exception {
                    comboBoxModelCategory.removeAllElements();

                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    ArrayList<Category> categoryList = databaseOperation.getAllCategorys();
                    for (Category c : categoryList) {
                        System.out.println(c.categoryName());
                        comboBoxModelCategory.addElement(c);
                    }

                    getComboCategory().setModel(comboBoxModelCategory);

                    return categoryList;
                }

                @Override
                protected void done() {
                    try {
                        ArrayList<Category> result = get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "404 - Error");
                    }
                }
            };

            worker.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void setProductComboBox() {
        
        try {
            SwingWorker<ArrayList<Product>, Void> worker;
            worker = new SwingWorker<ArrayList<Product>, Void>() {

                @Override
                protected ArrayList<Product> doInBackground() throws Exception {
                    
                    Category selectedCategory = (Category) getComboCategory().getSelectedItem();
                    
                    comboBoxModelProduct.removeAllElements();

                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    ArrayList<Product> productList = databaseOperation.getAllProductsWithSpecificCategory(selectedCategory);
                    for (Product p : productList) {
                        comboBoxModelProduct.addElement(p);
                    }

                    getComboProduct().setModel(comboBoxModelProduct); // IM SO STUPIDDDDDDDDDD WASTED +30 MIN FOR JUST THIS LINE  

                    return productList;
                }

                @Override
                protected void done() {
                    try {
                        //ArrayList<Product> result = get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "404 - Error");
                    }
                }
            };

            worker.execute();
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public JComboBox<Category> getComboCategory() {
        return comboCategory;
    }

    
    public JComboBox<Product> getComboProduct() {
        return comboProduct;
    }

    public javax.swing.JTextField getTxtAmount() {
        return txtAmount;
    }


    
    



}
