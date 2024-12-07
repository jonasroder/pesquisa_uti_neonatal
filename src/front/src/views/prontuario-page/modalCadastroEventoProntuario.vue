<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {onMounted, reactive, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {getOptionsAutocomplete, verificarCamposObrigatorios} from "@/service/common/utils";
import {serviceSaveEvento, serviceExcluirEvento} from "@/service/prontuario";


const props = defineProps({
    idNeonato        : {
        type   : [String, Number],
        default: ''
    },
    eventoSelecionado: {type: Object}
});


const optionsTipoEvento       = ref();
const optionsAntimicrobiano   = ref();
const optionsSitioCirurgia    = ref();
const optionsSitioColeta      = ref();
const optionsViaAdministracao = ref();
const emit                    = defineEmits(['close_modal', 'saved']);
const camposObrigatorios      = ref(true);
const idTipoEvento            = ref();
const dataInicio              = ref();
const tiutloPatina            = ref("Cadastrar novo evento");
const subtituloPagina         = ref("Adicione um novo evento na agenda");


const data = reactive({
    idEvento                : null,
    idNeonato               : null,
    dataEvento              : null,
    dataFim                 : null,
    idTipoEvento            : null,
    idEventoEntidade        : null,
    tipoEntidade            : null,
    idEntidade              : null,
    diaInteiro              : null,
    observacao              : null,
    is_active               : true,
    idEventoViaAdministracao: null,
    idViaAdministracao      : null,
});


onMounted(async () => {
    loading.show();

    await Promise.all([getOptionsTipoEvento(), getOptionsAntimicrobiano(), getOptionsSitioCirurgia(), getOptionsSitioColeta(), getOptionsViaAdministracao(),]);

    if (props.eventoSelecionado) {
        Object.assign(data, props.eventoSelecionado);
        dataInicio.value = formatarDataISO(data.dataEvento);
    }

    if (data.idEvento) {
        tiutloPatina.value    = "Atualizar Evento";
        subtituloPagina.value = "Salve para atualizar os dados";
    }

    loading.hide();
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


const getOptionsViaAdministracao = async () => {
    optionsViaAdministracao.value = await getOptionsAutocomplete({
        idColumn  : 'id_via_administracao',
        descColumn: 'descricao',
        tableName : 'via_administracao'
    });
}


const handleSave = async () => {
    camposObrigatorios.value = true;
    data.tipoEntidade        = definirTipoEntidade(data.idTipoEvento);
    data.dataEvento          = dataInicio.value;

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


const excluirEvento = async () => {
    loading.show();
    const idEvento = data.idEvento;

    if (idEvento) await serviceExcluirEvento(idEvento)

    emit('close_modal');
    loading.hide();
}


const formatarDataISO = (data) => {
    if (!data) return "";

    // Verifica se a data já está no formato "YYYY-MM-DD"
    const regexISODate = /^\d{4}-\d{2}-\d{2}$/;
    if (regexISODate.test(data)) {
        return data; // Retorna a data sem transformações
    }

    // Converte para o formato "YYYY-MM-DD" se necessário
    const date = new Date(data);
    return date.toLocaleDateString("sv-SE"); // Formato compatível com "YYYY-MM-DD"
};



</script>


<template>
    <card-formulario :title="tiutloPatina"
                     :subtitle="subtituloPagina">

        <v-col cols="12" class="pa-1">

            <v-row class="align-center mt-1">
                <v-col
                    :cols="data.idEvento || (data.idTipoEvento === 9 || data.idTipoEvento === 10) ? 12 : 6"
                    class="pb-0"
                >
                    <v-text-field
                        label="Data"
                        type="date"
                        density="compact"
                        variant="outlined"
                        v-model="dataInicio"
                    />
                </v-col>

                <v-col cols="6" class="pb-0" v-if="!data.idEvento && data.idTipoEvento !== 9 && data.idTipoEvento !== 10">
                    <v-text-field
                        label="Data de Término"
                        type="date"
                        density="compact"
                        variant="outlined"
                        v-model="data.dataFim"
                    />
                </v-col>
            </v-row>


            <v-row>
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


                <v-col cols="12" class="pb-0" v-if="data.idTipoEvento === 1">
                    <v-autocomplete
                        label="Via Administração"
                        :items="optionsViaAdministracao"
                        v-model="data.idViaAdministracao"
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

                <v-col cols="12">
                    <v-card-actions>
                        <v-spacer/>
                        <v-btn class="mr-2" variant="elevated" color="cinzaAzulado" @click="handleCloseModal">Fechar</v-btn>
                        <v-btn color="azulEscuro" variant="elevated" @click="handleSave">Salvar</v-btn>
                        <v-btn v-if="data.idEvento" class="mr-2" variant="elevated" color="red" @click="excluirEvento">Excluir</v-btn>
                    </v-card-actions>
                </v-col>
            </v-row>
        </v-col>
    </card-formulario>

</template>