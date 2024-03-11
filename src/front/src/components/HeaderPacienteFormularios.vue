<script setup>
import {defineProps} from 'vue';
import {formatarTelefone} from "@/service/common/utils"
import defaultImagePath from "@/assets/no_image.png";

defineProps({
    paciente: {
        type    : Object,
        required: true
    }
});

const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


</script>


<template>
    <v-row>
        <v-col cols="12" md="3" lg="2" class="d-flex justify-center align-center">
            <v-avatar size="130" class="ma-1">
                <img :src="getProfilePhoto(paciente.foto_perfil)" alt="foto perfil" class="fit-cover">
            </v-avatar>
        </v-col>

        <v-col cols="12" md="6" lg="7">
            <div class="text-h5 mb-2"><b>Paciente:</b> {{ paciente.nome }}</div>
            <div class="text-subtitle-1 mb-2">
                <b>Idade:</b> {{ paciente.idade }}
                <span v-if="paciente.idade !== null">anos</span>
            </div>
            <div class="text-subtitle-1 mb-2">
                <b>Telefone:</b> {{ formatarTelefone(paciente.telefone_1) }}
                <span v-if="paciente.telefone_2">/ {{ formatarTelefone(paciente.telefone_2) }}</span>
            </div>
            <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ paciente.plano_saude }}</div>
            <div class="text-subtitle-1 mb-2"><b>{{ paciente.tipo_consulta }}:</b> {{ paciente.data }}</div>
        </v-col>

        <v-col cols="12" md="3" lg="3">
            <v-row class="justify-end mr-2">
                <v-btn class="mb-2" size="small" block color="azulEscuro" @click="$emit('handleViewResults')">Visualizar Resultados</v-btn>
                <v-btn class=" mb-2
                " size="small" block color="azulEscuro" @click="$emit('handlePrintResults')">Imprimir Resultados</v-btn>
            </v-row>
        </v-col>
    </v-row>
</template>
