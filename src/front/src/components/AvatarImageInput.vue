<script setup>
import {ref, computed, watch, defineProps, defineEmits, onMounted} from 'vue';

const props = defineProps({
    modelValue: [String, Object]
});


const fileInput = ref(null);
const video = ref(null);
const div_video = ref(null);
const image = ref({base64: null, metadata: {name: null, type: null, size: null}});
const stream = ref(null);
const emit = defineEmits(['update:modelValue']);


onMounted(() => {
    if (props.modelValue) {
        image.value = props.modelValue;
    }
});


const updateImage = (newImage, newMetadata) => {
    image.value.base64 = newImage;
    image.value.metadata = newMetadata;
    emit('update:modelValue', image.value);
};


watch(() => props.modelValue, (newValue) => {
    if (newValue !== image.value) {
        image.value = newValue;
    }
});


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
        const base64Image = e.target.result;
        const imageMetadata = {
            name: file.name,
            type: file.type,
            size: file.size,
            base64: base64Image
        };
        updateImage(base64Image, imageMetadata);
    };

    reader.readAsDataURL(file);
};


const activateWebcam = async () => {
    try {
        toggleVideoDisplay();
        stream.value = await navigator.mediaDevices.getUserMedia({video: true});
        video.value.srcObject = stream.value;
        video.value.play();
    } catch (err) {
        console.error("Erro ao acessar a webcam:", err);
    }
};


const toggleVideoDisplay = () => {
    if (div_video.value) {
        div_video.value.style.display = div_video.value.style.display === 'none' ? 'block' : 'none';
    }
};


const captureFromWebcam = () => {
    const canvas = document.createElement("canvas");
    canvas.width = video.value.videoWidth;
    canvas.height = video.value.videoHeight;
    canvas.getContext("2d").drawImage(video.value, 0, 0);

    canvas.toBlob(blob => {
        const file = new File([blob], "webcam-image.png", {type: 'image/png'});
        createImage(file);
        stream.value.getTracks().forEach(track => track.stop());
        stream.value = null;
    }, 'image/png');

    toggleVideoDisplay();
};


const isMobile = computed(() => {
    return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
});
</script>


<template>
    <div class="d-flex align-center justify-center">
        <input type="file"
               @change="onFileChange"
               ref="fileInput"
               style="display: none"/>

        <div v-if="!stream"
             class="d-flex position-relative">
            <v-avatar v-if="image.base64"
                      :size="180"
                      @click="pickFile"
                      cover>
                <v-img :src="image.base64"
                       cover></v-img>
            </v-avatar>

            <v-avatar v-else
                      :size="180"
                      @click="pickFile"
                      color="grey lighten-4">
                <v-icon size="32"
                        class="fa fa-camera"></v-icon>
            </v-avatar>

            <!-- Botão Badge para ativar a webcam -->
            <v-btn v-if="!isMobile"
                   class="webcam-badge v-btn--icon v-btn--round"
                   density="compact"
                   color="cinzaAzulado"
                   @click="activateWebcam">
                <v-icon size="x-small"
                        class="fa fa-video"></v-icon>
            </v-btn>

        </div>


        <div style="display: none;"
             ref="div_video">
            <v-col cols="12">
                <v-avatar :size="180"
                          cover>
                    <video class="rounded"
                           id="video_avatar"
                           ref="video"
                           style="width: 100%; height: 100%; object-fit: cover;"></video>
                </v-avatar>
            </v-col>
            <v-col cols="12">
                <v-btn v-if="stream"
                       @click="captureFromWebcam"
                       density="compact"
                       color="cinzaAzulado">Capturar Imagem
                </v-btn>
            </v-col>
        </div>

    </div>
</template>

<style>
.position-relative {
    position: relative;
}

.webcam-badge {
    position: absolute;
    bottom: 5px;
    right: 5px;
    border: none;
    line-height: 0;
    z-index: 2;
}

</style>