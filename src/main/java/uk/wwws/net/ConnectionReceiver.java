package uk.wwws.net;

import java.net.Socket;
import org.jetbrains.annotations.NotNull;

public interface ConnectionReceiver {
    void handleConnection(@NotNull Connection c);
    Thread spawnServer(int port);
    void stopServer();
}
