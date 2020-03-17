import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsuranceObject } from 'app/shared/model/insurance-object.model';

@Component({
  selector: 'jhi-insurance-object-detail',
  templateUrl: './insurance-object-detail.component.html'
})
export class InsuranceObjectDetailComponent implements OnInit {
  insuranceObject: IInsuranceObject | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceObject }) => (this.insuranceObject = insuranceObject));
  }

  previousState(): void {
    window.history.back();
  }
}
