import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceDetailsUpdateComponent } from 'app/entities/insurance-instance-details/insurance-instance-details-update.component';
import { InsuranceInstanceDetailsService } from 'app/entities/insurance-instance-details/insurance-instance-details.service';
import { InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

describe('Component Tests', () => {
  describe('InsuranceInstanceDetails Management Update Component', () => {
    let comp: InsuranceInstanceDetailsUpdateComponent;
    let fixture: ComponentFixture<InsuranceInstanceDetailsUpdateComponent>;
    let service: InsuranceInstanceDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceDetailsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InsuranceInstanceDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceInstanceDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceInstanceDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InsuranceInstanceDetails(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new InsuranceInstanceDetails();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
