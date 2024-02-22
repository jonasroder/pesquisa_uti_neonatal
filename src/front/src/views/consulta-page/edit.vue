<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
// import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceLoad} from "@/service/consulta";
import {getIdFromUrl, formatarTelefone, getOptionsAutocomplete, adicionarParametrosURL} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";
import defaultImagePath from "@/assets/no_image.png";
import {serviceSave} from "@/service/consulta";


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
        Object.assign(consulta, data);
    }

    loading.hide()
});


const arrMedicamentoUsoPaciente = reactive([]);
const arrSuplementoUsoPaciente  = reactive([]);
const arrInformacaoSaude        = reactive([]);
const arrPrescricaoMedicamento  = reactive([]);
const arrPrescricaoSuplemento   = reactive([]);
const arrConsultaDiagnostico    = reactive([]);


const consulta = reactive({
    id_paciente              : id_paciente,
    id_consulta              : id_consulta,
    foto_perfil              : null,
    nome                     : null,
    idade                    : null,
    telefone_1               : null,
    telefone_2               : null,
    plano_saude              : null,
    id_tipo_consulta         : 1,
    convenio                 : null,
    observacoes              : null,
    sintomas                 : null,
    id_diagonostico_multiple : null,
    arrMedicamentoUsoPaciente: [],
    arrSuplementoUsoPaciente : [],
    arrInformacaoSaude       : [],
    arrPrescricaoMedicamento : [],
    arrPrescricaoSuplemento  : [],
    is_active                : 1,
});


const medicamentoUsoPacienteTemplate = {
    id_paciente_medicamento: null,
    id_consulta            : id_consulta,
    id_medicamento         : null,
    dosagem                : null,
    id_frequencia          : null,
    id_periodo             : null,
    is_active              : 1,
}

const suplementoUsoPacienteTemplate = {
    id_paciente_suplemento: null,
    id_consulta           : id_consulta,
    id_suplemento         : null,
    dosagem               : null,
    id_frequencia         : null,
    id_periodo            : null,
    is_active             : 1,
}

const informacaoSaudeTemplate = {
    id_consulta_informacao_saude: null,
    id_consulta                 : id_consulta,
    id_tipo_informacao_saude    : null,
    valor                       : null,
    is_active                   : 1,
}

const priscricaoMedicamentoTemplate = {
    id_prescricao_medicamento: null,
    id_consulta              : id_consulta,
    id_medicamento           : null,
    dosagem                  : null,
    instrucoes               : null,
    id_frequencia            : null,
    id_periodo               : null,
    is_active                : 1
}

const priscricaoSuplementoTemplate = {
    id_prescricao_suplemento: null,
    id_consulta             : id_consulta,
    id_suplemento           : null,
    dosagem                 : null,
    instrucoes              : null,
    id_frequencia           : null,
    id_periodo              : null,
    is_active               : 1
}

const consultaDiagnosticoTemplate = {
    id_consulta_diagnostico: null,
    id_consulta            : id_consulta,
    id_diagnostico         : null,
    is_active              : 1
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
    arrInformacaoSaude.push(novoInformacaoSaudeTemplate);
};

const addPrescricaoMedicamento = () => {
    const novaPrescricaoMedicamento = {...priscricaoMedicamentoTemplate};
    arrPrescricaoMedicamento.push(novaPrescricaoMedicamento);
};

const addPrescricaoSuplemento = () => {
    const novaPrescricaoSuplemento = {...priscricaoSuplementoTemplate};
    arrPrescricaoSuplemento.push(novaPrescricaoSuplemento);
};

const addConsultaDiagnostico = () => {
    const novoDiagnostico = {...consultaDiagnosticoTemplate};
    arrConsultaDiagnostico.push(novoDiagnostico);
};

