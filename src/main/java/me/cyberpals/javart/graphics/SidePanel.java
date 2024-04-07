package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.pictures.PictureManager;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    MainFrame instance;
    PictureManager manager;

    public SidePanel(MainFrame instance) {
        super();

        this.instance = instance;

        try {
            manager = new PictureManager("/testing.png", 16, 16);
            manager.addExtendablePicture("example1", 0, 1, 4, 4, 4, 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(150, instance.getHeight() - 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        // Draw a picture for example
        Dimension d = getPreferredSize();
        manager.getPicture("example1").drawPicture(g, 0, 0, d.width, d.height - 28, 4);
    }
}
