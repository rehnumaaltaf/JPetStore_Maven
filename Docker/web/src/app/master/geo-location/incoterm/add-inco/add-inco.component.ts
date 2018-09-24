import { Component, Inject, OnInit, OnDestroy, Output, Input, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormControl, FormsModule, FormBuilder, FormArray, Validators } from '@angular/forms';
import { AccordionModule } from 'ngx-bootstrap';

import { CanComponentDeactivate } from '../../../../shared/guards/can-deactivate-guard';
import { TreeviewItem, TreeviewConfig } from 'ngx-treeview';

import { DropdownTreeviewSelectComponent } from '../../../../shared/dropdown-treeview-select/dropdown-treeview-select.component';
import { IncotermService } from '../service/incoterm.service';

import { Subscription } from 'rxjs/Subscription';
import { incoTerm } from '../../../../shared/interface/router-links';
import { ModalDirective } from 'ngx-bootstrap/modal';
import {
  Incoterm, ContractTermsPurchase, ContractTermsSales, Cost, CostGroup, ProductMasterInterface,
  AddReduce, Basis, BaseRefDropdown
} from '../inco-terms-interface';


@Component({
  selector: 'app-add-inco',
  templateUrl: './add-inco.component.html',
  styleUrls: ['./add-inco.component.css']
})
export class AddIncoComponent implements OnInit, OnDestroy, CanComponentDeactivate {
  // update modal
  @ViewChild('statusChangePopupModal') public statusChangePopupModal: ModalDirective;

  // validation modal
  @ViewChild('errorChangePopupModal') public errorChangePopupModal: ModalDirective;

  // invalid count modal
  @ViewChild('invalidCount') public invalidCount: ModalDirective;

  public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
  public title = 'Add Incoterms'


  public isEditPage: Boolean = false;
  public draft: Boolean = false;
  public active: Boolean = false;
  public inactive: Boolean = false;

  // deACTIVATE
  isActionPerformed = false;

  // Modals
  public errorModal: Boolean = false; // for DB error message
  public updatePopupModal: Boolean = false;  // confirm with canel and ok
  public countModal: Boolean = false;  // should be hidden after 30 secs invalidCount
  public invalidCountMsg: String; // msg for 30 sec count
  public validationError: String;
  public updateConfrimMsg: String;
  public errorPopupModal: Boolean = false // error with

  // Form
  public incotermForm: FormGroup;

  // SUBSCRIPTION
  public subscription: Subscription;



  // OBJECT TO CLASSES
  public incoterm: Incoterm = new Incoterm();
  public costGroup: CostGroup[] = Array(new CostGroup());
  public cost: Cost[] = Array(new Cost());
  public BaseRefDropdown: BaseRefDropdown[] = Array(new BaseRefDropdown());
  public Basis: Basis[] = Array(new Basis());

  public addReduce: AddReduce[] = Array(new AddReduce());

  // for Validations
  public showCrossPurchase: Boolean[] = [];
  public showPlusPurchase: Boolean[] = [];
  public showCrossSale: Boolean[] = [];
  public showPlusSale: Boolean[] = [];
  public isSubmitted: Boolean = false;
  public requiedMesssage: String = 'is required';
  public counter: number;

  // Colors
  public invalidColor: String = '#d43c3c';
  public validColor: String = '#cfdee7';
  public conditionalPurchase: Boolean[] = [];
  public conditionalSale: Boolean[] = [];

  // for Edit
  public deletedIdsPurchase: Number[] = [];
  public deletedIdsSale: Number[] = [];
  public editId: number;
  public updatedstatus: String;
  public updateIncotermArray: Incoterm[] = [];
  public updateQueryParams: Object;

  public select = 'Select';

  // For treeView
  items: TreeviewItem[];
  selectedItem: TreeviewItem;
  treeViewValue: number
  // values: number[];
  config = TreeviewConfig.create({
    hasFilter: true,
    hasAllCheckBox: false,
    maxHeight: 400,
    hasCollapseExpand: true
  });

