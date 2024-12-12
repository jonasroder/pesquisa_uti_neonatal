<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete, verificarCamposObrigatorios, verificarCodigo} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/classe_medicamento";
import {setNotification} from "@/plugins/notificationService";


const emit                      = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router                    = useRouter();
const optionsTipoAntimicrobiano = ref();
const camposObrigatorios        = ref(true);
const id                        = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()
    await getOptionsFabricanteMedicamento();

    if (id.value > 0) {
        const data              = await serviceLoad(id.value);
        classeMedicamento.value = data;
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});


const getOptionsFabricanteMedicamento = async () => {
    optionsTipoAntimicrobiano.value = await getOptionsAutocomplete({
        idColumn  : 'id_tipo_antimicrobiano',
        descColumn: 'descricao',
        tableName : 'tipo_antimicrobiano'
    })
}


const classeMedicamento = ref({
    idClasseAntimicrobiano: null,
    descricao             : null,
    codigo                : null,
    idTipoAntimicrobiano  : null,
    isActive              : true,
});


const verivicarCodigo = async (codigo = null) => {
    const statusCodigo = await verificarCodigo({
        codigo: codigo,
        tabela: 'classe_antimicrobiano'
    });

    if (statusCodigo.codigoExistente || codigo === null) {
        if (codigo !== null) {
            setNotification(`O código ${codigo} já existe! Sugerido: ${statusCodigo.codigo}`, "warning");
        }
        classeMedicamento.value.codigo = statusCodigo.codigo;
    } else {
        setNotification(`O código ${codigo} está disponível.`, "success");
    }
};


const handleSave = async () => {
    loading.show()

    const data = classeMedicamento.value;

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : data,
        campos: ['descricao', 'codigo', 'idTipoAntimicrobiano']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(data);

    id.value                                       = res.id;
    classeMedicamento.value.idClasseAntimicrobiano = id.value;
    adicionarParametrosURL({id: res.id});

    loading.hide()
}

const handleBack = () => {
    console.log('teste')
    router.push({name: 'ClasseMedicamento-List'});
};

</script>

<template>
    <card-formulario title="Cadastro Classe Medicamento"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            :error="!classeMedicamento.descricao && !camposObrigatorios"
                            v-model="classeMedicamento.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Classe"
                            :items="optionsTipoAntimicrobiano"
                            :error="!classeMedicamento.idTipoAntimicrobiano && !camposObrigatorios"
                            v-model="classeMedicamento.idTipoAntimicrobiano"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="2" lg="2" xl="2" class="pb-0">
                        <v-text-field
                            label="Código"
                            type="number"
                            :error="!classeMedicamento.codigo && !camposObrigatorios"
                            v-model="classeMedicamento.codigo"
                            @blur="verivicarCodigo(classeMedicamento.codigo)"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="classeMedicamento.isActive === true ? 'Ativo' : 'Inativo'"
                            v-model="classeMedicamento.isActive"
                        />
                    </v-col>

                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>