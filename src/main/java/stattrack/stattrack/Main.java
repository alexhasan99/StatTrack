package stattrack.stattrack.gbranch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        String url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/LE/LE0102/LE0102L/LE0102L6";
        String jsonInputString = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07EjAggr\",\n" +
                "        \"values\": [\n" +
                "          \"0123\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Kon\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"5\",\n" +
                "          \"6\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Alder\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"0-21\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"2021\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"JSON\"\n" +
                "  }\n" +
                "}";

        ApiRequest test = new ApiRequest(url);
        JSONObject results = test.fetchApiData(jsonInputString);
        System.out.println(results.get("data"));
        JSONArray jsonArray = new JSONArray(results.get("data").toString());
        List<Node> nodes = new ArrayList<>();

        Map<String[],Object> node = new HashMap<>();
        // Iterate over the JSON array
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray valuesArray = jsonObject.getJSONArray("values");
            JSONArray keyArray = jsonObject.getJSONArray("key");

            String[] values = new String[valuesArray.length()];
            String[] key = new String[keyArray.length()];

            for (int j = 0; j < valuesArray.length(); j++) {
                values[j] = valuesArray.getString(j);
            }

            for (int j = 0; j < keyArray.length(); j++) {
                key[j] = keyArray.getString(j);
            }

            node.put(key,values[0]);


            // Create a node
           /* Map<String, Object> properties = new HashMap<>();
            for (int j = 1; j < key.length; j++) {
                properties.put(key[1], values[0]);
            }*/


            //Node node = new Node(key[0], properties);

            //Add the node to the list of nodes
            //nodes.add(node);

            System.out.println("Key: " + Arrays.toString(key));
            System.out.println("Values: " + Arrays.toString(values));
        }



        System.out.println(node.toString());
    }
}





  /*// Create relationships if necessary
if (key.length > 2) {
    String relationshipType = key[2];
    String startNodeLabel = key[0];
    String endNodeLabel = key[1];

    // Create relationship properties if available
    Map<String, Object> relationshipProperties = new HashMap<>();
    for (int j = 3; j < values.length; j++) {
        relationshipProperties.put(key[j], values[j - 2]);
    }

    // Create start and end nodes for the relationship
    Node startNode = new Node(startNodeLabel, Collections.emptyMap());
    Node endNode = new Node(endNodeLabel, Collections.emptyMap());

    // Create the relationship
    Relationship relationship = new Relationship(relationshipType, startNode, endNode, relationshipProperties);

    // Add the relationship to the list of relationships
    relationships.add(relationship);
}
*/



        // At this point, you have a list of nodes and relationships representing your data
        // You can further process or push them to the database as needed
        // ...

        // Example: Printing the nodes and relationships







        /*for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray valuesArray = jsonObject.getJSONArray("values");
            JSONArray keyArray = jsonObject.getJSONArray("key");

            String[] values = new String[valuesArray.length()];
            String[] key = new String[keyArray.length()];

            for (int j = 0; j < valuesArray.length(); j++) {
                values[j] = valuesArray.getString(j);
            }

            for (int j = 0; j < keyArray.length(); j++) {
                key[j] = keyArray.getString(j);
            }

            // Perform further processing with the values and key arrays


        }*/



