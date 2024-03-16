package me.cyberpals.javart.shapes;

import me.cyberpals.javart.vectors.Vector2Int;

public abstract class Shape {
    protected Vector2Int begin;
    protected Vector2Int end;

    public Shape(Vector2Int begin, Vector2Int end) {
        this.begin = begin;
        this.end = end;
    }

    public Vector2Int getBegin() {
        return begin;
    }

    public Vector2Int getEnd() {
        return end;
    }

    public abstract Boolean test(Vector2Int point);

    public abstract void resize(Vector2Int dPos);

    public abstract void showDetails(int offset);

    public void showDetails() {
        showDetails(0);
    }

    public void move(Vector2Int dPos) {
        begin = begin.add(dPos);
        end = end.add(dPos);
    }

    public void drawShape() {
        for (int y = begin.getY(); y <= end.getY(); y++) {
            for (int x = begin.getX(); x <= end.getX(); x++) {
                if (test(new Vector2Int(x, y))) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
