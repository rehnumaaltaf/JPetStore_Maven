import { GlBasicDTO } from './gl-basic';
import { ExternalGLMappingDto } from './gl-externalmapping';

export class GLAccount {
    glId: number;
    glCode: string;
    glName: string;
    glFullname: string;
   // glBaseCode :string ;
   // action : string;
    statusName: string;
    glBasicDto: GlBasicDTO= new GlBasicDTO();
    externalGLMappingDto: ExternalGLMappingDto[];
}