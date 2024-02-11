<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import SelectInput from '@/components/SelectInput.vue';
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceSave, serviceLoad, getEnderecoByCep} from "@/service/paciente";
import {getIdFromUrl, limparMascara, adicionarParametrosURL, verificarCamposObrigatorios} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";


const id = ref(getIdFromUrl());
const cpfDisabled = ref(false);
const router = useRouter();

onMounted(async () => {
    loading.show()

    if (id.value > 0) {
        cpfDisabled.value = true;
        const data = await serviceLoad(id.value);
        console.log(data)
        Object.assign(paciente, data);
        if (data.endereco != null) {
            Object.assign(endereco, data.endereco[0]);
        } else {
            Object.assign(endereco, []);
        }
    }

    loading.hide()
});


const paciente = reactive({
    id_paciente: "",
    nome: "",
    sobrenome: "",
    data_nascimento: "",
    telefone_1: "",
    telefone_2: "",
    email: "",
    cpf: "",
    naturalidade: "",
    nome_pai: "",
    nome_mae: "",
    nome_conjuge: "",
    numero_plano_saude: "",
    indicacao: "",
    observacao: "",
    id_profissao: "",
    id_plano_saude: "",
    id_estado_civil: "",
    id_sexo: "",
    id_etnia: "",
    id_escolaridade: "",
    id_religiao: "",
    foto_perfil: "",
    endereco: []
});

const endereco = reactive({
    logradouro: "",
    numero: "",
    complemento: "",
    bairro: "",
    cidade: "",
    id_uf: "",
    cep: "",
    referencia: ""
})


const handleSave = async () => {
    loading.show()

    if (endereco) {
        endereco.cep = limparMascara(endereco.cep);
        paciente.endereco = [];
        paciente.endereco.push(endereco)
    }

    paciente.cpf = limparMascara(paciente.cpf);
    paciente.telefone_1 = limparMascara(paciente.telefone_1);
    paciente.telefone_2 = limparMascara(paciente.telefone_2);


    const camposObrigatorios = [{ nome: "Nome" }, { cpf: "CPF" }, { telefone_1: "Telefone" }];

    if (!verificarCamposObrigatorios(paciente, camposObrigatorios)) {
        loading.hide();
        return;
    }


    if (id.value > 0) {
       await serviceSave(paciente, 'update');
    } else {
        const res = await serviceSave(paciente, 'insert');
        id.value = res.id;
        if (id.value > 0) {
            paciente.id_paciente = res.id;
            cpfDisabled.value = true;
            adicionarParametrosURL({id : res.id});
        }
    }

    loading.hide()
};


const handleBack = () => {
    router.push({ name: 'Paciente-List' });
};


const handleAppendIconClick = async () => {
    let respEndereco = {};
    if (endereco.cep.length >= 8) {
        respEndereco = await getEnderecoByCep(endereco.cep);
    } else {
        setNotification("O CEP é invalido", "error");
    }

    endereco.cep = respEndereco.cep;
    endereco.logradouro = respEndereco.logradouro;
    endereco.bairro = respEndereco.bairro;
    endereco.cidade = respEndereco.localidade;
};

</script>

