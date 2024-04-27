package me.cyberpals.javart.graphics.tools;

import me.cyberpals.javart.graphics.pictures.types.Picture;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Operation;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.shapes.simple.Rhombus;
import me.cyberpals.javart.shapes.simple.Triangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickManager {

    private ToolManager toolManager;

    public ClickManager(ToolManager toolManager) {
        this.toolManager = toolManager;
    }

    //methods used
    private ImageIcon generateIcon(Picture picture, int width, int height) {
        return new ImageIcon(picture.getPicture().getScaledInstance(width, height, Image.SCALE_DEFAULT));
    }

    //click handler

    public void mousePressed(MouseEvent e) {
        switch (toolManager.toolType) {
            case MOVE:
                switch (toolManager.toolDetails) {
                    case MOVE:
                        toolManager.current = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        if (toolManager.current != null) {
                            toolManager.deltaPos = new Vector2Int(e.getX() - toolManager.current.getBegin().getX() + toolManager.cameraOffset.getX(), e.getY() - toolManager.current.getBegin().getY() + toolManager.cameraOffset.getY());
                        }
                        break;
                    case RESIZE:
                        toolManager.s1 = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        if (toolManager.s1 != null) {
                            toolManager.current = toolManager.s1.copy();
                            toolManager.shapes.remove(toolManager.s1);
                        }
                        break;
                }
                break;
            case SHAPE:
                toolManager.customBegin = new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY());
                toolManager.customEnd = new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY());
                switch (toolManager.toolDetails) {
                    case OVAL:
                        toolManager.current = new Oval(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()), new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        break;
                    case RECTANGLE:
                        toolManager.current = new Rectangle(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()), new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        break;
                    case RHOMBUS:
                        toolManager.current = new Rhombus(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()), new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        break;
                    case TRIANGLE:
                        toolManager.current = new Triangle(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()), new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        break;
                }
                toolManager.canvas.repaint();
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (toolManager.toolType) {
            case MOVE:
                switch (toolManager.toolDetails) {
                    case MOVE:
                        if (toolManager.current != null) {
                            toolManager.current.move(new Vector2Int(e.getX() - toolManager.current.getBegin().getX() - toolManager.deltaPos.getX() + toolManager.cameraOffset.getX(), e.getY() - toolManager.current.getBegin().getY() - toolManager.deltaPos.getY() + toolManager.cameraOffset.getY()));
                            toolManager.current = null;
                            toolManager.deltaPos = null;
                            toolManager.canvas.repaint();
                        }
                        break;
                    case RESIZE:
                        if (toolManager.s1 != null) {
                            toolManager.s1.resize(new Vector2Int(e.getX() - toolManager.s1.getEnd().getX() + toolManager.cameraOffset.getX(), e.getY() - toolManager.s1.getEnd().getY() + toolManager.cameraOffset.getY()));
                            toolManager.shapes.add(toolManager.s1);
                            toolManager.s1 = null;
                            toolManager.current = null;
                            toolManager.canvas.repaint();
                        }
                        break;
                }
                break;
            case SHAPE:
                toolManager.addNewShapeFromCurrent();
                toolManager.current = null;
                toolManager.canvas.repaint();
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        switch (toolManager.toolType) {
            case MOVE:
                switch (toolManager.toolDetails) {
                    case MOVE:
                        if (toolManager.current != null) {
                            toolManager.current.move(new Vector2Int(e.getX() - toolManager.current.getBegin().getX() - toolManager.deltaPos.getX() + toolManager.cameraOffset.getX(), e.getY() - toolManager.current.getBegin().getY() - toolManager.deltaPos.getY() + toolManager.cameraOffset.getY()));
                            toolManager.canvas.repaint();
                        }
                        break;
                    case RESIZE:
                        if (toolManager.s1 != null) {
                            toolManager.current = toolManager.s1.copy();
                            toolManager.current.resize(
                                    new Vector2Int(
                                            Math.max(
                                                    e.getX() - toolManager.s1.getEnd().getX() + toolManager.cameraOffset.getX(),
                                                    -toolManager.current.getEnd().getX() + toolManager.current.getBegin().getX()
                                            ),
                                            Math.max(
                                                    e.getY() - toolManager.s1.getEnd().getY() + toolManager.cameraOffset.getY(),
                                                    -toolManager.current.getEnd().getY() + toolManager.current.getBegin().getY()
                                            )));
                            toolManager.canvas.repaint();
                        }
                        break;
                }
                break;
            case SHAPE:
                toolManager.customEnd = new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY());
                toolManager.current.setBegin(new Vector2Int(
                        Math.min(toolManager.customBegin.getX(), toolManager.customEnd.getX()),
                        Math.min(toolManager.customBegin.getY(), toolManager.customEnd.getY())
                ));
                toolManager.current.setEnd(new Vector2Int(
                        Math.max(toolManager.customBegin.getX(), toolManager.customEnd.getX()),
                        Math.max(toolManager.customBegin.getY(), toolManager.customEnd.getY())
                ));
                toolManager.canvas.repaint();
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        switch (toolManager.toolType) {
            case COMBINE:
                if (toolManager.fuseIndex == 0) {
                    toolManager.s1 = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                    if (toolManager.s1 != null) {
                        toolManager.fuseIndex = 1;
                        toolManager.canvas.repaint();
                    }
                } else {
                    toolManager.s2 = toolManager.getShapeNot(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()), toolManager.s1);
                    if (toolManager.s2 != null) {
                        toolManager.fuseSelectedShape();
                        toolManager.fuseIndex = 0;
                        toolManager.s1 = null;
                        toolManager.s2 = null;
                        toolManager.canvas.repaint();
                    }
                }
                break;
            case CLICK:

                switch (toolManager.toolDetails) {
                    case REMOVE:
                        toolManager.removeSelectedShape(toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY())));
                        toolManager.canvas.repaint();
                        break;
                    case SAVE:
                        Shape s = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        if (s == null) return;
                        //create file selection dialog
                        JFileChooser fileChooser = new JFileChooser();
                        int select = fileChooser.showSaveDialog(null);

                        if (select == JFileChooser.APPROVE_OPTION) {
                            String path = fileChooser.getSelectedFile().getAbsolutePath();
                            if (!path.endsWith(".toast")) {
                                path += ".toast";

                                try {
                                    toolManager.saveManager.saveShape(s, path);
                                    JOptionPane.showMessageDialog(null, "Shape saved", "Success", JOptionPane.INFORMATION_MESSAGE, generateIcon(toolManager.pictureManager.getPicture("Icon_info"), 64, 64));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Error while saving the shape", "Error", JOptionPane.ERROR_MESSAGE, generateIcon(toolManager.pictureManager.getPicture("Icon_error"), 64, 64));
                                }
                            }
                        }
                        break;
                    case LOAD:
                        //append current variable shape to the list

                        Shape s1 = toolManager.s1.copy();
                        s1.move(new Vector2Int(e.getX() - s1.getBegin().getX(), e.getY() - s1.getBegin().getY()));
                        toolManager.shapes.add(s1);
                        toolManager.canvas.repaint();

                        break;
                    case COPY:
                        if (toolManager.s1 == null) {
                            Shape sel = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                            if (sel != null) {
                                toolManager.s1 = sel;
                                toolManager.canvas.repaint();
                            }
                        } else {
                            Shape temp = toolManager.s1.copy();
                            temp.move(new Vector2Int(e.getX() - temp.getBegin().getX() + toolManager.cameraOffset.getX(), e.getY() - temp.getBegin().getY() + toolManager.cameraOffset.getY()));
                            toolManager.shapes.add(temp);
                            toolManager.canvas.repaint();
                        }
                        break;
                    case UNGROUP:
                        Shape sel = toolManager.getShape(new Vector2Int(e.getX() + toolManager.cameraOffset.getX(), e.getY() + toolManager.cameraOffset.getY()));
                        //test if sel extends from Operation
                        if (sel instanceof Operation) {
                            Operation op = (Operation) sel;
                            Shape c1 = op.getChild1();
                            Shape c2 = op.getChild2();
                            toolManager.shapes.add(c1);
                            toolManager.shapes.add(c2);
                            toolManager.shapes.remove(sel);
                            toolManager.canvas.repaint();
                        }
                }
                break;
        }
    }
}
