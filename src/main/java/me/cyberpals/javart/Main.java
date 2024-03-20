package me.cyberpals.javart;

import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.parser.Parser;
import me.cyberpals.javart.parser.arguments.PairArgument;
import me.cyberpals.javart.parser.arguments.SingleArgument;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Difference;
import me.cyberpals.javart.shapes.operations.Intersection;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        Parser parser = new Parser(args);
        ClientServerRmiShape clientServerRmiShape = new ClientServerRmiShape();

        Shape r1 = new Rectangle(new Vector2Int(0, 5), new Vector2Int(10, 10));
        Shape r2 = new Rectangle(new Vector2Int(5, 5), new Vector2Int(7, 7));
        Shape x = new Difference(r1, r2);
        Shape o3 = new Oval(new Vector2Int(0, 0), new Vector2Int(10, 10));
        Shape x2 = new Intersection(x, o3);

        parser.addArgument("-i", new SingleArgument() {
            @Override
            public void parse() {
                System.out.println("Server starting...");
                clientServerRmiShape.initializeServer(1099);
            }
        });
        parser.addArgument("-s", new PairArgument<>("localhost") {

            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                System.out.println("Saving shape...");
                clientServerRmiShape.initializeClient(1099, value);
                clientServerRmiShape.send(x2, "shape");
            }
        });
        parser.addArgument("-l", new PairArgument<>("localhost") {
            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                System.out.println("Loading shape...");
                clientServerRmiShape.initializeClient(1099, value);
                Shape shape = clientServerRmiShape.receive("shape");
                shape.drawShape();
                shape.showDetails();
            }
        });


        if (parser.size() > 0) {
            parser.parse();
        } else {
            x2.showDetails();
            x2.drawShape();
        }
    }
}