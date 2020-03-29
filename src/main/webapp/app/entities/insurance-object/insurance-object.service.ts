import { Injectable } from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInsuranceObject } from 'app/shared/model/insurance-object.model';

type EntityResponseType = HttpResponse<IInsuranceObject>;
type EntityArrayResponseType = HttpResponse<IInsuranceObject[]>;

@Injectable({ providedIn: 'root' })
export class InsuranceObjectService {
  public resourceUrl = SERVER_API_URL + 'api/insurance-objects';

  constructor(protected http: HttpClient) {}

  create(insuranceObject: IInsuranceObject): Observable<EntityResponseType> {
    return this.http.post<IInsuranceObject>(this.resourceUrl, insuranceObject, { observe: 'response' });
  }

  update(insuranceObject: IInsuranceObject): Observable<EntityResponseType> {
    return this.http.put<IInsuranceObject>(this.resourceUrl, insuranceObject, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInsuranceObject>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInsuranceObject[]>(this.resourceUrl, { params: options, observe: 'response' });
  }
  findByIdentification(id1?: any,id2?: any): Observable<EntityResponseType> {
    const options: HttpParams = new HttpParams();
    options.append("id1",id1);
    options.append("id2",id2);
    return this.http.get<IInsuranceObject>(`${this.resourceUrl}/byIdentification?id1=${id1}&id2=${id2}`, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
