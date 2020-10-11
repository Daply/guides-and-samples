import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-product-search',
  templateUrl: './product-search.component.html',
  styleUrls: ['./product-search.component.css']
})
export class ProductSearchComponent implements OnInit {

  @Input() query = '';
  @Output() search = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  searchStart(value) {
    this.search.emit(value);
  }

}
