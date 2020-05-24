package brizzi.model;

public class Position {

    private Float latitude;
    private Float longitude;

    public Position(){}

    public Position(Float latitude, Float longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }
}
