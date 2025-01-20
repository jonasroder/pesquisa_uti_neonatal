<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import {onMounted, reactive, ref} from "vue";
import {loading} from "@/plugins/loadingService";
import {serviceSaveFilaDownloadExcel} from "@/service/neonato";


const emit                    = defineEmits(['close_modal']);
const disableSaveButton       = ref(false);
const tiutloPatina            = ref("Solicitação Download");
const subtituloPagina         = ref("O arquivo poderá ser baixado no menu de Downloads");


const data = reactive({
    descricao                : null,
});


onMounted(async () => {
    loading.show();


    loading.hide();
});



const handleSave = async () => {
    disableSaveButton.value = true;
    loading.show();
    await serviceSaveFilaDownloadExcel(data);
    loading.hide();
    emit('close_modal');
    disableSaveButton.value = false;
};


const handleCloseModal = () => {
    emit('close_modal');
};


</script>


<template>
    <card-formulario :title="tiutloPatina"
                     :subtitle="subtituloPagina">

        <v-col cols="12" class="pa-1">

            <v-row class="align-center mt-1">

                    <v-text-field
                        label="Descrição"
                        density="compact"
                        variant="outlined"
                        v-model="data.descricao"
                    />
            </v-row>

                <v-alert
                    density="compact"
                    text="Acrescentar Filtros aqui!"
                    type="warning"
                ></v-alert>



                <v-divider/>

            <v-row class="align-center mt-1">
                <v-col cols="12">
                    <v-card-actions>
                        <v-spacer/>
                        <v-btn :disabled="disableSaveButton" class="mr-2" variant="elevated" color="cinzaAzulado" @click="handleCloseModal">Fechar</v-btn>
                        <v-btn :disabled="disableSaveButton" color="azulEscuro" variant="elevated" @click="handleSave">Salvar</v-btn>
                    </v-card-actions>
                </v-col>
            </v-row>
        </v-col>
    </card-formulario>

</template>