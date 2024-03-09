<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, getOptionsAutocomplete, formatarTelefone} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/formulario_pittsburgh";
import defaultImagePath from "@/assets/no_image.png";

const router                      = useRouter();
const id_consulta                 = ref(getIdFromUrl('id_consulta'));
const id_paciente                 = ref("");
const optionsFrequenciaDificulade = ref([]);
const optionsPresencaParceiro     = ref([]);
const optionsProblemaEntusiasmo   = ref([]);
const optionsQualidadeSono        = ref([]);


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
        idColumn  : 'id_frequencia_dificuldades',
        descColumn: 'descricao',
        tableName : 'frequencia_dificuldades'
    }), await getOptionsAutocomplete({
        idColumn  : 'id_presenca_parceiro',
        descColumn: 'descricao',
        tableName : 'presenca_parceiro'
    }), await getOptionsAutocomplete({
        idColumn  : 'id_problema_entusiasmo',
        descColumn: 'descricao',
        tableName : 'problema_entusiasmo'
    }), await getOptionsAutocomplete({
        idColumn  : 'id_qualidade_sono',
        descColumn: 'descricao',
        tableName : 'qualidade_sono'
    }),])

    optionsFrequenciaDificulade.value = results[0];
    optionsPresencaParceiro.value     = results[1];
    optionsProblemaEntusiasmo.value   = results[2];
    optionsQualidadeSono.value        = results[3];
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
    id_formulario_pittsburgh    : null,
    id_consulta                 : id_consulta.value,
    horario_deitar              : null,
    tempo_adormecer             : null,
    horario_levantar            : null,
    horas_sono                  : null,
    dificuldade_adormecer       : null,
    acordou_meio_noite          : null,
    levantou_banheiro           : null,
    dificuldade_respirar        : null,
    tossiu_roncou               : null,
    sentiu_frio                 : null,
    sentiu_calor                : null,
    sonhos_ruins                : null,
    teve_dor                    : null,
    outra_dificuldade           : null,
    frequencia_outra_dificuldade: null,
    qualidade_sono              : null,
    medicamento_sono            : null,
    dificuldade_ficar_acordado  : null,
    problema_entusiasmo         : null,
    tem_parceiro_quarto         : null,
    ronco_forte                 : null,
    paradas_respiracao          : null,
    contracoes_pernas           : null,
    desorientacao_sono          : null,
    outras_alteracoes_sono      : null,
    frequencia_outras_alteracoes: null,
});


