package brizzi.mappers;

import brizzi.model.Position;

import javax.ejb.Stateless;

@Stateless
public class PositionMapper {

    public Position parseStringToPosition(String rawPosition){
        String[] tmpPos = rawPosition.split("-");
        Float latitude = Float.parseFloat(tmpPos[0]);
        Float longitude = Float.parseFloat(tmpPos[1]);

        Position pos = new Position(latitude, longitude);
        return pos;
    }

}
