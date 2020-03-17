import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceComponent } from 'app/entities/insurance-instance/insurance-instance.component';
import { InsuranceInstanceService } from 'app/entities/insurance-instance/insurance-instance.service';
import { InsuranceInstance } from 'app/shared/model/insurance-instance.model';

describe('Component Tests', () => {
  describe('InsuranceInstance Management Component', () => {
    let comp: InsuranceInstanceComponent;
    let fixture: ComponentFixture<InsuranceInstanceComponent>;
    let service: InsuranceInstanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceComponent]
      })
        .overrideTemplate(InsuranceInstanceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceInstanceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceInstanceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InsuranceInstance(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.insuranceInstances && comp.insuranceInstances[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
