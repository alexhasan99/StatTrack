module stattrack.stattrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.json;


    requires java.net.http;
    requires org.neo4j.driver;
    requires org.neo4j.graphdb;
    exports stattrack.stattrack;

    opens stattrack.stattrack to javafx.fxml;
}