package me.cyberpals.javart.shapes;

import me.cyberpals.javart.vectors.Vector2Int;

import java.io.Serializable;

public abstract class Shape implements Serializable {
    protected Vector2Int begin;
    protected Vector2Int end;

    public Shape(Vector2Int begin, Vector2Int end) {
        this.begin = new Vector2Int(
                Math.min(begin.getX(), end.getX()),
                Math.min(begin.getY(), end.getY())
        );
        this.end = new Vector2Int(
                Math.max(begin.getX(), end.getX()),
                Math.max(begin.getY(), end.getY())
        );
    }

    public Vector2Int getBegin() {
        return begin;
    }

    public void setBegin(Vector2Int begin) {
        this.begin = begin;
    }

    public Vector2Int getEnd() {
        return end;
    }

    public void setEnd(Vector2Int end) {
        this.end = end;
    }

    public abstract Boolean test(Vector2Int point);

    public Boolean inBound(Vector2Int point) {
        return (point.getX() >= begin.getX() && point.getX() <= end.getX() &&
                point.getY() >= begin.getY() && point.getY() <= end.getY());
    }


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
        //create 2 temp variables with correct order
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
