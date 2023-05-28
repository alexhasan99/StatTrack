package stattrack.stattrack.APIRequest;

import stattrack.stattrack.PushAPIs.MunicipalityCodeLookup;

public class ApiQueries {
    protected static final String api1Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeSK";
    protected static final String api2Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/UF/UF0507/StudiedeltagandeK";
    protected static final String api3Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/MI/MI0810/MI0810B/BefTatortTypBostReg";
    protected static final String api4Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/MI/MI0805/MI0805A/GYMaTackeAllmToReg";
    protected static final String api5Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0111/HE0111A/HushallT30";
    protected static final String api5_2Url = " https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0111/HE0111A/HushallT30";
    protected static final String api6_1Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0202/HE0202T02";
    protected static final String api6_2Url = " https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0202/HE0202T02";
    protected static final String api6_3Url = "  https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0202/HE0202T02";
    protected static final String api7Url = "https://api.scb.se/OV0104/v1/doris/en/ssd/START/HE/HE0110/HE0110A/SamForvInk1c";
    private static String municipalitiesQuery(){
        String s= new String();
        MunicipalityCodeLookup m= new MunicipalityCodeLookup();
        for (String c: m.getMunicipalityMap().keySet()) {
            s+= "          \"" + c + "\",\n";
        }
        return s;
    }


    protected static String getFirstApiQuery(int year) {
        String addYear = "          \"" + year + "\",\n";
        String api1QueryFirst = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07\",\n" +
                "        \"values\": [\n"
                        +municipalitiesQuery()+
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

    protected static String getSecondApiQuery(int year) {
        String addYear = "          \"" + year + "\",\n";
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07\",\n" +
                "        \"values\": [\n"
                    +municipalitiesQuery()+
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

    protected static String getThirdApiQuery(int year) {
        String addYear = "          \"" + year + "\",\n";
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07EjAggr\",\n" +
                "        \"values\": [\n"
                    +municipalitiesQuery()+
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

    protected static String getFourthApiQuery() {
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07\",\n" +
                "        \"values\": [\n"
                        +municipalitiesQuery()+
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

    protected static String getFifthApi(int year, String code) {
        String addYear = "          \"" + year + "\",\n";
        String addCode = "          \"" + code + "\"\n";
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07EjAggr\",\n" +
                "        \"values\": [\n"
                    +municipalitiesQuery()+
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Boendeform\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"FBBO\",\n" +
                "          \"FBHY0\",\n" +
                "          \"FBBOHY\",\n" +
                "          \"SPBO\",\n" +
                "          \"OB\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Lagenhetstyp\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1R+\",\n" +
                "          \"1RKV+KS\",\n" +
                "          \"1RK\",\n" +
                "          \"2RKV+KS\",\n" +
                "          \"2RK\",\n" +
                "          \"3RK\",\n" +
                "          \"4RK\",\n" +
                "          \"5RK\",\n" +
                "          \"6+RK\",\n" +
                "          \"UPPGSAKNs\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"ContentsCode\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                + addCode +
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

    protected static String getFifthFirstApiQuery(int year) {
        return getFifthApi(year, "0000025T");
    }

    protected static String getFifthSecondApiQuery(int year) {
        return getFifthApi(year, "0000025U");
    }

    protected static String getSixthApi(int year, String code) {
        String addYear = "          \"" + year + "\",\n";
        String addCode = "          \"" + code + "\"\n";
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"0010\",\n" +
                "          \"0020\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Upplatelseform\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"1\",\n" +
                "          \"2\",\n" +
                "          \"3\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Hushallstyp\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"ESUB\",\n" +
                "          \"SBUB\",\n" +
                "          \"ESMB\",\n" +
                "          \"SBMB\",\n" +
                "          \"OVRIGA\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"ContentsCode\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                        +addCode+
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n"
                        +addYear+
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

    protected static String getSixthFirstApiQuery(int year) {
        return getSixthApi(year, "000000XP");
    }

    protected static String getSixthSecondApiQuery(int year) {
        return getSixthApi(year, "000000Y2");
    }

    protected static String getSixthThirdApiQuery(int year) {
        return getSixthApi(year, "000000Y1");
    }

    protected static String getSeventhFirstApiQuery(int year, String municipalityCode) {
        return getSeventhApiQuery(year, "HE0110AL", municipalityCode);
    }

    protected static String getSeventhSecondApiQuery(int year, String municipalityCode) {
        return getSeventhApiQuery(year, "HE0110AI", municipalityCode);
    }

    protected static String getSeventhThirdApiQuery(int year, String municipalityCode) {
        return getSeventhApiQuery(year, "HE0110AJ", municipalityCode);
    }

    protected static String getSeventhFourthApiQuery(int year, String municipalityCode) {
        return getSeventhApiQuery(year, "HE0110AK", municipalityCode);
    }

    protected static String getSeventhApiQuery(int year, String code, String municipalityCode) {
        String addYear = "          \"" + year + "\",\n";
        String addCode = "          \"" + code + "\",\n";
        String addMunicipalityCode = "          \"" + municipalityCode + "\",\n";
        String apiQuery = "{\n" +
                "  \"query\": [\n" +
                "    {\n" +
                "      \"code\": \"Region\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"vs:RegionKommun07EjAggr\",\n" +
                "        \"values\": [\n" +
                addMunicipalityCode+
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"UtbildningsNiva\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"21\",\n" +
                "          \"3+4\",\n" +
                "          \"8\",\n" +
                "          \"US\"\n" +
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
                "      \"code\": \"Inkomstklass\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                "          \"0\",\n" +
                "          \"1-19\",\n" +
                "          \"20-39\",\n" +
                "          \"40-59\",\n" +
                "          \"60-79\",\n" +
                "          \"80-99\",\n" +
                "          \"100-119\",\n" +
                "          \"120-139\",\n" +
                "          \"140-159\",\n" +
                "          \"160-179\",\n" +
                "          \"180-199\",\n" +
                "          \"200-219\",\n" +
                "          \"220-239\",\n" +
                "          \"240-259\",\n" +
                "          \"260-279\",\n" +
                "          \"280-299\",\n" +
                "          \"500-599\",\n" +
                "          \"300-319\",\n" +
                "          \"320-339\",\n" +
                "          \"340-359\",\n" +
                "          \"360-379\",\n" +
                "          \"380-399\",\n" +
                "          \"400-499\",\n" +
                "          \"600-799\",\n" +
                "          \"800-999\",\n" +
                "          \"1000+\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"ContentsCode\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                            addCode+
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"code\": \"Tid\",\n" +
                "      \"selection\": {\n" +
                "        \"filter\": \"item\",\n" +
                "        \"values\": [\n" +
                            addYear+
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
