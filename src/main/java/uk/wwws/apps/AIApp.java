package uk.wwws.apps;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.App;
import uk.wwws.game.players.AIPlayer;
import uk.wwws.net.Connection;
import uk.wwws.net.ConnectionSender;
import uk.wwws.net.threads.ServerConnectionThread;
import uk.wwws.net.threads.ServerThread;
import uk.wwws.tui.Action;

public class AIApp extends App implements ConnectionSender {
    private AIPlayer player;
    private ServerConnectionThread connectionThread;
    private Connection connection;

    private static AIApp instance;

    public static AIApp getInstance() {
        if (instance == null) {
            instance = new AIApp();
        }

        return instance;
    }

    public AIApp() {
        player = new AIPlayer();
    }

    static void main() {
        AIApp.getInstance().run();
    }

    @Override
    protected void handleAction(@Nullable Action action) {
        switch (action) {
            case null, default -> System.out.println(
                    "Invalid command or wrong argument usage. Type help to get command list");
        }
    }
}
