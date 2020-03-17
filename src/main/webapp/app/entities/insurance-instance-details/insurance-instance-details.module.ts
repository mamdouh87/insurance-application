import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceInstanceDetailsComponent } from './insurance-instance-details.component';
import { InsuranceInstanceDetailsDetailComponent } from './insurance-instance-details-detail.component';
import { InsuranceInstanceDetailsUpdateComponent } from './insurance-instance-details-update.component';
import { InsuranceInstanceDetailsDeleteDialogComponent } from './insurance-instance-details-delete-dialog.component';
import { insuranceInstanceDetailsRoute } from './insurance-instance-details.route';

@NgModule({
  imports: [InsuranceApplicationSharedModule, RouterModule.forChild(insuranceInstanceDetailsRoute)],
  declarations: [
    InsuranceInstanceDetailsComponent,
    InsuranceInstanceDetailsDetailComponent,
    InsuranceInstanceDetailsUpdateComponent,
    InsuranceInstanceDetailsDeleteDialogComponent
  ],
  entryComponents: [InsuranceInstanceDetailsDeleteDialogComponent]
})
export class InsuranceApplicationInsuranceInstanceDetailsModule {}
