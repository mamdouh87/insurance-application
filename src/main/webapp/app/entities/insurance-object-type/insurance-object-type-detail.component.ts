import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';

@Component({
  selector: 'jhi-insurance-object-type-detail',
  templateUrl: './insurance-object-type-detail.component.html'
})
export class InsuranceObjectTypeDetailComponent implements OnInit {
  insuranceObjectType: IInsuranceObjectType | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceObjectType }) => (this.insuranceObjectType = insuranceObjectType));
  }

  previousState(): void {
    window.history.back();
  }
}
