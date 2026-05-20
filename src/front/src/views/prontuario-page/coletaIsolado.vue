<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import { loading } from "@/plugins/loadingService";
import { getOptionsAutocomplete } from "@/service/common/utils";
import { serviceLoadColetaIsolado, serviceSaveIsoladoColeta } from "@/service/prontuario";
import { setNotification } from "@/plugins/notificationService";

const props = defineProps({
    idNeonato: { type: [String, Number], default: '' },
    onSave   : { type: Function, required: true }
});

const optionsMicroorganismos     = ref([]);
const optionsAntimicrobianos     = ref([]);
const optionsResistencia         = ref([]);
const optionsPerfilResistencia   = ref([]);
const optionsMecanismoReistencia = ref([]);
const coletasIsolados            = ref([]);

const modalAberto  = ref(false);
const indiceEdicao = ref(null);

const coletaAtual = computed(() =>
    indiceEdicao.value !== null ? coletasIsolados.value[indiceEdicao.value] : null
);

onMounted(async () => {
    loading.show();
    await Promise.all([getOpcoesAutocomplete(), carregarDadosIsolados()]);
    loading.hide();
});

const carregarDadosIsolados = async () => {
    coletasIsolados.value = await serviceLoadColetaIsolado(props.idNeonato);
    for (const coleta of coletasIsolados.value) {
        if (coleta.antibiogramas.length === 0) adicionarAntibiograma(coleta);
    }
};

const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([
        getOptionsAutocomplete({
            idColumn         : 'id_microorganismo',
            descColumn       : 'acronimo',
            tableName        : 'microorganismo',
            additionalColumns: ['id_classificacao_microorganismo', 'id_genero']
        }),
        getOptionsAutocomplete({
            idColumn         : 'id_antimicrobiano',
            descColumn       : `CONCAT(descricao, ' (', (SELECT descricao FROM classe_antimicrobiano ca WHERE ca.id_classe_antimicrobiano = t.id_classe_antimicrobiano), ')')`,
            tableName        : 'antimicrobiano',
            additionalColumns: ['id_classe_antimicrobiano']
        }),
        getOptionsAutocomplete({ idColumn: 'id_resistencia_microorganismo',          descColumn: 'descricao', tableName: 'resistencia_microorganismo' }),
        getOptionsAutocomplete({ idColumn: 'id_perfil_resistencia_microorganismo',   descColumn: 'descricao', tableName: 'perfil_resistencia_microorganismo' }),
        getOptionsAutocomplete({ idColumn: 'id_mecanismo_resistencia_microorganismo',descColumn: 'descricao', tableName: 'mecanismo_resistencia_microorganismo' }),
    ]);

    optionsMicroorganismos.value     = results[0];
    optionsAntimicrobianos.value     = results[1];
    optionsResistencia.value         = results[2];
    optionsPerfilResistencia.value   = results[3];
    optionsMecanismoReistencia.value = results[4];
};

const adicionarAntibiograma = coleta => {
    coleta.antibiogramas.push({
        idAntibiogramaIsolado      : null,
        idAntimicrobiano           : null,
        idResistenciaMicroorganismo: null,
        isActive                   : true
    });
};

const verificarResistencias = coleta => {
    const microganismo = optionsMicroorganismos.value.find(i => i.value === coleta.idMicroorganismo);
    const idGenero     = microganismo.additionalData.id_genero;
    const tipo         = microganismo.additionalData.id_classificacao_microorganismo;

    if (tipo === 3) return;

    const staphResistantOxacillin = coleta.antibiogramas.some(ab => {
        if (!ab.idResistenciaMicroorganismo) return false;
        return idGenero === 17 && ab.idResistenciaMicroorganismo === 2 && ab.idAntimicrobiano === 9;
    });
    if (staphResistantOxacillin) {
        coleta.idPerfilResistenciaMicroorganismo    = 2;
        coleta.idMecanismoResistenciaMicroorganismo = 2;
        setNotification(`Perfil de resistência MDR detectado`, "warning");
        return;
    }

    const verificarORS = coleta.antibiogramas.some(ab => {
        if (!ab.idResistenciaMicroorganismo) return false;
        return idGenero === 17 && ab.idResistenciaMicroorganismo === 1 && coleta.idMecanismoResistenciaMicroorganismo === 2 && ab.idAntimicrobiano === 9;
    });
    if (verificarORS) {
        coleta.idMecanismoResistenciaMicroorganismo = 1;
        setNotification(`Perfil de resistência MDR detectado`, "warning");
        return;
    }

    const totalClassesSet     = new Set();
    const resistantClassesSet = new Set();

    coleta.antibiogramas.forEach(ab => {
        const antimicrobiano = optionsAntimicrobianos.value.find(item => item.value === ab.idAntimicrobiano);
        if (antimicrobiano?.additionalData) {
            const classeId = antimicrobiano.additionalData.id_classe_antimicrobiano;
            if (classeId) {
                totalClassesSet.add(classeId);
                if (ab.idResistenciaMicroorganismo === 2) resistantClassesSet.add(classeId);
            }
        }
    });

    const totalClasses    = totalClassesSet.size;
    const resistantClasses = resistantClassesSet.size;
    let idPerfil           = 1;

    if (totalClasses > 0 && resistantClasses === totalClasses)          idPerfil = 4;
    else if (totalClasses > 1 && resistantClasses === totalClasses - 1) idPerfil = 3;
    else if (resistantClasses >= 3)                                      idPerfil = 2;
    else if (resistantClasses > 0 && resistantClasses <= 2)             idPerfil = 5;

    coleta.idPerfilResistenciaMicroorganismo = idPerfil;

    const msgs = { 4: ['PDR', 'warning'], 3: ['XDR', 'warning'], 2: ['MDR', 'warning'], 5: ['RESISTENTE', 'warning'] };
    if (msgs[idPerfil]) setNotification(`Perfil de resistência ${msgs[idPerfil][0]} detectado`, msgs[idPerfil][1]);
    else setNotification(`Perfil de resistência AUSENTE`, "info");
};

