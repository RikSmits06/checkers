package uk.wwws.ui;

import java.util.Scanner;
import javax.xml.crypto.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UI extends DataParser {
    void run();
    void handleAction(@Nullable CommandAction action, @NotNull Scanner data);
}
