<script setup>
import {onMounted, ref} from 'vue';
import CardListagem from "@/components/CardListagem.vue";
import {loading} from "@/plugins/loadingService";
import {serviceList} from "@/service/cadastros/local_nascimento";
import {useRouter} from "vue-router";
import TabelaListagemCadastro from "@/components/TabelaListagemCadastro.vue";


const emit   = defineEmits(['set-show-buttons']);
const data   = ref([]);
const router = useRouter();

onMounted(async () => {
    loading.show();
    data.value = await serviceList();

    emit('set-show-buttons', false);
    loading.hide();
});


const navigateToEditPage = (id) => {
    router.push({
        name : 'LocalNascimento-Page',
        query: {id: id}
    });
};
</script>

<template>
    <CardListagem
        title="Local do Nascimento"
        subtitle="Escolha um para editar ou cadastre um novo"
        @handleNew="() => navigateToEditPage(null)"
    >
        <TabelaListagemCadastro
            :data="data"
            :navigateToEditPage="navigateToEditPage"
            search-placeholder="Buscar na tabela"
        />
    </CardListagem>
</template>
