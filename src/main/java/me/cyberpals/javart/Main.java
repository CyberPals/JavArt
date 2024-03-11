package me.cyberpals.javart;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.operations.Union;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

public class Main {
    public static void main(String[] args) {
        Shape r1 = new Rectangle(
                new Vector2Int(1, 1),
                new Vector2Int(8, 8)
        );

        Shape r2 = new Rectangle(
                new Vector2Int(2, 2),
                new Vector2Int(7, 5)
        );

        Shape r3 = new Oval(
                new Vector2Int(5, 5),
                new Vector2Int(15, 15)
        );

        Shape r4 = new Oval(
                new Vector2Int(0, 0),
                new Vector2Int(20, 20)
        );

        Shape it = new Union(new Intersection(r1, r2), r3);

        r4.drawShape();
        it.showDetails();
    }
}