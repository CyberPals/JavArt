package me.cyberpals.javart.network.wrapper;

public interface IClientServerRmi<T> {
    void initializeClient(int port, String ip);

    void initializeServer(int port);

    void send(T object, String name);

    T receive(String name);
}
