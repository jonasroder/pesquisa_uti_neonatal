<script setup>
import { defineProps } from 'vue';

defineProps({
    showButtons: { type: Boolean, default: true },
});
</script>

<template>
    <v-app-bar app class="barra-superior" elevation="0">
        <v-app-bar-nav-icon @click="$emit('toggle-drawer')" />

        <v-toolbar-title>
            <span class="d-none d-sm-inline">Grupo de Pesquisa em Epidemiologia Neonatal</span>
            <span class="d-sm-none">Pesquisa Neonatal</span>
        </v-toolbar-title>

        <v-spacer />

        <template v-if="showButtons">
            <!-- Desktop: botões com texto -->
            <v-btn
                class="d-none d-sm-flex mr-2"
                variant="elevated"
                color="cinzaAzulado"
                @click="$emit('handle-back')"
            >
                Voltar
            </v-btn>
            <v-btn
                class="d-none d-sm-flex mr-2"
                variant="elevated"
                color="azulEscuro"
                @click="$emit('handle-save')"
            >
                Salvar
            </v-btn>

            <!-- Mobile: botões só com ícone -->
            <v-btn
                class="d-sm-none"
                icon
                variant="text"
                color="white"
                @click="$emit('handle-back')"
            >
                <v-icon>fa-solid fa-arrow-left</v-icon>
            </v-btn>
            <v-btn
                class="d-sm-none mr-1"
                icon
                variant="text"
                color="white"
                @click="$emit('handle-save')"
            >
                <v-icon>fa-solid fa-floppy-disk</v-icon>
            </v-btn>
        </template>
    </v-app-bar>
</template>

<style scoped>
.barra-superior {
    background: linear-gradient(to right, #6fbfd9, #00bdda) !important;
    color: white !important;
}
</style>
