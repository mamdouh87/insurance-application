import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from './insurance-specification.service';

@Component({
  templateUrl: './insurance-specification-delete-dialog.component.html'
})
export class InsuranceSpecificationDeleteDialogComponent {
  insuranceSpecification?: IInsuranceSpecification;

  constructor(
    protected insuranceSpecificationService: InsuranceSpecificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.insuranceSpecificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('insuranceSpecificationListModification');
      this.activeModal.close();
    });
  }
}
