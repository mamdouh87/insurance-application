import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceSpecificationComponent } from './insurance-specification.component';
import { InsuranceSpecificationDetailComponent } from './insurance-specification-detail.component';
import { InsuranceSpecificationUpdateComponent } from './insurance-specification-update.component';
import { InsuranceSpecificationDeleteDialogComponent } from './insurance-specification-delete-dialog.component';
import { insuranceSpecificationRoute } from './insurance-specification.route';

@NgModule({
  imports: [InsuranceApplicationSharedModule, RouterModule.forChild(insuranceSpecificationRoute)],
  declarations: [
    InsuranceSpecificationComponent,
    InsuranceSpecificationDetailComponent,
    InsuranceSpecificationUpdateComponent,
    InsuranceSpecificationDeleteDialogComponent
  ],
  entryComponents: [InsuranceSpecificationDeleteDialogComponent]
})
export class InsuranceApplicationInsuranceSpecificationModule {}
