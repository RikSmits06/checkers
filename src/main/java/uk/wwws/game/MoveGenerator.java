package uk.wwws.game;

import java.util.Arrays;
import java.util.HashSet;
import org.jetbrains.annotations.NotNull;
import uk.wwws.game.bitboards.Bitboard;
import uk.wwws.game.bitboards.CaptureBitboard;
import uk.wwws.game.bitboards.MoveBitboard;

/*
I am aware that bitboards are way more effective but this is a quick and dirty prototype implemetation.
 */
public class MoveGenerator {
    public static HashSet<CheckersMove> generateMoves(@NotNull Board board, @NotNull Checker turn) {
        HashSet<CheckersMove> legalMoves = new HashSet<>();

        for (int i = 0; i < Board.DIM * Board.DIM; i++) {
            if (board.getField(i).sameColor(turn)) {
                generateMovesForPiece(board, i, legalMoves);
                generateCapturesForPiece(board, i, legalMoves);
            }
        }

        return legalMoves;
    }

    private static void generateMovesForPiece(@NotNull Board board, int index,
                                              @NotNull HashSet<CheckersMove> legalMoves) {
        Checker piece = board.getField(index);

        Bitboard allPieces = new Bitboard(board.getCheckers(), null, Board.DIM);
        MoveBitboard moveBitboard =
                new MoveBitboard(Board.DIM).reposition(board.getRow(index), board.getCol(index));
        if (!piece.isQueen()) {
            if (piece.sameColor(Checker.WHITE)) {
                moveBitboard = moveBitboard.forward();
            } else {
                moveBitboard = moveBitboard.backward();
            }
        }

        for (Integer onIndex : moveBitboard.and(allPieces.not()).getOnIndexes()) {
            legalMoves.add(new CheckersMove(index, onIndex));
        }
    }

    private static void generateCapturesForPiece(@NotNull Board board, int index,
                                                 @NotNull HashSet<CheckersMove> legalMoves) {
        Checker piece = board.getField(index);

        Bitboard allPieces = new Bitboard(board.getCheckers(), null, Board.DIM);
        Bitboard oppPieces = new Bitboard(board.getCheckers(), piece.other(), Board.DIM);
        CaptureBitboard captures = new CaptureBitboard(Board.DIM, 5).reposition(board.getRow(index),
                                                                                board.getCol(
                                                                                        index));
        MoveBitboard move =
                new MoveBitboard(Board.DIM).reposition(board.getRow(index), board.getCol(index));

        Bitboard moveCapture = captures.and(captures.and(move).not());
    }

    static void main() {
        Board a = new Board();

        System.out.print(a);

        System.out.println(MoveGenerator.generateMoves(a, Checker.WHITE));
    }
}

// check one around
// check jumps