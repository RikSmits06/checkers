package uk.wwws.net;

import java.net.Socket;
import org.jetbrains.annotations.NotNull;

public interface ConnectionReceiver {
    Thread spawnServer(int port);
    void stopServer();
}
