import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { ProductMasterInterface } from '../product-master-interface';
import {ProductMasterService}  from '../product-master/../service/product-master.service';
import { Router, ActivatedRoute} from '@angular/router';
@Component({
  moduleId: module.id,
  selector: 'app-base-product-intgrade',
  templateUrl: 'add-multiple-grade.component.html',
})
export class AddMultiBaseProductGradeComponent implements OnInit {
  public indexPosition;
  public data0;
  public data1;
  public data2;
  public data3;
  public pageName;
  whentoAdd: boolean;
  whentoEdit: boolean;
  codeType: string;
  code: string;
  grade: any;
  subscription: Subscription;
  codedescription: string;
  @Input('group')
  public baseProductMappingforms: FormGroup;
  @Input() title: Number;
  @Input() financialCalendarModelsGrade: ProductMasterInterface[];
  constructor(public productMasterService: ProductMasterService, private route: ActivatedRoute) {
    this.subscription = this.productMasterService.getdestinationFromJson().subscribe(destination => {

      this.grade = destination.body.intlCodeTypeRefValues;
    },
      error => { throw error },
      () => console.log('Finished')
    );
    this.route.queryParams.subscribe(params => {
      // Defaults to 0 if no query param provided.
      this.pageName = +params['elem'];
    });
    if (this.pageName == 6) {
      this.whentoEdit = true;
      this.whentoAdd = false;
    } else {
      this.whentoAdd = true;
      this.whentoEdit = false;
    }
  }
  ngOnInit() {
    this.indexPosition = this.title;
    if (this.financialCalendarModelsGrade != null) {
      if (this.financialCalendarModelsGrade[this.indexPosition] != undefined) {
        this.data0 = this.financialCalendarModelsGrade[this.indexPosition].gradeMappingId;
        this.codeType = this.financialCalendarModelsGrade[this.indexPosition].codeTypeId;
        this.code = this.financialCalendarModelsGrade[this.indexPosition].code;
        this.codedescription = this.financialCalendarModelsGrade[this.indexPosition].codedescription;
      }
    }
  }
}
