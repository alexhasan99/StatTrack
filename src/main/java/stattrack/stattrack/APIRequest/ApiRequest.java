package stattrack.stattrack.APIRequest;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiRequest
{
   private URL BASE_URL;

    public ApiRequest(String apiURL) throws MalformedURLException {
        this.BASE_URL  = new URL(apiURL);
    }
    public JSONObject fetchApiData(String jsonInputString) {
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
        public JSONObject getData(String table) {
            try {
                // Construct the URL for the API request
                URL url = this.BASE_URL;

                // Open a connection to the URL and set the request method
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                // Read the response from the API
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                return new JSONObject(response.toString());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }


}

