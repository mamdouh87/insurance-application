import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInsuranceInstanceDetails, InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { InsuranceInstanceDetailsService } from './insurance-instance-details.service';
import { InsuranceInstanceDetailsComponent } from './insurance-instance-details.component';
import { InsuranceInstanceDetailsDetailComponent } from './insurance-instance-details-detail.component';
import { InsuranceInstanceDetailsUpdateComponent } from './insurance-instance-details-update.component';

@Injectable({ providedIn: 'root' })
export class InsuranceInstanceDetailsResolve implements Resolve<IInsuranceInstanceDetails> {
  constructor(private service: InsuranceInstanceDetailsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInsuranceInstanceDetails> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((insuranceInstanceDetails: HttpResponse<InsuranceInstanceDetails>) => {
          if (insuranceInstanceDetails.body) {
            return of(insuranceInstanceDetails.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InsuranceInstanceDetails());
  }
}

export const insuranceInstanceDetailsRoute: Routes = [
  {
    path: '',
    component: InsuranceInstanceDetailsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstanceDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InsuranceInstanceDetailsDetailComponent,
    resolve: {
      insuranceInstanceDetails: InsuranceInstanceDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstanceDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InsuranceInstanceDetailsUpdateComponent,
    resolve: {
      insuranceInstanceDetails: InsuranceInstanceDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstanceDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InsuranceInstanceDetailsUpdateComponent,
    resolve: {
      insuranceInstanceDetails: InsuranceInstanceDetailsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstanceDetails.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
