<script setup>
import { ref, watch } from 'vue';
import {useRouter} from "vue-router";


const router = useRouter();


const props = defineProps({
    drawer: Boolean
});

const localDrawerState = ref(props.drawer);

watch(() => props.drawer, (newVal) => {
    localDrawerState.value = newVal;
});



const links = ref([
    { icon: 'fas fa-home', text: 'Início', to: '/' },
    { icon: 'fas fa-user', text: 'Pacientes', to: '/paciente/index' },
    { icon: 'fas fa-table', text: 'Cadastros', to: '/tables' },
]);


const navigateTo = (route) => {
    router.push(route);
}

const  isActive = (route) => {
    return route === router.currentRoute.value.path;
}


</script>


<template>
    <v-navigation-drawer v-model="localDrawerState" app>
        <v-list-item>
            <v-list-subheader>Menu</v-list-subheader>
        </v-list-item>

        <v-divider/>

        <v-list-item
            v-for="link in links"
            :key="link.text"
            @click="navigateTo(link.to)"
            :class="{'v-list-item--active': isActive(link.to)}"
            :prepend-icon="link.icon"
        >
            {{ link.text }}
        </v-list-item>

    </v-navigation-drawer>
</template>

