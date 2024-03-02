<script setup>
import {ref, onMounted} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/cadastros/diagnostico";
import {useRouter} from "vue-router";


const data   = ref([]);
const search = ref("");
const router = useRouter();


onMounted(async () => {
    loading.show();
    const resp = await serviceList();
    data.value = resp;
    loading.hide();
});


const headers = ref([{
    title: 'Nome',
    key  : 'nome',
    align: 'start',
    class: 'text--primary',
}, {
    title: 'CID',
    key  : 'codigo_cid',
    align: 'start'
}, {
    title: 'Descrição',
    key  : 'descricao',
    align: 'start'
}]);


const navigateToEditPage = (id_diagnostico) => {
    router.push({
        name : 'Diagnostico-Page',
        query: {id: id_diagnostico}
    });
};


const handleNew = () => {
    router.push({name: 'Diagnostico-Page'});
}


</script>


<template>
    <CardListagem title="Lista de Diagnósticos"
                  subtitle="Escolha um para editar ou cadastre um novo"
                  @handleNew="handleNew">

        <v-card flat>
            <v-card-title class="d-flex align-center pe-2">
                <v-spacer/>
                <v-text-field
                    label="Buscar"
                    type="text"
                    prependInnerIcon="fa-solid fa-search"
                    v-model="search"
                />
            </v-card-title>

            <v-divider/>

            <v-data-table :headers="headers"
                          :items="data"
                          :hover="true"
                          v-model:search="search"
                          class="elevation-1">

                <!-- Coluna Nome -->
                <template v-slot:[`item.nome`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.id_diagnostico)">
                        <b>{{ item.nome }}</b> </a>
                </template>


            </v-data-table>
        </v-card>
    </CardListagem>
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
