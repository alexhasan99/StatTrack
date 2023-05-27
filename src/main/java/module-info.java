module stattrack.stattrack {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.neo4j.driver;
    requires json.simple;
    requires org.json;
    requires java.net.http;
    opens stattrack.stattrack to javafx.fxml;
    exports stattrack.stattrack;
    exports stattrack.stattrack.APIRequest;
    opens stattrack.stattrack.APIRequest to javafx.fxml;
    exports stattrack.stattrack.PushAPIs;
    opens stattrack.stattrack.PushAPIs to javafx.fxml;
}