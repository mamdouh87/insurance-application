import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

@Component({
  selector: 'jhi-insurance-instance-details-detail',
  templateUrl: './insurance-instance-details-detail.component.html'
})
export class InsuranceInstanceDetailsDetailComponent implements OnInit {
  insuranceInstanceDetails: IInsuranceInstanceDetails | null = null;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceInstanceDetails }) => (this.insuranceInstanceDetails = insuranceInstanceDetails));
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  previousState(): void {
    window.history.back();
  }
}
