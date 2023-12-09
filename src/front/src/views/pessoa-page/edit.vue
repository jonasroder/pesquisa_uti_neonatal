<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import SelectInput from '@/components/SelectInput.vue';
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {onMounted, reactive} from "vue";
import {serviceAuthenticateTeste} from "@/service/pessoa";


onMounted(async () => {
    pessoa.nome = await serviceAuthenticateTeste()
})

const pessoa = reactive({
    nome: "",
    sobrenome: "",
    sexo: "",
    data_nascimento: "",
    telefone: "",
    email: "",
    estado_civil: "",
    id_profissao: "",
    cpf: "",
    id_etnia: "",
    id_escolaridade: "",
    id_religiao: [],
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
    console.log(pessoa);
};

const handleBack = () => {
    // Implemente a lógica para voltar
    console.log('Retornando da tela de cadastro de usuário.');
};


</script>

<template>
    <card-formulario title="Cadastro de Pessoa"
                     subtitle="Centralizando Informações para Cuidado Integral"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-row>
            <v-col cols="12"
                   sm="4"
                   md="3"
                   justify="center"
                   align="center">
                <AvatarImageInput/>
            </v-col>

            <v-col cols="12"
                   sm="8"
                   md="9">

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

                    <SelectInput
                        label="Etnia"
                        placeholder="Digite ou selecione uma Etnia"
                        idColumn="id_etnia"
                        descColumn="descricao"
                        tableName="etnia"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_etnia"
                        cols="12"
                        md="4"
                    />


                    <SelectInput
                        label="Escolaridade"
                        placeholder="Digite ou selecione uma Escolaridade"
                        idColumn="id_escolaridade"
                        descColumn="descricao"
                        tableName="escolaridade"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_escolaridade"
                        cols="12"
                        md="4"
                    />


                    <SelectInput
                        label="Religião"
                        placeholder="Digite ou selecione uma religião"
                        idColumn="id_religiao"
                        descColumn="descricao"
                        tableName="religiao"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_religiao"
                        cols="12"
                        md="4"
                    />


                    <text-input
                        v-model="pessoa.telefone"
                        label="telefone"
                        type="phone"
                        :rules="['required']"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.email"
                        label="Email"
                        type="email"
                        :rules="['email', 'required']"
                        cols="12"
                        md="4"
                    />

                    <SelectInput
                        label="Profissão"
                        placeholder="Digite ou selecione uma Profissão"
                        idColumn="id_profissao"
                        descColumn="descricao"
                        tableName="profissao"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_profissao"
                        cols="12"
                        md="4"
                    />


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>
