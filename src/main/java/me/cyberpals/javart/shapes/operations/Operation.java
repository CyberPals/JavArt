package me.cyberpals.javart.shapes.operations;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Int;

import static java.lang.Math.max;
import static java.lang.Math.min;

public abstract class Operation extends Shape {
    protected Shape child1;
    protected Shape child2;

    public Operation(Shape shape1, Shape shape2) {
        super(
                new Vector2Int(
                        min(shape1.getBegin().getX(), shape2.getBegin().getX()),
                        min(shape1.getBegin().getY(), shape2.getBegin().getY())
                ),
                new Vector2Int(
                        max(shape1.getEnd().getX(), shape2.getEnd().getX()),
                        max(shape1.getEnd().getY(), shape2.getEnd().getY())
                )
        );
        this.child1 = shape1;
        this.child2 = shape2;
    }

    @Override
    public void resize(Vector2Int dPos) {

    }
    
    @Override
    public void move(Vector2Int dPos) {
        super.move(dPos);
        child1.move(dPos);
        child2.move(dPos);
    }
}
