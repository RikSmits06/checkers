package uk.wwws.apps;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.App;
import uk.wwws.game.players.HumanPlayer;
import uk.wwws.net.Connection;
import uk.wwws.net.ConnectionSender;
import uk.wwws.net.threads.ServerConnectionThread;
import uk.wwws.net.threads.ServerThread;
import uk.wwws.tui.Action;

public class ClientApp extends App implements ConnectionSender {
    private HumanPlayer player;
    private ServerConnectionThread connectionThread;
    private Connection connection;

    private static ClientApp instance;

    public static ClientApp getInstance() {
        if (instance == null) {
            instance = new ClientApp();
        }

        return instance;
    }

    public ClientApp() {
        player = new HumanPlayer();
    }

    static void main() {
        ClientApp.getInstance().run();
    }

    @Override
    protected void handleAction(@Nullable Action action) {

    }
}
