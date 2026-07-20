import { Component, inject } from '@angular/core';
import { HousingLocationInfo } from '../housinglocation';
import { ActivatedRoute } from '@angular/router';
import { Housing } from '../housing';

@Component({
  selector: 'app-details',
  imports: [],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details {
  route = inject(ActivatedRoute);
  housingService = inject(Housing);

  housingLocation?: HousingLocationInfo;

  constructor() {
    const housingLocationId = Number(this.route.snapshot.params['id']);
    this.housingLocation = this.housingService.getHousingLocationById(housingLocationId);
  }
}
