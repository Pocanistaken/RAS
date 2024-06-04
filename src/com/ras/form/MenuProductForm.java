package com.ras.form;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.ras.database.DatabaseOperation;
import com.ras.drawer.MyDrawerBuilder;
import com.ras.entity.Menu;
import com.ras.entity.Product;
import com.ras.entity.Region;
import com.ras.entity.Table;
import com.ras.entity.account.Employee;
import com.ras.enums.TableStatus;
import com.ras.exception.SelectedValueEmpty;
import com.ras.form.popup.AddProduct;
import com.ras.form.popup.CreateMenu;
import com.ras.form.popup.CreateTable;
import com.ras.swing.table.CheckBoxTableHeaderRenderer;
import com.ras.swing.table.TableHeaderAlignment;
import javax.swing.JOptionPane;
import com.ras.tabbed.TabbedForm;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
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



public class MenuProductForm extends TabbedForm implements IEntityForm {

    // I made something hope work on first try lol
    private Menu menu;
    
    public MenuProductForm(Menu menu) {
        initComponents();
        init();
        model = (DefaultTableModel) table.getModel();
        this.menu = menu;
        insertData("install");

        menuSubTitle.setText("View " + menu.menuName());
        lbTitle.setText("MENU - " + menu.menuName());

    }
    
    private void init() {
        
        
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
        menuSubTitle = new javax.swing.JLabel();

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel2.setText("Menu Manager");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SELECT", "#", "PRODUCT_NAME", "PRODUCT_DESC", "PRODUCT_PRICE", "PRODUCT_CATEGORY"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, true, false, true
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

        lbTitle.setText("MENU - X");

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

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(475, 475, 475)
                        .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmdDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(cmdAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menuSubTitle))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menuSubTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        
        // Add anti bug checkers - Pocan
        
            AddProduct addProduct = new AddProduct(menu);
            DefaultOption option = new DefaultOption() {
                @Override
                public boolean closeWhenClickOutside() {
                    return true;
                }
            };
            String actions[] = new String[]{"Cancel", "Save"};
            GlassPanePopup.showPopup(new SimplePopupBorder( addProduct, "Add Product", actions, (pc, i) -> {            
                if (i == 1) {
                    // save
                    try {

                        SwingWorker<Void, Void> worker;
                        worker = new SwingWorker<Void, Void>() {

                            @Override
                            protected Void doInBackground() throws Exception {

                                Product selectedProduct = (Product) addProduct.getComboProduct().getSelectedItem();

                                DatabaseOperation databaseOperation = new DatabaseOperation();
 
                                databaseOperation.addProductToMenu(selectedProduct, menu);
                                resetData(model);
                                return null;
                            }

                            @Override
                            protected void done() {
                                try {
                                    insertData("update");
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
    private com.ras.swing.button.ButtonAction cmdAdd;
    private com.ras.swing.button.ButtonAction cmdDelete;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTitle;
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
                        Notifications.getInstance().show(Notifications.Type.INFO, "Menu - (" + menu.menuID() + ") " + menu.menuName() +  " data loading...");
                    }
                    
                    DatabaseOperation databaseOperation = new DatabaseOperation();
                    ArrayList<Product> productList = databaseOperation.getProductListFromMenu(menu);
                    
                    System.out.println(productList.size());

                    int i = 0;
                    for(Product p : productList) {
                        
                        System.out.println(p.getProductID() + " " + p.getProductID());
                        Object[] add = {false, ++i, p.getProductName(), p.getProductDescription(), p.getProductPrice(), p.getProductCategory().categoryName()};
                        model.addRow(add);
                    }

                    return productList;
                }
                
                @Override
                protected void done() {
                    try {
                        ArrayList<Product> productList = get();
                        
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
                            
                            Product product = new Product((int) getSelectedValue(model, i-1, 1));
                            databaseOperation.deleteProductFromMenu(product, menu);
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


    public boolean isSelectedValueContainsTrue() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
