package me.cyberpals.javart.shapes.operations;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

public class Union extends Operation {
    public Union(Shape shape1, Shape shape2) {
        super(shape1, shape2);
    }

    @Override
    public Boolean test(Vector2Int point) {
        return child1.test(point) || child2.test(point);
    }

    @Override
    public void resize(Vector2Int dPos) {
        //TODO
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Union:(" + getBegin() + "," + getEnd() + ")");
        child1.showDetails(offset + 1);
        child2.showDetails(offset + 1);
    }
}
