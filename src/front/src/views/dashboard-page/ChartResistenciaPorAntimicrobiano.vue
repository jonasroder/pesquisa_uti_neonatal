<script setup>
import { computed } from 'vue';
import ApexChart from 'vue3-apexcharts';

const props = defineProps({
    dados: Array
});

const antimicrobianos = computed(() => [
    ...new Set(props.dados.map(i => i.antimicrobiano))
]);

const series = computed(() => {
    const mapa = {};
    props.dados.forEach(({ antimicrobiano, perfilResistencia, total }) => {
        if (!mapa[perfilResistencia]) mapa[perfilResistencia] = {};
        mapa[perfilResistencia][antimicrobiano] = total;
    });

    return Object.entries(mapa).map(([perfil, registros]) => ({
        name: perfil,
        data: antimicrobianos.value.map(a => registros[a] || 0)
    }));
});

const chartOptions = computed(() => ({
    chart: {
        type: 'bar',
        toolbar: { show: true },
        stacked: false
    },
    plotOptions: {
        bar: {
            horizontal: false,
            columnWidth: '55%',
            borderRadius: 2
        }
    },
    xaxis: {
        categories: antimicrobianos.value,
        labels: {
            rotate: -45,
            style: {
                fontSize: '11px'
            }
        },
        title: {
            text: 'Antimicrobianos'
        }
    },
    yaxis: {
        title: {
            text: 'Casos'
        }
    },
    legend: {
        position: 'bottom',
        horizontalAlign: 'center'
    },
    dataLabels: {
        enabled: true,
        style: {
            colors: ['#000'],
            fontSize: '11px',
            fontWeight: 'bold'
        },
        dropShadow: { enabled: false }
    },
    tooltip: {
        shared: true,
        intersect: false
    },
    title: {
        text: 'Resistência por Antimicrobiano (180 dias)',
        align: 'left'
    }
}));
</script>

<template>
    <apex-chart
        type="bar"
        :series="series"
        :options="chartOptions"
        height="500"
    />
</template>
