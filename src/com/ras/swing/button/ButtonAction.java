package com.ras.swing.button;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;


public class ButtonAction extends JButton {

    public ButtonAction() {

        putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:15;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:5,20,5,20;"
                + "background:$Panel.background");
    }
}
