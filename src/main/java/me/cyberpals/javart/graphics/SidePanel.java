package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.components.option.ToolButton;
import me.cyberpals.javart.graphics.tools.ToolDetails;
import me.cyberpals.javart.graphics.tools.ToolManager;

import javax.swing.*;
import java.awt.*;

public class SidePanel extends JPanel {
    MainFrame instance;
    ToolManager toolManager;


    // for layout purposes
    GridBagLayout layout;
    private int x = 0, y = 0;

    public SidePanel(MainFrame instance, ToolManager toolManager) {
        super();

        this.instance = instance;
        this.toolManager = toolManager;
        this.layout = new GridBagLayout();

        //setup Layout
        this.layout.columnWidths = new int[]{50, 50};
        this.layout.rowHeights = new int[]{};
        this.layout.columnWeights = new double[]{0.0, 0.0};
        this.layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        this.setLayout(this.layout);
        this.setVisible(true);
        setupButtons();

        this.setFocusable(false);
    }

    private void setupButtons() {

        //shapes
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Oval"),
                toolManager,
                ToolDetails.OVAL
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Rectangle"),
                toolManager,
                ToolDetails.RECTANGLE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Rhombus"),
                toolManager,
                ToolDetails.RHOMBUS
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Triangle"),
                toolManager,
                ToolDetails.TRIANGLE
        ));
        //combine
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Union"),
                toolManager,
                ToolDetails.UNION
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Intersection"),
                toolManager,
                ToolDetails.INTERSECT
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Difference"),
                toolManager,
                ToolDetails.DIFERENCE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Xor"),
                toolManager,
                ToolDetails.XOR
        ));
        // tools
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Move"),
                toolManager,
                ToolDetails.MOVE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Button"),
                toolManager,
                ToolDetails.RESIZE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Copy"),
                toolManager,
                ToolDetails.COPY
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Ungroup"),
                toolManager,
                ToolDetails.UNGROUP
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Remove"),
                toolManager,
                ToolDetails.REMOVE
        ));
    }

    private void addButtonTool(ToolButton b) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = x;
        c.gridy = y;

        this.add(b, c);
        // increment x and y
        if (++x == 2) {
            x = 0;
            y++;
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, instance.getHeight() - 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        // Draw a picture for example
        Dimension d = getPreferredSize();
        int topBar = instance.getInsets().top;
        toolManager.getPictureManager().getPicture("Left_panel").drawPicture(g, 0, 0, d.width, d.height - topBar, 4);
    }
}
