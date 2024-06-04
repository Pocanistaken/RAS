package com.ras.tabbed;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import net.miginfocom.swing.MigLayout;


public class PanelTabbed extends JPanel {

    private final ButtonGroup buttonGroup;

    public PanelTabbed() {
        setLayout(new MigLayout("filly,insets 3 10 3 10"));
        buttonGroup = new ButtonGroup();
    }

    public void addTab(JToggleButton item) {
        buttonGroup.add(item);
        add(item);
        repaint();
        revalidate();
    }
}
