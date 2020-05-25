package brizzi.mappers;


import brizzi.model.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ejb.Stateless;

@Stateless
public class WeatherMapper {

    public Weather parseJsonToWeather(String rawWeather){
        Gson gson = new Gson();

        JsonObject obj = gson.fromJson(rawWeather,JsonObject.class);

        Weather weather = new Weather();
        weather.setDaylight(getDayLight(obj));
        weather.setDescription(getDescription(obj));
        weather.setTemperature(getTemperature(obj));

        return weather;
    }

    private String getDayLight(JsonObject obj){
        if (getObservation(obj).get("daylight").getAsString() =="D") return "day";
        else return "night";
    }
    private String getDescription(JsonObject obj){
        return getObservation(obj).get("description").getAsString();
    }
    private Float getTemperature(JsonObject obj){
        return getObservation(obj).get("temperature").getAsFloat();
    }

    private JsonObject getObservation(JsonObject obj){ //bruttino eh, senz'altro ci saranno modi migliori
        return obj.getAsJsonObject("observations").get("location")
                .getAsJsonArray().get(0).getAsJsonObject().get("observation")
                .getAsJsonArray().get(0).getAsJsonObject();
    }

}
