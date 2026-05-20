<script setup>
import { onMounted, ref, computed } from 'vue';
import { loading } from '@/plugins/loadingService';
import { serviceList } from '@/service/neonato';
import { useRouter } from 'vue-router';
import CardFormulario from '@/components/CardFormulario.vue';
import { getSessionUserData } from '@/service/common/tokenService';
import ModalDownload from '@/views/neonato-page/modalDownload.vue';

const emit         = defineEmits(['set-show-buttons']);
const data         = ref([]);
const search       = ref('');
const router       = useRouter();
const roleUsuario  = ref('');
const modalDownload = ref(false);

onMounted(async () => {
    loading.show();
    const userData    = getSessionUserData();
    roleUsuario.value = userData.id_role;
    data.value        = await serviceList();
    emit('set-show-buttons', false);
    loading.hide();
});

const headers = [
    { title: 'Prontuário',  key: 'prontuario',     align: 'start' },
    { title: 'Nascimento',  key: 'dataNascimento', align: 'start' },
    { title: 'Internação',  key: 'dataInternacao', align: 'start' },
    { title: 'Desfecho',    key: 'dataDesfecho',   align: 'start' },
    { title: 'Status',      key: 'status',         align: 'start', sortable: false }
];

const navigateToEditPage = (idNeonato) =>
    router.push({ name: 'Neonato-Page', query: { id: idNeonato } });

const handleNew = () => router.push({ name: 'Neonato-Page' });

const downloadExcel = () => { modalDownload.value = true; };
const fecharModal   = () => { modalDownload.value = false; };

const statusLabel = (item) => item.dataDesfecho ? 'Com desfecho' : 'Em internação';
const statusClass = (item) => item.dataDesfecho ? 'status-chip--desfecho' : 'status-chip--internado';

const filteredData = computed(() => {
    if (!search.value) return data.value;
    const q = search.value.toLowerCase();
    return data.value.filter(n => n.prontuario && n.prontuario.toLowerCase().includes(q));
});
</script>

<template>
    <CardFormulario
        title="Neonatos"
        subtitle="Controle e registro de pacientes neonatos"
        :showCreateButton="true"
        createButton="Cadastrar Neonato"
        @handleNew="handleNew"
    >
        <div class="table-toolbar">
            <button
                v-if="roleUsuario === 1"
                class="toolbar-btn toolbar-btn--outline"
                @click="downloadExcel"
            >
                <i class="fa-solid fa-download toolbar-btn__icon" />
                Exportar
            </button>
            <v-spacer />
            <v-text-field
                v-model="search"
                density="compact"
                placeholder="Buscar por prontuário..."
                prepend-inner-icon="fa-solid fa-magnifying-glass"
                variant="outlined"
                hide-details
                single-line
                class="table-search"
            />
        </div>

        <v-data-table
            :headers="headers"
            :items="filteredData"
            :hover="true"
            items-per-page="50"
            density="comfortable"
            class="neonato-table"
            show-current-page
            show-first-last-page
        >
            <template v-slot:[`item.prontuario`]="{ item }">
                <span class="table-link" @click="navigateToEditPage(item.idNeonato)">
                    {{ item.prontuario }}
                </span>
            </template>

            <template v-slot:[`item.dataDesfecho`]="{ item }">
                <span class="table-date">{{ item.dataDesfecho || '—' }}</span>
            </template>

            <template v-slot:[`item.status`]="{ item }">
                <span :class="['status-chip', statusClass(item)]">
                    <span class="status-chip__dot" />
                    {{ statusLabel(item) }}
                </span>
            </template>
        </v-data-table>

        <v-dialog v-model="modalDownload" transition="dialog-top-transition" max-width="600px">
            <ModalDownload @close_modal="fecharModal" />
        </v-dialog>
    </CardFormulario>
</template>

<style lang="scss" scoped>
.table-toolbar {
    display: flex;
    align-items: center;
    padding: 14px 16px;
    border-bottom: 1px solid #F1F5F9;
}

.table-search {
    max-width: 320px;

    :deep(.v-field) {
        border-radius: 10px;
        background: #F8FAFC;
    }

    :deep(.v-field__outline__start)  { border-radius: 10px 0 0 10px; }
    :deep(.v-field__outline__end)    { border-radius: 0 10px 10px 0; }

    :deep(.v-field:not(.v-field--focused) .v-field__outline) {
        --v-field-border-opacity: 0.5;
    }

    :deep(.v-field--focused .v-field__outline) {
        color: #0882a0;
        --v-field-border-width: 2px;
    }

    :deep(.v-field__prepend-inner .v-icon) {
        color: #94A3B8;
        font-size: 0.78rem;
    }

    :deep(.v-field--focused .v-field__prepend-inner .v-icon) { color: #0882a0; }

    :deep(input::placeholder) { color: #94A3B8; font-size: 0.83rem; }
}

.toolbar-btn {
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
    &__icon  { font-size: 0.72rem; }

    &--outline {
        background: transparent;
        color: #1E40AF;
        border: 1.5px solid #BFDBFE;

        &:hover { background: #EFF6FF; border-color: #93C5FD; }
    }
}

.table-link {
    font-weight: 600;
    color: #1E40AF;
    cursor: pointer;
    transition: color 0.15s;

    &:hover { color: #3B82F6; text-decoration: underline; }
}

.table-date {
    font-size: 0.85rem;
    color: #64748B;
}

.status-chip {
    display: inline-flex;
    align-items: center;
    gap: 5px;
    padding: 3px 10px;
    border-radius: 20px;
    font-size: 0.72rem;
    font-weight: 600;
    white-space: nowrap;

    &__dot {
        width: 6px;
        height: 6px;
        border-radius: 50%;
        flex-shrink: 0;
    }

    &--internado {
        background: #DCFCE7;
        color: #166534;

        .status-chip__dot { background: #16A34A; }
    }

    &--desfecho {
        background: #F1F5F9;
        color: #475569;

        .status-chip__dot { background: #94A3B8; }
    }
}
</style>
