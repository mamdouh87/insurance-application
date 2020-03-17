import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';

type EntityResponseType = HttpResponse<IInsuranceSpecification>;
type EntityArrayResponseType = HttpResponse<IInsuranceSpecification[]>;

@Injectable({ providedIn: 'root' })
export class InsuranceSpecificationService {
  public resourceUrl = SERVER_API_URL + 'api/insurance-specifications';

  constructor(protected http: HttpClient) {}

  create(insuranceSpecification: IInsuranceSpecification): Observable<EntityResponseType> {
    return this.http.post<IInsuranceSpecification>(this.resourceUrl, insuranceSpecification, { observe: 'response' });
  }

  update(insuranceSpecification: IInsuranceSpecification): Observable<EntityResponseType> {
    return this.http.put<IInsuranceSpecification>(this.resourceUrl, insuranceSpecification, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IInsuranceSpecification>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IInsuranceSpecification[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
