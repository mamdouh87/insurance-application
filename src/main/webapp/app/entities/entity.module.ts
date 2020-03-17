import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'insurance-object',
        loadChildren: () => import('./insurance-object/insurance-object.module').then(m => m.InsuranceApplicationInsuranceObjectModule)
      },
      {
        path: 'insurance-instance',
        loadChildren: () =>
          import('./insurance-instance/insurance-instance.module').then(m => m.InsuranceApplicationInsuranceInstanceModule)
      },
      {
        path: 'insurance-specification',
        loadChildren: () =>
          import('./insurance-specification/insurance-specification.module').then(m => m.InsuranceApplicationInsuranceSpecificationModule)
      },
      {
        path: 'insurance-instance-details',
        loadChildren: () =>
          import('./insurance-instance-details/insurance-instance-details.module').then(
            m => m.InsuranceApplicationInsuranceInstanceDetailsModule
          )
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class InsuranceApplicationEntityModule {}
