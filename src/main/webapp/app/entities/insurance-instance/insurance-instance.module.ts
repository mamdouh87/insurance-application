import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceInstanceComponent } from './insurance-instance.component';
import { InsuranceInstanceDetailComponent } from './insurance-instance-detail.component';
import { InsuranceInstanceUpdateComponent } from './insurance-instance-update.component';
import { InsuranceInstanceDeleteDialogComponent } from './insurance-instance-delete-dialog.component';
import { insuranceInstanceRoute } from './insurance-instance.route';

@NgModule({
  imports: [InsuranceApplicationSharedModule, RouterModule.forChild(insuranceInstanceRoute)],
  declarations: [
    InsuranceInstanceComponent,
    InsuranceInstanceDetailComponent,
    InsuranceInstanceUpdateComponent,
    InsuranceInstanceDeleteDialogComponent
  ],
  entryComponents: [InsuranceInstanceDeleteDialogComponent]
})
export class InsuranceApplicationInsuranceInstanceModule {}
