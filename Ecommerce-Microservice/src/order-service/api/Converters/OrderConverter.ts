import { OrderDTO } from "../dtos/OrderDTO";
import { OrderItem } from "../models/OrderItem";

export function buildResponseOrder(orderDocument:any):OrderDTO{
    return {
        orderId:orderDocument._id,
        creationDate: orderDocument.creationDate,
        customerId: orderDocument.customerId,
        orderProducts: orderDocument.orderProducts
    }
}

export function buildResponseOrderItems(orderDocument:any):Array<OrderItem>{
    return orderDocument.orderProducts;
}