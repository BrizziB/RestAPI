package brizzi.mappers;

import brizzi.model.*;
import brizzi.model.DTO.RouteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.ejb.Stateless;
import java.util.ArrayList;


@Stateless
public class RouteMapper {

    public Route parseJsonToRoute(String rawRoute){
        Gson gson = new Gson();

        JsonObject obj = gson.fromJson(rawRoute,JsonObject.class);

        JsonArray jsonWaypoints = obj.getAsJsonObject("response").getAsJsonArray("route").get(0).getAsJsonObject().getAsJsonArray("waypoint");
        JsonObject jsonSummary = obj.getAsJsonObject("response").getAsJsonArray("route").get(0).getAsJsonObject().getAsJsonObject("summary");
        JsonArray jsonSegments = obj.getAsJsonObject("response").getAsJsonArray("route").get(0).getAsJsonObject().getAsJsonArray("leg");

        Route route = new Route();
        for (JsonElement elem: jsonWaypoints) {
            Waypoint waypoint = gson.fromJson(elem, Waypoint.class);
            route.addWaypoint(waypoint);
        }

        ArrayList<Float> segmentDistances = new ArrayList<>();
        ArrayList<Float> segmentTravelTimes = new ArrayList<>();
        for (JsonElement elem: jsonSegments) {
            segmentDistances.add(elem.getAsJsonObject().get("length").getAsFloat()/1000); 
            segmentTravelTimes.add(elem.getAsJsonObject().get("travelTime").getAsFloat()/3600);
        }
        route.setRouteSummary(gson.fromJson(jsonSummary, RouteSummary.class));
        route.getRouteSummary().setDistance(route.getRouteSummary().getDistance()/1000);
        route.getRouteSummary().setTravelTime(route.getRouteSummary().getTravelTime()/3600);
        route.populateSegments(segmentDistances, segmentTravelTimes);

        return route;
    }

    public RouteDTO buildRouteDTO(Route route){
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setDistance(route.getRouteSummary().getDistance());
        routeDTO.setTime(route.getRouteSummary().getTravelTime());
        routeDTO.setSegments(route.getSegments());
        return routeDTO;
    }


}
