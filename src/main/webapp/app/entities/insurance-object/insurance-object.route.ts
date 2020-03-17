import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInsuranceObject, InsuranceObject } from 'app/shared/model/insurance-object.model';
import { InsuranceObjectService } from './insurance-object.service';
import { InsuranceObjectComponent } from './insurance-object.component';
import { InsuranceObjectDetailComponent } from './insurance-object-detail.component';
import { InsuranceObjectUpdateComponent } from './insurance-object-update.component';

@Injectable({ providedIn: 'root' })
export class InsuranceObjectResolve implements Resolve<IInsuranceObject> {
  constructor(private service: InsuranceObjectService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInsuranceObject> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((insuranceObject: HttpResponse<InsuranceObject>) => {
          if (insuranceObject.body) {
            return of(insuranceObject.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InsuranceObject());
  }
}

export const insuranceObjectRoute: Routes = [
  {
    path: '',
    component: InsuranceObjectComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'insuranceApplicationApp.insuranceObject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InsuranceObjectDetailComponent,
    resolve: {
      insuranceObject: InsuranceObjectResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InsuranceObjectUpdateComponent,
    resolve: {
      insuranceObject: InsuranceObjectResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObject.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InsuranceObjectUpdateComponent,
    resolve: {
      insuranceObject: InsuranceObjectResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceObject.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
