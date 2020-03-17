import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInsuranceSpecification, InsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from './insurance-specification.service';
import { InsuranceSpecificationComponent } from './insurance-specification.component';
import { InsuranceSpecificationDetailComponent } from './insurance-specification-detail.component';
import { InsuranceSpecificationUpdateComponent } from './insurance-specification-update.component';

@Injectable({ providedIn: 'root' })
export class InsuranceSpecificationResolve implements Resolve<IInsuranceSpecification> {
  constructor(private service: InsuranceSpecificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInsuranceSpecification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((insuranceSpecification: HttpResponse<InsuranceSpecification>) => {
          if (insuranceSpecification.body) {
            return of(insuranceSpecification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InsuranceSpecification());
  }
}

export const insuranceSpecificationRoute: Routes = [
  {
    path: '',
    component: InsuranceSpecificationComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'insuranceApplicationApp.insuranceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InsuranceSpecificationDetailComponent,
    resolve: {
      insuranceSpecification: InsuranceSpecificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InsuranceSpecificationUpdateComponent,
    resolve: {
      insuranceSpecification: InsuranceSpecificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InsuranceSpecificationUpdateComponent,
    resolve: {
      insuranceSpecification: InsuranceSpecificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceSpecification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
