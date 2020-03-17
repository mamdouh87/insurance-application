import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InsuranceSpecificationService } from 'app/entities/insurance-specification/insurance-specification.service';
import { IInsuranceSpecification, InsuranceSpecification } from 'app/shared/model/insurance-specification.model';

describe('Service Tests', () => {
  describe('InsuranceSpecification Service', () => {
    let injector: TestBed;
    let service: InsuranceSpecificationService;
    let httpMock: HttpTestingController;
    let elemDefault: IInsuranceSpecification;
    let expectedResult: IInsuranceSpecification | IInsuranceSpecification[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InsuranceSpecificationService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InsuranceSpecification(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InsuranceSpecification', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new InsuranceSpecification()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InsuranceSpecification', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            descriptionAr: 'BBBBBB',
            descriptionEn: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InsuranceSpecification', () => {
        const returnedFromService = Object.assign(
          {
            code: 'BBBBBB',
            descriptionAr: 'BBBBBB',
            descriptionEn: 'BBBBBB'
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

      it('should delete a InsuranceSpecification', () => {
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
