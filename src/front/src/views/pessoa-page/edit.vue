<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {onMounted, reactive} from "vue";
import { serviceAuthenticateTeste } from "@/service/pessoa";



onMounted(async () => {
    pessoa.nome = await serviceAuthenticateTeste()
})

const pessoa = reactive({
    nome: "",
    sobrenome: "",
    sexo: "",
    data_nascimento: "",
    estado_civil: "",
    profissao: "",
    cpf: "",
    etnia: "",
    educacao: "",
    religiao: "",
    rua: "",
    numero: "",
    complemento: "",
    bairro: "",
    cidade: "",
    estado: "",
    cep: ""
});


const handleSave = () => {
    // Implemente a lógica de salvamento do formulário aqui
    console.log('Formulário de usuário salvo!');
};

const handleBack = () => {
    // Implemente a lógica para voltar
    console.log('Retornando da tela de cadastro de usuário.');
};
</script>

<template>
    <card-formulario title="Cadastro de Pessoa" subtitle="Centralizando Informações para Cuidado Integral"
                     @handleSave="handleSave" @handleBack="handleBack">
        <span>{{msg}}</span>
        <v-row>
            <v-col cols="12" sm="4" md="3" justify="center" align="center" >
                <AvatarImageInput/>
            </v-col>

            <v-col cols="12" sm="8" md="9">
                <v-row>
                    <text-input
                        v-model="pessoa.nome"
                        label="Nome"
                        type="text"
                        cols="12"
                        md="4"
                    />
                    <text-input
                        v-model="pessoa.sobrenome"
                        label="Sobrenome"
                        type="text"
                        cols="12"
                        md="4"
                    />
                    <text-input
                        v-model="pessoa.cpf"
                        label="CPF"
                        type="text"
                        cols="12"
                        md="4"
                    />
                    <text-input
                        v-model="pessoa.data_nascimento"
                        label="Data de Nascimento"
                        type="date"
                        cols="12"
                        md="4"
                    />
                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>
