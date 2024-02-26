<script setup>
import {onMounted, reactive, ref, watch} from 'vue';
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";
import {getMenu} from "@/service/menu";

const router = useRouter();
const menuLateral = ref([]);

onMounted(async () => {
    loading.show()
    menuLateral.value = await getMenu();
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
    <v-navigation-drawer v-model="localDrawerState" app>
        <v-list>
            <v-list-item>
                <v-list-subheader>Menu</v-list-subheader>
            </v-list-item>

            <v-divider/>

            <template v-for="item in menuLateral">
                <v-list-group
                    v-if="item.subMenus && item.subMenus.length"
                    :key="item.title"
                >
                    <template #activator="{ props }">
                        <v-list-item
                            v-bind="props"
                            :title="item.title"
                            :prepend-icon="item.icon"
                        />
                    </template>

                    <v-list-item
                        v-for="subItem in item.subMenus"
                        :key="subItem.title"
                        @click="navigateTo(subItem.vueRouter.path)"
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
                >
                    {{ item.title }}
                </v-list-item>
            </template>
        </v-list>
    </v-navigation-drawer>
</template>


