import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceSpecificationDetailComponent } from 'app/entities/insurance-specification/insurance-specification-detail.component';
import { InsuranceSpecification } from 'app/shared/model/insurance-specification.model';

describe('Component Tests', () => {
  describe('InsuranceSpecification Management Detail Component', () => {
    let comp: InsuranceSpecificationDetailComponent;
    let fixture: ComponentFixture<InsuranceSpecificationDetailComponent>;
    const route = ({ data: of({ insuranceSpecification: new InsuranceSpecification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceSpecificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InsuranceSpecificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceSpecificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load insuranceSpecification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.insuranceSpecification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
