import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceObjectTypeDetailComponent } from 'app/entities/insurance-object-type/insurance-object-type-detail.component';
import { InsuranceObjectType } from 'app/shared/model/insurance-object-type.model';

describe('Component Tests', () => {
  describe('InsuranceObjectType Management Detail Component', () => {
    let comp: InsuranceObjectTypeDetailComponent;
    let fixture: ComponentFixture<InsuranceObjectTypeDetailComponent>;
    const route = ({ data: of({ insuranceObjectType: new InsuranceObjectType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceObjectTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InsuranceObjectTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceObjectTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load insuranceObjectType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.insuranceObjectType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
