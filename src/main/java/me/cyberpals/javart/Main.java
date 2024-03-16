package me.cyberpals.javart;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Difference;
import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Shape r1 = new Rectangle(new Vector2Int(0, 5), new Vector2Int(10, 10));
        Shape r2 = new Rectangle(new Vector2Int(5, 5), new Vector2Int(7, 7));

        Shape x = new Difference(r1, r2);

        Shape o3 = new Oval(new Vector2Int(0, 0), new Vector2Int(10, 10));

        Shape x2 = new Intersection(x, o3);

        x2.drawShape();
        x2.showDetails();
    }
}