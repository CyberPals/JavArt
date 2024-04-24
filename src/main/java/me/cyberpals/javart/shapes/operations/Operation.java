package me.cyberpals.javart.shapes.operations;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Float;
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

    private Vector2Int calculateResizePos(Vector2Int corner1, Vector2Int corner2, Vector2Int shapePos, Vector2Int deltaPos) {
        if (corner1.equals(corner2)) return shapePos;

        Vector2Float denom = new Vector2Float(1f / (corner2.getX() - corner1.getX()), 1f / (corner2.getY() - corner1.getY()));

        return new Vector2Int(
                (int) ((shapePos.getX() - corner1.getX()) * (corner2.getX() + deltaPos.getX() - corner1.getX()) * denom.getX()) + corner1.getX(),
                (int) ((shapePos.getY() - corner1.getY()) * (corner2.getY() + deltaPos.getY() - corner1.getY()) * denom.getY()) + corner1.getY()
        );
    }

    @Override
    public void resize(Vector2Int dPos) {
        //shape 1
        Vector2Int shapeBegin = child1.getBegin();
        Vector2Int shapeEnd = child1.getEnd();
        Vector2Int newShapeBegin = calculateResizePos(getBegin(), getEnd(), shapeBegin, dPos);
        Vector2Int newShapeEnd = calculateResizePos(getBegin(), getEnd(), shapeEnd, dPos);
        child1.setBegin(newShapeBegin);
        child1.setEnd(newShapeEnd);

        //shape 2
        shapeBegin = child2.getBegin();
        shapeEnd = child2.getEnd();
        newShapeBegin = calculateResizePos(getBegin(), getEnd(), shapeBegin, dPos);
        newShapeEnd = calculateResizePos(getBegin(), getEnd(), shapeEnd, dPos);
        child2.setBegin(newShapeBegin);
        child2.setEnd(newShapeEnd);

        //operation
        setEnd(getEnd().add(dPos));
    }

    public Shape getChild1() {
        return child1;
    }

    public Shape getChild2() {
        return child2;
    }

    @Override
    public void move(Vector2Int dPos) {
        super.move(dPos);
        child1.move(dPos);
        child2.move(dPos);
    }
}
