package uk.wwws.game.players;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.game.*;
import uk.wwws.net.Connection;

public class ConnectedPlayer extends AbstractPlayer implements Player {
    private @Nullable CheckersGame game = null;
    private @NotNull Connection connection;

    public ConnectedPlayer(@NotNull Connection connection) {
        this.connection = connection;
    }

    @Override
    public Move determineMove(@NotNull Game game) {
        return null;
    }

    public void setGame(@Nullable CheckersGame game) {
        this.game = game;
    }

    public @Nullable CheckersGame getGame() {
        return game;
    }

    public @NotNull Connection getConnection() {
        return connection;
    }
}
