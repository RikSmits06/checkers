package uk.wwws;

import org.jetbrains.annotations.Nullable;
import uk.wwws.game.CheckersGame;
import uk.wwws.tui.Action;
import uk.wwws.tui.TUI;

abstract public class App extends TUI {
    protected CheckersGame game;

    public App() {
        game = new CheckersGame();
    }

    protected final void run() {
        boolean stop = false;
        while (!stop) {
            Action action = getNextAction();
            if (action == Action.QUIT) stop = true;
            handleAction(action);
        }
    }

    protected abstract void handleAction(@Nullable Action action);
}
