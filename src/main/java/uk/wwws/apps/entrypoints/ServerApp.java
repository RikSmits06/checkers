package uk.wwws.apps.entrypoints;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.apps.ServerLikeApp;
import uk.wwws.ui.CommandAction;

public class ServerApp extends ServerLikeApp {

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
}
