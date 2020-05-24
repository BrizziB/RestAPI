package brizzi.model;

public class RouteSummary {
    Float distance;
    Float trafficTime;
    Float baseTime;
    Float travelTime;

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getTrafficTime() {
        return trafficTime;
    }

    public void setTrafficTime(Float trafficTime) {
        this.trafficTime = trafficTime;
    }

    public Float getBaseTime() {
        return baseTime;
    }

    public void setBaseTime(Float baseTime) {
        this.baseTime = baseTime;
    }

    public Float getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Float travelTime) {
        this.travelTime = travelTime;
    }
}
