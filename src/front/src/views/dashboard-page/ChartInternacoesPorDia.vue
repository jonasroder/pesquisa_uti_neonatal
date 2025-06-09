<script setup>
import {ref, watchEffect} from 'vue';
import ApexChart from 'vue3-apexcharts';

const props = defineProps({
    dados: {
        type: Array,
        required: true,
    }
});

const series = ref([]);
const chartOptions = ref({});

watchEffect(() => {
    const categorias = props.dados.map(item => item.data);
    const valores = props.dados.map(item => item.total);

    series.value = [{
        name: 'Internações por dia',
        data: valores,
    }];

    chartOptions.value = {
        chart: {
            type: 'line',
            height: 350,
            zoom: { enabled: false },
        },
        title: {
            text: 'Internações por Dia (30 dias)',
            align: 'left',
        },
        xaxis: {
            categories: categorias,
            labels: {
                rotate: -45,
            },
        },
        yaxis: {
            title: { text: 'Total' }
        },
        dataLabels: {
            enabled: true,
        },
        stroke: {
            curve: 'smooth',
        },
        tooltip: {
            x: { format: 'yyyy-MM-dd' },
        }
    };
});
</script>

<template>
    <apex-chart
        height="350"
        type="line"
        :options="chartOptions"
        :series="series"
    />
</template>
