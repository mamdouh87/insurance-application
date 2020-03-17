import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInsuranceInstance, InsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InsuranceInstanceService } from './insurance-instance.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IInsuranceObject } from 'app/shared/model/insurance-object.model';
import { InsuranceObjectService } from 'app/entities/insurance-object/insurance-object.service';

type SelectableEntity = IUser | IInsuranceObject;

@Component({
  selector: 'jhi-insurance-instance-update',
  templateUrl: './insurance-instance-update.component.html'
})
export class InsuranceInstanceUpdateComponent implements OnInit {
  isSaving = false;
  users: IUser[] = [];
  insuranceobjects: IInsuranceObject[] = [];

  editForm = this.fb.group({
    id: [],
    instanceDate: [],
    user: [],
    insuranceObject: []
  });

  constructor(
    protected insuranceInstanceService: InsuranceInstanceService,
    protected userService: UserService,
    protected insuranceObjectService: InsuranceObjectService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ insuranceInstance }) => {
      if (!insuranceInstance.id) {
        const today = moment().startOf('day');
        insuranceInstance.instanceDate = today;
      }

      this.updateForm(insuranceInstance);

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.insuranceObjectService.query().subscribe((res: HttpResponse<IInsuranceObject[]>) => (this.insuranceobjects = res.body || []));
    });
  }

  updateForm(insuranceInstance: IInsuranceInstance): void {
    this.editForm.patchValue({
      id: insuranceInstance.id,
      instanceDate: insuranceInstance.instanceDate ? insuranceInstance.instanceDate.format(DATE_TIME_FORMAT) : null,
      user: insuranceInstance.user,
      insuranceObject: insuranceInstance.insuranceObject
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const insuranceInstance = this.createFromForm();
    if (insuranceInstance.id !== undefined) {
      this.subscribeToSaveResponse(this.insuranceInstanceService.update(insuranceInstance));
    } else {
      this.subscribeToSaveResponse(this.insuranceInstanceService.create(insuranceInstance));
    }
  }

  private createFromForm(): IInsuranceInstance {
    return {
      ...new InsuranceInstance(),
      id: this.editForm.get(['id'])!.value,
      instanceDate: this.editForm.get(['instanceDate'])!.value
        ? moment(this.editForm.get(['instanceDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      user: this.editForm.get(['user'])!.value,
      insuranceObject: this.editForm.get(['insuranceObject'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInsuranceInstance>>): void {
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
