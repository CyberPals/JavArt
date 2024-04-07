package me.cyberpals.javart.graphics.pictures.types;

public class PictureBox {
    int top, left, right, bottom;

    public PictureBox(int top, int left, int right, int bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    public int getTop() {
        return top;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }
    
}
