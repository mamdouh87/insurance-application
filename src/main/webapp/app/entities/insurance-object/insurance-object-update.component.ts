import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInsuranceObject, InsuranceObject } from 'app/shared/model/insurance-object.model';
import { InsuranceObjectService } from './insurance-object.service';
import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';
import { InsuranceObjectTypeService } from 'app/entities/insurance-object-type/insurance-object-type.service';

@Component({
  selector: 'jhi-insurance-object-update',
  templateUrl: './insurance-object-update.component.html'
})
export class InsuranceObjectUpdateComponent implements OnInit {
  isSaving = false;
  insuranceobjecttypes: IInsuranceObjectType[] = [];

  editForm = this.fb.group({
    id: [],
    identifier1: [],
    identifier2: [],
    identifier3: [],
    typeId: []
  });

  constructor(
    protected insuranceObjectService: InsuranceObjectService,
    protected insuranceObjectTypeService: InsuranceObjectTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceObject }) => {
      this.updateForm(insuranceObject);

      this.insuranceObjectTypeService
        .query()
        .subscribe((res: HttpResponse<IInsuranceObjectType[]>) => (this.insuranceobjecttypes = res.body || []));
    });
  }

  updateForm(insuranceObject: IInsuranceObject): void {
    this.editForm.patchValue({
      id: insuranceObject.id,
      identifier1: insuranceObject.identifier1,
      identifier2: insuranceObject.identifier2,
      identifier3: insuranceObject.identifier3,
      typeId: insuranceObject.typeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const insuranceObject = this.createFromForm();
    if (insuranceObject.id !== undefined) {
      this.subscribeToSaveResponse(this.insuranceObjectService.update(insuranceObject));
    } else {
      this.subscribeToSaveResponse(this.insuranceObjectService.create(insuranceObject));
    }
  }

  private createFromForm(): IInsuranceObject {
    return {
      ...new InsuranceObject(),
      id: this.editForm.get(['id'])!.value,
      identifier1: this.editForm.get(['identifier1'])!.value,
      identifier2: this.editForm.get(['identifier2'])!.value,
      identifier3: this.editForm.get(['identifier3'])!.value,
      typeId: this.editForm.get(['typeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsuranceObject>>): void {
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
