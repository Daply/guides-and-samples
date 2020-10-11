import { Injectable } from '@angular/core';
import { Actions, ofType, Effect } from '@ngrx/effects';
import { Action } from '@ngrx/store';
import { ProductService } from 'src/app/core/services/product.service';
import * as featureActions from './actions';
import { Observable, of } from 'rxjs';
import { startWith, switchMap, map, catchError } from 'rxjs/operators';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable()
export class ProductsStoreEffects {

    constructor(private actions$: Actions, 
                private productService: ProductService,
                private sanitizer: DomSanitizer) {}

    @Effect()
    products$: Observable<Action> = this.actions$.pipe(
        ofType<featureActions.SearchAction>(
            featureActions.SEARCH
        ),
        switchMap(action =>
            this.productService
                .searchProducts(action.payload)
                .pipe(
                    map(
                        items =>
                        {
                            for (let p of items) {
                                p.picture = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + p.image);
                            }
                            return new featureActions.SearchCompleteAction({
                                items
                            });
                        } 
                    ),
                    catchError(error =>
                        of(new featureActions.SearchFailureAction({ error }))
                    )
                )
        )
    )

}