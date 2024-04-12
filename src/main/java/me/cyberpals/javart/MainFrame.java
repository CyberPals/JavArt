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

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class MainFrame extends JFrame {

    //panels
    CanvasPanel canvas;
    SidePanel sidePanel;


    ToolManager toolManager;

    PictureManager pictureManager;

    public MainFrame() {
        super("JavArt");

        try {
            pictureManager = new PictureManager("/testing.png", 16, 16);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        toolManager = new ToolManager(pictureManager);

        canvas = new CanvasPanel(this, toolManager);
        sidePanel = new SidePanel(this, toolManager);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(new OptionPanel(this), BorderLayout.SOUTH);
        this.add(sidePanel, BorderLayout.WEST);
        this.add(canvas, BorderLayout.CENTER);

        this.setLocationRelativeTo(null);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyChar() == 'a') {
                    canvas.toolId = 0;
                    System.out.println("Tool: Draw");
                    setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                } else if (e.getKeyChar() == 'z') {
                    canvas.toolId = 1;
                    System.out.println("Tool: XOR");
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
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

        parser.addArgument("-s", new PairArgument<>("Baptiste") {
            @Override
            public String interpret(String value) {
                return value;
            }

            @Override
            public void parse(String value) {
                System.out.println("Bonsoir " + value + " !");
            }
        });


        if (parser.size() > 0) {
            parser.parse();
        } else {
            new MainFrame();
        }
    }
}