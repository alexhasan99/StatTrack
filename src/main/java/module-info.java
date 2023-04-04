module stattrack.stattrack {
    requires javafx.controls;
    requires javafx.fxml;


    opens stattrack.stattrack to javafx.fxml;
    exports stattrack.stattrack;
}