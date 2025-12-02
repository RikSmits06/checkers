package uk.wwws.game;

import java.util.HashSet;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface Game {
    boolean isGameOver();

    Player getTurn();

    Player getWinner();

    HashSet<? extends Move> getValidMoves();

    boolean isValidMove(@NotNull Move move);

    void doMove(@NotNull Move move);
}
