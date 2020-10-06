import { Router } from "express";
import { OrderService } from "../services/orders.service";

const orderRouter = Router();
const orderService=new OrderService();

orderRouter.post('/',orderService.addOrder);

orderRouter.get('/:orderId',orderService.findById);

orderRouter.get('/',orderService.findAll);

orderRouter.get('/:orderId/products',orderService.findOrderItems);

orderRouter.delete('/:orderId',orderService.deleteOrder);

module.exports = orderRouter;