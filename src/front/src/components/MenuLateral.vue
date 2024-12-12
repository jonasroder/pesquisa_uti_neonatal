<script setup>
import { onMounted, ref, watch } from 'vue';
import { loading } from "@/plugins/loadingService.js";
import { useRouter } from "vue-router";
import { getToken, getSessionUserData } from "@/service/common/tokenService";
import { removeToken } from "@/service/common/tokenService";

const router = useRouter();
const menuLateral = ref([]);
const userData = ref({});
const nomeUsuario = ref("");
const roleUsuario = ref("");

onMounted(async () => {
    if (!getToken()) {
        return;
    }

    userData.value = getSessionUserData();
    nomeUsuario.value = userData.value.nome_completo;
    roleUsuario.value = userData.value.id_role;

    loading.show();
    let cachedMenu = localStorage.getItem('cachedMenu');
    menuLateral.value = cachedMenu ? JSON.parse(cachedMenu) : null;
    loading.hide();
});

const props = defineProps({
    drawer: Boolean
});

const localDrawerState = ref(props.drawer);

watch(() => props.drawer, (newVal) => {
    localDrawerState.value = newVal;
});

const navigateTo = (route) => {
    router.push(route);
};

const isActive = (route) => {
    return route === router.currentRoute.value.path;
};

const logout = () => {
    removeToken();
    router.push('/login');
};

const abrirAdminPanel = () => {
    router.push({ name: 'Usuario-List' });
};
</script>

<template>
    <v-navigation-drawer
        v-model="localDrawerState"
        color="white"
        class="custom-drawer"
    >
        <v-list>
            <v-list-item>
                <div>
                    <v-list-item-title class="user-info">
                        {{ nomeUsuario }}
                        <v-icon v-if="roleUsuario === 1" @click="abrirAdminPanel" class="hoverable-icon">fa-solid fa-wrench</v-icon>
                        <v-icon @click="logout" class="hoverable-icon">fa-solid fa-arrow-right-from-bracket</v-icon>
                    </v-list-item-title>
                    <v-list-item-subtitle class="user-status">Logado</v-list-item-subtitle>
                </div>
            </v-list-item>

            <v-divider />

            <template v-for="item in menuLateral">
                <v-list-group
                    v-if="item.subMenus && item.subMenus.length"
                    :key="item.title"
                >
                    <template #activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            :prepend-icon="item.icon"
                            class="list-item-title"
                        >
                            {{ item.title }}
                        </v-list-item>
                    </template>

                    <v-list-item
                        v-for="subItem in item.subMenus"
                        :key="subItem.title"
                        @click="navigateTo(subItem.vueRouter.path)"
                        :class="{'v-list-item--active': isActive(subItem.vueRouter.path)}"
                        class="submenu-item"
                    >
                        {{ subItem.title }}
                    </v-list-item>
                </v-list-group>

                <v-list-item
                    v-else
                    :key="item.title"
                    @click="navigateTo(item.vueRouter.path)"
                    :class="{'v-list-item--active': isActive(item.vueRouter.path)}"
                    :prepend-icon="item.icon"
                    class="list-item-title"
                >
                    {{ item.title }}
                </v-list-item>
            </template>
        </v-list>
    </v-navigation-drawer>
</template>

<style scoped>
.custom-drawer {
    border-right: 1px solid #ddd;
    box-shadow: 2px 0 8px rgba(0, 0, 0, 0.1);
    width: 260px;
    background-color: #f9f9f9;
}

.user-info {
    display: flex;
    align-items: center;
    justify-content: space-between;
    font-weight: 600;
    font-size: 16px;
    color: #444;
}

.user-status {
    font-size: 12px;
    color: #888;
}

.hoverable-icon:hover {
    color: #1976D2;
    transition: color 0.3s ease;
}

.list-item-title {
    font-size: 14px;
    font-weight: 500;
    color: #333;
}

.submenu-item {
    font-size: 13px;
    color: #555;
    margin-left: 20px;
}

.v-list-item--active {
    background-color: rgba(25, 118, 210, 0.1);
    border-left: 4px solid #1976D2;
}
</style>
