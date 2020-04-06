import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInsuranceSpecification, InsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from './insurance-specification.service';
import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';
import { InsuranceObjectTypeService } from 'app/entities/insurance-object-type/insurance-object-type.service';

@Component({
  selector: 'jhi-insurance-specification-update',
  templateUrl: './insurance-specification-update.component.html'
})
export class InsuranceSpecificationUpdateComponent implements OnInit {
  isSaving = false;
  insuranceobjecttypes: IInsuranceObjectType[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    descriptionAr: [],
    descriptionEn: [],
    insurenceObjectTypeId: []
  });

  constructor(
    protected insuranceSpecificationService: InsuranceSpecificationService,
    protected insuranceObjectTypeService: InsuranceObjectTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceSpecification }) => {
      this.updateForm(insuranceSpecification);

      this.insuranceObjectTypeService
        .query()
        .subscribe((res: HttpResponse<IInsuranceObjectType[]>) => (this.insuranceobjecttypes = res.body || []));
    });
  }

  updateForm(insuranceSpecification: IInsuranceSpecification): void {
    this.editForm.patchValue({
      id: insuranceSpecification.id,
      code: insuranceSpecification.code,
      descriptionAr: insuranceSpecification.descriptionAr,
      descriptionEn: insuranceSpecification.descriptionEn,
      insurenceObjectTypeId: insuranceSpecification.insurenceObjectTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const insuranceSpecification = this.createFromForm();
    if (insuranceSpecification.id !== undefined) {
      this.subscribeToSaveResponse(this.insuranceSpecificationService.update(insuranceSpecification));
    } else {
      this.subscribeToSaveResponse(this.insuranceSpecificationService.create(insuranceSpecification));
    }
  }

  private createFromForm(): IInsuranceSpecification {
    return {
      ...new InsuranceSpecification(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      descriptionAr: this.editForm.get(['descriptionAr'])!.value,
      descriptionEn: this.editForm.get(['descriptionEn'])!.value,
      insurenceObjectTypeId: this.editForm.get(['insurenceObjectTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsuranceSpecification>>): void {
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

  trackById(index: number, item: IInsuranceObjectType): any {
    return item.id;
  }
}
