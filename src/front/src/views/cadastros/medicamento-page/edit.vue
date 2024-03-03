<script setup>
import {useRouter} from "vue-router";
import {defineProps, onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/medicamento";

const props = defineProps({
    confUrl: {
        type   : Boolean,
        default: true
    }
});

const emit                         = defineEmits(['saved']);
const router                       = useRouter();
const optionsFabricanteMedicamento = ref([]);
const id                           = ref(getIdFromUrl());

onMounted(async () => {
    loading.show()
    await getOptionsFabricanteMedicamento();

    if (id.value > 0 && props.confUrl) {
        const data        = await serviceLoad(id.value);
        medicamento.value = data;
    }

    loading.hide()
});


const getOptionsFabricanteMedicamento = async () => {
    optionsFabricanteMedicamento.value = await getOptionsAutocomplete({
        idColumn  : 'id_fabricante_medicamento',
        descColumn: 'descricao',
        tableName : 'fabricante_medicamento'
    })
}


const medicamento = ref({
    id_medicamento           : null,
    nome                     : null,
    dosagem                  : null,
    id_fabricante_medicamento: null,
    instrucoes               : null,
    is_active                : true,
});

const handleSave = async () => {
    loading.show()

    const data = medicamento.value;
    const res  = await serviceSave(data);

    id.value = res.id;
    if (id.value > 0 && props.confUrl) {
        medicamento.value.id_medicamento = id.value;
        adicionarParametrosURL({id: res.id});
    } else {
        emit('close_modal');
    }

    loading.hide()
}

const handleBack = () => {
    router.push({name: 'Medicamento-List'});
};

</script>

<template>
    <card-formulario title="Cadastro de Medicamento"
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
                            v-model="medicamento.nome"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Dose"
                            type="text"
                            v-model="medicamento.dosagem"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Fabricante"
                            :items="optionsFabricanteMedicamento"
                            v-model="medicamento.id_fabricante_medicamento"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="medicamento.is_active == true ? 'Ativo' : 'Inativo'"
                            v-model="medicamento.is_active"
                        />
                    </v-col>

                    <v-col cols="12">
                        <v-textarea
                            label="Observações"
                            v-model="medicamento.instrucoes"
                        />
                    </v-col>


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>