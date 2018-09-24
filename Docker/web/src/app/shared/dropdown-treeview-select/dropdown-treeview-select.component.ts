import { Component, Injectable, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormGroup ,  FormControl,  Validators,  FormBuilder, ReactiveFormsModule , FormsModule} from '@angular/forms';
import {
    TreeviewI18n, TreeviewItem, TreeviewConfig, TreeviewHelper, TreeviewComponent,
    TreeviewEventParser, DownlineTreeviewItem, TreeviewI18nDefault, DropdownDirective, DropdownTreeviewComponent
} from 'ngx-treeview'




export class DropdownTreeviewSelectI18n extends TreeviewI18nDefault {
    private _selectedItem: TreeviewItem;

    set selectedItem(value: TreeviewItem) {
        if (value && value.children === undefined) {
            this._selectedItem = value;
        }
    }

    get selectedItem(): TreeviewItem {
        debugger;
        return this._selectedItem;
    }

    getText(selectedItems: TreeviewItem[], isAll: boolean): string {
        debugger;
       return this._selectedItem ? this._selectedItem.text : 'All';
    }
}

@Component({
  selector: 'ngx-dropdown-treeview-select',
  templateUrl: './dropdown-treeview-select.component.html',
  styleUrls: ['./dropdown-treeview-select.component.css']
})

export class DropdownTreeviewSelectComponent {
    @Input() config: TreeviewConfig;
    @Input() items: TreeviewItem[];
    @Output() selectedChange = new EventEmitter<TreeviewItem>();
    @ViewChild(DropdownTreeviewComponent) dropdownTreeviewComponent: DropdownTreeviewComponent;
    filterText: string;
    private dropdownTreeviewSelectI18n: DropdownTreeviewSelectI18n;

    constructor(
        public i18n: TreeviewI18n
    ) {
        this.dropdownTreeviewSelectI18n = i18n as DropdownTreeviewSelectI18n;
    }

    select(item: TreeviewItem) {
        //if (item.children === undefined) {
            this.dropdownTreeviewComponent.dropdownDirective.close();
            this.dropdownTreeviewSelectI18n.selectedItem = item;
            this.selectedChange.emit(item);
        //}
    }

   

}