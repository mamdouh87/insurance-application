import { InstanceDetailsStatus } from 'app/shared/model/enumerations/instance-details-status.model';

export interface IInsuranceInstanceDetails {
  id?: number;
  imageContentType?: string;
  image?: any;
  comments?: string;
  status?: InstanceDetailsStatus;
  specificationId?: number;
  insuranceInstanceId?: number;
}

export class InsuranceInstanceDetails implements IInsuranceInstanceDetails {
  constructor(
    public id?: number,
    public imageContentType?: string,
    public image?: any,
    public comments?: string,
    public status?: InstanceDetailsStatus,
    public specificationId?: number,
    public insuranceInstanceId?: number
  ) {}
}
