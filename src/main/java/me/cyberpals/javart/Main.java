package me.cyberpals.javart;

import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Difference;
import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        ClientServerRmiShape clientServerRmiShape = new ClientServerRmiShape();
        Shape r1 = new Rectangle(new Vector2Int(0, 5), new Vector2Int(10, 10));
        Shape r2 = new Rectangle(new Vector2Int(5, 5), new Vector2Int(7, 7));

        Shape x = new Difference(r1, r2);

        Shape o3 = new Oval(new Vector2Int(0, 0), new Vector2Int(10, 10));

        Shape x2 = new Intersection(x, o3);

        if (args[0] == null) {
            x2.drawShape();
            x2.showDetails();
        } else if (args[0].equals("-i")) {
            System.out.println("Server starting...");
            clientServerRmiShape.initializeServer(1099);
        } else if (args[0].equals("-s")) {
            System.out.println("Saving shape...");
            clientServerRmiShape.initializeClient(1099, "localhost");
            clientServerRmiShape.send(x2, "shape");
        } else if (args[0].equals("-l")) {
            System.out.println("Loading shape...");
            clientServerRmiShape.initializeClient(1099, "localhost");
            Shape shape = clientServerRmiShape.receive("shape");
            shape.drawShape();
            shape.showDetails();
        }
    }
}