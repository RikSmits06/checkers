package uk.wwws.apps;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.App;
import uk.wwws.game.CheckersGame;
import uk.wwws.game.players.HumanPlayer;
import uk.wwws.net.Connection;
import uk.wwws.net.ConnectionSender;
import uk.wwws.net.exceptions.FailedToConnectException;
import uk.wwws.net.threads.ConnectionDataHandler;
import uk.wwws.net.threads.ServerConnectionThread;
import uk.wwws.net.threads.ServerThread;
import uk.wwws.tui.CommandAction;

public class ClientApp extends App implements ConnectionSender, ConnectionDataHandler {
    private HumanPlayer player;
    private ServerConnectionThread connectionThread;
    private CheckersGame game;

    private static ClientApp instance;

    public static ClientApp getInstance() {
        if (instance == null) {
            instance = new ClientApp();
        }

        return instance;
    }

    public ClientApp() {
        player = new HumanPlayer();
        game = new CheckersGame();
    }

    static void main() {
        ClientApp.getInstance().run();
    }

    @Override
    protected void handleAction(@Nullable CommandAction action) {
        switch (action) {
            case CONNECT -> handleConnect();
            case MOVE -> handleMove();
            case null, default -> System.out.println(
                    "Invalid command or wrong argument usage. Type help to get command list");
        }
    }

    private void handleMove() {
        if (connectionThread == null) {
            System.out.println("You need to be connected to send moves");
            return;
        }

        if (game == null) {
            System.out.println("You need to be in game to send moves");
        }

        String move = getNextStringArg();
        System.out.println("Sending new move: " + move);
        connectionThread.getConnection().write(move);
    }

    private void handleConnect() {

        String host = getNextStringArg();
        Integer port = getNextIntArg();

        System.out.println("Connecting to: " + host + ":" + port);

        if (host == null || port == null) {
            System.out.println("Invalid usage. Use: connect <host> <port>");
            return;
        }

        Connection connection;

        try {
            connection = new Connection(host, port);
        } catch (FailedToConnectException e) {
            System.out.println(e.getMessage());
            return;
        }

        connectionThread = new ServerConnectionThread(connection, this);
        connectionThread.start();

        System.out.println("Created new connection");
    }

    @Override
    public boolean handleData(@Nullable String data, @NotNull Connection c) {
        if (data == null) {
            connectionThread.interrupt();
            connectionThread = null;
        }

        System.out.println("Received new data: " + data);
        return true;
    }
}
