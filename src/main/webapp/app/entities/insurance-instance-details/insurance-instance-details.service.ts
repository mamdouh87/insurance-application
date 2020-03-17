import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

type EntityResponseType = HttpResponse<IInsuranceInstanceDetails>;
type EntityArrayResponseType = HttpResponse<IInsuranceInstanceDetails[]>;

@Injectable({ providedIn: 'root' })
export class InsuranceInstanceDetailsService {
  public resourceUrl = SERVER_API_URL + 'api/insurance-instance-details';

  constructor(protected http: HttpClient) {}

  create(insuranceInstanceDetails: IInsuranceInstanceDetails): Observable<EntityResponseType> {
    return this.http.post<IInsuranceInstanceDetails>(this.resourceUrl, insuranceInstanceDetails, { observe: 'response' });
  }

  update(insuranceInstanceDetails: IInsuranceInstanceDetails): Observable<EntityResponseType> {
    return this.http.put<IInsuranceInstanceDetails>(this.resourceUrl, insuranceInstanceDetails, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInsuranceInstanceDetails>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInsuranceInstanceDetails[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
