<script>
export default {
    props: {
        message: String,
    },
};
</script>


<template>
    <div class="notification" v-if="message">{{ message }}</div>
</template>

<style scoped>
.notification {
    position: fixed;
    bottom: 20px;
    left: 50%;
    transform: translateX(-50%);
    background-color: #007bff;
    color: #fff;
    padding: 10px 20px;
    border-radius: 5px;
    display: inline-block;
    z-index: 999;
}
</style>
