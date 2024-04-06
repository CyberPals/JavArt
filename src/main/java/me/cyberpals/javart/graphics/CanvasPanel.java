package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.tools.ToolManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                if (toolId == 0) {
                    super.mousePressed(e);
                    tx = e.getX();
                    ty = e.getY();
                    current = new Oval(
                            new Vector2Int(tx, ty),
                            new Vector2Int(tx, ty)
                    );
                } else {
                    for (Shape shape : shapes) {
                        if (shape.test(new Vector2Int(e.getX(), e.getY()))) {
                            if (id == 0) {
                                if (!shape.test(new Vector2Int(e.getX(), e.getY()))) {
                                    continue;
                                }
                                s1 = shape;
                                id = 1;
                                System.out.println("Shape 1 selected");
                                break;
                            } else if (shape != s1) {
                                if (!shape.test(new Vector2Int(e.getX(), e.getY()))) {
                                    continue;
                                }
                                s2 = shape;
                                id = 0;
                                shapes.remove(s1);
                                shapes.remove(s2);
                                shapes.add(new Xor(s1, s2));
                                System.out.println("Shape 2 selected");
                                repaint();
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (toolId == 0) {
                    super.mouseReleased(e);
                    shapes.add(new Oval(
                            new Vector2Int(tx, ty),
                            new Vector2Int(e.getX(), e.getY())
                    ));
                    current = null;
                    repaint();
                }
            }
        });

        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                current.setEnd(new Vector2Int(e.getX(), e.getY()));
                repaint();
            }
        });


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
