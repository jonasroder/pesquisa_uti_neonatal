<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, getOptionsAutocomplete} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/formulario_alimentar";
import HeaderPacienteFormularios from "@/components/HeaderPacienteFormularios.vue";

const router            = useRouter();
const id_consulta       = ref(getIdFromUrl('id_consulta'));
const id_paciente       = ref("");
const optionsQ18        = ref([]);
const optionsQ20        = ref([]);
const optionsQ26        = ref([]);
const optionsQ28        = ref([]);
const optionsQ30        = ref([]);
const optionsQ31        = ref([]);
const optionsFrequencia = ref([]);


onMounted(async () => {
    loading.show()

    await getAutoCompleteOptions();

    if (id_consulta.value > 0) {
        const data = await serviceLoad(id_consulta.value);
        paciente.value    = data.paciente;
        id_paciente.value = data.paciente.id_paciente;

        if (data.formulario) {
            formulario.value = data.formulario;
        }
    }

    loading.hide()
});


const getAutoCompleteOptions = async () => {
    const results = await Promise.all([getOptionsAutocomplete({
        idColumn  : 'id_options_q18',
        descColumn: 'descricao',
        tableName : 'options_q18_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_q20',
        descColumn: 'descricao',
        tableName : 'options_q20_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_q26',
        descColumn: 'descricao',
        tableName : 'options_q26_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_q28',
        descColumn: 'descricao',
        tableName : 'options_q28_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_q30',
        descColumn: 'descricao',
        tableName : 'options_q30_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_q31',
        descColumn: 'descricao',
        tableName : 'options_q31_formulario_alimentar'
    }), getOptionsAutocomplete({
        idColumn  : 'id_options_frequencia_formulario_alimentar',
        descColumn: 'descricao',
        tableName : 'options_frequencia_formulario_alimentar'
    }),])

    optionsQ18.value        = results[0];
    optionsQ20.value        = results[1];
    optionsQ26.value        = results[2];
    optionsQ28.value        = results[3];
    optionsQ30.value        = results[4];
    optionsQ31.value        = results[5];
    optionsFrequencia.value = results[6];
}


const paciente = ref({
    id_paciente  : null,
    foto_perfil  : null,
    nome         : null,
    idade        : null,
    plano_saude  : null,
    telefone_1   : null,
    telefone_2   : null,
    data         : null,
    tipo_consulta: null,
})


const formulario = ref({
    id_formulario_alimentar: null,
    id_consulta            : id_consulta.value,
    q15                    : null,
    q16                    : null,
    q17                    : null,
    q18                    : null,
    q19                    : null,
    q20                    : null,
    q25                    : null,
    q26                    : null,
    q27                    : null,
    q28                    : null,
    q29                    : null,
    q30                    : null,
    q31                    : null,
    R301A                  : null,
    R301B                  : null,
    R301C                  : null,
    R301D                  : null,
    R301E                  : null,
    R301F                  : null,
    R301G                  : null,
    R301H                  : null,
    R301I                  : null,
    R301J                  : null,
    R301K                  : null,
    R301L                  : null,
    R302A                  : null,
    R302B                  : null,
    R302C                  : null,
    R302D                  : null,
    R302E                  : null,
    R302F                  : null,
    R302G                  : null,
    R302H                  : null,
    R302I                  : null,
    R302J                  : null,
    R302K                  : null,
    R302L                  : null,
    R302M                  : null,
});


const handleSave = async () => {
    loading.show()

    const data = formulario.value;
    const res  = await serviceSave(data);
    const id   = res.id;

    if (id > 0) {
        formulario.value.id_formulario_alimentar = id;
    }

    loading.hide()
}


const handleBack = () => {
    router.push({
        name : 'Consulta-Page',
        query: {
            id_paciente: id_paciente.value,
            id_consulta: id_consulta.value
        }
    });
};


const imprimirResultados = () => {
    alert('Desenvolvimento Pendente');
}


const visualizarResultados = () => {
    alert('Desenvolvimento Pendente');
}


const verificarRespostasRelacionadasQ16 = () => {
    if(formulario.value.q16 == 6){
        formulario.value.q17 = null;
        formulario.value.q18 = null;
        formulario.value.q19 = null;
        formulario.value.q20 = null;
    }
}


const verificarRespostasRelacionadasQ17 = () => {
    if(formulario.value.q17 == 6 || formulario.value.q17 == 5){
        formulario.value.q18 = null;
    }
}

const verificarRespostasRelacionadasQ19 = () => {
    if(formulario.value.q19 == 6 || formulario.value.q19 == 5){
        formulario.value.q20 = null;
    }
}

const verificarRespostasRelacionadasQ25 = () => {
    if(formulario.value.q25 == 6 || formulario.value.q25 == 5){
        formulario.value.q26 = null;
    }
}

