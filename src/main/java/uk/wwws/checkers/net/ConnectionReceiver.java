package uk.wwws.checkers.net;

public interface ConnectionReceiver {
    Thread spawnServer(int port);

    void stopServer();
}
