package com.ras.image;

import java.net.URL;
import javax.swing.ImageIcon;


public class IconManager {
    
    private ImageIcon icon;
    
    
    public ImageIcon getIcon(String fileName) {
        URL imageUrl = IconManager.class.getResource("/com/ras/image/" + fileName);
        if (imageUrl == null) {
            System.err.println("İkon dosyası bulunamadı!");
        }
        return new ImageIcon(imageUrl);
    }
    
   
    
}
