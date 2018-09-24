import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule  } from '@angular/forms';
import { MasterRoutingModule } from './master.rounting';
import { MasterSetupComponent } from './master-setup/master-setup.component';
import { SharedModule } from '../shared/shared.module';
import { MasterSetupService } from './master-setup/service/master-setup.service';

@NgModule({
  declarations: [
    MasterSetupComponent
  ],
  imports: [
    CommonModule,
    MasterRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    SharedModule
  ],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ],
  providers: [ MasterSetupService ]
})

export class MasterModule {

}
