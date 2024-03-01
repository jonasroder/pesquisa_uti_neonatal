<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, adicionarParametrosURL, getOptionsAutocomplete} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/cadastros/suplemento";

const router                   = useRouter();
const optionsIngrediente       = ref([]);
const id                       = ref(getIdFromUrl());
const arrSuplementoIngrediente = ref([]);

const suplemento = ref({
    id_suplemento : null,
    nome          : null,
    descricao     : null,
    dosagem       : null,
    instrucoes_uso: null,
    is_active     : true,
});

const suplementoIgredienteTemplate = {
    id_suplemento_ingrediente: null,
    dosagem                  : null,
    id_ingrediente           : null,
    is_active                : true,
}


onMounted(async () => {
    loading.show()
    await getOptionsIngrediente();

    if (id.value > 0) {
        const data        = await serviceLoad(id.value);
        arrSuplementoIngrediente.value = data.arrSuplementosIngredientes;
        suplemento.value = data.suplementoResponse;
    }

    loading.hide()
});


const getOptionsIngrediente = async () => {
    optionsIngrediente.value = await getOptionsAutocomplete({
        idColumn  : 'id_ingrediente',
        descColumn: 'nome',
        tableName : 'ingrediente'
    })
}


const handleSave = async () => {
    loading.show()

    const data = {
        suplementoRequest : suplemento.value,
        suplementosIngredienteRequest : arrSuplementoIngrediente.value
    }


    const res  = await serviceSave(data);

    id.value = res.id;
    if (id.value > 0) {
        suplemento.value.id_suplemento = id.value;
        adicionarParametrosURL({id: res.id});
    }

    loading.hide()
}


const handleBack = () => {
    router.push({name: 'Suplemento-List'});
};


const addSuplementoIgrediente = () => {
    const novoSuplementoIgrediente = {...suplementoIgredienteTemplate};
    arrSuplementoIngrediente.value.push(novoSuplementoIgrediente);
};


const removerItem = (info, arrayObj, id) => {
    const index = arrayObj.findIndex(item => item[id] === info[id] || item === info);

    if (index !== -1) {
        if (info[id]) {
            arrayObj[index].is_active = 0;
        } else {
            arrayObj.splice(index, 1);
        }
    }
}

</script>

<template>
    <card-formulario title="Cadastro de Suplemento"
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
                            v-model="suplemento.nome"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="2" lg="2" xl="2" class="pb-0">
                        <v-text-field
                            label="Dose"
                            type="text"
                            v-model="suplemento.dosagem"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="5" lg="5" xl="5" class="pb-0">
                        <v-text-field
                            label="Descrição"
                            type="text"
                            v-model="suplemento.descricao"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="2" xl="2" class="pb-0 pt-0">
                        <v-switch
                            class="d-flex justify-center"
                            :true-value="true"
                            :false-value="false"
                            :label="suplemento.is_active == true ? 'Ativo' : 'Inativo'"
                            v-model="suplemento.is_active"
                        />
                    </v-col>

                    <v-col cols="12"  sm="12" md="6" lg="6" xl="6">
                        <v-textarea
                            label="Observações"
                            v-model="suplemento.instrucoes_uso"
                        />
                    </v-col>


                    <v-col cols="12"  sm="12" md="6" lg="6" xl="6">
                        <v-card class="mx-auto" variant="elevated">
                            <v-card-item>
                                <div>
                                    <div class="text-h6 mb-1">
                                        Composição do Suplemento
                                    </div>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                    <div class="text-caption">

                                        <v-row
                                            v-for="(info, i) in arrSuplementoIngrediente.filter(item => item.is_active !== 0)"
                                            :key="`med-${i}`"
                                            class="pb-0"
                                        >

                                            <v-col xl="2" lg="2" md="2" sm="2" cols="3" class="scale-80 pt-2 pb-0">
                                                <v-btn icon x-small variant="elevated" @click="() => removerItem(info, arrSuplementoIngrediente, 'id_suplemento_ingrediente')">
                                                    <v-icon size="small" color="red"> fas fa-trash-alt</v-icon>
                                                </v-btn>
                                            </v-col>


                                            <v-col cols="9" sm="6" md="6" lg="6" xl="6" class="pb-0">
                                                <v-autocomplete
                                                    label="Ingrediente"
                                                    :items="optionsIngrediente"
                                                    v-model="info.id_ingrediente"
                                                />
                                            </v-col>

                                            <v-col cols="12" md="4" lg="4" xl="4" class="pb-0">
                                                <v-text-field
                                                    label="Dose (mg/ml)"
                                                    type="text"
                                                    v-model="info.dosagem"
                                                />
                                            </v-col>

                                            <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>

                                        </v-row>
                                    </div>
                                </div>
                            </v-card-item>
                            <v-card-actions>
                                <v-btn class="ms-2" variant="elevated" size="small" color="cinzaAzulado" @click="addSuplementoIgrediente">
                                    Adicionar
                                </v-btn>
                            </v-card-actions>

                        </v-card>
                    </v-col>


                </v-row>
            </v-col>
        </v-row>
    </card-formulario>
</template>