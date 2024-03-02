<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import AvatarImageInput from '@/components/AvatarImageInput.vue';
import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceSave, serviceLoad, getEnderecoByCep} from "@/service/paciente";
import {
    getIdFromUrl,
    limparMascara,
    adicionarParametrosURL,
    verificarCamposObrigatorios,
    getOptionsAutocomplete,
    getMaskSettings
} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";


const id          = ref(getIdFromUrl());
const cpfDisabled = ref(false);
const router      = useRouter();
const camposObrigatorios = ref(true);

const maskCelular = reactive(getMaskSettings({maskType: 'celular'}));
const maskCPF     = reactive(getMaskSettings({maskType: 'cpf'}));
const maskCEP     = reactive(getMaskSettings({maskType: 'cep'}));


const optionsProfissao     = ref([]);
const optionsPlanoSaude    = ref([]);
const optionsEstadoCivil   = ref([]);
const optionsSexo          = ref([]);
const optionsEtnia         = ref([]);
const optionsEscolariadade = ref([]);
const optionsReligiao      = ref([]);
const optionsUF            = ref([]);


onMounted(async () => {
    loading.show()

    await getOpcoesAutocomplete();

    if (id.value > 0) {
        cpfDisabled.value = true;
        const data        = await serviceLoad(id.value);

        Object.assign(paciente, data.pacienteResponse);
        Object.assign(endereco, data.enderecoResponse);
        foto_perfil.value = data.fotoPerfilResponse?.caminho ?? null;
    }

    loading.hide()
});


const getOpcoesAutocomplete = async () => {

    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_profissao',
        descColumn: 'descricao',
        tableName : 'profissao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_plano_saude',
        descColumn: 'descricao',
        tableName : 'plano_saude'
    }), getOptionsAutocomplete({
        idColumn  : 'id_estado_civil',
        descColumn: 'descricao',
        tableName : 'estado_civil'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sexo',
        descColumn: 'descricao',
        tableName : 'sexo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_etnia',
        descColumn: 'descricao',
        tableName : 'etnia'
    }), getOptionsAutocomplete({
        idColumn  : 'id_escolaridade',
        descColumn: 'descricao',
        tableName : 'escolaridade'
    }), getOptionsAutocomplete({
        idColumn  : 'id_religiao',
        descColumn: 'descricao',
        tableName : 'religiao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_uf',
        descColumn: 'sigla',
        tableName : 'uf'
    })]);

    optionsProfissao.value     = results[0];
    optionsPlanoSaude.value    = results[1];
    optionsEstadoCivil.value   = results[2];
    optionsSexo.value          = results[3];
    optionsEtnia.value         = results[4];
    optionsEscolariadade.value = results[5];
    optionsReligiao.value      = results[6];
    optionsUF.value            = results[7];
}


const foto_perfil = ref();

const paciente = reactive({
    id_paciente       : null,
    nome              : null,
    sobrenome         : null,
    data_nascimento   : null,
    telefone_1        : null,
    telefone_2        : null,
    email             : null,
    cpf               : null,
    naturalidade      : null,
    nome_pai          : null,
    nome_mae          : null,
    nome_conjuge      : null,
    numero_plano_saude: null,
    indicacao         : null,
    observacao        : null,
    id_profissao      : null,
    id_plano_saude    : null,
    id_estado_civil   : null,
    id_sexo           : null,
    id_etnia          : null,
    id_escolaridade   : null,
    id_religiao       : null
});

const endereco = reactive({
    logradouro : null,
    numero     : null,
    complemento: null,
    bairro     : null,
    cidade     : null,
    id_uf      : null,
    cep        : null,
    referencia : null
})


const handleSave = async () => {
    loading.show()

    camposObrigatorios.value = true;

    endereco.cep        = limparMascara(endereco.cep);
    paciente.cpf        = limparMascara(paciente.cpf);
    paciente.telefone_1 = limparMascara(paciente.telefone_1);
    paciente.telefone_2 = limparMascara(paciente.telefone_2);

    const verificacoes       = [{
        dados : paciente,
        campos: ['nome', 'cpf', 'telefone_1']
    }]

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const data = {
        pacienteRequest  : paciente,
        enderecoRequest  : endereco,
        fotoPerfilRequest: foto_perfil.value
    }

    const res = await serviceSave(data);
    id.value  = res.id;
    if (id.value > 0) {
        paciente.id_paciente = res.id;
        cpfDisabled.value    = true;
        adicionarParametrosURL({id: res.id});
    }

    loading.hide()
};


const handleBack = () => {
    router.push({name: 'Paciente-List'});
};


