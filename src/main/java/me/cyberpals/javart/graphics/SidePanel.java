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
    ScrollPaneLayout scrollPaneLayout;
    private int x = 0, y = 0;

    public SidePanel(MainFrame instance, ToolManager toolManager) {
        super();

        this.instance = instance;
        this.toolManager = toolManager;
        this.layout = new GridBagLayout();
        this.scrollPaneLayout = new ScrollPaneLayout();
        this.scrollPaneLayout.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.scrollPaneLayout.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //setup Layout
        this.layout.columnWidths = new int[]{75, 75};
        this.layout.rowHeights = new int[]{};
        this.layout.columnWeights = new double[]{0.0, 0.0};
        this.layout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

        this.setLayout(this.layout);
        this.setVisible(true);
        setupButtons();
    }

    private void setupButtons() {

        //shapes
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("t1"),
                toolManager,
                ToolDetails.OVAL
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("t2"),
                toolManager,
                ToolDetails.RECTANGLE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("t3"),
                toolManager,
                ToolDetails.RHOMBUS
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("t4"),
                toolManager,
                ToolDetails.TRIANGLE
        ));
        //combine
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("example1"),
                toolManager,
                ToolDetails.XOR
        ));
        // tools
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("example1"),
                toolManager.getPictureManager().getPicture("example1"),
                toolManager,
                ToolDetails.MOVE
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
        return new Dimension(150, instance.getHeight() - 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.fillRect(0, 0, getPreferredSize().width, getPreferredSize().height);

        // Draw a picture for example
        Dimension d = getPreferredSize();
        int topBar = instance.getInsets().top;
        toolManager.getPictureManager().getPicture("example1").drawPicture(g, 0, 0, d.width, d.height - topBar, 4);
    }
}