package stattrack.stattrack;

import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;
import org.neo4j.driver.types.Node;
import org.neo4j.graphdb.RelationshipType;

enum RelTypes implements RelationshipType
{
    KNOWS
}
public class PushToDB {

    // Connect to Neo4j
    String uri = "bolt://localhost:7687/";
    String user = "neo4j";
    String password = "hamo1212";
    Driver driver;
    Session session;



    public boolean connect(String databaseName) throws Exception {
        try {
            driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
            session = driver.session();
        } catch (ClientException e) {
            throw new Exception(e.getMessage(), e);
        }
        try {
            Result result = session.run("RETURN 1");
            if (result.hasNext()) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Not connected to the database.");
            }

        }catch (Exception e){
            throw new Exception(e.getMessage(), e);
        }
        return true;
    }
    public void push() throws Exception {

        try {
            // Create a node
            //session.run("CREATE (n:Person {name: 'Alice', age: 30})");
            /*Node first = session.run("CREATE (n:Person {name: 'Alice', age: 30}) RETURN n")
                    .single()
                    .get("n")
                    .asNode();
            Node second = session.run("CREATE (d:Person {name: 'Sargon', age: 20}) RETURN d")
                    .single()
                    .get("d")
                    .asNode();*/

            Node london = session.run("CREATE (n:City {name: 'London', population: 8982000}) RETURN n")
                    .single()
                    .get("n")
                    .asNode();
            Node newyork = session.run("CREATE (n:City {name: 'New York', population: 8399000}) RETURN n")
                    .single()
                    .get("n")
                    .asNode();
            session.run("MATCH (a:Person {name: 'Alice'}), (b:City {name: 'London'}) CREATE (a)-[:LIVES_IN]->(b)");
            session.run("MATCH (a:Person {name: 'Sargon'}), (b:City {name: 'New York'}) CREATE (a)-[:LIVES_IN]->(b)");
            //session.run("MATCH (a:Person {name: 'Alice'}), (b:Person {name: 'Sargon'}) CREATE (a)-[:KNOWS]->(b)");


    // Create a relationship between the nodes

    // Save the changes to the database
    /*session.writeTransaction(tx -> {
        tx.createNode(node1);
        tx.createNode(node2);
        tx.createRelationship(relationship);
        return null;
    });*/
        }catch (Exception e){
            throw new Exception(e);
        }finally {
            // Close the session and driver
            session.close();
            driver.close();
        }
    }
    public static void main(String[] args) throws Exception {
        PushToDB p= new PushToDB();
        p.connect("ff");
        p.push();

    }
}
