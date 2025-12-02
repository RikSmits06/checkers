package uk.wwws.game;

import org.jetbrains.annotations.NotNull;

public class Board {
    public static final int DIM = 8;
    private Checker[] fields = new Checker[DIM*DIM];

    public Board() {
        for (int i = 0; i < (DIM * DIM); i++) {
            fields[i] = Checker.EMPTY;
        }
    }

    public int index(int row, int col) {
        return row * DIM + col;
    }

    public boolean isField(int index) {
        return (0 <= index) && (index < DIM * DIM);
    }

    public boolean isField(int row, int col) {
        return isField(index(row, col));
    }

    public Checker getField(int i) {
        return this.fields[i];
    }

    public Checker getField(int row, int col) {
        return getField(index(row, col));
    }

    public boolean isEmptyField(int i) {
        return getField(i) == Checker.EMPTY;
    }

    public boolean isEmptyField(int row, int col) {
        return isEmptyField(index(row, col));
    }

    public boolean isOnlyChecker(@NotNull Checker c) {
        for (int i = 0; i < DIM * DIM; i++) {
            if (getField(i) != c) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmpty() {
        return isOnlyChecker(Checker.EMPTY);
    }

    public boolean gameOver() {
        return hasWinner() || isEmpty();
    }

    public boolean isWinner(@NotNull Checker c) {
        return isOnlyChecker(c);
    }

    public @NotNull String toString() {
        return "";
    }

    public boolean hasWinner() {
        return isWinner(Checker.WHITE) || isWinner(Checker.BLACK);
    }

    public void reset() {
        this.fields = new Board().fields;
    }

    public void setField(int i, @NotNull Checker c) {
        this.fields[i] = c;
    }

    public void setField(int row, int col, @NotNull Checker c) {
        this.fields[index(row, col)] = c;
    }
}
