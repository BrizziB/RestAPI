import { RouteService } from '../services/route.service';
import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { Route } from '../model/Route';
import { Position } from '../model/Position';
import { CITIES } from '../resource/Cities';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(
    protected routeService: RouteService
  ) { }

  protected route: Route;
  protected fetched = false;
  protected stops: Position[];
  protected start: Position;
  protected end: Position;
  protected cities: Position[];
  protected waiting = false;

  ngOnInit() {
    this.end = new Position;
    this.start = new Position;
    this.stops = [];
    this.cities = CITIES;
  }

  public getRoute(positions: Position[]): void {
    this.waiting = true;
    this.routeService.getRoute(positions, this.waiting).subscribe(
      (resp) => {
        this.waiting = false;
        if (!isNullOrUndefined(resp)) {
          this.route = resp.body;
          this.fetched = true;
        }
      }
    );
  }

  public setStartingCity(city: String): void {
    if (city === 'Custom') { return; }
    const selectedCity = this.cities.find(pos => pos.name === city);
    this.start.copyPosition(selectedCity);
  }
  public setEndingCity(city: String): void {
    if (city === 'Custom') { return; }
    const selectedCity = this.cities.find(pos => pos.name === city);
    this.end.copyPosition(selectedCity);
  }
  public setStopCity(city: String, index: number): void {
    if (city === 'Custom') { return; }
    const selectedCity = this.cities.find(pos => pos.name === city);
    this.stops[index].copyPosition(selectedCity);
  }

  public addStop(): void {
    const pos = new Position;
    pos.name = 'fermata' + ( this.stops.length + 1 );
    this.stops.push(pos);
  }

  public calculateRoute(): void {
    let allPositions: Position[] = [];
    allPositions.push(this.start);
    allPositions = allPositions.concat(this.stops);
    allPositions.push(this.end);
    this.getRoute(allPositions);
  }

  public removeStop(): void {
    this.stops.pop();
  }

  public resetRoute(): void {
    this.fetched = false;
    this.start = new Position;
    this.end = new Position;
    this.stops = [];

  }


}