const verificarPerfilResistencia = coleta => {
    if (coleta.idMecanismoResistenciaMicroorganismo !== 1) {
        coleta.idPerfilResistenciaMicroorganismo = 2;
        setNotification(`Perfil de resistência MDR detectado`, "warning");
    } else {
        verificarResistencias(coleta);
    }
};

const verificarSeFungo = coleta => {
    const microganismo = optionsMicroorganismos.value.find(i => i.value === coleta.idMicroorganismo);
    const tipo         = microganismo.additionalData.id_classificacao_microorganismo;

    coleta.fungo                                = tipo === 3;
    coleta.idMecanismoResistenciaMicroorganismo = 1;

    if (coleta.fungo && (coleta.idPerfilResistenciaMicroorganismo !== 1 || coleta.idPerfilResistenciaMicroorganismo !== 5 || coleta.idPerfilResistenciaMicroorganismo === null)) {
        coleta.idPerfilResistenciaMicroorganismo    = 1;
        coleta.idMecanismoResistenciaMicroorganismo = null;
    }
};

watch(coletasIsolados, () => { props.onSave(coletasIsolados.value); }, { deep: true });

// ── Display helpers ──────────────────────────────────────────
const abrirModal = (i) => { indiceEdicao.value = i; modalAberto.value = true; };

const fecharModal = () => { modalAberto.value = false; };

const salvarEFechar = async () => {
    const coleta = coletaAtual.value;

    if (coleta?.idMicroorganismo) {
        loading.show();

        for (const ab of coleta.antibiogramas) {
            ab.isActive = !!(ab.idAntimicrobiano && ab.idResistenciaMicroorganismo);
        }

        await serviceSaveIsoladoColeta([coleta]);
        loading.hide();
    }

    fecharModal();
};

const labelOf = (options, id) => options?.find(o => o.value === id)?.label ?? '—';

const perfilMeta = {
    1: { text: 'Ausente',    cls: 'badge--ausente'    },
    2: { text: 'MDR',        cls: 'badge--mdr'        },
    3: { text: 'XDR',        cls: 'badge--xdr'        },
    4: { text: 'PDR',        cls: 'badge--pdr'        },
    5: { text: 'Resistente', cls: 'badge--resistente' },
};

const perfilClass = (id) => perfilMeta[id]?.cls ?? '';
const perfilText  = (id) => perfilMeta[id]?.text ?? labelOf(optionsPerfilResistencia.value, id);

const resistClass = (id) => {
    if (id === 1) return 'resist--s';
    if (id === 2) return 'resist--r';
    return 'resist--i';
};

const antibiogramasVisiveis = (coleta) =>
    coleta.antibiogramas.filter(ab => ab.idAntimicrobiano);

const opcoesPerfilFiltradas = (coleta) =>
    (optionsPerfilResistencia.value || []).filter(o => coleta.fungo ? [1, 5].includes(o.value) : true);
</script>


