export class OrderItem{
    productId:Number;
    quantity:Number;
    price:Number;
    constructor(productId:Number,quantity:Number,price:Number){
        this.productId=productId;
        this.quantity=quantity;
        this.price=price;
    }
}