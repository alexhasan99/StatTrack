package stattrack.stattrack;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws MalformedURLException {
        String url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeSK";
        String jsonInputString = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionLän07\",\n" +
                "        \"values\": [\n" +
                "          \"01\",\n" +
                "          \"03\",\n" +
                "          \"04\",\n" +
                "          \"05\",\n" +
                "          \"06\",\n" +
                "          \"07\",\n" +
                "          \"08\",\n" +
                "          \"09\",\n" +
                "          \"10\",\n" +
                "          \"12\",\n" +
                "          \"13\",\n" +
                "          \"14\",\n" +
                "          \"17\",\n" +
                "          \"18\",\n" +
                "          \"19\",\n" +
                "          \"20\",\n" +
                "          \"21\",\n" +
                "          \"22\",\n" +
                "          \"23\",\n" +
                "          \"24\",\n" +
                "          \"25\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Kon\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1\",\n" +
                "          \"2\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Studiedeltagande\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1\",\n" +
                "          \"2\",\n" +
                "          \"4\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"StudiePendling\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1\",\n" +
                "          \"2\",\n" +
                "          \"3\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"json\"\n" +
                "  }\n" +
                "}";
        String api2 = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/BE/BE0101/BE0101Y/FolkmDesoAldKonN";
        String json2 = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:CRegionKommun07\",\n" +
                "        \"values\": [\n" +
                "          \"0114\",\n" +
                "          \"0115\",\n" +
                "          \"0117\",\n" +
                "          \"0120\",\n" +
                "          \"0123\",\n" +
                "          \"0125\",\n" +
                "          \"0126\",\n" +
                "          \"0127\",\n" +
                "          \"0128\",\n" +
                "          \"0136\",\n" +
                "          \"0138\",\n" +
                "          \"0139\",\n" +
                "          \"0140\",\n" +
                "          \"0160\",\n" +
                "          \"0162\",\n" +
                "          \"0163\",\n" +
                "          \"0180\",\n" +
                "          \"0181\",\n" +
                "          \"0182\",\n" +
                "          \"0183\",\n" +
                "          \"0184\",\n" +
                "          \"0186\",\n" +
                "          \"0187\",\n" +
                "          \"0188\",\n" +
                "          \"0191\",\n" +
                "          \"0192\",\n" +
                "          \"0305\",\n" +
                "          \"0319\",\n" +
                "          \"0330\",\n" +
                "          \"0331\",\n" +
                "          \"0360\",\n" +
                "          \"0380\",\n" +
                "          \"0381\",\n" +
                "          \"0382\",\n" +
                "          \"0428\",\n" +
                "          \"0461\",\n" +
                "          \"0480\",\n" +
                "          \"0481\",\n" +
                "          \"0482\",\n" +
                "          \"0483\",\n" +
                "          \"0484\",\n" +
                "          \"0486\",\n" +
                "          \"0488\",\n" +
                "          \"0509\",\n" +
                "          \"0512\",\n" +
                "          \"0513\",\n" +
                "          \"0560\",\n" +
                "          \"0561\",\n" +
                "          \"0562\",\n" +
                "          \"0563\",\n" +
                "          \"0580\",\n" +
                "          \"0581\",\n" +
                "          \"0582\",\n" +
                "          \"0583\",\n" +
                "          \"0584\",\n" +
                "          \"0586\",\n" +
                "          \"0604\",\n" +
                "          \"0617\",\n" +
                "          \"0642\",\n" +
                "          \"0643\",\n" +
                "          \"0662\",\n" +
                "          \"0665\",\n" +
                "          \"0680\",\n" +
                "          \"0682\",\n" +
                "          \"0683\",\n" +
                "          \"0684\",\n" +
                "          \"0685\",\n" +
                "          \"0686\",\n" +
                "          \"0687\",\n" +
                "          \"0760\",\n" +
                "          \"0761\",\n" +
                "          \"0763\",\n" +
                "          \"0764\",\n" +
                "          \"0765\",\n" +
                "          \"0767\",\n" +
                "          \"0780\",\n" +
                "          \"0781\",\n" +
                "          \"0821\",\n" +
                "          \"0834\",\n" +
                "          \"0840\",\n" +
                "          \"0860\",\n" +
                "          \"0861\",\n" +
                "          \"0862\",\n" +
                "          \"0880\",\n" +
                "          \"0881\",\n" +
                "          \"0882\",\n" +
                "          \"0883\",\n" +
                "          \"0884\",\n" +
                "          \"0885\",\n" +
                "          \"0980\",\n" +
                "          \"1060\",\n" +
                "          \"1080\",\n" +
                "          \"1081\",\n" +
                "          \"1082\",\n" +
                "          \"1083\",\n" +
                "          \"1214\",\n" +
                "          \"1230\",\n" +
                "          \"1231\",\n" +
                "          \"1233\",\n" +
                "          \"1256\",\n" +
                "          \"1257\",\n" +
                "          \"1260\",\n" +
                "          \"1261\",\n" +
                "          \"1262\",\n" +
                "          \"1263\",\n" +
                "          \"1264\",\n" +
                "          \"1265\",\n" +
                "          \"1266\",\n" +
                "          \"1267\",\n" +
                "          \"1270\",\n" +
                "          \"1272\",\n" +
                "          \"1273\",\n" +
                "          \"1275\",\n" +
                "          \"1276\",\n" +
                "          \"1277\",\n" +
                "          \"1278\",\n" +
                "          \"1280\",\n" +
                "          \"1281\",\n" +
                "          \"1282\",\n" +
                "          \"1283\",\n" +
                "          \"1284\",\n" +
                "          \"1285\",\n" +
                "          \"1286\",\n" +
                "          \"1287\",\n" +
                "          \"1290\",\n" +
                "          \"1291\",\n" +
                "          \"1292\",\n" +
                "          \"1293\",\n" +
                "          \"1315\",\n" +
                "          \"1380\",\n" +
                "          \"1381\",\n" +
                "          \"1382\",\n" +
                "          \"1383\",\n" +
                "          \"1384\",\n" +
                "          \"1401\",\n" +
                "          \"1402\",\n" +
                "          \"1407\",\n" +
                "          \"1415\",\n" +
                "          \"1419\",\n" +
                "          \"1421\",\n" +
                "          \"1427\",\n" +
                "          \"1430\",\n" +
                "          \"1435\",\n" +
                "          \"1438\",\n" +
                "          \"1439\",\n" +
                "          \"1440\",\n" +
                "          \"1441\",\n" +
                "          \"1442\",\n" +
                "          \"1443\",\n" +
                "          \"1444\",\n" +
                "          \"1445\",\n" +
                "          \"1446\",\n" +
                "          \"1447\",\n" +
                "          \"1452\",\n" +
                "          \"1460\",\n" +
                "          \"1461\",\n" +
                "          \"1462\",\n" +
                "          \"1463\",\n" +
                "          \"1465\",\n" +
                "          \"1466\",\n" +
                "          \"1470\",\n" +
                "          \"1471\",\n" +
                "          \"1472\",\n" +
                "          \"1473\",\n" +
                "          \"1480\",\n" +
                "          \"1481\",\n" +
                "          \"1482\",\n" +
                "          \"1484\",\n" +
                "          \"1485\",\n" +
                "          \"1486\",\n" +
                "          \"1487\",\n" +
                "          \"1488\",\n" +
                "          \"1489\",\n" +
                "          \"1490\",\n" +
                "          \"1491\",\n" +
                "          \"1492\",\n" +
                "          \"1493\",\n" +
                "          \"1494\",\n" +
                "          \"1495\",\n" +
                "          \"1496\",\n" +
                "          \"1497\",\n" +
                "          \"1498\",\n" +
                "          \"1499\",\n" +
                "          \"1715\",\n" +
                "          \"1730\",\n" +
                "          \"1737\",\n" +
                "          \"1760\",\n" +
                "          \"1761\",\n" +
                "          \"1762\",\n" +
                "          \"1763\",\n" +
                "          \"1764\",\n" +
                "          \"1765\",\n" +
                "          \"1766\",\n" +
                "          \"1780\",\n" +
                "          \"1781\",\n" +
                "          \"1782\",\n" +
                "          \"1783\",\n" +
                "          \"1784\",\n" +
                "          \"1785\",\n" +
                "          \"1814\",\n" +
                "          \"1860\",\n" +
                "          \"1861\",\n" +
                "          \"1862\",\n" +
                "          \"1863\",\n" +
                "          \"1864\",\n" +
                "          \"1880\",\n" +
                "          \"1881\",\n" +
                "          \"1882\",\n" +
                "          \"1883\",\n" +
                "          \"1884\",\n" +
                "          \"1885\",\n" +
                "          \"1904\",\n" +
                "          \"1907\",\n" +
                "          \"1960\",\n" +
                "          \"1961\",\n" +
                "          \"1962\",\n" +
                "          \"1980\",\n" +
                "          \"1981\",\n" +
                "          \"1982\",\n" +
                "          \"1983\",\n" +
                "          \"1984\",\n" +
                "          \"2021\",\n" +
                "          \"2023\",\n" +
                "          \"2026\",\n" +
                "          \"2029\",\n" +
                "          \"2031\",\n" +
                "          \"2034\",\n" +
                "          \"2039\",\n" +
                "          \"2061\",\n" +
                "          \"2062\",\n" +
                "          \"2080\",\n" +
                "          \"2081\",\n" +
                "          \"2082\",\n" +
                "          \"2083\",\n" +
                "          \"2084\",\n" +
                "          \"2085\",\n" +
                "          \"2101\",\n" +
                "          \"2104\",\n" +
                "          \"2121\",\n" +
                "          \"2132\",\n" +
                "          \"2161\",\n" +
                "          \"2180\",\n" +
                "          \"2181\",\n" +
                "          \"2182\",\n" +
                "          \"2183\",\n" +
                "          \"2184\",\n" +
                "          \"2260\",\n" +
                "          \"2262\",\n" +
                "          \"2280\",\n" +
                "          \"2281\",\n" +
                "          \"2282\",\n" +
                "          \"2283\",\n" +
                "          \"2284\",\n" +
                "          \"2303\",\n" +
                "          \"2305\",\n" +
                "          \"2309\",\n" +
                "          \"2313\",\n" +
                "          \"2321\",\n" +
                "          \"2326\",\n" +
                "          \"2361\",\n" +
                "          \"2380\",\n" +
                "          \"2401\",\n" +
                "          \"2403\",\n" +
                "          \"2404\",\n" +
                "          \"2409\",\n" +
                "          \"2417\",\n" +
                "          \"2418\",\n" +
                "          \"2421\",\n" +
                "          \"2422\",\n" +
                "          \"2425\",\n" +
                "          \"2460\",\n" +
                "          \"2462\",\n" +
                "          \"2463\",\n" +
                "          \"2480\",\n" +
                "          \"2481\",\n" +
                "          \"2482\",\n" +
                "          \"2505\",\n" +
                "          \"2506\",\n" +
                "          \"2510\",\n" +
                "          \"2513\",\n" +
                "          \"2514\",\n" +
                "          \"2518\",\n" +
                "          \"2521\",\n" +
                "          \"2523\",\n" +
                "          \"2560\",\n" +
                "          \"2580\",\n" +
                "          \"2581\",\n" +
                "          \"2582\",\n" +
                "          \"2583\",\n" +
                "          \"2584\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Alder\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"totalt\",\n" +
                "          \"-4\",\n" +
                "          \"5-9\",\n" +
                "          \"10-14\",\n" +
                "          \"15-19\",\n" +
                "          \"20-24\",\n" +
                "          \"25-29\",\n" +
                "          \"30-34\",\n" +
                "          \"35-39\",\n" +
                "          \"40-44\",\n" +
                "          \"45-49\",\n" +
                "          \"50-54\",\n" +
                "          \"55-59\",\n" +
                "          \"60-64\",\n" +
                "          \"65-69\",\n" +
                "          \"70-74\",\n" +
                "          \"75-79\",\n" +
                "          \"80-\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Kon\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1\",\n" +
                "          \"2\",\n" +
                "          \"1+2\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"2010\",\n" +
                "          \"2011\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"json\"\n" +
                "  }\n" +
                "}";

        ApiRequest test = new ApiRequest(url);
        JSONObject results = test.fetchApiData(jsonInputString);
       // System.out.println(results.get("data"));
        JSONArray jsonArray = new JSONArray(results.get("data").toString());
        List<KeyValuePair> keyValuePairs = new ArrayList<>();

        Dataset last = new Dataset(api2,json2);
        keyValuePairs = last.getData();



        for (KeyValuePair keyValuePair : keyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
            // Create a node in Neo4j with the key and value
            // ...

            // Create relationships between nodes based on key-value associations
            // ...
        }



    }
}








        // At this point, you have a list of nodes and relationships representing your data
        // You can further process or push them to the database as needed
        // ...

        // Example: Printing the nodes and relationships







        /*for (int i = 0; i < jsonArray.length(); i++) {
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

            // Perform further processing with the values and key arrays






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

            // Create a node
           /* Map<String, Object> properties = new HashMap<>();
            for (int j = 1; j < key.length; j++) {
                properties.put(key[1], values[0]);
            }*/
//Node node = new Node(key[0], properties);

//Add the node to the list of nodes
//nodes.add(node);

//System.out.println("Key: " + Arrays.toString(key));
//System.out.println("Values: " + Arrays.toString(values));