const verificarRespostasRelacionadasQ27 = () => {
    if(formulario.value.q27 == 6 || formulario.value.q27 == 5){
        formulario.value.q28 = null;
    }
}

const verificarRespostasRelacionadasQ29 = () => {
    if(formulario.value.q29 == 6 || formulario.value.q29 == 5){
        formulario.value.q30 = null;
        formulario.value.q31 = null;
    }
}


</script>

<template>
    <card-formulario title="QUESTIONÁRIO ALIMENTAÇÃO"
                     subtitle="Você pode editar as informações a qualquer momento"
                     @handleSave="handleSave"
                     @handleBack="handleBack">


        <HeaderPacienteFormularios :paciente="paciente"
                                   @handleViewResults="visualizarResultados"
                                   @handlePrintResults="imprimirResultados"/>

        <v-row>
            <v-col cols="12">

                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de Feijão</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Dias com feijão"
                                :items="optionsFrequencia"
                                v-model="formulario.q15"
                            />
                            <v-tooltip text="Q15. Em quantos dias da semana, o(a) Sr.(a) costuma comer feijão? "/>
                        </v-col>
                    </v-row>
                </v-col>


                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de vegetais/Legummes</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Dias com vegetais/legumes"
                                :items="optionsFrequencia"
                                v-model="formulario.q16"
                                @update:modelValue="verificarRespostasRelacionadasQ16"
                            />
                            <v-tooltip text="Q16. Em quantos dias da semana, o(a) Sr.(a) costuma comer pelo menos um tipo de verdura ou legume (alface, tomate, couve, cenoura, chuchu, berinjela, abobrinha – não vale batata, mandioca ou inhame)? "/>
                        </v-col>
                    </v-row>
                </v-col>

                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de vegetais crus</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Frequência de consumo de saladas cruas"
                                :items="optionsFrequencia"
                                v-model="formulario.q17"
                                :disabled="formulario.q16 == 6"
                                @update:modelValue="verificarRespostasRelacionadasQ17"
                            />
                            <v-tooltip text="Q17. Em quantos dias da semana, o(a) Sr.(a) costuma comer salada de alface e tomate ou salada de qualquer outra verdura ou legume CRU?"/>
                        </v-col>

                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Refeições com salada"
                                :items="optionsQ18"
                                v-model="formulario.q18"
                                :disabled="formulario.q16 == 6 || formulario.q17 == 5 || formulario.q17 == 6"
                            />
                            <v-tooltip text="Q18. Num dia comum, o(a) sr.(a) come este tipo de salada"/>
                        </v-col>
                    </v-row>
                </v-col>


                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de vegetais cozidos</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Frequência de vegetais cozidos"
                                :items="optionsFrequencia"
                                v-model="formulario.q19"
                                :disabled="formulario.q16 == 6"
                                @update:modelValue="verificarRespostasRelacionadasQ19"
                            />
                            <v-tooltip text="Q19. Em quantos dias da semana, o(a) Sr.(a) costuma comer verdura ou legume COZIDO com a comida ou na sopa, como por exemplo, couve, cenoura, chuchu, berinjela, abobrinha, sem contar batata, mandioca ou inhame?"/>
                        </v-col>

                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Refeições com vegetais cozidos"
                                :items="optionsQ20"
                                v-model="formulario.q20"
                                :disabled="formulario.q16 == 6 || formulario.q19 == 5 || formulario.q19 == 6"
                            />
                            <v-tooltip text="Q20. Num dia comum, o(a) Sr.(a) come verdura ou legume cozido:"/>
                        </v-col>
                    </v-row>
                </v-col>


                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de Suco Natural</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Frequência de consumo de suco natural"
                                :items="optionsFrequencia"
                                v-model="formulario.q25"
                                @update:modelValue="verificarRespostasRelacionadasQ25"
                            />
                            <v-tooltip text="Q25. Em quantos dias da semana o(a) Sr.(a) costuma tomar suco de frutas natural?"/>
                        </v-col>

                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Quantidade diária de suco natural"
                                :items="optionsQ26"
                                v-model="formulario.q26"
                                :disabled="formulario.q25 == 6 || formulario.q25 == 5"
                            />
                            <v-tooltip text="Q26. Num dia comum, quantos copos o(a) Sr.(a) toma de suco de frutas natural?"/>
                        </v-col>
                    </v-row>
                </v-col>


                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de Frutas</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Frequência de consumo de frutas"
                                :items="optionsFrequencia"
                                v-model="formulario.q27"
                                @update:modelValue="verificarRespostasRelacionadasQ27"
                            />
                            <v-tooltip text="Q27. Em quantos dias da semana o(a) Sr.(a) costuma comer frutas?"/>
                        </v-col>


                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Quantidade diária de frutas consumidas"
                                :items="optionsQ28"
                                v-model="formulario.q28"
                                :disabled="formulario.q27 == 6 || formulario.q27 == 5"
                            />
                            <v-tooltip text="Q28. Num dia comum, quantas vezes o(a) Sr.(a) come frutas?"/>
                        </v-col>
                    </v-row>
                </v-col>


                <v-col cols="12">
                    <v-row>
                        <h4>Consumo de Bebidas</h4>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                        <v-col cols="12" sm="12" md="4" lg="4" xl="4" class="pb-0">
                            <v-autocomplete
                                label="Frequência de refrigerante/suco artificial"
                                :items="optionsFrequencia"
                                v-model="formulario.q29"
                                @update:modelValue="verificarRespostasRelacionadasQ29"
                            />
                            <v-tooltip text="Q29. Em quantos dias da semana o(a) Sr.(a) costuma tomar refrigerante ou suco artificial?"/>
                        </v-col>

                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Tipo de bebida"
                                :items="optionsQ30"
                                v-model="formulario.q30"
                                :disabled="formulario.q29 == 6 || formulario.q29 == 5"
                            />
                            <v-tooltip text="Q30. Que tipo?"/>
                        </v-col>

                        <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                            <v-autocomplete
                                label="Quantidade diária de bebidas"
                                :items="optionsQ31"
                                v-model="formulario.q31"
                                :disabled="formulario.q29 == 6 || formulario.q29 == 5"
                            />
                            <v-tooltip text="Q31. Quantos copos/latinhas contuma tomar por dia?"/>
                        </v-col>

                    </v-row>
                </v-col>


                <v-row class="mt-4">
                    <v-col cols="12">
                        <v-alert type="info">
                            <p>Agora vou listar alguns alimentos e gostaria que o Sr.(a) me dissesse se comeu algum deles ontem (desde quando acordou até quando foi dormir) </p>
                        </v-alert>
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="mt-3">
                        <v-card class="mx-auto" variant="elevated">
                            <v-card-item>
                                <div>
                                    <div class="text-h6 mb-1">
                                        <v-row class="align-center justify-space-between">
                                            <v-col>
                                                <span>Alimentos ou produtos industrializados</span>
                                            </v-col>
                                        </v-row>
                                    </div>

                                    <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301A" label="a. Alface, couve, brócolis, agrião ou espinafre">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301B" label="b. Abóbora, cenoura, batata-doce ou quiabo/caruru">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301C" label="c. Mamão, manga, melão amarelo ou pequi">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301D" label="d. Tomate, pepino, abobrinha, berinjela, chuchu ou beterraba">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301E" label="e. Laranja, banana, maçã ou abacaxi">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301F" label="f. Arroz, macarrão, polenta, cuscuz ou milho verde">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301G" label="g. Feijão, ervilha, lentilha ou grão de bico">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301H" label="h. Batata comum, mandioca, cará ou inhame">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301I" label="i. Carne de boi, porco, frango ou peixe ">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301J" label="j. Ovo frito, cozido ou mexido">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301K" label="k. Leite">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R301L" label="l. Amendoim, castanha-de-caju ou castanha-do-Brasil/Pará">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                </div>
                            </v-card-item>
                        </v-card>
                    </v-col>


                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="mt-3">

                        <v-card class="mx-auto" variant="elevated">
                            <v-card-item>
                                <div>
                                    <div class="text-h6 mb-1">
                                        <v-row class="align-center justify-space-between">
                                            <v-col>
                                                <span>Alimentos ou produtos industrializados</span>
                                            </v-col>
                                        </v-row>
                                    </div>

                                    <v-divider class="border-opacity-100 mt-3 mb-3" color="primary"/>


                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302A" label="a. Refrigerante">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302B" label="b. Suco de fruta em caixa, caixinha ou lata">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302C" label="c. Refresco em pó">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302D" label="d. Bebida achocolatada">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302E" label="e. Iogurte com sabor">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302F" label="f. Salgadinho de pacote (ou chips) ou biscoito/bolacha salgado">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302G" label="g. Biscoito/bolacha doce, biscoito recheado ou bolinho de pacote">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302H" label="h. Chocolate, sorvete, gelatina, flan ou outra sobremesa industrializada">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302I" label="i. Salsicha, linguiça, mortadela ou presunto">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302J" label="j. Pão de forma, de cachorro-quente ou de hambúrguer">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302K" label="k. Maionese, ketchup ou mostarda">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302L" label="l. Margarina">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>

                                    <v-col cols="12">
                                        <v-radio-group v-model="formulario.R302M" label="m. Macarrão instantâneo (como miojo), sopa de pacote, lasanha congelada ou outro prato pronto comprado congelado">
                                            <v-radio label="Sim" :value="true"></v-radio>
                                            <v-radio label="Não" :value="false"></v-radio>
                                        </v-radio-group>
                                    </v-col>
                                </div>
                            </v-card-item>
                        </v-card>
                    </v-col>


                </v-row>
            </v-col>


        </v-row>
    </card-formulario>
</template>

