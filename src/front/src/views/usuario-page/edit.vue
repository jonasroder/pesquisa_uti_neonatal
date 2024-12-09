<script setup>
import {useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete, getMaskSettings, verificarCamposObrigatorios} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/usuario";


const emit               = defineEmits(['set-back-action', 'set-save-action', 'set-show-buttons']);
const router             = useRouter();
const optionsRole        = ref();
const id                 = ref(getIdFromUrl());
const logs               = ref();
const maskCelular        = reactive(getMaskSettings({maskType: 'celular'}));
const maskCPF            = reactive(getMaskSettings({maskType: 'cpf'}));
const camposObrigatorios = ref(true);

onMounted(async () => {
    loading.show()
    await getOptionsRole();

    if (id.value > 0) {
        const data = await serviceLoad(id.value);
        logs.value = data.loginLog;
        delete data.loginLog;
        usuario.value = data;
    }

    emit('set-back-action', handleBack);
    emit('set-save-action', handleSave);
    emit('set-show-buttons', true);
    loading.hide()
});


const getOptionsRole = async () => {
    optionsRole.value = await getOptionsAutocomplete({
        idColumn  : 'id_role',
        descColumn: 'nome',
        tableName : 'role'
    })
}


const usuario = ref({
    idUsuario   : null,
    usuario     : null,
    senha       : null,
    nomeCompleto: null,
    telefone    : null,
    cpf         : null,
    idRole      : 2,
    isActive    : true,
});


const handleSave = async () => {
    loading.show()
    const data = usuario.value;
    camposObrigatorios.value = true;
    const verificacoes = [{
        dados : data,
        campos: ['usuario', 'cpf', 'nomeCompleto', 'idRole']
    }];

    if (!verificarCamposObrigatorios(verificacoes)) {
        camposObrigatorios.value = false;
        loading.hide();
        return;
    }


    const res  = await serviceSave(data);

    if (res.success) {
        id.value                    = res.id;
        usuario.value.idMedicamento = id.value;
        adicionarParametrosURL({id: res.id});
    }

    loading.hide()
}

const handleBack = () => {
    console.log('teste')
    router.push({name: 'Usuario-List'});
};



const headers = ref([{
    title: 'Data',
    key  : 'dataHora',
}, {
    title: 'IP Adress',
    key  : 'ipAddress',
}, {
    title: 'Status',
    key  : 'status',
}, {
    title: 'Dispositivo',
    key  : 'userAgent',
}]);
</script>

<template>
    <card-formulario title="Cadastro de Usuário"
                     subtitle="Você pode editar as informações a qualquer momento">

        <v-row class="pa-2">
            <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                <v-text-field
                    label="Nome Completo"
                    type="text"
                    v-model="usuario.nomeCompleto"
                    :error="!usuario.nomeCompleto && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="CPF"
                    type="text"
                    v-maska:[maskCPF]
                    v-model="usuario.cpf"
                    :error="!usuario.cpf && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                <v-text-field
                    label="Telefone"
                    type="text"
                    v-maska:[maskCelular]
                    v-model="usuario.telefone"
                />
            </v-col>


            <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                <v-switch
                    class="d-flex justify-center"
                    :true-value="true"
                    :false-value="false"
                    :label="usuario.isActive === true ? 'Ativo' : 'Inativo'"
                    v-model="usuario.isActive"
                />
            </v-col>


            <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                <v-text-field
                    label="Email"
                    type="email"
                    v-model="usuario.usuario"
                    :error="!usuario.usuario && !camposObrigatorios"
                />
            </v-col>

            <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                <v-text-field
                    label="Senha"
                    type="password"
                    v-model="usuario.senha"
                />
            </v-col>

            <v-col cols="12" sm="12" md="3" lg="4" xl="4" class="pb-0">
                <v-autocomplete
                    label="Permissões"
                    :items="optionsRole"
                    v-model="usuario.idRole"
                    :error="!usuario.idRole && !camposObrigatorios"
                />
            </v-col>
        </v-row>

        <v-row class="pa-2">
            <v-data-table :headers="headers"
                          :items="logs"
                          :hover="true"
                          class="elevation-1">
                <template #top>
                    <v-toolbar flat>
                        <v-toolbar-title>Histórico de Logins</v-toolbar-title>
                    </v-toolbar>
                </template>
            </v-data-table>
        </v-row>

    </card-formulario>
</template>