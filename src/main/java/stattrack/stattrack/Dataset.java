package stattrack.stattrack.gbranch;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private String url;
    private String jsonQuery;
    private ApiRequest apiRequest;

    public Dataset(String url, String jsonQuery) throws MalformedURLException {
        this.url = url;
        this.jsonQuery = jsonQuery;
        apiRequest = new ApiRequest(url);
    }

    public List<KeyValuePair> getData() {
        JSONObject results = apiRequest.fetchApiData(jsonQuery);
        System.out.println(results.get("data"));
        JSONArray jsonArray = new JSONArray(results.get("data").toString());
        ArrayList<KeyValuePair> keyValuePairs = new ArrayList<>();

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

            // Create a key-value pair object
            KeyValuePair keyValuePair = new KeyValuePair(key, values[0]);

            // Add the key-value pair to the list
            keyValuePairs.add(keyValuePair);
        }

        return (List<KeyValuePair>) keyValuePairs.clone();
    }
}
