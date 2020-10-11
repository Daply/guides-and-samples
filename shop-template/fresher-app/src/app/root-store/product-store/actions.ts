import { Action } from '@ngrx/store';
import { Product } from 'src/app/core/models/product.model';
import { createAction } from '@ngrx/store';

export const SEARCH =           '[Product] Search';
export const SEARCH_COMPLETE =  '[Product] Search Complete';
export const SEARCH_FAILURE =  '[Product] Search Failure';

export class SearchAction implements Action {
  readonly type = SEARCH;

  constructor(public payload: string) { }
}

export class SearchCompleteAction implements Action {
  readonly type = SEARCH_COMPLETE;

  constructor(public payload: { items: Product[] }) { }
}

export class SearchFailureAction implements Action {
    readonly type = SEARCH_FAILURE;
  
    constructor(public payload: { error: any }) { }
  }
  

/**
 * Export a type alias of all actions in this action group
 * so that reducers can easily compose action types
 */
export type Actions
  = SearchAction
  | SearchCompleteAction
  | SearchFailureAction;