package stattrack.stattrack;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Driver;

public class Neo4jConnection {

    private static final String NEO4J_URI = "bolt://localhost:7687";
    private static final String NEO4J_USERNAME = "neo4j";
    private static final String NEO4J_PASSWORD = "g1234567";

    private Driver driver;

    public Neo4jConnection() {
        this.driver = GraphDatabase.driver(NEO4J_URI, AuthTokens.basic(NEO4J_USERNAME, NEO4J_PASSWORD));
    }

    public Driver getDriver() {
        return this.driver;
    }

    public void close() {
        this.driver.close();
    }
}