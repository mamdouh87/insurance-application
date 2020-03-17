import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InsuranceInstanceDetailsService } from 'app/entities/insurance-instance-details/insurance-instance-details.service';
import { IInsuranceInstanceDetails, InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { InstanceDetailsStatus } from 'app/shared/model/enumerations/instance-details-status.model';

describe('Service Tests', () => {
  describe('InsuranceInstanceDetails Service', () => {
    let injector: TestBed;
    let service: InsuranceInstanceDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IInsuranceInstanceDetails;
    let expectedResult: IInsuranceInstanceDetails | IInsuranceInstanceDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InsuranceInstanceDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InsuranceInstanceDetails(0, 'image/png', 'AAAAAAA', 'AAAAAAA', InstanceDetailsStatus.Excellent);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InsuranceInstanceDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new InsuranceInstanceDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InsuranceInstanceDetails', () => {
        const returnedFromService = Object.assign(
          {
            image: 'BBBBBB',
            comments: 'BBBBBB',
            status: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InsuranceInstanceDetails', () => {
        const returnedFromService = Object.assign(
          {
            image: 'BBBBBB',
            comments: 'BBBBBB',
            status: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a InsuranceInstanceDetails', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
