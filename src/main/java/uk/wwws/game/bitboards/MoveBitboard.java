package uk.wwws.game.bitboards;

import org.jetbrains.annotations.NotNull;
import uk.wwws.game.Board;

public class MoveBitboard extends Bitboard {
    private int pieceRow;
    private int pieceCol;

    public MoveBitboard(int boardDim, int pieceRow, int pieceCol) {
        this(boardDim);

        assert pieceRow < boardDim;
        assert pieceCol < boardDim;
        assert pieceRow >= 0;
        assert pieceCol >= 0;

        from(shiftV(pieceRow - 1));
        from(shiftH(pieceCol - 1));

        this.pieceCol = pieceCol;
        this.pieceRow = pieceRow;
    }

    public @NotNull Bitboard forward() {
        Bitboard bitboard = new Bitboard(this, boardDim);
        bitboard.set((pieceRow + 1) * boardDim, boardDim*boardDim, false);
        return bitboard;
    }

    public @NotNull Bitboard backward() {
        Bitboard bitboard = new Bitboard(this, boardDim);
        bitboard.set(0, pieceRow * boardDim + pieceCol, false);
        return bitboard;
    }

    public MoveBitboard(int boardDim) {
        super(boardDim);


        //    1 0 1 0 0 0 0 0
        //    0 0 0 0 0 0 0 0
        //    1 0 1 0 0 0 0 0
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i % 2 != 0 || j % 2 != 0) {
                    continue;
                }

                set(i * Board.DIM + j);
            }
        }
    }

    static void main() {
        MoveBitboard c = new MoveBitboard(8, 1, 1);

        System.out.println(c.backward());
        System.out.println(c.forward());
    }
}
