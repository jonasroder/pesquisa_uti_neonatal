<script setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import CardFormulario from "@/components/CardFormulario.vue";
import {loading} from "@/plugins/loadingService";
import {getIdFromUrl, formatarTelefone} from "@/service/common/utils"
import {serviceSave, serviceLoad} from "@/service/formulario_atividade_fisica";
import defaultImagePath from "@/assets/no_image.png";

const router      = useRouter();
const id_consulta = ref(getIdFromUrl('id_consulta'));
const id_paciente = ref("");


const optionsDiasSemana = ref([{
    value: 0,
    label: 'Nenhum'
}, {
    value: 1,
    label: '1 dia por semana'
}, {
    value: 2,
    label: '2 dias por semana'
}, {
    value: 3,
    label: '3 dias por semana'
}, {
    value: 4,
    label: '4 dias por semana'
}, {
    value: 5,
    label: '5 dias por semana'
}, {
    value: 6,
    label: '6 dias por semana'
}, {
    value: 7,
    label: '7 dias por semana'
}]);

onMounted(async () => {
    loading.show()

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
    id_formulario_atividade_fisica: null,
    id_consulta                   : id_consulta.value,
    tempo_atividade_vigorosa      : null,
    dias_atividade_vigorosa       : null,
    tempo_atividade_moderada      : null,
    dias_atividade_moderada       : null,
    tempo_caminhada_moderada      : null,
    dias_caminhada_moderada       : null,
    tempo_sentado_dia_util        : null,
    tempo_sentado_fds             : null,
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
    <card-formulario title="QUESTIONÁRIO INTERNACIONAL DE ATIVIDADE FÍSICA"
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
                        <v-alert type="info">
                            <p>As perguntas estão relacionadas ao tempo que você gasta fazendo atividade física em uma semana
                                <strong>NORMAL, USUAL ou HABITUAL</strong>. As perguntas incluem as atividades que você faz no trabalho, para ir de um lugar a outro, por lazer, por esporte, por exercício ou como parte das suas atividades em casa a ou no jardim. Suas respostas são
                                <strong>MUITO</strong> importantes. Por favor responda cada questão mesmo que considere que não seja ativo.
                            </p><br>
                            <p>Para responder as questões lembre-se que:<br>
                                <strong>*Atividades físicas VIGOROSAS</strong> são aquelas que precisam de um grande esforço físico e que fazem respirar
                                <strong>MUITO</strong> mais forte que o normal.<br>
                                <strong>*Atividades físicas MODERADAS</strong> são aquelas que precisam de algum esforço físico e que fazem respirar
                                <strong>UM POUCO</strong> mais forte que o normal.
                            </p>
                        </v-alert>
                    </v-col>


                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <p>
                                Em quantos dias de uma semana normal, você realiza atividades
                                <strong>VIGOROSAS</strong> por
                                <u>pelo menos 10 minutos contínuos</u>, como por exemplo correr, fazer ginástica, aeróbica, jogar futebol, pedalar rápido na bicicleta, jogas basquete, fazer serviços domésticos pesados em casa, no quintal ou no jardim, carregar pesos elevados ou qualquer atividade que faça você suar
                                <strong>BASTANTE</strong> ou aumentem
                                <strong>MUITO</strong> sua respiração ou batimentos do coração.
                            </p>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Dias por semana"
                            :items="optionsDiasSemana"
                            v-model="formulario.dias_atividade_vigorosa"
                        />
                        <v-tooltip text="Dias de atividades físicas vigorosas por semana"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Tempo por dia"
                            type="time"
                            v-model="formulario.tempo_atividade_vigorosa"
                        />
                        <v-tooltip text="Tempo atividade física vigorosa por dia"/>
                    </v-col>


                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <p>
                                Em quantos dias de uma semana normal, você realiza atividades
                                <strong>MODERADAS</strong> por
                                <u>pelo menos 10 minutos contínuos</u>, como por exemplo pedalar leve na bicicleta, nadar, dançar, fazer ginástica aeróbica leve, jogar vôlei recreativo, carregar pesos leves, fazer serviços domésticos na casa, no quintal ou no jardim como varrer, aspirar, cuidar do jardim, ou qualquer atividade que faça você suar leve ou aumentem, moderadamente sua respiração ou batimentos do coração (POR FAVOR NÃO INCLUA CAMINHADA)
                            </p>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Dias por semana"
                            :items="optionsDiasSemana"
                            v-model="formulario.dias_atividade_moderada"
                        />
                        <v-tooltip text="Dias de atividades físicas moderadas por semana"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Tempo por dia"
                            type="time"
                            v-model="formulario.tempo_atividade_moderada"
                        />
                        <v-tooltip text="Tempo atividade física moderada por dia"/>
                    </v-col>


                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <p>
                                Em quantos dias de uma semana normal você caminha por
                                <u>pelo menos 10 minutos contínuos</u> em casa ou no trabalho, como forma de transporte para ir de um lugar para outro, por lazer, por prazer ou como forma de exercício?
                            </p>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3">
                        <v-autocomplete
                            label="Dias por semana"
                            :items="optionsDiasSemana"
                            v-model="formulario.dias_caminhada_moderada"
                        />
                        <v-tooltip text="Dias de de caminhada por pelo menos 10 minutos contínuos por semana"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Tempo por dia"
                            type="time"
                            v-model="formulario.tempo_caminhada_moderada"
                        />
                        <v-tooltip text="Tempo de caminhada por pelo menos 10 minutos contínuos por dia"/>
                    </v-col>


                    <v-col cols="12" class="mt-3">
                        <div class="d-flex justify-space-between align-center">
                            <p>
                                Estas últimas perguntas são em relação ao tempo que você gasta sentado ao todo no trabalho, em casa, na escola ou faculdade e durante o tempo livre. Isto inclui o tempo que você gasta sentado no escritório ou estudando, fazendo lição de casa, visitando amigos, lendo e sentado ou deitado assistindo televisão.
                            </p>
                        </div>
                        <v-divider class="border-opacity-100 mt-2 mb-2" color="primary"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Tempo por dia da semana"
                            type="time"
                            v-model="formulario.tempo_sentado_dia_util"
                        />
                        <v-tooltip text="Quanto tempo por dia você fica sentado em um dia da semana?"/>
                    </v-col>

                    <v-col cols="12" sm="12" md="3" lg="3" xl="3" class="pb-0">
                        <v-text-field
                            label="Tempo por dia no final de semana"
                            type="time"
                            v-model="formulario.tempo_sentado_fds"
                        />
                        <v-tooltip text="Quanto tempo por dia você fica sentado no final de semana?"/>
                    </v-col>


                </v-row>
            </v-col>


        </v-row>
    </card-formulario>
</template>

