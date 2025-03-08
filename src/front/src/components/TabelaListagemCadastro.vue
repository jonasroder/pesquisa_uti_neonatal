<script setup>
import {ref} from 'vue';


defineProps({
    data              : {
        type    : Array,
        required: true
    },
    navigateToEditPage: {
        type    : Function,
        required: true
    },
    searchPlaceholder : {
        type   : String,
        default: 'Buscar'
    }
});

const search = ref("");

const headers = [{
    title: 'Descrição',
    key  : 'descricao',
    align: 'start',
    class: 'text--primary'
}, {
    title: 'Código',
    key  : 'codigo',
    align: 'start'
}, {
    title: 'Data Cadastro',
    key  : 'dataCadastro',
    align: 'start'
}, {
    title: 'Data Alteração',
    key  : 'dataAlteracao',
    align: 'start'
}, {
    title: 'Status',
    key  : 'isActive',
    align: 'start'
}];

const getChipColor = (isActive) => (isActive ? 'success' : 'error');
</script>

<template>
    <v-card flat>
        <v-data-table
            :headers="headers"
            :items="data"
            :hover="true"
            v-model:search="search"
            items-per-page="15"
            class="elevation-3"
            show-current-page
            show-first-last-page
        >
            <template #top>
                <v-toolbar flat class="pa-2">
                    <v-icon size="24" class="me-2">
                        fa-solid fa-table
                    </v-icon>
                    <v-spacer></v-spacer>
                    <v-text-field
                        v-model="search"
                        density="compact"
                        label="Buscar"
                        prepend-inner-icon="fa-solid fa-search"
                        class="elevation-1"
                        hide-details
                        single-line
                    />
                </v-toolbar>
            </template>

            <!-- Customização da coluna Descrição -->
            <template v-slot:[`item.descricao`]="{ item }">
                <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.id)">
                    <b>{{ item.descricao }}</b>
                </a>
            </template>

            <!-- Customização da coluna Status -->
            <template v-slot:[`item.isActive`]="{ item }">
                <v-chip :color="getChipColor(item.isActive)" text-color="white" class="ma-2">
                    {{ item.isActive ? 'Ativo' : 'Inativo' }}
                </v-chip>
            </template>
        </v-data-table>
    </v-card>
</template>

<style scoped>
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
