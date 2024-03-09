<script setup>
import {ref} from "vue";
import {getSessionUserData} from "@/service/common/tokenService";

const userData = ref(getSessionUserData());

console.log('userData: ' + userData.value);

</script>

<template>
    <v-app-bar flat class="border-b d-flex" color="barrasSperior">
        <!-- conteúdo do app bar -->
        <v-app-bar-nav-icon @click="$emit('toggle-drawer')"/>


        <template #append>
            <v-btn>
                <v-badge dot color="info" class="mr-2">
                    <v-icon icon="far  fa-bell"/>
                </v-badge>
            </v-btn>

            <!--            <v-avatar>-->
            <!--                <v-img cover src="#"></v-img>-->
            <!--            </v-avatar>-->
        </template>

    </v-app-bar>
</template>


