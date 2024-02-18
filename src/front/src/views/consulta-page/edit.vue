<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
// import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceLoad} from "@/service/consulta";
import {getIdFromUrl, formatarTelefone, getOptionsAutocomplete} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";
import defaultImagePath from "@/assets/no_image.png";


const router      = useRouter();
const id_paciente = ref(getIdFromUrl('id_paciente'));
const id_consulta = ref(getIdFromUrl('id_consulta'));

const optionsMedicamentos        = ref([]);
const optionsSuplemento          = ref([]);
const optionsTipoInformacaoSaude = ref([]);
const optionsPeriodo             = ref([]);
const optionsFrequencia          = ref([]);
const optionsTipoConsulta        = ref([]);
const optionsDiagonostico        = ref([]);

onMounted(async () => {
    loading.show()

    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_medicamento',
        descColumn: 'nome',
        tableName : 'medicamento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_suplemento',
        descColumn: 'nome',
        tableName : 'suplemento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_tipo_informacao_saude',
        descColumn: 'nome',
        tableName : 'tipo_informacao_saude'
    }), getOptionsAutocomplete({
        idColumn  : 'id_periodo',
        descColumn: 'descricao',
        tableName : 'periodo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_frequencia',
        descColumn: 'descricao',
        tableName : 'frequencia'
    }), getOptionsAutocomplete({
        idColumn  : 'id_tipo_consulta',
        descColumn: 'descricao',
        tableName : 'tipo_consulta'
    }), getOptionsAutocomplete({
        idColumn  : 'id_diagnostico',
        descColumn: 'CONCAT(nome, IFNULL(CONCAT(" (cid: ", codigo_cid, ")"), ""))',
        tableName : 'diagnostico'
    }),]);

    optionsMedicamentos.value        = results[0];
    optionsSuplemento.value          = results[1];
    optionsTipoInformacaoSaude.value = results[2];
    optionsPeriodo.value             = results[3];
    optionsFrequencia.value          = results[4];
    optionsTipoConsulta.value        = results[5];
    optionsDiagonostico.value        = results[6];

    if (id_paciente.value > 0) {
        const data = await serviceLoad(id_paciente.value, id_consulta.value);
        console.log(data)
        Object.assign(consulta, data);
    }

    loading.hide()
});


const arrMedicamentoUsoPaciente  = reactive([]);
const arrSuplementoUsoPaciente   = reactive([]);
const arrInformacaoSaudeTemplate = reactive([])
const arrPrescricaoMedicamento   = reactive([])
const arrPrescricaoSuplemento    = reactive([])

const consulta = reactive({
    id_paciente             : null,
    id_consulta             : null,
    foto_perfil             : null,
    nome                    : null,
    idade                   : null,
    telefone_1              : null,
    telefone_2              : null,
    plano_saude             : null,
    id_tipo_consulta        : null,
    convenio                : null,
    observacoes             : null,
    sintomas                : null,
    id_diagonostico_multiple: null
});


const medicamentoUsoPacienteTemplate = {
    id_paciente_medicamento: null,
    id_consulta            : null,
    id_medicamento         : null,
    dosagem                : null,
    id_frequencia          : null,
    id_periodo             : null,
    is_active              : null,
}

const suplementoUsoPacienteTemplate = {
    id_paciente_suplemento: null,
    id_consulta           : null,
    id_suplemento         : null,
    dosagem               : null,
    id_frequencia         : null,
    id_periodo            : null,
    is_active             : null,
}

const informacaoSaudeTemplate = {
    id_consulta_informacao_saude: null,
    id_consulta                 : null,
    id_tipo_informacao_saude    : null,
    valor                       : null,
    is_active                   : null,
}

const priscricaoMedicamentoTemplate = {
    id_prescricao_medicamento: null,
    id_consulta              : null,
    id_medicamento           : null,
    dosagem                  : null,
    instrucoes               : null,
    id_frequencia            : null,
    inicio_tratamento        : null,
    fim_tratamento           : null,
    is_active                : null
}

const priscricaoSuplementoTemplate = {
    id_prescricao_suplemento: null,
    id_consulta             : null,
    id_suplemento           : null,
    dosagem                 : null,
    instrucoes              : null,
    id_frequencia           : null,
    inicio_tratamento       : null,
    fim_tratamento          : null,
    is_active               : null
}

const addMedicamentoUsoPaciente = () => {
    const novoMedicamentoUsoPaciente = {...medicamentoUsoPacienteTemplate};
    arrMedicamentoUsoPaciente.push(novoMedicamentoUsoPaciente);
};

const addSuplementoUsoPaciente = () => {
    const novoSuplementoUsoPaciente = {...suplementoUsoPacienteTemplate};
    arrSuplementoUsoPaciente.push(novoSuplementoUsoPaciente);
};

