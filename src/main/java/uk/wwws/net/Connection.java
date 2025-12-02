package uk.wwws.net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.jetbrains.annotations.NotNull;
import uk.wwws.net.exceptions.FailedToConnectException;
import uk.wwws.net.exceptions.FailedToCreateStreamsException;

public class Connection {
    private Socket socket;
    private @NotNull String host;
    private int port;
    private DataOutputStream out;
    private DataInputStream in;

    // for client to connect to said socket
    public Connection(@NotNull Socket socket) throws FailedToCreateStreamsException {
        this.host = socket.getInetAddress().getHostName();
        this.port = socket.getPort();

        try {
            assignDataStreams();
        } catch (Exception e) {
            throw new FailedToCreateStreamsException("Failed to create data streams");
        }
    }

    // for client to connect to said socket
    public Connection(@NotNull String host, int port) throws FailedToConnectException {
        this.host = host;
        this.port = port;

        try {
            connect();
        } catch (Exception e) {
            throw new FailedToConnectException(host, port);
        }
    }

    private void connect() throws IOException {
        socket = new Socket(this.host, this.port);
        assignDataStreams();
    }

    private void assignDataStreams() throws IOException {
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public void write(@NotNull String data) throws IOException {
        out.writeUTF(data);
    }

    public @NotNull String read() throws IOException {
        return in.readUTF();
    }

    public void disconnect() throws IOException {
        socket.close();
    }

    public Socket getSocket() {
        return socket;
    }
}
