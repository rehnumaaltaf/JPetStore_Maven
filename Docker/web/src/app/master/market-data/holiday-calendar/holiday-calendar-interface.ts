import { Holiday } from './holiday';

export class HolidayCalendar {
    holidayCalId: Number;
    holidayCalName: string;
    holidayCalCode: string;
    holidayCalDesc: string;
    geoId: number;
    geoName: string;
    statusName: string;
    holidayListDto: HolidayListDto[];
    /*holidayListName: string;
    holidayListDate: string;*/
    deletedIds: Number[];
}


export class HolidayListDto {
     holidayListId: number;
     holidayListName: string;
     // holidayListDate: string;
     holidayListDate: Date;
     /*holidayListName: HolidayListName[];
     holidayListDate: HolidayListDate[];*/
}

/*export class HolidayListName {
    holidayListNameId: number;
    holidayListName: string;
}

export class HolidayListDate {
    holidayListDateId: number;
    holidayListDate: string;
}*/


