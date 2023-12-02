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

        <!-- Botão para capturar a imagem da webcam -->
        <v-btn v-if="stream" @click="captureFromWebcam">Capturar Imagem</v-btn>

        <!-- Elemento de vídeo para a webcam -->
        <video ref="video" style="display: none;"></video>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const fileInput = ref(null);
const video = ref(null);
const image = ref(null);
const stream = ref(null);

const pickFile = () => {
    fileInput.value.click();
};

const onFileChange = (e) => {
    const file = e.target.files[0];
    createImage(file);
};

const createImage = (file) => {
    const reader = new FileReader();
    reader.onload = (e) => {
        image.value = e.target.result;
    };
    reader.readAsDataURL(file);
};

const activateWebcam = async () => {
    try {
        stream.value = await navigator.mediaDevices.getUserMedia({ video: true });
        video.value.srcObject = stream.value;
        video.value.play();
    } catch (err) {
        console.error("Erro ao acessar a webcam:", err);
    }
};

const captureFromWebcam = () => {
    const canvas = document.createElement("canvas");
    canvas.width = video.value.videoWidth;
    canvas.height = video.value.videoHeight;
    canvas.getContext("2d").drawImage(video.value, 0, 0);

    canvas.toBlob(blob => {
        const file = new File([blob], "webcam-image.png", { type: 'image/png' });
        createImage(file);
        stream.value.getTracks().forEach(track => track.stop());
        stream.value = null;
    }, 'image/png');
};
</script>
