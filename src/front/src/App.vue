<template>
    <div id="app">
        <router-view/>
        <Notification :message="notificationMessage" />
    </div>
</template>

<script setup>
import { watch } from "vue";
import { notificationMessage } from "@/service/notificationService";
import Notification from "@/components/Notification.vue";

// Assista às mudanças na mensagem de notificação global e atualize a variável local
watch(notificationMessage, (newMessage) => {
    notificationMessage.value = newMessage;
});
</script>
