package uk.wwws.game;

import org.jetbrains.annotations.NotNull;

public abstract class AbstractPlayer implements Player {
    public AbstractPlayer() {
    }

    public abstract Move determineMove(@NotNull Game game);
}