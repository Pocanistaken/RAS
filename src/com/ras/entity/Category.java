package com.ras.entity;

public record Category(String categoryName, int categoryID) {
    
    public Category(int categoryID) {
        this("Unknown", categoryID);
    }
    
}
