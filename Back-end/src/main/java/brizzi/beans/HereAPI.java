package brizzi.beans;


import brizzi.model.Position;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequestScoped
public class HereAPI { // ha la responsabilità di comunicare con l'endpoint di Here

    private Client client;
    private WebTarget target;

    private static final String routeBaseURL = "https://route.ls.hereapi.com/routing/7.2/calculateroute.json?";
    private static final String modeRelatedURL = "mode=fastest%3Bcar&";
    private static final String weatherBaseURL = "https://weather.ls.hereapi.com/weather/1.0/report.json?";
    private static final String oneObservationURL = "&oneobservation=true&";
    private static final String APIKeyURL = "apiKey=EgMofik-qVt5hrMY1QfA8gHumkeJOjzulcbo3wPMbe0";

    @PostConstruct
    public void init(){
        client = ClientBuilder.newClient();
    }

    @Produces("application/json")
    public String getRoute(List<Position> waypoints){
        target = client.target(buildRouteQuery(waypoints));
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    @Produces("application/json")
    public String getWeatherAtPosition(Position position){
        target = client.target(buildWeatherQuery(position));
        return target.request(MediaType.APPLICATION_JSON).get(String.class);
    }

    private String buildWeatherQuery(Position position){
        String reqBaseURL = weatherBaseURL + "product=observation&";
        String paramURL = "latitude="+ position.getLatitude() +"&longitude=" + position.getLongitude();
        return reqBaseURL + paramURL + oneObservationURL + APIKeyURL;
    }

    private String buildRouteQuery(List<Position> waypointsPositions){
        String paramURL = "";
        int i=0;
        for (Position position: waypointsPositions) {
            paramURL = paramURL +("waypoint" + i +"="+ position.getLatitude() +"%2C"+ position.getLongitude() + "&" );
            i++;
        }
        return routeBaseURL + paramURL + modeRelatedURL + APIKeyURL;

    }

}

