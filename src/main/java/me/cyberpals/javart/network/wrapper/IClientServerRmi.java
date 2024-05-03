package me.cyberpals.javart.network.wrapper;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public interface IClientServerRmi<T> {
    void initializeClient(int port, String ip);

    void initializeServer(int port);

    void send(T object, String name);

    T receive(String name) throws RemoteException, NotBoundException;
}
