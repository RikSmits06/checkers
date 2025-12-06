package uk.wwws.checkers.ui;

import java.util.Scanner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import uk.wwws.checkers.ErrorType;
import uk.wwws.checkers.apps.App;

public interface UI {
    void handleAction(@NotNull UIAction action, @Nullable Scanner data);
    void run();

    void setApp(@NotNull App app);
    @Nullable App getApp();
}
