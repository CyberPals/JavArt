package me.cyberpals.javart.network;

import me.cyberpals.javart.shapes.Shape;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SavableShape extends UnicastRemoteObject implements ISavableShape {

    Shape saved;

    public SavableShape() throws RemoteException {
    }

    @Override
    public void saveShape(Shape shape) throws RemoteException {
        saved = shape;
    }

    @Override
    public Shape loadShape() throws RemoteException {
        return saved;
    }
}
