<template>
    <v-container> <!-- fluid para largura total e pa-0 para remover o padding -->
        <v-row justify="center" align="center" class="fill-height">
            <v-col cols="12" class="pa-0 fill-height"> <!-- Utilize todas as 12 colunas -->

                <!-- Card principal para o formulário -->
                <v-card class="elevation-2 fill-height"> <!-- Ajuste a elevação conforme necessário e fill-height para altura total -->

                    <!-- Cabeçalho do formulário com título e botão de salvar -->
                    <v-card-title class="justify-space-between"> <!-- Espaço entre o título e o botão -->
                        <span class="text-h5">titulo</span>
                        <v-spacer></v-spacer> <!-- Espaçador para empurrar o botão para a direita -->
                        <v-btn color="primary" @click="saveForm">
                            Salvar
                        </v-btn>
                    </v-card-title>

                    <!-- Conteúdo do formulário -->
                    <v-card-text>
                        <v-form>
                            <v-text-field label="Nome" placeholder="Digite seu nome"></v-text-field>
                            <v-text-field label="Email" placeholder="Digite seu email"></v-text-field>
                            <!-- Adicione mais campos conforme necessário -->
                        </v-form>
                    </v-card-text>

                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script setup>
// Importações necessárias
// import { ref } from 'vue';
//
// // Propriedades e métodos
// const props = defineProps({
//     pageTitle: String
// });

const saveForm = () => {
    // Implemente a lógica de salvamento do formulário aqui
    console.log('Formulário salvo!');
};
</script>
