<script setup>
import {onMounted, ref, watch} from 'vue';
import {loading} from "@/plugins/loadingService";
import {getOptionsAutocomplete} from "@/service/common/utils";
import {serviceLoadColetaIsolado} from "@/service/prontuario";
import {setNotification} from "@/plugins/notificationService";


const props = defineProps({
    idNeonato: {
        type   : [String, Number],
        default: ''
    },
    onSave   : {
        type    : Function,
        required: true
    }
});

const optionsMicroorganismos     = ref();
const optionsAntimicrobianos     = ref();
const optionsResistencia         = ref();
const optionsPerfilResistencia   = ref();
const optionsMecanismoReistencia = ref();
const coletasIsolados            = ref([{
    idMicroorganismo                    : null,
    idMecanismoResistenciaMicroorganismo: null,
    idPerfilResistenciaMicroorganismo   : null,
    desconsiderarColeta                 : false,
    antibiogramas                       : []
}]);



onMounted(async () => {
    loading.show();
    await Promise.all([getOpcoesAutocomplete(), carregarDadosIsolados()]);
    loading.hide();
});


const carregarDadosIsolados = async () => {
    coletasIsolados.value = await serviceLoadColetaIsolado(props.idNeonato);
    for (const coleta of coletasIsolados.value) {

        console.log(coleta)
        if (coleta.antibiogramas.length === 0) {
            adicionarAntibiograma(coleta);
        }
    }
};



const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn: 'id_microorganismo',
        descColumn: 'acronimo',
        tableName: 'microorganismo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_antimicrobiano',
        descColumn: `CONCAT(descricao, ' (', (SELECT descricao FROM classe_antimicrobiano ca WHERE ca.id_classe_antimicrobiano = t.id_classe_antimicrobiano), ')')`,
        tableName : 'antimicrobiano',
        additionalColumns: ['id_classe_antimicrobiano']
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
    optionsPerfilResistencia.value   = results[3];
    optionsMecanismoReistencia.value = results[4];
};



const adicionarAntibiograma = (coleta) => {
    coleta.antibiogramas.push({
        idAntibiogramaIsolado      : null,
        idAntimicrobiano           : null,
        idResistenciaMicroorganismo: null,
        isActive                   : true,
    });
}


const verificarResistencias = (coleta) => {
    const antibiogramas = coleta.antibiogramas;
    const classesAntimicrobianos = new Set();

    for (const antibiograma of antibiogramas) {
        const idAntimicrobiano = antibiograma.idAntimicrobiano;
        const idResistencia = antibiograma.idResistenciaMicroorganismo;

        if (idResistencia === 2) {
            const antimicrobiano = optionsAntimicrobianos.value.find(
                (item) => item.value === idAntimicrobiano
            );

            if (antimicrobiano && antimicrobiano.additionalData) {
                const classeId = antimicrobiano.additionalData.id_classe_antimicrobiano;
                if (classeId) {
                    classesAntimicrobianos.add(classeId);
                }
            }
        }
    }

    if(classesAntimicrobianos.size >= 3){
        coleta.idPerfilResistenciaMicroorganismo = 2;
        setNotification(`Perfil de resistência MDR detectado`, "warning");
    } else if(classesAntimicrobianos.size < 3 && coleta.idMecanismoResistenciaMicroorganismo === 1 && coleta.idPerfilResistenciaMicroorganismo !== 1) {
        coleta.idPerfilResistenciaMicroorganismo = 1;
        setNotification(`Alteração no perfil de resistência`, "warning");
    }
};


const verificarPerfilResistencia = (coleta) => {
    if(coleta.idMecanismoResistenciaMicroorganismo !== 1){
        coleta.idPerfilResistenciaMicroorganismo = 2;
        setNotification(`Perfil de resistência MDR detectado`, "warning");
    } else {
        verificarResistencias(coleta);
    }
}


watch(coletasIsolados, () => {
    props.onSave(coletasIsolados.value); // Quando o pai invocar o save, passamos os dados de volta
});

</script>

<template>
    <v-row>
        <v-col cols="12">
            <v-row>
                <!-- Itera sobre o array coletasIsolados e cria um card para cada item -->
                <v-col v-for="(coleta, index) in coletasIsolados" :key="index" cols="12" md="4" class="mb-4">
                    <v-card>
                        <v-card-title>
                            {{ coleta.dataEvento }}
                        </v-card-title>

                        <v-alert
                            v-if="coleta.desconsiderarColeta"
                            density="compact"
                            text="DESCONSIDERADO POR EXISTIR UM RESULTADO IDÊNTICO REGISTRADO EM UM INTERVALO INFERIOR A 15 DIAS"
                            type="warning"
                            class="ma-3"
                        />

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
                                v-model="coleta.idMicroorganismo"
                            />
                        </v-col>

                        <v-card-subtitle class="d-flex justify-space-between pb-1">
                            Antibiogramas
                        </v-card-subtitle>

                        <v-row v-for="(antibiograma, i) in coleta.antibiogramas" :key="i" class="ma-0 pb-0">
                            <v-col cols="12" sm="12" md="7" lg="7" xl="7" class="pb-0">
                                <v-autocomplete
                                    label="Antimicrobiano"
                                    :items="optionsAntimicrobianos"
                                    v-model="antibiograma.idAntimicrobiano"
                                    @update:model-value="verificarResistencias(coleta)"
                                />
                            </v-col>

                            <v-col cols="12" sm="12" md="5" lg="5" xl="5" class="pb-0">
                                <v-autocomplete
                                    label="Resistencia"
                                    :items="optionsResistencia"
                                    v-model="antibiograma.idResistenciaMicroorganismo"
                                    @update:model-value="verificarResistencias(coleta)"
                                />
                            </v-col>

                            <v-col cols="12" class="pb-0">
                                <v-divider class="pb-0"/>
                            </v-col>
                        </v-row>

                        <v-col cols="12" class="pb-0 mb-5">
                            <v-btn block size="small" color="cinzaAzulado" @click="adicionarAntibiograma(coleta)">
                                Adicionar Antibiograma
                                <v-tooltip text="Adicionar novo Antimicrobiano"/>
                            </v-btn>
                        </v-col>

                        <v-card-subtitle>
                            Perfil de resistência
                        </v-card-subtitle>

                        <v-col cols="12">
                            <v-radio-group inline v-model="coleta.idPerfilResistenciaMicroorganismo">
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
                            <v-radio-group
                                inline
                                v-model="coleta.idMecanismoResistenciaMicroorganismo"
                                @update:model-value="verificarPerfilResistencia(coleta)"
                            >
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
