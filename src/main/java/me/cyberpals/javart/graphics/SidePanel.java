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
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    manager.addPicture("example_" + i + "_" + j, i, j);
                }
            }
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
        for (int i = 0; i < d.width; i += 32) {
            for (int j = 0; j < d.height; j += 32) {
                g.drawImage(manager.getPicture("example_0_0"), i, j, 32, 32, null);
            }
        }
    }
}