<template>
    <card-formulario title="Cadastro de Paciente"
                     subtitle="Centralizando Informações para Cuidado Integral"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-row>
            <v-col cols="12"
                   sm="4"
                   md="3"
                   justify="center"
                   align="center">

                <AvatarImageInput v-model="paciente.foto_perfil"/>
            </v-col>

            <v-col cols="12"
                   sm="8"
                   md="9">


                <v-row class="justify-end">

                    <text-input
                        v-model="paciente.nome"
                        label="Nome*"
                        type="text"
                    />


                    <text-input
                        v-model="paciente.sobrenome"
                        label="Sobrenome"
                        type="text"
                    />


                    <SelectInput
                        label="Sexo"
                        placeholder="Digite ou selecione o Sexo"
                        idColumn="id_sexo"
                        descColumn="descricao"
                        tableName="sexo"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_sexo"
                    />


                    <text-input
                        v-model="paciente.data_nascimento"
                        label="Data de Nascimento"
                        type="date"
                    />


                    <text-input
                        v-model="paciente.cpf"
                        label="CPF*"
                        :disabled="cpfDisabled"
                        type="text"
                        mask="cpf"
                    />


                    <text-input
                        v-model="paciente.telefone_1"
                        label="telefone 1"
                        type="phone"
                        :rules="['required']"
                        mask="telefone"
                    />

                    <text-input
                        v-model="paciente.telefone_2"
                        label="telefone 2"
                        mask="telefone"
                        type="phone"
                    />

                    <text-input
                        v-model="paciente.email"
                        label="Email"
                        type="email"
                        :rules="['email', 'required']"
                    />

                    <text-input
                        v-model="paciente.naturalidade"
                        label="Naturalidade"
                        type="text"
                    />

                    <text-input
                        v-model="paciente.nome_conjuge"
                        label="Nome do Conjuge"
                        type="text"
                    />

                    <text-input
                        v-model="paciente.nome_pai"
                        label="Nome do Pai"
                        type="text"
                    />

                    <text-input
                        v-model="paciente.nome_mae"
                        label="Nome da Mãe"
                        type="text"
                    />

                    <text-input
                        v-model="paciente.indicacao"
                        label="Quem Indicou"
                        type="text"
                    />

                    <text-input
                        v-model="paciente.numero_plano_saude"
                        label="Nº Plano Saúde"
                        type="text"
                    />

                    <SelectInput
                        label="Plano de Saúde"
                        placeholder="Digite ou selecione uma Plano de Saúde"
                        idColumn="id_plano_saude"
                        descColumn="descricao"
                        tableName="plano_saude"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_plano_saude"
                    />

                    <SelectInput
                        label="Etnia"
                        placeholder="Digite ou selecione uma Etnia"
                        idColumn="id_etnia"
                        descColumn="descricao"
                        tableName="etnia"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_etnia"
                    />

                    <SelectInput
                        label="Escolaridade"
                        placeholder="Digite ou selecione uma Escolaridade"
                        idColumn="id_escolaridade"
                        descColumn="descricao"
                        tableName="escolaridade"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_escolaridade"
                    />


                    <SelectInput
                        label="Religião"
                        placeholder="Digite ou selecione uma religião"
                        idColumn="id_religiao"
                        descColumn="descricao"
                        tableName="religiao"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_religiao"
                    />


                    <SelectInput
                        label="Profissão"
                        placeholder="Digite ou selecione uma Profissão"
                        idColumn="id_profissao"
                        descColumn="descricao"
                        tableName="profissao"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_profissao"
                    />


                    <SelectInput
                        label="Estado Civil"
                        placeholder="Digite ou selecione o Estado Civil"
                        idColumn="id_estado_civil"
                        descColumn="descricao"
                        tableName="estado_civil"
                        :is_active="true"
                        :multiple="false"
                        v-model="paciente.id_estado_civil"
                    />

                </v-row>
            </v-col>

            <v-col cols="12">
                <v-textarea
                    label="Observações"
                    variant="outlined"
                    color="primary"
                    v-model="paciente.observacao"
                />
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

                <v-divider class="border-opacity-100"
                           color="primary"/>

            </v-col>

            <v-col cols="12">
                <v-row>

                    <text-input
                        v-model="endereco.cep"
                        label="CEP"
                        type="text"
                        appendInnerIcon="fa-solid fa-search"
                        :rules="['required', 'cep']"
                        mask="cep"
                        @click:append-inner-icon="handleAppendIconClick"
                    />

                    <text-input
                        v-model="endereco.logradouro"
                        label="Rua"
                        type="text"
                    />

                    <text-input
                        v-model="endereco.numero"
                        label="Número"
                        type="number"
                    />

                    <text-input
                        v-model="endereco.complemento"
                        label="Complemento"
                        type="text"
                    />

                    <text-input
                        v-model="endereco.bairro"
                        label="Bairro"
                        type="text"
                    />

                    <text-input
                        v-model="endereco.cidade"
                        label="Bairro"
                        type="text"
                    />

                    <SelectInput
                        label="UF"
                        placeholder="Digite ou selecione a UF"
                        v-model="endereco.id_uf"
                        idColumn="id_uf"
                        descColumn="sigla"
                        tableName="uf"
                        :is_active="true"
                        :multiple="false"
                    />

                    <text-input
                        v-model="endereco.referencia"
                        label="Referência"
                        type="text"
                    />


                </v-row>
            </v-col>


        </v-row>
    </card-formulario>
</template>
