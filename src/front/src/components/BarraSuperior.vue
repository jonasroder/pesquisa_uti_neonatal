<template>
    <v-app-bar app class="border-b d-flex barra-superior" color="cyan-darken-3">
        <v-app-bar-nav-icon @click="$emit('toggle-drawer')"/>
        <v-toolbar-title>Grupo de Pesquisa em Epidemiologia Neonatal</v-toolbar-title>
        <v-spacer/>
        <v-btn class="mr-2" variant="elevated" color="cinzaAzulado" @click="$emit('handle-back')">Voltar</v-btn>
        <v-btn color="azulEscuro" variant="elevated" @click="$emit('handle-save')">Salvar</v-btn>
    </v-app-bar>
</template>

<style scoped>
.barra-superior {
    background: linear-gradient(to right, #6fbfd9, #00bdda);
}
</style>
