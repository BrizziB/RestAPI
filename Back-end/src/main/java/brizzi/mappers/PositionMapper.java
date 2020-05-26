package brizzi.mappers;

import brizzi.model.Position;

import javax.ejb.Stateless;

@Stateless
public class PositionMapper {

    public Position parseStringToPosition(String rawPosition){
        if (rawPosition == null) return null;
        String[] tmpPos = rawPosition.split(",");
        if(tmpPos[0].equals("undefined") || tmpPos[1].equals("undefined")) return null;
        Float latitude = Float.parseFloat(tmpPos[0]);
        Float longitude = Float.parseFloat(tmpPos[1]);

        Position pos = new Position(latitude, longitude);
        return pos;
    }

}
