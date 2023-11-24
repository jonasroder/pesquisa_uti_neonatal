<script setup>
    import { serviceAuthenticateUser } from "@/service/login";
    import {ref, onMounted, reactive} from "vue";

    // reactive state
    const usuario = ref('');
    const senha = ref('');
    const login = reactive({Usuario: "", Senha: ""});
    const msg = ref('');


    // lifecycle hooks
    onMounted(async () => {
       // msg.value = await serviceAuthenticateUser(10);
    })


    // functions that mutate state and trigger updates
    const submitLogin = async () => {
        const res = await serviceAuthenticateUser(login)
        msg.value=res;
        console.log(res)
        console.log(usuario.value + senha.value);
        console.log(login.Usuario + " " + login.Senha)
    }


</script>

<template>
    <div class="hello">
        <h1 class="row">{{ msg }}</h1>
        Senha: {{senha}}
        Usuario: {{usuario}}

        <hr>

        {{login.Usuario}}
        <br>
        {{login.Senha}}

        <div>
            <i class="fas fa-heart"></i> <!-- Ícone de coração do Font Awesome -->
            <i class="fas fa-star"></i> <!-- Ícone de estrela do Font Awesome -->
        </div>

        <div class="col-md-3 mt-2">
            <input type="text" name="Usuario" id="Usuario" placeholder="Usuário" v-model="login.Usuario">
            <input type="text" name="Senha" id="Senha" placeholder="Senha" v-model="login.Senha">
            <button type="submit" @click="submitLogin">Login</button>
        </div>
    </div>
</template>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
    margin: 40px 0 0;
}
ul {
    list-style-type: none;
    padding: 0;
}
li {
    display: inline-block;
    margin: 0 10px;
}
a {
    color: #42b983;
}
</style>
