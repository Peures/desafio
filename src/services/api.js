import axios from "axios";

export default axios.create({
  //URL total é http://localhost:8080/api/produtos
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-type": "application/json"
  }
});