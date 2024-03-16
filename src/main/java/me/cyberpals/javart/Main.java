package me.cyberpals.javart;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Union;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.shapes.simple.Triangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Shape r1 = new Rectangle(
                new Vector2Int(1, 1),
                new Vector2Int(8, 8)
        );

        Shape r2 = new Rectangle(
                new Vector2Int(2, 2),
                new Vector2Int(12, 5)
        );

        Shape r3 = new Oval(
                new Vector2Int(5, 5),
                new Vector2Int(15, 15)
        );

        Shape r4 = new Oval(
                new Vector2Int(0, 0),
                new Vector2Int(20, 20)
        );

        Shape r5 = new Triangle(
                new Vector2Int(0, 0),
                new Vector2Int(20, 20)
        );

        Shape it = new Union(new Xor(r1, r2), r3);

        it.drawShape();
        it.showDetails();
    }
}