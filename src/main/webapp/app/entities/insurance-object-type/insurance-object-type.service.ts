import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';

type EntityResponseType = HttpResponse<IInsuranceObjectType>;
type EntityArrayResponseType = HttpResponse<IInsuranceObjectType[]>;

@Injectable({ providedIn: 'root' })
export class InsuranceObjectTypeService {
  public resourceUrl = SERVER_API_URL + 'api/insurance-object-types';

  constructor(protected http: HttpClient) {}

  create(insuranceObjectType: IInsuranceObjectType): Observable<EntityResponseType> {
    return this.http.post<IInsuranceObjectType>(this.resourceUrl, insuranceObjectType, { observe: 'response' });
  }

  update(insuranceObjectType: IInsuranceObjectType): Observable<EntityResponseType> {
    return this.http.put<IInsuranceObjectType>(this.resourceUrl, insuranceObjectType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInsuranceObjectType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInsuranceObjectType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
