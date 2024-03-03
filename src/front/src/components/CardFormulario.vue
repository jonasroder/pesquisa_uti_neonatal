<template>
    <v-container>
        <v-row justify="center" align="center" class="d-flex  pa-0 fill-height border border-sm border-opacity-100 border-barrasSperior rounded">
            <v-col cols="12">
                <v-card>
                    <v-row class="mb-2">
                        <v-col cols="12" sm="12" md="8" lg="10">
                            <v-card-title class="d-flex flex-column align-start text-wrap">
                                <span class="text-h5">{{ $props.title }}</span>
                                <small class="text-subtitle-1">{{ $props.subtitle }}</small>
                            </v-card-title>
                        </v-col>

                        <v-col cols="12" sm="12" md="4" lg="2">
                            <div class="d-flex justify-end mt-3 mr-2">
                                <v-btn v-if="isModal" class="mr-2" variant="elevated" color="cinzaAzulado" @click="$emit('close_modal')">Fechar</v-btn>
                                <v-btn v-else variant="elevated" class="mr-2" color="cinzaAzulado" @click="$emit('handleBack')">Voltar</v-btn>

                                <v-btn color="azulEscuro" variant="elevated" @click="$emit('handleSave')">Salvar</v-btn>
                            </div>
                        </v-col>
                    </v-row>

                    <v-divider></v-divider>

                    <v-card-text>
                        <v-form>
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

defineProps({
    title   : {
        type    : String,
        required: true
    },
    subtitle: {
        type   : String,
        default: ''
    },
    isModal: {
        type: Boolean,
        default: false
    }
});
</script>
