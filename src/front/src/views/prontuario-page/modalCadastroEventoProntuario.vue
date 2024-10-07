<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {onMounted, reactive, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {getOptionsAutocomplete, verificarCamposObrigatorios} from "@/service/common/utils";
import {serviceSaveEvento} from "@/service/prontuario";

const props = defineProps({
    idNeonato        : {
        type   : [String, Number],
        default: ''
    },
    eventoSelecionado: {type: Object}
});


const optionsTipoEvento     = ref();
const optionsAntimicrobiano = ref();
const optionsSitioCirurgia  = ref();
const optionsSitioColeta = ref();
const emit                  = defineEmits(['close_modal', 'saved']);
const camposObrigatorios    = ref(true);
const idTipoEvento          = ref();


const data = reactive({
    idEvento        : null,
    idNeonato       : null,
    dataEvento      : null,
    idTipoEvento    : null,
    idEventoEntidade: null,
    tipoEntidade    : null,
    idEntidade      : null,
    diaInteiro      : null,
    observacao      : null,
    is_active       : true,
});


onMounted(async () => {
    loading.show()

    await Promise.all([getOptionsTipoEvento(), getOptionsAntimicrobiano(), getOptionsSitioCirurgia(), getOptionsSitioColeta()]);

    if (props.eventoSelecionado) {
        Object.assign(data, props.eventoSelecionado);
    }

    loading.hide()
});


const getOptionsTipoEvento = async () => {
    optionsTipoEvento.value = await getOptionsAutocomplete({
        idColumn  : 'id_tipo_evento',
        descColumn: 'descricao',
        tableName : 'tipo_evento'
    });
}


const getOptionsAntimicrobiano = async () => {
    optionsAntimicrobiano.value = await getOptionsAutocomplete({
        idColumn  : 'id_antimicrobiano',
        descColumn: 'descricao',
        tableName : 'antimicrobiano'
    });
}


const getOptionsSitioCirurgia = async () => {
    optionsSitioCirurgia.value = await getOptionsAutocomplete({
        idColumn  : 'id_sitio_cirurgia',
        descColumn: 'descricao',
        tableName : 'sitio_cirurgia'
    });
}


const getOptionsSitioColeta = async () => {
    optionsSitioColeta.value = await getOptionsAutocomplete({
        idColumn  : 'id_sitio_coleta',
        descColumn: 'descricao',
        tableName : 'sitio_coleta'
    });
}


const handleSave = async () => {
    camposObrigatorios.value = true;
    data.tipoEntidade        = definirTipoEntidade(data.idTipoEvento);
    debugger

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    await serviceSaveEvento(data);
    emit('close_modal');
};


const definirTipoEntidade = (idTipoEvento) => {
    switch (idTipoEvento) {
        case 1:
            return "antimicrobiano";
        case 9:
            return "sitio_cirurgia";
        case 10:
            return "sitio_coleta";
        default:
            return null;
    }
};


const verificacoes = [{
    dados : data,
    campos: ['idNeonato', 'dataEvento', 'idTipoEvento']
}];


const handleCloseModal = () => {
    emit('close_modal');
};

</script>


<template>
    <card-formulario title="Cadastrar novo evento"
                     subtitle="Adicione um novo evento na agenda">

        <v-row>
            <v-col cols="12">
                <v-col cols="12" class="pb-0">
                    <v-autocomplete
                        label="Tipo do Evento"
                        :items="optionsTipoEvento"
                        :error="!idTipoEvento && !camposObrigatorios"
                        v-model="data.idTipoEvento"
                        @update:modelValue="data.idEntidade = null"
                    />
                </v-col>

                <v-col cols="12" class="pb-0" v-if="data.idTipoEvento === 1">
                    <v-autocomplete
                        label="Antibiótico"
                        :items="optionsAntimicrobiano"
                        v-model="data.idEntidade"
                    />
                </v-col>

                <v-col cols="12" class="pb-0" v-if="data.idTipoEvento === 9">
                    <v-autocomplete
                        label="Sítio"
                        :items="optionsSitioCirurgia"
                        v-model="data.idEntidade"
                    />
                </v-col>

                <v-col cols="12" class="pb-0" v-if="data.idTipoEvento === 10">
                    <v-autocomplete
                        label="Sítio"
                        :items="optionsSitioColeta"
                        v-model="data.idEntidade"
                    />
                </v-col>

                <v-col cols="12">
                    <v-textarea
                        label="Observação"
                        v-model="data.observacao"
                    />
                </v-col>

                <v-divider/>

                <v-card-actions>
                    <v-spacer/>
                    <v-btn class="mr-2" variant="elevated" color="cinzaAzulado" @click="handleCloseModal">Fechar</v-btn>
                    <v-btn color="azulEscuro" variant="elevated" @click="handleSave">Salvar</v-btn>
                </v-card-actions>
            </v-col>
        </v-row>
    </card-formulario>

</template>