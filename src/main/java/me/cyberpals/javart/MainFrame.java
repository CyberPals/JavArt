package me.cyberpals.javart;

import me.cyberpals.javart.graphics.CanvasPanel;
import me.cyberpals.javart.graphics.OptionPanel;
import me.cyberpals.javart.graphics.SidePanel;
import me.cyberpals.javart.graphics.tools.ToolManager;
import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.parser.Parser;
import me.cyberpals.javart.parser.arguments.SingleArgument;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    CanvasPanel canvas;
    ToolManager toolManager;

    public MainFrame() {
        super("JavArt");

        toolManager = new ToolManager();

        canvas = new CanvasPanel(this, toolManager);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(new OptionPanel(this), BorderLayout.SOUTH);
        this.add(new SidePanel(this), BorderLayout.WEST);
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


        if (parser.size() > 0) {
            parser.parse();
        } else {
            new MainFrame();
        }
    }
}