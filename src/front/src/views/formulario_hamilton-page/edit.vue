<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, getOptionsAutocomplete, formatarTelefone} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/formulario_hamilton";
import defaultImagePath from "@/assets/no_image.png";

const router             = useRouter();
const id_consulta        = ref(getIdFromUrl('id_consulta'));
const id_paciente        = ref("");
const optionsIntensidade = ref([]);


onMounted(async () => {
    loading.show()
    await getOptionsEscalaIntensidade();
    if (id_consulta.value > 0) {
        const data = await serviceLoad(id_consulta.value);

        paciente.value = data.paciente;
        id_paciente.value = data.paciente.id_paciente;

        if (data.formulario) {
            formulario_hamilton.value = data.formulario;
        }
    }

    loading.hide()
});


const getOptionsEscalaIntensidade = async () => {
    optionsIntensidade.value = await getOptionsAutocomplete({
        idColumn  : 'id_intensidade',
        descColumn: 'descricao',
        tableName : 'escala_intensidade'
    })
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


const formulario_hamilton = ref({
    id_formulario_hamilton       : null,
    id_consulta                  : id_consulta,
    id_humor_ansioso             : null,
    id_tensao                    : null,
    id_medos                     : null,
    id_insonia                   : null,
    id_dificuldades_intelectuais : null,
    id_humor_deprimido           : null,
    id_somatizacoes_motoras      : null,
    id_somatizacoes_sensoriais   : null,
    id_sintomas_cardiovasculares : null,
    id_sintomas_respiratorios    : null,
    id_sintomas_gastrointestinais: null,
    id_sintomas_geniturinarios   : null,
    id_sintomas_neurovegetativos : null,
    id_comportamento_entrevista  : null,
});


const handleSave = async () => {
    loading.show()

    const data = formulario_hamilton.value;
    const res  = await serviceSave(data);
    const id   = res.id;

    if (id > 0) {
        formulario_hamilton.value.id_formulario_hamilton = id;
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
    <card-formulario title="Escala de Avaliação de Ansiedade de Hamilton"
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
                <v-row>
                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Humor Ansioso"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_humor_ansioso"
                        />
                        <v-tooltip text="Inquietação, temor do pior, apreensão quanto ao presente ou ao futuro, maus pressentimentos, irritabilidade"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Tensão"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_tensao"
                        />
                        <v-tooltip text="Sensação de tensão, fadiga, reações de sobressalto, choro fácil, tremores, sensação de cansaço, incapacidade de relaxar e agitação"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Medos"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_medos"
                        />
                        <v-tooltip text="De escuro, de estranhos, de ficar só, de animais de grande porte, de trânsito, de multidões"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Medos"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_insonia"
                        />
                        <v-tooltip text="Dificuldade em adormecer, sono interrompido, sono insatisfatório, fadiga de acordar, sonhos penosos, pesadelos, terror noturno"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Dificuldades Intelectuais"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_dificuldades_intelectuais"
                        />
                        <v-tooltip text="Dificuldade de concentração, falhas de memória."/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Humor Deprimido"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_humor_deprimido"
                        />
                        <v-tooltip text="Perda de interesse, oscilação do humor, depressão, despertar precoce"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Somatizações Motoras"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_somatizacoes_motoras"
                        />
                        <v-tooltip text="Dores musculares, rigidez muscular, contrações espásticas, contrações involuntárias,  ranger de dentes, voz insegura"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Somatizações Sensoriais"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_somatizacoes_sensoriais"
                        />
                        <v-tooltip text="Ondas de frio ou calor, sensação de fraqueza, visão borrada, sensação de picadas, formigamento, sensações auditivas de ruído, zumbidos"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sintomas Cardiovasculares"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_sintomas_cardiovasculares"
                        />
                        <v-tooltip text="Taquicardia, palpitações, dores no peito, sensação de desmaio, sensação de extra – sístole"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sintomas Respiratorios"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_sintomas_respiratorios"
                        />
                        <v-tooltip text="Pressão ou constrição no peito, dispneia, respiração suspirosa, sensação de sufocação"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sintomas Gastrintestinais"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_sintomas_gastrointestinais"
                        />
                        <v-tooltip text="Deglutição difícil, aerofagia, dispepsia, sensação de plenitude, dor pré- ou pós prandial, pirose, meteorismo, náusea, vômitos, sensação de vazio gástrico, diarréia ou constipação e cólicas"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sintomas Geniturinários"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_sintomas_geniturinarios"
                        />
                        <v-tooltip text="Polaciúria, urgência de micção, amenorréia, menorragia, frigidez, ejaculação precoce, ereção incompleta, impotência, diminuição da libido"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Sintomas Neurovegetativos"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_sintomas_neurovegetativos"
                        />
                        <v-tooltip text="Boca seca, palidez, ruborização, tendência à sudação, tonturas, cefaléia de tensão"/>
                    </v-col>


                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Comportamento Durante Uma Entrevista"
                            :items="optionsIntensidade"
                            v-model="formulario_hamilton.id_comportamento_entrevista"
                        />
                        <v-tooltip text="Geral: tenso, pouco à vontade, inquieto, agitação das mãos (mexer, retorcer, cacoetes, tremores);
                            Fisiológico: engolir a saliva, arrotos, dilatação pupilar, sudação, respiração suspirosa;
                            "/>
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