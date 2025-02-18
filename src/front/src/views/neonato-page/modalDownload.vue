<script setup>
import {onMounted, reactive, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {serviceSaveFilaDownloadExcel} from "@/service/neonato";
import {getOptionsAutocomplete, verificarCamposObrigatorios} from "@/service/common/utils";


const emit                        = defineEmits(['close_modal']);
const disableSaveButton           = ref(false);
const tiutloPatina                = ref("Solicitação Download");
const subtituloPagina             = ref("O arquivo poderá ser baixado no menu de Downloads");
const optionsEventos              = ref();
const optionsAntimicrobianos      = ref();
const optionsMotivoInternacao     = ref();
const optionsCausaObito           = ref();
const optionsSitioCirurgia        = ref();
const optionsSitioColeta          = ref();
const optionsIsolados             = ref();
const optionsMecanismoResistencia = ref();
const optionsPerfilResistencia    = ref();
const camposObrigatorios          = ref(true);

const optionsDesfecho = ref([{
    label: "Óbito",
    value: 1
}, {
    label: "Alta",
    value: 2
}]);


const data = reactive({
    descricao: null,
    filtros  : {
        dataInicial         : null,
        dataFinal           : null,
        eventos             : [],
        medicamentos        : [],
        isolados            : [],
        motivoInternacao    : [],
        causaObito          : [],
        sitioCirurgia       : [],
        sitioColeta         : [],
        perfilResistencia   : [],
        mecanismoResistencia: [],
        desfecho            : null

    }
});


onMounted(async () => {
    loading.show();
    await getOpcoesAutocomplete();
    loading.hide();
});



const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_tipo_evento',
        descColumn: 'descricao',
        tableName : 'tipo_evento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_antimicrobiano',
        descColumn: 'descricao',
        tableName : 'antimicrobiano'
    }), getOptionsAutocomplete({
        idColumn  : 'id_microorganismo',
        descColumn: 'acronimo',
        tableName : 'microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_motivo_internacao',
        descColumn: 'descricao',
        tableName : 'motivo_internacao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_causa_obito',
        descColumn: 'descricao',
        tableName : 'causa_obito'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_cirurgia',
        descColumn: 'descricao',
        tableName : 'sitio_cirurgia'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_coleta',
        descColumn: 'descricao',
        tableName : 'sitio_coleta'
    }), getOptionsAutocomplete({
        idColumn  : 'id_mecanismo_resistencia_microorganismo',
        descColumn: 'descricao',
        tableName : 'mecanismo_resistencia_microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_perfil_resistencia_microorganismo',
        descColumn: 'descricao',
        tableName : 'perfil_resistencia_microorganismo'
    })]);

    optionsEventos.value              = results[0];
    optionsAntimicrobianos.value      = results[1];
    optionsIsolados.value             = results[2];
    optionsMotivoInternacao.value     = results[3];
    optionsCausaObito.value           = results[4];
    optionsSitioCirurgia.value        = results[5];
    optionsSitioColeta.value          = results[6];
    optionsMecanismoResistencia.value = results[7];
    optionsPerfilResistencia.value    = results[8];

    console.log(optionsEventos.value)
};

const handleSave = async () => {
    disableSaveButton.value = true;
    loading.show();

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : data,
        campos: ['descricao']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    await serviceSaveFilaDownloadExcel(data);
    loading.hide();
    emit('close_modal');
    disableSaveButton.value = false;
};


const handleCloseModal = () => {
    emit('close_modal');
};


</script>


<template>
    <v-card>
        <v-row>

            <v-col cols="12" class="pb-0">
                <v-card-title class="d-flex flex-column align-start text-wrap">
                    <span class="text-h5">{{ tiutloPatina }}</span>
                    <small class="text-subtitle-1">{{ subtituloPagina }}</small>
                </v-card-title>
            </v-col>

            <v-col cols="12" class="pl-10 pr-10">

                <v-row class="align-center mt-1">
                    <v-text-field
                        label="Descrição"
                        density="compact"
                        variant="outlined"
                        :error="!data.descricao && !camposObrigatorios"
                        v-model="data.descricao"
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-text-field
                        class="mr-2"
                        label="Data Inicial"
                        type="date"
                        v-model="data.filtros.dataInicial"
                    />

                    <v-text-field
                        label="Data Final"
                        type="date"
                        v-model="data.filtros.dataFinal"
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Procedimento"
                        :items="optionsEventos"
                        v-model="data.filtros.eventos"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Isolado"
                        :items="optionsIsolados"
                        v-model="data.filtros.isolados"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Medicamento"
                        :items="optionsAntimicrobianos"
                        v-model="data.filtros.medicamentos"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Motivo Internação"
                        :items="optionsMotivoInternacao"
                        v-model="data.filtros.motivoInternacao"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Causa Óbito"
                        :items="optionsCausaObito"
                        v-model="data.filtros.causaObito"
                        multiple
                        chips
                    />
                </v-row>


                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Sítio Cirurgia"
                        :items="optionsSitioCirurgia"
                        v-model="data.filtros.sitioCirurgia"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Sítio Coleta"
                        :items="optionsSitioColeta"
                        v-model="data.filtros.sitioColeta"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Mecanismo Resistência"
                        :items="optionsMecanismoResistencia"
                        v-model="data.filtros.mecanismoResistencia"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Perfil Resistência"
                        :items="optionsPerfilResistencia"
                        v-model="data.filtros.perfilResistencia"
                        multiple
                        chips
                    />
                </v-row>

                <v-row class="align-center mt-1">
                    <v-autocomplete
                        label="Desfecho"
                        :items="optionsDesfecho"
                        v-model="data.filtros.desfecho"
                    />
                </v-row>

                <v-divider/>

                <v-row class="align-center mt-1">
                    <v-col cols="12">
                        <v-card-actions>
                            <v-spacer/>
                            <v-btn :disabled="disableSaveButton" class="mr-2" variant="elevated" color="cinzaAzulado" @click="handleCloseModal">Fechar</v-btn>
                            <v-btn :disabled="disableSaveButton" color="azulEscuro" variant="elevated" @click="handleSave">Salvar</v-btn>
                        </v-card-actions>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </v-card>

</template>
