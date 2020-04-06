import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { InsuranceObjectTypeService } from 'app/entities/insurance-object-type/insurance-object-type.service';
import { IInsuranceObjectType, InsuranceObjectType } from 'app/shared/model/insurance-object-type.model';

describe('Service Tests', () => {
  describe('InsuranceObjectType Service', () => {
    let injector: TestBed;
    let service: InsuranceObjectTypeService;
    let httpMock: HttpTestingController;
    let elemDefault: IInsuranceObjectType;
    let expectedResult: IInsuranceObjectType | IInsuranceObjectType[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InsuranceObjectTypeService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new InsuranceObjectType(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InsuranceObjectType', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new InsuranceObjectType()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InsuranceObjectType', () => {
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

      it('should return a list of InsuranceObjectType', () => {
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

      it('should delete a InsuranceObjectType', () => {
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
