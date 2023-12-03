<script setup>
    import { serviceAuthenticateUser } from "@/service/login";
    import { reactive } from "vue";
    import { useRouter } from "vue-router";
    import TextInput from '@/components/TextInput.vue';


    const login = reactive({usuario: "", senha: ""});
    const router = useRouter();


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
        <v-row  align="center" justify="center" class="fill-height">

            <v-col cols="12" md="5">
                <v-img :src="require('../../assets/logo_grande.png')" alt="Image" contain></v-img>
            </v-col>

            <v-col cols="12" md="5" class="d-flex align-center">
                <v-row justify="center">
                    <v-col cols="12" md="7">
                        <div class="text-center mb-4">
                            <h3>Bem-vindo ao Portal de Pesquisa Saúde em Equilíbrio</h3>
                            <p>Por favor, entre com seu usuário e senha para acessar o banco de dados de pesquisa.</p>
                        </div>

                            <!--Input Usuário-->
                            <text-input
                                v-model="login.usuario"
                                label="Usuário"
                                type="text"
                                prepend-icon="fa-solid fa-user"
                                md="12"
                                lg="12"
                                xl="12"
                            />

                            <!--Input Senha-->
                            <text-input
                                v-model="login.senha"
                                label="Senha"
                                type="password"
                                prepend-icon="fa-solid fa-lock"
                                md="12"
                                lg="12"
                                xl="12"
                            />

                        <v-btn color="primary" block @click="submitLogin">FAZER LOGIN</v-btn>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
</template>



<style  lang="scss">

    p {
        color: darken(#ccc, 10%);
        font-weight: 300;
    }

    .content {
        padding: 7rem 0;
    }



</style>
