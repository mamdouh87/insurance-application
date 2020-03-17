import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { InsuranceInstanceDetailsService } from './insurance-instance-details.service';

@Component({
  templateUrl: './insurance-instance-details-delete-dialog.component.html'
})
export class InsuranceInstanceDetailsDeleteDialogComponent {
  insuranceInstanceDetails?: IInsuranceInstanceDetails;

  constructor(
    protected insuranceInstanceDetailsService: InsuranceInstanceDetailsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.insuranceInstanceDetailsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('insuranceInstanceDetailsListModification');
      this.activeModal.close();
    });
  }
}
