export interface ViewUnit{
    unitCode: string;
    unitName: string;
    unitFullname: string;
    isgroupunit: string;
    parentunit: string;
    GlMapping:{
        legalEntity:string;
        glcode:string;
    },
    status:string;
}