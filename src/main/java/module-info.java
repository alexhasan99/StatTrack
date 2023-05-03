module stattrack.stattrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires scb.java.client;
    requires java.desktop;

    opens stattrack.stattrack to javafx.fxml;
    exports stattrack.stattrack;
}
