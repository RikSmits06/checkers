package uk.wwws.net;

public interface ConnectionReceiver {
    Thread spawnServer(int port);

    void stopServer();
}
