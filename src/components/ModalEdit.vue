<template>
  <div class="modal">
      <div class="container">
        <div>
            <a @click="$emit('close')" class="close">Voltar</a>
            <table class="table table-custom separator">
                <thead>
                    <tr class="darken">  
                        <th>Código</th>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Categoria</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr scope="row">
                        <td>{{produto.code}}</td>
                        <td>{{produto.name}}</td>
                        <td>{{produto.price}}</td>
                        <td>{{produto.category}}</td>
                        <td>{{produto.status}}</td>
                    </tr>
                </tbody>
                <div class="separator"></div>
                <thead>
                    <tr class="darken">  
                        <th>Novo código</th>
                        <th>Novo nome</th>
                        <th>Novo preço</th>
                        <th>Nova categoria</th>
                        <th>Novo status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr scope="row">
                       <td><input type="text" v-model="produtoEdit.code" required></td>
                        <td><input type="text" v-model="produtoEdit.name" required></td>
                        <td><input type="text" v-model="produtoEdit.price" required></td>
                        <td class="radio">
                            <label for="Categoria 1">Categoria 1
                                <input type="radio" value="Categoria 1" v-model="produtoEdit.category" required>
                            </label>
                            <label for="Categoria 2">Categoria 2
                                <input type="radio" value="Categoria 2" v-model="produtoEdit.category" required>
                            </label>
                            <label for="Categoria 3">Categoria 3
                                <input type="radio" value="Categoria 3" v-model="produtoEdit.category" required>
                            </label>
                            <label for="Categoria 4">Categoria 4
                                <input type="radio" value="Categoria 4" v-model="produtoEdit.category" required>
                            </label>
                        </td>
                        <td><select v-model="produtoEdit.status" id="status" required>
                                <option value="Em estoque">Em estoque.</option>
                                <option value="Fora de estoque">Fora de estoque.</option>
                            </select>
                        </td>
                        <button @click="$emit('close'), updateProduct(), initilizer" type="submit" class="edit">Editar</button>
                    </tr>
                </tbody>
            </table>
        </div>
      </div>
  </div>
</template>

<script>
import dataService from '../services/dataService'
export default {
    name: 'Modal',
    props: ['produto'],
    data(){
        return{
            produtoEdit:{
                code: '',
                name: '',
                price: null,
                category: '',
                status: ''
            },
        }
    },
    methods:{
        async updateProduct(){
            dataService.updateProduto(this.produto.id, this.produtoEdit)
            .then((response) =>{
               console.log(response);
               this.produtoEdit = {};
           })
           .catch(e =>{
               if(e === 500){
                   alert('Algo faltando.')
               } else {
                   alert('Código já existe.')
               }
           })
        }
    }
}
</script>

<style scoped>
      .modal{
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background: rgba(0,0,0,0.1);
        z-index: 999;
    }

    tr.darken th{
        background-color: black;
    }

    .modal .container{
        position: fixed;
        max-width: 900px;
        top: 9vh;
        left: 0;
        right: 0;
        margin: 0 auto;
        padding: 2rem;
        border-radius: 4px;
        background-color: #A8ADB4;
        box-shadow: 0 0 20px rgba(0,0,0,0.3);
        overflow: auto;
    }

    .close{
        cursor: pointer;
        background-color: #121212;
        padding: 4px 15px;
        color: white;
        position: absolute;
        top: 10px;
        left: 10px;
        margin-bottom: 150px;
    }

    .edit{
        cursor: pointer;
        background-color: #121212;
        padding: 4px 15px;
        color: white;
        position: absolute;
        top: 10px;
        left: 90px;
        background-color: #55CBCD;
    }

    .separator{
        margin-top: 20px;
    }

    input{
        width: 84%;
    }

    select{
        width: 84%;
    }

    label{
        display:inline-block;    
        height:20px;    
        padding:0 0 0 25px;    
        margin:0 10px 0 0;
        text-align: center;
    }

    .radio{
        margin-left: 11.5px;
        width: 300px;
    }

    .table-custom
    tbody tr td {
    background: #646464;
    border: none;
    -webkit-transition: .3s all ease;
    -o-transition: .3s all ease;
    transition: .3s all ease; 
    }
</style>