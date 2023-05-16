package stattrack.stattrack.APIRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import stattrack.stattrack.KeyValuePair;

import java.net.MalformedURLException;
import java.util.*;

public class TablesRequest {

    private static List<KeyValuePair> apiRequest(int startYear, int endYear) throws MalformedURLException {
        ApiRequest test = new ApiRequest(ApiQueries.api2Url);
        List<KeyValuePair> allKeyValuePairs = new ArrayList<>();

// Define the range of years you want to retrieve data for
        for (int year = startYear; year <= endYear; year++) {
            // Make the API request for the current year
            JSONObject results = test.fetchApiData(ApiQueries.getSecondApi(year));

            // Extract the data from the API response
            JSONArray jsonArray = new JSONArray(results.get("data").toString());
            Dataset dataset = new Dataset(ApiQueries.api2Url, ApiQueries.getSecondApi(year));
            List<KeyValuePair> keyValuePairs = dataset.getData();

            // Print the retrieved data for the current year
            for (KeyValuePair keyValuePair : keyValuePairs) {
                String[] key = keyValuePair.getKey();
                String value = keyValuePair.getValue();
                System.out.println(Arrays.toString(key));
                System.out.println(value);
            }
            // Add the retrieved data for the current year to the overall list
            allKeyValuePairs.addAll(keyValuePairs);
        }
        return allKeyValuePairs;
    }
    public static List<KeyValuePair> FirstApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs= apiRequest(2020, 2021);
// Process the overall list of key-value pairs after all years have been processed
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
    public static List<KeyValuePair> secondApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs = apiRequest(2021, 2021);
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("1", "men");
        keyReplacementMap.put("2", "woman");
        Map<String, String> key3ReplacementMap = new HashMap<>();
        key3ReplacementMap.put("1", "upper secondary education");
        key3ReplacementMap.put("2", "municipal adult education");
        key3ReplacementMap.put("H", "higher education");
        key3ReplacementMap.put("R", "other students");
        key3ReplacementMap.put("0", "non-students");

        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();

// Iterate over the original list
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1 = key[1];
            String updatedKey3 = key[3];

            // Check if the key is present in the key3ReplacementMap
            if (key3ReplacementMap.containsKey(updatedKey3)) {
                updatedKey3 = key3ReplacementMap.get(updatedKey3);
            }
            if (keyReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = keyReplacementMap.get(updatedKey1);
            }
            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, key[2], updatedKey3, key[4]},
                    value
            );
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }

}
