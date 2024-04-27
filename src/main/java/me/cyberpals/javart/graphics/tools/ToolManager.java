package me.cyberpals.javart.graphics.tools;

import me.cyberpals.javart.graphics.pictures.PictureManager;
import me.cyberpals.javart.graphics.pictures.types.Picture;
import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.serialisation.SaveManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Difference;
import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.operations.Union;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    //methods for handling Camera
    MovementKey cameraMovement = new MovementKey(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
    Vector2Int cameraOffset = new Vector2Int(0, 0);

    //managers for tools
    ClickManager clickManager;

    public ToolManager(PictureManager pictureManager) {
        this.pictureManager = pictureManager;
        shapes = new ArrayList<>();
        setTool(ToolDetails.OVAL);

        //setup managers
        saveManager = new SaveManager();
        clientRmiShape = new ClientServerRmiShape();
        clickManager = new ClickManager(this);
    }


    //methods for handling mouse events

    private void resetDatas() {
        current = null;
        s1 = null;
        s2 = null;
        fuseIndex = 0;
        path = null;

        if (canvas != null) canvas.repaint();
    }

    private ImageIcon generateIcon(Picture picture, int width, int height) {
        return new ImageIcon(picture.getPicture().getScaledInstance(width, height, Image.SCALE_DEFAULT));
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
                        JOptionPane.showMessageDialog(null, "Shape loaded", "Success", JOptionPane.INFORMATION_MESSAGE, generateIcon(pictureManager.getPicture("Icon_info"), 64, 64));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "File not found", "Error", JOptionPane.ERROR_MESSAGE, generateIcon(pictureManager.getPicture("Icon_error"), 64, 64));
                    } catch (ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "Unknown error", "Error", JOptionPane.ERROR_MESSAGE, generateIcon(pictureManager.getPicture("Icon_error"), 64, 64));
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

    //for click events
    public void mousePressed(MouseEvent e) {
        clickManager.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        clickManager.mouseReleased(e);
    }

    public void mouseDragged(MouseEvent e) {
        clickManager.mouseDragged(e);
    }

    public void mouseClicked(MouseEvent e) {
        clickManager.mouseClicked(e);
    }

    //methods for handling key events
    public void keyPressed(KeyEvent e) {
        cameraMovement.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        cameraMovement.keyReleased(e.getKeyCode());
    }

    //main loop
    public void mainLoop() {
        while (true) {
            Vector2Int camMvt = cameraMovement.getMovement();
            cameraOffset = cameraOffset.add(camMvt.mult(new Vector2Int(-5, -5)));

            if (canvas != null && (camMvt.getX() != 0 || camMvt.getY() != 0)) canvas.repaint();

            optionPanel.requestFocus(false);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
            if (shape == null) continue;
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
                            fillRect(g, savedX - cameraOffset.getX(), j - cameraOffset.getY(), len, shape);
                            inProgress = false;
                        }
                    }
                }
                if (inProgress) {
                    fillRect(g, savedX - cameraOffset.getX(), j - cameraOffset.getY(), len, shape);
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
                            fillRect(g, savedX - cameraOffset.getX(), j - cameraOffset.getY(), len, current);
                            inProgress = false;
                        }
                    }
                }
                if (inProgress) {
                    fillRect(g, savedX - cameraOffset.getX(), j - cameraOffset.getY(), len, current);
                    inProgress = false;
                }
            }
        }
    }
}
