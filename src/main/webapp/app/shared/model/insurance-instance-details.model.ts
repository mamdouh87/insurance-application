import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';
import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';
import { InstanceDetailsStatus } from 'app/shared/model/enumerations/instance-details-status.model';

export interface IInsuranceInstanceDetails {
  id?: number;
  imageContentType?: string;
  image?: any;
  comments?: string;
  status?: InstanceDetailsStatus;
  specification?: IInsuranceSpecification;
  insuranceInstance?: IInsuranceInstance;
}

export class InsuranceInstanceDetails implements IInsuranceInstanceDetails {
  constructor(
    public id?: number,
    public imageContentType?: string,
    public image?: any,
    public comments?: string,
    public status?: InstanceDetailsStatus,
    public specification?: IInsuranceSpecification,
    public insuranceInstance?: IInsuranceInstance
  ) {}
}
