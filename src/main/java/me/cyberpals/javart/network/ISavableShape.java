package me.cyberpals.javart.network;

import me.cyberpals.javart.shapes.Shape;

import java.rmi.RemoteException;

public interface ISavableShape extends ISavable<Shape> {
    void saveShape(Shape shape) throws RemoteException;

    Shape loadShape() throws RemoteException;
}
