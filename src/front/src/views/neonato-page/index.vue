<script setup>
import {onMounted, ref} from 'vue';
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/neonato";
import {useRouter} from "vue-router";
import CardFormulario from "@/components/CardFormulario.vue";
import {getSessionUserData} from "@/service/common/tokenService";
import ModalDownload from "@/views/neonato-page/modalDownload.vue";


const emit   = defineEmits(['set-show-buttons']);
const data   = ref([]);
const search = ref("");
const router = useRouter();
const userData = ref("");
const roleUsuario = ref("");
const modalDownload   = ref(false);


onMounted(async () => {
    loading.show();

    userData.value    = getSessionUserData();
    roleUsuario.value = userData.value.id_role;

    data.value = await serviceList();

    emit('set-show-buttons', false);
    loading.hide();
});


const headers = ref([{
    title: 'Prontuário',
    key  : 'prontuario',
    align: 'start'
}, {
    title: 'Data de Nascimento',
    key  : 'dataNascimento',
    align: 'start'
}, {
    title: 'Data de Internação',
    key  : 'dataInternacao',
    align: 'start'
}, {
    title: 'Data de Desfecho',
    key  : 'dataDesfecho',
    align: 'start'
}]);


const navigateToEditPage = (idNeonato) => {
    router.push({
        name : 'Neonato-Page',
        query: {id: idNeonato}
    });
};


const handleNew = () => {
    router.push({name: 'Neonato-Page'});
}

const downloadExcel = async () => {
    loading.show();
    modalDownload.value = true;
    loading.hide();
}


const fecharModal = async () => {
    modalDownload.value = false;
}

</script>


<template>
    <CardFormulario title="Neonatos"
                    subtitle="Controle e Registro de Neonatos"
                    :showCreateButton="true"
                    createButton="Cadastrar Neonato"
                    @handleNew="handleNew">


        <v-card flat>
            <v-data-table :headers="headers"
                          :items="data"
                          :hover="true"
                          v-model:search="search"
                          class="elevation-1">
                <template #top>
                    <v-toolbar flat class="pa-2">
                        <v-btn v-if="roleUsuario === 1" color="azulEscuro" variant="elevated" class="elevation-2" @click="downloadExcel()" >
                            baixar
                        </v-btn>

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

                <!-- Coluna Prontuário -->
                <template v-slot:[`item.prontuario`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.idNeonato)">
                        <b>{{ item.prontuario }}</b>
                        <v-tooltip text="Visualizar ou editar prontuário do neonato"/>
                    </a>
                </template>

            </v-data-table>
        </v-card>

        <v-dialog v-model="modalDownload" transition="dialog-top-transition" max-width="600px">
                <ModalDownload @close_modal="fecharModal"/>
        </v-dialog>
    </CardFormulario>

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
