<template>
    <div>
        <!-- Input de arquivo oculto -->
        <input type="file" @change="onFileChange" ref="fileInput" style="display: none" />

        <!-- Avatar que, ao ser clicado, aciona o input de arquivo -->
        <v-avatar v-if="image" :size="180" @click="pickFile" cover>
            <v-img :src="image" cover></v-img>
        </v-avatar>

        <!-- Placeholder do avatar se nenhuma imagem for selecionada -->
        <v-avatar v-else :size="180" @click="pickFile" color="grey lighten-4">
            <v-icon size="32" class="fa fa-camera"></v-icon>
        </v-avatar>
    </div>
</template>

<script>
export default {
    data() {
        return {
            image: null, // Para armazenar a imagem
        };
    },
    methods: {
        pickFile() {
            // Aciona o clique no input de arquivo
            this.$refs.fileInput.click();
        },
        onFileChange(e) {
            const file = e.target.files[0];
            this.createImage(file);
        },
        createImage(file) {
            const reader = new FileReader();
            reader.onload = (e) => {
                this.image = e.target.result;
            };
            reader.readAsDataURL(file);
        },
    },
};
</script>
