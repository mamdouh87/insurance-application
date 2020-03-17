import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceDetailsComponent } from 'app/entities/insurance-instance-details/insurance-instance-details.component';
import { InsuranceInstanceDetailsService } from 'app/entities/insurance-instance-details/insurance-instance-details.service';
import { InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

describe('Component Tests', () => {
  describe('InsuranceInstanceDetails Management Component', () => {
    let comp: InsuranceInstanceDetailsComponent;
    let fixture: ComponentFixture<InsuranceInstanceDetailsComponent>;
    let service: InsuranceInstanceDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceDetailsComponent]
      })
        .overrideTemplate(InsuranceInstanceDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceInstanceDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceInstanceDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InsuranceInstanceDetails(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.insuranceInstanceDetails && comp.insuranceInstanceDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
