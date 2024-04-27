package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.tools.ToolManager;
import me.cyberpals.javart.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CanvasPanel extends JPanel {
    public int toolId = 0;
    MainFrame instance;
    ToolManager toolManager;

    Shape s1, s2;
    int id = 0;
    int tx, ty;

    public CanvasPanel(MainFrame instance, ToolManager toolManager) {
        super();

        this.instance = instance;
        this.toolManager = toolManager;
        toolManager.setCanvas(this);

        // Add a mouse listener to the canvas
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                toolManager.mousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                toolManager.mouseReleased(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                toolManager.mouseClicked(e);
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                toolManager.mouseDragged(e);
            }
        });

        this.setFocusable(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth() - 100, instance.getHeight() - 50);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.decode("#FBFCFA"));
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        this.toolManager.paintData(g);
    }
}
