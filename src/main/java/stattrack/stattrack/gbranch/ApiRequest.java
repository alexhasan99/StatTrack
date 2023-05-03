package stattrack.stattrack.gbranch;

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

    public static JSONObject parse(String input) {
        String[] lines = input.split("\n");
        JSONObject data = new JSONObject();

        for (String line : lines) {
            String[] parts = line.split("=");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim().replaceAll("\"", "");

                if (key.equals("CREATION-DATE")) {
                    data.put("creationDate", value);
                } else if (key.equals("HEADING")) {
                    String[] headings = value.split(",");
                    data.put("headings", headings);
                } else if (key.equals("DATA")) {
                    String[] values = value.split("\\s+");
                    data.put("values", values);
                }
            }
        }

        return data;
    }
}

