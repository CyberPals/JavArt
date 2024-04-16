package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.tools.ToolManager;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    MainFrame instance;
    ToolManager toolManager;

    public OptionPanel(MainFrame instance, ToolManager toolManager) {
        super();

        this.instance = instance;
        this.toolManager = toolManager;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth(), 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.PINK);
        toolManager.getPictureManager().getPicture("example2").drawPicture(g, 0, 0, getPreferredSize().width, getPreferredSize().height, 4);
    }
}
