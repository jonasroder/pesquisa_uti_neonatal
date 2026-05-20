<script setup>
defineProps({
    showButtons: { type: Boolean, default: true },
});
</script>

<template>
    <v-app-bar app class="barra-superior" elevation="0">
        <v-app-bar-nav-icon class="bar-nav-icon" @click="$emit('toggle-drawer')" />

        <v-toolbar-title>
            <span class="bar-title d-none d-sm-inline">Grupo de Pesquisa em Epidemiologia Neonatal</span>
            <span class="bar-title d-sm-none">Pesquisa Neonatal</span>
        </v-toolbar-title>

        <v-spacer />

        <template v-if="showButtons">
            <!-- Desktop -->
            <button class="d-none d-sm-flex bar-btn bar-btn--ghost mr-2" @click="$emit('handle-back')">
                <i class="fa-solid fa-arrow-left bar-btn__icon" />
                Voltar
            </button>
            <button class="d-none d-sm-flex bar-btn bar-btn--solid mr-3" @click="$emit('handle-save')">
                <i class="fa-solid fa-floppy-disk bar-btn__icon" />
                Salvar
            </button>

            <!-- Mobile -->
            <v-btn class="d-sm-none" icon variant="text" color="white" @click="$emit('handle-back')">
                <v-icon>fa-solid fa-arrow-left</v-icon>
            </v-btn>
            <v-btn class="d-sm-none mr-1" icon variant="text" color="white" @click="$emit('handle-save')">
                <v-icon>fa-solid fa-floppy-disk</v-icon>
            </v-btn>
        </template>
    </v-app-bar>
</template>

<style scoped>
.barra-superior {
    background: linear-gradient(135deg, #0f2a45 0%, #0e6a85 100%) !important;
    color: #ffffff !important;
}

.bar-nav-icon {
    color: rgba(255, 255, 255, 0.9) !important;
}

.bar-title {
    font-weight: 600;
    font-size: 0.975rem;
    color: #ffffff;
    letter-spacing: 0.15px;
}

.bar-btn {
    display: inline-flex;
    align-items: center;
    gap: 7px;
    padding: 7px 18px;
    border-radius: 7px;
    font-size: 0.82rem;
    font-weight: 600;
    cursor: pointer;
    transition: background 0.15s, transform 0.1s;
    white-space: nowrap;

    &:active { transform: scale(0.98); }
    &__icon  { font-size: 0.72rem; }

    &--ghost {
        background: rgba(255, 255, 255, 0.12);
        color: #ffffff;
        border: 1.5px solid rgba(255, 255, 255, 0.45);

        &:hover { background: rgba(255, 255, 255, 0.22); }
    }

    &--solid {
        background: #ffffff;
        color: #0f2a45;
        border: none;
        font-weight: 700;
        box-shadow: 0 1px 4px rgba(0, 0, 0, 0.2);

        &:hover { background: #e8f4f8; }
    }
}
</style>
