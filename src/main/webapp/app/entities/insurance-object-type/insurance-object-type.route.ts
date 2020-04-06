import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInsuranceObjectType, InsuranceObjectType } from 'app/shared/model/insurance-object-type.model';
import { InsuranceObjectTypeService } from './insurance-object-type.service';
import { InsuranceObjectTypeComponent } from './insurance-object-type.component';
import { InsuranceObjectTypeDetailComponent } from './insurance-object-type-detail.component';
import { InsuranceObjectTypeUpdateComponent } from './insurance-object-type-update.component';

@Injectable({ providedIn: 'root' })
export class InsuranceObjectTypeResolve implements Resolve<IInsuranceObjectType> {
  constructor(private service: InsuranceObjectTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInsuranceObjectType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((insuranceObjectType: HttpResponse<InsuranceObjectType>) => {
          if (insuranceObjectType.body) {
            return of(insuranceObjectType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InsuranceObjectType());
  }
}

export const insuranceObjectTypeRoute: Routes = [
  {
    path: '',
    component: InsuranceObjectTypeComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'insuranceApplicationApp.insuranceObjectType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InsuranceObjectTypeDetailComponent,
    resolve: {
      insuranceObjectType: InsuranceObjectTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObjectType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InsuranceObjectTypeUpdateComponent,
    resolve: {
      insuranceObjectType: InsuranceObjectTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObjectType.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InsuranceObjectTypeUpdateComponent,
    resolve: {
      insuranceObjectType: InsuranceObjectTypeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObjectType.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
