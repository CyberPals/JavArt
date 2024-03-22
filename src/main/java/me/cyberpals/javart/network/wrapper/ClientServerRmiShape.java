package me.cyberpals.javart.network.wrapper;

import me.cyberpals.javart.network.ISavableShape;
import me.cyberpals.javart.network.SavableShape;
import me.cyberpals.javart.shapes.Shape;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientServerRmiShape implements IClientServerRmi<Shape> {

    private int clientPort;
    private String clientIp;

    private Registry clientRegistry;

    public ClientServerRmiShape() {
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    @Override
    public void initializeClient(int port, String ip) {
        clientPort = port;
        clientIp = ip;
        try {
            System.out.println(ip);
            clientRegistry = LocateRegistry.getRegistry(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initializeServer(int port) {
        try {
            Registry server = LocateRegistry.createRegistry(port);
            SavableShape savableShape = new SavableShape();
            server.bind("savableShape", savableShape);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Shape object, String name) {
        try {
            System.out.println(clientRegistry);
            ISavableShape savableShape = (ISavableShape) clientRegistry.lookup("savableShape");
            savableShape.saveShape(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shape receive(String name) {
        Shape shape = null;
        try {
            ISavableShape savableShape = (ISavableShape) clientRegistry.lookup("savableShape");
            shape = savableShape.loadShape();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shape;
    }
}
