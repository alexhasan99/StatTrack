package stattrack.stattrack.APIRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiRequest {
    private URL BASE_URL;

    public ApiRequest(String apiURL) throws MalformedURLException {
        this.BASE_URL = new URL(apiURL);
    }

    protected JSONObject fetchApiData(String jsonInputString) {
        URL apiUrl = this.BASE_URL;
        try {

            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            byte[] input = jsonInputString.getBytes("utf-8");
            connection.getOutputStream().write(input);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = new String();
            String line;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                response += line;
            }
            reader.close();
            return new JSONObject(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

