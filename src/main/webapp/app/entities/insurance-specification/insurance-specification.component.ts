import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from './insurance-specification.service';
import { InsuranceSpecificationDeleteDialogComponent } from './insurance-specification-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-specification',
  templateUrl: './insurance-specification.component.html'
})
export class InsuranceSpecificationComponent implements OnInit, OnDestroy {
  insuranceSpecifications?: IInsuranceSpecification[];
  eventSubscriber?: Subscription;

  constructor(
    protected insuranceSpecificationService: InsuranceSpecificationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.insuranceSpecificationService
      .query()
      .subscribe((res: HttpResponse<IInsuranceSpecification[]>) => (this.insuranceSpecifications = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInsuranceSpecifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceSpecification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInsuranceSpecifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceSpecificationListModification', () => this.loadAll());
  }

  delete(insuranceSpecification: IInsuranceSpecification): void {
    const modalRef = this.modalService.open(InsuranceSpecificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceSpecification = insuranceSpecification;
  }
}
