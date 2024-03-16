package me.cyberpals.javart.shapes.simple;

import me.cyberpals.javart.vectors.Vector2Float;
import me.cyberpals.javart.vectors.Vector2Int;

public class Oval extends SimpleShape {
    public Oval(Vector2Int begin, Vector2Int end) {
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
                (((float) centeredPoint.getX()) * 2.f / (end.getX() - begin.getX())) - 1.f,
                (((float) centeredPoint.getY()) * 2.f / (end.getY() - begin.getY())) - 1.f
        );

        //step 2 - verify if point in oval
        return normalizedPoint.getX() * normalizedPoint.getX() + normalizedPoint.getY() * normalizedPoint.getY() <= 1;
    }

    @Override
    public void showDetails(int offset) {
        System.out.println("  ".repeat(offset) + " + Oval:(" + getBegin() + "," + getEnd() + ")");
    }
}
