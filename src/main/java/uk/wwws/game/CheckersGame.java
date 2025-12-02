package uk.wwws.game;

import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class CheckersGame implements Game {
    private Board board;
    private HashMap<Checker, Player> players = new HashMap<>();

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public @NotNull Player getTurn() {
        return null;
    }

    @Override
    public @NotNull Player getWinner() {
        return null;
    }

    @Override
    public @NotNull List<? extends Move> getValidMoves() {
        return List.of();
    }

    @Override
    public boolean isValidMove(@NotNull Move move) {
        return false;
    }

    @Override
    public void doMove(@NotNull Move move) {

    }
}
