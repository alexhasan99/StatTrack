package stattrack.stattrack.APIRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.*;

public class TablesRequest {

    private static List<KeyValuePair> apiRequest(int startYear, int endYear, String apiUrl) throws MalformedURLException {
        ApiRequest test = new ApiRequest(apiUrl);
        List<KeyValuePair> allKeyValuePairs = new ArrayList<>();

// Define the range of years you want to retrieve data for
        for (int year = startYear; year <= endYear; year = getNextYear(year, apiUrl)) {
            // Make the API request for the current year
            String apiQuery = getApiQuery(year, apiUrl);
            JSONObject results = test.fetchApiData(apiQuery);

            // Extract the data from the API response
            JSONArray jsonArray = new JSONArray(results.get("data").toString());
            Dataset dataset = new Dataset(apiUrl, apiQuery);
            List<KeyValuePair> keyValuePairs = dataset.getData();

            // Print the retrieved data for the current year
            /*for (KeyValuePair keyValuePair : keyValuePairs) {
                String[] key = keyValuePair.getKey();
                String value = keyValuePair.getValue();
                System.out.println(Arrays.toString(key));
                System.out.println(value);
            }*/
            // Add the retrieved data for the current year to the overall list
            allKeyValuePairs.addAll(keyValuePairs);
        }
        return allKeyValuePairs;
    }

    private static int getNextYear(int currentYear, String apiUrl) {
        if (apiUrl.equals(ApiQueries.api3Url)) {
            // 5-year intervals
            return currentYear + 5;
        } else if (apiUrl.equals(ApiQueries.api4Url)) {
            return currentYear + 5;
        } else if (apiUrl.equals(ApiQueries.api6_1Url)) {
            return currentYear + 2;
        }else if (apiUrl.equals(ApiQueries.api6_2Url)) {
            return currentYear + 2;
        }else if (apiUrl.equals(ApiQueries.api6_3Url)) {
            return currentYear + 2;
        }else {
            // Default to year after year
            return currentYear + 1;
        }

    }

    private static String getApiQuery(int year, String apiUrl) {
        String apiQuery = switch (apiUrl) {
            case ApiQueries.api1Url -> ApiQueries.getApi1QueryFirst(year);
            case ApiQueries.api2Url -> ApiQueries.getSecondApi(year);
            case ApiQueries.api3Url -> ApiQueries.getThirdApi(year);
            case ApiQueries.api4Url -> ApiQueries.getFourthApi();
            case ApiQueries.api5Url -> ApiQueries.getFifthFirstApi(year);
            case ApiQueries.api5_2Url -> ApiQueries.getFifthSecondApi(year);
            case ApiQueries.api6_1Url -> ApiQueries.getSixthFirstApi(year);
            case ApiQueries.api6_2Url -> ApiQueries.getSixthSecondApi(year);
            case ApiQueries.api6_3Url -> ApiQueries.getSixthThirdApi(year);
            case ApiQueries.api7Url -> ApiQueries.getSeventhApi(year);
            default ->
                // Handle the case for unknown API URL
                    "";
        };

        return apiQuery;
    }


    public static List<KeyValuePair> firstApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs = apiRequest(2020, 2021, ApiQueries.api1Url);
// Process the overall list of key-value pairs after all years have been processed
        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("1", "men");
        key1ReplacementMap.put("2", "woman");

        Map<String, String> key2ReplacementMap = new HashMap<>();
        key2ReplacementMap.put("1", "upper secondary education");
        key2ReplacementMap.put("2", "municipal adult education");
        key2ReplacementMap.put("4", "higher education, first and second cycle studies");

// Define the mapping of key "9" to its corresponding string options
        Map<String, String> key3ReplacementMap = new HashMap<>();
        key3ReplacementMap.put("1", "living and studying in the same municipality");
        key3ReplacementMap.put("2", "studying in other municipality in the county of residence");
        key3ReplacementMap.put("3", "studying outside the county of residence");
        key3ReplacementMap.put("0", "non-students");
        key3ReplacementMap.put("9", "no information");

// Create a new list to store the updated KeyValuePair objects
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();

// Iterate over the original list
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1 = key[1];
            String updatedKey2 = key[2];
            String updatedKey3 = key[3];  // Assuming the key is at index 3 in the key array

