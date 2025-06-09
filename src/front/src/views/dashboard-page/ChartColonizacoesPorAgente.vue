<script setup>
import { computed } from 'vue';

const props = defineProps({
    dados: {
        type: Array,
        required: true
    }
});

// As datas serão mantidas na ordem do back-end
const series = computed(() => {
    const agentes = new Set();
    const datas = [];
    const mapa = {};

    props.dados.forEach(({ agente, data }) => {
        agentes.add(agente);
        if (!datas.includes(data)) datas.push(data); // mantém ordem original
    });

    const agentesOrdenados = Array.from(agentes).sort(); // ok ordenar agentes

    agentesOrdenados.forEach(agente => {
        mapa[agente] = {};
        datas.forEach(data => {
            mapa[agente][data] = { total: 0, prontuarios: '' };
        });
    });

    props.dados.forEach(({ agente, data, total, prontuarios }) => {
        mapa[agente][data] = { total, prontuarios };
    });

    return agentesOrdenados.map(agente => ({
        name: agente,
        data: datas.map(data => mapa[agente][data].total)
    }));
});

const chartOptions = computed(() => ({
    chart: {
        type: 'bar',
        stacked: true,
        toolbar: { show: true },
        animations: {
            enabled: true,
            easing: 'easeinout',
            speed: 600
        }
    },
    colors: [
        '#4E79A7', '#F28E2B', '#E15759', '#76B7B2', '#59A14F',
        '#EDC948', '#B07AA1', '#FF9DA7', '#9C755F', '#BAB0AC',
        '#003f5c', '#58508d', '#bc5090', '#ff6361', '#ffa600',
        '#8dd3c7', '#fb8072', '#80b1d3', '#fdb462', '#b3de69',
        '#fccde5', '#d9d9d9', '#bc80bd', '#ccebc5', '#ffed6f',
        '#e41a1c', '#377eb8', '#4daf4a', '#984ea3', '#ff7f00',
        '#a65628', '#f781bf', '#999999', '#66c2a5', '#fc8d62',
        '#8da0cb', '#e78ac3', '#a6d854', '#ffd92f', '#e5c494',
        '#b3b3b3'
    ],
    title: {
        text: 'Colonizações por Agente (180 dias)',
        align: 'left',
        style: {
            fontSize: '16px',
            fontWeight: 'bold'
        }
    },
    plotOptions: {
        bar: {
            horizontal: false,
            borderRadius: 2
        }
    },
    xaxis: {
        categories: props.dados.map(d => d.data).filter((v, i, a) => a.indexOf(v) === i),
        title: { text: 'Data' },
        labels: { rotate: -45 }
    },
    yaxis: {
        title: { text: 'Casos' }
    },
    legend: {
        position: 'bottom',
        horizontalAlign: 'center'
    },
    tooltip: {
        shared: false,
        intersect: true,
        custom: function({ series, seriesIndex, dataPointIndex, w }) {
            const total = series[seriesIndex][dataPointIndex];
            if (!total) return '';

            const agente = w.globals.seriesNames[seriesIndex];
            const data = w.globals.labels[dataPointIndex];
            const info = w.config.customData?.[agente]?.[data];

            if (!info || !info.prontuarios) return '';

            return `
                <div style="padding: 8px; border-radius: 8px; background: white;">
                    <strong>${agente}</strong><br/>
                    <small>Data:</small> ${data}<br/>
                    <small>Total:</small> ${total}<br/>
                    <small>Prontuários:</small> ${info.prontuarios}
                </div>
            `;
        }
    },
    customData: props.dados.reduce((acc, item) => {
        acc[item.agente] = acc[item.agente] || {};
        acc[item.agente][item.data] = {
            total: item.total,
            prontuarios: item.prontuarios
        };
        return acc;
    }, {})
}));
</script>

<template>
    <div>
        <apexchart
            width="100%"
            height="400"
            :options="chartOptions"
            :series="series"
            type="bar"
        />
    </div>
</template>
