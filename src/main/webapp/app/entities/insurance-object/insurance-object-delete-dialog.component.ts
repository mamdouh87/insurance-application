import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsuranceObject } from 'app/shared/model/insurance-object.model';
import { InsuranceObjectService } from './insurance-object.service';

@Component({
  templateUrl: './insurance-object-delete-dialog.component.html'
})
export class InsuranceObjectDeleteDialogComponent {
  insuranceObject?: IInsuranceObject;

  constructor(
    protected insuranceObjectService: InsuranceObjectService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.insuranceObjectService.delete(id).subscribe(() => {
      this.eventManager.broadcast('insuranceObjectListModification');
      this.activeModal.close();
    });
  }
}
