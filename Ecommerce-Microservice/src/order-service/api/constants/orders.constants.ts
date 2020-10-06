
export const PORT = process.env.PORT || 8095;
export const MONGO_USERNAME= process.env.MONGO_USERNAME;
export const MONGO_PASSWORD= process.env.MONGO_PASSWORD;
export const MONGO_HOST=process.env.MONGO_HOST;
export const MONGO_PORT=process.env.MONGO_PORT;
export const MONGO_DB=process.env.MONGO_DB;
export const MONGO_URL = "mongodb://"+MONGO_USERNAME+":"+MONGO_PASSWORD+"@"+MONGO_HOST+":"+MONGO_PORT+"/"+MONGO_DB;
//mongodb://username:password@host:port/database