const handleSave = async () => {
    loading.show()

    const data = formulario.value;
    const res  = await serviceSave(data);
    const id   = res.id;

    if (id > 0) {
        formulario.value.id_formulario_pittsburgh = id;
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


const getProfilePhoto = (path) => {
    return path ? `${path}` : defaultImagePath;
};


const imprimirResultados = () => {
    alert('Desenvolvimento Pendente');
}


const visualizarResultados = () => {
    alert('Desenvolvimento Pendente');
}

</script>

<template>
    <card-formulario title="QUALIDADE DE SONO DE PITTSBURGH (PSQI-BR)"
                     subtitle="Você pode editar as informações a qualquer momento"
                     @handleSave="handleSave"
                     @handleBack="handleBack">
        <v-row>
            <v-col cols="12" md="3" lg="2" class="d-flex justify-center align-center">
                <v-avatar size="130" class="ma-1">
                    <img :src="getProfilePhoto(paciente.foto_perfil)" alt="foto perfil" class="fit-cover">
                </v-avatar>
            </v-col>

            <v-col cols="12" md="6" lg="7">
                <div class="text-h5 mb-2"><b>Paciente:</b> {{ paciente.nome }}</div>
                <div class="text-subtitle-1 mb-2">
                    <b>Idade:</b> {{ paciente.idade }}
                    <span v-if="paciente.idade !== null">anos</span>
                </div>
                <div class="text-subtitle-1 mb-2">
                    <b>Telefone:</b> {{ formatarTelefone(paciente.telefone_1) }}
                    <span v-if="paciente.telefone_2">/ {{ formatarTelefone(paciente.telefone_2) }}</span>
                </div>
                <div class="text-subtitle-1 mb-2"><b>Plano de Saúde:</b> {{ paciente.plano_saude }}</div>
                <div class="text-subtitle-1 mb-2"><b>{{ paciente.tipo_consulta }}:</b> {{ paciente.data }}</div>
            </v-col>

            <v-col cols="12" md="3" lg="3">
                <v-row class="justify-end mr-2" v-if="id_consulta">
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="visualizarResultados">Visualizar Resultados</v-btn>
                    <v-btn class="mb-2" size="small" block color="azulEscuro" @click="imprimirResultados">Imprimir Resultados</v-btn>
                </v-row>
            </v-col>

        </v-row>

        <v-row>
            <v-col cols="12">

                <v-divider class="border-opacity-100 mb-2" color="primary"/>

                <v-row>

                    <v-col cols="12">
                        <v-alert
                            text="As seguintes perguntas são relativas aos seus hábitos usuais de sono durante o último mês somente.
                                As respostas devem indicar a lembrança mais exata da maioria dos dias e noites no último mês. Por favor, responda a todas as perguntas."
                            type="info"
                        />
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Hora usual de deitar"
                            type="time"
                            v-model="formulario.horario_deitar"
                        />
                        <v-tooltip text="Durante o último mês, quando você geralmente foi para a cama à noite?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Minutos para dormir"
                            type="number"
                            v-model="formulario.tempo_adormecer"
                        />
                        <v-tooltip text="Durante o último mês, quanto tempo (em minutos) você geralmente levou para dormir à noite?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Hora usual de levantar"
                            type="time"
                            v-model="formulario.horario_levantar"
                        />
                        <v-tooltip text="Durante o último mês, quando você geralmente levantou de manhã?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Horas de sono por noite"
                            type="number"
                            v-model="formulario.horas_sono"
                        />
                        <v-tooltip text="Durante o último mês, quantas horas de sono você teve por noite? (Este pode ser diferente do número de horas que você ficou na cama)."/>
                    </v-col>



                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <h3>
                                Durante o último mês, com que frequência você teve dificuldade de dormir porque você...
                            </h3>
                            <v-icon class="mr-3">fas fa-bed</v-icon>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve dificuldade de Adormecer"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.dificuldade_adormecer"
                        />
                        <v-tooltip text="Não conseguiu adormecer em até 30 minutos"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Acordou no meio da noite"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.acordou_meio_noite"
                        />
                        <v-tooltip text="Acordou no meio da noite ou de manhã cedo"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Levantou para ir ao banheiro"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.levantou_banheiro"
                        />
                        <v-tooltip text="Precisou levantar para ir ao banheiro "/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve dificuldade ao Respirar"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.dificuldade_respirar"
                        />
                        <v-tooltip text="Não conseguiu respirar confortavelmente"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Tossiu ou roncou forte"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.tossiu_roncou"
                        />
                        <v-tooltip text="Tossiu ou roncou forte"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sentiu muito frio"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.sentiu_frio"
                        />
                        <v-tooltip text="Sentiu muito frio"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sentiu muito calor"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.sentiu_calor"
                        />
                        <v-tooltip text="Sentiu muito calor"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve sonhos ruins"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.sonhos_ruins"
                        />
                        <v-tooltip text="Teve sonhos ruins"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve dor"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.teve_dor"
                        />
                        <v-tooltip text="Teve dor"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="6" lg="6" xl="6" class="pb-0">
                        <v-text-field
                            label="Outra(s)	razão(ões),	por	favor descreva"
                            type="text"
                            v-model="formulario.outra_dificuldade"
                        />
                        <v-tooltip text="Descreva por quais outros motivos você teve dificuldade de adormecer."/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Qual a freqüência das outras razões"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.frequencia_outra_dificuldade"
                        />
                        <v-tooltip text="Com que freqüência, durante o último mês, você teve dificuldade para dormir devido a essa razão?"/>
                    </v-col>



                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <h3>
                                Sobre sua rotina Responda ...
                            </h3>
                            <v-icon class="mr-3">fas fa-list</v-icon>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Qual a qualidade geral do sono"
                            :items="optionsQualidadeSono"
                            v-model="formulario.qualidade_sono"
                        />
                        <v-tooltip text="Durante o último mês, como você classificaria a qualidade do seu sono de uma maneira geral?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Tomou medicamento para dormir"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.medicamento_sono"
                        />
                        <v-tooltip text="Durante o último mês, com que freqüência você tomou medicamento (prescrito ou “por conta própria”) para lhe ajudar a dormir?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve dificuldae para ficar acordado"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.dificuldade_ficar_acordado"
                        />
                        <v-tooltip text="No último mês, com que freqüência você teve dificuldade de ficar acordado enquanto dirigia, comia ou participava de uma atividade social (festa, reunião de amigos, trabalho, estudo)?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve problemas com entusiasmo (ânimo)"
                            :items="optionsProblemaEntusiasmo"
                            v-model="formulario.problema_entusiasmo"
                        />
                        <v-tooltip text="Durante o último mês, quão problemático foi para você manter o entusiasmo (ânimo) para fazer as coisas (suas atividades habituais)?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Tem parceiro de quarto"
                            :items="optionsPresencaParceiro"
                            v-model="formulario.tem_parceiro_quarto"
                        />
                        <v-tooltip text="Você tem um(a) parceiro [esposo(a)] ou colega de quarto?"/>
                    </v-col>



                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <h3>
                                Se você tem um parceiro ou colega de quarto, pergunte a ele/ela com que freqüência no último mês você teve...
                            </h3>
                            <v-icon class="mr-3">fas fa-user-friends</v-icon>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Roncou forte"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.ronco_forte"
                        />
                        <v-tooltip text="Roncou forte"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve paradas na Respiração"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.paradas_respiracao"
                        />
                        <v-tooltip text="Longas paradas na respiração enquanto dormia"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve contrações e puxões nas pernas"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.contracoes_pernas"
                        />
                        <v-tooltip text="Contrações ou puxões nas pernas enquanto você dormia"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Teve episódios de desorientação"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.desorientacao_sono"
                        />
                        <v-tooltip text="Episódios de desorientação ou confusão durante o sono"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="9" lg="9" xl="9" class="pb-0">
                        <v-text-field
                            label="Outras alterações (inquietações), por	favor descreva"
                            type="text"
                            v-model="formulario.outras_alteracoes_sono"
                        />
                        <v-tooltip text="Outras alterações (inquietações) enquanto você dorme; por favor, descreva"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Qual a freqüência das outras alterações"
                            :items="optionsFrequenciaDificulade"
                            v-model="formulario.frequencia_outras_alteracoes"
                        />
                        <v-tooltip text="Com que freqüência, durante o último mês, foi observado essas alterações?"/>
                    </v-col>

                </v-row>
            </v-col>


        </v-row>
    </card-formulario>
</template>

<style lang="scss">
p {
    color: darken(#ccc, 10%);
    font-weight: 300;
}
</style>