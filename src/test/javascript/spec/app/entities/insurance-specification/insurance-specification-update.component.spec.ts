import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceSpecificationUpdateComponent } from 'app/entities/insurance-specification/insurance-specification-update.component';
import { InsuranceSpecificationService } from 'app/entities/insurance-specification/insurance-specification.service';
import { InsuranceSpecification } from 'app/shared/model/insurance-specification.model';

describe('Component Tests', () => {
  describe('InsuranceSpecification Management Update Component', () => {
    let comp: InsuranceSpecificationUpdateComponent;
    let fixture: ComponentFixture<InsuranceSpecificationUpdateComponent>;
    let service: InsuranceSpecificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceSpecificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InsuranceSpecificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceSpecificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceSpecificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InsuranceSpecification(123);
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
        const entity = new InsuranceSpecification();
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
