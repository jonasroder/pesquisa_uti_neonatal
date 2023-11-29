<script setup>
    import { computed, ref } from 'vue';
    import { useRoute } from 'vue-router';
    import MenuLateral from "@/components/MenuLateral.vue";
    import BarraSuperior from "@/components/BarraSuperior.vue";

    const route = useRoute();
    const isLoginPage = computed(() => route.path === '/login');

    const drawer = ref(false);

    const toggleDrawer = () => {
    drawer.value = !drawer.value;
    console.log(drawer.value);
}


</script>

<template>
    <v-app v-if="!isLoginPage">
        <menu-lateral :drawer="drawer" :key="drawer" />
        <barra-superior @toggle-drawer="toggleDrawer" />
        <v-main>
            <v-container>

                <div id="app">
                    <router-view/>
                </div>

            </v-container>
        </v-main>
    </v-app>


    <div id="app" v-if="isLoginPage">
        <router-view/>
    </div>

</template>
