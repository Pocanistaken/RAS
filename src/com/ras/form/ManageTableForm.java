package com.ras.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.ras.database.DatabaseOperation;
import com.ras.drawer.MyDrawerBuilder;
import com.ras.entity.Bill;
import com.ras.entity.Category;
import com.ras.entity.Menu;
import com.ras.entity.Product;
import com.ras.entity.Region;
import com.ras.entity.Table;
import com.ras.entity.account.Employee;
import com.ras.enums.PaymentType;
import com.ras.enums.TableStatus;
import com.ras.exception.SelectedValueEmpty;
import com.ras.form.popup.AddProduct;
import com.ras.form.popup.CreateMenu;
import com.ras.form.popup.CreateRegion;
import com.ras.form.popup.CreateTable;
import com.ras.form.popup.ManageEditTable;
import com.ras.form.popup.ManageTable;
import com.ras.swing.button.toggle.ToggleAdapter;
import com.ras.swing.button.toggle.ToggleButton;
import com.ras.swing.table.CheckBoxTableHeaderRenderer;
import com.ras.swing.table.TableHeaderAlignment;
import javax.swing.JOptionPane;
import com.ras.tabbed.TabbedForm;
import com.ras.tabbed.WindowsTabbed;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.plaf.ComboBoxUI;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;
import raven.drawer.Drawer;
import raven.popup.DefaultOption;
import raven.popup.GlassPanePopup;
import raven.popup.component.SimplePopupBorder;
import raven.toast.Notifications;



public class ManageTableForm extends TabbedForm implements IEntityForm {

    private Table tableEntity;
    
