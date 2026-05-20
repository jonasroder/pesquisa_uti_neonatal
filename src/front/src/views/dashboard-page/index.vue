<script setup>
import { ref, onMounted, computed } from 'vue';
import { loading } from '@/plugins/loadingService';
import { getDistribuicaoMecanismos, getDistribuicaoPerfis, getResistenciaPorAntimicrobiano, getInternacoesEmAberto, getListaInternacoesEmAberto, getMetricas, getRankingMicroorganismos, getDistribuicaoPeso, getPacientesSemEventos, serviceGetCadastrosPorDia, serviceGetColonizacoesPorAgente, serviceGetInfeccoesPorAgente, serviceLoadUltimosNeonatosCadastrados } from '@/service/dashboard';
import { useRouter } from 'vue-router';
import ChartInternacoesPorDia from "@/views/dashboard-page/ChartInternacoesPorDia.vue";
import ChartInfeccoesPorAgente from "@/views/dashboard-page/ChartInfeccoesPorAgente.vue";
import ChartResistenciaPorAntimicrobiano from "@/views/dashboard-page/ChartResistenciaPorAntimicrobiano.vue";
import ChartDistribuicaoMecanismos from "@/views/dashboard-page/ChartDistribuicaoMecanismos.vue";
import ChartDistribuicaoPerfis from "@/views/dashboard-page/ChartDistribuicaoPerfis.vue";
import ChartColonizacoesPorAgente from "@/views/dashboard-page/ChartColonizacoesPorAgente.vue";

const emit   = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router = useRouter();

const ultimosNeonatos              = ref([]);
const internacoesPorDia            = ref([]);
const infeccoesPorAgente           = ref([]);
const colonizacoesPorAgente        = ref([]);
const distribuicaoPerfis           = ref([]);
const distribuicaoMecanismos       = ref([]);
const resistenciaPorAntimicrobiano = ref([]);
const internacoesEmAberto          = ref(0);
const listaInternacoesEmAberto     = ref([]);
const listaExpandida               = ref(true);
const metricas                     = ref(null);
const rankingMicroorganismos       = ref([]);
const distribuicaoPeso             = ref([]);
const pacientesSemEventos          = ref([]);
const alertasExpandido             = ref(true);

onMounted(async () => {
    emit('set-show-buttons', false);
    loading.show();

    [
        ultimosNeonatos.value,
        internacoesPorDia.value,
        infeccoesPorAgente.value,
        colonizacoesPorAgente.value,
        distribuicaoPerfis.value,
        distribuicaoMecanismos.value,
        resistenciaPorAntimicrobiano.value,
        internacoesEmAberto.value,
        listaInternacoesEmAberto.value,
        metricas.value,
        rankingMicroorganismos.value,
        distribuicaoPeso.value,
        pacientesSemEventos.value
    ] = await Promise.all([
        serviceLoadUltimosNeonatosCadastrados(),
        serviceGetCadastrosPorDia(),
        serviceGetInfeccoesPorAgente(),
        serviceGetColonizacoesPorAgente(),
        getDistribuicaoPerfis(),
        getDistribuicaoMecanismos(),
        getResistenciaPorAntimicrobiano(),
        getInternacoesEmAberto(),
        getListaInternacoesEmAberto(),
        getMetricas(),
        getRankingMicroorganismos(),
        getDistribuicaoPeso(),
        getPacientesSemEventos()
    ]);

    loading.hide();
});

const verProntuario = (idNeonato) => {
    router.push({ name: 'Prontuario-Page', query: { id: idNeonato } });
};

const dataAtual = computed(() =>
    new Date().toLocaleDateString('pt-BR', {
        weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'
    })
);

const totalInternacoes30d = computed(() =>
    internacoesPorDia.value.reduce((sum, i) => sum + (i.total || 0), 0)
);

const totalInfeccoes180d = computed(() =>
    infeccoesPorAgente.value.reduce((sum, i) => sum + (i.total || 0), 0)
);

const totalColonizacoes180d = computed(() =>
    colonizacoesPorAgente.value.reduce((sum, i) => sum + (i.total || 0), 0)
);

const diasInternado = (dataBR) => {
    if (!dataBR) return 0;
    const [dia, mes, ano] = dataBR.split('/').map(Number);
    const internacao = new Date(ano, mes - 1, dia);
    const hoje = new Date();
    hoje.setHours(0, 0, 0, 0);
    return Math.max(0, Math.floor((hoje - internacao) / 86400000));
};

