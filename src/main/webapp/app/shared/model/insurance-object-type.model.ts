export interface IInsuranceObjectType {
  id?: number;
  code?: string;
  descriptionAr?: string;
  descriptionEn?: string;
  objectTypeId?: number;
}

export class InsuranceObjectType implements IInsuranceObjectType {
  constructor(
    public id?: number,
    public code?: string,
    public descriptionAr?: string,
    public descriptionEn?: string,
    public objectTypeId?: number
  ) {}
}
