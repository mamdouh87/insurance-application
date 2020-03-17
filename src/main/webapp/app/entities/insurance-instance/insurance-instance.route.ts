import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInsuranceInstance, InsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InsuranceInstanceService } from './insurance-instance.service';
import { InsuranceInstanceComponent } from './insurance-instance.component';
import { InsuranceInstanceDetailComponent } from './insurance-instance-detail.component';
import { InsuranceInstanceUpdateComponent } from './insurance-instance-update.component';

@Injectable({ providedIn: 'root' })
export class InsuranceInstanceResolve implements Resolve<IInsuranceInstance> {
  constructor(private service: InsuranceInstanceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInsuranceInstance> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((insuranceInstance: HttpResponse<InsuranceInstance>) => {
          if (insuranceInstance.body) {
            return of(insuranceInstance.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InsuranceInstance());
  }
}

export const insuranceInstanceRoute: Routes = [
  {
    path: '',
    component: InsuranceInstanceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'insuranceApplicationApp.insuranceInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InsuranceInstanceDetailComponent,
    resolve: {
      insuranceInstance: InsuranceInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InsuranceInstanceUpdateComponent,
    resolve: {
      insuranceInstance: InsuranceInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InsuranceInstanceUpdateComponent,
    resolve: {
      insuranceInstance: InsuranceInstanceResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'insuranceApplicationApp.insuranceInstance.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
