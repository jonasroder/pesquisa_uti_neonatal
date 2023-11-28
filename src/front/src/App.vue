<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import MenuLateral from "@/components/MenuLateral.vue";
import BarraSuperior from "@/components/BarraSuperior.vue";

const route = useRoute();
const isLoginPage = computed(() => route.path === '/login');
</script>


<template>
    <v-app v-if="!isLoginPage">
        <menu-lateral/>
        <barra-superior />
        <v-main>
            <v-container>
                <v-row>
                    <v-col>
                        <v-sheet min-height="70vh"  rounded="lg" color="primary" >
                            <div id="app">
                                <router-view/>
                            </div>
                        </v-sheet>
                    </v-col>
                </v-row>
            </v-container>
        </v-main>
    </v-app>


    <div id="app" v-if="isLoginPage">
        <router-view/>
    </div>

</template>
