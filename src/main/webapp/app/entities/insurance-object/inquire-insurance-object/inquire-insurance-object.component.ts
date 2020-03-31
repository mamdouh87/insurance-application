import {Component, ElementRef, OnInit} from '@angular/core';

import {IInsuranceObject} from "app/shared/model/insurance-object.model";
import {InsuranceObjectService} from "app/entities/insurance-object/insurance-object.service";
import {ActivatedRoute, Router} from "@angular/router";
import {JhiDataUtils, JhiEventManager, JhiEventWithContent, JhiFileLoadError} from "ng-jhipster";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {IInsuranceInstanceDetails} from "app/shared/model/insurance-instance-details.model";
import {FormBuilder} from "@angular/forms";
import {AlertError} from "app/shared/alert/alert-error.model";

import {HttpResponse} from "@angular/common/http";
import {NgxImageCompressService} from "ngx-image-compress";

@Component({
  selector: 'jhi-inquire-insurance-object',
  templateUrl: './inquire-insurance-object.component.html',
  styleUrls: ['./inquire-insurance-object.component.scss']
})
export class InquireInsuranceObjectComponent implements OnInit {

  myAngularxQrCode?: string ;
  insuranceObject?: IInsuranceObject;
  insuranceInstanceDetails: IInsuranceInstanceDetails | null = null;
  imgResultBeforeCompress?:any;
  imageContentType?:any;
  imgResultAfterCompress?:any;

  editForm = this.fb.group({
    image: [],
    imageContentType: [],
    comments: [],
    status: []
  });

  constructor(
    protected insuranceObjectService: InsuranceObjectService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected elementRef: ElementRef,
    private fb: FormBuilder,
    protected dataUtils: JhiDataUtils,
    private imageCompress: NgxImageCompressService
  ) { }

  ngOnInit(): void {
  }
  scanSuccessHandler(result: string):void{
    this.myAngularxQrCode=result;
    const query = JSON.parse(result);
    this.insuranceObjectService.findByIdentification(query.id1,query.id2).subscribe(
      (res: HttpResponse<IInsuranceObject>) => this.onSuccess(res.body),
      () => this.onError()
    );
  }

  protected onSuccess(data: IInsuranceObject | null): void {

    if(data){
      this.insuranceObject = data ;
    }
  }

  protected onError(): void {
  }
  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('insuranceApplicationApp.error', { ...err, key: 'error.file.' + err.key })
      );
    });

  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null
    });
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }
  clearInputImage2( idInput: string): void {
    this.imgResultBeforeCompress = null;
    this.imgResultAfterCompress = null;
    if (this.elementRef && idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }
  compressFile() : void{

    this.imageCompress.uploadFile().then(({image, orientation}) => {

      this.imgResultBeforeCompress = image;
      console.warn('Size in bytes was:', this.imageCompress.byteCount(image));
      const base64ContentArray = image.split(",");
      if ( base64ContentArray[0]) {
        const mimeType = base64ContentArray[0].match('/[^:\\s*]\\w+\\/[\\w-+\\d.]+(?=[;| ])/');
        if(mimeType){
          this.imageContentType = mimeType[0];
        }


      }

      this.imageCompress.compressFile(image, orientation, 50, 50).then(
        result => {
          this.imgResultAfterCompress = result;
          console.warn('Size in bytes is now:', this.imageCompress.byteCount(result));
        }
      );

    });

  }
}
