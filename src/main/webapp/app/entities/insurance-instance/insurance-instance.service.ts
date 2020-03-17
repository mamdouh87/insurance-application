import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';

type EntityResponseType = HttpResponse<IInsuranceInstance>;
type EntityArrayResponseType = HttpResponse<IInsuranceInstance[]>;

@Injectable({ providedIn: 'root' })
export class InsuranceInstanceService {
  public resourceUrl = SERVER_API_URL + 'api/insurance-instances';

  constructor(protected http: HttpClient) {}

  create(insuranceInstance: IInsuranceInstance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(insuranceInstance);
    return this.http
      .post<IInsuranceInstance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(insuranceInstance: IInsuranceInstance): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(insuranceInstance);
    return this.http
      .put<IInsuranceInstance>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInsuranceInstance>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInsuranceInstance[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(insuranceInstance: IInsuranceInstance): IInsuranceInstance {
    const copy: IInsuranceInstance = Object.assign({}, insuranceInstance, {
      instanceDate:
        insuranceInstance.instanceDate && insuranceInstance.instanceDate.isValid() ? insuranceInstance.instanceDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.instanceDate = res.body.instanceDate ? moment(res.body.instanceDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((insuranceInstance: IInsuranceInstance) => {
        insuranceInstance.instanceDate = insuranceInstance.instanceDate ? moment(insuranceInstance.instanceDate) : undefined;
      });
    }
    return res;
  }
}