const addInformacaoSaude = () => {
    const novoInformacaoSaudeTemplate = {...informacaoSaudeTemplate};
    arrInformacaoSaudeTemplate.push(novoInformacaoSaudeTemplate);
};

const addPrescricaoMedicamento = () => {
    const novaPrescricaoMedicamento = {...priscricaoMedicamentoTemplate};
    arrPrescricaoMedicamento.push(novaPrescricaoMedicamento);
};

const addPrescricaoSuplemento = () => {
    const novaPrescricaoSuplemento = {...priscricaoSuplementoTemplate};
    arrPrescricaoSuplemento.push(novaPrescricaoSuplemento);
};

const handleSave = async () => {
    loading.show()
    console.log(consulta);
    console.log(arrMedicamentoUsoPaciente)
    loading.hide()
};


const handleBack = () => {
    router.push({name: 'Paciente-List'});
};


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const removerMedicamentoPaciente = (med, index) => {
    if (med.id_paciente_medicamento) {
        arrMedicamentoUsoPaciente[index].is_active = 0;
    } else {
        arrMedicamentoUsoPaciente.splice(index, 1);
    }
}

const removerSuplementoPaciente = (sup, index) => {
    if (sup.id_paciente_suplemento) {
        arrSuplementoUsoPaciente[index].is_active = 0;
    } else {
        arrSuplementoUsoPaciente.splice(index, 1);
    }
}

const removerInformacaoSaude = (info, index) => {
    if (info.id_consulta_informacao_saude) {
        arrInformacaoSaudeTemplate[index].is_active = 0;
    } else {
        arrInformacaoSaudeTemplate.splice(index, 1);
    }
}

const removerPrescricaoMedicamento = (info, index) => {
    if (info.id_consulta_informacao_saude) {
        arrPrescricaoMedicamento[index].is_active = 0;
    } else {
        arrPrescricaoMedicamento.splice(index, 1);
    }
}

const removerPrescricaoSuplemento = (info, index) => {
    if (info.id_consulta_informacao_saude) {
        arrPrescricaoSuplemento[index].is_active = 0;
    } else {
        arrPrescricaoSuplemento.splice(index, 1);
    }
}


</script>

