package com.ras.main;

import com.ras.database.DatabaseOperation;
import raven.drawer.Drawer;
import com.ras.drawer.MyDrawerBuilder;
import com.ras.entity.account.Employee;
import raven.popup.GlassPanePopup;
import com.ras.tabbed.WindowsTabbed;
import javax.swing.SwingWorker;
import raven.toast.Notifications;


public class Main extends javax.swing.JFrame {

    public static Main main;
    private Login loginForm;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        init();
        this.setResizable(false);
    }

    private void init() {
        GlassPanePopup.install(this);
        Notifications.getInstance().setJFrame(this);
        MyDrawerBuilder myDrawerBuilder = new MyDrawerBuilder();
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        WindowsTabbed.getInstance().install(this, body);
        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.BOTTOM_LEFT, "Login is succesfully.");

        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        //login();
        
        
    
        
        
    }
    
    
    
    

    public void login() {
        if (loginForm == null) {
          //  loginForm = new Login();
          loginForm = new Login();

        }
        WindowsTabbed.getInstance().showTabbed(false);
        loginForm.applyComponentOrientation(getComponentOrientation());
        setContentPane(loginForm);
        revalidate();
        repaint();
    }

    public void showMainForm() {
        WindowsTabbed.getInstance().showTabbed(true);
        WindowsTabbed.getInstance().removeAllTabbed();
        setContentPane(body);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 850, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
