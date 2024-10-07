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
    await buscarListaMicroorganismos();
    loading.hide();
});


const coletasIsolados        = ref(props.coletasIsolados);
const optionsMicroorganismos = ref();


const buscarListaMicroorganismos = async () => {
    optionsMicroorganismos.value = await getOptionsAutocomplete({
        idColumn  : 'id_microorganismo',
        descColumn: 'acronimo',
        tableName : 'microorganismo'
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

                        <v-card-text v-if="coleta.observacao">
                            Observação: {{ coleta.observacao || 'Nenhuma observação' }}
                        </v-card-text>

                        <v-col cols="12" class="pb-0">
                            <v-autocomplete
                                label="Isolado"
                                :items="optionsMicroorganismos"
                                v-model="coleta.idEvento"
                            />
                        </v-col>

                        <v-col cols="12">
                            <v-radio-group inline>
                                <v-radio
                                    label="Resistente"
                                    value="1"
                                ></v-radio>
                                <v-radio
                                    label="MDR"
                                    value="2"
                                ></v-radio>
                                <v-radio
                                    label="XDR"
                                    value="3"
                                ></v-radio>
                                <v-radio
                                    label="PDR"
                                    value="4"
                                ></v-radio>
                            </v-radio-group>
                        </v-col>

                    </v-card>
                </v-col>
            </v-row>
        </v-col>
    </v-row>
</template>
