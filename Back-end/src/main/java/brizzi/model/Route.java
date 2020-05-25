package brizzi.model;

import java.util.ArrayList;
import java.util.List;

public class Route {


    private List<Waypoint> waypoints;
    private List<Segment> segments;
    private RouteSummary routeSummary;

    public Route(){}


    public List<Segment> getSegments() {
        return segments;
    }
    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }
    public void addSegment(Segment seg){
        if(this.segments==null) this.segments = new ArrayList<>();
        this.segments.add(seg);
    }

    public RouteSummary getRouteSummary(){
        return this.routeSummary;
    }
    public void setRouteSummary(RouteSummary routeSummary){
        this.routeSummary = routeSummary;
    }

    public List<Waypoint> getWaypoints() {
        return waypoints;
    }
    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
    public void addWaypoint(Waypoint point){
        if(this.waypoints==null) this.waypoints = new ArrayList<>();
        this.waypoints.add(point);
    }

    public void populateSegments(List<Float> distances, List<Float> travelTimes){
        if(this.segments==null) this.segments = new ArrayList<>();
        for(int i=1; i<waypoints.size(); i++){
            Position startingPos = waypoints.get(i-1).getMappedPosition();
            Position endingPos = waypoints.get(i).getMappedPosition();
            segments.add(new Segment(startingPos, endingPos, distances.get(i-1), travelTimes.get(i-1)));
        }
    }

    public Position getEndingPosition(){
        if(waypoints == null) return new Position();
        int lastWaypointIdx = waypoints.size()-1;
        return(waypoints.get(lastWaypointIdx).getMappedPosition());
    }

}
