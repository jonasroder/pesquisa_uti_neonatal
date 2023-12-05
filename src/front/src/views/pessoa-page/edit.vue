<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import SelectInput from '@/components/SelectInput.vue';
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {onMounted, reactive, ref} from "vue";
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
    profissao: "",
    cpf: "",
    etnia: "",
    educacao: "",
    religiao: [],
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



const religioes = ref([
    { label: 'Católico', value: 1 },
    { label: 'Evangélico', value: 2 },
    { label: 'Espírita', value: 3 },
    { label: 'Umbandista', value: 4 },
    { label: 'Candomblecista', value: 5 },
    { label: 'Budista', value: 6 },
    { label: 'Judeu', value: 7 },
    { label: 'Muçulmano', value: 8 },
    { label: 'Hindu', value: 9 },
    { label: 'Ateu', value: 10 },
    { label: 'Agnóstico', value: 11 }
]);




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

                    <text-input
                        v-model="pessoa.etnia"
                        label="Etnia"
                        type="text"
                        cols="12"
                        md="4"
                    />


                    <text-input
                        v-model="pessoa.educacao"
                        label="Educação"
                        type="text"
                        cols="12"
                        md="4"
                    />


                    <SelectInput
                        label="Religião"
                        placeholder="Digite ou selecione uma religião"
                        :items="religioes"
                        :multiple="false"
                        v-model="pessoa.religiao"
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

                    <text-input
                        v-model="pessoa.profissao"
                        label="Profissão"
                        type="text"
                        cols="12"
                        md="4"
                    />



                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>
