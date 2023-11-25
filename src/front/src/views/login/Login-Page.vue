<script setup>
    import { serviceAuthenticateUser } from "@/service/login";
    import {ref, onMounted, reactive} from "vue";

    // reactive state
    const login = reactive({Usuario: "", Senha: ""});
    const msg = ref('');

    // lifecycle hooks
    onMounted(async () => {
       // msg.value = await serviceAuthenticateUser(10);
        console.log(msg)
       await submitLogin()
    })


    // functions that mutate state and trigger updates
    const submitLogin = async () => {
        const res = await serviceAuthenticateUser(login)
        console.log(login.Usuario + " " + login.Senha + res)
    }


</script>

<template>

    <div class="content">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <img src="../../assets/logo_grande.png" alt="Image" class="img-fluid">
                </div>

                <div class="col-md-6 contents  d-flex align-items-center">
                    <div class="row justify-content-center">
                        <div class="col-md-8">

                            <div class="mb-4">
                                <h3 class="text-center">Bem-vindo ao Portal de Pesquisa Saúde em Equilíbio</h3>
                                <p class="mb-4">Por favor, entre com seu usuário e senha para acessar o banco de dados de pesquisa.</p>
                            </div>

                            <div>
                                <div class="form-floating mb-3">
                                    <input type="text" class="form-control" id="username" placeholder="Usuário" v-model="login.Usuario">
                                    <label for="username">Usuário</label>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="password" class="form-control" id="password" placeholder="Senha" v-model="login.Senha">
                                    <label for="password">Senha</label>
                                </div>
                            </div>

                            <input type="submit" value="Fazer login" class="btn btn-block btn-primary"  @click="submitLogin">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  lang="scss">
    $font-family-sans-serif-2: 'Roboto', sans-serif;
    $font-family-serif: 'Source Serif Pro', serif;
    $font-family-sans-serif: 'Roboto', sans-serif;

    .btn-block {
        display: block;
        width: 100%;
    }

    body {
        font-family: $font-family-sans-serif;
        background-color: #f8fafb;
    }

    p {
        color: darken(#ccc, 10%);
        font-weight: 300;
    }

    .content {
        padding: 7rem 0;
    }

    .content {
        .btn {
            height: 54px;
            padding-left: 30px;
            padding-right: 30px;
        }
    }

</style>
