package me.cyberpals.javart.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISavable<T> extends Remote {
    void saveShape(T shape) throws RemoteException;

    T loadShape() throws RemoteException;
}
