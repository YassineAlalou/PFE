import { Request, Response } from "express";
import { MongooseDocument } from 'mongoose';
const mongoose=require('mongoose');

import { Order } from '../models/Order';
import { buildResponseOrder, buildResponseOrderItems } from "../Converters/OrderConverter";

export class OrderService {
  public findAll(request: Request, response: Response,next: Function) {
    Order.find({},(error: Error, orders:any) => {
            if(orders.length>0 && orders){
            const result ={
                orders: orders.map((order: any) =>{
                    return buildResponseOrder(order);
                })
            }
            response.status(200).json(result);
          }else{
            response.status(200).json({message: "No document found"});
          }
        }).catch((error: Error) => {
            console.log(error);
            response.status(500).json({
                error:error
            });
        });
  }

  public findById(request: Request, response: Response,next: Function) {
    const orderId=request.params.orderId;
    Order.findById(orderId, (error: Error, order: MongooseDocument) => {
            if(!order){
                return response.status(404).json(
                    {message : 'Order Not found'}
                )
            }
            response.status(201).json(buildResponseOrder(order));
        }).catch((error:Error) =>{
            response.status(404).json({
                error:error
            })
        })
  }

  public findOrderItems(request: Request, response: Response,next: Function) {
    const orderId=request.params.orderId;
    Order.findById(orderId, (error: Error, order: MongooseDocument) => {
            if(!order){
                return response.status(404).json(
                    {message : 'Order Not found'}
                )
            }
            response.status(201).json(buildResponseOrderItems(order));
        }).catch((error:Error) =>{
            response.status(404).json({
                error:error
            });
        });
  }

  public addOrder(request: Request, response: Response,next: Function) {
    const newOrder=new Order({
        _id: mongoose.Types.ObjectId(),
        creationDate: new Date(),
        customerId: request.body.customerId,
        orderProducts: request.body.orderProducts
    });
    newOrder.save((error: Error, order: MongooseDocument) => {
      if (error) {
        response.status(500).send(error);
      }
      response.status(201).json(buildResponseOrder(order));
    });
  }

  public deleteOrder(request: Request, response: Response,next: Function) {
    const orderId=request.params.orderId;
    Order.findByIdAndDelete(orderId, (error: Error, deleted: any) => {
      if (error) {
        response.status(500).json({error:error})
      }
      const message = deleted ? 'Deleted successfully' : 'Order not found :(';
      response.status(200).json({message:message});
    });
  }
  
}