  constructor(public incotermService: IncotermService,
    private fb: FormBuilder, private router: Router, private route: ActivatedRoute) {
    this.incoterm.contractTermsPurchaseDto = Array(new ContractTermsPurchase());
    this.incoterm.contractTermsSalesDto = Array(new ContractTermsSales());
  }


  ngOnInit() {
    window.scrollTo(0, 0);

    // ** Needs to be implement after Ngx-treeview single select is done uncomment below call
    // this.getGeoDropDown();
    this.getCost();
    this.getCostGroup();
    this.getAddReduce();
    this.getBasis();
    this.getBaseRef();
    // CHECKING FOR EDIT
    if (this.route.snapshot.params['id'] !== undefined) {
      this.editId = this.route.snapshot.params['id'];
      this.title = 'Edit Incoterms';
      this.isEditPage = true;
      this.buildIncoterm();
      this.getIncotermById(this.editId);
    } else {
      this.buildIncoterm();
      this.addIncoterm(0);
    }
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  // ** wrritten to check ngx-treeview select must be removed
  ngx() {
    alert('insdie ngx');
    alert(JSON.stringify(this.selectedItem));
    alert('2nd alert');
  }

  getCost() {
    this.subscription = this.incotermService.getCost().subscribe(addGeoDetail => {
      this.cost = addGeoDetail.body;
    },
      error => { throw error },
      () => console.log('Finished')
    );

  }
  getCostGroup() {
    this.subscription = this.incotermService.getCostGroup().subscribe(addGeoDetail => {
      this.costGroup = addGeoDetail.body;
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  getAddReduce() {
    this.subscription = this.incotermService.getAddReduce().subscribe(addGeoDetail => {
      this.addReduce = addGeoDetail.body;
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  getBasis() {
    this.subscription = this.incotermService.getBasis().subscribe(addGeoDetail => {
      this.Basis = addGeoDetail.body;
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  getBaseRef() {
    this.subscription = this.incotermService.getBaseRef().subscribe(addGeoDetail => {
      this.BaseRefDropdown = addGeoDetail.body;
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }
  //  Populating the product

  getGeoDropDown() {
    this.subscription = this.incotermService.getProductFromJson().subscribe(addGeoDetail => {
      this.items = this.populatetree(addGeoDetail.body);
    },
      error => { throw error },
      () => console.log('Finished')
    );
  }

  populatetree(geoList: ProductMasterInterface[]): TreeviewItem[] {
    const treeDropDown: TreeviewItem[] = [];
    geoList.forEach(geo => {
      let treeList;
      treeList = new TreeviewItem({
        text: geo.prodName, value: geo.prodId, checked: false
      });
      if (geo.children != null && geo.children.length > 0) {
        this.recursion(geo.children, treeList);
      }
      treeDropDown.push(new TreeviewItem(treeList));
    });
    return treeDropDown;


  }
  recursion(geoList: ProductMasterInterface[], treeList: TreeviewItem): TreeviewItem {
    geoList.forEach(geo => {
      let newView;
      newView = new TreeviewItem({
        text: geo.prodName, value: geo.prodId, checked: false
      });

      if (geo.children != null && geo.children.length > 0) {
        if (treeList.children == null) {
          treeList.children = [this.recursion(geo.children, newView)];
        } else {
          treeList.children.push(this.recursion(geo.children, newView));
        }
      } else {
        if (treeList.children == null) {
          treeList.children = [newView];
        } else {
          treeList.children.push(newView);
        }
      }
    });
    return treeList;
  }

  // product population ends


  getIncotermById(editId) {
    this.incotermService.getIncotermById(editId).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.incoterm = data.body;

        if (this.incoterm.statusName === 'Draft') {
          this.draft = true;
          this.active = false;
          this.inactive = false;
        } else if (this.incoterm.statusName === 'Active') {
          this.draft = false;
          this.active = true;
          this.inactive = false;
        } else {
          this.draft = false;
          this.active = false;
          this.inactive = true;
        }

        this.patchForm();
      }
    }, error => { console.log(JSON.stringify(error)) });
    this.isActionPerformed = true;
  }
  // fOR THE EDIT PART

  patchForm() {
    this.incotermForm.patchValue({
      contractTermsId: this.incoterm.contractTermsId,
      contractTermsCode: this.incoterm.contractTermsCode,
      contractTermsName: this.incoterm.contractTermsName,
      contractTermsDesc: this.incoterm.contractTermsDesc,
      prodId: this.incoterm.prodId,
      contractTermsBasisRefId: this.incoterm.contractTermsBasisRefId,
      contractTermsBaseRefId: this.incoterm.contractTermsBaseRefId,
      statusName: this.incoterm.statusName,
      deletedPurchaseIds: '',
      deletedSalesIds: ''
    })
    this.setPurchaseSalesArray();
  }

  setPurchaseSalesArray() {

    if (this.incoterm.contractTermsPurchaseDto) {
      const controlPurchase = <FormArray>this.incotermForm.controls.contractTermsPurchaseDto;
      this.incoterm.contractTermsPurchaseDto.forEach(x => {
        controlPurchase.push(this.fb.group({
          contractTermsPurchaseId: x.contractTermsPurchaseId, contractTermsId: x.contractTermsId,
          costGroupId: [x.costGroupId, Validators.required],
          costId: [x.costId, Validators.required],
          budgetInd: [x.budgetInd],
          contrTrmsAddreduceRefId: [x.contrTrmsAddreduceRefId, Validators.required],
        }))

        if (this.showPlusPurchase.length > 0) {
          this.showPlusPurchase[this.showPlusPurchase.length - 1] = false
        }
        this.showPlusPurchase.push(true);
        this.showCrossPurchase.push(true);
        if (this.showCrossPurchase.length === 1) {
          this.showCrossPurchase[0] = false;
        } else if (this.showCrossPurchase.length > 1) {
          this.showCrossPurchase[0] = true;
        }
        // For conditional Mandator check
        this.conditionalPurchase.push(false)
      })

    } else {
      this.addIncoterm(1)
    }

    // Populating the Sale Array
    if (this.incoterm.contractTermsSalesDto) {
      const controlSale = <FormArray>this.incotermForm.controls.contractTermsSalesDto;
      this.incoterm.contractTermsSalesDto.forEach(x => {
        controlSale.push(this.fb.group({
          contractTermsSalesId: x.contractTermsSalesId,
          contractTermsId: x.contractTermsId,
          costGroupId: [x.costGroupId, Validators.required],
          costId: [x.costId, Validators.required],
          budgetInd: [x.budgetInd],
          contrTrmsAddreduceRefId: [x.contrTrmsAddreduceRefId, Validators.required],
        }))


        if (this.showPlusSale.length > 0) {
          this.showPlusSale[this.showPlusSale.length - 1] = false
        }
        this.showPlusSale.push(true);
        this.showCrossSale.push(true);
        if (this.showCrossSale.length === 1) {
          this.showCrossSale[0] = false;
        } else if (this.showCrossSale.length > 1) {
          this.showCrossSale[0] = true;
        }
        // For conditional Mandator check
        this.conditionalSale.push(false)
      })
    } else {
      this.addIncoterm(2);
    }
  }

  // ADD PART

  buildIncoterm(): void {
    this.incotermForm =
      this.fb.group({
        contractTermsId: [this.incoterm.contractTermsId],
        contractTermsCode: [this.incoterm.contractTermsCode, [Validators.required, Validators.pattern('^[a-zA-Z0-9-_]*$')]],
        contractTermsName: [this.incoterm.contractTermsName,
        [Validators.required, Validators.pattern('^[a-zA-Z0-9_-]+[a-zA-Z0-9-_\\s]*$')]],
        contractTermsDesc: [this.incoterm.contractTermsDesc],
        prodId: [this.incoterm.prodId],
        contractTermsBasisRefId: [this.incoterm.contractTermsBasisRefId, Validators.required],
        contractTermsBaseRefId: [this.incoterm.contractTermsBaseRefId, Validators.required],
        contractTermsPurchaseDto: this.fb.array([]),
        contractTermsSalesDto: this.fb.array([]),
        statusName: [this.incoterm.statusName],
        deletedPurchaseIds: this.incoterm.deletedPurchaseIds,
        deletedSalesIds: this.incoterm.deletedSalesIds


      });
  }

  addIncoterm(flag?: number) {

    if (flag === 0) {

      // purchase
      const controlPurchase = <FormArray>this.incotermForm.controls['contractTermsPurchaseDto'];
      controlPurchase.push(this.fb.group({
        contractTermsPurchaseId: [''],
        contractTermsId: '',
        costGroupId: ['', Validators.required],
        costId: ['', Validators.required],
        budgetInd: 'N',
        contrTrmsAddreduceRefId: ['', Validators.required],
      }));

      this.showPlusPurchase.push(true);
      this.showCrossPurchase.push(false);

      // For conditional Mandator check
      this.conditionalPurchase.push(true)



      // sales
      const controlSale = <FormArray>this.incotermForm.controls['contractTermsSalesDto'];
      controlSale.push(this.fb.group({
        contractTermsSalesId: '',
        contractTermsId: '',
        costGroupId: ['', Validators.required],
        costId: ['', Validators.required],
        budgetInd: 'N',
        contrTrmsAddreduceRefId: ['', Validators.required],
      }));
      this.showPlusSale.push(true);
      this.showCrossSale.push(false);

      // For conditional Mandator check
      this.conditionalSale.push(true)

    } else if (flag === 1) {
      const controlPurchase = <FormArray>this.incotermForm.controls['contractTermsPurchaseDto'];
      controlPurchase.push(this.fb.group({
        contractTermsPurchaseId: [''],
        contractTermsId: '',
        costGroupId: ['', Validators.required],
        costId: ['', Validators.required],
        budgetInd: 'N',
        contrTrmsAddreduceRefId: ['', Validators.required],
      }));
      // Fpr show plus and cross functionality
      if (this.showPlusPurchase.length > 0) {
        this.showPlusPurchase[this.showPlusPurchase.length - 1] = false
      }
      this.showPlusPurchase.push(true);
      this.showCrossPurchase.push(true);
      if (this.showCrossPurchase.length === 1) {
        this.showCrossPurchase[0] = false;
      } else if (this.showCrossPurchase.length > 1) {
        this.showCrossPurchase[0] = true;
      }

      // For conditional Mandator check
      this.conditionalPurchase.push(true)


    } else if (flag === 2) {
      const controlSale = <FormArray>this.incotermForm.controls['contractTermsSalesDto'];
      controlSale.push(this.fb.group({
        contractTermsSalesId: '',
        contractTermsId: '',
        costGroupId: ['', Validators.required],
        costId: ['', Validators.required],
        budgetInd: 'N',
        statusName: 'Draft',
        contrTrmsAddreduceRefId: ['', Validators.required],
      }));

      if (this.showPlusSale.length > 0) {
        this.showPlusSale[this.showPlusSale.length - 1] = false
      }
      this.showPlusSale.push(true);
      this.showCrossSale.push(true);
      if (this.showCrossSale.length === 1) {
        this.showCrossSale[0] = false;
      } else if (this.showCrossSale.length > 1) {
        this.showCrossSale[0] = true;
      }

      // For conditional Mandator check
      this.conditionalSale.push(true)
    }
  }

  removeIncoterm(whichArray: String, i: number, id?: number) {
    if (whichArray === 'purchase') {
      if (this.incotermForm.value.contractTermsPurchaseDto.length > 1) {
        const control = <FormArray>this.incotermForm.controls['contractTermsPurchaseDto'];

        // to be sent to update service
        if (this.isEditPage) {
          this.deletedIdsPurchase.push(this.incotermForm.value.contractTermsPurchaseDto[i].contractTermsPurchaseId);
        }
        control.removeAt(i);

        // For plus and cross icon
        this.showCrossPurchase.splice(i, 1);
        this.showPlusPurchase.splice(i, 1);
        if (i === (this.showPlusPurchase.length)) {
          this.showPlusPurchase[this.showPlusPurchase.length - 1] = true;
        }
        if (this.showCrossPurchase.length === 1) {
          this.showCrossPurchase[0] = false;
          this.showPlusPurchase[0] = true;
        }

        // For Conditional Mandatory check
        this.conditionalPurchase.splice(i, 1);
      }
    } else if (whichArray === 'sale') {
      if (this.incotermForm.value.contractTermsSalesDto.length > 1) {
        const control = <FormArray>this.incotermForm.controls['contractTermsSalesDto'];

        // TO BE SENT TO UPDATE SERVICE
        if (this.isEditPage) {
          this.deletedIdsSale.push(this.incotermForm.value.contractTermsSalesDto[i].contractTermsSalesId);
        }
        control.removeAt(i);

        // FOR PLUS AND CROSS
        this.showCrossSale.splice(i, 1);
        this.showPlusSale.splice(i, 1);
        if (i === (this.showPlusSale.length)) {
          this.showPlusSale[this.showPlusSale.length - 1] = true;
        }
        if (this.showCrossSale.length === 1) {
          this.showCrossSale[0] = false;
          this.showPlusSale[0] = true;
        }

        // For Conditional Mandatory check
        this.conditionalSale.splice(i, 1);
      }

    }

  }

  changeColor(whichArray: string, i: number, val?: string) {
    if (whichArray === 'purchase') {
      this.conditionalPurchase[i] = false;

      /*if (!val) {
  const control = <FormGroup>(<FormArray>this.incotermForm.controls['contractTermsPurchaseDto'])[i].controls;
  console.log(control);
  control['costGroupId'].setValue('');
        this.incotermForm.value.contractTermsPurchaseDto[i].patchValue(costGroupId : '');
        this.incotermForm.value.contractTermsPurchaseDto[i].costId.patchValue('');
        this.incotermForm.value.contractTermsPurchaseDto[i].budgetInd.patchValue('N');
        this.incotermForm.value.contractTermsPurchaseDto[i].contrTrmsAddreduceRefId.patchValue('');
        this.conditionalPurchase[i] = true
      }*/

    } if (whichArray === 'sale') {
      this.conditionalSale[i] = false;
      /*if (!val) {
        this.incotermForm.value.contractTermsSalesDto[i].value.costGroupId = '';
        this.incotermForm.value.contractTermsSalesDto[i].value.costId = '';
        this.incotermForm.value.contractTermsSalesDto[i].value.budgetInd = 'N';
        this.incotermForm.value.contractTermsSalesDto[i].value.contrTrmsAddreduceRefId = '';
        this.conditionalSale[i] = true;
      }*/
    }
  }

  getSomeClass(whichArray: string, i: number) {
    if (whichArray === 'purchase') {
      if (this.conditionalPurchase[i]) {
        return 'colr-lblue font-regular font-14'
      } else {
        return 'colr-red font-regular font-14'
      }
    } else if (whichArray === 'sale') {
      if (this.conditionalSale[i]) {
        return 'colr-lblue font-regular font-14'
      } else {
        return 'colr-red font-regular font-14'
      }
    }
  }

  isValidForm() {
    this.counter = 0;

    if (!this.incotermForm.value.contractTermsCode) {
      this.counter++
    }
    if (!this.incotermForm.value.contractTermsName) {
      this.counter++
    } if (!this.incotermForm.value.contractTermsBasisRefId) {
      this.counter++
    } if (!this.incotermForm.value.contractTermsBaseRefId) {
      this.counter++
    }

    for (let i = 0; i < this.incotermForm.value.contractTermsPurchaseDto.length; i++) {
      if ((!this.incotermForm.value.contractTermsPurchaseDto[i].costGroupId && !this.conditionalPurchase[i])) {
        this.counter++
      }
      if ((!this.incotermForm.value.contractTermsPurchaseDto[i].costId && !this.conditionalPurchase[i])) {
        this.counter++
      }
      if ((!this.incotermForm.value.contractTermsPurchaseDto[i].contrTrmsAddreduceRefId && !this.conditionalPurchase[i])) {
        this.counter++
      }
    }

    for (let i = 0; i < this.incotermForm.value.contractTermsSalesDto.length; i++) {
      if ((!this.incotermForm.value.contractTermsSalesDto[i].costGroupId && !this.conditionalSale[i])) {
        this.counter++
      }
      if ((!this.incotermForm.value.contractTermsSalesDto[i].costId && !this.conditionalSale[i])) {
        this.counter++
      }
      if ((!this.incotermForm.value.contractTermsSalesDto[i].contrTrmsAddreduceRefId && !this.conditionalSale[i])) {
        this.counter++
      }
    }

    if (this.counter === 0) {

      const costIdPurchase: Number[] = new Array;
      for (let i = 0; i < this.conditionalPurchase.length; i++) {
        if (!this.conditionalPurchase[i]) {
          costIdPurchase.push(Number(this.incotermForm.value.contractTermsPurchaseDto[i].costId))

        }

      }

      // --- Duplice costId check for purchase
      const costIdSale: Number[] = new Array;
      for (let i = 0; i < this.conditionalSale.length; i++) {
        if (!this.conditionalSale[i]) {
          costIdSale.push(Number(this.incotermForm.value.contractTermsSalesDto[i].costId))

        }

      }

      if (costIdPurchase.length !== new Set(costIdPurchase).size) {
        this.validationError = 'Please check deplicate Purchase Cost Name';
        this.errorPopupModal = true;
        return false;
      } else if (costIdSale.length !== new Set(costIdSale).size) {
        this.validationError = 'Please check deplicate Sales Cost Name';
        this.errorPopupModal = true;
        return false;
      }
    } else {
      this.invalidCountMsg = 'Please fill ' + this.counter + ' Mandatory Fields';
      this.countModal = true;
      if (this.invalidCount) {
        setTimeout(() => { this.invalidCount.hide(); }, 3000);
      }



      return false;
    }

    // checking the pattern error for Code and Name
    if (this.incotermForm.controls.contractTermsCode.hasError('pattern')) {
      this.validationError = 'Invalid Incoterm Code';
      this.errorPopupModal = true;
      return false;
    } else if (this.incotermForm.controls.contractTermsName.hasError('pattern')) {
      this.validationError = 'Invalid Incoterm Name';
      this.errorPopupModal = true;
      return false;
    }



    // removinng empty element for PUrchase Array
    const spliceIndexesPurchase: number[] = []
    for (let i = 0; i < this.conditionalPurchase.length; i++) {
      if (this.conditionalPurchase[i]) {
        spliceIndexesPurchase.push(i);

      }

    }


    for (const element of spliceIndexesPurchase) {
      this.incotermForm.value.contractTermsPurchaseDto.splice(element, 1)
    }

    // removinng empty element for PUrchase Array
    const spliceIndexesSale: number[] = []
    for (let i = 0; i < this.conditionalSale.length; i++) {
      if (this.conditionalSale[i]) {
        spliceIndexesSale.push(i);

      }

    }


    for (const element of spliceIndexesSale) {
      this.incotermForm.value.contractTermsSalesDto.splice(element, 1)
    }

    return true;
  }


  saveIncoterm(action: number) {
    this.isActionPerformed = true;
    this.isSubmitted = true;

    if (action === 1) {
      if (this.isValidForm()) {
        this.incotermForm.value.statusName = 'Draft';

        this.subscription = this.incotermService.saveIncoterm(this.incotermForm.value).subscribe(glDetail => {
          this.router.navigate([incoTerm], { queryParams: { 'success': 1, 'incoterm': this.incotermForm.value.contractTermsName } });
        }, error => {
          this.errorModal = true;
          if (this.errorModal) {
            setTimeout(() => { this.errorModal = false; }, 3000);
          }
        });

      }
    }
    if (action === 2) {
      if (this.isValidForm()) {
        this.incotermForm.value.statusName = 'Active';

        this.subscription = this.incotermService.saveIncoterm(this.incotermForm.value).subscribe(glDetail => {
          this.router.navigate([incoTerm], { queryParams: { 'success': 2, 'incoterm': this.incotermForm.value.contractTermsName } });
        }, error => {
          this.errorModal = true;
          setTimeout(() => { this.errorModal = false; }, 3000);
        });
      }
    }

  }


  // Edit update functionaliy
  conf_update(selection: boolean) {
    this.statusChangePopupModal.hide();
    if (selection) {
      this.perfromUpdate()
    } else {
      this.updatePopupModal = false;
    }

  }

  updateIncoterm(statusType: number, incoterm?: Incoterm) {
    this.isActionPerformed = true;
    this.isSubmitted = true;

    if (this.isValidForm()) {
      this.incotermForm.value.deletedPurchaseIds = this.deletedIdsPurchase;
      this.incotermForm.value.deletedSalesIds = this.deletedIdsSale;

      if (statusType === 1) {
        this.incotermForm.value.statusName = 'Draft';
        this.updateConfrimMsg = 'Please confrim to save draft ' + this.incotermForm.value.contractTermsName;
      } else if (statusType === 2) {
        this.incotermForm.value.statusName = 'Active';
        this.updateConfrimMsg = 'Please confirm to reactivate ' + this.incotermForm.value.contractTermsName;
      } else if (statusType === 3) {
        this.incotermForm.value.statusName = 'InActive';
        this.updateConfrimMsg = 'Please confirm to deactivate ' + this.incotermForm.value.contractTermsName;
      } else if (statusType === 0) {
        // NO change in the status it is read from DB
        this.updateConfrimMsg = 'Please confirm to update ' + this.incotermForm.value.contractTermsName;
      } else if (statusType === 4) {
        this.incotermForm.value.statusName = 'Active';
        this.updateConfrimMsg = 'Please confirm to activate ' + this.incotermForm.value.contractTermsName;
      }
      this.updateQueryParams = { addupdate: statusType, incoterm: this.incotermForm.value.contractTermsName }

      this.updatePopupModal = true;
    }
  }

  perfromUpdate() {

    const updateIncotermArray: Incoterm[] = Array();
    updateIncotermArray.push(this.incotermForm.value);

    this.subscription = this.incotermService.updateIncoterm(updateIncotermArray).subscribe(data => {
      if (data.body !== null && data.body !== '') {
        this.router.navigate([incoTerm], { queryParams: this.updateQueryParams });
      } else {
      }
    }, error => { throw error; });
  }


  resetForm(): void {
    this.isSubmitted = false;


    for (let i = 0; i < this.conditionalPurchase.length; i++) {
      this.conditionalPurchase[i] = true
    }

    for (let i = 0; i < this.conditionalSale.length; i++) {
      this.conditionalSale[i] = true
    }

  }


  getcontractTermsPurchaseDto(incotermForm) {
    return incotermForm.get('contractTermsPurchaseDto').controls;
  }
  getcontractTermsSalesDto(incotermForm) {
    return incotermForm.get('contractTermsSalesDto').controls;
  }

  onHiddenReset() {
    this.errorModal = false;

    this.updatePopupModal = false;
    this.countModal = false;

    // Resetting all purchase to non - mandatory
    for (let i ; i < this.conditionalPurchase.length ; i++) {
      this.conditionalPurchase[i] = true;
    }

    // Resetting all sale to non - mandatory
    for (let i ; i < this.conditionalPurchase.length ; i++) {
      this.conditionalSale[i] = true;
    }


  }

  canDeactivate(): boolean {
    return this.isActionPerformed;
  }

  onCancel() {
    this.router.navigate([incoTerm]);
  }
}
