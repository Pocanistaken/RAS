package com.ras.database;

import com.ras.entity.Category;
import com.ras.entity.Menu;
import com.ras.entity.Product;
import com.ras.entity.Region;
import com.ras.entity.Table;
import com.ras.exception.RegionAlreadyExists;
import com.ras.exception.TableAlreadyExists;
import com.ras.manager.FileManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import raven.toast.Notifications;

public class DatabaseOperation {

    private final String databaseHostIP = "193.106.196.116";
    private final String databaseName = "yetk_aliabiveritabani";
    private final String databaseUsername = "yetk_aliabiveritabani";
    private final String databasePassword = "*0e!tkFkmFkH0udG";
    private final String databasePort = "3306";


    private Connection con = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ArrayList<Product> products = null;
    


    public DatabaseOperation() {
        String request = "jdbc:mysql://" + databaseHostIP + ":" + databasePort + "/" + databaseName + "?useUnicode=true&characterEncoding=utf8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(request, databaseUsername, databasePassword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    


        
        
    
    
    
    public boolean loginPanel(String username, String password) {
        final var request = "Select * from user where username = ? and password = ?";
        try (final var statement = con.prepareStatement(request)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
                
        }
    }
    
    public String getEmployeeInfo(String username, String param) { // SQL INJECT
        String request = "SELECT * FROM user where username = '" + username + "'";
        String info = null;
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
   
      
            while (rs.next()) {
                info = rs.getString(param);
            }
            return info;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    public File getEmployeeAvatar(String username) {
        String request = "SELECT avatar FROM user WHERE username = ?";
        FileOutputStream fos = null;
        File file = null;

        try (final var statement = con.prepareStatement(request)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("avatar");
                byte[] b = blob.getBytes(1, (int) blob.length());

                file = new File(FileManager.getInstance().getApplicationFolderPath().toString(), "avatar.png");

                fos = new FileOutputStream(file);
                fos.write(b);
                fos.flush();
            }
            return file;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    
    
    public void updateEmployeeInfo(String username, String key, String value) {
        final var request = "UPDATE user SET " + key + " = ? WHERE username = ?";
        System.out.println("SQL Query: " + request);
        try {
            con.setAutoCommit(true); // Auto-commit modunu aç
            try (final var statement = con.prepareStatement(request)) {
                if (key.equals("avatar")) {
                    File imgFile = new File(value);
                    if (imgFile.exists() && !imgFile.isDirectory()) {
                        InputStream in = new FileInputStream(imgFile);
                        statement.setBinaryStream(1, in, (int) imgFile.length());
                        statement.setString(2, username);
                        int rowsUpdated = statement.executeUpdate();
                        System.out.println("Avatar updated, rows affected: " + rowsUpdated);
                    } else {
                        System.out.println("File not found or is a directory: " + value);
                    }
                } else {
                    statement.setString(1, value);
                    statement.setString(2, username);
                    int rowsUpdated = statement.executeUpdate();
                    System.out.println("Info updated, rows affected: " + rowsUpdated);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    
    
    
    public int increaseID(String tableName) {
        String request = "SELECT * FROM `" + tableName + "`";        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
            int id = 0;

            while (rs.next()) {
                id = rs.getInt(tableName + "_id");
            }
            
            return ++id;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
//         String request = "SELECT * FROM `" + tableName + "` WHERE id = ?";        
    
    
    
    public String convertIdToName(String tableName, int id) {

        String request = "SELECT * FROM `" + tableName + "` WHERE " + tableName + "_id = ?";
        String name = null;

        try {

            final var statement = con.prepareStatement(request);
            statement.setInt(1, id); 

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                name = rs.getString(tableName + "Name");
            }

            rs.close();
            statement.close();

            return name;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }        
    }




    // REGION

    
    public void addRegionToDatabase(Region region) {
        
        if (getAllRegions().contains(region)) {
            
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "Exception - RegionAlreadyExists");

            try {
                throw new RegionAlreadyExists("Region is already exists.");
                
                } 
            catch (RegionAlreadyExists ex) {
                Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else {
        
            final var request = "INSERT INTO `region` (region_id,regionName) VALUES (?,?)";
            try (final var statement = con.prepareStatement(request)) {
                statement.setInt(1, region.regionID());
                statement.setString(2, region.regionName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    public void deleteRegionFromDatabase(Region region) {
        String request = "DELETE FROM `region` WHERE region_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1,region.regionID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    public ArrayList<Region> getAllRegions() {
        String request = "SELECT * FROM `region`";
        ArrayList<Region> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                int regionID = rs.getInt("region_id");
                String regionName = rs.getString("regionName");

                list.add(new Region(regionName, regionID));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    // TABLE
    

    public void addTableToDatabase(Table table) {
        
        if (getAllTables().contains(table)) {
            
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.BOTTOM_LEFT, "Exception - TableAlreadyExists");

            
            try {
                throw new TableAlreadyExists("Table is already exists.");
                } 
            catch (TableAlreadyExists ex) {
                Logger.getLogger(DatabaseOperation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        else {
        
        
            final var request = "INSERT INTO `table` (table_id, tableName, tableChairAmount, tableOwnerPhoneNumber, tableStatus, region_id) VALUES (?,?,?,?,?,?)";
            try (final var statement = con.prepareStatement(request)) {
                statement.setInt(1, table.getTableID());
                statement.setString(2, table.getTableName());
                statement.setInt(3, table.getTableChairAmount());
                statement.setString(4, table.getTableOwnerPhoneNumber());
                statement.setString(5, table.getTableStatus());
                statement.setInt(6, table.getTableRegion().regionID());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
    
    
    public void deleteTableFromDatabase(Table table) {
        String request = "DELETE FROM `table` WHERE table_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1, table.getTableID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    
    public ArrayList<Table> getAllTables() {
        
        
        
        String request = "SELECT * FROM `table`";
        ArrayList<Table> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                int id = rs.getInt("table_id");
                String tableName = rs.getString("tableName");
                int tableChairAmount = rs.getInt("tableChairAmount");
                String tableOwnerPhoneNumber = rs.getString("tableOwnerPhoneNumber");
                String tableStatus = rs.getString("tableStatus");
                int tableRegionID = rs.getInt("region_id");


                list.add(new Table(tableName, tableChairAmount, tableOwnerPhoneNumber, tableStatus, new Region(convertIdToName("region", tableRegionID), tableRegionID), id));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // CATEGORY


    public void addCategoryToDatabase(Category category) {
        final var request = "INSERT Into `category` (category_id,categoryName) VALUES (?,?)";
        try (final var statement = con.prepareStatement(request)) {
            statement.setInt(1, category.categoryID());
            statement.setString(2, category.categoryName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   
    
    public void deleteCategoryFromDatabase(Category category) {
        String request = "DELETE FROM `category` WHERE category_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1,category.categoryID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    
    public ArrayList<Category> getAllCategorys() {
        
        String request = "SELECT * FROM `category`";
        ArrayList<Category> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                int id = rs.getInt("category_id");
                String categoryName = rs.getString("categoryName");
                
                list.add(new Category(categoryName, id));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    // PRODUCT
    
    public void addProductToDatabase(Product product) {
        final var request = "INSERT Into `product` (product_id,productName,productDescription,productPrice,category_id) VALUES (?,?,?,?,?)";
        try (final var statement = con.prepareStatement(request)) {
            statement.setInt(1, product.getProductID());
            statement.setString(2, product.getProductName());
            statement.setString(3, product.getProductDescription());
            statement.setDouble(4, product.getProductPrice());
            statement.setInt(5, product.getProductCategory().categoryID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteProductFromDatabase(Product product) {
        String request = "DELETE FROM `product` WHERE product_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1,product.getProductID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    public ArrayList<Product> getAllProducts() {
        
        String request = "SELECT * FROM `product`";
        ArrayList<Product> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                int id = rs.getInt("product_id");
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                double productPrice = rs.getDouble("productPrice");
                int category_id = rs.getInt("category_id");
                
               list.add(new Product(productName, productDescription, productPrice, new Category(convertIdToName("category", category_id), category_id), id));


            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public Product getProductFromID(Product product) {
        
        String request = "SELECT * FROM `product` WHERE `product_id` = " + product.getProductID();
        ArrayList<Product> list = new ArrayList<>();
        Product value_product = null;
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                String productName = rs.getString("productName");
                String productDescription = rs.getString("productDescription");
                double productPrice = rs.getDouble("productPrice");
                int category_id = rs.getInt("category_id");
                
                value_product = new Product(productName, productDescription, productPrice, new Category(convertIdToName("category", category_id), category_id), product.getProductID());

            }
            
            return value_product;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
   
    
    
    // MENU


    
    
    public void addMenuToDatabase(Menu menu) {
        final var request = "INSERT Into `menu` (menu_id,menuName) VALUES (?,?)";
        try (final var statement = con.prepareStatement(request)) {
            statement.setInt(1, menu.menuID());
            statement.setString(2, menu.menuName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteMenuFromDatabase(Menu menu) {
        String request = "DELETE FROM `menu` WHERE menu_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1,menu.menuID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    
    
    public ArrayList<Menu> getAllMenus() {
        
        String request = "SELECT * FROM `menu`";
        ArrayList<Menu> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                int id = rs.getInt("menu_id");
                String menuName = rs.getString("menuName");
                
                
               list.add(new Menu(menuName, id));

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    public ArrayList<Product> getProductListFromMenu(Menu menu) {
        
        String request = "SELECT * FROM `menu_product` WHERE `menu_id` = " + menu.menuID();
        ArrayList<Product> list = new ArrayList<>();
        
        try {
            final var statement = con.createStatement();
            ResultSet rs = statement.executeQuery(request);
  
      
            while (rs.next()) {
                
                int productID = rs.getInt("product_id");
                list.add(getProductFromID(new Product(productID))); 

            }
            
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    // MENU - PRODUCT
    
    
    public void addProductToMenu(Product product, Menu menu) {
        final var request = "INSERT Into `menu_product` (menu_id,product_id) VALUES (?,?)";
        try (final var statement = con.prepareStatement(request)) {
            statement.setInt(1, menu.menuID());
            statement.setInt(2, product.getProductID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void deleteProductFromMenu(Product product, Menu menu) {
        String request = "DELETE FROM `menu_product` WHERE product_id = ? AND menu_id = ?";
        try {
            preparedStatement = con.prepareStatement(request);
            preparedStatement.setInt(1, product.getProductID());
            preparedStatement.setInt(2, menu.menuID());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }   
    }
    


}














