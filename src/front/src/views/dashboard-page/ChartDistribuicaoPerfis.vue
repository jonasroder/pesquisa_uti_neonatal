<script setup>
import { computed } from 'vue';
import ApexChart from 'vue3-apexcharts';

const props = defineProps({ dados: Array });

const series = computed(() => props.dados.map(item => item.total));
const labels = computed(() => props.dados.map(item => item.perfil));

const chartOptions = computed(() => ({
    chart: { type: 'donut' },
    labels: labels.value,
    legend: { position: 'bottom' },
    title: { text: 'Distribuição dos Perfis de Resistência (180 dias)' },
    tooltip: { y: { formatter: val => `${val} casos` } }
}));
</script>

<template>
    <apex-chart
        v-if="series.length && chartOptions.labels?.length"
        type="donut"
        :series="series"
        :options="chartOptions"
        height="350"
    />
</template>
