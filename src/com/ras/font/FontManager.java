
package com.ras.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;


public class FontManager {
    private Font customFont;
    
    public Font getFont(String fontName, float fontSize) {
        try {
            //create the font to use. Specify the size!
            //customFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/Fonts/" + fontName + ".ttf")).deriveFont(fontSize);
            customFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("com/ras/font/" + fontName + ".ttf")).deriveFont(fontSize);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);

        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
       
        return customFont;
        
    }
    
}
