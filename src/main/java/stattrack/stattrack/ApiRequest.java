package stattrack.stattrack;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiRequest
{

    public static JSONObject fetchApiData(String url, String jsonInputString) {
        try {
            URL apiUrl = new URL(url);
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
                System.out.println(line);
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

