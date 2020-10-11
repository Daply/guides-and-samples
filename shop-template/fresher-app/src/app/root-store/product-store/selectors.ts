import { State, featureAdapter } from './state';
import { MemoizedSelector, createFeatureSelector, createSelector } from '@ngrx/store';
import { Product } from 'src/app/core/models/product.model';


export const getError = (state: State): any => state.error;

export const getLoading = (state: State): boolean => state.isLoading;

export const getQuery = (state: State): string => state.query;

export const selectProductState: MemoizedSelector<object, State> = createFeatureSelector<State>('product');

export const selectAllProductItems: (state: object) => Product[] = featureAdapter.getSelectors(selectProductState).selectAll;

export const selectProductById = (id: number) => 
     createSelector(selectAllProductItems, 
        (products: Product[]) => {
            if (products) {
               products.find(p => p.productId === id)
            }
            else {
                return null;
            }
    });

export const selectProductError: MemoizedSelector<object, any> = createSelector(selectProductState, getError);

export const selectProductIsLoading: MemoizedSelector<object, boolean> = createSelector(selectProductState, getLoading);

export const selectProductQuery: MemoizedSelector<object, string> = createSelector(selectProductState, getQuery);