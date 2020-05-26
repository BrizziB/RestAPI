package brizzi.mappers;

import brizzi.model.*;
import brizzi.model.DTO.RouteDTO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.math3.util.Precision;

import javax.ejb.Stateless;
import java.util.ArrayList;


@Stateless
public class RouteMapper {

    private Gson gson;

    public Route parseJsonToRoute(String rawRoute){
        gson = new Gson();

        JsonObject obj = gson.fromJson(rawRoute,JsonObject.class);
        obj = getRouteFromJson(obj);

        Route route = new Route();

        JsonArray jsonWaypoints= obj.getAsJsonArray("waypoint");
        addWaypoints(jsonWaypoints, route);

        JsonArray jsonSegments = obj.getAsJsonArray("leg");
        addSegments(jsonSegments, route);

        JsonObject jsonSummary = obj.getAsJsonObject("summary");
        addSummary(jsonSummary, route);

        return route;
    }

    public RouteDTO buildRouteDTO(Route route){
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setDistance(route.getRouteSummary().getDistance());
        routeDTO.setTime(route.getRouteSummary().getTravelTime());
        routeDTO.setSegments(route.getSegments());
        return routeDTO;
    }

    private JsonObject getRouteFromJson(JsonObject obj){
        return obj.getAsJsonObject("response")
                .getAsJsonArray("route")
                .get(0).getAsJsonObject();
    }

    private void addWaypoints(JsonArray jsonWaypoints, Route route){
        for (JsonElement elem: jsonWaypoints) {
            Waypoint waypoint = gson.fromJson(elem, Waypoint.class);
            route.addWaypoint(waypoint);
        }
    }

    private void addSegments(JsonArray jsonSegments, Route route){
        ArrayList<Float> segmentDistances = new ArrayList<>();
        ArrayList<Float> segmentTravelTimes = new ArrayList<>();
        for (JsonElement elem: jsonSegments) {
            segmentDistances.add( Precision.round (
                    elem.getAsJsonObject().get("length").getAsFloat()/1000
                    , 2)); // conversione in metri
            segmentTravelTimes.add( Precision.round (
                    elem.getAsJsonObject().get("travelTime").getAsFloat()/3600
                    ,2)); // conversione in ore
        }
        route.populateSegments(segmentDistances, segmentTravelTimes);
    }

    private void addSummary(JsonObject jsonSummary, Route route){
        route.setRouteSummary(gson.fromJson(jsonSummary, RouteSummary.class));
        route.getRouteSummary().setDistance( Precision.round (
                route.getRouteSummary().getDistance()/1000
                ,2));
        route.getRouteSummary().setTravelTime( Precision.round (
                route.getRouteSummary().getTravelTime()/3600
                ,2));
    }




}
