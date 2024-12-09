<script setup>
import {ref, onMounted} from 'vue';
import {loading} from "@/plugins/loadingService";
import {serviceList, serviceDownloadDataExcel} from "@/service/neonato";
import {useRouter} from "vue-router";
import CardFormulario from "@/components/CardFormulario.vue";
import {getSessionUserData} from "@/service/common/tokenService";


const emit   = defineEmits(['set-show-buttons']);
const data   = ref([]);
const search = ref("");
const router = useRouter();
const userData = ref("");
const roleUsuario = ref("");


onMounted(async () => {
    loading.show();

    userData.value    = getSessionUserData();
    roleUsuario.value = userData.value.id_role;

    const resp = await serviceList();
    data.value = resp;

    emit('set-show-buttons', false);
    loading.hide();
});


const headers = ref([{
    title: 'RN de',
    key  : 'nomeMae',
    align: 'start',
    class: 'text--primary',
}, {
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


const verProntuario = (idNeonato) => {
    router.push({
        name : 'Prontuario-Page',
        query: {id: idNeonato}
    });
}


</script>


<template>
    <CardFormulario title="Lista de Neonatos"
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
                        <v-btn v-if="roleUsuario === 1" color="azulEscuro" variant="elevated" class="elevation-2" @click="serviceDownloadDataExcel()" >
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

                <!-- Coluna Nome Mãe -->
                <template v-slot:[`item.nomeMae`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.idNeonato)">
                        <b>{{ item.nomeMae }}</b>
                        <v-tooltip text="Visualizar ou editar cadastro do neonato"/>
                    </a>
                </template>

                <!-- Coluna Prontuário -->
                <template v-slot:[`item.prontuario`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="verProntuario(item.idNeonato)">
                        <b>{{ item.prontuario }}</b>
                        <v-tooltip text="Visualizar ou editar prontuário do neonato"/>
                    </a>
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
