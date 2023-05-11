package stattrack.stattrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TablesRequest {
    public List<KeyValuePair> FirstApi() throws MalformedURLException {
        ApiRequest test = new ApiRequest(ApiQueries.api1Url);
        List<KeyValuePair> allKeyValuePairs = new ArrayList<>();

// Define the range of years you want to retrieve data for
        int startYear = 1999;
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
                System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
                System.out.println("Value: " + keyValuePair.getValue());
            }

            // Add the retrieved data for the current year to the overall list
            allKeyValuePairs.addAll(keyValuePairs);
        }

// Process the overall list of key-value pairs after all years have been processed
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            System.out.println("Overall Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Overall Value: " + keyValuePair.getValue());
        }
    return allKeyValuePairs;
    }
}
