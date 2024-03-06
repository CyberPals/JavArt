package me.cyberpals.javart.shapes;

public abstract class Shape {
    private int begin;
    private int end;

    public Shape(int begin, int end) {
        this.begin = begin;
        this.end = end;
        return;
    }

    public abstract Boolean test(int point);

    public abstract void resize(int dPos);

    public abstract void showDetails(int offset);

    public void showDetails() {
        showDetails(0);
    }

    public void move(int dPos) {
        //TODO
    }

    public void drawShape() {
        //TODO
    }
}
