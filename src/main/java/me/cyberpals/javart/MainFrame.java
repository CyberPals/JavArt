package me.cyberpals.javart;

import me.cyberpals.javart.graphics.CanvasPanel;
import me.cyberpals.javart.graphics.OptionPanel;
import me.cyberpals.javart.graphics.SidePanel;
import me.cyberpals.javart.graphics.pictures.PictureManager;
import me.cyberpals.javart.graphics.tools.ToolManager;
import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.parser.Parser;
import me.cyberpals.javart.parser.arguments.PairArgument;
import me.cyberpals.javart.parser.arguments.SingleArgument;
import me.cyberpals.javart.serialisation.SaveManager;
import me.cyberpals.javart.shapes.Shape;
import me.cyberpals.javart.shapes.operations.Union;
import me.cyberpals.javart.shapes.simple.Oval;
import me.cyberpals.javart.shapes.simple.Rectangle;
import me.cyberpals.javart.vectors.Vector2Int;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {

    //panels
    CanvasPanel canvas;
    SidePanel sidePanel;
    OptionPanel optionPanel;


    ToolManager toolManager;
    PictureManager pictureManager;

    static int serverPort = 1099;

    public MainFrame() {
        super("JavArt");

        //setup diferent panels
        try {
            pictureManager = new PictureManager("/testing.png", 16, 16);
            setupTextures();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        toolManager = new ToolManager(pictureManager);

        canvas = new CanvasPanel(this, toolManager);
        sidePanel = new SidePanel(this, toolManager);
        optionPanel = new OptionPanel(this, toolManager);

        toolManager.setCanvas(canvas);

        //setup frame

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(optionPanel, BorderLayout.SOUTH);
        this.add(sidePanel, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);
        this.pack();
    }

    public static void main(String[] args) {
        Parser parser = new Parser(args);
        ClientServerRmiShape clientServerRmiShape = new ClientServerRmiShape();


        parser.addArgument("-s", new PairArgument<>(1099) {

            @Override
            public Integer interpret(String value) {
                return Integer.parseInt(value);
            }

            @Override
            public void parse(Integer value) {
                int port = value;
                System.out.println("Server starting...");
                clientServerRmiShape.initializeServer(port);
                System.out.println("Server OK");
            }

        });


        parser.addArgument("-t", new SingleArgument() {

            @Override
            public void parse() {

                Shape t1 = new Rectangle(
                        new Vector2Int(0, 0),
                        new Vector2Int(2, 2)
                );
                Shape t2 = new Rectangle(
                        new Vector2Int(2, 2),
                        new Vector2Int(6, 6)
                );
                Shape u = new Union(t1, t2);
                u.drawShape();
                u.showDetails();
            }
        });

        parser.addArgument("-e", new PairArgument<>("localhost") {

            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                ClientServerRmiShape csrs = new ClientServerRmiShape();
                csrs.initializeClient(1099,value);

                Shape t1 = new Oval(
                        new Vector2Int(0, 0),
                        new Vector2Int(2, 2)
                );
                Shape t2 = new Rectangle(
                        new Vector2Int(2, 2),
                        new Vector2Int(6, 6)
                );
                Shape u = new Union(t1, t2);
                u.drawShape();
                u.showDetails();
                csrs.send(u,"test");
            }
        });

        parser.addArgument("-r", new PairArgument<>("localhost") {

            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                ClientServerRmiShape csrs = new ClientServerRmiShape();
                csrs.initializeClient(1099,value);
                Shape u = csrs.receive("test");
                u.drawShape();
                u.showDetails();

            }
        });
        parser.addArgument("-h", new SingleArgument() {
            @Override
            public void parse() {
                System.out.println("-s [port default=1099]\t executes the rmi server at the port addressed. if no ports are selected, the port will automatically be 1099. To be able to use \"-e\" and\"-n\", use this command on an other terminal.\n" +
                        "-t\t  shows a shape in the terminal.\n" +
                        "-e [ip default=localhost]\t sends a test shape to the server at port 1099 with the ip addressed. If no ip are selected, the ip address will automatically be localhost.\n" +
                        "-r [ip default=localhost]\t asks to the server at port 1099 with the ip addressed, the test shape. If no ip are selected, the ip address will automatically be localhost.\n");
            }
        });

        if (parser.size() > 0) {
            parser.parse();
        } else {
            new MainFrame();
        }
    }


    private void setupTextures() {
        this.pictureManager.addExtendablePicture("example1", 0, 1, 4, 4, 4, 4);
        this.pictureManager.addExtendablePicture("example2", 1, 0, 4, 4, 4, 4);
        this.pictureManager.addPicture("t1", 0, 2);
        this.pictureManager.addPicture("t2", 1, 2);
        this.pictureManager.addPicture("t3", 2, 2);
        this.pictureManager.addPicture("t4", 3, 2);
        this.pictureManager.addPicture("t5", 0, 3);
        this.pictureManager.addExtendablePicture("button", 2, 0, 4, 4, 4, 4);
    }
}