<script setup>
import {onMounted, reactive, ref, watch} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin from '@fullcalendar/interaction';
import ptLocale from '@fullcalendar/core/locales/pt-br';
import {serviceLoad, serviceSaveIsoladoColeta} from "@/service/prontuario";
import {loading} from "@/plugins/loadingService";
import ModalCadastroEventoAgenda from "@/views/prontuario-page/modalCadastroEventoProntuario.vue";
import ColetaIsolado from "@/views/prontuario-page/coletaIsolado.vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {useRouter} from "vue-router";
import {getIdFromUrl} from "@/service/common/utils";


const id                    = ref(getIdFromUrl());
const emit                  = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const calendarEvents        = ref([]);
const abaPagina             = ref();
const modalCadastroRapido   = ref(false);
const fullCalendarRef       = ref(null);
const eventoSelecionado     = ref();
const router                = useRouter();
const nomeMae               = ref();
const prontuario            = ref();
const dataInternacao        = ref();
const carregarDadosIsolados = ref(false);

const coletaData = ref([{
    idMicroorganismo                    : null,
    idMecanismoResistenciaMicroorganismo: null,
    idPerfilResistenciaMicroorganismo   : null,
    antibiogramas                       : []
}]);



onMounted(async () => {
    loading.show()
    await carregarDadosAgenda();

    if (dataInternacao.value) {
        const calendarApi = fullCalendarRef.value.getApi();
        calendarApi.gotoDate(dataInternacao.value);
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', false);

    loading.hide()
});



const handleEventClick = ({event}) => {
    eventoSelecionado.value   = event._def.extendedProps.evento;
    modalCadastroRapido.value = true;
};



const data = reactive({
    idEventoMedico  : null,
    idEvento        : null,
    idNeonato       : null,
    dataEvento      : null,
    idTipoEvento    : null,
    idEventoEntidade: null,
    tipoEntidade    : null,
    diaInteiro      : null,
    observaco       : null,
    is_active       : true,
});



const cadastroRapido = ({
                            date,
                            allDay
                        }) => {
    data.dataEvento = date;
    data.diaInteiro = allDay;
    data.idNeonato  = id;

    eventoSelecionado.value   = data;
    modalCadastroRapido.value = true;
}


const calendarOptions = ref({
    plugins      : [dayGridPlugin, timeGridPlugin, listPlugin, interactionPlugin],
    headerToolbar: {
        left  : 'prev,next today',
        center: '',
        right : 'title' //,timeGridDay,listWeek,dayGridMonth,timeGridWeek
    },
    titleFormat  : {
        month: 'short',
        year : 'numeric'
    },
    initialView  : 'dayGridMonth',
    initialDate  : dataInternacao.value,
    locale       : ptLocale,
    events       : calendarEvents.value,
    selectable   : true,
    selectMirror : true,
    eventClick   : handleEventClick,
    dateClick    : cadastroRapido,
    contentHeight: 'auto',
});



const carregarDadosAgenda = async () => {
    const data           = await serviceLoad(id.value);
    nomeMae.value        = data.neonato.nomeMae
    prontuario.value     = data.neonato.prontuario;
    const eventos        = data.eventos;
    dataInternacao.value = formatDateToUS(data.neonato.dataInternacao);

    const eventosFormatados = eventos.map(evento => ({
        title : evento.tipoEvento,
        start : evento.dataEvento,
        allDay: evento.diaInteiro === true,
        color : evento.corEvento,
        evento: evento
    }));

    const calendarApi = fullCalendarRef.value.getApi();
    calendarApi.removeAllEvents();
    calendarApi.addEventSource(eventosFormatados);
}



const fecharModalCadastroRapido = async () => {
    loading.show()
    await carregarDadosAgenda();
    modalCadastroRapido.value = false
    loading.hide()
}


const formatDateToUS = (dateBR) => {
    if (!dateBR) {
        const today = new Date();
        return today.toISOString().split('T')[0];
    }

    const [day, month, year] = dateBR.split('/');
    if (!day || !month || !year) {
        throw new Error('Formato de data inválido. Use o formato DD/MM/AAAA.');
    }

    return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`;
};


const handleSave = async () => {
    loading.show();

    coletaData.value = coletaData.value.filter(coleta => {
        if (coleta.idMicroorganismo != null) return coleta;
    });

    for (const coleta of coletaData.value) {
        for (const antibiograma of coleta.antibiogramas) {
            if (antibiograma.idResistenciaMicroorganismo === null || antibiograma.idAntimicrobiano === null) {
                antibiograma.isActive = false;
            }
        }
    }

    const status = await serviceSaveIsoladoColeta(coletaData.value);

    if (status.success) carregarDadosIsolados.value = !carregarDadosIsolados.value;

    loading.hide();
};



const saveColetasIsolados = (coletas) => {
    coletaData.value = coletas; // Recebe os dados do componente filho
};



const handleBack = () => {
    router.push({name: 'Neonato-List'});
};



const ocultarBotoesNavBar = () => {
    emit('set-show-buttons', false);
};



const mostrarBotoesNavBar = () => {
    emit('set-show-buttons', true);

    carregarDadosIsolados.value = !carregarDadosIsolados.value;
};


const navigateToEditPage = (idNeonato) => {
    router.push({
        name : 'Neonato-Page',
        query: {id: idNeonato}
    });
};



watch(abaPagina, (newVal) => {
    if (newVal === '1') {
        setTimeout(() => {
            const calendarApi = fullCalendarRef.value.getApi();
            calendarApi.render();
        }, 10);
    }
});

</script>


<template>
    <card-formulario :title="`Prontuário do Neonato de: ${nomeMae} - ${prontuario}` "
                     subtitle="Você pode editar o formulário a qualquer momento">

        <v-container class="pa-0">

            <div class="d-flex justify-end mb-2">
                <a href="#" class="editable-name" @click.prevent="navigateToEditPage(id)">
                    <b>Cadastro do Neonato <i class="fas fa-external-link-alt"></i></b>
                </a>
            </div>

            <v-card>
                <v-tabs v-model="abaPagina" bg-color="primary">
                    <v-tab value="1" @click="ocultarBotoesNavBar"><b>Procedimentos</b></v-tab>
                    <v-tab value="2" @click="mostrarBotoesNavBar"><b>Isolados</b></v-tab>
                </v-tabs>

                <v-card-text>
                    <v-window v-model="abaPagina">

                        <v-window-item value="1">
                            <FullCalendar ref="fullCalendarRef" :options="calendarOptions"/>
                        </v-window-item>

                        <v-window-item value="2">  <!-- Passamos a prop reativa "carregar" para o filho -->
                            <ColetaIsolado :key="carregarDadosIsolados" :idNeonato="id" :onSave="saveColetasIsolados"/>
                        </v-window-item>
                    </v-window>
                </v-card-text>
            </v-card>

        </v-container>

        <v-dialog v-model="modalCadastroRapido" transition="dialog-top-transition" max-width="400px">
            <ModalCadastroEventoAgenda @close_modal="fecharModalCadastroRapido" :eventoSelecionado="eventoSelecionado" :idNeonato="id"/>
        </v-dialog>


    </card-formulario>
</template>

<style>
.fc .fc-button .fc-icon {
    margin-top: -9px !important;
}

.editable-name {
    color: #2c3e50;
    text-decoration: none;
    cursor: pointer;
}

.editable-name:hover {
    text-decoration: underline;
    color: #3498db;
}
</style>
