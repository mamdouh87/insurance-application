import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceUpdateComponent } from 'app/entities/insurance-instance/insurance-instance-update.component';
import { InsuranceInstanceService } from 'app/entities/insurance-instance/insurance-instance.service';
import { InsuranceInstance } from 'app/shared/model/insurance-instance.model';

describe('Component Tests', () => {
  describe('InsuranceInstance Management Update Component', () => {
    let comp: InsuranceInstanceUpdateComponent;
    let fixture: ComponentFixture<InsuranceInstanceUpdateComponent>;
    let service: InsuranceInstanceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InsuranceInstanceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InsuranceInstanceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceInstanceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InsuranceInstance(123);
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
        const entity = new InsuranceInstance();
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
