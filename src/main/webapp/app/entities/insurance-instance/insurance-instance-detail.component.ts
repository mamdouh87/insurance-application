import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';

@Component({
  selector: 'jhi-insurance-instance-detail',
  templateUrl: './insurance-instance-detail.component.html'
})
export class InsuranceInstanceDetailComponent implements OnInit {
  insuranceInstance: IInsuranceInstance | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceInstance }) => (this.insuranceInstance = insuranceInstance));
  }

  previousState(): void {
    window.history.back();
  }
}
