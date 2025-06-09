<script setup>
import {ref, onMounted} from 'vue';
import {loading} from '@/plugins/loadingService';
import {getDistribuicaoMecanismos, getDistribuicaoPerfis, getResistenciaPorAntimicrobiano, serviceGetCadastrosPorDia, serviceGetInfeccoesPorAgente, serviceLoadUltimosNeonatosCadastrados} from '@/service/dashboard';
import {useRouter} from 'vue-router';
import CardFormulario from "@/components/CardFormulario.vue";
import ChartInternacoesPorDia from "@/views/dashboard-page/ChartInternacoesPorDia.vue";
import ChartInfeccoesPorAgente from "@/views/dashboard-page/ChartInfeccoesPorAgente.vue";
import ChartResistenciaPorAntimicrobiano from "@/views/dashboard-page/ChartResistenciaPorAntimicrobiano.vue";
import ChartDistribuicaoMecanismos from "@/views/dashboard-page/ChartDistribuicaoMecanismos.vue";
import ChartDistribuicaoPerfis from "@/views/dashboard-page/ChartDistribuicaoPerfis.vue";



const emit   = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router = useRouter();

const ultimosNeonatos              = ref([]);
const internacoesPorDia            = ref([]);
const infeccoesPorAgente           = ref([]);
const distribuicaoPerfis           = ref([]);
const distribuicaoMecanismos       = ref([]);
const resistenciaPorAntimicrobiano = ref([]);

onMounted(async () => {
    loading.show();
    ultimosNeonatos.value              = await serviceLoadUltimosNeonatosCadastrados();
    internacoesPorDia.value            = await serviceGetCadastrosPorDia();
    infeccoesPorAgente.value           = await serviceGetInfeccoesPorAgente();
    distribuicaoPerfis.value           = await getDistribuicaoPerfis();
    distribuicaoMecanismos.value       = await getDistribuicaoMecanismos();
    resistenciaPorAntimicrobiano.value = await getResistenciaPorAntimicrobiano();

    console.log(distribuicaoPerfis.value)
    console.log(distribuicaoMecanismos.value)
    console.log(resistenciaPorAntimicrobiano.value)
    emit('set-show-buttons', false);
    loading.hide();
});

const verProntuario = (idNeonato) => {
    router.push({
        name : 'Prontuario-Page',
        query: {id: idNeonato},
    });
};
</script>

<template>
    <card-formulario title="Painel de Monitoramento Epidemiológico Neonatal">
        <v-container class="pa-4 fill-height">
            <v-row dense class="h-100" align="stretch">
                <!-- Coluna lateral -->
                <v-col cols="12" md="4" class="d-flex flex-column">
                    <v-card elevation="2" class="pa-2 bg-grey-lighten-4 flex-grow-1">
                        <v-card-title class="text-subtitle-1 font-weight-medium">
                            Admissões Recentes
                        </v-card-title>
                        <v-divider class="mb-2"></v-divider>

                        <v-list density="comfortable" class="pb-4">
                            <v-list-item
                                v-for="neonato in ultimosNeonatos"
                                :key="neonato.idNeonato"
                                @click="verProntuario(neonato.idNeonato)"
                                class="neonato-item"
                            >
                                <v-list-item-title class="font-weight-bold text-primary">
                                    #{{ neonato.protocolo }}
                                </v-list-item-title>
                                <v-list-item-subtitle class="text-body-2">
                                    <v-row dense no-gutters>
                                        <v-col cols="12"><strong>Nasc:</strong> {{ neonato.dataNascimento }}</v-col>
                                        <v-col cols="12"><strong>Cadastro:</strong> {{ neonato.dataCadastro }}</v-col>
                                        <v-col cols="12"><strong>Usuário:</strong> {{ neonato.usuario }}</v-col>
                                    </v-row>
                                </v-list-item-subtitle>
                            </v-list-item>
                        </v-list>
                    </v-card>
                </v-col>

                <!-- Coluna com o gráfico -->
                <v-col cols="12" md="8" class="d-flex flex-column">
                    <v-card class="pa-4 bg-grey-lighten-4" elevation="2">
                        <ChartInternacoesPorDia :dados="internacoesPorDia"/>
                    </v-card>
                    <v-card class="pa-4 mt-3 bg-grey-lighten-4" elevation="2">
                        <ChartInfeccoesPorAgente :dados="infeccoesPorAgente"/>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <v-container class="pa-4 fill-height">
            <v-row dense class="h-100" align="stretch">
                <v-col cols="12" md="6" class="d-flex flex-column">
                    <v-card elevation="2" class="pa-2 bg-grey-lighten-4 flex-grow-1">
                        <ChartDistribuicaoPerfis :dados="distribuicaoPerfis"/>
                    </v-card>
                </v-col>

                <v-col cols="12" md="6" class="d-flex flex-column">
                    <v-card class="pa-4 bg-grey-lighten-4" elevation="2">
                        <ChartDistribuicaoMecanismos :dados="distribuicaoMecanismos"/>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <v-container class="pa-4 fill-height">
            <v-row dense class="h-100" align="stretch">
                <v-col cols="12" class="d-flex flex-column">
                    <v-card elevation="2" class="pa-2 bg-grey-lighten-4 flex-grow-1">
                        <ChartResistenciaPorAntimicrobiano :dados="resistenciaPorAntimicrobiano" />
                    </v-card>
                </v-col>
            </v-row>
        </v-container>


    </card-formulario>
</template>

<style scoped>

.neonato-item {
    border-bottom: 1px solid #e0e0e0;
    padding-bottom: 8px;
    margin-bottom: 8px;
    cursor: pointer;
    transition: background-color 0.2s ease;
}

.neonato-item:hover {
    background-color: #f9f9f9;
}
</style>
