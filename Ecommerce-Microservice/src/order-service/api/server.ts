
import app from "./app";
import { PORT } from "./constants/orders.constants";

app.listen(PORT, () => console.log(`Listening on port ${PORT}`));
