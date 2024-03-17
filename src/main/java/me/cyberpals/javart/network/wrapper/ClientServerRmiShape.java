package me.cyberpals.javart.network.wrapper;

import me.cyberpals.javart.network.ISavableShape;
import me.cyberpals.javart.network.SavableShape;
import me.cyberpals.javart.shapes.Shape;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class ClientServerRmiShape implements IClientServerRmi<Shape> {

    private int clientPort;
    private String clientIp;

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
            LocateRegistry.getRegistry(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initializeServer(int port) {
        try {
            LocateRegistry.createRegistry(port);
            SavableShape savableShape = new SavableShape();
            Naming.bind("savableShape", savableShape);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(Shape object, String name) {
        try {
            ISavableShape savableShape = (ISavableShape) Naming.lookup("rmi://" + clientIp + "/savableShape");
            savableShape.saveShape(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shape receive(String name) {
        Shape shape = null;
        try {
            ISavableShape savableShape = (ISavableShape) Naming.lookup("rmi://" + clientIp + "/savableShape");
            shape = savableShape.loadShape();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shape;
    }
}
