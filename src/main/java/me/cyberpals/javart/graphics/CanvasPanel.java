package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.tools.ToolManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CanvasPanel extends JPanel {
    public int toolId = 0;
    MainFrame instance;
    ToolManager toolManager;
    ArrayList<Shape> shapes;
    Shape current;
    Shape s1, s2;
    int id = 0;
    int tx, ty;

    public CanvasPanel(MainFrame instance, ToolManager toolManager) {
        super();

        shapes = new ArrayList<>();

        this.instance = instance;
        this.toolManager = toolManager;

        // Add a mouse listener to the canvas


    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth() - 150, instance.getHeight() - 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.decode("#FBFCFA"));
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        g.setColor(Color.decode("#050505"));
        for (Shape shape : shapes) {
            for (int i = shape.getBegin().getX(); i < shape.getEnd().getX(); i++) {
                for (int j = shape.getBegin().getY(); j < shape.getEnd().getY(); j++) {
                    if (shape.test(new Vector2Int(i, j))) {
                        g.fillRect(i, j, 1, 1);
                    }
                }
            }
        }
        if (current != null) {
            for (int i = current.getBegin().getX(); i < current.getEnd().getX(); i++) {
                for (int j = current.getBegin().getY(); j < current.getEnd().getY(); j++) {
                    if (current.test(new Vector2Int(i, j))) {
                        g.fillRect(i, j, 1, 1);
                    }
                }
            }
        }
    }
}
