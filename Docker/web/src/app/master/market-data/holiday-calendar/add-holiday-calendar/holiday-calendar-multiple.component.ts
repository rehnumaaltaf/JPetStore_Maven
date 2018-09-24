import { Component, Input, NgModule, OnChanges } from '@angular/core';
import {  FormGroup, FormControl, Validators, FormArray, FormBuilder, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HolidayCalendarService } from '../service/holiday-calendar.service';
import { SharedModule } from '../../../../shared/shared.module';
import { BrowserModule } from '@angular/platform-browser';
import { Subscription } from 'rxjs/Subscription';
import { Router } from '@angular/router';
import { HolidayCalendar } from '../holiday-calendar-interface';
import { HolidayListDto } from '../holiday-calendar-interface';

@Component({
    moduleId: module.id,
    selector: 'app-holiday-calendar-multiple',
    templateUrl: 'holiday-calendar-multiple.component.html',
})
export class HolidayCalendarMultipleComponent implements OnChanges {
    @Input('group') public holidayCalendarMultipleForm: FormGroup;
    @Input() title: any;
    @Input() holidayCalendarDto: HolidayCalendar = new HolidayCalendar();
    @Input() reqholidayName: boolean;
    @Input() reqholidayDate: boolean;
    public min;
    public max;

    msgHolidayName = 'Field is required';
    holidayListName: string;
    // holidayListDate: string;
    holidayListDate: Date;
    holidayMappingId: string ;
     holidaylistMultiple: HolidayListDto;
    constructor(private holidayCalendarService: HolidayCalendarService) {
    }
    ngOnChanges() {

        // console.log('start' + JSON.stringify(this.holidayCalendarNgModelBinding));
        if (this.holidayCalendarDto != null) {

              if (this.holidayCalendarDto.holidayListDto != null) {

                if (this.holidayCalendarDto.holidayListDto[this.title] != null ) {
                    // alert(this.holidayCalendarNgModelBinding.holidayListDto[this.title].holidayListName);
                if (this.holidayCalendarDto.holidayListDto[this.title].holidayListName != null) {
                    this.holidayListName = this.holidayCalendarDto.holidayListDto[this.title].holidayListName;
                 }
                if (this.holidayCalendarDto.holidayListDto[this.title].holidayListDate != null) {

                    this.holidayListDate = this.holidayCalendarDto.holidayListDto[this.title].holidayListDate;
                }
                 if (this.holidayCalendarDto.holidayListDto[this.title].holidayListId != null) {

                    this.holidayMappingId = this.holidayCalendarDto.holidayListDto[this.title].holidayListId.toString();
                }
                }

            }
        }
    }

    getStyleHolidayName(value) {

        if ( this.reqholidayName) {
                return '#cfdee7';
        }else if (this.holidayCalendarService.editcode != null ) {
          this.holidaylistMultiple = this.holidayCalendarService.editcode;
          if (this.holidayCalendarService.editcode[value] != null) {
             // alert(this.holidayCalendarService.editcode[value].holidayListName)
            if (!this.holidayCalendarService.editcode[value].holidayListName) {
            // if ( this.reqholidayName) {
                return '#d43c3c';
            } else {
                return '#cfdee7';
            }
        }
    }
      }

      getStyleHolidayDate(value) {
        //   alert(value)
        if ( this.reqholidayDate) {
                return '#cfdee7';
        }else if (this.holidayCalendarService.editcode != null ) {
          this.holidaylistMultiple = this.holidayCalendarService.editcode;
          if (this.holidayCalendarService.editcode[value] != null) {
            if (!this.holidayCalendarService.editcode[value].holidayListDate) {
            // if (this.reqholidayDate) {
            // if (!this.holidayCalendarService.editcode[value].holidayListDate) {
                return '#d43c3c';
            } else {
                return '#cfdee7';
            }
        }
    }
      }
}

