package uk.wwws.ui;

import javax.xml.crypto.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface UI extends DataParser {
    void run();
    void handleAction(@Nullable String data);
}
