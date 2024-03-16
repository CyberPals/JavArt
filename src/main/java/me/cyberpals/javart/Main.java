package me.cyberpals.javart;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Shape r1 = new Rectangle(
                new Vector2Int(0, 0),
                new Vector2Int(3, 3)
        );

        Shape r2 = new Rectangle(
                new Vector2Int(2, 2),
                new Vector2Int(5, 5)
        );

        Shape un = new Xor(r1, r2);
        un.drawShape();
        System.out.println("-------------------");
        un.resize(new Vector2Int(6, 6));
        un.move(new Vector2Int(2, 2));
        un.drawShape();

    }
}