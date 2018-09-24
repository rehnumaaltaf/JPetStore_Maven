import { Component, Input, Output, EventEmitter, ViewEncapsulation, OnChanges, ViewChild } from '@angular/core';
import { MessageModel } from './message.model';
import { ModalDirective } from 'ngx-bootstrap';
import { TranslationService } from 'angular-l10n';
import { PLACEHOLDER } from '../interface/common.constants';
@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MessageComponent implements OnChanges {
  @Input() msgs: MessageModel;
  @Output() msgsChange: EventEmitter<MessageModel> = new EventEmitter<MessageModel>();
  @Input() life: number;
  messageFadeOut: Number = 4000;
  placeHolderStr = PLACEHOLDER;
  @Output() onClose: EventEmitter<{isClosed: boolean}> = new EventEmitter<{isClosed: boolean}> ();
  @ViewChild('autoShownModal') public autoShownModal: ModalDirective;
  // @Output() msgsChange: EventEmitter<MessageModel[]> = new EventEmitter<MessageModel[]>();
  constructor(private translation: TranslationService ) { }
  countPlaceholder(str) {
    return (str.match(new RegExp(this.placeHolderStr + '' , 'g')) || []).length;
  }
  changeAndLocalizeMessage() {    
    // for (let i = 0; i < this.msgs.length; i++) {
       if (this.msgs && this.msgs.placeHolder) {
         const placeHolderCnt = this.countPlaceholder(this.translation.translate(this.msgs.summary));
         if ( placeHolderCnt  === this.msgs.placeHolder.length) {
          const arr = this.translation.translate(this.msgs.summary).split(this.placeHolderStr);
          let finalStr: string;
          finalStr = '';
          for ( let i = 0; i < arr.length; i++ ) {
            if ( this.msgs.placeHolder[i] ) {
              finalStr  =  finalStr + arr[i] + this.translation.translate(this.msgs.placeHolder[i]);
            }else {
              finalStr  =  finalStr + arr[i];
            }
          }
          this.msgs.summary = finalStr;
         } else {
           this.msgs.summary = this.translation.translate(this.msgs.summary);
           console.log('placeholder count mismatched');
         }
       }
      /* if (this.msgs && this.autoShownModal) {
          this.autoShownModal.show();
       }*/
   // }
  }
  ngOnChanges() {
    if (this.msgs) {
        if (this.msgs.summary && this.msgs.summary !== '') {
        this.msgs.summary = this.translation.translate(this.msgs.summary);
          this.changeAndLocalizeMessage();
          if (this.life) {
            this.messageFadeOut = this.life;
          }
          setTimeout(() => {
          /*if (this.autoShownModal) {
              this.autoShownModal.hide();
          } else {*/
            // this.msgs = [];
            this.msgs = new MessageModel();
            this.msgsChange.emit(this.msgs);
              this.onClose.emit({isClosed: true});
          // }
        }, this.messageFadeOut);
      }
    }
  }

  public onHidden(): void {
    this.msgs = new MessageModel();
    this.msgsChange.emit(this.msgs);
    this.onClose.emit({isClosed: true});
  }
}

