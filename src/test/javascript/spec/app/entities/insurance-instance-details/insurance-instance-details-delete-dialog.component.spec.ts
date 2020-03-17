import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { InsuranceApplicationTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { InsuranceInstanceDetailsDeleteDialogComponent } from 'app/entities/insurance-instance-details/insurance-instance-details-delete-dialog.component';
import { InsuranceInstanceDetailsService } from 'app/entities/insurance-instance-details/insurance-instance-details.service';

describe('Component Tests', () => {
  describe('InsuranceInstanceDetails Management Delete Component', () => {
    let comp: InsuranceInstanceDetailsDeleteDialogComponent;
    let fixture: ComponentFixture<InsuranceInstanceDetailsDeleteDialogComponent>;
    let service: InsuranceInstanceDetailsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [InsuranceApplicationTestModule],
        declarations: [InsuranceInstanceDetailsDeleteDialogComponent]
      })
        .overrideTemplate(InsuranceInstanceDetailsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InsuranceInstanceDetailsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InsuranceInstanceDetailsService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
