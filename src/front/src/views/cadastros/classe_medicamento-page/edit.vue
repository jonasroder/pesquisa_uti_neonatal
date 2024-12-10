<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete, verificarCamposObrigatorios, verificarCodigo} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/medicamento";
import {setNotification} from "@/plugins/notificationService";


const emit                         = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router                       = useRouter();
const optionsFabricanteMedicamento = ref([]);
const camposObrigatorios           = ref(true);
const id                           = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()
    await getOptionsFabricanteMedicamento();

    if (id.value > 0) {
        const data        = await serviceLoad(id.value);
        medicamento.value = data;
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});


const getOptionsFabricanteMedicamento = async () => {
    optionsFabricanteMedicamento.value = await getOptionsAutocomplete({
        idColumn  : 'id_classe_antimicrobiano',
        descColumn: 'descricao',
        tableName : 'classe_antimicrobiano'
    })
}


const medicamento = ref({
    idMedicamento         : null,
    descricao             : null,
    codigo                : null,
    idClasseAntimicrobiano: null,
    isActive              : true,
});


const verivicarCodigo = async (codigo = null) => {
    const statusCodigo = await verificarCodigo({
        codigo: codigo,
        tabela: 'antimicrobiano'
    });

    if (statusCodigo.codigoExistente || codigo === null) {
        if(codigo !== null) {
            setNotification(`O código ${codigo} já existe! Sugerido: ${statusCodigo.codigo}`, "warning");
        }
        medicamento.value.codigo = statusCodigo.codigo;
    } else {
        setNotification(`O código ${codigo} está disponível.`, "success");
    }
};


const handleSave = async () => {
    loading.show()

    const data = medicamento.value;

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : data,
        campos: ['descricao', 'codigo', 'idClasseAntimicrobiano']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(data);

    id.value                        = res.id;
    medicamento.value.idMedicamento = id.value;
    adicionarParametrosURL({id: res.id});

    loading.hide()
}

const handleBack = () => {
    console.log('teste')
    router.push({name: 'Medicamento-List'});
};

</script>

<template>
    <card-formulario title="Cadastro de Medicamento"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            :error="!medicamento.descricao && !camposObrigatorios"
                            v-model="medicamento.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Classe Antimicrobiano"
                            :items="optionsFabricanteMedicamento"
                            :error="!medicamento.idClasseAntimicrobiano && !camposObrigatorios"
                            v-model="medicamento.idClasseAntimicrobiano"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="2" lg="2" xl="2" class="pb-0">
                        <v-text-field
                            label="Código"
                            type="number"
                            :error="!medicamento.codigo && !camposObrigatorios"
                            v-model="medicamento.codigo"
                            @blur="verivicarCodigo(medicamento.codigo)"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="medicamento.isActive === true ? 'Ativo' : 'Inativo'"
                            v-model="medicamento.isActive"
                        />
                    </v-col>


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>