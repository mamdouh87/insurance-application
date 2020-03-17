import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceObjectComponent } from './insurance-object.component';
import { InsuranceObjectDetailComponent } from './insurance-object-detail.component';
import { InsuranceObjectUpdateComponent } from './insurance-object-update.component';
import { InsuranceObjectDeleteDialogComponent } from './insurance-object-delete-dialog.component';
import { insuranceObjectRoute } from './insurance-object.route';

@NgModule({
  imports: [InsuranceApplicationSharedModule, RouterModule.forChild(insuranceObjectRoute)],
  declarations: [
    InsuranceObjectComponent,
    InsuranceObjectDetailComponent,
    InsuranceObjectUpdateComponent,
    InsuranceObjectDeleteDialogComponent
  ],
  entryComponents: [InsuranceObjectDeleteDialogComponent]
})
export class InsuranceApplicationInsuranceObjectModule {}
