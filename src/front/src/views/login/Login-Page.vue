<script setup>
    import { serviceAuthenticateUser } from "@/service/login";
    import { reactive } from "vue";
    import { useRouter } from "vue-router"; // Importe o useRouter
    import TextInput from '@/components/TextInput.vue';


    const login = reactive({usuario: "", senha: ""});
    const router = useRouter(); // Crie uma instância do useRouter



    const submitLogin = async () => {
        try {
            const response = await serviceAuthenticateUser(login);
            if (response) {
                router.push('/');
            }
        } catch (error) {
            console.error("Erro ao realizar login:", error);
        }
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
                                <!--Input Usuário-->
                                <text-input
                                    v-model="login.usuario"
                                    label="Usuário"
                                    type="text"
                                    prepend-icon="fa-solid fa-user"
                                />

                                <!--Input Senha-->
                                <text-input
                                    v-model="login.senha"
                                    label="Senha"
                                    type="password"
                                    prepend-icon="fa-solid fa-lock"
                                />
                            </div>

                            <div class="d-grid gap-2">
                                <button  type="button" class="btn btn-primary" @click="submitLogin">FAZER LOGIN</button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>



<!-- Add "scoped" attribute to limit CSS to this component only -->
<style  lang="scss">

    p {
        color: darken(#ccc, 10%);
        font-weight: 300;
    }

    .content {
        padding: 7rem 0;
    }



</style>
