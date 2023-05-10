package stattrack.stattrack.test2;

import org.json.JSONArray;
import org.json.JSONObject;
import stattrack.stattrack.ScbApiRequest;

public class main2
{
    public static void main(String[] args) {
        // Construct the JSON query
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

        // Send the API request and get the response
        ScbApiRequest request = new ScbApiRequest();
        JSONObject response = request.postData(requestBody);

        // Print the response
        System.out.println(response);
    }

}
