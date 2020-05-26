import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { Observable, of, throwError } from 'rxjs';
import { Route } from '../model/Route';
import { Position } from '../model/Position';


@Injectable({
  providedIn: 'root'
})

export class RouteService {
  private httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  private routeBaseUrl = 'http://localhost:8080/RestAPI_prova/routes/getRoute?';

  constructor(
    private http: HttpClient,
    ) {      }

  public getRoute(positions: Position[], waiting: boolean): Observable<HttpResponse<Route>> {
    const url = this.routeBaseUrl + this.buildRouteURL(positions);
    return this.http
    .get<Route>(
      url, {observe: 'response', headers: this.httpOptions.headers })
      .pipe(
        catchError(this.catchError)
        );
  }

  private catchError() {
    alert('Route non definibile');
    return throwError('Route non definibile');
  }

  private buildRouteURL(positions: Position[]): string {
    let url = '';
    positions.forEach(position => {
      url = url + ('waypoint=' + position.latitude + ',' + position.longitude + '&');
    });
    url = url.substring(0, url.length - 1);


    return url;
  }



}
