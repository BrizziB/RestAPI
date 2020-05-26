package brizzi.services;

import brizzi.controllers.RouteServiceController;
import brizzi.model.DTO.RouteDTO;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("routes")
public class RouteService {

    @Inject
    RouteServiceController routeController;

    @GET
    @Path("getRoute")
    public Response getRoute(@QueryParam("waypoint") List<String> waypointsPositions, @QueryParam("userKey") String key){
        Gson gson = new Gson();
        String jsonPayload = gson.toJson(routeController.calculateRoute(waypointsPositions), RouteDTO.class);
        return Response.ok(jsonPayload).build();
    }

}
