<script setup>
import {useRouter} from "vue-router";
import {defineProps, onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/diagnostico";


const props = defineProps({
    confUrl: {
        type   : Boolean,
        default: true
    }
});

const emit   = defineEmits(['saved']);
const router = useRouter();
const id     = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()
    if (id.value > 0 && props.confUrl) {
        const data        = await serviceLoad(id.value);
        diagnostico.value = data;
    }

    loading.hide()
});

const diagnostico = ref({
    id_diagnostico: null,
    nome          : null,
    descricao     : null,
    codigo_cid    : null,
    is_active     : true,
});

const handleSave = async () => {
    loading.show()

    const data = diagnostico.value;
    const res  = await serviceSave(data);

    id.value = res.id;
    if (id.value > 0 && props.confUrl) {
        diagnostico.value.id_diagnostico = id.value;
        adicionarParametrosURL({id: res.id});
    } else {
        emit('close_modal');
    }

    loading.hide()
}

const handleBack = () => {
    router.push({name: 'Diagnostico-List'});
};

</script>

<template>
    <card-formulario title="Cadastro de Diagnóstico"
                     subtitle="Você pode editar as informações a qualquer momento"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-row>
            <v-col cols="12">
                <v-row>

                    <v-col cols="12" sm="12" md="7" lg="7" xl="7" class="pb-0">
                        <v-text-field
                            label="Nome"
                            type="text"
                            v-model="diagnostico.nome"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="CID"
                            type="text"
                            v-model="diagnostico.codigo_cid"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="diagnostico.is_active == true ? 'Ativo' : 'Inativo'"
                            v-model="diagnostico.is_active"
                        />
                    </v-col>


                    <v-col cols="12" sm="12" md="12" lg="12" xl="12" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            v-model="diagnostico.descricao"
                        />
                    </v-col>

                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>