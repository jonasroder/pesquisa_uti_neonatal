<script setup>
import {ref, onMounted} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/paciente";

const data = ref([]);
const defaultImagePath = require('@/assets/no_image.png');

onMounted(async () => {
    loading.show();

    const resp = await serviceList();
    data.value = resp;

    loading.hide();

});

const headers = ref([
    {key: 'foto_perfil', align: 'start', sortable: false},
    {title: 'Nome', key: 'nome', align: 'start', class: 'text--primary'},
    {title: 'Idade', key: 'idade', align: 'start'},
    {title: 'Telefone 1', key: 'telefone_1', align: 'start'},
    {title: 'Telefone 2', key: 'telefone_2', align: 'start'},
    {title: 'Último Atendimento', key: 'data_ultimo_atendimento', align: 'start'},
    {title: 'Nº Visitas', key: 'num_visitas', align: 'start'},
]);


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};
</script>


<template>
    <CardListagem title="Lista de Pacientes"
                  subtitle="Centralizando Informações para Cuidado Integral">

        <v-data-table :headers="headers"
                      :items="data"
                      :hover="true"
                      class="elevation-1">

            <template v-slot:[`item.foto_perfil`]="{ item }">
                <v-avatar size="55"
                          class="ma-2">
                    <img :src="getProfilePhoto(item.foto_perfil)"
                         :alt="item.nome"
                         class="fit-cover">
                </v-avatar>
            </template>

        </v-data-table>

    </CardListagem>
</template>


<style scoped>
.fit-cover {
    object-fit: cover;
    width: 100%;
    height: 100%;
}

</style>
