import { MemoizedSelector, createSelector } from '@ngrx/store';
import { ProductStoreSelectors } from './product-store';

export const selectError: MemoizedSelector<object, string> = 
       createSelector(ProductStoreSelectors.selectProductError, 
                     (selectProductError: string) => selectProductError);

export const selectIsLoading: MemoizedSelector<object, boolean> =
       createSelector(ProductStoreSelectors.selectProductIsLoading,
                     (selectProductIsLoading: boolean) => selectProductIsLoading);

export const selectQuery: MemoizedSelector<object, string> = 
       createSelector(ProductStoreSelectors.selectProductQuery,
                     (selectProductQuery: string) => selectProductQuery)