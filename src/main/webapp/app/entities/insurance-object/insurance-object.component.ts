import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceObject } from 'app/shared/model/insurance-object.model';
import { InsuranceObjectService } from './insurance-object.service';
import { InsuranceObjectDeleteDialogComponent } from './insurance-object-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-object',
  templateUrl: './insurance-object.component.html'
})
export class InsuranceObjectComponent implements OnInit, OnDestroy {
  insuranceObjects?: IInsuranceObject[];
  eventSubscriber?: Subscription;

  constructor(
    protected insuranceObjectService: InsuranceObjectService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.insuranceObjectService.query().subscribe((res: HttpResponse<IInsuranceObject[]>) => (this.insuranceObjects = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInsuranceObjects();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceObject): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInsuranceObjects(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceObjectListModification', () => this.loadAll());
  }

  delete(insuranceObject: IInsuranceObject): void {
    const modalRef = this.modalService.open(InsuranceObjectDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceObject = insuranceObject;
  }
}
