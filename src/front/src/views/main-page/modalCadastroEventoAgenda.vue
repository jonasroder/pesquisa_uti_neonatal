<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {onMounted, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {formatarTelefone, getOptionsAutocomplete, getScreenSize} from "@/service/common/utils";
import defaultImagePath from "@/assets/no_image.png";
import PacienteForm from '@/views/paciente-page/edit.vue';


const options_tipo_evento         = ref();
const options_cpf                 = ref();
const modalCadastroRapidoPaciente = ref(false);
const tamanhoModal                = ref("");
const emit                        = defineEmits(['close_modal', 'saved']);


const paciente = ref({
    nome       : null,
    idade      : null,
    telefone_1 : null,
    telefone_2 : null,
    plano_saude: null
});


const agenda = ref({
    id_evento       : null,
    id_tipo_evento  : null,
    id_usuario      : null,
    id_status_agenda: null,
    id_paciente     : null,
    inicio          : null,
    fim             : null,
    dia_inteiro     : null,
    url             : null,
    descricao       : null,
    is_active       : null,
});


onMounted(async () => {
    loading.show()
    await getAutoCompleteOptions();
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
    })]);

    options_tipo_evento.value = results[0];
    options_cpf.value         = results[1];
}


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const handleSave = () => {
};


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
                            v-model="agenda.id_tipo_evento"
                        />
                    </v-col>

                    <v-col cols="12" class="pb-0">
                        <v-autocomplete
                            label="CPF do Paciente"
                            :items="options_cpf"
                            v-model="agenda.id_paciente"
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
                            <div class="text-subtitle-1 mb-2">
                                <b>Idade:</b> {{ paciente.idade }}
                                <span v-if="paciente.idade !== null">anos</span>
                            </div>
                            <div class="text-subtitle-1 mb-2">
                                <b>Telefone:</b> {{ formatarTelefone(paciente.telefone_1) }}
                                <span v-if="paciente.telefone_2">/ {{ formatarTelefone(paciente.telefone_2) }}</span>
                            </div>
                            <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ paciente.plano_saude }}</div>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>

            <v-divider/>

            <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                <v-text-field
                    label="Início"
                    type="datetime-local"
                    v-model="agenda.inicio"
                />
                <v-tooltip text="Data e hora inícial do evento"/>
            </v-col>

            <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                <v-text-field
                    label="Fim"
                    type="datetime-local"
                    v-model="agenda.fim"
                    :disabled="agenda.dia_inteiro === true"
                />
                <v-tooltip text="Data e hora final do evento"/>
            </v-col>

            <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0 pt-0">
                <v-switch
                    class="d-flex justify-center"
                    :true-value="true"
                    :false-value="false"
                    label="Dia Inteiro"
                    v-model="agenda.dia_inteiro"
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