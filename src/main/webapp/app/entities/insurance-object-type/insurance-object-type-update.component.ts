import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInsuranceObjectType, InsuranceObjectType } from 'app/shared/model/insurance-object-type.model';
import { InsuranceObjectTypeService } from './insurance-object-type.service';
import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { InsuranceSpecificationService } from 'app/entities/insurance-specification/insurance-specification.service';

@Component({
  selector: 'jhi-insurance-object-type-update',
  templateUrl: './insurance-object-type-update.component.html'
})
export class InsuranceObjectTypeUpdateComponent implements OnInit {
  isSaving = false;
  insurancespecifications: IInsuranceSpecification[] = [];

  editForm = this.fb.group({
    id: [],
    code: [],
    descriptionAr: [],
    descriptionEn: [],
    objectTypeId: []
  });

  constructor(
    protected insuranceObjectTypeService: InsuranceObjectTypeService,
    protected insuranceSpecificationService: InsuranceSpecificationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceObjectType }) => {
      this.updateForm(insuranceObjectType);

      this.insuranceSpecificationService
        .query()
        .subscribe((res: HttpResponse<IInsuranceSpecification[]>) => (this.insurancespecifications = res.body || []));
    });
  }

  updateForm(insuranceObjectType: IInsuranceObjectType): void {
    this.editForm.patchValue({
      id: insuranceObjectType.id,
      code: insuranceObjectType.code,
      descriptionAr: insuranceObjectType.descriptionAr,
      descriptionEn: insuranceObjectType.descriptionEn,
      objectTypeId: insuranceObjectType.objectTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const insuranceObjectType = this.createFromForm();
    if (insuranceObjectType.id !== undefined) {
      this.subscribeToSaveResponse(this.insuranceObjectTypeService.update(insuranceObjectType));
    } else {
      this.subscribeToSaveResponse(this.insuranceObjectTypeService.create(insuranceObjectType));
    }
  }

  private createFromForm(): IInsuranceObjectType {
    return {
      ...new InsuranceObjectType(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      descriptionAr: this.editForm.get(['descriptionAr'])!.value,
      descriptionEn: this.editForm.get(['descriptionEn'])!.value,
      objectTypeId: this.editForm.get(['objectTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsuranceObjectType>>): void {
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

  trackById(index: number, item: IInsuranceSpecification): any {
    return item.id;
  }
}
