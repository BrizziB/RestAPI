package brizzi.model.DTO;

import brizzi.model.Segment;
import brizzi.model.Weather;

import java.util.List;

public class RouteDTO {

    private Float distance;
    private Float time;
    private List<Segment> segments;
    private Weather weatheratdest;

    public RouteDTO() {
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public Float getTime() {
        return time;
    }

    public void setTime(Float time) {
        this.time = time;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public Weather getWeatheratdest() {
        return weatheratdest;
    }

    public void setWeatheratdest(Weather weatheratdest) {
        this.weatheratdest = weatheratdest;
    }
}
