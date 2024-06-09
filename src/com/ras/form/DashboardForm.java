package com.ras.form;

import com.ras.database.DatabaseOperation;
import com.ras.entity.Table;
import com.ras.entity.model.DailyEarnModelData;
import com.ras.enums.TableStatus;
import static com.ras.form.TableManagerForm.calculateTableLayout;
import com.ras.swing.curvelinechart.chart.ModelChart;
import javax.swing.JOptionPane;
import com.ras.tabbed.TabbedForm;
import com.ras.tabbed.WindowsTabbed;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import raven.toast.Notifications;



public class DashboardForm extends TabbedForm {
    
    private final int percentage = 100;
    
    public DashboardForm() {
        initComponents();
        chart.setTitle("Daily Earn");
        chart.addLegend("Money", Color.decode("#7b4397"), Color.decode("#dc2430"));
        init();
    }

    private void init() {

        
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                
                DatabaseOperation databaseOperation = new DatabaseOperation();
                ArrayList<DailyEarnModelData> list = databaseOperation.getDashboardData();
                Collections.reverse(list);

                for (int i = list.size() - 1 ; i>= 0; i--) {
                    DailyEarnModelData modelData = list.get(i);
                    chart.addData(new ModelChart(modelData.getDate(), new double[]{modelData.getDailyTotal()}));
                }
                chart.start();
                
                
                int todayEarn = (int) list.get(0).getDailyTotal();
                int dailyCustomer = databaseOperation.getTodayInvoiceCount();
                int instantTableCount = databaseOperation.getTotalInstantTableCount();
                
                
                todayEarnLb.setText(String.valueOf(list.get(0).getDailyTotal()) + "$");
                dailyCustomerLb.setText(String.valueOf(dailyCustomer));
                instantTableCountLb.setText(String.valueOf(instantTableCount));

                
                int enBuyuk = Math.max(Math.max(todayEarn, dailyCustomer), instantTableCount);
                int enKucuk = Math.min(Math.min(todayEarn, dailyCustomer), instantTableCount);

                Integer[] sayilar = {todayEarn, dailyCustomer, instantTableCount};
                Arrays.sort(sayilar);
                int ortanca = sayilar[1];

                
                int enBuyukProgress = percentage;
                int enKucukProgress = ((percentage * enKucuk) / enBuyuk);
                int ortancaProgress = ((percentage * ortanca) / enBuyuk);

                
                if (todayEarn == enBuyuk) {
                    spinnerTodayEarn.setValue(enBuyuk);
                }
                if (todayEarn == ortanca) {
                    spinnerTodayEarn.setValue(ortancaProgress);
                }
                if (todayEarn == enKucuk) {
                    spinnerTodayEarn.setValue(enKucukProgress);
                }
                
                if (dailyCustomer == enBuyuk) {
                    spinnerDailyCustomer.setValue(enBuyukProgress);
                }
                if (dailyCustomer == ortanca) {
                    spinnerDailyCustomer.setValue(ortancaProgress);
                }           
                if (dailyCustomer == enKucuk) {
                    spinnerDailyCustomer.setValue(enKucukProgress);
                }
                
                if (instantTableCount == enBuyuk) {
                    spinnerInsantTable.setValue(enBuyukProgress);
                }  

                if (instantTableCount == ortanca) {
                    spinnerInsantTable.setValue(ortancaProgress);
                } 
                if (instantTableCount == enKucuk) {
                    spinnerInsantTable.setValue(enKucukProgress);
                } 
                


                return null;
            }

