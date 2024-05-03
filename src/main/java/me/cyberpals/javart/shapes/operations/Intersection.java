package me.cyberpals.javart.shapes.operations;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

public class Intersection extends Operation {
    public Intersection(Shape shape1, Shape shape2) {
        super(shape1, shape2);
    }

    @Override
    public Boolean test(Vector2Int point) {
        return child1.test(point) && (child2.test(point));
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Intersection:(" + getBegin() + "," + getEnd() + ")");
        child1.showDetails(offset + 1);
        child2.showDetails(offset + 1);
    }

    @Override
    public Shape copy() {
        return new Intersection(child1.copy(), child2.copy());
    }
}
