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
        campos: ['prontuario', 'dataNascimento', 'dataInternacao']
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
    dialog.value = false;
};


const cancelar = () => {
    dialog.value = false;
};


const validateDates = () => {
    if (!neonato.dataDesfecho || !neonato.dataInternacao) return

    const dataDesfecho   = new Date(neonato.dataDesfecho)
    const dataInternacao = new Date(neonato.dataInternacao)

    if (dataDesfecho < dataInternacao) {
        setNotification(`Erro: A Data de Desfecho não pode ser menor que a Data de Internação`, "error");
    }

    const diffDays = Math.abs(dataDesfecho - dataInternacao) / (1000 * 60 * 60 * 24)

    if (diffDays > 90) {
        setNotification(`A Diferença entre as datas de internação e desfecho é superior a 90 dias, verifique se está correto!`, "warning");
    }
}


watch(() => [neonato.dataDesfecho, neonato.dataInternacao], () => validateDates())


const validateAusenciaDates = () => {
    neonato.ausenciaUTI.forEach(ausencia => {
        if (!ausencia.dataSaidaUti || !ausencia.dataRetornoUti) return;
        const dataSaida   = new Date(ausencia.dataSaidaUti);
        const dataRetorno = new Date(ausencia.dataRetornoUti);

        if (dataRetorno < dataSaida) {
            setNotification('Erro: A Data de Retorno UTI não pode ser menor que a Data de Saída UTI', 'error');
        }

        const diffDays = Math.abs(dataRetorno - dataSaida) / (1000 * 60 * 60 * 24);
        if (diffDays > 90) {
            setNotification('Aviso: Diferença entre as datas de saída e retorno da UTI superior a 90 dias, verifique se está correto!', 'warning');
        }
    });
};

watch(() => neonato.ausenciaUTI.map(item => [item.dataSaidaUti, item.dataRetornoUti]), () => validateAusenciaDates(), {deep: true});

</script>


