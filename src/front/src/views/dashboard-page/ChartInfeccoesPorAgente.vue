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
    title: {
        text: 'Infecções por Agente (180 dias)',
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
