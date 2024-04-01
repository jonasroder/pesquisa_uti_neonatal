<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {onMounted, reactive, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {
    formatarTelefone, getOptionsAutocomplete, getScreenSize, verificarCamposObrigatorios
} from "@/service/common/utils";
import defaultImagePath from "@/assets/no_image.png";
import PacienteForm from '@/views/paciente-page/edit.vue';
import {serviceGetInfoBasica} from "@/service/paciente";
import {serviceSave} from "@/service/agenda";

const props = defineProps({
    usuarioAgendaSelecionado: {
        type   : [String, Number],
        default: ''
    },
    eventoSelecionado       : {type: Object}
});


const options_tipo_evento         = ref();
const options_cpf                 = ref();
const options_status_agenda       = ref();
const modalCadastroRapidoPaciente = ref(false);
const tamanhoModal                = ref("");
const emit                        = defineEmits(['close_modal', 'saved']);
const camposObrigatorios          = ref(true);


const paciente = ref({
    foto_perfil: null,
    nome       : null,
    idade      : null,
    telefone_1 : null,
    telefone_2 : null,
    plano_saude: null,
});


const agenda = reactive({
    id_evento       : null,
    id_tipo_evento  : null,
    id_usuario      : null,
    id_status_agenda: 2,
    id_paciente     : null,
    data_evento     : null,
    hora_inicio     : null,
    hora_fim        : null,
    dia_inteiro     : null,
    url             : null,
    descricao       : null,
    is_active       : true,
});


onMounted(async () => {
    loading.show()
    await getAutoCompleteOptions();

    if (props.eventoSelecionado) {
        Object.assign(agenda, props.eventoSelecionado);
        await buscarPacienteSelecionado(agenda.id_paciente);
    }

    loading.hide()
});


const getAutoCompleteOptions = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_tipo_evento_agenda',
        descColumn: 'nome',
        tableName : 'tipo_evento_agenda'
    }), getOptionsAutocomplete({
        idColumn  : 'id_paciente',
        descColumn: 'cpf',
        tableName : 'paciente'
    }), getOptionsAutocomplete({
        idColumn  : 'id_status_agenda',
        descColumn: 'nome',
        tableName : 'status_agenda'
    })]);

    options_tipo_evento.value   = results[0];
    options_cpf.value           = results[1];
    options_status_agenda.value = results[2];
}


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const handleSave = async () => {
    camposObrigatorios.value = true;
    agenda.id_usuario        = props.usuarioAgendaSelecionado;

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const data = agenda;
    await serviceSave(data);
    emit('close_modal');
};


const verificacoes = [{
    dados : agenda,
    campos: ['id_usuario', 'data_evento', 'id_tipo_evento']
}];


const handleCloseModal = () => {
    emit('close_modal');
};


const abrirModalAdicionarPaciente = () => {
    modalCadastroRapidoPaciente.value = true;
    const screenWidth                 = getScreenSize().width;

    if (screenWidth < 600) {
        tamanhoModal.value = "90%";
    } else if (screenWidth >= 600 && screenWidth <= 1200) {
        tamanhoModal.value = '80%';
    } else {
        tamanhoModal.value = '60%';
    }
};


const fecharModalCadastroRapidoPaciente = async () => {
    await getAutoCompleteOptions();
    modalCadastroRapidoPaciente.value = false
}


const buscarPacienteSelecionado = async (id_paciente) => {
    if (!id_paciente) {
        paciente.value = {};
        return;
    }
    paciente.value = await serviceGetInfoBasica(id_paciente)
}

</script>


