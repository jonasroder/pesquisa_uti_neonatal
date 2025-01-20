<script setup>
import {onMounted, ref} from 'vue';
import {loading} from "@/plugins/loadingService";
import {serviceDownloadDataExcel, serviceList} from "@/service/download";
import CardFormulario from "@/components/CardFormulario.vue";


const data   = ref([]);
const search = ref("");


onMounted(async () => {
    loading.show();

    data.value = await serviceList();

    loading.hide();
});


const headers = ref([{
    title: 'Descrição',
    key  : 'descricao',
    align: 'start',
    class: 'text--primary',
}, {
    title: 'Usuário',
    key  : 'usuario',
    align: 'start'
}, {
    title: 'Data',
    key  : 'data',
    align: 'start'
}, {
    title: 'Status',
    key  : 'status',
    align: 'start'
}, {
    title: '#',
    key  : 'idSolicitacaoDownload',
    align: 'start'
}]);



const downloadExcel = async (idSolicitacaoDownload) => {
    loading.show();
    await serviceDownloadDataExcel(idSolicitacaoDownload);
    loading.hide();
}

const getChipColor = (status) => {
    switch (status) {
        case 'PENDENTE':
            return 'grey';
        case 'EM_ANDAMENTO':
            return 'blue';
        case 'CONCLUIDO':
            return 'green';
        case 'FALHA':
            return 'red';
        default:
            return 'grey';
    }
};

</script>


<template>
    <CardFormulario title="Downloads"
                    subtitle="Fila e controle de downloads"
                    :showCreateButton="false">


        <v-card flat>
            <v-data-table :headers="headers"
                          :items="data"
                          :hover="true"
                          v-model:search="search"
                          class="elevation-1">
                <template #top>
                    <v-toolbar flat class="pa-2">
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

                <!-- Customização da coluna Status -->
                <template v-slot:[`item.status`]="{ item }">
                    <v-chip :color="getChipColor(item.status)" text-color="white" class="ma-2">
                        {{ item.status }}
                    </v-chip>
                </template>

                <!-- Customização da última coluna (#) -->
                <template v-slot:[`item.idSolicitacaoDownload`]="{ item }">
                    <template v-if="item.status === 'CONCLUIDO'">
                        <a href="#" class="editable-name" @click.prevent="downloadExcel(item.idSolicitacaoDownload)">
                            <v-icon>fa-solid fa-download</v-icon>
                        </a>
                    </template>
                </template>


            </v-data-table>
        </v-card>

    </CardFormulario>

</template>


<style scoped>
.fit-cover {
    object-fit: cover;
    width: 100%;
    height: 100%;
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
