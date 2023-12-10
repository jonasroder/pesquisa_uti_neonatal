<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import SelectInput from '@/components/SelectInput.vue';
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {setNotification} from "@/service/notificationService";
import {onMounted, reactive} from "vue";
import {serviceSave, getEnderecoByCep} from "@/service/pessoa";


onMounted(async () => {
   console.log("oi");
})


const pessoa = reactive({
    foto_perfil: "",
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
    id_religiao: "",
    id_sexo: "",
    id_tipo_pessoa: "",
    endereco: {
        rua: "",
        numero: "",
        complemento: "",
        bairro: "",
        cidade: "",
        estado: "",
        cep: ""
    }
});


const handleSave = async () => {

   const res = await serviceSave(pessoa, 'insert');

    console.log(res);
};

const handleBack = () => {
    // Implemente a lógica para voltar
    console.log('Retornando da tela de cadastro de usuário.');
};


const handleAppendIconClick = async () => {
    let respEndereco = {};
    if (pessoa.endereco.cep.length >= 8) {
        respEndereco = await getEnderecoByCep(pessoa.endereco.cep);
    } else {
        setNotification("O CEP é invalido", "error");
    }

    pessoa.endereco.cep = respEndereco.cep;
    pessoa.endereco.rua = respEndereco.logradouro;
    pessoa.endereco.bairro = respEndereco.bairro;
    pessoa.endereco.cidade = respEndereco.localidade;
    pessoa.endereco.estado = respEndereco.uf;

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

                <AvatarImageInput v-model="pessoa.foto_perfil"/>
            </v-col>

            <v-col cols="12"
                   sm="8"
                   md="9">


                <v-row class="justify-end">

                    <text-input
                        v-model="pessoa.nome"
                        label="Nome"
                        type="text"
                        cols="12"
                        md="6"
                        lg="6"
                        xl="6"
                    />


                    <text-input
                        v-model="pessoa.sobrenome"
                        label="Sobrenome"
                        type="text"
                        cols="12"
                        md="6"
                        lg="6"
                        xl="6"
                    />


                    <SelectInput
                        label="Sexo"
                        placeholder="Digite ou selecione o Sexo"
                        idColumn="id_sexo"
                        descColumn="descricao"
                        tableName="sexo"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_sexo"
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
                        v-model="pessoa.cpf"
                        label="CPF"
                        type="text"
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
                        md="6"
                        lg="6"
                        xl="6"
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


                    <SelectInput
                        label="Estado Civil"
                        placeholder="Digite ou selecione o Estado Civil"
                        idColumn="id_estado_civil"
                        descColumn="descricao"
                        tableName="estado_civil"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_profissao"
                        cols="12"
                        md="4"
                    />


                    <SelectInput
                        label="Tipo de Pessoa"
                        placeholder="Digite ou selecione o Tipo"
                        idColumn="id_tipo_pessoa"
                        descColumn="descricao"
                        tableName="tipos_pessoa"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.id_tipo_pessoa"
                        cols="12"
                        md="4"
                    />

                </v-row>
            </v-col>

            <v-col cols="12">

                <v-row align="center">
                    <v-col cols="auto">
                        <i class="fa fa-home fa-lg"
                           aria-hidden="true"></i>
                    </v-col>
                    <v-col>
                        <span class="text-h6">Endereço</span>
                    </v-col>
                </v-row>

                <v-divider class="border-opacity-100" color="primary"/>

            </v-col>

            <v-col cols="12">
                <v-row>

                    <text-input
                        v-model="pessoa.endereco.rua"
                        label="Rua"
                        type="text"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.endereco.numero"
                        label="Número"
                        type="number"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.endereco.complemento"
                        label="Complemento"
                        type="text"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.endereco.bairro"
                        label="Bairro"
                        type="text"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.endereco.cidade"
                        label="Bairro"
                        type="text"
                        cols="12"
                        md="4"
                    />

                    <SelectInput
                        label="Estado"
                        placeholder="Digite ou selecione a UF"
                        idColumn="id_uf"
                        descColumn="sigla"
                        tableName="uf"
                        :is_active="true"
                        :multiple="false"
                        v-model="pessoa.endereco.estado"
                        cols="12"
                        md="4"
                    />

                    <text-input
                        v-model="pessoa.endereco.cep"
                        label="CEP"
                        type="text"
                        appendInnerIcon="fa-solid fa-search"
                        :rules="['required', 'cep']"
                        cols="12"
                        md="4"
                        @click:append-inner-icon="handleAppendIconClick"
                    />

                </v-row>
            </v-col>


        </v-row>
    </card-formulario>
</template>
