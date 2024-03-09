<script setup>
import {onMounted, ref, watch} from 'vue';
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";
import {getToken, getSessionUserData} from "@/service/common/tokenService";


const router      = useRouter();
const menuLateral = ref([]);
const userData    = ref({});
const nomeUsuario = ref("");


onMounted(async () => {
    if (!getToken()) {
        return;
    }

    userData.value = getSessionUserData();
    nomeUsuario.value = userData.value.nome_completo

    loading.show()
    let cachedMenu    = localStorage.getItem('cachedMenu');
    menuLateral.value = cachedMenu ? JSON.parse(cachedMenu) : null;
    loading.hide()
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
}

const isActive = (route) => {
    return route === router.currentRoute.value.path;
}


</script>


<template>
    <v-navigation-drawer v-model="localDrawerState"  color="cyan-lighten-4">
        <v-list>
            <v-list-item
                :title="nomeUsuario"
                subtitle="Logado"
            ></v-list-item>

            <v-divider/>

            <template v-for="item in menuLateral">
                <v-list-group
                    v-if="item.subMenus && item.subMenus.length"
                    :key="item.title"
                >
                    <template #activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            :prepend-icon="item.icon"
                        >
                            <b>{{ item.title }}</b>
                        </v-list-item>
                    </template>

                    <v-list-item
                        v-for="subItem in item.subMenus"
                        :key="subItem.title"
                        @click="navigateTo(subItem.vueRouter.path)"
                        :class="{'v-list-item--active': isActive(subItem.vueRouter.path)}"
                    >
                        <b>{{ subItem.title }}</b>
                    </v-list-item>
                </v-list-group>

                <v-list-item
                    v-else
                    :key="item.title"
                    @click="navigateTo(item.vueRouter.path)"
                    :class="{'v-list-item--active': isActive(item.vueRouter.path)}"
                    :prepend-icon="item.icon"
                >
                    <b>{{ item.title }}</b>
                </v-list-item>
            </template>
        </v-list>
    </v-navigation-drawer>
</template>


