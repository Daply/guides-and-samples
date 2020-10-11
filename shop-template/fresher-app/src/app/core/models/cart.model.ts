import { ProductCartQuantity } from './product-cart-quantity.model';

export class Cart {
    cartId: number;
    products: ProductCartQuantity[];
    totalPrice: number;
    userId: number;

    constructor(newProducts?: ProductCartQuantity[]) {
        this.products = newProducts;
    }

}