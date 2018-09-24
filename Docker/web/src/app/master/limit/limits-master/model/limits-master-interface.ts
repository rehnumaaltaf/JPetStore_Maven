export class LimitsMaster {
    limitBasis: LimitBasis[];
    limitHeaderId: Number;
    limitHeaderNo: string;
    limitBasisTypeId: Number;
    additionallimitBasisTypeName:string;
    limitBasisId: Number;
    additionallimitBasisTypeId: Number;
    additionallimitBasisId: Number;
    comments: Comments[];
    comment1: Comments= new Comments();
    statusName: string;
    limitBasisTypeName: string;
    limitBasisName: string; 
    additionallimitBasisName: string; 
    limitDetails: LimitDetails[];
    limitsAttribute: LimitsAttribute[];
    modifiedDate:string;
    createdDate:string;
    modifiedBy:string;
    createdBy:string;
    // limitFxDetails: LimitFxDetails[];
   //  limitExchangeDetails: LimitExchangeDetails[];
}

export class LimitBasis {
   limitBasisTypeId: Number;
   limitBasisTypeCode: string;
   limitBasisTypeName: string;
}

export class Comments {
  limitCommentId: Number;
  commentText: string;
  createdDate: Date;
}

export class LimitDetails {
   limitDetailsId: Number;
   isTemporary: string;
   validFrom: Date;
   validTo: Date;
   limitOutRightAttribute: LimitsAttribute;
   limitBasisAttribute: LimitsAttribute;
   limitStructureAttribute: LimitsAttribute;
   limitDrawDownAttribute: LimitsAttribute;
   limitVaRAttribute: LimitsAttribute;
   limitDeltaAttribute: LimitsAttribute;
   limitGammaAttribute: LimitsAttribute;
   limitVegaAttribute: LimitsAttribute;
   currentMonthAttribute: LimitsAttribute;
   forwardMonthAttribute: LimitsAttribute;
   totalAttribute: LimitsAttribute;
   valueAttribute: LimitsAttribute;
}

export class LimitsAttribute {
   limitAttributeRefId: Number;
   limitVolumeValueRefId: Number;
   limitVolumeValueRefCode: string;
   limitVolumeValueRefName: string;
   limitVolumeUomIdCurrencycode: string;
   limit: Number;
   limitCurrencyId: Number;
   limitCurrencyCode: string;
   limitCurrencyName: string;
   limitVolumeUomId: Number;
   limitVolumeUomCode: string;
   limitVolumeUomName: string
}



export const active: String = 'Active';
export const inactive: String = 'InActive';
export const draft: String = 'Draft' ;
export const deactive: String = 'Deactive';
export const update: String = 'Update';
export const reactivate: String = 'Reactivate';
export const save: String = 'Save';
export const defaultSelectValue: Number = -1;


export const msgsuccess: String = 'Success :';
export const msgupdate: String = 'updated';
export const msgactivateorsubmit: String = 'activated';
export const msgdelete: String = 'deleted';
export const msgdeactivate: String = 'deactivated';
export const msgreactivate: String = 'reactivated';
export const msgsave: String = 'saved';
export const msgduplicatecheck: String = ' exists. Please reenter valid unique value';
export const msgconfirmdeactivate: String = 'Please confirm to deactive';
export const msgconfirmdelete: String = 'Please confirm to delete ';
export const msgconfirmreactive: String = ' Please confirm to reactive';