const buscarEnderecoCEP = async () => {
    let respEndereco = {};
    if (endereco.cep.length >= 8) {
        respEndereco = await getEnderecoByCep(endereco.cep);
    } else {
        setNotification("O CEP é invalido", "error");
    }

    const ufObjeto = optionsUF.value.find(option => option.label == respEndereco.uf);
    if (ufObjeto) {
        endereco.id_uf = ufObjeto.value;
    }

    endereco.cep        = respEndereco.cep;
    endereco.logradouro = respEndereco.logradouro;
    endereco.bairro     = respEndereco.bairro;
    endereco.cidade     = respEndereco.localidade;
};

</script>

<template>
    <card-formulario title="Cadastro de Paciente"
                     subtitle="Centralizando Informações para Cuidado Integral"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-row>
            <v-col cols="12" sm="4" md="3" justify="center" align="center">
                <AvatarImageInput v-model="foto_perfil"/>
            </v-col>

            <v-col cols="12" sm="8" md="9">
                <v-row class="justify-end">

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nome*"
                            type="text"
                            v-model="paciente.nome"
                            :error="!paciente.nome && !camposObrigatorios"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Sobrenome*"
                            type="text"
                            v-model="paciente.sobrenome"
                            :error="!paciente.sobrenome && !camposObrigatorios"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Sexo"
                            :items="optionsSexo"
                            v-model="paciente.id_sexo"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Data de Nascimento"
                            type="date"
                            v-model="paciente.data_nascimento"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="CPF*"
                            type="text"
                            v-maska:[maskCPF]
                            :disabled="cpfDisabled"
                            v-model="paciente.cpf"
                            :error="!paciente.cpf  && !camposObrigatorios"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Telefone 1*"
                            type="phone"
                            v-maska:[maskCelular]
                            v-model="paciente.telefone_1"
                            :error="!paciente.telefone_1 && !camposObrigatorios"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Telefone 2"
                            type="phone"
                            v-maska:[maskCelular]
                            v-model="paciente.telefone_2"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Email"
                            type="email"
                            v-model="paciente.email"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Naturalidade"
                            type="text"
                            v-model="paciente.naturalidade"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nome do Conjuge"
                            type="text"
                            v-model="paciente.nome_conjuge"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nome do Pai"
                            type="text"
                            v-model="paciente.nome_pai"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nome da Mãe"
                            type="text"
                            v-model="paciente.nome_mae"
                        />
                    </v-col>

                </v-row>
            </v-col>

            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Quem Indicou"
                            type="text"
                            v-model="paciente.indicacao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nº Plano Saúde"
                            type="text"
                            v-model="paciente.numero_plano_saude"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Plano de Saúde"
                            :items="optionsPlanoSaude"
                            v-model="paciente.id_plano_saude"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Etnia"
                            :items="optionsEtnia"
                            v-model="paciente.id_etnia"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Escolaridade"
                            :items="optionsEscolariadade"
                            v-model="paciente.id_escolaridade"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Religião"
                            :items="optionsReligiao"
                            v-model="paciente.id_religiao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Profissão"
                            :items="optionsProfissao"
                            v-model="paciente.id_profissao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="Estado Civil"
                            :items="optionsEstadoCivil"
                            v-model="paciente.id_estado_civil"
                        />
                    </v-col>

                </v-row>
            </v-col>

            <v-col cols="12">
                <v-textarea
                    label="Observações"
                    v-model="paciente.observacao"
                />
            </v-col>

            <v-col cols="12">
                <v-row align="center">
                    <v-col cols="auto">
                        <i class="fa fa-home fa-lg" aria-hidden="true"></i>
                    </v-col>
                    <v-col>
                        <span class="text-h6">Endereço</span>
                    </v-col>
                </v-row>
                <v-divider class="border-opacity-100" color="primary"/>
            </v-col>

            <v-col cols="12">
                <v-row>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="CEP"
                            type="text"
                            append-inner-icon="fa-solid fa-search"
                            @click:appendInner="buscarEnderecoCEP"
                            v-maska:[maskCEP]
                            v-model="endereco.cep"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Logradouro"
                            type="text"
                            v-model="endereco.logradouro"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Número"
                            type="number"
                            v-model="endereco.numero"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Complemento"
                            type="text"
                            v-model="endereco.complemento"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Bairro"
                            type="text"
                            v-model="endereco.bairro"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Cidade"
                            type="text"
                            v-model="endereco.cidade"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-autocomplete
                            label="UF"
                            :items="optionsUF"
                            v-model="endereco.id_uf"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Referência"
                            type="text"
                            v-model="endereco.referencia"
                        />
                    </v-col>

                </v-row>
            </v-col>

        </v-row>
    </card-formulario>
</template>