    public ManageTableForm(Table tableEntity) {
        initComponents();
        init();
        model = (DefaultTableModel) table.getModel();
        this.tableEntity = tableEntity;
        
        insertData("install");

        menuSubTitle.setText("View " + tableEntity.getTableName());
        lbTitle.setText("TABLE - " + tableEntity.getTableName());
        
        
        cashToggleButton.addEventToggleSelected(new ToggleAdapter() {
            @Override
            public void onSelected(boolean selected) {
                
                if (creditcardToggleButton.isSelected()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.BOTTOM_LEFT, "You can only select one payment type.");
                }
            }
        });
        creditcardToggleButton.addEventToggleSelected(new ToggleAdapter() {
            @Override
            public void onSelected(boolean selected) {
                
                if (cashToggleButton.isSelected()) {
                    Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.BOTTOM_LEFT, "You can only select one payment type.");
                }
            }
        });
        
        
        
        
    }
    
    private void init() {
        
        
        cmdConfirm.putClientProperty("JComponent.roundRect", true);

        cmdCancel.putClientProperty("JComponent.roundRect", true);

        
        
        
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:25;"
                + "background:$Table.background");
        
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE, ""
                + "height:30;"
                + "hoverBackground:null;"
                + "pressedBackground:null;"
                + "separatorColor:$TableHeader.background;"
                + "font:bold;");
        
        
        table.putClientProperty(FlatClientProperties.STYLE, ""
                + "rowHeight:30;"
                + "showHorizontalLines:true;"
                + "intercellSpacing:0,1;" // it shows horizontal lines when clicking the value. 0 for vertical 1 for horinzotal
                + "cellFocusColor:$TableHeader.hoverBackground;"
                + "selectionBackground:$TableHeader.hoverBackground;"
                + "selectionForeground:$Table.foreground;");
        
        
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "trackInsets:3,3,3,3;"
                + "thumbInsets:3,3,3,3;"
                + "background:$Table.background;");
        
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
        + "font:bold +5;");
        
        txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search...");
        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon("com/ras/svg/search.svg"));
        txtSearch.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
        
      table.getColumnModel().getColumn(0).setHeaderRenderer(new CheckBoxTableHeaderRenderer(table, 0));
      table.getTableHeader().setDefaultRenderer(new TableHeaderAlignment(table));

    }
    
    
    
    
    
    
    private void updateTotalLabel() {
        // 5 price
        /// 6 amount

        double total = 0;
        
        for(int i = model.getRowCount() - 1; i >=0; i--) {
            double price = (double) getSelectedValue(model, i, 5);
            int amount = (int) getSelectedValue(model, i, 6);
            total += price * amount;
        }
        lbTotal.setText(String.valueOf(total) + "$");
    }
    
    
    private double getTotal() {
        String tempString = lbTotal.getText();
        tempString = tempString.replaceAll("\\$", "");
        System.out.println("debug-> " + tempString);
        return Double.valueOf(tempString);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        scroll = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        lbTitle = new javax.swing.JLabel();
        cmdAdd = new com.ras.swing.button.ButtonAction();
        cmdDelete = new com.ras.swing.button.ButtonAction();
        cmdEdit = new com.ras.swing.button.ButtonAction();
        lbTotalSign = new javax.swing.JLabel();
        lbTotal = new javax.swing.JLabel();
        lbTotalSign2 = new javax.swing.JLabel();
        cashToggleButton = new com.ras.swing.button.toggle.ToggleButton();
        lbTotalSign1 = new javax.swing.JLabel();
        creditcardToggleButton = new com.ras.swing.button.toggle.ToggleButton();
        cashToggleButton1 = new com.ras.swing.button.toggle.ToggleButton();
        cashToggleButton2 = new com.ras.swing.button.toggle.ToggleButton();
        cmdConfirm = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        menuSubTitle = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel2.setText("Manage Table");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SELECT", "ID", "PRODUCT_NAME", "PRODUCT_DESC", "PRODUCT_CATEGORY", "PRODUCT_PRICE", "AMOUNT", "PRICE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        scroll.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setMaxWidth(50);
            table.getColumnModel().getColumn(1).setMaxWidth(50);
        }

        lbTitle.setText("TABLE - X");

        cmdAdd.setText("Add");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });

        cmdDelete.setText("Delete");
        cmdDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDeleteActionPerformed(evt);
            }
        });

        cmdEdit.setText("Edit");
        cmdEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditActionPerformed(evt);
            }
        });

        lbTotalSign.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        lbTotalSign.setText("Total =");

        lbTotal.setFont(new java.awt.Font("Helvetica Neue", 1, 48)); // NOI18N
        lbTotal.setText("0");

        lbTotalSign2.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbTotalSign2.setText("Cash");

        cashToggleButton.setForeground(new java.awt.Color(50, 205, 50));

        lbTotalSign1.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        lbTotalSign1.setText("Credit Card");

        creditcardToggleButton.setForeground(new java.awt.Color(31, 31, 31));

        cashToggleButton1.setForeground(new java.awt.Color(31, 31, 31));

        cashToggleButton2.setForeground(new java.awt.Color(50, 205, 50));
        cashToggleButton1.add(cashToggleButton2);

        creditcardToggleButton.add(cashToggleButton1);

        cmdConfirm.setBackground(new java.awt.Color(50, 205, 50));
        cmdConfirm.setText("Confirm");
        cmdConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdConfirmActionPerformed(evt);
            }
        });

        cmdCancel.setBackground(new java.awt.Color(255, 51, 51));
        cmdCancel.setText("Cancel");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(385, 385, 385)
                                .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTitle))
                        .addContainerGap(79, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addComponent(lbTotalSign2)
                                        .addGap(30, 30, 30)
                                        .addComponent(cashToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(lbTotalSign)
                                        .addGap(18, 18, 18)
                                        .addComponent(lbTotal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(lbTotalSign1)
                                        .addGap(30, 30, 30)
                                        .addComponent(creditcardToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(cmdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cashToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lbTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTotalSign2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTotalSign1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(creditcardToggleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotalSign, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        menuSubTitle.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        menuSubTitle.setText("View x");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuSubTitle)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(154, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        
        // Add anti bug checkers - Pocan
        
            ManageTable manageTable = new ManageTable(tableEntity);
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };
            String actions[] = new String[]{"Cancel", "Save"};
            GlassPanePopup.showPopup(new SimplePopupBorder( manageTable, "Add Product", actions, (pc, i) -> {            
                if (i == 1) {
                    // save
                    try {

                        SwingWorker<Void, Void> worker;
                        worker = new SwingWorker<Void, Void>() {

                            @Override
                            protected Void doInBackground() throws Exception {
                                
                                Category selectedCategory = (Category) manageTable.getComboCategory().getSelectedItem();
                                Product selectedProduct = (Product) manageTable.getComboProduct().getSelectedItem();
                                int amount = Integer.valueOf(manageTable.getTxtAmount().getText());

                                DatabaseOperation databaseOperation = new DatabaseOperation();
                                
                                
                                databaseOperation.addProductToTable(selectedProduct, tableEntity, amount);
                                resetData(model);
                                return null;
                            }

                            @Override
                            protected void done() {
                                try {
                                    insertData("update");
                                    updateTotalLabel();
                                    pc.closePopup();
                                    Notifications.getInstance().show(Notifications.Type.SUCCESS, "Product succesfully added.");

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
                } else {
                    pc.closePopup();
                }
            }), option);

    }//GEN-LAST:event_cmdAddActionPerformed

    private void cmdDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDeleteActionPerformed
            cmdDeleteAction(model, this.getClass());
    }//GEN-LAST:event_cmdDeleteActionPerformed

    private void cmdEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditActionPerformed
        cmdEditAction();
        
        
        

    }//GEN-LAST:event_cmdEditActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmdCancelActionPerformed

    private void cmdConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdConfirmActionPerformed

        if (creditcardToggleButton.isSelected() && cashToggleButton.isSelected()) {
            MessageAlerts.getInstance().showMessage("ERROR", "Please select only one payment type.", MessageAlerts.MessageType.ERROR); 
            return;
        }
        if (!creditcardToggleButton.isSelected() && !cashToggleButton.isSelected()) {
            MessageAlerts.getInstance().showMessage("ERROR", "Please select a payment type.", MessageAlerts.MessageType.ERROR); 
        }
        else {
                DatabaseOperation databaseOperation = new DatabaseOperation();

                Bill bill = new Bill(databaseOperation.increaseID("bill"), tableEntity.getTableID(), getTotal(), getPaymentType(), Instant.now());

                databaseOperation.addBillToDatabase(bill);

                ArrayList<Product> productList = databaseOperation.getProductListFromTable(tableEntity);

                for(Product p : productList) {
                    int productAmount = databaseOperation.getProductAmountFromTable(p, tableEntity);
                    System.out.println(p.getProductCategory());
                    databaseOperation.addProductToBill(p, bill, productAmount);
                }


                deleteAllDatas();
                
                MessageAlerts.getInstance().showMessage("SUCCESS", "Bill successfully created.", MessageAlerts.MessageType.SUCCESS); 


        
        }
    }//GEN-LAST:event_cmdConfirmActionPerformed

    
    private void dodo() {
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    DatabaseOperation databaseOperation = new DatabaseOperation();

                    System.out.println(getTotal() + " " + getPaymentType());
                    Bill bill = new Bill(databaseOperation.increaseID("bill"), tableEntity.getTableID(), getTotal(), getPaymentType(), Instant.now());
                                        
                    databaseOperation.addBillToDatabase(bill);
                    
                    ArrayList<Product> productList = databaseOperation.getProductListFromTable(tableEntity);
                    
                    for(Product p : productList) {
                        int productAmount = databaseOperation.getProductAmountFromTable(p, tableEntity);
                        System.out.println(p.getProductCategory());
                        databaseOperation.addProductToBill(p, bill, productAmount);
                    }
                    
                    
                    deleteAllDatas();


                    
                    return null;
                }

                @Override
                protected void done() {
                    try {
                MessageAlerts.getInstance().showMessage("SUCCESS", "Bill successfully created.", MessageAlerts.MessageType.SUCCESS); 

                    } catch (Exception e) {
                        e.printStackTrace();
                        Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "404 - Error");
                    }
                }
            };

            worker.execute();
            
        }
        
        
        
    
    
    
    private PaymentType getPaymentType() {
        if (creditcardToggleButton.isSelected() && !cashToggleButton.isSelected()) {
            return PaymentType.CREDIT_CARD;
        }
        if (cashToggleButton.isSelected() && !creditcardToggleButton.isSelected()) {
            return PaymentType.CASH;
        }
        return null;
    }
    
    private void cmdEditAction() {
        
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                
                
                ArrayList<Boolean> selectedList = getSelectedBooleanValues(model);
                if (getSelectedValueAmount(model) == 1) {
                    if (isSelectedValueContainsTrue(selectedList)) {
                        int i = 0;
                        for(Boolean b : selectedList) {
                            i++;
                            if (b) {
                                DatabaseOperation databaseOperation = new DatabaseOperation();

                                String productName = (String) getSelectedValue(model, i-1, 2);
                                String productDescription = (String) getSelectedValue(model, i-1, 3);
                                Category productCategory = (Category) getSelectedValue(model, i-1, 4);

                                double price = (double) getSelectedValue(model, i-1, 5);
                                int amount = (int) getSelectedValue(model, i-1, 6);
                                int productID = databaseOperation.getProductIDFromAllFeatures(productName, productDescription, price, productCategory.categoryID());
                                
                                
                                Product product = new Product(productName, productDescription, price, productCategory, productID);
                                
                                ManageEditTable managaEditTable = new ManageEditTable(tableEntity, product, amount);
                                        DefaultOption option = new DefaultOption() {
                                            @Override
                                            public boolean closeWhenClickOutside() {
                                                return true;
                                            }
                                        };
                                        String actions[] = new String[]{"Cancel", "Save"};
                                        GlassPanePopup.showPopup(new SimplePopupBorder(managaEditTable, "Edit Added Product", actions, (pc, c) -> {            
                                            if (c == 1) {
                                                
                                                int selectedAmount = (int) Integer.valueOf(managaEditTable.getTxtAmount().getText());
                                                databaseOperation.updateProductAmountInTable(selectedAmount, tableEntity.getTableID(), productID);
                                                Notifications.getInstance().show(Notifications.Type.SUCCESS, "Table product item succesfully edited.");
                                                insertData("update");


                                                pc.closePopup();
                                            
                                            }else {
                                                pc.closePopup();
                                                
                                                }
                                        }), option);

                                
                            }   
                        }
                    }
                }
                else { 
                    insertData("update");

                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "You must select only 1 value.");

                }

                resetData(model);
                return null;
            }

            @Override
            protected void done() {
                try {
                 //   insertData("update");

                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "404 - Error");
                }
            }
        };


        worker.execute();
        
        
        
        
        
        
        
        
    }
    
    @Override
    public boolean formClose() {
        int opt = JOptionPane.showConfirmDialog(this, "Data not save do you want to close ?", "Close", JOptionPane.YES_NO_OPTION);
        return opt == JOptionPane.YES_OPTION;
    }

    @Override
    public void formOpen() {
        System.out.println("Form open");
    }

    private DefaultTableModel model;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ras.swing.button.toggle.ToggleButton cashToggleButton;
    private com.ras.swing.button.toggle.ToggleButton cashToggleButton1;
    private com.ras.swing.button.toggle.ToggleButton cashToggleButton2;
    private com.ras.swing.button.ButtonAction cmdAdd;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdConfirm;
    private com.ras.swing.button.ButtonAction cmdDelete;
    private com.ras.swing.button.ButtonAction cmdEdit;
    private com.ras.swing.button.toggle.ToggleButton creditcardToggleButton;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lbTotal;
    private javax.swing.JLabel lbTotalSign;
    private javax.swing.JLabel lbTotalSign1;
    private javax.swing.JLabel lbTotalSign2;
    private javax.swing.JLabel menuSubTitle;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertData(String type) {
        
        try {                                    
            SwingWorker<ArrayList<Product>, Void> worker;
            worker = new SwingWorker<ArrayList<Product>, Void>() {

                @Override
                protected ArrayList<Product> doInBackground() throws Exception {
                    
                    
                    if (type.equals("install")) {
                        Notifications.getInstance().show(Notifications.Type.INFO, "Table - (" + tableEntity.getTableID() + ") " + tableEntity.getTableName()+  " data loading...");
                    }
                    
                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    
                    
                    ArrayList<Product> productList = databaseOperation.getProductListFromTable(tableEntity);
                                        
                   
                    int i = 0;
                    for(Product p : productList) {
                        
                        int productAmount = databaseOperation.getProductAmountFromTable(p, tableEntity);
                        System.out.println(productAmount);
                        double price = productAmount * p.getProductPrice();                        
                        Object[] add = {false, ++i, p.getProductName(), p.getProductDescription(), p.getProductCategory(), p.getProductPrice(), productAmount, price};
                        model.addRow(add);
                    }

                    return productList;
                }
                
                @Override
                protected void done() {
                    try {
                        ArrayList<Product> productList = get();
                        updateTotalLabel();
                        
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
    
    
    @Override
    public void deleteSelectedValues() {
        try {                                    
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
            int counter = 0;
                @Override
                protected Void doInBackground() throws Exception {
                    ArrayList<Boolean> selectedValueList = getSelectedBooleanValues(model);
                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    int i = 0;
                    for(Boolean b : selectedValueList) {
                        i++;
                        if (b) {
                            
                            String productName = (String) getSelectedValue(model, i-1, 2);
                            String productDescription = (String) getSelectedValue(model, i-1, 3);
                            Category productCategory = (Category) getSelectedValue(model, i-1, 4);

                            double price = (double) getSelectedValue(model, i-1, 5);
                            int amount = (int) getSelectedValue(model, i-1, 6);
                            int productID = databaseOperation.getProductIDFromAllFeatures(productName, productDescription, price, productCategory.categoryID());

                                
                            Product product = new Product(productName, productDescription, price, productCategory, productID);
                                                       
                            
                            databaseOperation.deleteProductFromTable(product, tableEntity);
                            counter++;
                        }   
                    }
                    resetData(model);
                    
                    insertData("update");
                    
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        updateTotalLabel();
                        if (counter == 1) {
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Product has been deleted");
                        }
                        else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, "Products has been deleted");
                        }

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
    
    
    private void deleteAllDatas() {
        
        try {                                    
            SwingWorker<Void, Void> worker;
            worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    ArrayList<Boolean> selectedValueList = getSelectedBooleanValues(model);
                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    int i = 0;
                    for(Boolean b : selectedValueList) {
                        i++;
                            
                        String productName = (String) getSelectedValue(model, i-1, 2);
                        String productDescription = (String) getSelectedValue(model, i-1, 3);
                        Category productCategory = (Category) getSelectedValue(model, i-1, 4);

                        double price = (double) getSelectedValue(model, i-1, 5);
                        int amount = (int) getSelectedValue(model, i-1, 6);
                        int productID = databaseOperation.getProductIDFromAllFeatures(productName, productDescription, price, productCategory.categoryID());


                        Product product = new Product(productName, productDescription, price, productCategory, productID);

                        databaseOperation.deleteProductFromTable(product, tableEntity);
                    }
                    resetData(model);
                    
                    insertData("update");
                    
                    return null;
                }

                @Override
                protected void done() {
                    try {
                        updateTotalLabel();

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
    
    
    
    
    
    


    public boolean isSelectedValueContainsTrue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
