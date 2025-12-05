package uk.wwws.game.bitboards;

import org.jetbrains.annotations.NotNull;

abstract public class PositionedBitboard extends Bitboard implements Directional {
    private final int offset;
    protected int row;
    protected int col;

    public PositionedBitboard(int boardDim, int offset) {
        super(boardDim);

        this.offset = offset;
    }

    @SuppressWarnings("unchecked")
    public @NotNull <T extends PositionedBitboard> T reposition(int row, int col) {
        assert row < boardDim;
        assert col < boardDim;
        assert row >= 0;
        assert col >= 0;

        this.col = col;
        this.row = row;

        from(shiftV(row - offset));
        from(shiftH(col - offset));

        return (T) this; // yes, unchecked cast :)
    }

    public @NotNull MoveBitboard forward() {
        MoveBitboard bitboard = (MoveBitboard) this.clone();
        bitboard.set((row + 1) * boardDim, boardDim * boardDim, false);
        return bitboard;
    }

    public @NotNull MoveBitboard backward() {
        MoveBitboard bitboard = (MoveBitboard) this.clone();
        bitboard.set(0, row * boardDim + col, false);
        return bitboard;
    }
}
