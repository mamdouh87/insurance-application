import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceObjectUpdateComponent } from 'app/entities/insurance-object/insurance-object-update.component';
import { InsuranceObjectService } from 'app/entities/insurance-object/insurance-object.service';
import { InsuranceObject } from 'app/shared/model/insurance-object.model';

describe('Component Tests', () => {
  describe('InsuranceObject Management Update Component', () => {
    let comp: InsuranceObjectUpdateComponent;
    let fixture: ComponentFixture<InsuranceObjectUpdateComponent>;
    let service: InsuranceObjectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceObjectUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InsuranceObjectUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceObjectUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceObjectService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InsuranceObject(123);
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
        const entity = new InsuranceObject();
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
