package stattrack.stattrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.*;

public class TablesRequest {
    public static List<KeyValuePair> FirstApi() throws MalformedURLException {
        ApiRequest test = new ApiRequest(ApiQueries.api1Url);
        List<KeyValuePair> allKeyValuePairs = new ArrayList<>();

// Define the range of years you want to retrieve data for
        int startYear = 2020;
        int endYear = 2021;

        for (int year = startYear; year <= endYear; year++) {
            // Make the API request for the current year
            JSONObject results = test.fetchApiData(ApiQueries.getApi1QueryFirst(year));

            // Extract the data from the API response
            JSONArray jsonArray = new JSONArray(results.get("data").toString());
            Dataset dataset = new Dataset(ApiQueries.api1Url, ApiQueries.getApi1QueryFirst(year));
            List<KeyValuePair> keyValuePairs = dataset.getData();

            // Print the retrieved data for the current year
            for (KeyValuePair keyValuePair : keyValuePairs) {
                String[] key = keyValuePair.getKey();
                String value = keyValuePair.getValue();
            }
            // Add the retrieved data for the current year to the overall list
            allKeyValuePairs.addAll(keyValuePairs);
        }

// Process the overall list of key-value pairs after all years have been processed
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
        }
        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("1", "men");
        key1ReplacementMap.put("2", "woman");

        Map<String, String> key2ReplacementMap = new HashMap<>();
        key2ReplacementMap.put("1", "upper secondary education");
        key2ReplacementMap.put("2", "municipal adult education");
        key2ReplacementMap.put("4", "higher education, first and second cycle studies");

// Define the mapping of key "9" to its corresponding string options
        Map<String, String> key3ReplacementMap = new HashMap<>();
        key3ReplacementMap.put("1", "living and studying in the same municipality");
        key3ReplacementMap.put("2", "studying in other municipality in the county of residence");
        key3ReplacementMap.put("3", "studying outside the county of residence");
        key3ReplacementMap.put("0", "non-students");
        key3ReplacementMap.put("9", "no information");

// Create a new list to store the updated KeyValuePair objects
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();

// Iterate over the original list
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1= key[1];
            String updatedKey2 = key[2];
            String updatedKey3 = key[3];  // Assuming the key is at index 3 in the key array

            // Check if the key is present in the key3ReplacementMap
            if (key3ReplacementMap.containsKey(updatedKey3)) {
                updatedKey3 = key3ReplacementMap.get(updatedKey3);
            }
            if(key2ReplacementMap.containsKey(updatedKey2)){
                updatedKey2= key2ReplacementMap.get(updatedKey2);
            }
            if(key1ReplacementMap.containsKey(updatedKey1)){
                updatedKey1= key1ReplacementMap.get(updatedKey1);
            }
            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, updatedKey2, updatedKey3, key[4]},
                    value
            );

            updatedKeyValuePairList.add(updatedKeyValuePair);
        }

// Print the updated key-value pairs
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }
}
