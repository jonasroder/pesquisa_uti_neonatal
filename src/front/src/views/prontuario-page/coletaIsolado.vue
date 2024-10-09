<script setup>
import {onMounted, ref} from 'vue';
import {loading} from "@/plugins/loadingService";
import {getOptionsAutocomplete} from "@/service/common/utils";


const props = defineProps({
    coletasIsolados: {
        type   : Array,
        default: () => []
    }
});


onMounted(async () => {
    loading.show();
    await getOpcoesAutocomplete();
    loading.hide();
});


const coletasIsolados            = ref(props.coletasIsolados);
const optionsMicroorganismos     = ref();
const optionsAntimicrobianos     = ref();
const optionsResistencia         = ref();
const optionsPerfilResistencia   = ref();
const optionsMecanismoReistencia = ref();



const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_microorganismo',
        descColumn: 'acronimo',
        tableName : 'microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_classe_antimicrobiano',
        descColumn: 'descricao',
        tableName : 'antimicrobiano'
    }), getOptionsAutocomplete({
        idColumn  : 'id_resistencia_microorganismo',
        descColumn: 'descricao',
        tableName : 'resistencia_microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_perfil_resistencia_microorganismo',
        descColumn: 'descricao',
        tableName : 'perfil_resistencia_microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_mecanismo_resistencia_microorganismo',
        descColumn: 'descricao',
        tableName : 'mecanismo_resistencia_microorganismo'
    })]);

    optionsMicroorganismos.value     = results[0];
    optionsAntimicrobianos.value     = results[1];
    optionsResistencia.value         = results[2];
    optionsPerfilResistencia.value   = results[2];
    optionsMecanismoReistencia.value = results[3];
};

const adicionarAntibiograma = (coleta) => {
    coleta.antibiogramaIsolado.push({
        id_antibiograma              : "",
        id_coleta                    : "",
        id_antimicrobiano            : "",
        id_resistencia_microorganismo: "",
    });
}

</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-row>
                <!-- Itera sobre o array coletasIsolados e cria um card para cada item -->
                <v-col v-for="(coleta, index) in coletasIsolados" :key="index" cols="12" md="4">
                    <v-card>
                        <v-card-title>
                            {{ coleta.dataEvento }}
                        </v-card-title>

                        <v-card-subtitle>
                            Local: {{ coleta.descricao || 'Sem descrição' }}
                        </v-card-subtitle>

                        <v-card-subtitle v-if="coleta.observacao">
                            Observação: {{ coleta.observacao || 'Nenhuma observação' }}
                        </v-card-subtitle>

                        <v-col cols="12" class="pb-0">
                            <v-autocomplete
                                label="Isolado"
                                :items="optionsMicroorganismos"
                                v-model="coleta.idEvento"
                            />
                        </v-col>

                        <v-card-subtitle class="d-flex justify-space-between pb-1">
                            Antibiograma

                            <v-btn size="x-small" icon color="cinzaAzulado" @click="adicionarAntibiograma(coleta)">
                                <v-icon size="small">fas fa-plus</v-icon>
                                <v-tooltip text="Adicionar novo Antimicrobiano"/>
                            </v-btn>

                        </v-card-subtitle>

                        <v-row v-for="(antibiograma, i) in coleta.antibiogramaIsolado" :key="i" class="ma-0 pb-0">
                            <v-col cols="12" sm="12" md="7" lg="7" xl="7" class="pb-0">
                                <v-autocomplete
                                    label="Antimicrobiano"
                                    :items="optionsAntimicrobianos"
                                    v-model="antibiograma.id_antimicrobiano"
                                />
                            </v-col>

                            <v-col cols="12" sm="12" md="5" lg="5" xl="5" class="pb-0">
                                <v-autocomplete
                                    label="Resistencia"
                                    :items="optionsResistencia"
                                    v-model="antibiograma.id_resistencia_microorganismo"
                                />
                            </v-col>

                            <v-col cols="12" class="pb-0">
                                <v-divider class="mb-5 pb-0"/>
                            </v-col>
                        </v-row>

                        <v-card-subtitle>
                            Classificação pelo perfil de resistência
                        </v-card-subtitle>

                        <v-col cols="12">
                            <v-radio-group inline v-model="coleta.selectedValue">
                                <v-radio
                                    v-for="option in optionsPerfilResistencia"
                                    :key="option.value"
                                    :label="option.label"
                                    :value="option.value"
                                ></v-radio>
                            </v-radio-group>
                        </v-col>

                        <v-card-subtitle>
                            Mecanismos de resistência
                        </v-card-subtitle>

                        <v-col cols="12">
                            <v-radio-group inline v-model="coleta.selectedValue3">
                                <v-radio
                                    v-for="option in optionsMecanismoReistencia"
                                    :key="option.value"
                                    :label="option.label"
                                    :value="option.value"
                                ></v-radio>
                            </v-radio-group>
                        </v-col>


                    </v-card>
                </v-col>
            </v-row>
        </v-col>
    </v-row>
</template>
