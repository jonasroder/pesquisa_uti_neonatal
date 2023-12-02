<template>
    <div>
        <input type="file" @change="onFileChange" ref="fileInput" style="display: none" />

        <v-avatar v-if="image" :size="180" @click="pickFile" cover>
            <v-img :src="image" cover></v-img>
        </v-avatar>

        <v-avatar v-else :size="180" @click="pickFile" color="grey lighten-4">
            <v-icon size="32" class="fa fa-camera"></v-icon>
        </v-avatar>

        <!-- Botão para ativar a webcam -->
        <v-btn @click="activateWebcam">Ativar Webcam</v-btn>

        <!-- Elemento de vídeo para a webcam -->
        <video ref="video" style="display: none;"></video>
    </div>
</template>

<script>
export default {
    data() {
        return {
            image: null,
            stream: null, // Stream da webcam
        };
    },
    methods: {
        pickFile() {
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
        async activateWebcam() {
            try {
                this.stream = await navigator.mediaDevices.getUserMedia({ video: true });
                this.$refs.video.srcObject = this.stream;
                this.$refs.video.play();
                // Poderia adicionar um botão para capturar a imagem aqui
            } catch (err) {
                console.error("Erro ao acessar a webcam:", err);
            }
        },
        // Método para capturar a imagem da webcam
        captureFromWebcam() {
            const canvas = document.createElement("canvas");
            canvas.width = this.$refs.video.videoWidth;
            canvas.height = this.$refs.video.videoHeight;
            canvas.getContext("2d").drawImage(this.$refs.video, 0, 0);
            this.image = canvas.toDataURL("image/png");
            // Encerra o stream da webcam
            this.stream.getTracks().forEach(track => track.stop());
        },
    },
};
</script>