const handleSave = async () => {
    loading.show()

    console.log(consulta);

    const data = {
        id_paciente              : consulta.id_paciente,
        id_consulta              : consulta.id_consulta,
        id_tipo_consulta         : consulta.id_tipo_consulta,
        observacoes              : consulta.observacoes,
        sintomas                 : consulta.sintomas,
        arrConsultaDiagnostico   : arrConsultaDiagnostico,
        arrInformacaoSaude       : arrInformacaoSaude,
        arrMedicamentoUsoPaciente: arrMedicamentoUsoPaciente,
        arrSuplementoUsoPaciente : arrSuplementoUsoPaciente,
        arrPrescricaoSuplemento  : arrPrescricaoSuplemento,
        arrPrescricaoMedicamento : arrPrescricaoMedicamento,
    }


    if (id_consulta.value > 0) {
        await serviceSave(data, 'update');
    } else {
        const res         = await serviceSave(data, 'insert');
        id_consulta.value = res.id;
        if (id_consulta.value > 0) {
            consulta.id_consulta = res.id;
            adicionarParametrosURL({id_paciente: res.id});
        }
    }


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
        arrInformacaoSaude[index].is_active = 0;
    } else {
        arrInformacaoSaude.splice(index, 1);
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


const removerConsultaDiagnostico = (info, index) => {
    if (info.id_consulta_diagnostico) {
        arrConsultaDiagnostico[index].is_active = 0;
    } else {
        arrConsultaDiagnostico.splice(index, 1);
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

            <v-col cols="12" md="4" lg="4">
                <div class="text-h5 mb-2"><b>Paciente:</b> {{ consulta.nome }}</div>
                <div class="text-subtitle-1 mb-2"><b>Idade:</b> {{ consulta.idade }} anos</div>
                <div class="text-subtitle-1 mb-2">
                    <b>Telefone:</b> {{ formatarTelefone(consulta.telefone_1) }} / {{ formatarTelefone(consulta.telefone_2) }}
                </div>
                <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ consulta.plano_saude }}</div>
            </v-col>

            <v-col cols="12" md="3" lg="3">
                <v-radio-group v-model="consulta.id_tipo_consulta" inline label="Tipo do Atendimento">
                    <v-radio color="primary" label="Consulta" :value="1"></v-radio>
                    <v-radio color="primary" label="Retorno" :value="2"></v-radio>
                </v-radio-group>
            </v-col>

            <v-col cols="12" md="3" lg="3">
                <v-row class="justify-end mr-2">
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="addAtividadeFisica">Formulário Atividade Física</v-btn>
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="addAlimentacao">Formulário Alimentação</v-btn>
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="addSono">Formulário Sono</v-btn>
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="addEscalaHamilton">Formulário Escala de Hamilton</v-btn>
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

                                    <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                        <v-text-field
                                            label="Dose (mg/ml)"
                                            type="text"
                                            v-model="med.dosagem"
                                        />
                                    </v-col>

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

                                    <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                        <v-text-field
                                            label="Dose (mg/ml)"
                                            type="text"
                                            v-model="sup.dosagem"
                                        />
                                    </v-col>

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
                                    v-for="(info, i) in arrInformacaoSaude.filter(item => item.is_active !== 0)"
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

                                    <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                        <v-text-field
                                            label="Valor"
                                            type="text"
                                            v-model="info.valor"
                                        />
                                    </v-col>

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

                                <v-row
                                    v-for="(info, i) in arrConsultaDiagnostico.filter(item => item.is_active !== 0)"
                                    :key="`med-${i}`"
                                    class="pb-0"
                                >

                                    <v-col xl="1" lg="1" md="1" sm="2" cols="3" class="scale-80 pt-2 pb-0">
                                        <v-btn icon x-small variant="elevated" @click="() => removerConsultaDiagnostico(info, i)">
                                            <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                        </v-btn>
                                    </v-col>

                                    <v-col cols="9" sm="10" md="11" lg="11" xl="11" class="pb-0">
                                        <v-autocomplete
                                            label="Diagnóstico"
                                            :items="optionsDiagonostico"
                                            v-model="info.id_diagnostico"
                                        />
                                    </v-col>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                </v-row>
                            </div>
                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addConsultaDiagnostico">
                            Adicionar
                        </v-btn>
                    </v-card-actions>

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

                                    <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                        <v-text-field
                                            label="Dose (mg/ml)"
                                            type="text"
                                            v-model="med.dosagem"
                                        />
                                    </v-col>

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


                                    <v-col cols="12" class="pb-0">
                                        <v-textarea
                                            label="Instruções"
                                            v-model="med.instrucoes"
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
                                            label="Suplemento"
                                            :items="optionsSuplemento"
                                            v-model="med.id_suplemento"
                                        />
                                    </v-col>

                                    <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                        <v-text-field
                                            label="Dose (mg/ml)"
                                            type="text"
                                            v-model="med.dosagem"
                                        />
                                    </v-col>

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

                                    <v-col cols="12" class="pb-0">
                                        <v-textarea
                                            label="Instruções"
                                            v-model="med.instrucoes"
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

            <v-divider class="border-opacity-100 mt-4 mb-3" color="primary"/>

            <v-col cols="12" md="6" lg="6">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Observações Gerais
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                            <div class="text-caption">
                                <v-textarea
                                    label="Observações"
                                    v-model="consulta.observacoes"
                                />
                            </div>
                        </div>
                    </v-card-item>
                </v-card>
            </v-col>

            <v-col cols="12" md="6" lg="6">
                <v-card class="mx-auto" variant="elevated">
                    <v-card-item>
                        <div>
                            <div class="text-h6 mb-1">
                                Anexos
                            </div>

                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                        </div>
                    </v-card-item>
                    <v-card-actions>
                        <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="adicionarAnexo">
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