package uk.wwws.net;

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