<template>
    <card-formulario title="Cadastrar novo evento"
                     subtitle="Adicione um novo evento na agenda"
                     :isModal="true"
                     @handleSave="handleSave"
                     @close_modal="handleCloseModal">
        <v-row>
            <v-col cols="5" sm="12" md="5" lg="5" xl="5">
                <v-row>
                    <v-col cols="12" class="pb-0">
                        <v-autocomplete
                            label="Tipo do Evento"
                            :items="options_tipo_evento"
                            :error="!agenda.id_tipo_evento && !camposObrigatorios"
                            v-model="agenda.id_tipo_evento"
                        />
                    </v-col>

                    <v-col cols="12" class="pb-0">
                        <v-autocomplete
                            label="CPF do Paciente"
                            :items="options_cpf"
                            itemTitle="label"
                            v-model="agenda.id_paciente"
                            @update:modelValue="buscarPacienteSelecionado"
                        >
                            <template #append>
                                <v-btn size="small" icon color="cinzaAzulado" @click="abrirModalAdicionarPaciente">
                                    <v-icon size="small">fas fa-plus</v-icon>
                                    <v-tooltip text="Cadastrar novo paciente"/>
                                </v-btn>
                            </template>
                        </v-autocomplete>
                    </v-col>

                </v-row>
            </v-col>

            <v-col cols="7" sm="12" md="7" lg="7" xl="7">
                <v-card :disabled="!agenda.id_paciente">
                    <v-row>
                        <v-col cols="12" md="5" lg="4" class="d-flex justify-center align-center">
                            <v-avatar size="130">
                                <img :src="getProfilePhoto(paciente.foto_perfil)" alt="foto perfil" class="fit-cover">
                            </v-avatar>
                        </v-col>
                        <v-col cols="12" md="7" lg="8">

                            <div class="text-h5 mb-2"><b>Paciente:</b> {{ paciente.nome }}</div>

                            <div class="text-body-2 mb-2">
                                <b>Idade:</b> {{ paciente.idade }}
                                <span v-if="paciente.idade">anos</span>
                            </div>

                            <div class="mb-2">
                                <b>Telefone: </b>
                                <a v-if="paciente.telefone_1"
                                   class="text-body-2"
                                   :href="`https://wa.me/+55${paciente.telefone_1.replace(/[^0-9]/g, '')}`"
                                   target="_blank">
                                    {{ formatarTelefone(paciente.telefone_1) }}
                                </a>
                                <span v-if="paciente.telefone_2" class="mx-1">/</span>
                                <a v-if="paciente.telefone_2"
                                   class="text-body-2"
                                   :href="`https://wa.me/+55${paciente.telefone_2.replace(/[^0-9]/g, '')}`"
                                   target="_blank">
                                    {{ formatarTelefone(paciente.telefone_2) }}
                                </a>
                            </div>


                            <div class="text-body-2 mb-2"><b>Plano de Saúde:</b> {{ paciente.plano_saude }}</div>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>

            <v-divider/>

            <v-col cols="12" sm="4" md="3">
                <v-text-field
                    v-model="agenda.data_evento"
                    label="Data do evento"
                    :error="!agenda.data_evento && !camposObrigatorios"
                    type="date"
                ></v-text-field>
            </v-col>

            <v-col cols="12" sm="4" md="2">
                <v-text-field
                    v-model="agenda.hora_inicio"
                    label="Hora Inicial"
                    type="time"
                    :disabled="agenda.dia_inteiro"
                ></v-text-field>
            </v-col>

            <v-col cols="12" sm="4" md="2">
                <v-text-field
                    v-model="agenda.hora_fim"
                    label="Hora Final"
                    type="time"
                    :disabled="agenda.dia_inteiro"
                ></v-text-field>
            </v-col>

            <v-col cols="12" sm="6" md="2" class="pb-0 pt-0">
                <v-switch
                    class="d-flex justify-center"
                    :true-value="true"
                    :false-value="false"
                    label="Dia Inteiro"
                    v-model="agenda.dia_inteiro"
                />
            </v-col>

            <v-col cols="12" sm="6" md="3">
                <v-autocomplete
                    label="Status"
                    :items="options_status_agenda"
                    :clearable="false"
                    v-model="agenda.id_status_agenda"
                />
            </v-col>

            <v-col cols="12">
                <v-textarea
                    label="Descrição"
                    v-model="agenda.descricao"
                />
            </v-col>


        </v-row>

    </card-formulario>

    <v-dialog v-model="modalCadastroRapidoPaciente" transition="dialog-top-transition" :width="tamanhoModal">
        <PacienteForm @close_modal="fecharModalCadastroRapidoPaciente" :isModal="true" :confUrl="false"/>
    </v-dialog>

</template>