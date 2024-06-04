package com.ras.entity;

import java.util.ArrayList;


public record Menu(String menuName, ArrayList<Product> productList, int menuID) {
    
    public Menu(int menuID) {
        this("Unknown", null, menuID);
    }
    
    public Menu(String menuName, int menuID) {
        this(menuName, null, menuID);
    }
    
}
