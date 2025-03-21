<script setup>
import {onMounted, reactive, ref, watch} from 'vue';
import {useRouter} from 'vue-router';
import {serviceSave, serviceLoad, serviceApagarNeonato} from "@/service/neonato";
import {
    getIdFromUrl, adicionarParametrosURL, verificarCamposObrigatorios, getOptionsAutocomplete
} from "@/service/common/utils";
import {loading} from "@/plugins/loadingService.js";
import CardFormulario from "@/components/CardFormulario.vue";
import {getSessionUserData} from "@/service/common/tokenService";
import {setNotification} from "@/plugins/notificationService";


const roleUsuario             = ref(getSessionUserData().id_role);
const id                      = ref(getIdFromUrl());
const emit                    = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const cpfDisabled             = ref(false);
const router                  = useRouter();
const camposObrigatorios      = ref(true);
const optionsSexo             = ref();
const optionsPesoNascimento   = ref();
const optionsLocalNascimento  = ref();
const optionsMotivoInternacao = ref();
const optionsIdadeGestacional = ref();
const optionsTipoParto        = ref();
const optionsRoturaMembrana   = ref();
const optionsSitioMalformacao = ref();
const optionsSitioCirurgia    = ref();
const optionsCausaObito       = ref();
const dialog                  = ref(false);


const neonato = reactive({
    idNeonato         : null,
    nomeMae           : null,
    prontuario        : null,
    dataNascimento    : null,
    dataInternacao    : null,
    dataDesfecho      : null,
    obito             : false,
    gemelar           : false,
    apgar1            : null,
    apgar5            : null,
    pesoGramas        : null,
    idSexo            : null,
    idPesoNascimento  : null,
    idMotivoInternacao: null,
    idLocalNascimento : null,
    idIdadeGestacional: null,
    idTipoParto       : null,
    idRoturaMembrana  : null,
    idSitioMalformacao: [],
    idSitioCirurgia   : null,
    idCausaObito      : null,
    riscoInfeccioso   : false,
    sepseClinica      : false,
    ausenciaUTI       : [{
        idNeonatosAusenciaUti: null,
        dataSaidaUti         : null,
        dataRetornoUti       : null,
    }]
});


onMounted(async () => {
    loading.show();
    await getOpcoesAutocomplete();

    if (id.value > 0) {
        await getDadosNeonato();
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide();
});


const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_sexo',
        descColumn: 'descricao',
        tableName : 'sexo'
    }), getOptionsAutocomplete({
        idColumn  : 'id_peso_nascimento',
        descColumn: 'descricao',
        tableName : 'peso_nascimento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_local_nascimento',
        descColumn: 'descricao',
        tableName : 'local_nascimento'
    }), getOptionsAutocomplete({
        idColumn  : 'id_motivo_internacao',
        descColumn: 'descricao',
        tableName : 'motivo_internacao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_idade_gestacional',
        descColumn: 'descricao',
        tableName : 'idade_gestacional'
    }), getOptionsAutocomplete({
        idColumn  : 'id_tipo_parto',
        descColumn: 'descricao',
        tableName : 'tipo_parto'
    }), getOptionsAutocomplete({
        idColumn  : 'id_rotura_membrana',
        descColumn: 'descricao',
        tableName : 'rotura_membrana'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_malformacao',
        descColumn: 'descricao',
        tableName : 'sitio_malformacao'
    }), getOptionsAutocomplete({
        idColumn  : 'id_sitio_cirurgia',
        descColumn: 'descricao',
        tableName : 'sitio_cirurgia'
    }), getOptionsAutocomplete({
        idColumn  : 'id_causa_obito',
        descColumn: 'descricao',
        tableName : 'causa_obito'
    })]);

    optionsSexo.value             = results[0];
    optionsPesoNascimento.value   = results[1];
    optionsLocalNascimento.value  = results[2];
    optionsMotivoInternacao.value = results[3];
    optionsIdadeGestacional.value = results[4];
    optionsTipoParto.value        = results[5];
    optionsRoturaMembrana.value   = results[6];
    optionsSitioMalformacao.value = results[7];
    optionsSitioCirurgia.value    = results[8];
    optionsCausaObito.value       = results[9];
};


