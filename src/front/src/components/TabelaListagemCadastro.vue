<script setup>
import { ref } from 'vue';

defineProps({
    data              : { type: Array,    required: true },
    navigateToEditPage: { type: Function, required: true },
    searchPlaceholder : { type: String,   default: 'Buscar' }
});

const search = ref('');

const headers = [
    { title: 'Descrição',      key: 'descricao',    align: 'start' },
    { title: 'Código',         key: 'codigo',       align: 'start' },
    { title: 'Cadastro',       key: 'dataCadastro', align: 'start' },
    { title: 'Alteração',      key: 'dataAlteracao',align: 'start' },
    { title: 'Status',         key: 'isActive',     align: 'start', sortable: false }
];
</script>

<template>
    <v-data-table
        :headers="headers"
        :items="data"
        v-model:search="search"
        :hover="true"
        items-per-page="50"
        density="comfortable"
        class="cadastro-table"
        show-current-page
        show-first-last-page
    >
        <template #top>
            <div class="table-toolbar">
                <v-text-field
                    v-model="search"
                    density="compact"
                    placeholder="Buscar..."
                    prepend-inner-icon="fa-solid fa-magnifying-glass"
                    variant="outlined"
                    hide-details
                    single-line
                    class="table-search"
                />
            </div>
        </template>

        <template v-slot:[`item.descricao`]="{ item }">
            <span class="table-link" @click="navigateToEditPage(item.id)">
                {{ item.descricao }}
            </span>
        </template>

        <template v-slot:[`item.isActive`]="{ item }">
            <span :class="['status-chip', item.isActive ? 'status-chip--active' : 'status-chip--inactive']">
                {{ item.isActive ? 'Ativo' : 'Inativo' }}
            </span>
        </template>
    </v-data-table>
</template>

<style lang="scss" scoped>
.table-toolbar {
    display: flex;
    align-items: center;
    justify-content: flex-end;
    padding: 14px 16px;
    border-bottom: 1px solid #F1F5F9;
}

.table-search {
    max-width: 280px;

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

.table-link {
    font-weight: 600;
    color: #1E40AF;
    cursor: pointer;
    transition: color 0.15s;

    &:hover { color: #3B82F6; text-decoration: underline; }
}

.status-chip {
    display: inline-flex;
    align-items: center;
    padding: 3px 10px;
    border-radius: 20px;
    font-size: 0.72rem;
    font-weight: 600;
    white-space: nowrap;

    &--active   { background: #DCFCE7; color: #166534; }
    &--inactive { background: #FEE2E2; color: #991B1B; }
}
</style>
