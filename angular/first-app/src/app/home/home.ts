import { Component, inject } from '@angular/core';
import { HousingLocation } from "../housing-location/housing-location";
import { HousingLocationInfo } from '../housinglocation';
import { Housing } from '../housing';

@Component({
  selector: 'app-home',
  imports: [HousingLocation],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  housingLocationList: HousingLocationInfo[] = [];
  housing: Housing = inject(Housing);

  constructor() {
    this.housingLocationList = this.housing.getAllHousingLocations();
  }
}
