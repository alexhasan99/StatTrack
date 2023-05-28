package stattrack.stattrack;

import stattrack.stattrack.APIRequest.TablesRequest;
import stattrack.stattrack.PushAPIs.PushToDB;

public class Main {
    public static void main(String[] args) throws Exception {
        PushToDB p= new PushToDB();
        TablesRequest.secondApi();

    }
}
