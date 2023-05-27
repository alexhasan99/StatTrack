package stattrack.stattrack;

import org.neo4j.driver.Record;
import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.ClientException;
import stattrack.stattrack.APIRequest.TablesRequest;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
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


    private boolean connect() throws Exception {
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

        } catch (Exception e) {
            throw new Exception(e.getMessage(), e);
        }
        return true;
    }

    private void pushCounties() throws Exception {
        municipalityCodeLookup = new MunicipalityCodeLookup();
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

        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void pushFirstApi() throws Exception {
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
                if (countResult.hasNext() && count != 0) {
                    Record record = countResult.next();
                    long personCount = record.get("personCount").asLong();
                    System.out.println(personCount);
                    if (personCount < count) {
                        count = count - (int) personCount;
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
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void pushSecondApi() throws MalformedURLException {
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
                    count = count - (int) personCount;
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

    private void pushThirdApi() throws Exception {
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
                if (countResult.hasNext() && count != 0) {
                    Record record = countResult.next();
                    long dwellingCount = record.get("dwellingCount").asLong();
                    System.out.println(dwellingCount);
                    if (dwellingCount < count) {
                        count = count - (int) dwellingCount;
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
            } catch (Exception e) {
                throw new Exception(e);
            }
        }
    }

    private void pushFourthApi() throws Exception {
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

            } catch (Exception e) {
                throw new Exception(e);
            }
        }
    }


    private void pushFifthApi() throws Exception {
        List<KeyValuePair> numPersonPair = new ArrayList<KeyValuePair>(TablesRequest.fifth2Api());
        for (KeyValuePair keyValuePair : TablesRequest.fifth1Api()) {
            if (keyValuePair.getValue().equals("..")) {
                continue;
            }

            String[] keys = keyValuePair.getKey();
            double count = Double.parseDouble(keyValuePair.getValue());
            String municipalityCode = keys[0];
            String housingType = keys[1];
            String dwellingSize = keys[2];
            String year = keys[3];
            String personCount = "";
            for (KeyValuePair keyValuePair2 : numPersonPair) {
                String[] keys2 = keyValuePair2.getKey();
                String municipalityCode2 = keys2[0];
                String housingType2 = keys2[1];
                String dwellingSize2 = keys2[2];
                String year2 = keys2[3];
                if (municipalityCode2.equals(municipalityCode) && housingType2.equals(housingType) & dwellingSize2.equals(dwellingSize) && year2.equals(year)) {
                    personCount = keyValuePair2.getValue();
                    session.run("MERGE (d:NumberOfPerson {value: $personCount})", parameters("personCount", personCount));
                    break;
                }
            }

            session.run("MERGE (y:Year {value: $year})", parameters("year", year));
            session.run("MERGE (d:Dwelling_Housing_Type {name: $type})", parameters("type", housingType));
            session.run("MERGE (d:Dwelling_Housing_Size {name: $size})", parameters("size", dwellingSize));
            Result countResult = session.run(
                    "MATCH (d:Dwelling)-[:HAS_HOUSING_TYPE]->(n:Dwelling_Housing_Type {name: $type}), " +
                            "(d)-[:HAS_DWELLING_SIZE]->(s:Dwelling_Housing_Size {name: $size}), " +
                            "(d)-[:LOCATED_IN]->(m:Municipality {code: $code}), " +
                            "(d)-[:PARTICIPATED_IN]->(y:Year {value: $year}) " +
                            "RETURN COUNT(DISTINCT d) AS dwellingCount",
                    parameters("code", municipalityCode, "type", housingType, "size", dwellingSize, "year", year)
            );
            if (countResult.hasNext() && count != 0) {
                Record record = countResult.next();
                long dwellingCount = record.get("dwellingCount").asLong();
                System.out.println(dwellingCount);
                if (dwellingCount < count) {
                    count = count - dwellingCount;

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
                                        "MATCH (e:NumberOfPerson {value: $personCount}) " +
                                        "MERGE (d)-[:HAS_NUMBER_OF_PERSON]->(e) " +
                                        "WITH d " +
                                        "MATCH (y:Year {value: $year}) " +
                                        "MERGE (d)-[:PARTICIPATED_IN]->(y)",
                                parameters(
                                        "code", municipalityCode,
                                        "type", housingType,
                                        "size", dwellingSize,
                                        "personCount", personCount,
                                        "year", year
                                )
                        );
                    }
                }
            }
        }
    }

    private void pushSixthApi() throws Exception {
        List<KeyValuePair> medianList = new ArrayList<KeyValuePair>(TablesRequest.sixth2Api());
        List<KeyValuePair> meanList = new ArrayList<KeyValuePair>(TablesRequest.sixth3Api());
        for (KeyValuePair keyValuePair : TablesRequest.sixth1Api()) {
            if (keyValuePair.getValue().equals("..")) {
                continue;
            }
            String[] keys = keyValuePair.getKey();
            String municipalityCode = keys[0];
            double count = Double.parseDouble(keyValuePair.getValue());
            String tenure = keys[1];
            String householdType = keys[2];
            String year = keys[3];
            String medianValue = "";
            String meanValue = "";
            for (KeyValuePair p : medianList) {
                String[] keys2 = p.getKey();
                String municipalityCode2 = keys2[0];
                String tenure2 = keys2[1];
                String householdType2 = keys2[2];
                String year2 = keys2[3];
                if (municipalityCode2.equals(municipalityCode) && tenure2.equals(tenure) && householdType2.equals(householdType) && year2.equals(year)) {
                    medianValue = p.getValue();
                    session.run("MERGE (h:Housing_Expenditures_Median {value: $median})", parameters("median", medianValue));
                    break;
                }
            }
            for (KeyValuePair p2 : meanList) {
                String[] keys3 = p2.getKey();
                String municipalityCode3 = keys3[0];
                String tenure3 = keys3[1];
                String householdType3 = keys3[2];
                String year3 = keys3[3];
                if (municipalityCode3.equals(municipalityCode) && tenure3.equals(tenure) && householdType3.equals(householdType) && year3.equals(year)) {
                    meanValue = p2.getValue();
                    session.run("MERGE (h:Housing_Expenditures_Mean {value: $mean})", parameters("mean", meanValue));
                    break;
                }
            }
            session.run("MERGE (y:Year {value: $year})", parameters("year", year));
            session.run("MERGE (t:Dwelling_Household_Type {name: $type})", parameters("type", householdType));
            session.run("MERGE (t:Dwelling_Tenure {name: $tenure})", parameters("tenure", tenure));

            Result countResult = session.run(
                    "MATCH (d:Dwelling)-[:HAS_HOUSEHOLD_TYPE]->(n:Dwelling_Household_Type {name: $type}), " +
                            "(d)-[:HAS_TENURE]->(s:Dwelling_Tenure {name: $tenure}), " +
                            "(d)-[:LOCATED_IN]->(m:Municipality {code: $code}), " +
                            "(d)-[:PARTICIPATED_IN]->(y:Year {value: $year}) " +
                            "RETURN COUNT(DISTINCT d) AS dwellingCount",
                    parameters("code", municipalityCode, "type", householdType, "tenure", tenure, "year", year)
            );
            if (countResult.hasNext() && count != 0) {
                Record record = countResult.next();
                long dwellingCount = record.get("dwellingCount").asLong();
                System.out.println(dwellingCount);
                if (dwellingCount < count) {
                    count = count - dwellingCount;
                    for (int i = 0; i < count; i++) {
                        session.run(
                                "MATCH (m:Municipality {code: $code}) " +
                                        "WITH m " +
                                        "CREATE (d:Dwelling)-[:LOCATED_IN]->(m) " +
                                        "WITH d " +
                                        "MATCH (n:Dwelling_Household_Type {name: $type}) " +
                                        "MERGE (d)-[:HAS_HOUSEHOLD_TYPE]->(n) " +
                                        "WITH d " +
                                        "MATCH (s:Dwelling_Tenure {name: $tenure}) " +
                                        "MERGE (d)-[:HAS_TENURE]->(s) " +
                                        "WITH d " +
                                        "MATCH (e:Housing_Expenditures_Mean {value: $mean}) " +
                                        "MERGE (d)-[:HAS_HOUSING_EXPENDITURES_MEAN]->(e) " +
                                        "WITH d " +
                                        "MATCH (w:Housing_Expenditures_Median {value: $median}) " +
                                        "MERGE (d)-[:HAS_HOUSING_EXPENDITURES_MEDIAN]->(w) " +
                                        "WITH d " +
                                        "MATCH (y:Year {value: $year}) " +
                                        "MERGE (d)-[:PARTICIPATED_IN]->(y)",
                                parameters(
                                        "code", municipalityCode,
                                        "type", householdType,
                                        "tenure", tenure,
                                        "mean", meanValue,
                                        "median", medianValue,
                                        "year", year
                                )
                        );
                    }
                }
            }

        }
    }

    private void pushSeventhApi() throws Exception {
        try {
            for (KeyValuePair keyValuePair : TablesRequest.seventhApi()) {
                if (keyValuePair.getValue().equals("..")) {
                    continue;
                }
                String[] keys = keyValuePair.getKey();
                int count = Integer.parseInt(keyValuePair.getValue());
                String municipalityCode = keys[0];
                String educationLevel = keys[1];
                String gender = keys[2];
                String ageRange = keys[3];
                String incomeRange = keys[4];
                String year = keys[5];

                session.run("MERGE (g:Gender {name: $gender})", parameters("gender", gender));
                session.run("MERGE (e:Education_Level {name: $educationLevel})", parameters("educationLevel", educationLevel));
                session.run("MERGE (a:Age_Range {name: $age})", parameters("age", ageRange));
                session.run("MERGE (i:Income {value: $income})", parameters("income", incomeRange));
                session.run("MERGE (y:Year {value: $year})", parameters("year", year));

                Result countResult = session.run(
                        "MATCH (p:Person)-[:HAS_EDUCATION_LEVEL]->(n:Education_Level {name: $level})," +
                                "(p)-[:LIVES_IN]->(m:Municipality {code: $code})," +
                                "(p)-[:HAS_GENDER]->(g:Gender {name: $gender})," +
                                "(p)-[:HAS_AGE_RANGE]->(a:Age_Range {name: $range})," +
                                "(p)-[:HAS_INCOME]->(i:Income {value: $income})," +
                                "(p)-[:PARTICIPATED_IN]->(y:Year {value: $year}) " +
                                "RETURN COUNT(DISTINCT p) AS personCount",
                        parameters(
                                "code", municipalityCode,
                                "gender", gender,
                                "level", educationLevel,
                                "range", ageRange,
                                "income", incomeRange,
                                "year", year
                        )
                );

                if (countResult.hasNext() && count != 0) {
                    Record record = countResult.next();
                    long personCount = record.get("personCount").asLong();
                    System.out.println(personCount);
                    if (personCount < count) {
                        int additionalCount = count - (int) personCount;
                        for (int i = 0; i < additionalCount; i++) {
                            session.run(
                                    "MATCH (m:Municipality {code: $code}) " +
                                            "WITH m " +
                                            "CREATE (p:Person)-[:LIVES_IN]->(m) " +
                                            "WITH p " +
                                            "MATCH (g:Gender {name: $gender}) " +
                                            "MERGE (p)-[:HAS_GENDER]->(g) " +
                                            "WITH p " +
                                            "MATCH (a:Age_Range {name: $age}) " +
                                            "MERGE (p)-[:HAS_AGE_RANGE]->(a) " +
                                            "WITH p " +
                                            "MATCH (e:Education_Level {name: $level}) " +
                                            "MERGE (p)-[:HAS_EDUCATION_LEVEL]->(e) " +
                                            "WITH p " +
                                            "MATCH (i:Income {value: $income}) " +
                                            "MERGE (p)-[:HAS_INCOME]->(i) " +
                                            "WITH p " +
                                            "MATCH (y:Year {value: $year}) " +
                                            "MERGE (p)-[:PARTICIPATED_IN]->(y)",
                                    parameters(
                                            "code", municipalityCode,
                                            "gender", gender,
                                            "level", educationLevel,
                                            "age", ageRange,
                                            "income", incomeRange,
                                            "year", year
                                    )
                            );
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    private void disconnect() throws Exception {
        try {
            // Close the session and driver
            session.close();
            driver.close();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void run() throws Exception {
        PushToDB p = new PushToDB();
        p.connect();
        p.pushCounties();
        p.pushFirstApi();
        p.pushSecondApi();
        p.pushThirdApi();
        p.pushFourthApi();
        p.pushFifthApi();
        p.pushSixthApi();
        p.pushSeventhApi();
        p.disconnect();
    }
}

