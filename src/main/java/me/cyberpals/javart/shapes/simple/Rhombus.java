package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.vectors.Vector2Float;
import me.cyberpals.javart.vectors.Vector2Int;

import static java.lang.Math.abs;

public class Rhombus extends SimpleShape {
    public Rhombus(Vector2Int begin, Vector2Int end) {
        super(begin, end);
    }

    public Boolean test(Vector2Int point) {
        //step 0 - verify if point in bound
        if (!(
                point.getX() >= getBegin().getX() &&
                        point.getX() <= getEnd().getX() &&
                        point.getY() >= getBegin().getY() &&
                        point.getY() <= getEnd().getY()
        )) return false;

        //step 1 - normalize point
        Vector2Int centeredPoint = point.sub(getBegin());
        Vector2Float normalizedPoint = new Vector2Float(
                (((float) centeredPoint.getX()) * 2.f / (end.getX() - begin.getX())) - 1.f,
                (((float) centeredPoint.getY()) * 2.f / (end.getY() - begin.getY())) - 1.f
        );

        //step 2 - verify if point in rhombus
        return abs(normalizedPoint.getX()) + abs(normalizedPoint.getY()) <= 1;
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Rhombus:(" + getBegin() + "," + getEnd() + ")");
    }

    @Override
    public Shape copy() {
        return new Rhombus(getBegin(), getEnd());
    }
}

