package stattrack.stattrack.PushAPIs;

import java.util.HashMap;
import java.util.Map;

public class CountyCodeLookup {
    private final HashMap<String, String> countyMap = new HashMap<>();

    public CountyCodeLookup(){
        countyMap.put("01", "Stockholms län");
        countyMap.put("03", "Uppsala län");
        countyMap.put("04", "Södermanlands län");
        countyMap.put("05", "Östergötlands län");
        countyMap.put("06", "Jönköpings län");
        countyMap.put("07", "Kronobergs län");
        countyMap.put("08", "Kalmar län");
        countyMap.put("09", "Gotlands län");
        countyMap.put("10", "Blekinge län");
        countyMap.put("12", "Skåne län");
        countyMap.put("13", "Hallands län");
        countyMap.put("14", "Västra Götalands län");
        countyMap.put("17", "Värmlands län");
        countyMap.put("18", "Örebro län");
        countyMap.put("19", "Västmanlands län");
        countyMap.put("20", "Dalarnas län");
        countyMap.put("21", "Gävleborgs län");
        countyMap.put("22", "Västernorrlands län");
        countyMap.put("23", "Jämtlands län");
        countyMap.put("24", "Västerbottens län");
        countyMap.put("25", "Norrbottens län");
    }
    public String getCountyNameByCode(String code) {
        for (Map.Entry<String, String> entry : countyMap.entrySet()) {
            if (entry.getValue().equals(code)) {
                return entry.getKey();
            }
        }
        return null; // Code not found
    }

    public HashMap<String, String> getCountyMap() {
        return countyMap;
    }
}