const diasSemEvento = (ultimoEventoBR) => {
    if (!ultimoEventoBR) return null;
    return diasInternado(ultimoEventoBR);
};

const maxRanking = computed(() =>
    Math.max(...rankingMicroorganismos.value.map(r => r.total), 1)
);

const totalRanking = computed(() =>
    rankingMicroorganismos.value.reduce((s, r) => s + r.total, 0) || 1
);

const maxPeso = computed(() =>
    Math.max(...distribuicaoPeso.value.map(r => r.total), 1)
);

const totalPeso = computed(() =>
    distribuicaoPeso.value.reduce((s, r) => s + r.total, 0) || 1
);

const pct = (value, total) => Math.round(value / total * 100);
</script>

<template>
    <div class="dashboard">

        <!-- Cabeçalho da página -->
        <div class="dash-header">
            <div>
                <h1 class="dash-header__title">Painel de Monitoramento</h1>
                <p class="dash-header__subtitle">Epidemiologia Neonatal &nbsp;·&nbsp; {{ dataAtual }}</p>
            </div>
        </div>

        <!-- KPIs — linha 1 -->
        <v-row class="mb-4">
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--blue">
                        <i class="fa-solid fa-baby" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ ultimosNeonatos.length }}</div>
                        <div class="kpi-card__label">Admissões recentes</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--teal">
                        <i class="fa-solid fa-hospital" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ totalInternacoes30d }}</div>
                        <div class="kpi-card__label">Internações (30 dias)</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--orange">
                        <i class="fa-solid fa-virus" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ totalInfeccoes180d }}</div>
                        <div class="kpi-card__label">Infecções (180 dias)</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--purple">
                        <i class="fa-solid fa-microscope" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ totalColonizacoes180d }}</div>
                        <div class="kpi-card__label">Colonizações (180 dias)</div>
                    </div>
                </div>
            </v-col>
        </v-row>

        <!-- KPIs — linha 2: métricas clínicas -->
        <v-row class="mb-6">
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--indigo">
                        <i class="fa-solid fa-chart-line" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ metricas?.densidadeInfeccao180d ?? '—' }}</div>
                        <div class="kpi-card__label">Densidade IRAS / 1000 pac-dia</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--red">
                        <i class="fa-solid fa-heart-crack" />
                    </div>
                    <div>
                        <div class="kpi-card__value">
                            {{ metricas?.totalObitos30d ?? '—' }}
                            <span class="kpi-card__value-sub" v-if="metricas">({{ metricas.taxaMortalidade30d }}%)</span>
                        </div>
                        <div class="kpi-card__label">Óbitos (30 dias)</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--slate">
                        <i class="fa-solid fa-clock-rotate-left" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ metricas?.tempoMedioInternacao90d ?? '—' }}<span class="kpi-card__value-unit" v-if="metricas">d</span></div>
                        <div class="kpi-card__label">Tempo médio internação (90d)</div>
                    </div>
                </div>
            </v-col>
            <v-col cols="6" sm="6" lg="3" class="d-flex">
                <div class="kpi-card">
                    <div class="kpi-card__icon kpi-card__icon--amber">
                        <i class="fa-solid fa-pen-to-square" />
                    </div>
                    <div>
                        <div class="kpi-card__value">{{ metricas?.atrasoMedioCadastro ?? '—' }}<span class="kpi-card__value-unit" v-if="metricas">d</span></div>
                        <div class="kpi-card__label">Atraso médio de cadastro</div>
                    </div>
                </div>
            </v-col>
        </v-row>

        <!-- Card internações em aberto (contagem + lista expansível) -->
        <v-row class="mb-6">
            <v-col cols="12">
                <div class="kpi-aberto-card">
                    <div class="kpi-aberto-card__header" @click="listaExpandida = !listaExpandida">
                        <div class="kpi-aberto-card__icon">
                            <i class="fa-solid fa-bed-pulse" />
                        </div>
                        <div class="kpi-aberto-card__info">
                            <span class="kpi-aberto-card__value">{{ internacoesEmAberto }}</span>
                            <span class="kpi-aberto-card__label">Internações em aberto — sem alta nem óbito registrado</span>
                        </div>
                        <div class="kpi-aberto-card__badge d-none d-sm-block">Em acompanhamento</div>
                        <button class="kpi-aberto-card__toggle">
                            <i :class="listaExpandida ? 'fa-solid fa-chevron-up' : 'fa-solid fa-chevron-down'" />
                        </button>
                    </div>

                    <v-expand-transition>
                        <div v-show="listaExpandida">
                            <div class="kpi-aberto-card__divider" />
                            <div class="internacao-list">
                                <div
                                    v-for="item in listaInternacoesEmAberto"
                                    :key="item.idNeonato"
                                    class="internacao-item"
                                    @click.stop="verProntuario(item.idNeonato)"
                                >
                                    <div class="internacao-item__avatar">
                                        <i class="fa-solid fa-baby" />
                                    </div>
                                    <div class="internacao-item__info">
                                        <span class="internacao-item__protocol">Prontuário {{ item.protocolo }}</span>
                                        <span class="internacao-item__meta">
                                            Nasc. {{ item.dataNascimento }}
                                            &nbsp;·&nbsp;
                                            Internado em {{ item.dataInternacao }}
                                            &nbsp;·&nbsp;
                                            {{ item.usuario }}
                                        </span>
                                    </div>
                                    <div class="internacao-item__dias">{{ diasInternado(item.dataInternacao) }}d</div>
                                    <i class="fa-solid fa-chevron-right internacao-item__arrow" />
                                </div>
                                <div v-if="listaInternacoesEmAberto.length === 0" class="internacao-list__empty">
                                    Nenhuma internação em aberto no momento.
                                </div>
                            </div>
                        </div>
                    </v-expand-transition>
                </div>
            </v-col>
        </v-row>

        <!-- Alertas: pacientes sem eventos recentes -->
        <p class="dash-section-label">Alertas</p>
        <v-row class="mb-6">
            <v-col cols="12">
                <div class="alerta-card">
                    <div class="alerta-card__header" @click="alertasExpandido = !alertasExpandido">
                        <div class="alerta-card__icon">
                            <i class="fa-solid fa-triangle-exclamation" />
                        </div>
                        <div class="alerta-card__info">
                            <span class="alerta-card__value">{{ pacientesSemEventos.length }}</span>
                            <span class="alerta-card__label">Pacientes sem registro de eventos nos últimos 7 dias</span>
                        </div>
                        <div class="alerta-card__badge d-none d-sm-block">Requer atenção</div>
                        <button class="alerta-card__toggle">
                            <i :class="alertasExpandido ? 'fa-solid fa-chevron-up' : 'fa-solid fa-chevron-down'" />
                        </button>
                    </div>
                    <v-expand-transition>
                        <div v-show="alertasExpandido">
                            <div class="alerta-card__divider" />
                            <div class="internacao-list">
                                <div
                                    v-for="item in pacientesSemEventos"
                                    :key="item.idNeonato"
                                    class="internacao-item internacao-item--alerta"
                                    @click.stop="verProntuario(item.idNeonato)"
                                >
                                    <div class="internacao-item__avatar internacao-item__avatar--alerta">
                                        <i class="fa-solid fa-baby" />
                                    </div>
                                    <div class="internacao-item__info">
                                        <span class="internacao-item__protocol internacao-item__protocol--alerta">Prontuário {{ item.protocolo }}</span>
                                        <span class="internacao-item__meta">
                                            Internado em {{ item.dataInternacao }}
                                            &nbsp;·&nbsp;
                                            {{ item.usuario }}
                                        </span>
                                    </div>
                                    <div class="internacao-item__dias internacao-item__dias--alerta">
                                        {{ item.ultimoEvento ? diasSemEvento(item.ultimoEvento) + 'd' : 'Sem eventos' }}
                                    </div>
                                    <i class="fa-solid fa-chevron-right internacao-item__arrow" />
                                </div>
                                <div v-if="pacientesSemEventos.length === 0" class="internacao-list__empty">
                                    Todos os pacientes têm registros recentes.
                                </div>
                            </div>
                        </div>
                    </v-expand-transition>
                </div>
            </v-col>
        </v-row>

        <!-- Seção: Atividade Recente -->
        <p class="dash-section-label">Atividade Recente</p>
        <v-row class="mb-6">
            <v-col cols="12" md="4">
                <div class="dash-card dash-card--full">
                    <div class="dash-card__header">
                        <span class="dash-card__title">
                            <i class="fa-solid fa-list-ul mr-2" />
                            Admissões Recentes
                        </span>
                    </div>
                    <div class="neonato-list">
                        <div
                            v-for="neonato in ultimosNeonatos"
                            :key="neonato.idNeonato"
                            class="neonato-item"
                            @click="verProntuario(neonato.idNeonato)"
                        >
                            <div class="neonato-item__avatar">
                                <i class="fa-solid fa-baby" />
                            </div>
                            <div class="neonato-item__info">
                                <span class="neonato-item__protocol">Protocolo {{ neonato.protocolo }}</span>
                                <div class="neonato-item__dates">
                                    <span class="neonato-item__date-chip">Nasc. {{ neonato.dataNascimento }}</span>
                                    <span class="neonato-item__date-chip">Int. {{ neonato.dataInternacao }}</span>
                                    <span class="neonato-item__date-chip neonato-item__date-chip--cad">Cad. {{ neonato.dataCadastro }}</span>
                                </div>
                                <span class="neonato-item__user">{{ neonato.usuario }}</span>
                            </div>
                            <i class="fa-solid fa-chevron-right neonato-item__arrow" />
                        </div>
                    </div>
                </div>
            </v-col>
            <v-col cols="12" md="8">
                <div class="dash-card dash-card--full">
                    <ChartInternacoesPorDia :dados="internacoesPorDia" />
                </div>
            </v-col>
        </v-row>

        <!-- Seção: Epidemiologia -->
        <p class="dash-section-label">Epidemiologia (180 dias)</p>
        <v-row class="mb-6">
            <v-col cols="12">
                <div class="dash-card">
                    <ChartInfeccoesPorAgente :dados="infeccoesPorAgente" />
                </div>
            </v-col>
            <v-col cols="12">
                <div class="dash-card">
                    <ChartColonizacoesPorAgente :dados="colonizacoesPorAgente" />
                </div>
            </v-col>
        </v-row>

        <!-- Seção: Perfil dos Pacientes -->
        <p class="dash-section-label">Perfil dos Pacientes (180 dias)</p>
        <v-row class="mb-6">
            <v-col cols="12" md="6">
                <div class="dash-card dash-card--full">
                    <div class="dash-card__header">
                        <span class="dash-card__title">
                            <i class="fa-solid fa-ranking-star mr-2" />
                            Ranking de Microorganismos
                        </span>
                    </div>
                    <div class="ranking-list">
                        <div v-for="(item, i) in rankingMicroorganismos" :key="item.perfil" class="ranking-item">
                            <div class="ranking-item__header">
                                <span class="ranking-item__pos">{{ i + 1 }}</span>
                                <span class="ranking-item__name">{{ item.perfil }}</span>
                                <span class="ranking-item__stats">
                                    {{ item.total }} isolados
                                    <em class="ranking-item__pct">{{ pct(item.total, totalRanking) }}%</em>
                                </span>
                            </div>
                            <div class="ranking-item__bar-wrap">
                                <div class="ranking-item__bar" :style="{ width: pct(item.total, totalRanking) + '%' }" />
                            </div>
                        </div>
                        <div v-if="rankingMicroorganismos.length === 0" class="internacao-list__empty">
                            Nenhum isolado nos últimos 180 dias.
                        </div>
                    </div>
                </div>
            </v-col>
            <v-col cols="12" md="6">
                <div class="dash-card dash-card--full">
                    <div class="dash-card__header">
                        <span class="dash-card__title">
                            <i class="fa-solid fa-weight-scale mr-2" />
                            Distribuição por Peso ao Nascer
                        </span>
                    </div>
                    <div class="ranking-list">
                        <div v-for="item in distribuicaoPeso" :key="item.perfil" class="ranking-item">
                            <div class="ranking-item__header">
                                <span class="ranking-item__name">{{ item.perfil }}</span>
                                <span class="ranking-item__stats">
                                    {{ item.total }} pacientes
                                    <em class="ranking-item__pct ranking-item__pct--teal">{{ pct(item.total, totalPeso) }}%</em>
                                </span>
                            </div>
                            <div class="ranking-item__bar-wrap">
                                <div class="ranking-item__bar ranking-item__bar--teal" :style="{ width: pct(item.total, totalPeso) + '%' }" />
                            </div>
                        </div>
                        <div v-if="distribuicaoPeso.length === 0" class="internacao-list__empty">
                            Nenhum dado de peso nos últimos 180 dias.
                        </div>
                    </div>
                </div>
            </v-col>
        </v-row>

        <!-- Seção: Distribuição e Resistência -->
        <p class="dash-section-label">Distribuição e Resistência</p>
        <v-row>
            <v-col cols="12" md="6">
                <div class="dash-card">
                    <ChartDistribuicaoPerfis :dados="distribuicaoPerfis" />
                </div>
            </v-col>
            <v-col cols="12" md="6">
                <div class="dash-card">
                    <ChartDistribuicaoMecanismos :dados="distribuicaoMecanismos" />
                </div>
            </v-col>
            <v-col cols="12">
                <div class="dash-card">
                    <ChartResistenciaPorAntimicrobiano :dados="resistenciaPorAntimicrobiano" />
                </div>
            </v-col>
        </v-row>

    </div>