<template>
    <card-formulario title="Cadastro de Neonato"
                     subtitle="Você pode editar o formulário a qualquer momento">

        <v-dialog v-model="dialog" max-width="460">
            <v-card rounded="lg">
                <v-card-title class="dialog-title">Confirmar Exclusão</v-card-title>
                <v-card-text class="dialog-text">
                    Deseja realmente apagar este neonato? Esta ação não pode ser desfeita.
                </v-card-text>
                <v-card-actions class="dialog-actions">
                    <v-spacer />
                    <button class="form-btn form-btn--ghost" @click="cancelar">Cancelar</button>
                    <button class="form-btn form-btn--danger" @click="confirmarApagar">
                        <i class="fa-solid fa-trash form-btn__icon" />
                        Confirmar
                    </button>
                </v-card-actions>
            </v-card>
        </v-dialog>

        <div class="form-body">

            <!-- Barra de ações -->
            <div class="form-topbar">
                <a v-if="id" class="record-link" @click.prevent="verProntuario(id)" href="#">
                    <i class="fa-solid fa-file-medical record-link__icon" />
                    Ver Prontuário
                    <i class="fa-solid fa-arrow-up-right-from-square record-link__arrow" />
                </a>
                <div v-else class="record-link record-link--disabled">
                    <i class="fa-solid fa-file-medical record-link__icon" />
                    Prontuário disponível após salvar
                </div>
                <button v-if="roleUsuario === 1 && id" class="form-btn form-btn--danger-outline" @click="apagarNeonato">
                    <i class="fa-solid fa-trash form-btn__icon" />
                    Excluir
                </button>
            </div>

            <!-- Seção: Identificação -->
            <div class="form-section">
                <div class="form-section__header">
                    <span class="form-section__title">Identificação</span>
                </div>
                <v-row align="start">
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="Prontuário *"
                            v-model="neonato.prontuario"
                            :error="!neonato.prontuario && !camposObrigatorios"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="Data de Nascimento *"
                            type="date"
                            v-model="neonato.dataNascimento"
                            :error="!neonato.dataNascimento && !camposObrigatorios"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="Data de Internação *"
                            type="date"
                            v-model="neonato.dataInternacao"
                            :error="!neonato.dataInternacao && !camposObrigatorios"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Sexo"
                            :items="optionsSexo"
                            v-model="neonato.idSexo"
                        />
                    </v-col>
                </v-row>
            </div>

            <!-- Seção: Dados Clínicos -->
            <div class="form-section">
                <div class="form-section__header">
                    <span class="form-section__title">Dados Clínicos</span>
                </div>
                <v-row align="start">
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="Peso ao Nascimento (g)"
                            type="number"
                            v-model="neonato.pesoGramas"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Classificação do Peso"
                            :items="optionsPesoNascimento"
                            v-model="neonato.idPesoNascimento"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Local de Nascimento"
                            :items="optionsLocalNascimento"
                            v-model="neonato.idLocalNascimento"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Idade Gestacional"
                            :items="optionsIdadeGestacional"
                            v-model="neonato.idIdadeGestacional"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Tipo de Parto"
                            :items="optionsTipoParto"
                            v-model="neonato.idTipoParto"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Bolsa Rota"
                            :items="optionsRoturaMembrana"
                            v-model="neonato.idRoturaMembrana"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="APGAR 1'"
                            type="number"
                            v-model="neonato.apgar1"
                            :rules="apgarRules"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="APGAR 5'"
                            type="number"
                            v-model="neonato.apgar5"
                            :rules="apgarRules"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Causa da Internação"
                            :items="optionsMotivoInternacao"
                            v-model="neonato.idMotivoInternacao"
                        />
                    </v-col>
                    <v-col cols="12" sm="12" md="8" lg="6">
                        <v-autocomplete
                            label="Sítio de Malformação Congênita"
                            :items="optionsSitioMalformacao"
                            v-model="neonato.idSitioMalformacao"
                            multiple
                            chips
                            closable-chips
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <div class="radio-field">
                            <span class="radio-field__label">Gemelar</span>
                            <div class="radio-field__options">
                                <label :class="['radio-btn', { 'radio-btn--on': neonato.gemelar === true }]">
                                    <input type="radio" :value="true" v-model="neonato.gemelar" />
                                    Sim
                                </label>
                                <label :class="['radio-btn', { 'radio-btn--on': neonato.gemelar === false }]">
                                    <input type="radio" :value="false" v-model="neonato.gemelar" />
                                    Não
                                </label>
                            </div>
                        </div>
                    </v-col>
                </v-row>
            </div>

            <!-- Seção: Desfecho -->
            <div class="form-section">
                <div class="form-section__header">
                    <span class="form-section__title">Desfecho</span>
                </div>
                <v-row align="start">
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-text-field
                            label="Data do Desfecho"
                            type="date"
                            v-model="neonato.dataDesfecho"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <v-autocomplete
                            label="Causa do Óbito"
                            :items="optionsCausaObito"
                            v-model="neonato.idCausaObito"
                        />
                    </v-col>
                    <v-col cols="12" sm="6" md="4" lg="3">
                        <div class="radio-field">
                            <span class="radio-field__label">Óbito</span>
                            <div class="radio-field__options">
                                <label :class="['radio-btn', 'radio-btn--danger', { 'radio-btn--on': neonato.obito === true }]">
                                    <input type="radio" :value="true" v-model="neonato.obito" />
                                    Sim
                                </label>
                                <label :class="['radio-btn', { 'radio-btn--on': neonato.obito === false }]">
                                    <input type="radio" :value="false" v-model="neonato.obito" />
                                    Não
                                </label>
                            </div>
                        </div>
                    </v-col>
                </v-row>
            </div>

            <!-- Seção: Ausências na UTI -->
            <div class="form-section">
                <div class="form-section__header">
                    <span class="form-section__title">Períodos de Ausência na UTI</span>
                    <button class="form-btn form-btn--add" @click="adicionarPeriodoAusencia">
                        <i class="fa-solid fa-plus form-btn__icon" />
                        Adicionar Período
                    </button>
                </div>

                <div
                    v-for="(ausencia, i) in neonato.ausenciaUTI"
                    :key="i"
                    class="ausencia-item"
                >
                    <span class="ausencia-item__num">{{ i + 1 }}</span>
                    <div class="ausencia-item__fields">
                        <v-text-field
                            label="Data de Saída da UTI"
                            type="date"
                            v-model="ausencia.dataSaidaUti"
                            hide-details="auto"
                        />
                        <v-text-field
                            label="Data de Retorno à UTI"
                            type="date"
                            v-model="ausencia.dataRetornoUti"
                            hide-details="auto"
                        />
                    </div>
                </div>
            </div>

        </div>
    </card-formulario>
</template>


<style lang="scss" scoped>
.form-body {
    padding: 24px 24px 36px;

    @media (max-width: 600px) { padding: 16px 12px 28px; }
}

/* ── Barra de ações ── */
.form-topbar {
    display: flex;
    align-items: center;
    justify-content: space-between;
    flex-wrap: wrap;
    gap: 10px;
    margin-bottom: 28px;
}

