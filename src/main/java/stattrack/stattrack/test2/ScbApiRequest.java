package stattrack.stattrack.test2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ScbApiRequest {

    private static final String BASE_URL = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeSK";

    public JSONObject postData(JSONObject requestBody) {
        try {
            // Construct the URL for the API request
            URL url = new URL(BASE_URL);

            // Open a connection to the URL and set the request method to POST
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            // Set the request headers
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            // Enable output and input to the connection
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // Write the request body to the connection
            OutputStream os = conn.getOutputStream();
            os.write(requestBody.toString().getBytes("UTF-8"));
            os.close();

            // Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Return the response as a JSONObject
            return new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        // Construct the request body
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

        // Make the API request
        ScbApiRequest apiRequest = new ScbApiRequest();
        JSONObject response = apiRequest.postData(requestBody);
        System.out.println(response);
    }

}
