package uk.wwws.game.bitboards;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.game.Board;
import uk.wwws.game.Checker;

public class Bitboard extends BitSet {
    protected final int boardDim;

    public Bitboard(int boardDim) {
        super(boardDim * boardDim);
        this.boardDim = boardDim;
    }

    public int getBoardDim() {
        return boardDim;
    }

    /*@
        ensures this.cardinality() == \old(this.cardinality());
    */
    public Bitboard(@NotNull BitSet bitSet, int boardDim) {
        this(boardDim);
        from(bitSet);
    }

    public void from(@NotNull BitSet board) {
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i)) {
                set(i);
            } else {
                clear(i);
            }
        }
    }

    public Bitboard(@NotNull Checker[] checkers, @Nullable Checker mask, int boardDim) {
        this(boardDim);

        for (int i = 0; i < checkers.length; i++) {
            if (checkers[i] == mask || (mask == null && Checker.EMPTY != checkers[i])) {
                set(i);
            }
        }
    }

    public @NotNull Set<Integer> getMaskIndexes(@NotNull Bitboard board, boolean mask) {
        Set<Integer> indexes = new HashSet<>();

        for (int i = 0; i < board.length(); i++) {
            if (get(i) == mask) {
                indexes.add(i);
            }
        }

        return indexes;
    }


    public @NotNull Set<Integer> getOnIndexes(@NotNull Bitboard board) {
        return getMaskIndexes(board, true);
    }

    public @NotNull Set<Integer> getOffIndexes(@NotNull Bitboard board) {
        return getMaskIndexes(board, false);
    }

    protected @NotNull Bitboard shiftH(int n) {
        Bitboard bitboard = new Bitboard(boardDim);

        for (int i = 0; i < size(); i++) {
            // fucking modular shitfuckery
            // todo simplify
            if ((i+n) % boardDim == i % boardDim + n % boardDim && i + n >= 0 && get(i)) {
                bitboard.set(i + n);
            }
        }

        return bitboard;
    }

    protected @NotNull Bitboard shiftV(int n) {
        Bitboard bitboard = new Bitboard(boardDim);

        for (int i = 0; i < size(); i++) {
            if (i + n < size() && (i + n*boardDim) >= 0 && get(i)) {
                bitboard.set(i + n*boardDim);
            }
        }

        return bitboard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < boardDim; i++) {
            for (int j = 0; j < boardDim; j++) {
                sb.append(get(i * boardDim + j) ? 1 : 0).append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    static void main() {
        Bitboard bitboard = new Bitboard(8);
        bitboard.set(0);

        System.out.println(bitboard.shiftV(9));
    }
}
