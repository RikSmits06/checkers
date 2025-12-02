package uk.wwws.tui;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TUI {
    Scanner scanner;

    protected TUI() {
        scanner = new Scanner(System.in);
    }

    protected @Nullable Action getNextAction() {
        try {
            return Action.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return null;
        }
    }

    protected @Nullable String getNextStringArg() {
        try {
            return scanner.next();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return null;
        }
    }

    protected @Nullable Integer getNextIntArg() {
        try {
            return scanner.nextInt();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return null;
        }
    }
}
