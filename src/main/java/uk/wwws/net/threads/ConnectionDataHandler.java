package uk.wwws.net.threads;

import org.jetbrains.annotations.NotNull;
import uk.wwws.net.Connection;

public interface ConnectionDataHandler {

    /**
     *
     * @param data
     * @param c
     * @return whether the handler wants to continue further handling (false = should exit)
     */
    boolean handle(@NotNull String data, @NotNull Connection c);
}
