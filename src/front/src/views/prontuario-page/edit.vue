<script setup>
import {onMounted, ref} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin from '@fullcalendar/interaction';
import ptLocale from '@fullcalendar/core/locales/pt-br';
//import {serviceLoad} from "@/service/prontuario";
import {loading} from "@/plugins/loadingService";
// import {getScreenSize} from "@/service/common/utils";
import ModalCadastroEventoAgenda from "@/views/prontuario-page/modalCadastroEventoProntuario.vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {useRouter} from "vue-router";


const calendarEvents      = ref([]);
//const eventosProntuario   = ref();
const modalCadastroRapido = ref(false);
const tamanhoModal        = ref("");
const fullCalendarRef     = ref(null);
const eventoSelecionado   = ref();
const router              = useRouter();

onMounted(async () => {
    loading.show()
    //eventosProntuario.value = await serviceLoad();
    loading.hide()
});

const handleEventClick = ({event}) => {
    eventoSelecionado.value = event._def.extendedProps.evento;
    //cadastroRapido();
};


const calendarOptions = ref({
    plugins: [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
    headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
    },
    initialView: window.innerWidth < 768 ? 'listWeek' : 'dayGridMonth',
    locale: ptLocale,
    events: calendarEvents.value,
    selectable: true,
    selectMirror: true,
    eventClick: handleEventClick,
    contentHeight: 'auto',
});

//
// const selecionarMedico = async (itemId) => {
//     usuario_agenda_selecionado.value = itemId;
//     eventoSelecionado.value          = null;
//     await carregarDadosAgenda();
// }
//
//
// const carregarDadosAgenda = async () => {
//
//     const eventos = await servicebuscarEventosCalendario(usuario_agenda_selecionado.value);
//
//     const eventosFormatados = eventos.map(evento => ({
//         title : evento.tipo_evento,
//         start : `${evento.data_evento}T${evento.hora_inicio}`,
//         end   : `${evento.data_evento}T${evento.hora_fim}`,
//         allDay: evento.dia_inteiro === true,
//         color : evento.status_agenda_cor_associada,
//         evento: evento
//     }));
//
//     const calendarApi = fullCalendarRef.value.getApi();
//     calendarApi.removeAllEvents();
//     calendarApi.addEventSource(eventosFormatados);
// }
//
//
// const criarNovoEvento = (id_usuario) => {
//     usuario_agenda_selecionado.value = id_usuario;
//     cadastroRapido();
// }
//
//
// const cadastroRapido = () => {
//     modalCadastroRapido.value = true;
//     const screenWidth         = getScreenSize().width;
//
//     if (screenWidth < 600) {
//         tamanhoModal.value = "90%";
//     } else if (screenWidth >= 600 && screenWidth <= 1200) {
//         tamanhoModal.value = '70%';
//     } else {
//         tamanhoModal.value = '50%';
//     }
// }
//
//
// const fecharModalCadastroRapido = async () => {
//     loading.show()
//
//     await carregarDadosAgenda();
//     modalCadastroRapido.value = false
//
//     loading.hide()
// }
//

const handleSave = async () => {

}


const handleBack = () => {
    router.push({name: 'Neonato-List'});
};

</script>

<template>
    <card-formulario title="Prontuário do Neonato"
                     subtitle="Você pode editar o formulário a qualquer momento"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-container>

            <v-row>
                <v-col cols="12" class="fill-height">
                    <v-card class="elevation-3">
                        <v-card-text>
                            <FullCalendar ref="fullCalendarRef" :options="calendarOptions"/>
                        </v-card-text>
                    </v-card>
                </v-col>

            </v-row>

        </v-container>

        <v-dialog v-model="modalCadastroRapido" transition="dialog-top-transition" :width="tamanhoModal">
            <ModalCadastroEventoAgenda @close_modal="fecharModalCadastroRapido" :eventoSelecionado="eventoSelecionado" :usuarioAgendaSelecionado="usuario_agenda_selecionado"/>
            />
        </v-dialog>


    </card-formulario>
</template>

<style>
.fc .fc-button .fc-icon {
    margin-top: -9px !important;
}

</style>
