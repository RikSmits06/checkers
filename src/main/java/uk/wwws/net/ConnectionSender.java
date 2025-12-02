package uk.wwws.net;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.net.exceptions.FailedToConnectException;
import uk.wwws.net.exceptions.FailedToCreateStreamsException;

public interface ConnectionSender {
    default @Nullable Connection connect(@NotNull String host, int port) {
        try {
            return new Connection(host, port);
        } catch (FailedToConnectException | FailedToCreateStreamsException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
