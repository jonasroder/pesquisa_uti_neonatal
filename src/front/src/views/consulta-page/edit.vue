<script setup>
import CardFormulario from "@/components/CardFormulario.vue";
import TextInput from '@/components/TextInput.vue';
import SelectInput from '@/components/SelectInput.vue';
// import {setNotification} from "@/plugins/notificationService";
import {onMounted, reactive, ref} from "vue";
import {serviceLoad} from "@/service/consulta";
import {getIdFromUrl, formatarTelefone} from "@/service/common/utils"
import {loading} from "@/plugins/loadingService.js";
import {useRouter} from "vue-router";
import defaultImagePath from "@/assets/no_image.png";


const id_paciente = ref(getIdFromUrl('id_paciente'));
const id_consulta = ref(getIdFromUrl('id_consulta'));
const router = useRouter();

onMounted(async () => {
    loading.show()

    debugger
    if (id_paciente.value > 0) {
        const data = await serviceLoad(id_paciente.value, id_consulta.value);
        console.log(data)
        Object.assign(consulta, data);
    }

    loading.hide()
});


const consulta = reactive({
    id_paciente: "",
    id_consulta: "",
    foto_perfil: "",
    nome: "",
    idade: "",
    telefone_1: "",
    telefone_2: "",
    plano_saude: "",
    id_tipo_consulta: "",
    convenio: "",
    observacoes: "",

});


const handleSave = async () => {
    loading.show()

    loading.hide()
};


const handleBack = () => {
    router.push({name: 'Paciente-List'});
};


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


</script>

<template>
    <card-formulario title="Consulta de Paciente"
                     subtitle="Registro e Diagnóstico de Consultas"
                     @handleSave="handleSave"
                     @handleBack="handleBack">
        <v-row>
            <v-col cols="12"
                   md="3"
                   lg="2"
                   class="d-flex justify-center align-center">
                <v-avatar size="120"
                          class="ma-1">
                    <img :src="getProfilePhoto(consulta.foto_perfil)"
                         class="fit-cover">
                </v-avatar>
            </v-col>

            <v-col cols="12"
                   md="6"
                   lg="7">
                <div class="text-h5 mb-2"><b>Paciente:</b> {{ consulta.nome }}</div>
                <div class="text-subtitle-1 mb-2"><b>Idade:</b> {{ consulta.idade }} anos</div>
                <div class="text-subtitle-1 mb-2"><b>Telefone:</b> {{ formatarTelefone(consulta.telefone_1) }} / {{ formatarTelefone(consulta.telefone_2) }}</div>
                <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ consulta.plano_saude }}</div>
            </v-col>

            <v-col cols="12"
                   md="3"
                   lg="3">
                <v-row class="justify-end">
                    <SelectInput
                        label="Tipo de Consulta"
                        placeholder="Selecione o tipo da Consulta"
                        idColumn="id_tipo_consulta"
                        descColumn="descricao"
                        tableName="tipo_consulta"
                        :multiple="false"
                        v-model="consulta.id_tipo_consulta"
                        xl="12"
                        lg="12"
                        md="12"
                        sm="12"
                    />
                </v-row>
            </v-col>

        </v-row>


        <v-divider class="border-opacity-100 mt-2 mb-3"
                   color="primary"/>

        <v-row>

            <text-input
                v-model="consulta.nome_paciente"
                label="Nome Paciente"
                type="text"
                cols="12"
            />


        </v-row>
    </card-formulario>
</template>
