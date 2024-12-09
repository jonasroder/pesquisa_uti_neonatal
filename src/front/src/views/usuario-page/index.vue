<script setup>
import {onMounted, ref} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/usuario";
import {useRouter} from "vue-router";


const emit   = defineEmits(['set-show-buttons']);
const data   = ref([]);
const router = useRouter();
const search = ref("");

onMounted(async () => {
    loading.show();
    data.value = await serviceList();
    emit('set-show-buttons', false);
    loading.hide();
});


const navigateToEditPage = (idUsuario) => {
    router.push({
        name : 'Usuario-Page',
        query: {id: idUsuario}
    });
};

const headers = ref([{
    title: 'Nome',
    key  : 'nome',
    align: 'start',
    class: 'text--primary',
}, {
    title: 'Usuário',
    key  : 'email',
    align: 'start'
}, {
    title: 'Permissão',
    key  : 'role',
    align: 'start'
}, {
    title: 'Último Login',
    key  : 'ultimoLogin',
    align: 'start'
}, {
    title: 'Status',
    key  : 'isActive',
    align: 'start'
}]);


const getChipColor = (isActive) => (isActive ? 'success' : 'error');
</script>

<template>
    <CardListagem
        title="Usuários"
        subtitle="Escolha um para editar ou cadastre um novo"
        @handleNew="() => navigateToEditPage(null)"
    >


        <v-data-table :headers="headers"
                      :items="data"
                      :hover="true"
                      v-model:search="search"
                      class="elevation-1">

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

            <!-- Coluna Nome -->
            <template v-slot:[`item.nome`]="{ item }">
                <a href="#" class="editable-name" @click.prevent="navigateToEditPage(item.idUsuario)">
                    <b>{{ item.nome }}</b>
                    <v-tooltip text="Visualizar ou editar cadastro do neonato"/>
                </a>
            </template>

            <!-- Customização da coluna Status -->
            <template v-slot:[`item.isActive`]="{ item }">
                <v-chip :color="getChipColor(item.isActive)" text-color="white" class="ma-2">
                    {{ item.isActive ? 'Ativo' : 'Inativo' }}
                </v-chip>
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
