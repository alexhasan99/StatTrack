package stattrack.stattrack;

import org.neo4j.driver.Record;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;
import stattrack.stattrack.APIRequest.TablesRequest;

import java.net.MalformedURLException;
import java.util.Map;

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

                // Merge or create County node
                session.run("MERGE (c:County {code: $code}) ON CREATE SET c.name = $name ON MATCH SET c.name = $name",
                        parameters("code", countyCode, "name", countyName));

                for (Map.Entry<String, String> municipalityEntry : municipalityCodeLookup.getMunicipalityMap().entrySet()) {
                    String municipalityCode = municipalityEntry.getKey();
                    String municipalityName = municipalityEntry.getValue();

                    // Filter municipalities based on the county code
                    if (municipalityCode.startsWith(countyCode)) {
                        // Merge or create Municipality node
                        session.run("MERGE (m:Municipality {code: $code}) ON CREATE SET m.name = $name ON MATCH SET m.name = $name",
                                parameters("code", municipalityCode, "name", municipalityName));

                        // Create relationship between County and Municipality
                        session.run("MATCH (c:County {code: $countyCode}) " +
                                        "MATCH (m:Municipality {code: $municipalityCode}) " +
                                        "MERGE (c)-[:HAS]->(m)",
                                parameters("countyCode", countyCode, "municipalityCode", municipalityCode));
                    }
                }
            }

        }catch (Exception e){
            throw new Exception(e);
        }
    }
    public void pushFirstApi() throws Exception {
        try {
            for (KeyValuePair keyValuePair : TablesRequest.firstApi()) {
                String[] keys = keyValuePair.getKey();
                int count = Integer.parseInt(keyValuePair.getValue());
                String municipalityCode = keys[0];
                String gender = keys[1];
                String educationType = keys[2];
                String placeOfSchool = keys[3];
                String year = keys[4];
                session.run("MERGE (g:Gender {name: $gender})",
                        parameters("gender", gender));

                // Create or update the EducationType node
                session.run("MERGE (e:Education_Type {name: $educationType})",
                        parameters("educationType", educationType));

                // Create or update the PlaceOfSchool node
                session.run("MERGE (p:PlaceOfSchool {name: $placeOfSchool})",
                        parameters("placeOfSchool", placeOfSchool));

                // Create or update the Year node
                session.run("MERGE (y:Year {value: $year})",
                        parameters("year", year));

                Result countResult = session.run("MATCH (p:Person)-[:HAS_EDUCATION_TYPE]->(n:Education_Type {name: $type})," +
                                "(p)-[:LIVES_IN]->(m:Municipality {code:$code})," +
                                "(p)-[:HAS_GENDER]->(g:Gender {name:$gender})," +
                                "(p)-[:ATTENDED_SCHOOL_AT]->(a:PlaceOfSchool {name:$place})," +
                                "(p)-[:PARTICIPATED_IN]->(y:Year{value:$year}) RETURN COUNT(DISTINCT p) AS personCount",
                        parameters("code", municipalityCode, "gender", gender,
                                "type", educationType, "place", placeOfSchool, "year", year));
                if (countResult.hasNext() && count!=0) {
                    Record record = countResult.next();
                    long personCount = record.get("personCount").asLong();
                    System.out.println(personCount);
                    if (personCount < count) {
                        count= count-(int) personCount;
                        for (int i = 0; i < count; i++) {
                            session.run(
                                    "MATCH (m:Municipality {code: $code}) " +
                                            "WITH m " +
                                            "CREATE (p:Person)-[:LIVES_IN]->(m) " +
                                            "WITH p " +
                                            "MATCH (g:Gender {name: $gender}) " +
                                            "MERGE (p)-[:HAS_GENDER]->(g) " +
                                            "WITH p " +
                                            "MATCH (e:Education_Type {name: $educationType}) " +
                                            "MERGE (p)-[:HAS_EDUCATION_TYPE]->(e) " +
                                            "WITH p " +
                                            "MATCH (pos:PlaceOfSchool {name: $placeOfSchool}) " +
                                            "MERGE (p)-[:ATTENDED_SCHOOL_AT]->(pos) " +
                                            "WITH p " +
                                            "MATCH (y:Year {value: $year}) " +
                                            "MERGE (p)-[:PARTICIPATED_IN]->(y)",
                                    parameters("code", municipalityCode,
                                            "gender", gender,
                                            "educationType", educationType,
                                            "placeOfSchool", placeOfSchool,
                                            "year", year));

                        }
                    }
                }
            }
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    public void pushSecondApi() throws MalformedURLException {
        for (KeyValuePair keyValuePair : TablesRequest.secondApi()) {
            String[] keys = keyValuePair.getKey();
            int count = Integer.parseInt(keyValuePair.getValue());
            String municipalityCode = keys[0];
            String gender = keys[1];
            String age = keys[2];
            String educationLevel = keys[3];
            String year = keys[4];

            session.run("MERGE (g:Gender {name: $gender})",
                    parameters("gender", gender));

            // Create or update the EducationType node
            session.run("MERGE (e:Education_Level {name: $educationType})",
                    parameters("educationType", educationLevel));

            // Create or update the Year node
            session.run("MERGE (y:Year {value: $year})",
                    parameters("year", year));

            boolean isAgeRange = age.matches("^\\d{2}-\\d{2}$");

            String ageNodeLabel = isAgeRange ? "Age_Range" : "Age";
            String ageRelationship = isAgeRange ? "HAS_AGE_RANGE" : "HAS_AGE";

            session.run("MERGE (a:" + ageNodeLabel + " {name: $age})", parameters("age", age));

            Result countResult = session.run(
                    "MATCH (p:Person)-[:HAS_EDUCATION_LEVEL]->(n:Education_Level {name: $level})" +
                            "MATCH (p)-[:LIVES_IN]->(m:Municipality {code:$code})" +
                            "MATCH (p)-[:HAS_GENDER]->(g:Gender {name:$gender})" +
                            "MATCH (p)-[:" + ageRelationship + "]->(a:" + ageNodeLabel + " {name: $age})" +
                            "MATCH (p)-[:PARTICIPATED_IN]->(y:Year {value:$year})" +
                            "RETURN COUNT(DISTINCT p) AS personCount",
                    parameters("code", municipalityCode, "gender", gender, "level", educationLevel, "age", age, "year", year)
            );

            if (countResult.hasNext() && count != 0) {
                Record record = countResult.next();
                long personCount = record.get("personCount").asLong();
                System.out.println(personCount);

                if (personCount < count) {
                    count= count-(int) personCount;
                    for (int i = 0; i < count; i++) {
                        session.run(
                                "MATCH (m:Municipality {code: $code}) " +
                                        "WITH m " +
                                        "CREATE (p:Person)-[:LIVES_IN]->(m) " +
                                        "WITH p " +
                                        "MATCH (g:Gender {name: $gender}) " +
                                        "MERGE (p)-[:HAS_GENDER]->(g) " +
                                        "WITH p " +
                                        "MATCH (e:Education_Level {name: $educationLevel}) " +
                                        "MERGE (p)-[:HAS_EDUCATION_LEVEL]->(e) " +
                                        "WITH p " +
                                        "MATCH (a:" + ageNodeLabel + " {name: $age}) " +
                                        "MERGE (p)-[:" + ageRelationship + "]->(a) " +
                                        "WITH p " +
                                        "MATCH (y:Year {value: $year}) " +
                                        "MERGE (p)-[:PARTICIPATED_IN]->(y)",
                                parameters("code", municipalityCode, "gender", gender, "educationLevel", educationLevel,
                                        "age", age, "year", year)
                        );
                    }
                }
            }
        }
    }

    public void pushThirdApi() throws Exception {
        for (KeyValuePair keyValuePair : TablesRequest.thirdApi()) {
            String[] keys = keyValuePair.getKey();
            int count = Integer.parseInt(keyValuePair.getValue());
            String municipalityCode = keys[0];
            String dwellingType = keys[1];
            String year = keys[2];
            try {
                session.run("MERGE (d:Dwelling_Type {name: $type})",
                        parameters("type", dwellingType));

                session.run("MERGE (y:Year {value: $year})",
                        parameters("year", year));

                Result countResult = session.run("MATCH (d:Dwelling)-[:HAS_DWELLING_TYPE]->(n:Dwelling_Type {name: $type})," +
                                "(d)-[:LOCATED_IN]->(m:Municipality {code:$code})," +
                                "(d)-[:PARTICIPATED_IN]->(y:Year{value:$year}) RETURN COUNT(DISTINCT d) AS dwellingCount",
                        parameters("code", municipalityCode, "type", dwellingType, "year", year));
                if (countResult.hasNext() && count!=0) {
                    Record record = countResult.next();
                    long dwellingCount = record.get("dwellingCount").asLong();
                    System.out.println(dwellingCount);
                    if (dwellingCount < count) {
                        count= count-(int) dwellingCount;
                        for (int i = 0; i < count; i++) {
                            session.run(
                                    "MATCH (m:Municipality {code: $code}) " +
                                            "WITH m " +
                                            "CREATE (d:Dwelling)-[:LOCATED_IN]->(m) " +
                                            "WITH d " +
                                            "MATCH (n:Dwelling_Type {name: $type}) " +
                                            "MERGE (d)-[:HAS_DWELLING_TYPE]->(n) " +
                                            "WITH d " +
                                            "MATCH (y:Year {value: $year}) " +
                                            "MERGE (d)-[:PARTICIPATED_IN]->(y)",
                                    parameters("code", municipalityCode,
                                            "type", dwellingType,
                                            "year", year));
                        }
                    }
                }
            }catch(Exception e) {
                throw new Exception(e);
            }
        }
    }

    public void pushFourthApi() throws Exception {
        for (KeyValuePair keyValuePair : TablesRequest.forthApi()) {
            String[] keys = keyValuePair.getKey();
            int value = Integer.parseInt(keyValuePair.getValue());
            String municipalityCode = keys[0];
            String landUse = keys[1];
            String year = keys[2];
            try {
                session.run("MERGE (y:Year {value: $year})",
                        parameters("year", year));

                session.run("MERGE (m:Municipality {code: $municipalityCode}) " +
                        "MERGE (y:Year {value: $year}) " +
                        "MERGE (m)-[:OCCURRED_IN]->(y) " +
                        "WITH m, y " +
                        "MERGE (m)-[:HAS_LAND_USE]->(l:LandUse {name: $landUse}) " +
                        "SET l.value = $value " +
                        "MERGE (l)-[:OCCURRED_IN]->(y)", parameters("municipalityCode", municipalityCode,
                        "landUse", landUse,
                        "year", year,
                        "value", value));

            }catch(Exception e) {
                throw new Exception(e);
            }
        }
    }

    public void pushFifthApi() throws Exception{
        for (KeyValuePair keyValuePair: TablesRequest.fifthApi()) {
            if (keyValuePair.getValue().equals("..")) {
                continue;
            }
            String[] keys = keyValuePair.getKey();
            double count = Double.parseDouble(keyValuePair.getValue());
            String municipalityCode = keys[0];
            String  housingType= keys[1];
            String dwellingSize = keys[2];
            String year = keys[3];
            try {
                session.run("MERGE (y:Year {value: $year})",
                        parameters("year", year));

                session.run("MERGE (d:Dwelling_Housing_Type {name: $type})",
                        parameters("type", housingType));

                session.run("MERGE (d:Dwelling_Housing_Size {name: $size})",
                        parameters("size", dwellingSize));

                Result countResult = session.run("MATCH (d:Dwelling)-[:HAS_HOUSING_TYPE]->(n:Dwelling_Housing_Type {name: $type})," +
                                "(d)-[:HAS_DWELLING_SIZE]->(s:Dwelling_Housing_Size {name: $size}),"+
                                "(d)-[:LOCATED_IN]->(m:Municipality {code:$code})," +
                                "(d)-[:PARTICIPATED_IN]->(y:Year{value:$year}) RETURN COUNT(DISTINCT d) AS dwellingCount",
                        parameters("code", municipalityCode, "type", housingType,"size", dwellingSize, "year", year));
                if (countResult.hasNext() && count!=0) {
                    Record record = countResult.next();
                    long dwellingCount = record.get("dwellingCount").asLong();
                    System.out.println(dwellingCount);
                    if (dwellingCount < count) {
                        count= count-(int) dwellingCount;
                        for (int i = 0; i < count; i++) {
                            session.run(
                                    "MATCH (m:Municipality {code: $code}) " +
                                            "WITH m " +
                                            "CREATE (d:Dwelling)-[:LOCATED_IN]->(m) " +
                                            "WITH d " +
                                            "MATCH (n:Dwelling_Housing_Type {name: $type}) " +
                                            "MERGE (d)-[:HAS_HOUSING_TYPE]->(n) " +
                                            "WITH d " +
                                            "MATCH (s:Dwelling_Housing_Size {name: $size}) " +
                                            "MERGE (d)-[:HAS_DWELLING_SIZE]->(s) " +
                                            "WITH d " +
                                            "MATCH (y:Year {value: $year}) " +
                                            "MERGE (d)-[:PARTICIPATED_IN]->(y)",
                                    parameters("code", municipalityCode,
                                            "type", housingType,
                                            "size", dwellingSize,
                                            "year", year));

                        }
                    }
                }

            }catch (Exception e){
                throw new Exception(e);
            }
        }
    }
    public void disconnect() throws Exception {
        try {
            // Close the session and driver
            session.close();
            driver.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public static void main(String[] args) throws Exception {
        PushToDB p = new PushToDB();
        p.connect();
        p.pushCounties();
        p.pushFifthApi();
        /*p.pushFirstApi();
        p.pushSecondApi();
        p.pushThirdApi();
        //TablesRequest.FirstApi();
// Create threads for each method
        /*Thread pushCountiesThread = new Thread(() -> {
            try {
                p.pushCounties();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread pushFirstApiThread = new Thread(() -> {
            try {
                p.pushFirstApi();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

// Start the threads
        pushCountiesThread.start();
        pushFirstApiThread.start();

// Wait for the threads to finish
        try {
            pushCountiesThread.join();
            pushFirstApiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

// Disconnect from the database
        p.disconnect();

    }
}

