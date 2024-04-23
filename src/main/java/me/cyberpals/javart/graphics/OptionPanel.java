package me.cyberpals.javart.graphics;

import me.cyberpals.javart.MainFrame;
import me.cyberpals.javart.graphics.components.option.ToolButton;
import me.cyberpals.javart.graphics.tools.ToolDetails;
import me.cyberpals.javart.graphics.tools.ToolManager;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    MainFrame instance;
    ToolManager toolManager;

    // for layout purpose
    GridBagLayout layout;
    private int x = 0;

    public OptionPanel(MainFrame instance, ToolManager toolManager) {
        super();

        this.instance = instance;
        this.toolManager = toolManager;
        this.layout = new GridBagLayout();

        //setup layout
        this.layout.columnWidths = new int[]{};
        this.layout.rowHeights = new int[]{50};
        this.layout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
        this.layout.rowWeights = new double[]{0.0};

        this.setLayout(this.layout);
        this.setVisible(true);
        setupButtons();

        toolManager.setOptionPanel(this);
    }

    private void setupButtons() {
        addFinalPanel();

        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Save_local"),
                toolManager,
                ToolDetails.SAVE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Load_local"),
                toolManager,
                ToolDetails.LOAD
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Save_server"),
                toolManager,
                ToolDetails.SAVE
        ));
        addButtonTool(new ToolButton(
                toolManager.getPictureManager().getPicture("Button"),
                toolManager.getPictureManager().getPicture("Load_server"),
                toolManager,
                ToolDetails.LOAD
        ));
    }

    private void addButtonTool(JButton b) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = x;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0;
        c.insets = (x == 0) ? new Insets(0, 50, 0, 0) : new Insets(0, 0, 0, 0);
        x++;
        this.add(b, c);
    }

    private void addFinalPanel() {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.gridx = x++;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0f;
        c.insets = (x == 0) ? new Insets(0, 50, 0, 0) : new Insets(0, 0, 0, 0);

        JPanel panel = new JPanel();
        panel.setOpaque(false);

        this.add(panel, c);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(instance.getWidth(), 50);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.PINK);
        toolManager.getPictureManager().getPicture("Down_panel").drawPicture(g, 0, 0, getPreferredSize().width, getPreferredSize().height, 4);
        toolManager.getPictureManager().getPicture("Left_panel").drawPicture(g, 0, 0, 50, 50, 2);
        toolManager.getToolPics().drawPicture(g, 0, 0, 50, 50);
    }
}
