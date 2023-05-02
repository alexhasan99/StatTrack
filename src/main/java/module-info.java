module stattrack.stattrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.neo4j.driver;
    requires json.simple;
    requires org.json;

    requires org.slf4j;
    requires java.net.http;
    requires scb.java.client;

    opens stattrack.stattrack to javafx.fxml;
    exports stattrack.stattrack;
}