import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { HousingLocationInfo } from '../housinglocation';
import { ActivatedRoute } from '@angular/router';
import { Housing } from '../housing';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-details',
  imports: [ReactiveFormsModule],
  templateUrl: './details.html',
  styleUrl: './details.css',
})
export class Details {
  private readonly changeDetectorRef = inject(ChangeDetectorRef);
  route = inject(ActivatedRoute);
  housingService = inject(Housing);

  housingLocation?: HousingLocationInfo;

  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl(''),
  });

  constructor() {
    const housingLocationId = Number(this.route.snapshot.params['id']);
     this.housingService.getHousingLocationById(housingLocationId).then((housingLocation) => {
      this.housingLocation = housingLocation;
      this.changeDetectorRef.markForCheck();
    });
  }

  submitApplication() {
    this.housingService.submitApplication(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? '',
    );
  }
}
