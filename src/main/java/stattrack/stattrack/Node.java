package stattrack.stattrack.gbranch;

import java.util.Map;


public class Node {
    private String label;
    private Map<String[], Object> properties;

    public Node(String label, Map<String[], Object> properties) {
        this.label = label;
        this.properties = properties;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label){
        this.label = label;
    }

        public Map<String[], Object> getProperties() {
            return properties;
        }

        public void setProperties(Map<String[], Object> properties) {
            this.properties = properties;
        }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Label: ").append(label).append(System.lineSeparator());
        sb.append("Properties: ").append(properties).append(System.lineSeparator());
        return sb.toString();
    }
    }

