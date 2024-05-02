<script setup>
import {ref, onMounted} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/neonato";
import {useRouter} from "vue-router";
// import {formatarTelefone} from "@/service/common/utils"
// import defaultImagePath from "@/assets/no_image.png";


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
    title: 'RN de',
    key  : 'nomeMae',
    align: 'start',
    class: 'text--primary',
    width: '30%'
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
}, {
    key  : 'action',
    align: 'center'
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

//
// const editarConsulta = (id_paciente, id_consulta) => {
//     const routeData = router.resolve({
//         name : 'Consulta-Page',
//         query: {
//             id_paciente: id_paciente,
//             id_consulta: id_consulta
//         }
//     });
//
//     window.open(routeData.href, '_blank');
// }
//

</script>


<template>
    <CardListagem title="Lista de Neonatos"
                  subtitle="Centralizando Informações para Cuidado Integral"
                  botao="Novo Neonato"
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

                <!-- Coluna Nome Mãe -->
                <template v-slot:[`item.nomeMae`]="{ item }">
                    <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.idNeonato)">
                        <b>{{ item.nomeMae }}</b>
                        <v-tooltip text="Visualizar ou editar cadastro de neonato"/>
                    </a>
                </template>


                <!-- Botão nova consulta -->
                <template v-slot:[`item.action`]="{ item }">
                    <v-col class="text-right align-center">
                        <v-btn color="cinzaAzulado" @click="createNewConsulta(item.idNeonato)">
                            Prontuário
                        </v-btn>
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
