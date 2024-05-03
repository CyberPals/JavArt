package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

public class Rectangle extends SimpleShape {

    public Rectangle(Vector2Int begin, Vector2Int end) {
        super(begin, end);
    }

    @Override
    public Boolean test(Vector2Int point) {
        return point.getX() >= getBegin().getX()
                && point.getX() <= getEnd().getX()
                && point.getY() >= getBegin().getY()
                && point.getY() <= getEnd().getY();
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Rectangle:(" + getBegin() + "," + getEnd() + ")");
    }

    @Override
    public Shape copy() {
        return new Rectangle(getBegin(), getEnd());
    }
}