.record-link {
    display: inline-flex;
    align-items: center;
    gap: 7px;
    font-size: 0.83rem;
    font-weight: 600;
    color: #0882a0;
    text-decoration: none;
    padding: 7px 14px;
    border: 1.5px solid #b2dce6;
    border-radius: 8px;
    background: #f0f9fb;
    transition: background 0.15s, border-color 0.15s;

    &:hover { background: #e0f4f8; border-color: #0882a0; }

    &__icon  { font-size: 0.8rem; }
    &__arrow { font-size: 0.6rem; opacity: 0.65; }

    &--disabled {
        color: #94A3B8;
        border-color: #E2E8F0;
        background: #F8FAFC;
        pointer-events: none;
        font-weight: 500;
    }
}

/* ── Seções ── */
.form-section {
    margin-bottom: 32px;

    &__header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding-bottom: 10px;
        margin-bottom: 16px;
        border-bottom: 1.5px solid #F1F5F9;
    }

    &__title {
        font-size: 0.72rem;
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 0.9px;
        color: #94A3B8;
    }
}

/* ── Botões de ação ── */
.form-btn {
    display: inline-flex;
    align-items: center;
    gap: 7px;
    padding: 7px 16px;
    border-radius: 7px;
    font-size: 0.82rem;
    font-weight: 600;
    cursor: pointer;
    transition: background 0.15s, transform 0.1s;
    white-space: nowrap;

    &:active { transform: scale(0.98); }
    &__icon  { font-size: 0.68rem; }

    &--ghost {
        background: #F1F5F9;
        color: #475569;
        border: 1.5px solid #E2E8F0;

        &:hover { background: #E2E8F0; }
    }

    &--danger {
        background: #DC2626;
        color: #fff;
        border: none;

        &:hover { background: #B91C1C; }
    }

    &--danger-outline {
        background: transparent;
        color: #DC2626;
        border: 1.5px solid #FECACA;

        &:hover { background: #FEF2F2; border-color: #DC2626; }
    }

    &--add {
        background: transparent;
        color: #0882a0;
        border: 1.5px solid #b2dce6;
        padding: 5px 12px;
        font-size: 0.78rem;

        &:hover { background: #f0f9fb; border-color: #0882a0; }
    }
}

/* ── Radio Sim/Não ── */
.radio-field {
    display: flex;
    flex-direction: column;
    gap: 8px;

    &__label {
        font-size: 0.82rem;
        color: #94A3B8;
        font-weight: 400;
        padding-left: 2px;
    }

    &__options {
        display: flex;
        gap: 6px;
    }
}

.radio-btn {
    display: inline-flex;
    align-items: center;
    padding: 5px 18px;
    border-radius: 20px;
    font-size: 0.82rem;
    font-weight: 500;
    cursor: pointer;
    border: 1.5px solid #E2E8F0;
    color: #94A3B8;
    background: #F8FAFC;
    transition: all 0.15s;
    user-select: none;

    input[type="radio"] { display: none; }

    &:hover:not(.radio-btn--on) { border-color: #CBD5E1; color: #64748B; }

    &--on {
        background: #e6f5f8;
        border-color: #0882a0;
        color: #0882a0;
        font-weight: 600;
    }

    &--danger.radio-btn--on {
        background: #FEF2F2;
        border-color: #DC2626;
        color: #DC2626;
    }
}

/* ── Períodos de ausência ── */
.ausencia-item {
    display: flex;
    align-items: flex-start;
    gap: 14px;
    background: #F8FAFC;
    border: 1px solid #F1F5F9;
    border-radius: 10px;
    padding: 14px;
    margin-bottom: 12px;

    &__num {
        display: flex;
        align-items: center;
        justify-content: center;
        width: 26px;
        height: 26px;
        border-radius: 50%;
        background: #E2E8F0;
        color: #475569;
        font-size: 0.72rem;
        font-weight: 700;
        flex-shrink: 0;
        margin-top: 10px;
    }

    &__fields {
        flex: 1;
        display: grid;
        grid-template-columns: 1fr 1fr;
        gap: 12px;

        @media (max-width: 600px) { grid-template-columns: 1fr; }
    }
}

/* ── Dialog ── */
.dialog-title {
    font-size: 1rem !important;
    font-weight: 700;
    color: #0f2a45;
    padding: 20px 20px 8px !important;
}

.dialog-text {
    font-size: 0.875rem;
    color: #475569;
    padding: 0 20px 16px !important;
}

.dialog-actions {
    padding: 0 16px 16px !important;
    gap: 8px;
}
</style>