            @Override
            protected void done() {
                try {
                    //UIManager.put("ProgressBar.cycleTime", 1000);
                    //UIManager.put("ProgressBar.repaintInterval", 15);
                    
                    
                    //spinnerInsantTable.setIndeterminate(false);
                    //spinnerTodayEarn.setIndeterminate(false);
                    //spinnerDailyCustomer.setIndeterminate(false);
                    

                } catch (Exception e) {
                    e.printStackTrace();
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "404 - Error");
                }
            }
        };
        
        worker.execute();
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        kButton1 = new com.k33ptoo.components.KButton();
        panelchart = new javax.swing.JPanel();
        panelShadow = new com.ras.swing.curvelinechart.panel.PanelShadow();
        chart = new com.ras.swing.curvelinechart.chart.CurveLineChart();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        incomecustomersPanel = new javax.swing.JPanel();
        spinnerInsantTable = new com.ras.swing.spinner.SpinnerProgress();
        jLabel5 = new javax.swing.JLabel();
        instantTableCountLb = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        spinnerTodayEarn = new com.ras.swing.spinner.SpinnerProgress();
        jLabel7 = new javax.swing.JLabel();
        todayEarnLb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        spinnerDailyCustomer = new com.ras.swing.spinner.SpinnerProgress();
        jLabel9 = new javax.swing.JLabel();
        dailyCustomerLb = new javax.swing.JLabel();

        kButton1.setText("kButton1");

        panelShadow.setBackground(new java.awt.Color(40, 40, 40));
        panelShadow.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelShadow.setColorGradient(new java.awt.Color(30, 30, 30));

        chart.setForeground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout panelShadowLayout = new javax.swing.GroupLayout(panelShadow);
        panelShadow.setLayout(panelShadowLayout);
        panelShadowLayout.setHorizontalGroup(
            panelShadowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 886, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadowLayout.setVerticalGroup(
            panelShadowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelchartLayout = new javax.swing.GroupLayout(panelchart);
        panelchart.setLayout(panelchartLayout);
        panelchartLayout.setHorizontalGroup(
            panelchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelchartLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelShadow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        panelchartLayout.setVerticalGroup(
            panelchartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelchartLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(panelShadow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel2.setText("View Dashboard");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel3.setText("Dashboard");
        jLabel3.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(102, 102, 102)));

        incomecustomersPanel.setBackground(new java.awt.Color(40, 40, 40));
        incomecustomersPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        spinnerInsantTable.setValue(50);
        spinnerInsantTable.setIndeterminate(true);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Instant Table");

        instantTableCountLb.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        instantTableCountLb.setForeground(new java.awt.Color(204, 204, 204));
        instantTableCountLb.setText("      0");

        javax.swing.GroupLayout incomecustomersPanelLayout = new javax.swing.GroupLayout(incomecustomersPanel);
        incomecustomersPanel.setLayout(incomecustomersPanelLayout);
        incomecustomersPanelLayout.setHorizontalGroup(
            incomecustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(incomecustomersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spinnerInsantTable, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(incomecustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(instantTableCountLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        incomecustomersPanelLayout.setVerticalGroup(
            incomecustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incomecustomersPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(incomecustomersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(incomecustomersPanelLayout.createSequentialGroup()
                        .addComponent(spinnerInsantTable, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, incomecustomersPanelLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(12, 12, 12)
                        .addComponent(instantTableCountLb)
                        .addGap(51, 51, 51))))
        );

        jPanel2.setBackground(new java.awt.Color(40, 40, 40));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        spinnerTodayEarn.setForeground(new java.awt.Color(255, 51, 51));
        spinnerTodayEarn.setValue(50);
        spinnerTodayEarn.setIndeterminate(true);

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Today Earn");

        todayEarnLb.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        todayEarnLb.setForeground(new java.awt.Color(204, 204, 204));
        todayEarnLb.setText("   0$");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spinnerTodayEarn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(todayEarnLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addComponent(todayEarnLb))
                    .addComponent(spinnerTodayEarn, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel3.setBackground(new java.awt.Color(40, 40, 40));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 0, 0)));

        spinnerDailyCustomer.setForeground(new java.awt.Color(0, 255, 102));
        spinnerDailyCustomer.setValue(50);
        spinnerDailyCustomer.setIndeterminate(true);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Daily Customer");

        dailyCustomerLb.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        dailyCustomerLb.setForeground(new java.awt.Color(204, 204, 204));
        dailyCustomerLb.setText("     0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spinnerDailyCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(dailyCustomerLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel9))
                .addGap(33, 33, 33))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(spinnerDailyCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(dailyCustomerLb)
                        .addGap(51, 51, 51))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(incomecustomersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(95, 95, 95)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(119, 119, 119)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(panelchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(incomecustomersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(panelchart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ras.swing.curvelinechart.chart.CurveLineChart chart;
    private javax.swing.JLabel dailyCustomerLb;
    private javax.swing.JPanel incomecustomersPanel;
    private javax.swing.JLabel instantTableCountLb;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private com.k33ptoo.components.KButton kButton1;
    private com.ras.swing.curvelinechart.panel.PanelShadow panelShadow;
    private javax.swing.JPanel panelchart;
    private com.ras.swing.spinner.SpinnerProgress spinnerDailyCustomer;
    private com.ras.swing.spinner.SpinnerProgress spinnerInsantTable;
    private com.ras.swing.spinner.SpinnerProgress spinnerTodayEarn;
    private javax.swing.JLabel todayEarnLb;
    // End of variables declaration//GEN-END:variables
}
