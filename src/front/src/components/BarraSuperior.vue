<script setup>
import {removeToken} from "@/service/common/tokenService";
import router from '@/router';

const logout = () => {
    removeToken();
    router.push('/login');
}
</script>

<template>
    <v-app-bar app class="border-b d-flex barra-superior" color="cyan-darken-3">

        <v-app-bar-nav-icon @click="$emit('toggle-drawer')"/>
        <v-toolbar-title>Grupo de Pesquisa em Epidemiologia Neonatal</v-toolbar-title>

        <v-spacer/>

        <v-btn icon @click="logout">
            <v-icon>fa-solid fa-arrow-right-from-bracket</v-icon>
            <v-tooltip text="Sair"/>
        </v-btn>

    </v-app-bar>
</template>


<style>
::v-deep .barra-superior {
    background: linear-gradient(to right, #6fbfd9, #00bdda);
}

</style>
