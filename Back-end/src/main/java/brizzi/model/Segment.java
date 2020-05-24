package brizzi.model;

public class Segment {

    private Position startingPosition;
    private Position endingPosition;
    private Float distance;
    private Float time;

    public Segment(){}

    public Segment(Position startingPosition, Position endingPosition, Float distance, Float time){
        this.startingPosition = startingPosition;
        this.endingPosition = endingPosition;
        this.distance = distance;
        this.time = time;
    }

    public Position getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(Position startingPosition) {
        this.startingPosition = startingPosition;
    }

    public Position getEndingPosition() {
        return endingPosition;
    }

    public void setEndingPosition(Position endingPosition) {
        this.endingPosition = endingPosition;
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
}
