package me.cyberpals.javart.graphics.pictures.types;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ExtendablePicture extends Picture {
    PictureBox bounds;

    BufferedImage[][] boundPictures;


    public ExtendablePicture(BufferedImage picture, PictureBox bounds) {
        this.bounds = bounds;
        this.picture = picture;

        boundPictures = new BufferedImage[3][3];

        // calculates different parts of the picture

        boundPictures[0][0] = picture.getSubimage(0, 0, bounds.getLeft(), bounds.getTop());
        boundPictures[1][0] = picture.getSubimage(bounds.getLeft(), 0, picture.getWidth() - bounds.getLeft() - bounds.getRight(), bounds.getTop());
        boundPictures[2][0] = picture.getSubimage(picture.getWidth() - bounds.getRight(), 0, bounds.getRight(), bounds.getTop());

        boundPictures[0][1] = picture.getSubimage(0, bounds.getTop(), bounds.getLeft(), picture.getHeight() - bounds.getTop() - bounds.getBottom());
        boundPictures[1][1] = picture.getSubimage(bounds.getLeft(), bounds.getTop(), picture.getWidth() - bounds.getLeft() - bounds.getRight(), picture.getHeight() - bounds.getTop() - bounds.getBottom());
        boundPictures[2][1] = picture.getSubimage(picture.getWidth() - bounds.getRight(), bounds.getTop(), bounds.getRight(), picture.getHeight() - bounds.getTop() - bounds.getBottom());

        boundPictures[0][2] = picture.getSubimage(0, picture.getHeight() - bounds.getBottom(), bounds.getLeft(), bounds.getBottom());
        boundPictures[1][2] = picture.getSubimage(bounds.getLeft(), picture.getHeight() - bounds.getBottom(), picture.getWidth() - bounds.getLeft() - bounds.getRight(), bounds.getBottom());
        boundPictures[2][2] = picture.getSubimage(picture.getWidth() - bounds.getRight(), picture.getHeight() - bounds.getBottom(), bounds.getRight(), bounds.getBottom());
    }

    @Override
    public void drawPicture(Graphics g, int x, int y, int width, int height) {
        // draw the corners
        g.drawImage(boundPictures[0][0], x, y, bounds.getLeft(), bounds.getTop(), null);
        g.drawImage(boundPictures[2][0], x + width - bounds.getRight(), y, bounds.getRight(), bounds.getTop(), null);
        g.drawImage(boundPictures[0][2], x, y + height - bounds.getBottom(), bounds.getLeft(), bounds.getBottom(), null);
        g.drawImage(boundPictures[2][2], x + width - bounds.getRight(), y + height - bounds.getBottom(), bounds.getRight(), bounds.getBottom(), null);

        // draw the sides
        g.drawImage(boundPictures[1][0], x + bounds.getLeft(), y, width - bounds.getLeft() - bounds.getRight(), bounds.getTop(), null);
        g.drawImage(boundPictures[0][1], x, y + bounds.getTop(), bounds.getLeft(), height - bounds.getTop() - bounds.getBottom(), null);
        g.drawImage(boundPictures[2][1], x + width - bounds.getRight(), y + bounds.getTop(), bounds.getRight(), height - bounds.getTop() - bounds.getBottom(), null);
        g.drawImage(boundPictures[1][2], x + bounds.getLeft(), y + height - bounds.getBottom(), width - bounds.getLeft() - bounds.getRight(), bounds.getBottom(), null);

        // draw the center
        g.drawImage(boundPictures[1][1], x + bounds.getLeft(), y + bounds.getTop(), width - bounds.getLeft() - bounds.getRight(), height - bounds.getTop() - bounds.getBottom(), null);
    }

    @Override
    public void drawPicture(Graphics g, int x, int y, int width, int height, int zoom) {
        // draw the corners
        g.drawImage(boundPictures[0][0], x, y, bounds.getLeft() * zoom, bounds.getTop() * zoom, null);
        g.drawImage(boundPictures[2][0], x + width - bounds.getRight() * zoom, y, bounds.getRight() * zoom, bounds.getTop() * zoom, null);
        g.drawImage(boundPictures[0][2], x, y + height - bounds.getBottom() * zoom, bounds.getLeft() * zoom, bounds.getBottom() * zoom, null);
        g.drawImage(boundPictures[2][2], x + width - bounds.getRight() * zoom, y + height - bounds.getBottom() * zoom, bounds.getRight() * zoom, bounds.getBottom() * zoom, null);

        // draw the sides
        g.drawImage(boundPictures[1][0], x + bounds.getLeft() * zoom, y, width - ((bounds.getLeft() + bounds.getRight()) * zoom), bounds.getTop() * zoom, null);
        g.drawImage(boundPictures[0][1], x, y + bounds.getTop() * zoom, bounds.getLeft() * zoom, height - ((bounds.getTop() + bounds.getBottom()) * zoom), null);
        g.drawImage(boundPictures[2][1], x + width - bounds.getRight() * zoom, y + bounds.getTop() * zoom, bounds.getRight() * zoom, height - ((bounds.getTop() + bounds.getBottom()) * zoom), null);
        g.drawImage(boundPictures[1][2], x + bounds.getLeft() * zoom, y + height - bounds.getBottom() * zoom, width - ((bounds.getLeft() + bounds.getRight()) * zoom), bounds.getBottom() * zoom, null);

        // draw the center
        g.drawImage(boundPictures[1][1], x + bounds.getLeft() * zoom, y + bounds.getTop() * zoom, width - ((bounds.getLeft() + bounds.getRight()) * zoom), height - ((bounds.getTop() + bounds.getBottom()) * zoom), null);
    }
}
