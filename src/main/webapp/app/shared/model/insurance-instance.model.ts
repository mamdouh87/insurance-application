import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';
import { IInsuranceObject } from 'app/shared/model/insurance-object.model';

export interface IInsuranceInstance {
  id?: number;
  instanceDate?: Moment;
  user?: IUser;
  details?: IInsuranceInstanceDetails[];
  insuranceObject?: IInsuranceObject;
}

export class InsuranceInstance implements IInsuranceInstance {
  constructor(
    public id?: number,
    public instanceDate?: Moment,
    public user?: IUser,
    public details?: IInsuranceInstanceDetails[],
    public insuranceObject?: IInsuranceObject
  ) {}
}
