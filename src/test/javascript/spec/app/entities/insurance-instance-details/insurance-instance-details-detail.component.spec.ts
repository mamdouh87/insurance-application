import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { InsuranceInstanceDetailsDetailComponent } from 'app/entities/insurance-instance-details/insurance-instance-details-detail.component';
import { InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

describe('Component Tests', () => {
  describe('InsuranceInstanceDetails Management Detail Component', () => {
    let comp: InsuranceInstanceDetailsDetailComponent;
    let fixture: ComponentFixture<InsuranceInstanceDetailsDetailComponent>;
    let dataUtils: JhiDataUtils;
    const route = ({ data: of({ insuranceInstanceDetails: new InsuranceInstanceDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(InsuranceInstanceDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceInstanceDetailsDetailComponent);
      comp = fixture.componentInstance;
      dataUtils = fixture.debugElement.injector.get(JhiDataUtils);
    });

    describe('OnInit', () => {
      it('Should load insuranceInstanceDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.insuranceInstanceDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });

    describe('byteSize', () => {
      it('Should call byteSize from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'byteSize');
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.byteSize(fakeBase64);

        // THEN
        expect(dataUtils.byteSize).toBeCalledWith(fakeBase64);
      });
    });

    describe('openFile', () => {
      it('Should call openFile from JhiDataUtils', () => {
        // GIVEN
        spyOn(dataUtils, 'openFile');
        const fakeContentType = 'fake content type';
        const fakeBase64 = 'fake base64';

        // WHEN
        comp.openFile(fakeContentType, fakeBase64);

        // THEN
        expect(dataUtils.openFile).toBeCalledWith(fakeContentType, fakeBase64);
      });
    });
  });
});
