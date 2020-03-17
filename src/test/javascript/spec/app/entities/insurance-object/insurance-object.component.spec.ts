import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceObjectComponent } from 'app/entities/insurance-object/insurance-object.component';
import { InsuranceObjectService } from 'app/entities/insurance-object/insurance-object.service';
import { InsuranceObject } from 'app/shared/model/insurance-object.model';

describe('Component Tests', () => {
  describe('InsuranceObject Management Component', () => {
    let comp: InsuranceObjectComponent;
    let fixture: ComponentFixture<InsuranceObjectComponent>;
    let service: InsuranceObjectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceObjectComponent]
      })
        .overrideTemplate(InsuranceObjectComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceObjectComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceObjectService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InsuranceObject(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.insuranceObjects && comp.insuranceObjects[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
