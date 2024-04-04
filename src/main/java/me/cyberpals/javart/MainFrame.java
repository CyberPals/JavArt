package me.cyberpals.javart;

import me.cyberpals.javart.graphics.CanvasPanel;
import me.cyberpals.javart.graphics.OptionPanel;
import me.cyberpals.javart.graphics.SidePanel;
import me.cyberpals.javart.network.wrapper.ClientServerRmiShape;
import me.cyberpals.javart.parser.Parser;
import me.cyberpals.javart.parser.arguments.SingleArgument;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        super("JavArt");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setMinimumSize(new Dimension(800, 600));
        this.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(new OptionPanel(this), BorderLayout.SOUTH);
        this.add(new SidePanel(this), BorderLayout.WEST);
        this.add(new CanvasPanel(this), BorderLayout.CENTER);

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


        if (parser.size() > 0) {
            parser.parse();
        } else {
            new MainFrame();
        }
    }
}