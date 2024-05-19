<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
//import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceSave, serviceLoad} from "@/service/neonato";
import {
    getIdFromUrl, adicionarParametrosURL, verificarCamposObrigatorios, getOptionsAutocomplete
} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";


const id                 = ref(getIdFromUrl());
const cpfDisabled        = ref(false);
const router             = useRouter();
const camposObrigatorios = ref(true);


const optionsSexo             = ref([]);
const optionsPesoNascimento   = ref([]);
const optionsLocalNascimento  = ref([]);
const optionsMotivoInternacao = ref([]);
const optionsIdadeGestacional = ref([]);
const optionsTipoParto        = ref([]);
const optionsRoturaMembrana   = ref([]);
const optionsSitioMalformacao = ref([]);
const optionsSitioCirurgia    = ref([]);
const optionsCausaObito       = ref([]);


onMounted(async () => {
    loading.show()

    await getOpcoesAutocomplete();

    if (id.value > 0) {
        await getDadosNeonato();
    }

    loading.hide()
});


const getOpcoesAutocomplete = async () => {

    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_sexo',
        descColumn: 'descricao',
        tableName : 'sexo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_peso_nascimento',
        descColumn: 'descricao',
        tableName : 'peso_nascimento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_local_nascimento',
        descColumn: 'descricao',
        tableName : 'local_nascimento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_motivo_internacao',
        descColumn: 'descricao',
        tableName : 'motivo_internacao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_idade_gestacional',
        descColumn: 'descricao',
        tableName : 'idade_gestacional'
    }), getOptionsAutocomplete({
        idColumn  : 'id_tipo_parto',
        descColumn: 'descricao',
        tableName : 'tipo_parto'
    }), getOptionsAutocomplete({
        idColumn  : 'id_rotura_membrana',
        descColumn: 'descricao',
        tableName : 'rotura_membrana'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_malformacao',
        descColumn: 'descricao',
        tableName : 'sitio_malformacao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_cirurgia',
        descColumn: 'descricao',
        tableName : 'sitio_cirurgia'
    }), getOptionsAutocomplete({
        idColumn  : 'id_causa_obito',
        descColumn: 'descricao',
        tableName : 'causa_obito'
    })]);

    optionsSexo.value             = results[0];
    optionsPesoNascimento.value   = results[1];
    optionsLocalNascimento.value  = results[2];
    optionsMotivoInternacao.value = results[3];
    optionsIdadeGestacional.value = results[4];
    optionsTipoParto.value        = results[5];
    optionsRoturaMembrana.value   = results[6];
    optionsSitioMalformacao.value = results[7];
    optionsSitioCirurgia.value    = results[8];
    optionsCausaObito.value       = results[9];
}

const neonato = reactive({
    idNeonato         : null,
    nomeMae           : null,
    prontuario        : null,
    dataNascimento    : null,
    dataInternacao    : null,
    dataDesfecho      : null,
    obito             : false,
    apgar1            : null,
    apgar5            : null,
    pesoGramas        : null,
    idSexo            : null,
    idPesoNascimento  : null,
    idMotivoInternacao: null,
    idLocalNascimento : null,
    idIdadeGestacional: null,
    idTipoParto       : null,
    idRoturaMembrana  : null,
    idSitioMalformacao: null,
    idSitioCirurgia   : null,
    idCausaObito      : null,
    riscoInfeccioso   : false,
    sepseClinica      : false,
});


const handleSave = async () => {
    loading.show()

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : neonato,
        campos: ['prontuario', 'nomeMae']
    }]

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(neonato);

    if (res.id > 0) {
        id.value          = res.id;
        neonato.idNeonato = res.id;
        cpfDisabled.value = true;
        adicionarParametrosURL({id: res.id});
        await getDadosNeonato();
    }

    loading.hide()
};


const getDadosNeonato = async () => {
    const resp = await serviceLoad(id.value)
    Object.assign(neonato, resp)
}


const handleBack = () => {
    router.push({name: 'Neonato-List'});
};

console.log(handleBack);
console.log(handleSave)

const apgarRules = ref([v => {
    if (v === '' || v == null) return true;
    return (Number.isInteger(Number(v))) || 'Apenas números inteiros são permitidos e o valor deve estar entre 0 e 10.';
}, v => {
    if (v === '' || v == null) return true;
    return (v >= 0 && v <= 10) || 'O valor deve estar entre 0 e 10.';
}]);

</script>

<template>
    <card-formulario title="Cadastro de Neonato"
                     subtitle="Você pode editar o formulário a qualquer momento">

        <v-row>
            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="RN de *"
                    type="text"
                    v-model="neonato.nomeMae"
                    :error="!neonato.nomeMae && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Prontuário*"
                    type="text"
                    v-model="neonato.prontuario"
                    :error="!neonato.prontuario && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data de Nascimento"
                    type="date"
                    v-model="neonato.dataNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data de Internação"
                    type="date"
                    v-model="neonato.dataInternacao"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Sexo"
                    :items="optionsSexo"
                    v-model="neonato.idSexo"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Peso ao Nascimento (g)"
                    type="number"
                    v-model="neonato.pesoGramas"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Classificação do peso ao nascer"
                    :items="optionsPesoNascimento"
                    v-model="neonato.idPesoNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Local Nascimento"
                    :items="optionsLocalNascimento"
                    v-model="neonato.idLocalNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Idade Gestacional"
                    :items="optionsIdadeGestacional"
                    v-model="neonato.idIdadeGestacional"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Tipo de Parto"
                    :items="optionsTipoParto"
                    v-model="neonato.idTipoParto"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Bolsa Rota"
                    :items="optionsRoturaMembrana"
                    v-model="neonato.idRoturaMembrana"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="APGAR 1'"
                    type="number"
                    v-model="neonato.apgar1"
                    :rules="apgarRules"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="APGAR 5'"
                    type="number"
                    v-model="neonato.apgar5"
                    :rules="apgarRules"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Causa da Internação"
                    :items="optionsMotivoInternacao"
                    v-model="neonato.idMotivoInternacao"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Sítio Malformação Congênita"
                    :items="optionsSitioMalformacao"
                    v-model="neonato.idSitioMalformacao"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Sítio Cirurgia"
                    :items="optionsSitioCirurgia"
                    v-model="neonato.idSitioCirurgia"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data do Desfecho"
                    type="date"
                    v-model="neonato.dataDesfecho"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Causa do Óbito"
                    :items="optionsCausaObito"
                    v-model="neonato.idCausaObito"
                />
            </v-col>

        </v-row>

        <v-row>
            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-radio-group v-model="neonato.riscoInfeccioso" inline label="Risco Infeccioso">
                    <v-radio label="Sim" :value="true"/>
                    <v-radio label="Não" :value="false"/>
                </v-radio-group>
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-radio-group v-model="neonato.sepseClinica" inline label="Sepse Clínica">
                    <v-radio label="Sim" :value="true"/>
                    <v-radio label="Não" :value="false"/>
                </v-radio-group>
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-radio-group v-model="neonato.obito" inline label="Óbito">
                    <v-radio label="Sim" :value="true"/>
                    <v-radio label="Não" :value="false"/>
                </v-radio-group>
            </v-col>
        </v-row>

    </card-formulario>
</template>
