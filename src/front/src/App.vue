<template>
    <v-app v-if="!isLoginPage">
        <barra-superior @toggle-drawer="toggleDrawer" @handle-save="handleSave" @handle-back="handleBack" />
        <menu-lateral :drawer="drawer" :key="drawer" />
        <v-main class="d-flex">
            <v-container>
                <router-view @set-back-action="setBackAction" @set-save-action="setSaveAction" />
            </v-container>
        </v-main>
    </v-app>
    <v-app v-else>
        <v-main class="d-flex">
            <router-view />
        </v-main>
    </v-app>
    <div v-if="isLoading" class="overlay"></div>
    <v-progress-circular v-if="isLoading" :size="50" color="barraSuperior" indeterminate class="loading-overlay"></v-progress-circular>
</template>

<script setup>
import { computed, ref } from 'vue';
import { useRoute} from 'vue-router';
import MenuLateral from "@/components/MenuLateral.vue";
import BarraSuperior from "@/components/BarraSuperior.vue";
import { loading } from "@/plugins/loadingService.js";

const route = useRoute();
const isLoginPage = computed(() => route.path === '/login');
const isLoading = computed(() => loading.state.value);
const drawer = ref(true);

const toggleDrawer = () => {
    drawer.value = !drawer.value;
    console.log(drawer.value);
};

const backAction = ref(() => {}); // Default back action
const saveAction = ref(() => {}); // Default save action

const handleBack = () => {
    backAction.value();
};

const handleSave = () => {
    saveAction.value();
};

const setBackAction = (action) => {
    backAction.value = action;
};

const setSaveAction = (action) => {
    saveAction.value = action;
};
</script>

<style>
.loading-overlay {
    position: fixed !important;
    top: 50% !important;
    left: 50% !important;
    transform: translate(-50%, -50%) !important;
    z-index: 2001 !important;
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(255, 255, 255, 0.5);
    z-index: 2000;
}
</style>
