package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

public abstract class SimpleShape extends Shape {
    public SimpleShape(Vector2Int begin, Vector2Int end) {
        super(begin, end);
    }

    @Override
    public void resize(Vector2Int dPos) {
        //test if negative to edit begin instead of end
        if (end.getX() + dPos.getX() < begin.getX()) {
            begin.setX(end.getX() + dPos.getX());
        } else {
            end.setX(end.getX() + dPos.getX());
        }

        if (end.getY() + dPos.getY() < begin.getY()) {
            begin.setY(end.getY() + dPos.getY());
        } else {
            end.setY(end.getY() + dPos.getY());
        }
    }
}
