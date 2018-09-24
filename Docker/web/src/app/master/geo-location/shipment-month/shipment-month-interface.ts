export class ShipmentWeek {

  shipmentTimeframeCode: string;
  shipmentTimeframeName: string;
  shipmentTimeframeDesc: string;
  statusName: string;
  printDescription: string;
  shipmentRuleId: number;
  shipmentWeekTimeframeId: Number;
  shipmentRule: ShipmentRule;
  shipmentRuleName: string;
  action: string;
  checked: Boolean;
}

export class ShipmentRule {

                shipmentRuleId: Number;
                shipmentRuleName: String;
                shipmentRuleCode?: String;
                shipmentRuleDesc?: String;
                rangeType?: String;
                startRange?: Number;
                endRange?: Number;
}
