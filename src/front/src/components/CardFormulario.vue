<template>
    <v-container class="fill-height">
        <v-row justify="center" align="center" class="fill-height">
            <v-col cols="12" class="pa-0 fill-height">
                <v-card class="elevation-2 fill-height">
                    <v-card-title class="d-flex justify-space-between">
                        <!-- Título alinhado à esquerda, acessado diretamente via $props -->
                        <span class="text-h5">{{ $props.title }}</span>

                        <!-- Container div para os botões -->
                        <div>
                            <v-btn color="secondary" @click="$emit('handleBack')">Voltar</v-btn>
                            <v-btn color="primary" @click="$emit('handleSave')">Salvar</v-btn>
                        </div>
                    </v-card-title>

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

import { defineProps } from 'vue';

// Definindo as props diretamente
defineProps({
    title: {
        type: String,
        required: true
    }
});

</script>
