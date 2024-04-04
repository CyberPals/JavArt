package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;

import javax.swing.*;
import java.awt.*;

public class CanvasPanel extends JPanel {
    MainFrame instance;

    public CanvasPanel(MainFrame instance) {
        super();

        this.instance = instance;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth() - 150, instance.getHeight() - 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
    }
}
