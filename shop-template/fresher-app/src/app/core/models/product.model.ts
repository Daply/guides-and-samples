export class Product {
     productId: number;
     name: string;
     price: number;
     description: string;
     image: string;
     picture: any;

     constructor (newName?: string, newPrice?: number) {
         this.name = newName;
         this.price = newPrice;
     }

}