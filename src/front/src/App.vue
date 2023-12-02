<script setup>
import {computed, ref} from 'vue';
import {useRoute} from 'vue-router';
import MenuLateral from "@/components/MenuLateral.vue";
import BarraSuperior from "@/components/BarraSuperior.vue";

const route = useRoute();
const isLoginPage = computed(() => route.path === '/login');

const drawer = ref(true);

const toggleDrawer = () => {
    drawer.value = !drawer.value;
    console.log(drawer.value);
}


</script>

<template>
    <v-app v-if="!isLoginPage" >
        <barra-superior @toggle-drawer="toggleDrawer"/>
        <menu-lateral :drawer="drawer" :key="drawer"/>

        <v-main class="d-flex">
            <v-container>
                <router-view/>
            </v-container>
        </v-main>
    </v-app>

    <v-app v-if="isLoginPage">
        <v-main class="d-flex">
        <router-view/>
        </v-main>
    </v-app>
</template>
