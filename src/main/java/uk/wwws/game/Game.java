package uk.wwws.game;

import java.util.List;

public interface Game {
    boolean isGameOver();

    Player getTurn();

    Player getWinner();

    List<? extends Move> getValidMoves();

    boolean isValidMove(Move move);

    void doMove(Move move);
}
