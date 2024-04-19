package me.cyberpals.javart.graphics.pictures.types;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Picture {

    BufferedImage picture;

    public abstract void drawPicture(Graphics g, int x, int y, int width, int height);

    public abstract void drawPicture(Graphics g, int x, int y, int width, int height, int zoom);

    public BufferedImage getPicture() {
        return picture;
    }
}
