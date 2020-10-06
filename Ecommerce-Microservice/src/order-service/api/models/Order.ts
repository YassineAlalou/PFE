import { OrderItem } from "./OrderItem";

const mongoose=require('mongoose');

type NewType = OrderItem;

const orderSchema=mongoose.Schema({
   _id: mongoose.Schema.Types.ObjectId,
   creationDate: { type: Date,required:true},
   customerId: {type: Number},
   orderProducts : {type:Array<NewType>(),required:true}
});

export const Order=mongoose.model('Order',orderSchema);
