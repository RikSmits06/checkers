package uk.wwws.ui;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.net.PacketAction;

public interface DataParser {
    default @Nullable String getNext(@NotNull String data) {
        Scanner s = new Scanner(data);
        try {
            return s.next();
        } catch (Exception e) {
            return null;
        }
    }

    default @Nullable Integer getNextInt(@NotNull String data) {
        Scanner s = new Scanner(data);
        try {
            return s.nextInt();
        } catch (Exception e) {
            return null;
        }
    }

    default @Nullable CommandAction getNextCommandAction(@NotNull String data) {
        try {
            return CommandAction.valueOf(getNext(data).toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    default @Nullable PacketAction getNextPacketAction(@NotNull String data) {
        try {
            return PacketAction.valueOf(getNext(data).toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}
