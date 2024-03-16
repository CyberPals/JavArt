package me.cyberpals.javart;

import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Xor;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Shape r1 = new Rectangle(
                new Vector2Int(5, 5),
                new Vector2Int(8, 8)
        );

        Shape r2 = new Rectangle(
                new Vector2Int(7, 7),
                new Vector2Int(10, 10)
        );

        Shape un = new Xor(r1, r2);
        un.drawShape();
        System.out.println("-------------------");
        un.resize(new Vector2Int(6, 6));
        un.move(new Vector2Int(2, 2));
        un.drawShape();

    }
}