<script setup>
import {onMounted, ref} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import ptLocale from '@fullcalendar/core/locales/pt-br';
import {servicebuscarEventosCalendario} from "@/service/agenda";
import {loading} from "@/plugins/loadingService";
import {getScreenSize} from "@/service/common/utils";
import ModalCadastroEventoAgenda from "@/views/main-page/modalCadastroEventoAgenda.vue";


const calendarEvents             = ref([]);
const usuario_agenda_selecionado = ref(null);
const usuarios_agenda            = ref();
const modalCadastroRapido        = ref(false);
const tamanhoModal               = ref("");
const fullCalendarRef            = ref(null);
const eventoSelecionado          = ref();

onMounted(async () => {
    loading.show()
    //usuarios_agenda.value = await servicebuscarUsuariosAgenda();
    loading.hide()
});

const handleEventClick = ({event}) => {
    eventoSelecionado.value = event._def.extendedProps.evento;
    cadastroRapido();
};


const calendarOptions = ref({
    plugins      : [dayGridPlugin, timeGridPlugin, interactionPlugin],
    headerToolbar: {
        left  : 'prev,next today',
        center: 'title',
        right : 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    initialView  : 'dayGridMonth',
    locale       : ptLocale,
    events       : calendarEvents.value,
    selectable   : true,
    selectMirror : true,
    eventClick   : handleEventClick,
});


const selecionarMedico = async (itemId) => {
    usuario_agenda_selecionado.value = itemId;
    eventoSelecionado.value          = null;
    await carregarDadosAgenda();
}


const carregarDadosAgenda = async () => {

    const eventos = await servicebuscarEventosCalendario(usuario_agenda_selecionado.value);

    const eventosFormatados = eventos.map(evento => ({
        title : evento.tipo_evento,
        start : `${evento.data_evento}T${evento.hora_inicio}`,
        end   : `${evento.data_evento}T${evento.hora_fim}`,
        allDay: evento.dia_inteiro === true,
        color : evento.status_agenda_cor_associada,
        evento: evento
    }));

    const calendarApi = fullCalendarRef.value.getApi();
    calendarApi.removeAllEvents();
    calendarApi.addEventSource(eventosFormatados);
}


const criarNovoEvento = (id_usuario) => {
    usuario_agenda_selecionado.value = id_usuario;
    cadastroRapido();
}


const cadastroRapido = () => {
    modalCadastroRapido.value = true;
    const screenWidth         = getScreenSize().width;

    if (screenWidth < 600) {
        tamanhoModal.value = "90%";
    } else if (screenWidth >= 600 && screenWidth <= 1200) {
        tamanhoModal.value = '70%';
    } else {
        tamanhoModal.value = '50%';
    }
}


const fecharModalCadastroRapido = async () => {
    loading.show()

    await carregarDadosAgenda();
    modalCadastroRapido.value = false

    loading.hide()
}


</script>

<template>
    <v-container>
        <v-row justify="center" align="center" class="d-flex  pa-0 fill-height border border-sm border-opacity-100 border-barrasSperior rounded">
            <v-col cols="12">
                <v-card>
                    <v-row class="mb-2">
                        <v-col cols="12">
                            <v-card-title class="d-flex flex-column align-start text-wrap">
                                <span class="text-h5">Agenda</span>
                                <small class="text-subtitle-1">Sistema de Agendamento de Consultas</small>
                            </v-card-title>
                        </v-col>
                    </v-row>

                    <v-divider/>

                    <v-card-text>
                        <v-row>
                            <v-col cols="10">
                                <v-card class="elevation-3 fill-height">
                                    <v-card-text>
                                        <FullCalendar ref="fullCalendarRef" :options="calendarOptions"/>
                                    </v-card-text>
                                </v-card>
                            </v-col>

                            <!-- Barra lateral com os doutores -->
                            <v-col cols="2" class="d-flex flex-column">
                                <v-card class="elevation-3 fill-height pa-2">

                                    <v-hover v-for="item in usuarios_agenda" :key="item.id_usuario">
                                        <template v-slot:default="{ isHovering, props }">
                                            <v-card
                                                v-bind="props" :elevation="isHovering ? '7' : '2'"
                                                :color="usuario_agenda_selecionado === item.id_usuario ? 'primary' : ''"
                                                @click="selecionarMedico(item.id_usuario)"
                                                class="mb-3"
                                            >
                                                <v-card-title>
                                                    {{ item.nome_completo }}
                                                </v-card-title>
                                                <v-card-subtitle>
                                                    {{ item.especialidade }}
                                                </v-card-subtitle>
                                                <v-card-actions>
                                                    <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="criarNovoEvento(item.id_usuario)">
                                                        Novo Evento
                                                        <v-tooltip text="Adicionar novo evento"/>
                                                    </v-btn>
                                                </v-card-actions>
                                            </v-card>
                                        </template>
                                    </v-hover>

                                </v-card>
                            </v-col>
                        </v-row>

                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>

    <v-dialog v-model="modalCadastroRapido" transition="dialog-top-transition" :width="tamanhoModal">
        <ModalCadastroEventoAgenda @close_modal="fecharModalCadastroRapido" :eventoSelecionado="eventoSelecionado" :usuarioAgendaSelecionado="usuario_agenda_selecionado"/>
        />
    </v-dialog>

</template>

<style>
.fc .fc-button .fc-icon {
    margin-top: -9px !important;
}

</style>
