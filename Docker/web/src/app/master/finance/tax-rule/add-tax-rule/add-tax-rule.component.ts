import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-tax-rule',
  templateUrl: './add-tax-rule.component.html',
  styleUrls: ['./add-tax-rule.component.css']
})
export class AddTaxRuleComponent implements OnInit {
public status: any = {
    isFirstOpen: true,
    isFirstDisabled: false
  };
public
  constructor() { }

  ngOnInit() {
  }

}
