import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInsuranceSpecification } from 'app/shared/model/insurance-specification.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { InsuranceSpecificationService } from './insurance-specification.service';
import { InsuranceSpecificationDeleteDialogComponent } from './insurance-specification-delete-dialog.component';

@Component({
  selector: 'jhi-insurance-specification',
  templateUrl: './insurance-specification.component.html'
})
export class InsuranceSpecificationComponent implements OnInit, OnDestroy {
  insuranceSpecifications?: IInsuranceSpecification[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;

  constructor(
    protected insuranceSpecificationService: InsuranceSpecificationService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.insuranceSpecificationService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IInsuranceSpecification[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
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
    this.registerChangeInInsuranceSpecifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInsuranceSpecification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInsuranceSpecifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('insuranceSpecificationListModification', () => this.loadPage());
  }

  delete(insuranceSpecification: IInsuranceSpecification): void {
    const modalRef = this.modalService.open(InsuranceSpecificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.insuranceSpecification = insuranceSpecification;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IInsuranceSpecification[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/insurance-specification'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.insuranceSpecifications = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
}
