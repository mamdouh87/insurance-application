import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IInsuranceInstanceDetails, InsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { InsuranceInstanceDetailsService } from './insurance-instance-details.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from 'app/entities/insurance-specification/insurance-specification.service';
import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InsuranceInstanceService } from 'app/entities/insurance-instance/insurance-instance.service';

type SelectableEntity = IInsuranceSpecification | IInsuranceInstance;

@Component({
  selector: 'jhi-insurance-instance-details-update',
  templateUrl: './insurance-instance-details-update.component.html'
})
export class InsuranceInstanceDetailsUpdateComponent implements OnInit {
  isSaving = false;
  insurancespecifications: IInsuranceSpecification[] = [];
  insuranceinstances: IInsuranceInstance[] = [];

  editForm = this.fb.group({
    id: [],
    image: [],
    imageContentType: [],
    comments: [],
    status: [],
    specificationId: [],
    insuranceInstanceId: []
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected insuranceInstanceDetailsService: InsuranceInstanceDetailsService,
    protected insuranceSpecificationService: InsuranceSpecificationService,
    protected insuranceInstanceService: InsuranceInstanceService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceInstanceDetails }) => {
      this.updateForm(insuranceInstanceDetails);

      this.insuranceSpecificationService
        .query()
        .subscribe((res: HttpResponse<IInsuranceSpecification[]>) => (this.insurancespecifications = res.body || []));

      this.insuranceInstanceService
        .query()
        .subscribe((res: HttpResponse<IInsuranceInstance[]>) => (this.insuranceinstances = res.body || []));
    });
  }

  updateForm(insuranceInstanceDetails: IInsuranceInstanceDetails): void {
    this.editForm.patchValue({
      id: insuranceInstanceDetails.id,
      image: insuranceInstanceDetails.image,
      imageContentType: insuranceInstanceDetails.imageContentType,
      comments: insuranceInstanceDetails.comments,
      status: insuranceInstanceDetails.status,
      specificationId: insuranceInstanceDetails.specificationId,
      insuranceInstanceId: insuranceInstanceDetails.insuranceInstanceId
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('insuranceApplicationApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });

  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const insuranceInstanceDetails = this.createFromForm();
    if (insuranceInstanceDetails.id !== undefined) {
      this.subscribeToSaveResponse(this.insuranceInstanceDetailsService.update(insuranceInstanceDetails));
    } else {
      this.subscribeToSaveResponse(this.insuranceInstanceDetailsService.create(insuranceInstanceDetails));
    }
  }

  private createFromForm(): IInsuranceInstanceDetails {
    return {
      ...new InsuranceInstanceDetails(),
      id: this.editForm.get(['id'])!.value,
      imageContentType: this.editForm.get(['imageContentType'])!.value,
      image: this.editForm.get(['image'])!.value,
      comments: this.editForm.get(['comments'])!.value,
      status: this.editForm.get(['status'])!.value,
      specificationId: this.editForm.get(['specificationId'])!.value,
      insuranceInstanceId: this.editForm.get(['insuranceInstanceId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsuranceInstanceDetails>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
