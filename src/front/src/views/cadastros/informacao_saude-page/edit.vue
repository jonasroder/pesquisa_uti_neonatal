<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/informacao_saude";


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
        const data             = await serviceLoad(id.value);
        informacao_saude.value = data;
    }

    loading.hide()
});

const informacao_saude = ref({
    id_tipo_informacao_saude: null,
    nome                    : null,
    descricao               : null,
    is_active               : true,
});

const handleSave = async () => {
    loading.show()

    const data = informacao_saude.value;
    const res  = await serviceSave(data);

    id.value = res.id;
    if (id.value > 0 && props.confUrl) {
        informacao_saude.value.id_tipo_informacao_saude = id.value;
        adicionarParametrosURL({id: res.id});
    } else {
        emit('close_modal');
    }

    loading.hide()
}

const handleBack = () => {
    router.push({name: 'InformacaoSaude-List'});
};

</script>

<template>
    <card-formulario title="Cadastro do tipo de informação saúde"
                     subtitle="Você pode editar as informações a qualquer momento"
                     @handleSave="handleSave"
                     @handleBack="handleBack">

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Nome"
                            type="text"
                            v-model="informacao_saude.nome"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="7" lg="7" xl="7" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            v-model="informacao_saude.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="informacao_saude.is_active == true ? 'Ativo' : 'Inativo'"
                            v-model="informacao_saude.is_active"
                        />
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>