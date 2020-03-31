import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceObjectType } from 'app/shared/model/insurance-object-type.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { InsuranceObjectTypeService } from './insurance-object-type.service';
import { InsuranceObjectTypeDeleteDialogComponent } from './insurance-object-type-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-object-type',
  templateUrl: './insurance-object-type.component.html'
})
export class InsuranceObjectTypeComponent implements OnInit, OnDestroy {
  insuranceObjectTypes?: IInsuranceObjectType[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected insuranceObjectTypeService: InsuranceObjectTypeService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.insuranceObjectTypeService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IInsuranceObjectType[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInInsuranceObjectTypes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceObjectType): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInsuranceObjectTypes(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceObjectTypeListModification', () => this.loadPage());
  }

  delete(insuranceObjectType: IInsuranceObjectType): void {
    const modalRef = this.modalService.open(InsuranceObjectTypeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceObjectType = insuranceObjectType;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IInsuranceObjectType[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/insurance-object-type'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.insuranceObjectTypes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
