
<template>
  <div class="home">
    <div class="content">
        <div class="container">
            <button @click="iniatilizer" class="edit">Refresh</button>
            <button class="new row" @click="newProduto">+ Novo</button>
             <ModalNew
                v-if="newPress"
                @close="newProduto()">
              </ModalNew>
            <button class="del row" @click="deleteProduto()">- Deletar</button>
            <input  type="text" v-model="search" class="search" placeholder="Pesquise pelo nome...">
            <button @click="searchState" class="search">Pesquisar</button>
        </div>

        <div class="container">
            <h3 class="sortBy">Organizar por:</h3>

            <button class="edit" @click="sortCode">Código</button>
            <button class="edit" @click="sortName">Nome</button>
            <button class="edit" @click="sortPrice">Preço</button>
            <button class="edit" @click="sortCategory">Categoria</button>
            <button class="edit" @click="sortStatus">Status</button>

            <h1 class="mb-5"><strong>GERENCIAR PRODUTOS</strong></h1>

            <div class=" custom-table-responsive coolTable" id="checkboxes">
                <table v-if="produtos.length > 0"  class="table custom-table" >
                    <thead>
                        <tr>  
                          <th scope="col">SELECT</th>
                          <th scope="col">CÓDIGO</th>
                          <th scope="col">NOME</th>
                          <th scope="col">PREÇO</th>
                          <th scope="col">CATEGORIA</th>
                          <th scope="col">STATUS</th>
                          <th scope="col">OPÇÕES</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr v-for="produto in filteredProdutos" :key="produto.id" scope="row">
                            <td class="center">
                              <label class="control control--checkbox">
                                <input type="checkbox" v-model="delProdutos" :value="produto.id">
                                <div class="control__indicator"></div>
                              </label>
                            </td>
                            <td>{{produto.code}}</td>
                            <td>{{produto.name}}</td>
                            <td>{{produto.price}}</td>
                            <td>{{produto.category}}</td>
                            <td>{{produto.status}}</td>
                            <td>
                                <button @click="editProduto(produto)" class="edit">
                                    Editar
                                </button>
                                <button @click="deleteById(produto)" class="delete del">
                                    Deletar
                                </button>
                            </td>
                            <ModalEdit
                              v-if="editPress"
                              :produto = "fullForm"
                              @close="editProduto()">
                            </ModalEdit>  
                        </tr>
                        <tr class="spacer"><td colspan="100"></td></tr>
                        <button v-if="searchOn" class="edit" @click="searchState">Voltar</button>
                    </tbody>
                </table>

                <div v-else><h1>Página ainda vazia.</h1></div>
            </div>

            <button class="bigDel" @click="deleteAll()">Deletar TODOS</button>

        </div>
    </div>
  </div>
</template>

<script>

import dataService from '../services/dataService';
import ModalEdit from '../components/ModalEdit';
import ModalNew from '../components/ModalNew'; 
export default {
    name: "Home",
    components:{
      ModalEdit,
      ModalNew,
    },
    data(){
      return{
        produtos: [],
        editPress: false,
        fullForm: {},
        newPress: false,
        search: '',
        searchOn : false,
        delProdutos: [],
        delPress: false,
      }
  },
  methods:{
      iniatilizer(){
        dataService.getAllProdutos()
        .then(response => {
            console.log(response.data);
            this.produtos = (response.data);
        })
        .catch(e =>{
          console.log(e);
        })
      },
      deleteById(produto){
        if(confirm("Tem certeza?")){
          dataService.deleteProduto(produto.id);
          alert('Produto apagado!');
        } else{
          alert('Cancelado.')
        }
      },
      editProduto(produto){
          this.editPress = !this.editPress;
          if(this.editPress){
            this.fullForm = produto;
          }
          else{
            this.fullForm = {};
          }
      },
      searchState(){
        this.searchOn = !this.searchOn;
      },
      newProduto(){
        this.newPress = !this.newPress;
      },
      sortCode(){
        this.produtos.sort((a, b) => a.code > b.code ? 1: -1);
      },
      sortPrice(){
        this.produtos.sort((a, b) => a.price > b.price ? 1: -1);
      },
      sortName(){
        this.produtos.sort((a, b) => a.name > b.name ? 1: -1);
      },
      sortCategory(){
        this.produtos.sort((a, b) => a.category > b.category ? 1: -1);
      },
      sortStatus(){
        this.produtos.sort((a, b) => a.status > b.status ? 1: -1);
      },
      deleteAll(){
        if(confirm("Deletar todos? Tem certeza?")){
          dataService.deleteAllProdutos()
          .then(response =>{
            console.log(response)
            alert('Produtos apagados!');
          })
          .catch(e=>{
            console.log(e);
          })
        } else{
          alert('Cancelado.')
        }
      },
      deleteProduto(){
        if(this.delProdutos.length === 0){
          alert('Nenhum produto selecionado!');
        } else if(confirm("Tem certeza?")){
            this.delProdutos.forEach(id => {
            dataService.deleteProduto(id);
            })
            alert('Produtos apagados!');
          } else{
            alert('Cancelado.')
          }
      },
  },
  created(){
      this.iniatilizer();
  },
  computed:{
    filteredProdutos: function(){
      if(this.searchOn){
        return this.produtos.filter((produto) => {
          return produto.name.match(this.search)
        })
      } else{
        return this.produtos;
      }
    },
  }
}
</script>

<style scoped>

div.container{
  width: 900px;
  margin: 0 auto;
}
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: lightgrey;
}
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}
.lblChk{
   display: block;
  position: relative;
  padding-left: 35px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  padding: 10%;
  margin-left: 25px;
}
  
.lblChk input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}
.lblChk:hover input ~ .checkmark {
  background-color: darkgrey;
}
.lblChk input:checked ~ .checkmark {
  background-color: #2196F3;
}
.lblChk input:checked ~ .checkmark:after {
  display: block;
}
.lblChk .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
button {
  cursor: pointer;
  border: none;
  color: white;
  padding: 15px 32px;
  font-weight: bold;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  padding: 10px;
}
.new{
    background-color: #3D9776; /* Green */
}
.del{
    background-color:#FF6145; /* Green */
}
.bigDel{
  background-color: red;
}
.search{
    border-radius: 5%;
}
input.search{
    margin-left: 347px;
    padding-left: 30px;
    border: 2px solid black;
    height: 35px;
}
button.search{
    background-color: #55CBCD;
}
.delete{
    margin-left: 20px;
}
.edit{
  background-color: #55CBCD;
}
tbody tr {
  text-align: center;
}
tbody td{
    color: white;
    padding: 10px 5px;
}

.center{
  align-items: center;
  justify-content: center;
  text-align: center;
  align-content: center;
  position: relative;
  padding-left: 33px;
}
.row{
  margin-left: 10px;
}
.coolTable{
        overflow-y: hidden, scroll;
        overflow-x:hidden;
        height: 550px;
        display: block;
    }
.coolTable table{
  border-radius: 4px;
}
.coolTable table th{
  padding: 20px 5px;
  position: sticky;
  background-color: rgb(41, 40, 40);
}

h3{
  color: white;
}
</style>
