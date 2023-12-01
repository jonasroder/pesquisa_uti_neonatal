<template>
    <card-formulario title="Cadastro de Pessoa" subtitle="Centralizando Informações para Cuidado Integral" @handleSave="handleSave" @handleBack="handleBack">
        <v-row>

        <!--Input Nome-->
        <text-input
            v-model="pessoa.nome"
            label="Nome"
            type="text"
            cols="12"
            md="6"
            lg="3"
        />

        <!--Input Sobrenome-->
        <text-input
            v-model="pessoa.sobrenome"
            label="Sobrenome"
            type="text"
            cols="12"
            md="6"
            lg="3"
        />

        <!--Input CPF-->
        <text-input
            v-model="pessoa.cpf"
            label="CPF"
            type="text"
            cols="12"
            md="6"
            lg="3"
        />

        <!--Input Data de Nascimento -->
        <text-input
            v-model="pessoa.data_nascimento"
            label="Data de Nascimento"
            type="date"
            cols="12"
            md="6"
            lg="3"
        />


        </v-row>

    </card-formulario>
</template>

<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import {reactive} from "vue";


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
