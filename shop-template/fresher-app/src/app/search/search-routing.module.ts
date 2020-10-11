import { NgModule } from '@angular/core';
import { SearchComponent } from './search.component';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: 'search', component: SearchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class SearchRoutingModule { }