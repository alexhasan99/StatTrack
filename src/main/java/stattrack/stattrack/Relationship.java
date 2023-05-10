package stattrack.stattrack.gbranch;

import java.util.Map;



public class Relationship {
    private String type;
    private Node startNode;
    private Node endNode;
    private Map<String, Object> properties;

    public Relationship(String type, Node startNode, Node endNode, Map<String, Object> properties) {
        this.type = type;
        this.startNode = startNode;
        this.endNode = endNode;
        this.properties = properties;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}