package me.cyberpals.javart.graphics.tools;

import me.cyberpals.javart.graphics.pictures.PictureManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ToolManager {
    // tool enums

    ToolType toolType;
    ToolDetails toolDetails;

    PictureManager pictureManager;
    ArrayList<Shape> shapes;

    JPanel Canvas;

    // atributes to help events

    Shape current;
    Shape s1, s2;


    public ToolManager(PictureManager pictureManager) {
        setTool(ToolDetails.MOVE);
    }


    private void resetDatas() {

    }

    public void setTool(ToolDetails toolDetails) {
        this.toolDetails = toolDetails;

        resetDatas();

        // set toolType
        switch (toolDetails) {
            case MOVE:
            case MOVE_OBJECT:
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
            case REMOVE:
            case SAVE:
            case LOAD:
                toolType = ToolType.CLICK;
                break;
        }
    }

    //methods for handling mouse events

    public void mousePressed(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                break;
            case SHAPE:
                break;
        }
    }

    public void mouseReleased(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                break;
            case SHAPE:
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        switch (toolType) {
            case MOVE:
                break;
            case SHAPE:
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        switch (toolType) {
            case COMBINE:
                break;
            case CLICK:
                break;
        }
    }

    // methods for controlling shapes

    public void updateCurrentAddingShape() {

    }

    public void addNewShapeFromCurrent() {

    }

    public void removeSelectedShape(Shape s) {

    }

    public void fuseSelectedShape() {

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

    public void setCanvas(JPanel canvas) {
        Canvas = canvas;
    }
}
