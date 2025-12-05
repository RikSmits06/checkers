package uk.wwws.checkers.ui;

import java.util.Scanner;

abstract public class SimpleTUI implements UI {
    private final Scanner scanner;

    protected SimpleTUI() {
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean stop = false;
        while (!stop) {
            CommandAction nextAction = getNextCommandAction(scanner);
            if (nextAction == CommandAction.QUIT) {
                stop = true;
                continue;
            }
            handleAction(nextAction, scanner);
        }
    }
}
