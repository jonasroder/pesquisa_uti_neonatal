<script setup>
import {serviceAuthenticateUser} from "@/service/login";
import {onMounted, reactive} from "vue";
import {useRouter} from "vue-router";
import {loading} from "@/plugins/loadingService.js";


const router = useRouter();
const login  = reactive({
    usuario: "",
    senha  : ""
});


onMounted(() => {
    window.addEventListener('keydown', handleEnter);
});


const submitLogin = async () => {
    loading.show()
    const response = await serviceAuthenticateUser(login);
    if (response) {
        router.push({name: 'Neonato-List'});
        window.removeEventListener('keydown', handleEnter);
    }
    loading.hide()
}


const handleEnter = (event) => {
    if (event.key === 'Enter' && login.usuario !== "" && login.senha !== "") {
        submitLogin();
    }
};

</script>

<template>
    <v-row align="center" justify="center" class="fill-height">

        <v-col cols="12" md="5">
            <v-img :src="require('../../assets/logo_login_uti.png')" alt="Image" contain></v-img>
        </v-col>

        <v-col cols="12" md="5" class="d-flex align-center pa-3">
            <v-row justify="center">
                <v-col cols="12" md="7">
                    <div class="text-center mb-4">
                        <h3>Bem-vindo ao Portal de Pesquisa em Epidemiologia Neonatal</h3>
                        <p>Por favor, entre com seu usuário e senha para acessar o banco de dados de pesquisa.</p>
                    </div>

                    <!--Input Usuário-->
                    <v-col cols="12">
                        <v-text-field
                            label="Email"
                            type="email"
                            prepend-icon="fa-solid fa-user"
                            v-model="login.usuario"
                        />
                    </v-col>

                    <!--Input Senha-->
                    <v-col cols="12">
                        <v-text-field
                            label="Senha"
                            type="password"
                            prepend-icon="fa-solid fa-lock"
                            v-model="login.senha"
                        />
                    </v-col>

                    <v-col cols="12">
                        <v-btn color="primary" block @click="submitLogin">FAZER LOGIN</v-btn>
                    </v-col>
                </v-col>
            </v-row>
        </v-col>
    </v-row>
</template>


<style lang="scss">

p {
    color: darken(#ccc, 10%);
    font-weight: 300;
}

.content {
    padding: 7rem 0;
}


</style>
