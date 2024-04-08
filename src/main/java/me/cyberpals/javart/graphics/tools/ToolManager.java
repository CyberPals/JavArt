package me.cyberpals.javart.graphics.tools;

public class ToolManager {
    // tool enums

    ToolType toolType;
    ToolDetails toolDetails;

    public ToolManager() {
        setTool(ToolDetails.MOVE);
    }


    public void setTool(ToolDetails toolDetails) {
        this.toolDetails = toolDetails;

        // set toolType
        switch (toolDetails) {
            case MOVE:
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
                toolType = ToolType.REMOVE;
                break;
        }
    }
}
