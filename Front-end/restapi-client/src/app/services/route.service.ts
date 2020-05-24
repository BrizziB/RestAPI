import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { Route } from '../model/Route';


@Injectable({
  providedIn: 'root'
})

export class RouteService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  public userID: Number;

  constructor(
    private http: HttpClient,
    ) {}

  getRoute(): Observable<HttpResponse<Route>> {
    const url = 'http://localhost:8080/RestAPI_prova/routes/getRoute?' +
                'waypoint=40.7480-73.9862&waypoint=40.7500-73.9933&waypoint=40.7558-73.9869&waypoint=40.7858-72.9869';

    const req = this.http.get<Route>(
      url, {observe: 'response', headers: this.httpOptions.headers }
    );
    return req;
  }



}
