<script setup>
import { ref } from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid'; // Importado para visualização por hora
import interactionPlugin from '@fullcalendar/interaction';
import ptLocale from '@fullcalendar/core/locales/pt-br';

// Uma lista reativa para manter os eventos
const calendarEvents = ref([]);

// Manipulador para clique em datas
const handleDateClick = (arg) => {
    let title = prompt('Digite o título do evento:');
    if (title) {
        calendarEvents.value.push({
            title,
            start: arg.date,
            allDay: arg.allDay
        });
    }
};

// Manipulador para seleção de intervalo de datas (útil para selecionar horários)
const handleDateSelect = (selectInfo) => {
    let title = prompt('Digite o título do evento:');
    let calendarApi = selectInfo.view.calendar;

    calendarApi.unselect();

    if (title) {
        calendarApi.addEvent({
            title,
            start: selectInfo.startStr,
            end: selectInfo.endStr,
            allDay: selectInfo.allDay
        });
    }
};



const calendarOptions = ref({
    plugins: [dayGridPlugin, timeGridPlugin, interactionPlugin],
    headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    initialView: 'timeGridWeek',
    locale: ptLocale,
    events: calendarEvents.value,
    dateClick: handleDateClick,
    selectable: true,
    selectMirror: true,
    select: handleDateSelect,
});

</script>

<template>
    <v-row justify="center" align="center" class="d-flex  pa-0 fill-height border border-sm border-opacity-100 border-barrasSperior rounded">
        <v-col cols="12">
            <v-card>
                <v-card-text>
                    <v-row justify="center" align="center" class="d-flex  pa-0 fill-height border border-sm border-opacity-100 border-barrasSuperior rounded">
                        <v-col cols="12">
                            <v-card>
                                <v-card-text>
                                    <FullCalendar :options="calendarOptions" />
                                </v-card-text>
                            </v-card>
                        </v-col>
                    </v-row>
                </v-card-text>

            </v-card>
        </v-col>
    </v-row>

</template>

<style>
.fc .fc-button .fc-icon {
    margin-top: -9px !important;
}

</style>