<template>
    <card-formulario title="Consulta de Paciente"
                     subtitle="Registro e Diagnóstico de Consultas"
                     @handleSave="handleSave"
                     @handleBack="handleBack">
        <v-row>
            <v-col cols="12" md="3" lg="2" class="d-flex justify-center align-center">
                <v-avatar size="120" class="ma-1">
                    <img :src="getProfilePhoto(consulta.foto_perfil)" class="fit-cover">
                </v-avatar>
            </v-col>

            <v-col cols="12" md="8" lg="8">
                <div class="text-h5 mb-2"><b>Paciente:</b> {{ consulta.nome }}</div>
                <div class="text-subtitle-1 mb-2"><b>Idade:</b> {{ consulta.idade }} anos</div>
                <div class="text-subtitle-1 mb-2">
                    <b>Telefone:</b> {{ formatarTelefone(consulta.telefone_1) }} / {{ formatarTelefone(consulta.telefone_2) }}
                </div>
                <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ consulta.plano_saude }}</div>
            </v-col>

            <v-col cols="12" md="2" lg="2">
                <v-row class="justify-end mt-1">
                    <v-autocomplete
                        label="Tipo da Consulta"
                        :items="optionsTipoConsulta"
                        v-model="consulta.id_tipo_consulta"
                    />
                </v-row>
            </v-col>
        </v-row>

        <v-divider class="border-opacity-100 mt-2 mb-3" color="primary"/>

        <v-row>

            <v-col cols="12" md="4" lg="4">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Sintomas do Paciente
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">
                                <v-textarea
                                    label="Descrição"
                                    variant="outlined"
                                    color="primary"
                                    v-model="consulta.sintomas"
                                />
                            </div>
                        </div>
                    </v-card-item>
                </v-card>
            </v-col>


            <v-col cols="12" md="4" lg="4">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Medicamentos de uso do Paciente
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">

                                <v-row
                                    v-for="(med, i) in arrMedicamentoUsoPaciente.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="2" lg="2" md="2" sm="2" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerMedicamentoPaciente(med, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>


                                    <v-col cols="9" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Medicamento"
                                            :items="optionsMedicamentos"
                                            v-model="med.id_medicamento"
                                        />
                                    </v-col>

                                    <text-input
                                        v-model="med.dosagem"
                                        label="Dose (mg/ml)"
                                        type="number"
                                        xl="4" lg="4" md="4"
                                    />

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Frequência"
                                            :items="optionsFrequencia"
                                            v-model="med.id_frequencia"
                                        />
                                    </v-col>

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Período"
                                            :items="optionsPeriodo"
                                            v-model="med.id_periodo"
                                        />
                                    </v-col>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addMedicamentoUsoPaciente">
                            Adicionar
                        </v-btn>
                    </v-card-actions>

                </v-card>
            </v-col>


            <v-col cols="12" md="4" lg="4">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Suplementos de uso do Paciente
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">

                                <v-row
                                    v-for="(sup, i) in arrSuplementoUsoPaciente.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="2" lg="2" md="2" sm="2" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerSuplementoPaciente(sup, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>

                                    <v-col cols="9" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Suplemento"
                                            :items="optionsSuplemento"
                                            v-model="sup.id_suplemento"
                                        />
                                    </v-col>

                                    <text-input
                                        v-model="sup.dosagem"
                                        label="Dose (mg/ml)"
                                        type="number"
                                        xl="4" lg="4" md="4"
                                    />

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Frequência"
                                            :items="optionsFrequencia"
                                            v-model="sup.id_frequencia"
                                        />
                                    </v-col>

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Período"
                                            :items="optionsPeriodo"
                                            v-model="sup.id_periodo"
                                        />
                                    </v-col>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addSuplementoUsoPaciente">
                            Adicionar
                        </v-btn>
                    </v-card-actions>

                </v-card>
            </v-col>
        </v-row>


        <v-divider class="border-opacity-100 mt-4 mb-3" color="primary"/>


        <v-row>
            <v-col cols="12" md="4" lg="4">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Informações Gerais Saúde
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">

                                <v-row
                                    v-for="(info, i) in arrInformacaoSaudeTemplate.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="2" lg="2" md="2" sm="2" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerInformacaoSaude(info, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>

                                    <v-col cols="9" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Selecione"
                                            :items="optionsTipoInformacaoSaude"
                                            v-model="info.id_tipo_informacao_saude"
                                        />
                                    </v-col>

                                    <text-input
                                        v-model="info.valor"
                                        label="Valor"
                                        type="text"
                                        xl="4" lg="4" md="4"
                                    />

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addInformacaoSaude">
                            Adicionar
                        </v-btn>
                    </v-card-actions>

                </v-card>
            </v-col>


            <v-col cols="12" md="8" lg="8">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Diagnósticos
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">
                                <v-col class="pb-0">
                                    <v-autocomplete
                                        label="Diagnósticos"
                                        :items="optionsDiagonostico"
                                        v-model="consulta.id_diagonostico_multiple"
                                        :chips="true"
                                        :multiple="true"
                                    />
                                </v-col>
                            </div>
                        </div>
                    </v-card-item>

                </v-card>
            </v-col>

            <v-divider class="border-opacity-100 mt-4 mb-3" color="primary"/>


            <v-col cols="12" md="6" lg="6">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Prescrição Medicamentos
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">

                                <v-row
                                    v-for="(med, i) in arrPrescricaoMedicamento.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="1" lg="1" md="1" sm="1" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerPrescricaoMedicamento(med, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>


                                    <v-col cols="9" sm="7" md="7" lg="7" xl="7" class="pb-0">
                                        <v-autocomplete
                                            label="Medicamento"
                                            :items="optionsMedicamentos"
                                            v-model="med.id_medicamento"
                                        />
                                    </v-col>

                                    <text-input
                                        v-model="med.dosagem"
                                        label="Dose (mg/ml)"
                                        type="number"
                                        xl="4" lg="4" md="4"
                                    />

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Frequência"
                                            :items="optionsFrequencia"
                                            v-model="med.id_frequencia"
                                        />
                                    </v-col>

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Período"
                                            :items="optionsPeriodo"
                                            v-model="med.id_periodo"
                                        />
                                    </v-col>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addPrescricaoMedicamento">
                            Adicionar
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>


            <v-col cols="12" md="6" lg="6">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Prescrição Suplementos
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">

                                <v-row
                                    v-for="(med, i) in arrPrescricaoSuplemento.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="1" lg="1" md="1" sm="1" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerPrescricaoSuplemento(med, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>


                                    <v-col cols="9" sm="7" md="7" lg="7" xl="7" class="pb-0">
                                        <v-autocomplete
                                            label="Medicamento"
                                            :items="optionsMedicamentos"
                                            v-model="med.id_medicamento"
                                        />
                                    </v-col>

                                    <text-input
                                        v-model="med.dosagem"
                                        label="Dose (mg/ml)"
                                        type="number"
                                        xl="4" lg="4" md="4"
                                    />

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Frequência"
                                            :items="optionsFrequencia"
                                            v-model="med.id_frequencia"
                                        />
                                    </v-col>

                                    <v-col cols="12" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                        <v-autocomplete
                                            label="Período"
                                            :items="optionsPeriodo"
                                            v-model="med.id_periodo"
                                        />
                                    </v-col>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addPrescricaoSuplemento">
                            Adicionar
                        </v-btn>
                    </v-card-actions>

                </v-card>
            </v-col>


        </v-row>


    </card-formulario>


</template>

<style scoped>
.scale-80 {
    scale: 0.8;
}
</style>