package uk.wwws.ui;

import java.util.NoSuchElementException;
import java.util.Scanner;
import org.jetbrains.annotations.Nullable;

abstract public class TUI implements UI {
    Scanner scanner;

    protected TUI() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean stop = false;
        while (!stop) {
            String nextAction = getNextAction();
            if (nextAction != null && nextAction.startsWith(CommandAction.QUIT.name())) {
                stop = true;
                continue;
            }
            handleAction(nextAction);
        }
    }

    protected @Nullable String getNextAction() {
        try {
            return scanner.nextLine();
        } catch (IllegalArgumentException | NoSuchElementException e) {
            return null;
        }
    }
}
