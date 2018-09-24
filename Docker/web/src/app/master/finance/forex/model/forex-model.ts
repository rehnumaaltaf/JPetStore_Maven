import { DropdownModel } from './dropdown-model';
import { ForwardTenor } from './forward-tenor-model';
export class ForexModel {
    baseCurrencyList: DropdownModel[];
    counterCurrencyList: DropdownModel[];
    frequencyList: DropdownModel[];
    tenorList: ForwardTenor[];
    tenorDropDownList: DropdownModel[];
    tenorDurationTypeList: DropdownModel[];
    tenorDayTypeList: DropdownModel[];
    status: string;
    forexCode: string;
    forexName: string;
    forexDesc: string;
    baseCurrency: string;
    counterCurrency: string;
    toValidate: string;
    frequency: string;
    statusId: number;
    forexId: string;
    message: string;
}
