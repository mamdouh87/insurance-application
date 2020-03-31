import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';
import { InsuranceObjectTypeService } from './insurance-object-type.service';

@Component({
  templateUrl: './insurance-object-type-delete-dialog.component.html'
})
export class InsuranceObjectTypeDeleteDialogComponent {
  insuranceObjectType?: IInsuranceObjectType;

  constructor(
    protected insuranceObjectTypeService: InsuranceObjectTypeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.insuranceObjectTypeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('insuranceObjectTypeListModification');
      this.activeModal.close();
    });
  }
}
