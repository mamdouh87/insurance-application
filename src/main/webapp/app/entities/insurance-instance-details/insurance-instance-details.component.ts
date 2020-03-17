import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { InsuranceInstanceDetailsService } from './insurance-instance-details.service';
import { InsuranceInstanceDetailsDeleteDialogComponent } from './insurance-instance-details-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-instance-details',
  templateUrl: './insurance-instance-details.component.html'
})
export class InsuranceInstanceDetailsComponent implements OnInit, OnDestroy {
  insuranceInstanceDetails?: IInsuranceInstanceDetails[];
  eventSubscriber?: Subscription;

  constructor(
    protected insuranceInstanceDetailsService: InsuranceInstanceDetailsService,
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.insuranceInstanceDetailsService
      .query()
      .subscribe((res: HttpResponse<IInsuranceInstanceDetails[]>) => (this.insuranceInstanceDetails = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInsuranceInstanceDetails();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceInstanceDetails): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    return this.dataUtils.openFile(contentType, base64String);
  }

  registerChangeInInsuranceInstanceDetails(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceInstanceDetailsListModification', () => this.loadAll());
  }

  delete(insuranceInstanceDetails: IInsuranceInstanceDetails): void {
    const modalRef = this.modalService.open(InsuranceInstanceDetailsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceInstanceDetails = insuranceInstanceDetails;
  }
}
