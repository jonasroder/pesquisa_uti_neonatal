<script setup>
import {ref, onMounted} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/paciente";
import TextInput from "@/components/TextInput.vue";
import {useRouter} from "vue-router";


const data = ref([]);
const search = ref("");
const defaultImagePath = require('@/assets/no_image.png');
const router = useRouter();


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
    {title: 'Telefone', key: 'telefone_1', align: 'start'},
    {title: 'Último Atendimento', key: 'data_ultimo_atendimento', align: 'start'},
    {title: 'Nº Visitas', key: 'num_visitas', align: 'start'},
]);


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const navigateToEditPage = (idPaciente) => {
    router.push({ name: 'Paciente-Page', query: { id: idPaciente } });
};


const handleNew = () => {
    router.push({ name: 'Paciente-Page' });
}
</script>


<template>
    <CardListagem title="Lista de Pacientes"
                  subtitle="Centralizando Informações para Cuidado Integral"
                  @handleNew="handleNew">


        <v-card flat>
            <v-card-title class="d-flex align-center pe-2">
                <v-spacer/>
                <text-input
                    v-model="search"
                    label="Buscar"
                    type="text"
                    prependInnerIcon="fa-solid fa-search"
                />
            </v-card-title>

            <v-divider/>

            <v-data-table :headers="headers"
                          :items="data"
                          :hover="true"
                          v-model:search="search"
                          class="elevation-1">

                <!-- Coluna Imagem perfil -->
                <template v-slot:[`item.foto_perfil`]="{ item }">
                    <v-avatar size="55"
                              class="ma-1">
                        <img :src="getProfilePhoto(item.foto_perfil)"
                             :alt="item.nome"
                             class="fit-cover">
                    </v-avatar>
                </template>

                <!-- Coluna Nome -->
                <template v-slot:[`item.nome`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.id_paciente)">
                        {{ item.nome }}
                    </a>
                </template>

                <!-- Coluna Telefone -->
                <template v-slot:[`item.telefone_1`]="{ item }">
                    <div>
                        <a v-if="item.telefone_1" :href="`https://wa.me/+55${item.telefone_1.replace(/[^0-9]/g, '')}`" target="_blank">{{ item.telefone_1 }}</a>
                        <br v-if="item.telefone_1 && item.telefone_2">
                        <a v-if="item.telefone_2" :href="`https://wa.me/+55${item.telefone_2.replace(/[^0-9]/g, '')}`" target="_blank">{{ item.telefone_2 }}</a>
                    </div>
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
