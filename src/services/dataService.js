import http from "./api";

class dataService {
  getAllProdutos() {
    return http.get("/produtos");
  }

  findProdutoById(id) {
    return http.get(`/produtos/${id}`);
  } 

  createProduto(data) {
    return http.post("/produtos", data);
  }

  updateProduto(id, data) {
    return http.put(`/produtos/${id}`, data);
  }

  deleteProduto(id) {
    return http.delete(`/produtos/${id}`);
  }

  deleteAllProdutos() {
    return http.delete(`/produtos`);
  }

  findByName(name) {
    return http.get(`/produtos?name=${name}`);
  }
}

export default new dataService();
