<script setup>

    import { ref } from 'vue';

    const links = ref([
        'Dashboard',
        'Messages',
        'Profile',
        'Updates',
    ]);

</script>

<template>
    <v-app-bar flat class="border-b" color="barrasSperior">
        <!-- conteúdo do app bar -->
        <v-app-bar-nav-icon @click="$emit('toggle-drawer')" />
        <v-app-bar-title>Saúde em Equilibrio</v-app-bar-title>

        <v-btn v-for="link in links" :key="link" :text="link" variant="text"></v-btn>



        <template #append>
            <v-btn>
                <v-badge dot color="info" class="mr-2">
                    <v-icon  icon="far  fa-bell"/>
                </v-badge>
            </v-btn>

            <v-avatar>
                <v-img cover src="#"></v-img>
            </v-avatar>
        </template>

    </v-app-bar>
</template>


