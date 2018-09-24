/**import { Component, OnInit } from '@angular/core';
import { MasterSetupService } from '../../master-setup/service/master-setup.service';
import { Subscription } from 'rxjs/Subscription';
import { GradeDefinitionService } from './service/grade-definition.service';
import { GradeDefinition }  from './grade-definition';


@Component({
  selector: 'app-grade-definition',
  templateUrl: './grade-definition.component.html',
  styleUrls: ['./grade-definition.component.css']
})
export class GradeDefinitionComponent implements OnInit {

  subscription : Subscription;
  isComplete : boolean;
 

  constructor(private masterSetupService: MasterSetupService, private gradeDefinitionService: GradeDefinitionService) {
    
   }

  ngOnInit() {

    this.loadingGradeList();
  }

  loadingGradeList()
  {

      this.subscription = this.gradeDefinitionService.getGradeDefinitionJSON().subscribe(gradeDetails => {  
      this.gradeDefinitionService.gradeDetails = gradeDetails;  

       /* const link = this.masterSetupService.getChildObject('View Uom.GET');
      this.subscription = this.unitMeasurementService.getUnitMeasurementJSON(link.href).subscribe(addUomDetail => {
      this.unitMeasurementService.uomDetails = addUomDetail.body;
      this.unitMeasurementService.addChildObjects(addUomDetail.links);
     },
      error => alert(error),
      () => console.log('Finished')
      );  */
    
  /**},
      error => alert(error),
      () => console.log('Finished')
    );   


  }
  
  }
**/

