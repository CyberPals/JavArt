package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    MainFrame instance;

    public OptionPanel(MainFrame instance) {
        super();

        this.instance = instance;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth(), 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.PINK);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);
    }
}
