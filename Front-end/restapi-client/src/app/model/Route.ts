import { Weather } from './Weather';
import { Segment } from './Segment';


export class Route {

  distance: Number;
  time: Number;
  segments: Segment[];
  weatheratdest: Weather;

}
