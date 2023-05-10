    package stattrack.stattrack;

    import org.neo4j.driver.Driver;
    import org.neo4j.driver.Result;
    import org.neo4j.driver.Session;

    public class lal {

        public static void main(String[] args) {
            Neo4jConnection connection = new Neo4jConnection();
            Driver driver = connection.getDriver();

            try (Session session = driver.session()) {
                Result result = session.run("MATCH (n) RETURN count(n)");
                System.out.println(result.single().get(0).asInt());
            }

            connection.close();
            // Create an instance of the ScpApiRequest class



            /* // Construct the JSON query
            // Skapa en JSONObject för din förfrågan
           JSONObject requestBody = new JSONObject();
            JSONArray queryArray = new JSONArray();
            JSONObject queryObject = new JSONObject();
            JSONObject selectionObject = new JSONObject();

            selectionObject.put("filter", "item");
            JSONArray valuesArray = new JSONArray();
            valuesArray.put("2021");
            selectionObject.put("values", valuesArray);

            queryObject.put("code", "Tid");
            queryObject.put("selection", selectionObject);
            queryArray.put(queryObject);

            JSONObject responseObject = new JSONObject();
            responseObject.put("format", "px");

            requestBody.put("query", queryArray);
            requestBody.put("response", responseObject);

    // Skicka förfrågan och ta emot svaret
            JSONObject json2 = new JSONObject (requestBody);
            System.out.println(requestBody);

    // Display the data
            System.out.println(json2.names());
            System.out.println(json2.getBoolean("empty"));
            Iterator<String> keys = json2.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                Object value = json2.get(key);
                System.out.println("Key: " + key + " Value: " + value);
            }*/


        }

    }
