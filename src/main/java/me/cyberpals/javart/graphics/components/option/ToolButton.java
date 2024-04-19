package me.cyberpals.javart.graphics.components.option;

import me.cyberpals.javart.graphics.pictures.types.Picture;
import me.cyberpals.javart.graphics.tools.ToolDetails;
import me.cyberpals.javart.graphics.tools.ToolManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolButton extends JButton {
    Picture bg, fg;
    ToolManager toolManager;
    ToolDetails toolDetails;


    public ToolButton(Picture bg, Picture fg, ToolManager toolManager, ToolDetails toolDetails) {
        super("");
        this.bg = bg;
        this.fg = fg;
        this.toolManager = toolManager;
        this.toolDetails = toolDetails;

        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toolManager.setTool(toolDetails);
            }
        });

        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(75, 75);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //super.paintComponent(g2d);

        //draw blue background
        int offset = 7;
        int fgOffset = 8;
        bg.drawPicture(g2d, offset, offset, getWidth() - 2 * offset, getHeight() - 2 * offset, 4);
        fg.drawPicture(g2d, fgOffset + offset, fgOffset + offset, getWidth() - 2 * (offset + fgOffset), getHeight() - 2 * (offset + fgOffset), 4);
    }
}
