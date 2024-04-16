package me.cyberpals.javart.graphics.components.option;

import java.awt.*;

import static javax.swing.UIManager.getInsets;

public class ToolLayout implements LayoutManager {

    private int minWidth = 0, minHeight = 0;

    public ToolLayout(int minWidth, int minHeight) {
        this.minWidth = minWidth;
        this.minHeight = minHeight;
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {

    }

    @Override
    public void removeLayoutComponent(Component comp) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Insets insets = getInsets(parent);
        return new Dimension(10, 0);
    }

    /* Required by LayoutManager. */
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Dimension dim = new Dimension(0, 0);
        int nComps = parent.countComponents();

        //Always add the container's insets!
        Insets insets = getInsets(parent);
        dim.width = minWidth + insets.left + insets.right;
        dim.height = minHeight + insets.top + insets.bottom;

        boolean sizeUnknown = false;

        return dim;
    }


    @Override
    public void layoutContainer(Container parent) {

    }
}
