import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceObjectDetailComponent } from 'app/entities/insurance-object/insurance-object-detail.component';
import { InsuranceObject } from 'app/shared/model/insurance-object.model';

describe('Component Tests', () => {
  describe('InsuranceObject Management Detail Component', () => {
    let comp: InsuranceObjectDetailComponent;
    let fixture: ComponentFixture<InsuranceObjectDetailComponent>;
    const route = ({ data: of({ insuranceObject: new InsuranceObject(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceObjectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InsuranceObjectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceObjectDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load insuranceObject on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.insuranceObject).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