<template>
    <div class="isolados-wrap">

        <!-- Lista de cards ── modo leitura -->
        <div v-if="coletasIsolados.length === 0" class="isolados-empty">
            <i class="fa-solid fa-flask-vial isolados-empty__icon" />
            <p>Nenhum isolado registrado para este neonato.</p>
        </div>

        <div
            v-for="(coleta, i) in coletasIsolados"
            :key="i"
            class="isolado-card"
            :class="{ 'isolado-card--desconsiderado': coleta.desconsiderarColeta }"
        >
            <!-- Cabeçalho -->
            <div class="isolado-card__head">
                <div class="isolado-card__meta">
                    <span class="isolado-card__date">
                        <i class="fa-regular fa-calendar" />
                        {{ coleta.dataEvento || '—' }}
                    </span>
                    <span class="isolado-card__local">
                        <i class="fa-solid fa-location-dot" />
                        {{ coleta.descricao || 'Local não informado' }}
                    </span>
                </div>
                <button class="isolado-edit-btn" @click="abrirModal(i)">
                    <i class="fa-solid fa-pen-to-square" />
                    Editar
                </button>
            </div>

            <!-- Aviso desconsiderado -->
            <div v-if="coleta.desconsiderarColeta" class="isolado-card__alert">
                <i class="fa-solid fa-triangle-exclamation" />
                Desconsiderado — resultado idêntico registrado em intervalo inferior a 15 dias
            </div>

            <!-- Observação -->
            <p v-if="coleta.observacao" class="isolado-card__obs">
                <i class="fa-regular fa-note-sticky" />
                {{ coleta.observacao }}
            </p>

            <!-- Corpo -->
            <div class="isolado-card__body">

                <!-- Microorganismo -->
                <div class="isolado-info">
                    <span class="isolado-info__label">Microorganismo</span>
                    <span class="isolado-info__value" :class="{ 'isolado-info__value--empty': !coleta.idMicroorganismo }">
                        {{ labelOf(optionsMicroorganismos, coleta.idMicroorganismo) }}
                    </span>
                </div>

                <!-- Antibiograma -->
                <div v-if="antibiogramasVisiveis(coleta).length" class="antibiogram">
                    <span class="isolado-info__label">Antibiograma</span>
                    <div
                        v-for="(ab, j) in antibiogramasVisiveis(coleta)"
                        :key="j"
                        class="antibiogram__row"
                    >
                        <span class="antibiogram__drug">{{ labelOf(optionsAntimicrobianos, ab.idAntimicrobiano) }}</span>
                        <span :class="['resist-chip', resistClass(ab.idResistenciaMicroorganismo)]">
                            {{ labelOf(optionsResistencia, ab.idResistenciaMicroorganismo) }}
                        </span>
                    </div>
                </div>

                <!-- Badges de perfil e mecanismo -->
                <div class="isolado-card__badges">
                    <span
                        v-if="coleta.idPerfilResistenciaMicroorganismo"
                        :class="['profile-badge', perfilClass(coleta.idPerfilResistenciaMicroorganismo)]"
                    >
                        {{ perfilText(coleta.idPerfilResistenciaMicroorganismo) }}
                    </span>
                    <span v-if="!coleta.fungo && coleta.idMecanismoResistenciaMicroorganismo" class="mech-chip">
                        {{ labelOf(optionsMecanismoReistencia, coleta.idMecanismoResistenciaMicroorganismo) }}
                    </span>
                </div>
            </div>
        </div>


        <!-- Modal de edição -->
        <v-dialog v-model="modalAberto" max-width="600" scrollable>
            <v-card v-if="coletaAtual" rounded="lg">
                <div class="modal-header">
                    <div>
                        <div class="modal-header__title">Editar Isolado</div>
                        <div class="modal-header__sub">
                            {{ coletaAtual.dataEvento }} · {{ coletaAtual.descricao || 'Local não informado' }}
                        </div>
                    </div>
                    <button class="modal-close" @click="fecharModal">
                        <i class="fa-solid fa-xmark" />
                    </button>
                </div>

                <v-card-text class="modal-body">

                    <div class="modal-section">
                        <v-autocomplete
                            label="Microorganismo (Isolado)"
                            :items="optionsMicroorganismos"
                            v-model="coletaAtual.idMicroorganismo"
                            @update:model-value="verificarSeFungo(coletaAtual)"
                        />
                    </div>

                    <div class="modal-section">
                        <div class="modal-section__header">
                            <span class="modal-section__title">Antibiograma</span>
                            <button class="add-btn" @click="adicionarAntibiograma(coletaAtual)">
                                <i class="fa-solid fa-plus" /> Adicionar
                            </button>
                        </div>

                        <div
                            v-for="(ab, j) in coletaAtual.antibiogramas"
                            :key="j"
                            class="ab-row"
                        >
                            <v-autocomplete
                                label="Antimicrobiano"
                                :items="optionsAntimicrobianos"
                                v-model="ab.idAntimicrobiano"
                                @update:model-value="verificarResistencias(coletaAtual)"
                                class="ab-row__drug"
                            />
                            <v-autocomplete
                                label="Resistência"
                                :items="optionsResistencia"
                                v-model="ab.idResistenciaMicroorganismo"
                                @update:model-value="verificarResistencias(coletaAtual)"
                                class="ab-row__resist"
                            />
                        </div>
                    </div>

                    <div class="modal-section">
                        <span class="modal-section__title">Perfil de Resistência</span>
                        <v-radio-group v-model="coletaAtual.idPerfilResistenciaMicroorganismo" inline hide-details class="mt-2">
                            <v-radio
                                v-for="opt in opcoesPerfilFiltradas(coletaAtual)"
                                :key="opt.value"
                                :label="opt.label"
                                :value="opt.value"
                            />
                        </v-radio-group>
                    </div>

                    <div v-if="!coletaAtual.fungo" class="modal-section">
                        <span class="modal-section__title">Mecanismo de Resistência</span>
                        <v-radio-group
                            v-model="coletaAtual.idMecanismoResistenciaMicroorganismo"
                            inline
                            hide-details
                            class="mt-2"
                            @update:model-value="verificarPerfilResistencia(coletaAtual)"
                        >
                            <v-radio
                                v-for="opt in optionsMecanismoReistencia"
                                :key="opt.value"
                                :label="opt.label"
                                :value="opt.value"
                            />
                        </v-radio-group>
                    </div>

                </v-card-text>

                <div class="modal-footer">
                    <button class="modal-btn modal-btn--primary" @click="salvarEFechar">
                        <i class="fa-solid fa-check" /> Salvar
                    </button>
                </div>
            </v-card>
        </v-dialog>

    </div>
