<script setup >
    import { watch, ref } from 'vue';

    const props = defineProps({
        drawer: Boolean
    });

    const localDrawerState = ref(props.drawer);


    watch(() => props.drawer, (newVal) => {
        localDrawerState.value = newVal;
    });




    const links = ref([
        { icon: 'mdi-home', text: 'Home' },
        { icon: 'mdi-account', text: 'Profile' },
    ]);






</script>

<template>

    <v-navigation-drawer v-model="localDrawerState" app>
    <!-- Cabeçalho da Barra Lateral -->
        <v-sheet class="pa-4" tile>
            <v-list-item>
                <v-icon left>mdi-icon-name</v-icon>
                <v-list-item-title>Título do item</v-list-item-title>
                <v-list-item-subtitle>Subtítulo do item</v-list-item-subtitle>
            </v-list-item>

        </v-sheet>

        <v-divider></v-divider>

        <!-- Itens da lista -->
        <v-list dense nav>
            <v-list-item v-for="link in links" :key="link.text" link>
                <v-icon>{{ link.icon }}</v-icon>
                <v-list-item-title>{{ link.text }}</v-list-item-title>
            </v-list-item>
        </v-list>
    </v-navigation-drawer>
</template>


