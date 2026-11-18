
import axios from "axios";
const API=axios.create({baseURL:"http://10.88.19.221:9898/api",
    headers:{"Content-Type":"application/json" }
});
export default API;