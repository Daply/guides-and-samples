import { createEntityAdapter, EntityAdapter, EntityState } from '@ngrx/entity';
import { Product } from 'src/app/core/models/product.model';

export const featureAdapter: EntityAdapter<Product> = 
createEntityAdapter<Product>({
    selectId: model => model.productId,
    sortComparer: (a: Product, b: Product): number =>
        b.productId.toString().localeCompare(a.productId.toString())
});

export interface State extends EntityState<Product> {
    query?: string;
    isLoading?: boolean;
    error?: any;
}

export const initialState: State = featureAdapter.getInitialState({
     query: '', 
     isLoading: false,
     error: null
})
