<template>
    <v-container class="fill-height">
        <v-row justify="center" align="center" class="pa-1 fill-height border border-sm border-opacity-100 border-barrasSperior rounded" >
            <v-col cols="12" class="fill-height">
                <v-card class="elevation-2 fill-height">

                    <v-card-title class="d-flex flex-row justify-space-between align-center">
                        <div class="d-flex flex-column">
                            <span class="text-h5">{{ $props.title }}</span>
                            <span class="text-subtitle-1 ml-4">{{ $props.subtitle }}</span>
                        </div>

                        <div class="d-flex justify-end">
                            <v-btn class="mr-2" color="cinzaAzulado" @click="$emit('handleBack')">Voltar</v-btn>
                            <v-btn color="azulEscuro" @click="$emit('handleSave')">Salvar</v-btn>
                        </div>
                    </v-card-title>

                    <VDivider class="mb-3"/>

                    <v-card-text>
                        <v-form>
                            <!-- Slot para campos de formulário customizados -->
                            <slot></slot>
                        </v-form>
                    </v-card-text>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
import {defineProps} from 'vue';

// Definindo as props diretamente
defineProps({
    title: {
        type: String,
        required: true
    },
    subtitle: {
        type: String,
        required: false
    }
});
</script>
