package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.vectors.Vector2Float;
import me.cyberpals.javart.vectors.Vector2Int;

import static java.lang.Math.abs;

public class Triangle extends SimpleShape {
    public Triangle(Vector2Int begin, Vector2Int end) {
        super(begin, end);
    }

    @Override
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
                (((float) centeredPoint.getX()) / (end.getX() - begin.getX())) - .5f,
                (((float) centeredPoint.getY()) / (end.getY() - begin.getY()))
        );

        //step 2 - verify if point in oval
        return abs(normalizedPoint.getX()) + abs(1 - normalizedPoint.getY() / 2f) <= 1f;
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Triangle:(" + getBegin() + "," + getEnd() + ")");
    }
}
