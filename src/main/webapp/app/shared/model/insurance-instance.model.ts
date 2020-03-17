import { Moment } from 'moment';
import { IInsuranceInstanceDetails } from 'app/shared/model/insurance-instance-details.model';

export interface IInsuranceInstance {
  id?: number;
  instanceDate?: Moment;
  userId?: number;
  details?: IInsuranceInstanceDetails[];
  insuranceObjectId?: number;
}

export class InsuranceInstance implements IInsuranceInstance {
  constructor(
    public id?: number,
    public instanceDate?: Moment,
    public userId?: number,
    public details?: IInsuranceInstanceDetails[],
    public insuranceObjectId?: number
  ) {}
}
