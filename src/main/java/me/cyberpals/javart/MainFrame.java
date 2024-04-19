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


        parser.addArgument("-i", new SingleArgument() {
            @Override
            public void parse() {
                System.out.println("Server starting...");
                clientServerRmiShape.initializeServer(1099);
            }
        });

        parser.addArgument("-ts", new PairArgument<>("test") {
            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                SaveManager sm = new SaveManager();

                Shape t1 = new Rectangle(
                        new Vector2Int(0, 0),
                        new Vector2Int(4, 4)
                );
                Shape t2 = new Rectangle(
                        new Vector2Int(2, 2),
                        new Vector2Int(6, 6)
                );
                System.out.println("save following shape");
                Shape u = new Union(t1, t2);
                u.drawShape();
                u.showDetails();
                try {
                    sm.saveShape(u, value);
                } catch (IOException e) {
                    System.out.println("error while saving the shape");
                }
            }
        });

        parser.addArgument("-tl", new PairArgument<>("test") {
            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                SaveManager sm = new SaveManager();
                System.out.println("load shape");
                try {
                    Shape p = sm.loadShape(value);
                    p.drawShape();
                    p.showDetails();
                } catch (IOException e) {
                    System.out.println("file not found");
                } catch (ClassNotFoundException e) {
                    System.out.println("unkown error");
                }
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