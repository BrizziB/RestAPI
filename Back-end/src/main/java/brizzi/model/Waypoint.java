package brizzi.model;

public class Waypoint {

    private Long linkId;
    private Position mappedPosition;
    private Position originalPosition;
    private String type;
    private Float spot;
    private String sideOfTheStreet;
    private String mappedRoadName;
    private String label;
    private int shapeIndex;

    public Long getLinkId() {
        return linkId;
    }

    public void setLinkId(Long linkId) {
        this.linkId = linkId;
    }

    public Position getMappedPosition() {
        return mappedPosition;
    }

    public void setMappedPosition(Position mappedPosition) {
        this.mappedPosition = mappedPosition;
    }

    public Position getOriginalPosition() {
        return originalPosition;
    }

    public void setOriginalPosition(Position originalPosition) {
        this.originalPosition = originalPosition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getSpot() {
        return spot;
    }

    public void setSpot(Float spot) {
        this.spot = spot;
    }

    public String getSideOfTheStreet() {
        return sideOfTheStreet;
    }

    public void setSideOfTheStreet(String sideOfTheStreet) {
        this.sideOfTheStreet = sideOfTheStreet;
    }

    public String getMappedRoadName() {
        return mappedRoadName;
    }

    public void setMappedRoadName(String mappedRoadName) {
        this.mappedRoadName = mappedRoadName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getShapeIndex() {
        return shapeIndex;
    }

    public void setShapeIndex(int shapeIndex) {
        this.shapeIndex = shapeIndex;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    private String source;






}