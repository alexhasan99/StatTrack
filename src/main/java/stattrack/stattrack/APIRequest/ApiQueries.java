package stattrack.stattrack.APIRequest;

public class ApiQueries {
    public static final String api1Url= "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeSK";
    public static final String api2Url= "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeK";
    public static final String api3Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/MI/MI0810/MI0810B/BefTatortTypBostReg";

    public static String getApi1QueryFirst(int year){
        String addYear= "          \"" + year + "\",\n";
        String api1QueryFirst = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07\",\n" +
                "        \"values\": [\n" +
                "          \"0180\",\n" +
                "          \"0380\",\n" +
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
                "          \"3\",\n" +
                "          \"0\",\n" +
                "          \"9\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                            + addYear +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"JSON\"\n" +
                "  }\n" +
                "}";
        return api1QueryFirst;
    }

    public static String getSecondApi(int year){
        String addYear= "          \"" + year + "\",\n";
        String apiQuery= "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07\",\n" +
                "        \"values\": [\n" +
                "          \"0114\"\n" +
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
                "      \"code\": \"Alder\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"15\",\n" +
                "          \"16\",\n" +
                "          \"17\",\n" +
                "          \"18\",\n" +
                "          \"19\",\n" +
                "          \"20\",\n" +
                "          \"21\",\n" +
                "          \"22\",\n" +
                "          \"23\",\n" +
                "          \"24\",\n" +
                "          \"25\",\n" +
                "          \"26\",\n" +
                "          \"27\",\n" +
                "          \"28\",\n" +
                "          \"29\",\n" +
                "          \"30-34\",\n" +
                "          \"35-44\",\n" +
                "          \"45-54\",\n" +
                "          \"55-64\",\n" +
                "          \"65+\"\n" +
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
                "          \"H\",\n" +
                "          \"R\",\n" +
                "          \"0\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                + addYear +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"JSON\"\n" +
                "  }\n" +
                "}";
        return apiQuery;
    }

    public static String getThirdApi(int year){
        String addYear= "          \"" + year + "\",\n";
        String apiQuery= "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07EjAggr\",\n" +
                "        \"values\": [\n" +
                "          \"0114\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                + addYear +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"response\": {\n" +
                "    \"format\": \"JSON\"\n" +
                "  }\n" +
                "}";
    return apiQuery;
    }
}