const handleSave = async () => {
    loading.show();

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : neonato,
        campos: ['prontuario']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(neonato);

    if (res.success) {
        id.value          = res.id;
        neonato.idNeonato = res.id;
        cpfDisabled.value = true;
        adicionarParametrosURL({id: res.id});
        await getDadosNeonato();
    }

    loading.hide();
};


const getDadosNeonato = async () => {
    const resp = await serviceLoad(id.value);
    Object.assign(neonato, resp);
};


const handleBack = () => {
    router.push({name: 'Neonato-List'});
};


const apgarRules = ref([v => {
    if (v === '' || v == null) return true;
    return (Number.isInteger(Number(v))) || 'Apenas números inteiros são permitidos e o valor deve estar entre 0 e 10.';
}, v => {
    if (v === '' || v == null) return true;
    return (v >= 0 && v <= 10) || 'O valor deve estar entre 0 e 10.';
}]);


const adicionarPeriodoAusencia = async () => {
    neonato.ausenciaUTI.push({
        idNeonatosAusenciaUti: null,
        dataSaidaUti         : null,
        dataRetornoUti       : null,
    })
};


const verProntuario = (idNeonato) => {
    router.push({
        name : 'Prontuario-Page',
        query: {id: idNeonato}
    });
}



const apagarNeonato = () => {
    dialog.value = true;
};


const confirmarApagar = async () => {
    await serviceApagarNeonato(id.value);
    handleBack();
    dialog.value   = false;
};


const cancelar = () => {
    dialog.value   = false;
};


const validateDates = () => {
    if (!neonato.dataDesfecho || !neonato.dataInternacao) return

    const dataDesfecho = new Date(neonato.dataDesfecho)
    const dataInternacao = new Date(neonato.dataInternacao)

    if (dataDesfecho < dataInternacao) {
        setNotification(`Erro: A Data de Desfecho não pode ser menor que a Data de Internação`, "error");
    }

    const diffDays = Math.abs(dataDesfecho - dataInternacao) / (1000 * 60 * 60 * 24)

    if (diffDays > 90) {
        setNotification(`A Diferença entre as datas de internação e desfecho é superior a 90 dias, verifique se está correto!`, "warning");
    }
}


watch(
    () => [neonato.dataDesfecho, neonato.dataInternacao],
    () => validateDates()
)


const validateAusenciaDates = () => {
    neonato.ausenciaUTI.forEach(ausencia => {
        if (!ausencia.dataSaidaUti || !ausencia.dataRetornoUti) return;
        const dataSaida = new Date(ausencia.dataSaidaUti);
        const dataRetorno = new Date(ausencia.dataRetornoUti);

        if (dataRetorno < dataSaida) {
            setNotification(
                'Erro: A Data de Retorno UTI não pode ser menor que a Data de Saída UTI',
                'error'
            );
        }

        const diffDays = Math.abs(dataRetorno - dataSaida) / (1000 * 60 * 60 * 24);
        if (diffDays > 90) {
            setNotification(
                'Aviso: Diferença entre as datas de saída e retorno da UTI superior a 90 dias, verifique se está correto!',
                'warning'
            );
        }
    });
};

watch(
    () => neonato.ausenciaUTI.map(item => [item.dataSaidaUti, item.dataRetornoUti]),
    () => validateAusenciaDates(),
    { deep: true }
);

</script>


