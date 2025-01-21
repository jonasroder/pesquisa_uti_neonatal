<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {adicionarParametrosURL, getIdFromUrl, getOptionsAutocomplete, verificarCamposObrigatorios} from "@/service/common/utils"
import {serviceLoad, serviceSave} from "@/service/cadastros/motivo_internacao"


const emit                      = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router                    = useRouter();
const camposObrigatorios        = ref(true);
const id                        = ref(getIdFromUrl());
const optionsSistemaFisiologico = ref();


onMounted(async () => {
    loading.show()

    await getOpcoesAutocomplete();

    if (id.value > 0) {
        motivoInternacao.value = await serviceLoad(id.value);
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});


const motivoInternacao = ref({
    idMotivoInternacao: null,
    descricao         : null,
    codigo            : null,
    isActive          : true,
});


const handleSave = async () => {
    loading.show()

    const data = motivoInternacao.value;

    camposObrigatorios.value = true;

    const verificacoes = [{
        dados : data,
        campos: ['descricao', 'codigo']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }

    const res = await serviceSave(data);

    id.value                                  = res.id;
    motivoInternacao.value.idMotivoInternacao = id.value;
    adicionarParametrosURL({id: res.id});

    loading.hide()
}


const getOpcoesAutocomplete = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_sistema_fisiologico',
        descColumn: 'descricao',
        tableName : 'sistema_fisiologico'
    })]);

    optionsSistemaFisiologico.value = results[0];
};


const handleBack = () => {
    router.push({name: 'MotivoInternacao-List'});
};

</script>

<template>
    <card-formulario title="Motivo Internação"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            :error="!motivoInternacao.descricao && !camposObrigatorios"
                            v-model="motivoInternacao.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                        <v-autocomplete
                            label="Sistema Fisiológico"
                            :items="optionsSistemaFisiologico"
                            :error="!motivoInternacao.codigo && !camposObrigatorios"
                            v-model="motivoInternacao.codigo"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="motivoInternacao.isActive === true ? 'Ativo' : 'Inativo'"
                            v-model="motivoInternacao.isActive"
                        />
                    </v-col>

                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>