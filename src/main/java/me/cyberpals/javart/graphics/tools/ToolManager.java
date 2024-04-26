package me.cyberpals.javart.graphics.tools;

import me.cyberpals.javart.graphics.pictures.PictureManager;
import me.cyberpals.javart.graphics.pictures.types.Picture;
import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.serialisation.SaveManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.*;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.shapes.simple.Rhombus;
import me.cyberpals.javart.shapes.simple.Triangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ToolManager {
    // tool enums

    ToolType toolType;
    ToolDetails toolDetails;

    PictureManager pictureManager;
    ArrayList<Shape> shapes;
    Shape currentShape;
    Vector2Int deltaPos;

    JPanel canvas, optionPanel;

    // atributes to help events

    Shape current;
    Shape s1, s2;

    Vector2Int customBegin, customEnd;

    int fuseIndex = 0;

    String path;

    //attributes for save purpose

    SaveManager saveManager;
    ClientServerRmiShape clientRmiShape;

    public ToolManager(PictureManager pictureManager) {
        this.pictureManager = pictureManager;
        shapes = new ArrayList<>();
        setTool(ToolDetails.OVAL);

        //setup managers
        saveManager = new SaveManager();
        clientRmiShape = new ClientServerRmiShape();
    }


    private void resetDatas() {
        current = null;
        s1 = null;
        s2 = null;
        fuseIndex = 0;
        path = null;

        if (canvas != null) canvas.repaint();
    }

    public void setTool(ToolDetails toolDetails) {
        this.toolDetails = toolDetails;

        resetDatas();

        // set toolType
        switch (toolDetails) {
            case MOVE:
            case MOVE_OBJECT:
            case RESIZE:
                toolType = ToolType.MOVE;
                break;
            case OVAL:
            case RECTANGLE:
            case RHOMBUS:
            case TRIANGLE:
                toolType = ToolType.SHAPE;
                break;
            case DIFERENCE:
            case INTERSECT:
            case UNION:
            case XOR:
                toolType = ToolType.COMBINE;
                break;
            case LOAD:
                toolType = ToolType.CLICK;
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Load shape");

                FileNameExtensionFilter filter = new FileNameExtensionFilter("Shape files", "toast");
                fileChooser.setFileFilter(filter);

                int select = fileChooser.showOpenDialog(null);
                if (select == JFileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getAbsolutePath();
                    //open file
                    try {
                        Shape s = saveManager.loadShape(path);
                        JOptionPane.showMessageDialog(null, "Shape loaded", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Unknown error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    setTool(ToolDetails.MOVE);
                }

                break;
            case COPY:
            case UNGROUP:
            case SAVE:
            case SELECT:
            case REMOVE:
            case RMI_SAVE:
            case RMI_LOAD:
                toolType = ToolType.CLICK;
                break;
        }
        if (optionPanel != null)
            optionPanel.repaint();
    }

    //methods for handling mouse events

    public void mousePressed(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                switch (toolDetails) {
                    case MOVE:
                        current = getShape(new Vector2Int(e.getX(), e.getY()));
                        if (current != null) {
                            deltaPos = new Vector2Int(e.getX() - current.getBegin().getX(), e.getY() - current.getBegin().getY());
                        }
                        break;
                    case RESIZE:
                        s1 = getShape(new Vector2Int(e.getX(), e.getY()));
                        if (s1 != null) {
                            current = s1.copy();
                            shapes.remove(s1);
                        }
                        break;
                }
                break;
            case SHAPE:
                customBegin = new Vector2Int(e.getX(), e.getY());
                customEnd = new Vector2Int(e.getX(), e.getY());
                switch (toolDetails) {
                    case OVAL:
                        current = new Oval(new Vector2Int(e.getX(), e.getY()), new Vector2Int(e.getX(), e.getY()));
                        break;
                    case RECTANGLE:
                        current = new Rectangle(new Vector2Int(e.getX(), e.getY()), new Vector2Int(e.getX(), e.getY()));
                        break;
                    case RHOMBUS:
                        current = new Rhombus(new Vector2Int(e.getX(), e.getY()), new Vector2Int(e.getX(), e.getY()));
                        break;
                    case TRIANGLE:
                        current = new Triangle(new Vector2Int(e.getX(), e.getY()), new Vector2Int(e.getX(), e.getY()));
                        break;
                }
                canvas.repaint();
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                switch (toolDetails) {
                    case MOVE:
                        if (current != null) {
                            current.move(new Vector2Int(e.getX() - current.getBegin().getX() - deltaPos.getX(), e.getY() - current.getBegin().getY() - deltaPos.getY()));
                            current = null;
                            deltaPos = null;
                            canvas.repaint();
                        }
                        break;
                    case RESIZE:
                        if (s1 != null) {
                            s1.resize(new Vector2Int(e.getX() - s1.getEnd().getX(), e.getY() - s1.getEnd().getY()));
                            shapes.add(s1);
                            s1 = null;
                            current = null;
                            canvas.repaint();
                        }
                        break;
                }
                break;
            case SHAPE:
                addNewShapeFromCurrent();
                current = null;
                canvas.repaint();
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                switch (toolDetails) {
                    case MOVE:
                        if (current != null) {
                            current.move(new Vector2Int(e.getX() - current.getBegin().getX() - deltaPos.getX(), e.getY() - current.getBegin().getY() - deltaPos.getY()));
                            canvas.repaint();
                        }
                        break;
                    case RESIZE:
                        if (s1 != null) {
                            current = s1.copy();
                            current.resize(new Vector2Int(e.getX() - s1.getEnd().getX(), e.getY() - s1.getEnd().getY()));
                            canvas.repaint();
                        }
                        break;
                }
                break;
            case SHAPE:
                customEnd = new Vector2Int(e.getX(), e.getY());
                current.setBegin(new Vector2Int(
                        Math.min(customBegin.getX(), customEnd.getX()),
                        Math.min(customBegin.getY(), customEnd.getY())
                ));
                current.setEnd(new Vector2Int(
                        Math.max(customBegin.getX(), customEnd.getX()),
                        Math.max(customBegin.getY(), customEnd.getY())
                ));
                canvas.repaint();
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        switch (toolType) {
            case COMBINE:
                if (fuseIndex == 0) {
                    s1 = getShape(new Vector2Int(e.getX(), e.getY()));
                    if (s1 != null) {
                        fuseIndex = 1;
                        canvas.repaint();
                    }
                } else {
                    s2 = getShapeNot(new Vector2Int(e.getX(), e.getY()), s1);
                    if (s2 != null) {
                        fuseSelectedShape();
                        fuseIndex = 0;
                        s1 = null;
                        s2 = null;
                        canvas.repaint();
                    }
                }
                break;
            case CLICK:

                switch (toolDetails) {
                    case REMOVE:
                        removeSelectedShape(getShape(new Vector2Int(e.getX(), e.getY())));
                        canvas.repaint();
                        break;
                    case SAVE:
                        Shape s = getShape(new Vector2Int(e.getX(), e.getY()));
                        if (s == null) return;
                        //create file selection dialog
                        JFileChooser fileChooser = new JFileChooser();
                        int select = fileChooser.showSaveDialog(null);

                        if (select == JFileChooser.APPROVE_OPTION) {
                            path = fileChooser.getSelectedFile().getAbsolutePath();
                            if (!path.endsWith(".toast")) {
                                path += ".toast";

                                try {
                                    saveManager.saveShape(s, path);
                                    JOptionPane.showMessageDialog(null, "Shape saved", "Success", JOptionPane.INFORMATION_MESSAGE);
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Error while saving the shape", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                        break;
                    case LOAD:
                        //append current variable shape to the list
                        try {
                            Shape s1 = saveManager.loadShape(path);
                            s1.move(new Vector2Int(e.getX() - s1.getBegin().getX(), e.getY() - s1.getBegin().getY()));
                            shapes.add(s1);
                            canvas.repaint();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Unknown error", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case COPY:
                        if (s1 == null) {
                            Shape sel = getShape(new Vector2Int(e.getX(), e.getY()));
                            if (sel != null) {
                                s1 = sel;
                                canvas.repaint();
                            }
                        } else {
                            Shape temp = s1.copy();
                            temp.move(new Vector2Int(e.getX() - temp.getBegin().getX(), e.getY() - temp.getBegin().getY()));
                            shapes.add(temp);
                            canvas.repaint();
                        }
                        break;
                    case UNGROUP:
                        Shape sel = getShape(new Vector2Int(e.getX(), e.getY()));
                        //test if sel extends from Operation
                        if (sel instanceof Operation) {
                            Operation op = (Operation) sel;
                            Shape c1 = op.getChild1();
                            Shape c2 = op.getChild2();
                            shapes.add(c1);
                            shapes.add(c2);
                            shapes.remove(sel);
                            canvas.repaint();
                        }
                }
                break;
        }
    }

    // methods for controlling shapes
    public Picture getToolPics() {
        switch (toolDetails) {
            case OVAL:
                return pictureManager.getPicture("Oval");
            case RECTANGLE:
                return pictureManager.getPicture("Rectangle");
            case RHOMBUS:
                return pictureManager.getPicture("Rhombus");
            case TRIANGLE:
                return pictureManager.getPicture("Triangle");
            case DIFERENCE:
                return pictureManager.getPicture("Difference");
            case INTERSECT:
                return pictureManager.getPicture("Intersection");
            case UNION:
                return pictureManager.getPicture("Union");
            case XOR:
                return pictureManager.getPicture("Xor");
            case MOVE:
                return pictureManager.getPicture("Move");
            case RESIZE:
                return pictureManager.getPicture("Button");
            case COPY:
                return pictureManager.getPicture("Copy");
            case UNGROUP:
                return pictureManager.getPicture("Ungroup");
            case SAVE:
                return pictureManager.getPicture("Save_local");
            case LOAD:
                return pictureManager.getPicture("Load_local");
            case REMOVE:
                return pictureManager.getPicture("Remove");
        }
        return null;
    }

    public void addNewShapeFromCurrent() {
        shapes.add(current);
    }

    public void removeSelectedShape(Shape s) {
        if (s != null) shapes.remove(s);
    }

    public void fuseSelectedShape() {
        switch (toolDetails) {
            case DIFERENCE:
                shapes.add(new Difference(s1, s2));
                break;
            case INTERSECT:
                shapes.add(new Intersection(s1, s2));
                break;
            case UNION:
                shapes.add(new Union(s1, s2));
                break;
            case XOR:
                shapes.add(new Xor(s1, s2));
                break;
        }
        shapes.remove(s1);
        shapes.remove(s2);
    }

    public Shape getShape(Vector2Int v) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s = shapes.get(i);
            if (s.test(v)) {
                return s;
            }
        }
        return null;
    }

    public Shape getShapeNot(Vector2Int v, Shape s) {
        for (int i = shapes.size() - 1; i >= 0; i--) {
            Shape s1 = shapes.get(i);
            if (s1.test(v) && s1 != s) {
                return s1;
            }
        }
        return null;
    }

    public void setCanvas(JPanel canvas) {
        this.canvas = canvas;
    }

    public void setOptionPanel(JPanel optionPanel) {
        this.optionPanel = optionPanel;
    }

    public PictureManager getPictureManager() {
        return pictureManager;
    }

    private void fillRect(Graphics g, int x, int y, int w, Shape current) {
        if (current == s1) {
            g.setColor(Color.decode("#B50505"));
        } else {
            g.setColor(Color.decode("#050505"));
        }
        g.fillRect(x, y, w, 1);
    }

    public void paintData(Graphics g) {
        g.setColor(Color.decode("#050505"));
        for (Shape shape : shapes) {
            boolean inProgress = false;
            int len = 0;
            int savedX = 0;
            for (int j = shape.getBegin().getY(); j < shape.getEnd().getY(); j++) {
                for (int i = shape.getBegin().getX(); i < shape.getEnd().getX(); i++) {
                    if (shape.test(new Vector2Int(i, j))) {
                        if (!inProgress) {
                            inProgress = true;
                            len = 1;
                            savedX = i;
                        } else {
                            len++;
                        }
                    } else {
                        //fill rectangle
                        if (inProgress) {
                            fillRect(g, savedX, j, len, shape);
                            inProgress = false;
                        }
                    }
                }
                if (inProgress) {
                    fillRect(g, savedX, j, len, shape);
                    inProgress = false;
                }
            }
        }
        if (current != null) {
            //recalculate manually the coordinates
            Vector2Int begin = new Vector2Int(
                    Math.min(current.getBegin().getX(), current.getEnd().getX()),
                    Math.min(current.getBegin().getY(), current.getEnd().getY())
            );
            Vector2Int end = new Vector2Int(
                    Math.max(current.getBegin().getX(), current.getEnd().getX()),
                    Math.max(current.getBegin().getY(), current.getEnd().getY())
            );
            boolean inProgress = false;
            int len = 0;
            int savedX = 0;
            for (int j = begin.getY(); j < end.getY(); j++) {
                for (int i = begin.getX(); i < end.getX(); i++) {
                    if (current.test(new Vector2Int(i, j))) {
                        if (!inProgress) {
                            inProgress = true;
                            len = 1;
                            savedX = i;
                        } else {
                            len++;
                        }
                    } else {
                        //fill rectangle
                        if (inProgress) {
                            fillRect(g, savedX, j, len, current);
                            inProgress = false;
                        }
                    }
                }
                if (inProgress) {
                    fillRect(g, savedX, j, len, current);
                    inProgress = false;
                }
            }
        }
    }
}
