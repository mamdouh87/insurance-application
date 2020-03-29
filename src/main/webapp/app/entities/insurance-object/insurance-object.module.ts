import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { InsuranceApplicationSharedModule } from 'app/shared/shared.module';
import { InsuranceObjectComponent } from './insurance-object.component';
import { InsuranceObjectDetailComponent } from './insurance-object-detail.component';
import { InsuranceObjectUpdateComponent } from './insurance-object-update.component';
import { InsuranceObjectDeleteDialogComponent } from './insurance-object-delete-dialog.component';
import { insuranceObjectRoute } from './insurance-object.route';
import { QRCodeModule } from 'angularx-qrcode';
import { ZXingScannerModule } from '@zxing/ngx-scanner';
import {NgxImageCompressService} from 'ngx-image-compress';
import { InquireInsuranceObjectComponent } from './inquire-insurance-object/inquire-insurance-object.component';

@NgModule({
  imports: [InsuranceApplicationSharedModule,QRCodeModule,ZXingScannerModule,RouterModule.forChild(insuranceObjectRoute)],
  declarations: [
    InsuranceObjectComponent,
    InsuranceObjectDetailComponent,
    InsuranceObjectUpdateComponent,
    InsuranceObjectDeleteDialogComponent,
    InquireInsuranceObjectComponent
  ],
  entryComponents: [InsuranceObjectDeleteDialogComponent],
  providers :[NgxImageCompressService]
})
export class InsuranceApplicationInsuranceObjectModule {}
