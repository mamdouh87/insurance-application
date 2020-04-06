export interface IInsuranceObjectType {
  id?: number;
  code?: string;
  descriptionAr?: string;
  descriptionEn?: string;
}

export class InsuranceObjectType implements IInsuranceObjectType {
  constructor(public id?: number, public code?: string, public descriptionAr?: string, public descriptionEn?: string) {}
}
