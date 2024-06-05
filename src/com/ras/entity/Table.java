package com.ras.entity;

import com.ras.enums.TableStatus;

public class Table {

    private String tableName;
    private int tableChairAmount;
    private String tableOwnerPhoneNumber;
    private String tableStatus;
    private Region tableRegion;
    private int tableID;

    public Table(String tableName, int tableChairAmount, String tableOwnerPhoneNumber, String tableStatus, Region tableRegion, int tableID) {
        this.tableName = tableName;
        this.tableStatus = tableStatus;
        this.tableChairAmount = tableChairAmount;
        this.tableRegion = tableRegion;
        this.tableID = tableID;
        if (tableStatus.equals(TableStatus.RESERVATION.toString())) {
            this.tableOwnerPhoneNumber = tableOwnerPhoneNumber;
        }
    }

    public Table(int tableID) {
        this.tableID = tableID;
    }
    
    


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableOwnerPhoneNumber() {
        return tableOwnerPhoneNumber;
    }

    public void setTableOwnerPhoneNumber(String tableOwnerPhoneNumber) {
        this.tableOwnerPhoneNumber = tableOwnerPhoneNumber;
    }

    public String getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public int getTableChairAmount() {
        return tableChairAmount;
    }

    public void setTableChairAmount(int tableChairAmount) {
        this.tableChairAmount = tableChairAmount;
    }


    public Region getTableRegion() {
        return tableRegion;
    }

    public void setTableRegion(Region tableRegion) {
        this.tableRegion = tableRegion;
    }

    /**
     * @return the tableID
     */
    public int getTableID() {
        return tableID;
    }

    /**
     * @param tableID the tableID to set
     */
    public void setTableID(int tableID) {
        this.tableID = tableID;
    }
}
