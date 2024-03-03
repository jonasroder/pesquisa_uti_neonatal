<script setup>
import {ref, onMounted} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/paciente";
import {useRouter} from "vue-router";
import {formatarTelefone} from "@/service/common/utils"
import defaultImagePath from "@/assets/no_image.png";


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
    key     : 'foto_perfil',
    align   : 'start',
    sortable: false
}, {
    title: 'Nome',
    key  : 'nome',
    align: 'start',
    class: 'text--primary',
    width: '30%'
}, {
    title: 'Idade',
    key  : 'idade',
    align: 'start'
}, {
    title: 'Plano de Saúde',
    key  : 'plano_saude',
    align: 'start'
}, {
    title: 'Cidade',
    key  : 'cidade',
    align: 'start'
}, {
    title: 'Telefone',
    key  : 'telefone_1',
    align: 'start'
}, {
    title: 'Últimos Atendimentos',
    key  : 'listaConsultas',
    align: 'start'
}, {
    key  : 'action',
    align: 'center'
}]);


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const navigateToEditPage = (idPaciente) => {
    router.push({
        name : 'Paciente-Page',
        query: {id: idPaciente}
    });
};


const handleNew = () => {
    router.push({name: 'Paciente-Page'});
}


const createNewConsulta = (id_paciente) => {
    router.push({
        name : 'Consulta-Page',
        query: {id_paciente: id_paciente}
    });
}

const editarConsulta = (id_paciente, id_consulta) => {
    const routeData = router.resolve({
        name : 'Consulta-Page',
        query: {
            id_paciente: id_paciente,
            id_consulta: id_consulta
        }
    });

    window.open(routeData.href, '_blank');
}


</script>


<template>
    <CardListagem title="Lista de Pacientes"
                  subtitle="Centralizando Informações para Cuidado Integral"
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

                <!-- Coluna Imagem perfil -->
                <template v-slot:[`item.foto_perfil`]="{ item }">
                    <v-avatar size="55" class="ma-1">
                        <img :src="getProfilePhoto(item.foto_perfil)" :alt="item.nome" class="fit-cover">
                    </v-avatar>
                </template>

                <!-- Coluna Nome -->
                <template v-slot:[`item.nome`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.id_paciente)">
                        <b>{{ item.nome }}</b> </a>
                </template>

                <!-- Coluna Telefone -->
                <template v-slot:[`item.telefone_1`]="{ item }">
                    <div>
                        <a v-if="item.telefone_1" :href="`https://wa.me/+55${item.telefone_1.replace(/[^0-9]/g, '')}`" target="_blank">{{ formatarTelefone(item.telefone_1) }}</a>
                        <br v-if="item.telefone_1 && item.telefone_2">
                        <a v-if="item.telefone_2" :href="`https://wa.me/+55${item.telefone_2.replace(/[^0-9]/g, '')}`" target="_blank">{{ formatarTelefone(item.telefone_2) }}</a>
                    </div>
                </template>

                <!-- Coluna Lista Consultas -->
                <template v-slot:[`item.listaConsultas`]="{ item }">
                    <div v-for="(consulta, index) in item.listaConsultas"
                         :key="`consulta-${index}`">
                        <a href="#" class="editable-name" @click.prevent="editarConsulta(item.id_paciente, consulta.id_consulta)">
                            {{ `${consulta.tipo_consulta}: ${consulta.data}` }}
                        </a>
                    </div>
                </template>

                <!-- Botão nova consulta -->
                <template v-slot:[`item.action`]="{ item }">

                    <v-col class="text-right align-center">
                        <v-tooltip text="Cadastrar nova consulta">
                            <template v-slot:activator="{ props }">
                                <v-btn v-bind="props" size="small" icon color="cinzaAzulado" @click="createNewConsulta(item.id_paciente)">
                                    <v-icon size="small">fas fa-plus</v-icon>
                                </v-btn>
                            </template>
                        </v-tooltip>
                    </v-col>

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
