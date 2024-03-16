package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

public abstract class SimpleShape extends Shape {
    public SimpleShape(Vector2Int begin, Vector2Int end) {
        super(begin, end);
    }

    @Override
    public void resize(Vector2Int dPos) {
        end = end.add(dPos);
    }
}
