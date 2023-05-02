package stattrack.stattrack.gbranch;

import org.json.JSONObject;

public class Main
{
    public static void main(String[] args) {
        String url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeSK";
        String jsonInputString = "{\"query\":[{\"code\":\"Tid\",\"selection\":{\"filter\":\"item\",\"values\":[\"2021\"]}}],\"response\":{\"format\":\"JSON\"}}";
        JSONObject x = ApiRequest.fetchApiData(url,jsonInputString);
        System.out.println(x);
        System.out.println(x.names());
        System.out.println(x.get("data"));

    }

}
