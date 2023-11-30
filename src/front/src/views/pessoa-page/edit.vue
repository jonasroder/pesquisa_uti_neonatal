<template>
    <card-formulario title="Cadastro de Usuário" @handleSave="handleSave" @handleBack="handleBack">
        <v-text-field label="Nome" placeholder="Digite seu nome"></v-text-field>
        <v-text-field label="Email" placeholder="Digite seu email"></v-text-field>
        <v-text-field label="Email" placeholder="Digite seu email"></v-text-field>
        <v-text-field label="Email" placeholder="Digite seu email"></v-text-field>
        <v-text-field label="Email" placeholder="Digite seu email"></v-text-field>
        <!-- Adicione mais campos conforme necessário -->
    </card-formulario>
</template>

<script setup>
import CardFormulario from "@/components/CardFormulario.vue";

const handleSave = () => {
    // Implemente a lógica de salvamento do formulário aqui
    console.log('Formulário de usuário salvo!');
};

const handleBack = () => {
    // Implemente a lógica para voltar
    console.log('Retornando da tela de cadastro de usuário.');
};
</script>