            // Check if the key is present in the key3ReplacementMap
            if (key3ReplacementMap.containsKey(updatedKey3)) {
                updatedKey3 = key3ReplacementMap.get(updatedKey3);
            }
            if (key2ReplacementMap.containsKey(updatedKey2)) {
                updatedKey2 = key2ReplacementMap.get(updatedKey2);
            }
            if (key1ReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = key1ReplacementMap.get(updatedKey1);
            }
            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, updatedKey2, updatedKey3, key[4]},
                    value
            );

            updatedKeyValuePairList.add(updatedKeyValuePair);
        }

// Print the updated key-value pairs
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }

    public static List<KeyValuePair> secondApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs = apiRequest(2021, 2021, ApiQueries.api2Url);
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("1", "men");
        keyReplacementMap.put("2", "woman");
        Map<String, String> key3ReplacementMap = new HashMap<>();
        key3ReplacementMap.put("1", "upper secondary education");
        key3ReplacementMap.put("2", "municipal adult education");
        key3ReplacementMap.put("H", "higher education");
        key3ReplacementMap.put("R" +
                "", "other students");
        key3ReplacementMap.put("0", "non-students");

        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();

// Iterate over the original list
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1 = key[1];
            String updatedKey3 = key[3];

            // Check if the key is present in the key3ReplacementMap
            if (key3ReplacementMap.containsKey(updatedKey3)) {
                updatedKey3 = key3ReplacementMap.get(updatedKey3);
            }
            if (keyReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = keyReplacementMap.get(updatedKey1);
            }
            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, key[2], updatedKey3, key[4]},
                    value
            );
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }

    public static List<KeyValuePair> thirdApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs = apiRequest(2015, 2020, ApiQueries.api3Url);
        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("0045", "one- or two dwelling buildings");
        key1ReplacementMap.put("0055", "multi-dwelling buildings and commercial buildings");
        key1ReplacementMap.put("0065", "other");
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey = key[1];
            if (key1ReplacementMap.containsKey(updatedKey)) {
                updatedKey = key1ReplacementMap.get(updatedKey);
            }
            if (!updatedKey.equals("007")) {
                KeyValuePair updatedKeyValuePair = new KeyValuePair(
                        new String[]{key[0], updatedKey, key[2]},
                        value
                );
                updatedKeyValuePairList.add(updatedKeyValuePair);
            }
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }

    public static List<KeyValuePair> forthApi() throws MalformedURLException {
        List<KeyValuePair> allKeyValuePairs = apiRequest(2010, 2015, ApiQueries.api4Url);
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("OM", "Open land");
        keyReplacementMap.put("SKOG", "Forest");
        keyReplacementMap.put("TOTG", "Total green space");
        keyReplacementMap.put("TOTLA", "Total land area");
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();
        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey = key[1];
            if (keyReplacementMap.containsKey(updatedKey)) {
                updatedKey = keyReplacementMap.get(updatedKey);
            }
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey, key[2]},
                    value);
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }


    public static List<KeyValuePair> fifth1Api()throws MalformedURLException{
        List<KeyValuePair> allKeyValuePairs = apiRequest(2021, 2022, ApiQueries.api5Url);
        return updateFifthApiKeys(allKeyValuePairs);
    }

    public static List<KeyValuePair> fifth2Api()throws MalformedURLException{
        List<KeyValuePair> allKeyValuePairs2 = apiRequest(2021, 2022, ApiQueries.api5_2Url);
        return updateFifthApiKeys(allKeyValuePairs2);
    }
    private static List<KeyValuePair> updateFifthApiKeys(List<KeyValuePair> keyValuePairList) {
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();
        // Key replacement mappings
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("FBBO", "multi-dwelling buildings, tenant-owned");
        keyReplacementMap.put("FBHY0", "multi-dwelling buildings, rented");
        keyReplacementMap.put("FBBOHY", "Multi-dwelling buildings, tenant-owned and rented dwellings");
        keyReplacementMap.put("SPBO", "special housing");
        keyReplacementMap.put("OB", "other housing");

        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("1R+", "dwellings without kitchen equipment");
        key1ReplacementMap.put("1RKV+KS", "1 room and kitchenette");
        key1ReplacementMap.put("1RK", "1 room and kitchen");
        key1ReplacementMap.put("2RKV+KS", "2 or more rooms with kitchenette");
        key1ReplacementMap.put("2RK", "2 rooms and kitchen");
        key1ReplacementMap.put("3RK", "3 rooms and kitchen");
        key1ReplacementMap.put("4RK", "4 rooms and kitchen");
        key1ReplacementMap.put("5RK", "5 rooms and kitchen");
        key1ReplacementMap.put("6+RK", "6 or more rooms and kitchen");
        key1ReplacementMap.put("UPPGSAKNs", "data missing");

        // Iterate over the original list
        for (KeyValuePair keyValuePair : keyValuePairList) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1 = key[1];
            String updatedKey2 = key[2];

            if (key1ReplacementMap.containsKey(updatedKey2)) {
                updatedKey2 = key1ReplacementMap.get(updatedKey2);
            }
            if (keyReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = keyReplacementMap.get(updatedKey1);
            }

            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, updatedKey2, key[3]},
                    value
            );
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }

        return updatedKeyValuePairList;
    }

    public static List<KeyValuePair> sixth1Api() throws MalformedURLException{
        return updateSixthApiKeys(apiRequest(2015,2017,ApiQueries.api6_1Url));
    }

    public static List<KeyValuePair> sixth2Api() throws MalformedURLException{
        return updateSixthApiKeys(apiRequest(2015,2017,ApiQueries.api6_2Url));
    }

    public static List<KeyValuePair> sixth3Api() throws MalformedURLException{
        return updateSixthApiKeys(apiRequest(2015,2017,ApiQueries.api6_3Url));
    }

    private static List<KeyValuePair> updateSixthApiKeys(List<KeyValuePair> keyValuePairList) {
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();
        // Key replacement mappings
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("0010", "0180");
        keyReplacementMap.put("0020", "1480");

        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("1", "rented dwellings");
        key1ReplacementMap.put("2", "tenant-owned dwellings");
        key1ReplacementMap.put("3", "owner-occupied dwellings");

        Map<String, String> key2ReplacementMap = new HashMap<>();
        key2ReplacementMap.put("ESUB", "single persons without children");
        key2ReplacementMap.put("SBUB", "cohabiting persons without children");
        key2ReplacementMap.put("ESMB", "single persons with children");
        key2ReplacementMap.put("SBMB", "cohabiting persons with children");
        key2ReplacementMap.put("OVRIGA", "other households");


        // Iterate over the original list
        for (KeyValuePair keyValuePair : keyValuePairList) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey = key[0];
            String updatedKey1 = key[1];
            String updatedKey2 = key[2];
            if (keyReplacementMap.containsKey(updatedKey)) {
                updatedKey = keyReplacementMap.get(updatedKey);
            }
            if (key1ReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = key1ReplacementMap.get(updatedKey1);
            }
            if (key2ReplacementMap.containsKey(updatedKey2)) {
                updatedKey2 = key2ReplacementMap.get(updatedKey2);
            }
            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{updatedKey, updatedKey1, updatedKey2, key[3]},
                    value
            );
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }



    public static List<KeyValuePair> seventhApi() throws MalformedURLException {
        List<KeyValuePair> updatedKeyValuePairList = new ArrayList<>();
        List<KeyValuePair> allKeyValuePairs = apiRequest(2020, 2021, ApiQueries.api7Url);
        Map<String, String> keyReplacementMap = new HashMap<>();
        keyReplacementMap.put("21", "primary and lower secondary education");
        keyReplacementMap.put("3+4", "upper secondary education");
        keyReplacementMap.put("8", "post secondary education");
        keyReplacementMap.put("US", "no information about level of educational attainment");

        Map<String, String> key1ReplacementMap = new HashMap<>();
        key1ReplacementMap.put("1", "men");
        key1ReplacementMap.put("2", "women");


        for (KeyValuePair keyValuePair : allKeyValuePairs) {
            String[] key = keyValuePair.getKey();
            String value = keyValuePair.getValue();
            String updatedKey1 = key[1];
            String updatedKey2 = key[2];
            if (key1ReplacementMap.containsKey(updatedKey2)) {
                updatedKey2 = key1ReplacementMap.get(updatedKey2);
            }
            if (keyReplacementMap.containsKey(updatedKey1)) {
                updatedKey1 = keyReplacementMap.get(updatedKey1);
            }

            // Create a new KeyValuePair with the updated key and original value
            KeyValuePair updatedKeyValuePair = new KeyValuePair(
                    new String[]{key[0], updatedKey1, updatedKey2, key[3], key[4], key[5]},
                    value
            );
            updatedKeyValuePairList.add(updatedKeyValuePair);
        }
        for (KeyValuePair keyValuePair : updatedKeyValuePairList) {
            System.out.println("Key: " + Arrays.toString(keyValuePair.getKey()));
            System.out.println("Value: " + keyValuePair.getValue());
        }
        return updatedKeyValuePairList;
    }


}
