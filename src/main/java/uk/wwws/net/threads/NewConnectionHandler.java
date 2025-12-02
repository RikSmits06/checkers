package uk.wwws.net.threads;

import java.net.Socket;
import org.jetbrains.annotations.NotNull;

public interface NewConnectionHandler {
    void handle(@NotNull Socket socket);
}
