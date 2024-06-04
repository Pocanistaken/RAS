
package com.ras.manager;

import com.github.weisj.jsvg.nodes.Path;
import com.ras.entity.account.Employee;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileManager {
    
    private static FileManager instance;

    
    
    private FileManager() {
    }
    
    public static FileManager getInstance() {
        if (instance == null) {
            instance = new FileManager();
        }
        return instance;
    }

    
    
    public void createApplicationFolderPath() {
        String userHome = System.getProperty("user.home");
        java.nio.file.Path appDirPath = Paths.get(userHome, "RAS");
        if (!Files.exists(appDirPath)) {
            try {
                Files.createDirectories(appDirPath);
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public java.nio.file.Path getApplicationFolderPath() {
        String userHome = System.getProperty("user.home");
        java.nio.file.Path appDirPath = Paths.get(userHome, "RAS");
        return appDirPath;
    }
    
    
    public File getAvatarFile() {
         File file = new File(FileManager.getInstance().getApplicationFolderPath().toString(), "avatar.png");    
         if (!file.exists()) {       
             URL url = getClass().getResource("/com/ras/image/avatar.png");  
            if (url != null) {
                try (InputStream is = url.openStream()) {
                    Files.copy(is, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
         return file;
    }  
}