</template>

<style lang="scss" scoped>
.dashboard {
    padding: 28px 24px;
    min-height: 100%;

    @media (max-width: 600px) {
        padding: 16px 12px;
    }
}

// ── Cabeçalho ────────────────────────────────────────────
.dash-header {
    margin-bottom: 28px;

    &__title {
        font-size: 1.45rem;
        font-weight: 700;
        color: #0f2a45;
        margin: 0 0 4px;
        line-height: 1.2;

        @media (max-width: 600px) { font-size: 1.2rem; }
    }

    &__subtitle {
        font-size: 0.83rem;
        color: #94A3B8;
        margin: 0;
        text-transform: capitalize;

        @media (max-width: 600px) { font-size: 0.75rem; }
    }
}

// ── Card internações em aberto (contagem + lista) ────────
.kpi-aberto-card {
    background: linear-gradient(180deg, #ECFDF5 0%, #ffffff 80%);
    border: 1px solid #6EE7B7;
    border-radius: 12px;
    overflow: hidden;

    &__header {
        display: flex;
        align-items: center;
        gap: 18px;
        padding: 18px 24px;
        cursor: pointer;
        user-select: none;
        transition: background 0.15s;

        &:hover { background: rgba(209, 250, 229, 0.4); }

        @media (max-width: 600px) { padding: 14px 16px; gap: 12px; }
    }

    &__icon {
        width: 52px;
        height: 52px;
        border-radius: 13px;
        background: #059669;
        color: #ffffff;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.2rem;
        flex-shrink: 0;

        @media (max-width: 600px) { width: 42px; height: 42px; font-size: 1rem; }
    }

    &__info {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 3px;
        min-width: 0;
    }

    &__value {
        font-size: 1.75rem;
        font-weight: 700;
        color: #065F46;
        line-height: 1;

        @media (max-width: 600px) { font-size: 1.45rem; }
    }

    &__label {
        font-size: 0.8rem;
        color: #047857;

        @media (max-width: 600px) { font-size: 0.72rem; }
    }

    &__badge {
        flex-shrink: 0;
        padding: 5px 14px;
        background: #059669;
        color: #ffffff;
        border-radius: 20px;
        font-size: 0.72rem;
        font-weight: 600;
        white-space: nowrap;
    }

    &__toggle {
        flex-shrink: 0;
        width: 32px;
        height: 32px;
        border-radius: 8px;
        border: 1px solid #A7F3D0;
        background: transparent;
        color: #059669;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.75rem;
        cursor: pointer;
        transition: background 0.15s;

        &:hover { background: #D1FAE5; }
    }

    &__divider {
        height: 1px;
        background: #A7F3D0;
        margin: 0 16px;
    }
}

// ── KPI Cards ────────────────────────────────────────────
.kpi-card {
    background: #ffffff;
    border-radius: 12px;
    padding: 18px 20px;
    display: flex;
    align-items: center;
    gap: 16px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.06), 0 4px 16px rgba(0,0,0,0.04);
    border: 1px solid rgba(0,0,0,0.05);
    width: 100%;
    height: 100%;

    @media (max-width: 600px) {
        padding: 12px 14px;
        gap: 10px;
        border-radius: 10px;
    }

    &__icon {
        width: 48px;
        height: 48px;
        border-radius: 12px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.1rem;
        flex-shrink: 0;

        @media (max-width: 600px) {
            width: 38px;
            height: 38px;
            border-radius: 9px;
            font-size: 0.9rem;
        }

        &--blue   { background: #EFF6FF; color: #3B82F6; }
        &--teal   { background: #F0FDFA; color: #0D9488; }
        &--orange { background: #FFF7ED; color: #EA580C; }
        &--purple { background: #FAF5FF; color: #9333EA; }
    }

    &__value {
        font-size: 1.65rem;
        font-weight: 700;
        color: #0f2a45;
        line-height: 1;

        @media (max-width: 600px) { font-size: 1.35rem; }
    }

    &__label {
        font-size: 0.77rem;
        color: #94A3B8;
        margin-top: 4px;

        @media (max-width: 600px) { font-size: 0.68rem; }
    }
}

// ── Seções ───────────────────────────────────────────────
.dash-section-label {
    font-size: 0.72rem;
    font-weight: 600;
    color: #94A3B8;
    text-transform: uppercase;
    letter-spacing: 1.1px;
    margin: 0 0 14px;
}

// ── Cards de conteúdo ────────────────────────────────────
.dash-card {
    background: #ffffff;
    border-radius: 12px;
    padding: 16px;
    box-shadow: 0 1px 3px rgba(0,0,0,0.06), 0 4px 16px rgba(0,0,0,0.04);
    border: 1px solid rgba(0,0,0,0.05);

    &--full {
        height: 100%;
        display: flex;
        flex-direction: column;
    }

    &__header {
        padding-bottom: 12px;
        margin-bottom: 4px;
        border-bottom: 1px solid #F1F5F9;
    }

    &__title {
        font-size: 0.875rem;
        font-weight: 600;
        color: #334155;
    }
}

// ── Lista de admissões ───────────────────────────────────
.neonato-list {
    display: flex;
    flex-direction: column;
    flex: 1;
    overflow-y: auto;
}

.neonato-item {
    display: flex;
    align-items: flex-start;
    gap: 12px;
    padding: 10px 6px;
    cursor: pointer;
    border-radius: 8px;
    transition: background 0.15s;

    &:not(:last-child) {
        border-bottom: 1px solid #F8FAFC;
    }

    &:hover { background: #F8FAFC; }

    &__avatar {
        width: 36px;
        height: 36px;
        border-radius: 8px;
        background: #EFF6FF;
        color: #3B82F6;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.85rem;
        flex-shrink: 0;
        margin-top: 2px;
    }

    &__info {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 4px;
        min-width: 0;
    }

    &__protocol {
        font-size: 0.855rem;
        font-weight: 600;
        color: #1E40AF;
    }

    &__dates {
        display: flex;
        flex-wrap: wrap;
        gap: 3px 6px;
    }

    &__date-chip {
        font-size: 0.7rem;
        color: #475569;
        background: #F1F5F9;
        padding: 1px 7px;
        border-radius: 4px;
        white-space: nowrap;

        &--cad {
            background: #FFF7ED;
            color: #92400E;
        }
    }

    &__user {
        font-size: 0.72rem;
        color: #94A3B8;
    }

    &__arrow {
        font-size: 0.6rem;
        color: #CBD5E1;
        flex-shrink: 0;
        margin-top: 6px;
    }
}

// ── Lista de internações ─────────────────────────────────
.internacao-list {
    display: flex;
    flex-direction: column;
    max-height: 320px;
    overflow-y: auto;
    padding: 8px 16px;

    &__empty {
        padding: 20px 8px;
        text-align: center;
        font-size: 0.82rem;
        color: #94A3B8;
    }
}

.internacao-item {
    display: flex;
    align-items: center;
    gap: 12px;
    padding: 10px 6px;
    cursor: pointer;
    border-radius: 8px;
    transition: background 0.15s;

    &:not(:last-child) {
        border-bottom: 1px solid #ECFDF5;
    }

    &:hover { background: #ECFDF5; }

    &__avatar {
        width: 36px;
        height: 36px;
        border-radius: 8px;
        background: #D1FAE5;
        color: #059669;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.85rem;
        flex-shrink: 0;
    }

    &__info {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 2px;
        min-width: 0;
    }

    &__protocol {
        font-size: 0.855rem;
        font-weight: 600;
        color: #065F46;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    &__meta {
        font-size: 0.75rem;
        color: #6B7280;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    &__dias {
        flex-shrink: 0;
        padding: 3px 10px;
        background: #D1FAE5;
        color: #065F46;
        border-radius: 20px;
        font-size: 0.72rem;
        font-weight: 700;
        white-space: nowrap;
    }

    &__arrow {
        font-size: 0.6rem;
        color: #6EE7B7;
        flex-shrink: 0;
    }
}

// ── KPI — novos modificadores de cor ─────────────────────
.kpi-card__icon--indigo { background: #EEF2FF; color: #6366F1; }
.kpi-card__icon--red    { background: #FEF2F2; color: #EF4444; }
.kpi-card__icon--slate  { background: #F8FAFC; color: #64748B; }
.kpi-card__icon--amber  { background: #FFFBEB; color: #D97706; }

.kpi-card__value-sub {
    font-size: 0.85rem;
    font-weight: 500;
    color: #94A3B8;
    margin-left: 2px;
}

.kpi-card__value-unit {
    font-size: 1rem;
    font-weight: 400;
    color: #94A3B8;
    margin-left: 2px;
}

// ── Card de alertas ──────────────────────────────────────
.alerta-card {
    background: linear-gradient(180deg, #FFFBEB 0%, #ffffff 80%);
    border: 1px solid #FCD34D;
    border-radius: 12px;
    overflow: hidden;

    &__header {
        display: flex;
        align-items: center;
        gap: 18px;
        padding: 18px 24px;
        cursor: pointer;
        user-select: none;
        transition: background 0.15s;

        &:hover { background: rgba(253, 230, 138, 0.3); }

        @media (max-width: 600px) { padding: 14px 16px; gap: 12px; }
    }

    &__icon {
        width: 52px;
        height: 52px;
        border-radius: 13px;
        background: #D97706;
        color: #ffffff;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.2rem;
        flex-shrink: 0;

        @media (max-width: 600px) { width: 42px; height: 42px; font-size: 1rem; }
    }

    &__info {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 3px;
        min-width: 0;
    }

    &__value {
        font-size: 1.75rem;
        font-weight: 700;
        color: #92400E;
        line-height: 1;

        @media (max-width: 600px) { font-size: 1.45rem; }
    }

    &__label {
        font-size: 0.8rem;
        color: #B45309;

        @media (max-width: 600px) { font-size: 0.72rem; }
    }

    &__badge {
        flex-shrink: 0;
        padding: 5px 14px;
        background: #D97706;
        color: #ffffff;
        border-radius: 20px;
        font-size: 0.72rem;
        font-weight: 600;
        white-space: nowrap;
    }

    &__toggle {
        flex-shrink: 0;
        width: 32px;
        height: 32px;
        border-radius: 8px;
        border: 1px solid #FCD34D;
        background: transparent;
        color: #D97706;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 0.75rem;
        cursor: pointer;
        transition: background 0.15s;

        &:hover { background: #FEF3C7; }
    }

    &__divider {
        height: 1px;
        background: #FCD34D;
        margin: 0 16px;
    }
}

// ── Variantes alerta nos itens de internação ─────────────
.internacao-item--alerta:hover { background: #FFFBEB; }

.internacao-item__avatar--alerta {
    background: #FEF3C7;
    color: #D97706;
}

.internacao-item__protocol--alerta { color: #92400E; }

.internacao-item__dias--alerta {
    background: #FEF3C7;
    color: #92400E;
}

// ── Ranking / distribuição ───────────────────────────────
.ranking-list {
    display: flex;
    flex-direction: column;
    gap: 12px;
    padding: 4px 0;
    flex: 1;
}

.ranking-item {
    display: flex;
    flex-direction: column;
    gap: 5px;

    &__header {
        display: flex;
        align-items: baseline;
        gap: 6px;
    }

    &__pos {
        font-size: 0.7rem;
        font-weight: 700;
        color: #CBD5E1;
        flex-shrink: 0;
        min-width: 16px;
    }

    &__name {
        flex: 1;
        font-size: 0.82rem;
        font-weight: 600;
        color: #334155;
    }

    &__stats {
        font-size: 0.75rem;
        color: #64748B;
        white-space: nowrap;
        flex-shrink: 0;
    }

    &__pct {
        font-style: normal;
        font-weight: 700;
        color: #6366F1;
        margin-left: 4px;

        &--teal { color: #0D9488; }
    }

    &__bar-wrap {
        height: 6px;
        background: #F1F5F9;
        border-radius: 3px;
        overflow: hidden;
    }

    &__bar {
        height: 100%;
        background: linear-gradient(90deg, #6366F1, #818CF8);
        border-radius: 3px;
        transition: width 0.7s ease;

        &--teal {
            background: linear-gradient(90deg, #0D9488, #14B8A6);
        }
    }
}
</style>
