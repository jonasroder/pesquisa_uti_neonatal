<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {ref} from "vue";

const agenda     = ref({
    id_evento       : null,
    id_tipo_evento  : null,
    id_usuario      : null,
    id_status_agenda: null,
    inicio          : null,
    fim             : null,
    dia_inteiro     : null,
    url             : null,
    descricao       : null,
    is_active       : null,
});
const handleSave = () => {
};

const handleCloseModal = () => {
};
</script>


<template>
    <card-formulario title="Cadastrar novo evento"
                     subtitle="Adicione um novo evento na agenda"
                     @handleSave="handleSave"
                     @close_modal="handleCloseModal">
        <v-row>
            <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Início"
                    type="datetime-local"
                    v-model="agenda.inicio"
                />
                <v-tooltip text="Data e hora inícial do evento"/>
            </v-col>


            <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Fim"
                    type="datetime-local"
                    v-model="agenda.fim"
                />
                <v-tooltip text="Data e hora final do evento"/>
            </v-col>


        </v-row>

    </card-formulario>
</template>