import { Component, Input } from '@angular/core';


export class EventLogComponent {
  @Input() title: string;
  @Input() events: string[];
}
