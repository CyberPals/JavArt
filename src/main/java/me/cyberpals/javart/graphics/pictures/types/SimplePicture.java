package me.cyberpals.javart.graphics.pictures.types;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SimplePicture extends Picture {

    public SimplePicture(BufferedImage picture) {
        this.picture = picture;
    }

    @Override
    public void drawPicture(Graphics g, int x, int y, int width, int height) {
        g.drawImage(picture, x, y, width, height, null);
    }

    @Override
    public void drawPicture(Graphics g, int x, int y, int width, int height, int zoom) {
        g.drawImage(picture, x, y, width, height, null);
    }
}
