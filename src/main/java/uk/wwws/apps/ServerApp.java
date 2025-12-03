package uk.wwws.apps;

import java.net.Socket;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.App;
import uk.wwws.game.Checker;
import uk.wwws.game.CheckersGame;
import uk.wwws.game.CheckersMove;
import uk.wwws.game.Player;
import uk.wwws.game.players.ConnectedPlayer;
import uk.wwws.net.Connection;
import uk.wwws.net.ConnectionReceiver;
import uk.wwws.net.PacketAction;
import uk.wwws.net.threads.*;
import uk.wwws.tui.CommandAction;

public class ServerApp extends App
        implements ConnectionReceiver, ConnectionDataHandler, NewConnectionHandler {
    HashSet<ConnectedClientThread> connections = new HashSet<>();
    Queue<ConnectedPlayer> queue = new LinkedList<>();

    private @Nullable ServerThread serverThread;

    private static ServerApp instance;

    public static ServerApp getInstance() {
        if (instance == null) {
            instance = new ServerApp();
        }

        return instance;
    }

    static void main() {
        ServerApp.getInstance().run();
    }

    @Override
    protected void handleAction(@Nullable CommandAction action) {
        switch (action) {
            case START_SERVER -> handleStartServer();
            case STOP_SERVER -> stopServer();
            case STATE -> displayState();
            case null, default -> System.out.println(
                    "Invalid command or wrong argument usage. Type help to get command list");
        }
    }

    private void displayState() {
        System.out.println("Size of connections: " + connections.size());
        System.out.println("Queue size: " + queue.size());
    }

    private void handleStartServer() {
        Integer port = getNextIntArg();

        if (port == null) {
            System.out.println("Incorrect usage, should be: start_server <port>");
            return;
        }

        this.serverThread = spawnServer(port);
    }

    @Override
    public ServerThread spawnServer(int port) {
        ServerThread newServerThread = new ServerThread(port, this);
        newServerThread.start();
        System.out.println("Spawned new server");
        return newServerThread;
    }

    @Override
    public void handleNewConnection(@NotNull Socket socket) {
        System.out.println("New client connected");
        ConnectedClientThread client = new ConnectedClientThread(new ConnectedPlayer(new Connection(socket)), this);
        connections.add(client);
        client.start();
        client.getPlayer().getConnection().write("HI");
    }

    private @Nullable ConnectedClientThread getConnectedPlayer(@NotNull Connection c) {
        for (ConnectedClientThread connection : connections) {
            if (connection.getPlayer().getConnection() == c) {
                return connection;
            }
        }

        return null;
    }

    private boolean connectionIsNotAThread(@NotNull Connection c) {
        return getConnectedPlayer(c) == null;
    }

    @Override
    public boolean handleData(@Nullable String data, @NotNull Connection c) {
        if (data == null) {
            handleDisconnect(c);
            return false;
        }

        Scanner input = new Scanner(data);

        System.out.println("New data: " + data);

        try {
            switch (PacketAction.valueOf(input.next().toUpperCase())) {
                case QUEUE -> handleQueue(c);
                case MOVE -> handleMove(data, c);
                case BYE, ERROR -> {
                    handleDisconnect(c);
                    return false;
                }
                default -> {
                    c.write("ERROR");
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            c.write("ERROR");
        }

        return false;
    }

    private void handleQueue(@NotNull Connection c) {
        if (connectionIsNotAThread(c)) {
            return;
        }

        ConnectedPlayer player = getConnectedPlayer(c).getPlayer();
        queue.add(player);
        checkQueue();
    }

    private void handleGameEnd(@NotNull Player player) {
        if (player instanceof ConnectedPlayer cp) {
            cp.setGame(null);
            cp.getConnection().write("GAMEOVER");
        }
    }

    private void handleDisconnect(@NotNull Connection c) {
        System.out.println("Client trying to disconnect");
        if (connectionIsNotAThread(c)) {
            return;
        }

        ConnectedClientThread clientThread = getConnectedPlayer(c);
        ConnectedPlayer player = clientThread.getPlayer();
        if (player.getGame() != null) {
            for (Player otherPlayer : player.getGame().getPlayers()) {
                handleGameEnd(otherPlayer);
            }
        }

        clientThread.interrupt();
        connections.remove(clientThread);
        queue.remove(player);
        System.out.println("Client disconnected");
    }

    private void handleMove(@NotNull String data, @NotNull Connection c) {
        System.out.println("Recieved move data: " + data);
        Scanner input = new Scanner(data);

        int fromIndex;
        int toIndex;

        try {
            fromIndex = input.nextInt();
            toIndex = input.nextInt();
        } catch (NoSuchElementException | IllegalStateException e) {
            System.out.println("Invalid move packet: " + data);
            c.write("ERROR");
            return;
        }

        ConnectedPlayer player = getConnectedPlayer(c).getPlayer();
        if (player.getGame() == null) {
            c.write("ERROR");
            System.out.println("Player of no game tried to make a move");
            return;
        }

        if (game.getTurn() != player) {
            c.write("ERROR");
            System.out.println("Player tried to move when its not its turn");
        }

        player.getGame().doMove(new CheckersMove(fromIndex, toIndex));

        if (game.isGameOver()) {
            for (Player otherPlayer : game.getPlayers()) {
                handleGameEnd(otherPlayer);
            }
        }
    }

    private void checkQueue() {
        if (queue.size() > 1) {
            CheckersGame game = new CheckersGame();
            Player player1 = queue.poll();
            Player player2 = queue.poll();
            game.addPlayer(player1, Checker.WHITE);
            game.addPlayer(player2, Checker.BLACK);

            checkQueue();
        }
    }

    @Override
    public void stopServer() {
        if (serverThread == null) {
            return;
        }

        serverThread.interrupt();
        serverThread = null;

        System.out.println("Stopped server");
    }
}
