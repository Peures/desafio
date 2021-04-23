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
                        <td><input type="text" v-model="produtoNew.code" placeholder="Escreva o código..."  required></td>
                        <td><input type="text" v-model="produtoNew.name" placeholder="Escreva o nome..."  required></td>
                        <td><input type="text" v-model="produtoNew.price" placeholder="Escreva o preço..."  required></td>
                        <td class="radio">
                            <label for="Categoria 1">Categoria 1
                                <input type="radio"  value="Categoria 1" v-model="produtoNew.category"  :required="produtoNew.category">
                            </label>
                            <label for="Categoria 2">Categoria 2
                                <input type="radio" value="Categoria 2" v-model="produtoNew.category"  :required="produtoNew.category">
                            </label>
                            <label for="Categoria 3">Categoria 3
                                <input type="radio" value="Categoria 3" v-model="produtoNew.category"  :required="produtoNew.category">
                            </label>
                            <label for="Categoria 4">Categoria 4
                                <input type="radio" value="Categoria 4" v-model="produtoNew.category"  :required="produtoNew.category">
                            </label>
                        </td>
                        <td>
                            <select v-model="produtoNew.status" id="status"  required>
                                <option value="Em estoque">Em estoque.</option>
                                <option value="Fora de estoque">Fora de estoque.</option>
                            </select>
                        </td>
                        <button @click="$emit('close'), createProduto(produtoNew)" type="submit" class="edit new">Criar</button>
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
    data(){
        return{
            produtos: [],
            produtoNew:{
                code: '',
                name: '',
                price: null,
                category: '',
                status: '',
            },
        }
    },
    created(){
     this.iniatilizer();
    },
    methods:{   
        iniatilizer(){
            dataService.getAllProdutos()
            .then(response => {
                console.log(response.data),
                this.produtos = (response.data)
            })
            .catch(e =>{
            console.log(e);
            })
        },
        async createProduto(produto){
           dataService.createProduto(produto)
           .then((response) => {
               console.log(response);
           })
           .catch((e) => {
               alert('Código repetido ou algo faltando. Tente novamente.')
               console.log(e);
           })
        },
        sendMessage(){
            alert('Algo faltando.');
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
        background: rgba(0,0,0,0.5);
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
        box-shadow: 0 0 20px rgba(0,0,0,0.7);
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
    }

    .edit{
        cursor: pointer;
        background-color: #121212;
        padding: 4px 15px;
        color: white;
        position: absolute;
        top: 10px;
        left: 90px;
    }

    .separator{
        margin-top: 20px;
    }

    input{
        margin-left: 11.5px;
        width: 84%;
    }

    select{
        margin-left: 11.5px;
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

    .new{
        background-color: #3D9776; /* Green */
    }

    tr{
        color: white;
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