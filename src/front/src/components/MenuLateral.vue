<script setup>
import { onMounted, ref, watch } from 'vue';
import { useDisplay } from 'vuetify';
import { loading } from "@/plugins/loadingService.js";
import { useRouter } from "vue-router";
import { getSessionUserData } from "@/service/common/tokenService";
import { authStore } from "@/service/common/authStore";
import { serviceLogout } from "@/service/login";

const router         = useRouter();
const { smAndDown }  = useDisplay();
const menuLateral    = ref([]);
const userData       = ref({});
const nomeUsuario    = ref("");
const roleUsuario    = ref("");

const iniciais = (nome) => {
    if (!nome) return '?';
    const partes = nome.trim().split(' ');
    return partes.length >= 2
        ? (partes[0][0] + partes[partes.length - 1][0]).toUpperCase()
        : partes[0].substring(0, 2).toUpperCase();
};

const carregarMenu = () => {
    userData.value    = getSessionUserData();
    nomeUsuario.value = userData.value?.nome_completo ?? '';
    roleUsuario.value = userData.value?.id_role;

    const cachedMenu  = localStorage.getItem('cachedMenu');
    menuLateral.value = cachedMenu ? JSON.parse(cachedMenu) : [];
};

onMounted(() => {
    if (authStore.accessToken) carregarMenu();
});

watch(() => authStore.accessToken, (token) => {
    if (token && menuLateral.value.length === 0) carregarMenu();
});

const props = defineProps({ drawer: Boolean });
const emit  = defineEmits(['update:drawer']);

const localDrawerState = ref(props.drawer);

watch(() => props.drawer, (val) => { localDrawerState.value = val; });
watch(localDrawerState,   (val) => { emit('update:drawer', val); });

const closeOnMobile = () => {
    if (smAndDown.value) localDrawerState.value = false;
};

const navigateTo = (route) => {
    router.push(route);
    closeOnMobile();
};

const isActive = (route) => route === router.currentRoute.value.path;

const logout = async () => {
    await serviceLogout();
    router.push('/login');
};

const abrirAdminPanel = () => {
    router.push({ name: 'Usuario-List' });
    closeOnMobile();
};
</script>

<template>
    <v-navigation-drawer v-model="localDrawerState" class="nav-drawer">

        <!-- Seção do usuário -->
        <div class="user-section">
            <div class="user-section__avatar">
                {{ iniciais(nomeUsuario) }}
            </div>
            <div class="user-section__info">
                <span class="user-section__name">{{ nomeUsuario?.split(' ')[0] }}</span>
                <span class="user-section__status">
                    <span class="status-dot" />
                    Online
                </span>
            </div>
            <div class="user-section__actions">
                <button
                    v-if="roleUsuario === 1"
                    class="action-btn"
                    title="Administração"
                    @click="abrirAdminPanel"
                >
                    <i class="fa-solid fa-wrench" />
                </button>
                <button
                    class="action-btn action-btn--logout"
                    title="Sair"
                    @click="logout"
                >
                    <i class="fa-solid fa-arrow-right-from-bracket" />
                </button>
            </div>
        </div>

        <v-divider />

        <!-- Menu -->
        <v-list nav density="compact" class="nav-list">
            <template v-for="item in menuLateral" :key="item.title">
                <template v-if="!(roleUsuario === 2 && item.idRole === 1)">

                    <v-list-group v-if="item.subMenus && item.subMenus.length">
                        <template #activator="{ props: activatorProps }">
                            <v-list-item
                                v-bind="activatorProps"
                                :prepend-icon="item.icon"
                                class="nav-item"
                                rounded="lg"
                            >
                                {{ item.title }}
                            </v-list-item>
                        </template>

                        <v-list-item
                            v-for="subItem in item.subMenus"
                            :key="subItem.title"
                            :class="['nav-subitem', { 'nav-subitem--active': isActive(subItem.vueRouter.path) }]"
                            rounded="lg"
                            @click="navigateTo(subItem.vueRouter.path)"
                        >
                            {{ subItem.title }}
                        </v-list-item>
                    </v-list-group>

                    <v-list-item
                        v-else
                        :prepend-icon="item.icon"
                        :class="['nav-item', { 'nav-item--active': isActive(item.vueRouter?.path) }]"
                        rounded="lg"
                        @click="navigateTo(item.vueRouter.path)"
                    >
                        {{ item.title }}
                    </v-list-item>

                </template>
            </template>
        </v-list>

    </v-navigation-drawer>
</template>

<style lang="scss" scoped>
.nav-drawer {
    border-right: 1px solid #E2E8F0 !important;
    box-shadow: 2px 0 12px rgba(0, 0, 0, 0.04) !important;
}

// ── Seção do usuário ─────────────────────────────────────
.user-section {
    display: flex;
    align-items: center;
    gap: 10px;
    padding: 16px 14px;

    &__avatar {
        width: 38px;
        height: 38px;
        border-radius: 10px;
        background: linear-gradient(135deg, #6fbfd9, #34568B);
        color: #ffffff;
        font-size: 0.78rem;
        font-weight: 700;
        display: flex;
        align-items: center;
        justify-content: center;
        flex-shrink: 0;
        letter-spacing: 0.5px;
    }

    &__info {
        flex: 1;
        display: flex;
        flex-direction: column;
        gap: 2px;
        min-width: 0;
    }

    &__name {
        font-size: 0.875rem;
        font-weight: 600;
        color: #1E293B;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }

    &__status {
        display: flex;
        align-items: center;
        gap: 5px;
        font-size: 0.72rem;
        color: #94A3B8;
    }

    &__actions {
        display: flex;
        gap: 4px;
        flex-shrink: 0;
    }
}

.status-dot {
    width: 6px;
    height: 6px;
    border-radius: 50%;
    background: #22C55E;
    flex-shrink: 0;
}

.action-btn {
    width: 30px;
    height: 30px;
    border: none;
    border-radius: 8px;
    background: transparent;
    color: #94A3B8;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 0.78rem;
    transition: background 0.15s, color 0.15s;

    &:hover {
        background: #F1F5F9;
        color: #475569;
    }

    &--logout:hover {
        background: #FEF2F2;
        color: #EF4444;
    }
}

// ── Itens de navegação ───────────────────────────────────
.nav-list {
    padding: 8px !important;
}

.nav-item {
    font-size: 0.855rem !important;
    font-weight: 500 !important;
    color: #475569 !important;
    margin-bottom: 2px;
    min-height: 40px !important;

    &--active {
        background: #EFF6FF !important;
        color: #1D4ED8 !important;
        font-weight: 600 !important;
    }

    &:hover:not(.nav-item--active) {
        background: #F8FAFC !important;
    }
}

.nav-subitem {
    font-size: 0.82rem !important;
    color: #64748B !important;
    padding-left: 40px !important;
    min-height: 36px !important;
    margin-bottom: 1px;

    &--active {
        background: #EFF6FF !important;
        color: #1D4ED8 !important;
        font-weight: 600 !important;
    }

    &:hover:not(.nav-subitem--active) {
        background: #F8FAFC !important;
    }
}
</style>
