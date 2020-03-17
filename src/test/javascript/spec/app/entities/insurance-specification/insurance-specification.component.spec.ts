import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceSpecificationComponent } from 'app/entities/insurance-specification/insurance-specification.component';
import { InsuranceSpecificationService } from 'app/entities/insurance-specification/insurance-specification.service';
import { InsuranceSpecification } from 'app/shared/model/insurance-specification.model';

describe('Component Tests', () => {
  describe('InsuranceSpecification Management Component', () => {
    let comp: InsuranceSpecificationComponent;
    let fixture: ComponentFixture<InsuranceSpecificationComponent>;
    let service: InsuranceSpecificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceSpecificationComponent]
      })
        .overrideTemplate(InsuranceSpecificationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceSpecificationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceSpecificationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InsuranceSpecification(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.insuranceSpecifications && comp.insuranceSpecifications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
