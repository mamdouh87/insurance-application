import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InsuranceInstanceService } from './insurance-instance.service';
import { InsuranceInstanceDeleteDialogComponent } from './insurance-instance-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-instance',
  templateUrl: './insurance-instance.component.html'
})
export class InsuranceInstanceComponent implements OnInit, OnDestroy {
  insuranceInstances?: IInsuranceInstance[];
  eventSubscriber?: Subscription;

  constructor(
    protected insuranceInstanceService: InsuranceInstanceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.insuranceInstanceService
      .query()
      .subscribe((res: HttpResponse<IInsuranceInstance[]>) => (this.insuranceInstances = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInsuranceInstances();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceInstance): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInsuranceInstances(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceInstanceListModification', () => this.loadAll());
  }

  delete(insuranceInstance: IInsuranceInstance): void {
    const modalRef = this.modalService.open(InsuranceInstanceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceInstance = insuranceInstance;
  }
}
