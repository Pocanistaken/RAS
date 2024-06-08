package com.ras.entity.model;

public class DailyEarnModelData {
    
    
    private double dailyTotal;
    private String date;

    public DailyEarnModelData(double dailyTotal, String date) {
        this.dailyTotal = dailyTotal;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDailyTotal() {
        return dailyTotal;
    }

    public void setDailyTotal(double dailyTotal) {
        this.dailyTotal = dailyTotal;
    }

}
