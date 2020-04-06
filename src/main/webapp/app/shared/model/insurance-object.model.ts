import { IInsuranceInstance } from 'app/shared/model/insurance-instance.model';

export interface IInsuranceObject {
  id?: number;
  identifier1?: string;
  identifier2?: string;
  identifier3?: string;
  instances?: IInsuranceInstance[];
  typeId?: number;
  typeDescEn?: string;
  typeDescAr?: string;
}

export class InsuranceObject implements IInsuranceObject {
  constructor(
    public id?: number,
    public identifier1?: string,
    public identifier2?: string,
    public identifier3?: string,
    public instances?: IInsuranceInstance[],
    public typeId?: number,
    public typeDescEn?: string,
    public typeDescAr?: string
  ) {}
}