</template>


<style lang="scss" scoped>
.isolados-wrap {
    padding: 16px 0 8px;
}

/* ── Empty state ── */
.isolados-empty {
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 48px 16px;
    color: #CBD5E1;
    gap: 12px;

    &__icon { font-size: 2rem; }
    p { font-size: 0.875rem; margin: 0; }
}

/* ── Card ── */
.isolado-card {
    background: #fff;
    border: 1px solid #E2E8F0;
    border-radius: 12px;
    margin-bottom: 14px;
    overflow: hidden;
    transition: box-shadow 0.15s;

    &:hover { box-shadow: 0 2px 8px rgba(0,0,0,0.07); }

    &--desconsiderado { border-color: #FDE68A; }

    &__head {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 14px 16px 10px;
        border-bottom: 1px solid #F1F5F9;
        gap: 12px;
        flex-wrap: wrap;
    }

    &__meta {
        display: flex;
        flex-direction: column;
        gap: 3px;
    }

    &__date {
        font-size: 0.875rem;
        font-weight: 700;
        color: #0f2a45;
        display: flex;
        align-items: center;
        gap: 6px;

        i { font-size: 0.75rem; color: #0882a0; }
    }

    &__local {
        font-size: 0.78rem;
        color: #64748B;
        display: flex;
        align-items: center;
        gap: 6px;

        i { font-size: 0.68rem; color: #94A3B8; }
    }

    &__alert {
        display: flex;
        align-items: flex-start;
        gap: 8px;
        padding: 8px 16px;
        background: #FFFBEB;
        color: #92400E;
        font-size: 0.75rem;
        font-weight: 500;
        border-bottom: 1px solid #FDE68A;

        i { flex-shrink: 0; margin-top: 1px; }
    }

    &__obs {
        padding: 8px 16px 0;
        font-size: 0.78rem;
        color: #64748B;
        margin: 0;
        display: flex;
        align-items: flex-start;
        gap: 6px;

        i { flex-shrink: 0; margin-top: 2px; color: #94A3B8; }
    }

    &__body {
        padding: 14px 16px 16px;
        display: flex;
        flex-direction: column;
        gap: 12px;
    }

    &__badges {
        display: flex;
        flex-wrap: wrap;
        gap: 6px;
        margin-top: 4px;
    }
}

/* ── Edit button ── */
.isolado-edit-btn {
    display: inline-flex;
    align-items: center;
    gap: 6px;
    padding: 5px 14px;
    border-radius: 7px;
    font-size: 0.78rem;
    font-weight: 600;
    cursor: pointer;
    border: 1.5px solid #b2dce6;
    color: #0882a0;
    background: #f0f9fb;
    transition: background 0.15s;
    white-space: nowrap;

    i { font-size: 0.7rem; }
    &:hover { background: #e0f4f8; border-color: #0882a0; }
    &:active { transform: scale(0.98); }
}

/* ── Info row ── */
.isolado-info {
    display: flex;
    flex-direction: column;
    gap: 2px;

    &__label {
        font-size: 0.7rem;
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 0.6px;
        color: #94A3B8;
    }

    &__value {
        font-size: 0.875rem;
        font-weight: 600;
        color: #1E293B;

        &--empty { color: #CBD5E1; font-weight: 400; font-style: italic; }
    }
}

/* ── Antibiogram ── */
.antibiogram {
    display: flex;
    flex-direction: column;
    gap: 6px;

    .isolado-info__label { margin-bottom: 2px; }

    &__row {
        display: flex;
        align-items: center;
        justify-content: space-between;
        padding: 6px 10px;
        background: #F8FAFC;
        border-radius: 7px;
        gap: 8px;
    }

    &__drug {
        font-size: 0.82rem;
        color: #334155;
        flex: 1;
        min-width: 0;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }
}

/* ── Resistance chip ── */
.resist-chip {
    font-size: 0.68rem;
    font-weight: 700;
    padding: 2px 9px;
    border-radius: 10px;
    white-space: nowrap;
    flex-shrink: 0;

    &--s { background: #DCFCE7; color: #166534; }
    &--r { background: #FEE2E2; color: #991B1B; }
    &--i { background: #FEF9C3; color: #854D0E; }
}

/* ── Profile badge ── */
.profile-badge {
    font-size: 0.72rem;
    font-weight: 700;
    padding: 3px 10px;
    border-radius: 20px;
    white-space: nowrap;

    &--ausente    { background: #F1F5F9; color: #475569; }
    &--resistente { background: #FEF9C3; color: #854D0E; }
    &--mdr        { background: #FFEDD5; color: #9A3412; }
    &--xdr        { background: #FEE2E2; color: #991B1B; }
    &--pdr        { background: #fce7f3; color: #9d174d; }
}

/* ── Mechanism chip ── */
.mech-chip {
    font-size: 0.72rem;
    font-weight: 600;
    padding: 3px 10px;
    border-radius: 20px;
    background: #EFF6FF;
    color: #1E40AF;
    white-space: nowrap;
}

/* ── Modal ── */
.modal-header {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    padding: 20px 20px 14px;
    border-bottom: 1px solid #F1F5F9;
    gap: 12px;

    &__title {
        font-size: 1rem;
        font-weight: 700;
        color: #0f2a45;
        line-height: 1.3;
    }

    &__sub {
        font-size: 0.78rem;
        color: #64748B;
        margin-top: 2px;
    }
}

.modal-close {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 30px;
    height: 30px;
    border-radius: 7px;
    border: none;
    background: #F1F5F9;
    color: #64748B;
    cursor: pointer;
    flex-shrink: 0;
    font-size: 0.9rem;
    transition: background 0.15s;

    &:hover { background: #E2E8F0; color: #0f2a45; }
}

.modal-body {
    padding: 16px 20px !important;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.modal-section {
    &__header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        margin-bottom: 10px;
    }

    &__title {
        font-size: 0.72rem;
        font-weight: 700;
        text-transform: uppercase;
        letter-spacing: 0.7px;
        color: #94A3B8;
        display: block;
        margin-bottom: 10px;
    }
}

.ab-row {
    display: grid;
    grid-template-columns: 1fr 160px;
    gap: 10px;
    margin-bottom: 8px;

    @media (max-width: 480px) { grid-template-columns: 1fr; }
}

.add-btn {
    display: inline-flex;
    align-items: center;
    gap: 5px;
    padding: 4px 12px;
    border-radius: 6px;
    font-size: 0.75rem;
    font-weight: 600;
    cursor: pointer;
    border: 1.5px solid #b2dce6;
    color: #0882a0;
    background: transparent;
    transition: background 0.15s;

    i { font-size: 0.62rem; }
    &:hover { background: #f0f9fb; }
}

.modal-footer {
    display: flex;
    justify-content: flex-end;
    padding: 12px 20px 16px;
    border-top: 1px solid #F1F5F9;
}

.modal-btn {
    display: inline-flex;
    align-items: center;
    gap: 7px;
    padding: 8px 20px;
    border-radius: 8px;
    font-size: 0.875rem;
    font-weight: 600;
    cursor: pointer;
    transition: background 0.15s, transform 0.1s;
    white-space: nowrap;
    border: none;

    &:active { transform: scale(0.98); }

    &--primary {
        background: #0882a0;
        color: #fff;
        box-shadow: 0 1px 3px rgba(8,130,160,0.25);

        &:hover { background: #07718c; }
    }
}
</style>