<template>
    <card-formulario title="Cadastro de Neonato"
                     subtitle="Você pode editar o formulário a qualquer momento">

        <v-btn v-if="roleUsuario === 1" color="red" small variant="elevated" class="elevation-2" @click="apagarNeonato()">
            Apagar Neonato
        </v-btn>


        <v-dialog v-model="dialog" max-width="500">
            <v-card>
                <v-card-title>Confirmar Exclusão</v-card-title>
                <v-card-text>
                    <p>Deseja realmente apagar o neonato?</p>
                </v-card-text>
                <v-card-actions>
                    <v-btn variant="text" @click="cancelar">Cancelar</v-btn>
                    <v-btn color="red" @click="confirmarApagar">Confirmar</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>


        <div class="d-flex justify-end mb-4" v-if="id">
            <a href="#" class="editable-name" @click.prevent="verProntuario(id)">
                <h3>Prontuário do Neonato <i class="fas fa-external-link-alt"></i></h3>
            </a>
        </div>

        <v-row>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Prontuário*"
                    type="text"
                    v-model="neonato.prontuario"
                    :error="!neonato.prontuario && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data de Nascimento"
                    type="date"
                    v-model="neonato.dataNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data de Internação"
                    type="date"
                    v-model="neonato.dataInternacao"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Sexo"
                    :items="optionsSexo"
                    v-model="neonato.idSexo"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Peso ao Nascimento (g)"
                    type="number"
                    v-model="neonato.pesoGramas"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Classificação do peso ao nascer"
                    :items="optionsPesoNascimento"
                    v-model="neonato.idPesoNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Local Nascimento"
                    :items="optionsLocalNascimento"
                    v-model="neonato.idLocalNascimento"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Idade Gestacional"
                    :items="optionsIdadeGestacional"
                    v-model="neonato.idIdadeGestacional"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Tipo de Parto"
                    :items="optionsTipoParto"
                    v-model="neonato.idTipoParto"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Bolsa Rota"
                    :items="optionsRoturaMembrana"
                    v-model="neonato.idRoturaMembrana"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="APGAR 1'"
                    type="number"
                    v-model="neonato.apgar1"
                    :rules="apgarRules"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="APGAR 5'"
                    type="number"
                    v-model="neonato.apgar5"
                    :rules="apgarRules"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Causa da Internação"
                    :items="optionsMotivoInternacao"
                    v-model="neonato.idMotivoInternacao"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Sítio Malformação Congênita"
                    :items="optionsSitioMalformacao"
                    v-model="neonato.idSitioMalformacao"
                    multiple
                    chips
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Data do Desfecho"
                    type="date"
                    v-model="neonato.dataDesfecho"
                />
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-autocomplete
                    label="Causa do Óbito"
                    :items="optionsCausaObito"
                    v-model="neonato.idCausaObito"
                />
            </v-col>
            <!--
                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-radio-group v-model="neonato.riscoInfeccioso" inline label="Risco Infeccioso">
                            <v-radio label="Sim" :value="true"/>
                            <v-radio label="Não" :value="false"/>
                        </v-radio-group>
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                        <v-radio-group v-model="neonato.sepseClinica" inline label="Sepse Clínica">
                            <v-radio label="Sim" :value="true"/>
                            <v-radio label="Não" :value="false"/>
                        </v-radio-group>
                    </v-col>
            -->

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-radio-group v-model="neonato.gemelar" inline label="Gemelar">
                    <v-radio label="Sim" :value="true"/>
                    <v-radio label="Não" :value="false"/>
                </v-radio-group>
            </v-col>

            <v-col cols="12" sm="12" md="6" lg="3" xl="3" class="pb-0">
                <v-radio-group v-model="neonato.obito" inline label="Óbito">
                    <v-radio label="Sim" :value="true"/>
                    <v-radio label="Não" :value="false"/>
                </v-radio-group>
            </v-col>
        </v-row>

        <v-row>
            <v-col cols="6">
                <v-col cols="12" class="d-flex align-center pb-1">
                    <h4 class="mr-2">Períodos de Ausência na UTI</h4>
                    <v-btn size="x-small" icon color="cinzaAzulado" @click="adicionarPeriodoAusencia">
                        <v-icon size="small">fas fa-plus</v-icon>
                        <v-tooltip text="Adicionar novo Período"/>
                    </v-btn>
                </v-col>

                <v-col cols="12" class="pb-2">
                    <v-divider></v-divider>
                </v-col>

                <v-row v-for="(ausencia, i) in neonato.ausenciaUTI" :key="i" class="ma-0 pb-0">
                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="pb-0">
                        <v-text-field
                            label="Data Saída UTI"
                            type="date"
                            v-model="ausencia.dataSaidaUti"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="pb-0">
                        <v-text-field
                            label="Data Retorno UTI"
                            type="date"
                            v-model="ausencia.dataRetornoUti"
                        />
                    </v-col>

                    <v-col cols="12" class="pb-0" v-if="neonato.ausenciaUTI > 1 || neonato.ausenciaUTI.length !== i+1">
                        <v-divider class="mb-5 pb-0"></v-divider>
                    </v-col>
                </v-row>
            </v-col>
        </v-row>

    </card-formulario>
</template>


<style scoped>

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