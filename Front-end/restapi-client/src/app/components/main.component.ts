import { RouteService } from '../services/route.service';
import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { Route } from '../model/Route';

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

  ngOnInit() {

  }

  public getRoute(): void {
    this.routeService.getRoute().subscribe(
      (resp) => {
        if (!isNullOrUndefined(resp)) {
          this.route = resp.body;
          this.fetched = true;
        }
      }
    );

  }


}
