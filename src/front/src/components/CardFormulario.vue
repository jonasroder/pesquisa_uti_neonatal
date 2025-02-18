<script setup>
import {defineProps, onBeforeUnmount, onMounted, ref} from 'vue';
import {useDisplay} from "vuetify";


defineProps({
    title           : {
        type    : String,
        required: true
    },
    subtitle        : {
        type   : String,
        default: ''
    },
    createButton    : {
        type   : String,
        default: 'Novo Cadastro'
    },
    showCreateButton: {
        type   : Boolean,
        default: false,
    },
});

const {smAndDown} = useDisplay();
const paddingConf = ref(smAndDown.value ? 'pa-0' : '');

const updatePaddingConf = () => {
    paddingConf.value = smAndDown.value ? 'pa-1' : '';
};

onMounted(() => {
    window.addEventListener('resize', updatePaddingConf);
    updatePaddingConf(); // Chamada inicial para definir o valor correto
});

onBeforeUnmount(() => {
    window.removeEventListener('resize', updatePaddingConf);
});
</script>

<template>
    <v-container :class="paddingConf">
        <v-row justify="center" align="center" class="d-flex  pa-0 fill-height border border-sm border-opacity-100 border-barrasSperior rounded">
            <v-col cols="12" :class="paddingConf">
                <v-card>
                    <v-row class="mb-2">
                        <v-col cols="12" sm="12" :md="!$props.showCreateButton ? 12 : 6" :lg="!$props.showCreateButton ? 12 : 8">
                            <v-card-title class="d-flex flex-column align-start text-wrap">
                                <span class="text-h5">{{ $props.title }}</span>
                                <small class="text-subtitle-1">{{ $props.subtitle }}</small>
                            </v-card-title>
                        </v-col>

                        <v-col cols="12" sm="12" md="6" lg="4" v-if="$props.showCreateButton">
                            <div class="d-flex justify-end mt-3 mr-2">
                                <v-btn class="mr-2" color="azulEscuro" @click="$emit('handleNew')">{{ $props.createButton }}</v-btn>
                            </div>
                        </v-col>

                    </v-row>

                    <v-divider></v-divider>

                    <v-card-text :class="paddingConf">
                        <v-form>
                            <slot></slot>
                        </v-form>
                    </v-card-text>

                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>
