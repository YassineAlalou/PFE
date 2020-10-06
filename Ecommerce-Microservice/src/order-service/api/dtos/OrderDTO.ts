import { OrderItem } from "../models/OrderItem";

export class OrderDTO{
    orderId: String;
    creationDate: Date;
    customerId: Number;
    orderProducts: Array<OrderItem> = [];
    constructor(order:any){
       this.orderId=order.id;
       this.creationDate=order.creationDate;
       this.customerId=order.customerId;
       this.orderProducts=order.orderProducts;  
    }
    
}