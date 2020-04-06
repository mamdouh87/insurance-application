import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceObjectTypeComponent } from './insurance-object-type.component';
import { InsuranceObjectTypeDetailComponent } from './insurance-object-type-detail.component';
import { InsuranceObjectTypeUpdateComponent } from './insurance-object-type-update.component';
import { InsuranceObjectTypeDeleteDialogComponent } from './insurance-object-type-delete-dialog.component';
import { insuranceObjectTypeRoute } from './insurance-object-type.route';

@NgModule({
  imports: [InsuranceApplicationSharedModule, RouterModule.forChild(insuranceObjectTypeRoute)],
  declarations: [
    InsuranceObjectTypeComponent,
    InsuranceObjectTypeDetailComponent,
    InsuranceObjectTypeUpdateComponent,
    InsuranceObjectTypeDeleteDialogComponent
  ],
  entryComponents: [InsuranceObjectTypeDeleteDialogComponent]
})
export class InsuranceApplicationInsuranceObjectTypeModule {}
