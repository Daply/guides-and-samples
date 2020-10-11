import { Product } from './product.model';
import { Cart } from './cart.model';

export class ProductCartQuantity {

    productQuantityId: number;
    product: Product;
    quantity: number;
    weight: number;
    price: number;
    cart: Cart;

    
}