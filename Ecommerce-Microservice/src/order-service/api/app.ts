
const morgan= require('morgan');
import express, { Application } from 'express';

import bodyParser from 'body-parser';
import { MONGO_URL } from './constants/orders.constants';

const mongoose=require('mongoose');

const orderRouter = require('./routes/OrderRouter');

// swagger
const swaggerUi = require('swagger-ui-express');
const swaggerDocument = require('./swagger.json');

class App {
  public app: Application;

  constructor() {
    this.app = express();
    this.setConfig();
    this.setMongoConfig();
    this.setRoutes();
  }

  private setConfig() {
    this.app.use(morgan('dev'));
    this.app.use(bodyParser.urlencoded({extended: false}));
    this.app.use(bodyParser.json());
    this.setCors();
  }

  private setMongoConfig() {
    mongoose.Promise = global.Promise;
    mongoose.connect(MONGO_URL, {
      useNewUrlParser: true,
      useUnifiedTopology:true
    }).then(() => {
            console.log("Connected to Database");
    }).catch((error:Error) => {
        console.log("Not Connected to Database ERROR! ", error);
    });
  }

  private setCors(){
    this.app.use((req,res,next)=>{
        res.header('Access-Control-Allow-Origin','*');
        req.header(
            'Access-Control-Allow-Headers,Origin, X-Requested-With, Content-Type, Accept, Authorization'
         );
        if(req.method ==='OPTIONS'){
            res.header('Access-Control-Allow-Mthods','POST, PUT, PATCH, DELETE, GET');
            return res.status(200).json({});
        }
        next();
    });
  }
  
  
  private setRoutes(){
    this.app.use('/orders',orderRouter);
    this.app.use('/api-docs', swaggerUi.serve, swaggerUi.setup(swaggerDocument));
  }
  
}

export default new App().app;