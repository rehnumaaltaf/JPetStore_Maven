import { Component, OnInit,  ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { master } from '../interface/router-links';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css'],
   encapsulation: ViewEncapsulation.None,
})
export class NavBarComponent implements OnInit {

  constructor(private router: Router) {

  }

  ngOnInit() {
  }

  getMasterScreen() {
    this.router.navigate([master]);
  }

}
