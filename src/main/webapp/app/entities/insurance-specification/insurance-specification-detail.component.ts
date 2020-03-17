import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';

@Component({
  selector: 'jhi-insurance-specification-detail',
  templateUrl: './insurance-specification-detail.component.html'
})
export class InsuranceSpecificationDetailComponent implements OnInit {
  insuranceSpecification: IInsuranceSpecification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceSpecification }) => (this.insuranceSpecification = insuranceSpecification));
  }

  previousState(): void {
    window.history.back();
  }
}
