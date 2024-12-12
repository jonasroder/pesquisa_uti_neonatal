<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete, verificarCamposObrigatorios, verificarCodigo} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/microorganismo";
import {setNotification} from "@/plugins/notificationService";


const emit                               = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router                             = useRouter();
const optionsGenero                      = ref();
const optionsClassificacaoMicroorganismo = ref();
const camposObrigatorios                 = ref(true);
const id                                 = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()
    await getOpcoesAutocomplete();

    if (id.value > 0) {
        const data           = await serviceLoad(id.value);
        microorganismo.value = data;
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});



const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_genero',
        descColumn: 'descricao',
        tableName : 'genero'
    }), getOptionsAutocomplete({
        idColumn  : 'id_classificacao_microorganismo',
        descColumn: 'descricao',
        tableName : 'classificacao_microorganismo'
    })]);

    optionsGenero.value                      = results[0];
    optionsClassificacaoMicroorganismo.value = results[1];
};

const microorganismo = ref({
    idMicroorganismo             : null,
    idGenero                     : null,
    idClassificacaoMicroorganismo: null,
    especie                      : null,
    codigo                       : null,
    acronimo                     : null,
    isActive                     : true,
});


const verivicarCodigo = async (codigo = null) => {
    const statusCodigo = await verificarCodigo({
        codigo: codigo,
        tabela: 'microorganismo'
    });

    if (statusCodigo.codigoExistente || codigo === null) {
        if (codigo !== null) {
            setNotification(`O código ${codigo} já existe! Sugerido: ${statusCodigo.codigo}`, "warning");
        }
        microorganismo.value.codigo = statusCodigo.codigo;
    } else {
        setNotification(`O código ${codigo} está disponível.`, "success");
    }
};


const handleSave = async () => {
    loading.show()

    const data = microorganismo.value;

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : data,
        campos: ['acronimo', 'codigo', 'idGenero', 'idClassificacaoMicroorganismo', 'especie']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(data);

    id.value                              = res.id;
    microorganismo.value.idMicroorganismo = id.value;
    adicionarParametrosURL({id: res.id});

    loading.hide()
}


const handleBack = () => {
    router.push({name: 'Microorganismo-List'});
};

</script>

<template>
    <card-formulario title="Cadastro de Microorganismo"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row>
            <v-col cols="12">
                <v-row>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Gênero"
                            :items="optionsGenero"
                            :error="!microorganismo.idGenero && !camposObrigatorios"
                            v-model="microorganismo.idGenero"
                        />
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Tipo"
                            :items="optionsClassificacaoMicroorganismo"
                            :error="!microorganismo.idClassificacaoMicroorganismo && !camposObrigatorios"
                            v-model="microorganismo.idClassificacaoMicroorganismo"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-text-field
                            label="Espécie"
                            type="text"
                            :error="!microorganismo.especie && !camposObrigatorios"
                            v-model="microorganismo.especie"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-text-field
                            label="Acrônimo"
                            type="text"
                            :error="!microorganismo.acronimo && !camposObrigatorios"
                            v-model="microorganismo.acronimo"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="2" lg="6" xl="6" class="pb-0">
                        <v-text-field
                            label="Código"
                            type="number"
                            :error="!microorganismo.codigo && !camposObrigatorios"
                            v-model="microorganismo.codigo"
                            @blur="verivicarCodigo(microorganismo.codigo)"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="microorganismo.isActive === true ? 'Ativo' : 'Inativo'"
                            v-model="microorganismo.isActive"
                        />
                    </v-col>


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>