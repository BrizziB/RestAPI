package brizzi.controllers;


import brizzi.beans.HereAPI;
import brizzi.mappers.PositionMapper;
import brizzi.mappers.RouteMapper;
import brizzi.mappers.WeatherMapper;
import brizzi.model.DTO.RouteDTO;
import brizzi.model.Position;
import brizzi.model.Route;
import brizzi.model.Segment;
import brizzi.model.Weather;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class RouteServiceController {

    @Inject
    HereAPI hereAPI;

    @Inject
    PositionMapper positionMapper;

    @Inject
    RouteMapper routeMapper;

    @Inject
    WeatherMapper weatherMapper;

    public RouteDTO calculateRoute(List<String> waypoints){

        String rawRoute = getRawRouteFromAPI(waypoints);

        Route route = routeMapper.parseJsonToRoute(rawRoute);

        String rawWeather = getRawWeatherFromAPI(route.getEndingPosition());

        Weather weather = weatherMapper.parseJsonToWeather(rawWeather);

        RouteDTO routeDTO = routeMapper.buildRouteDTO(route);
        routeDTO.setWeatheratdest(weather); // non ho prodotto anche un WeatherDTO, alla fine ho preferito lasciare basilare la versione del model

        return routeDTO;
    }

    private String getRawWeatherFromAPI(Position position){
        return hereAPI.getWeatherAtPosition(position);
    }

    private String getRawRouteFromAPI(List<String> waypoints){
        List<Position> waypointsPosition = new ArrayList<>();

        for (String rawWaypoint : waypoints) {
            Position pos = positionMapper.parseStringToPosition(rawWaypoint);
            if(pos != null) waypointsPosition.add( pos );
        }
        return hereAPI.getRoute(waypointsPosition);
    }



}
