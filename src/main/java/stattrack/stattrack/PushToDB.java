package stattrack.stattrack;

import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;

import java.net.MalformedURLException;
import java.util.*;

import static org.neo4j.driver.Values.parameters;


public class PushToDB {

    // Connect to Neo4j
    String uri = "bolt://localhost:7687/";
    String user = "neo4j";
    String password = "hamo1212";
    Driver driver;
    Session session;
    CountyCodeLookup countyCodeLookup;
    MunicipalityCodeLookup municipalityCodeLookup;



    public boolean connect() throws Exception {
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

    public void pushCounties() throws Exception {
        municipalityCodeLookup= new MunicipalityCodeLookup();
        countyCodeLookup = new CountyCodeLookup();

        try {
            for (Map.Entry<String, String> countyEntry : countyCodeLookup.getCountyMap().entrySet()) {
                String countyCode = countyEntry.getKey();
                String countyName = countyEntry.getValue();

                // Check if county node already exists
                Result result = session.run("MATCH (c:County {code: $code}) RETURN c", parameters("code", countyCode));
                if (result.hasNext()) {
                    // County node exists, update its name
                    session.run("MATCH (c:County {code: $code}) SET c.name = $name",
                            parameters("code", countyCode, "name", countyName));
                } else {
                    // County node does not exist, create it
                    session.run("CREATE (c:County {code: $code, name: $name})",
                            parameters("code", countyCode, "name", countyName));
                }

                for (Map.Entry<String, String> municipalityEntry : municipalityCodeLookup.getMunicipalityMap().entrySet()) {
                    String municipalityCode = municipalityEntry.getKey();
                    String municipalityName = municipalityEntry.getValue();

                    // Filter municipalities based on the county code
                    if (municipalityCode.startsWith(countyCode)) {
                        // Check if municipality node already exists
                        Result municipalityResult = session.run("MATCH (m:Municipality {code: $code}) RETURN m", parameters("code", municipalityCode));
                        if (!municipalityResult.hasNext()) {
                            // Municipality node does not exist, create it
                            session.run("CREATE (m:Municipality {code: $code, name: $name})", parameters("code", municipalityCode, "name", municipalityName));
                        }

                        // Create relationship between county and municipality
                        // Check if the relationship already exists
                        Result relationshipResult = session.run("MATCH (c:County {code: $countyCode})-[:HAS]->(m:Municipality {code: $municipalityCode}) RETURN c, m",
                                parameters("countyCode", countyCode, "municipalityCode", municipalityCode));
                        if (!relationshipResult.hasNext()) {
                            // Create relationship between county and municipality
                            session.run("MATCH (m:Municipality {code: $municipalityCode}) " +
                                            "WITH m " +
                                            "MATCH (c:County {code: $countyCode}) " +
                                            "CREATE (c)-[:HAS]->(m)",
                                    parameters("municipalityCode", municipalityCode, "countyCode", countyCode));
                        }
                    }
                }
            }

        }catch (Exception e){
            throw new Exception(e);
        }finally {
            // Close the session and driver
            session.close();
            driver.close();
        }
    }
    public void pushFirstApi() throws MalformedURLException {
        /*for (KeyValuePair keyValuePair : TablesRequest.FirstApi()) {
            String[] keys = keyValuePair.getKey();

            for (String keyElement : keys) {
                int keyElementValue = Integer.parseInt(keyElement);
                // Perform operations with the key element
                System.out.println("Key Element: " + keyElementValue);
            }
        }*/
        TablesRequest.FirstApi();
    }

    public static void main(String[] args) throws Exception {
        PushToDB p= new PushToDB();
        //p.connect();
        p.pushFirstApi();
    }
}
