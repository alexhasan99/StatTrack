package stattrack.stattrack;

import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) throws MalformedURLException {

    }
}

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
            // Create a key-value pair object
            KeyValuePair keyValuePair = new KeyValuePair(key, values[0]);

            // Add the key-value pair to the list
            keyValuePairs.add(keyValuePair);

            // Create a node
           /* Map<String, Object> properties = new HashMap<>();
            for (int j = 1; j < key.length; j++) {
                properties.put(key[1], values[0]);
            }*/
//Node node = new Node(key[0], properties);

//Add the node to the list of nodes
//nodes.add(node);

//System.out.println("Key: " + Arrays.toString(key));
//System.out.println("Values: " + Arrays.toString(values));










