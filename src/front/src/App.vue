<script setup>
import { computed, onMounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import { useDisplay } from 'vuetify';
import MenuLateral from "@/components/MenuLateral.vue";
import BarraSuperior from "@/components/BarraSuperior.vue";
import { loading } from "@/plugins/loadingService.js";
import { connectWebSocket, disconnectWebSocket, lastDownloadNotification } from "@/service/common/websocketService.js";
import { setNotification } from "@/plugins/notificationService.js";

const route       = useRoute();
const { mdAndUp } = useDisplay();
const isLoginPage = computed(() => route.path === '/login');
const isLoading   = computed(() => loading.state.value);
const drawer      = ref(true); // começa aberto; onMounted fecha no mobile

onMounted(() => {
    if (!mdAndUp.value) drawer.value = false;
});

watch(isLoginPage, (onLoginPage) => {
    if (onLoginPage) {
        disconnectWebSocket();
    } else {
        connectWebSocket();
    }
}, { immediate: true });

watch(lastDownloadNotification, (notification) => {
    if (!notification) return;
    if (notification.status === 'CONCLUIDO') {
        setNotification(`"${notification.descricao}" concluído! Acesse Downloads para baixar.`, 'success');
    } else if (notification.status === 'FALHA') {
        setNotification(`Falha ao processar "${notification.descricao}". Tente novamente.`, 'error');
    }
});

const toggleDrawer = () => { drawer.value = !drawer.value; };

const backAction  = ref(() => {});
const saveAction  = ref(() => {});
const showButtons = ref(true);

const setShowButtons = (value) => { showButtons.value = value; };
const handleBack     = () => { backAction.value(); };
const handleSave     = () => { saveAction.value(); };
const setBackAction  = (action) => { backAction.value = action; };
const setSaveAction  = (action) => { saveAction.value = action; };
</script>

<template>
    <v-app v-if="!isLoginPage">
        <barra-superior
            @toggle-drawer="toggleDrawer"
            @handle-save="handleSave"
            @handle-back="handleBack"
            :show-buttons="showButtons"
        />
        <menu-lateral :drawer="drawer" @update:drawer="drawer = $event" />
        <v-main style="background: #F1F5F9;">
            <v-container fluid>
                <router-view
                    @set-back-action="setBackAction"
                    @set-save-action="setSaveAction"
                    @set-show-buttons="setShowButtons"
                />
            </v-container>
        </v-main>
    </v-app>

    <v-app v-else>
        <v-main class="d-flex" style="overflow: hidden;">
            <div style="width: 100%; min-width: 0; overflow: hidden;">
                <router-view />
            </div>
        </v-main>
    </v-app>

    <div v-if="isLoading" class="overlay">
        <v-progress-circular :size="50" color="primary" indeterminate class="loading-overlay" />
    </div>
</template>

<style>
.loading-overlay {
    position: fixed !important;
    top: 50% !important;
    left: 50% !important;
    transform: translate(-50%, -50%) !important;
    z-index: 10001 !important;
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background-color: rgba(255, 255, 255, 0.5);
    z-index: 10000;
}
</style>
