<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/ingrediente";

const router = useRouter();
const id     = ref(getIdFromUrl());


onMounted(async () => {
    loading.show()
    if (id.value > 0) {
        const data        = await serviceLoad(id.value);
        ingrediente.value = data;
    }

    loading.hide()
});

const ingrediente = ref({
    id_ingrediente: null,
    nome          : null,
    descricao     : null,
    is_active     : true,
});

const handleSave = async () => {
    loading.show()

    const data = ingrediente.value;
    const res  = await serviceSave(data);

    id.value = res.id;
    if (id.value > 0) {
        ingrediente.value.id_ingrediente = id.value;
        adicionarParametrosURL({id: res.id});
    }

    loading.hide()
}

const handleBack = () => {
    router.push({name: 'Ingrediente-List'});
};

</script>

<template>
    <card-formulario title="Cadastro de Ingrediente"
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
                            v-model="ingrediente.nome"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="7" lg="7" xl="7" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            v-model="ingrediente.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="ingrediente.is_active == true ? 'Ativo' : 'Inativo'"
                            v-model="ingrediente.is_active"
                        />
                    </v-col>
                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>