
export class Position {

  latitude: Number;
  longitude: Number;
  name: String;

  public copyPosition(position: Position): void {
    this.name = position.name;
    this.latitude = position.latitude;
    this.longitude = position.longitude;
  }

}
