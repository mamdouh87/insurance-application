import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceDetailComponent } from 'app/entities/insurance-instance/insurance-instance-detail.component';
import { InsuranceInstance } from 'app/shared/model/insurance-instance.model';

describe('Component Tests', () => {
  describe('InsuranceInstance Management Detail Component', () => {
    let comp: InsuranceInstanceDetailComponent;
    let fixture: ComponentFixture<InsuranceInstanceDetailComponent>;
    const route = ({ data: of({ insuranceInstance: new InsuranceInstance(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InsuranceInstanceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceInstanceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load insuranceInstance on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.insuranceInstance).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
