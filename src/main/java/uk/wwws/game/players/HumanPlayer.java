package uk.wwws.game.players;

import org.jetbrains.annotations.NotNull;
import uk.wwws.game.AbstractPlayer;
import uk.wwws.game.Game;
import uk.wwws.game.Move;
import uk.wwws.game.Player;

public class HumanPlayer extends AbstractPlayer implements Player {
    public HumanPlayer() {
    }

    @Override
    public Move determineMove(@NotNull Game game) {
        return null;
    }
}
