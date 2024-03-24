<script setup>
import {ref} from 'vue';
import FullCalendar from '@fullcalendar/vue3';
import dayGridPlugin from '@fullcalendar/daygrid';
//import listPlugin from '@fullcalendar/list';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from '@fullcalendar/interaction';
import ptLocale from '@fullcalendar/core/locales/pt-br';


const calendarEvents = ref([]);

const handleDateClick = (arg) => {
    let title = prompt('Digite o título do evento:');
    if (title) {
        calendarEvents.value.push({
            title,
            start : arg.date,
            allDay: arg.allDay
        });
    }
};


const handleDateSelect = (selectInfo) => {
    let title       = prompt('Digite o título do evento:');
    let calendarApi = selectInfo.view.calendar;

    calendarApi.unselect();

    if (title) {
        calendarApi.addEvent({
            title,
            start : selectInfo.startStr,
            end   : selectInfo.endStr,
            allDay: selectInfo.allDay
        });
    }
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
    dateClick    : handleDateClick,
    selectable   : true,
    selectMirror : true,
    select       : handleDateSelect, // Adicione mais opções aqui para customizar o layout conforme necessário
});


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

                    <v-divider></v-divider>

                    <v-card-text>
                        <v-row>
                            <v-col cols="10">
                                <v-card class="elevation-3 fill-height">
                                    <v-card-text>
                                        <FullCalendar :options="calendarOptions"/>
                                    </v-card-text>
                                </v-card>
                            </v-col>

                            <!-- Barra lateral com os doutores -->
                            <v-col cols="2" class="d-flex flex-column">
                                <v-card class="elevation-3 fill-height pa-2">
                                    <v-hover>
                                        <template v-slot:default="{ isHovering, props }">
                                            <v-card class="mb-2" v-bind="props" :color="isHovering ? 'primary' : undefined">
                                                <v-card-text>
                                                    Dr Medico 1
                                                </v-card-text>
                                            </v-card>
                                        </template>
                                    </v-hover>
                                    <v-card class="mb-2">
                                        <v-card-text>
                                            Dr Medico 2
                                        </v-card-text>
                                    </v-card>
                                    <v-card class="mb-2">
                                        <v-card-text>
                                            Dr Medico 3
                                        </v-card-text>
                                    </v-card>
                                </v-card>
                            </v-col>
                        </v-row>

                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<style>
.fc .fc-button .fc-icon {
    margin-top: -9px !important;
}

</style>
