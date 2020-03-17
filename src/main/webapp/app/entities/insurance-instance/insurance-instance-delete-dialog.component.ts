import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InsuranceInstanceService } from './insurance-instance.service';

@Component({
  templateUrl: './insurance-instance-delete-dialog.component.html'
})
export class InsuranceInstanceDeleteDialogComponent {
  insuranceInstance?: IInsuranceInstance;

  constructor(
    protected insuranceInstanceService: InsuranceInstanceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.insuranceInstanceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('insuranceInstanceListModification');
      this.activeModal.close();
    });
  }
}
