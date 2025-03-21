<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, verificarCamposObrigatorios, verificarCodigo} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/idade_gestacional";
import {setNotification} from "@/plugins/notificationService";


const emit               = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router             = useRouter();
const camposObrigatorios = ref(true);
const id                 = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()

    if (id.value > 0) {
        const data    = await serviceLoad(id.value);
        request.value = data;
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});


const request = ref({
    id       : null,
    descricao: null,
    codigo   : null,
    isActive : true,
});


const verivicarCodigo = async (codigo = null) => {
    const statusCodigo = await verificarCodigo({
        codigo: codigo,
        tabela: 'idade_gestacional'
    });

    if (statusCodigo.codigoExistente || codigo === null) {
        if (codigo !== null) {
            setNotification(`O código ${codigo} já existe! Sugerido: ${statusCodigo.codigo}`, "warning");
        }
        request.value.codigo = statusCodigo.codigo;
    } else {
        setNotification(`O código ${codigo} está disponível.`, "success");
    }
};


const handleSave = async () => {
    loading.show()

    const data = request.value;

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

    id.value         = res.id;
    request.value.id = id.value;
    adicionarParametrosURL({id: res.id});

    loading.hide()
}

const handleBack = () => {
    router.push({name: 'IdadeGestacional-List'});
};

</script>

<template>
    <card-formulario title="Cadastro Idade Gestacional"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row>
            <v-col cols="12">
                <v-row>
                    <v-col cols="12" sm="12" md="3" lg="8" xl="8" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            :error="!request.descricao && !camposObrigatorios"
                            v-model="request.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="2" lg="2" xl="2" class="pb-0">
                        <v-text-field
                            label="Código"
                            type="number"
                            :error="!request.codigo && !camposObrigatorios"
                            v-model="request.codigo"
                            @blur="verivicarCodigo(request.codigo)"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="request.isActive === true ? 'Ativo' : 'Inativo'"
                            v-model="request.isActive"
                        />
                    </v-col>


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>