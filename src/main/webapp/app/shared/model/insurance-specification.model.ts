export interface IInsuranceSpecification {
  id?: number;
  code?: string;
  descriptionAr?: string;
  descriptionEn?: string;
  insurenceObjectTypeId?: number;
}

export class InsuranceSpecification implements IInsuranceSpecification {
  constructor(
    public id?: number,
    public code?: string,
    public descriptionAr?: string,
    public descriptionEn?: string,
    public insurenceObjectTypeId?: number
  ) {}
}
