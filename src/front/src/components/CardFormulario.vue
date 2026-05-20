<script setup>
defineProps({
    title            : { type: String, required: true },
    subtitle         : { type: String, default: '' },
    createButton     : { type: String, default: 'Novo Cadastro' },
    showCreateButton : { type: Boolean, default: false }
});

defineEmits(['handleNew']);
</script>

<template>
    <div class="list-page">
        <div class="list-page__header">
            <div>
                <h1 class="list-page__title">{{ title }}</h1>
                <p v-if="subtitle" class="list-page__subtitle">{{ subtitle }}</p>
            </div>
            <button v-if="showCreateButton" class="page-btn" @click="$emit('handleNew')">
                <i class="fa-solid fa-plus page-btn__icon" />
                {{ createButton }}
            </button>
        </div>
        <div class="list-page__body">
            <slot />
        </div>
    </div>
</template>

<style lang="scss" scoped>
.list-page {
    padding: 28px 24px;

    @media (max-width: 600px) { padding: 16px 12px; }

    &__header {
        display: flex;
        align-items: center;
        justify-content: space-between;
        flex-wrap: wrap;
        gap: 12px;
        margin-bottom: 24px;
    }

    &__title {
        font-size: 1.45rem;
        font-weight: 700;
        color: #0f2a45;
        margin: 0 0 4px;
        line-height: 1.2;

        @media (max-width: 600px) { font-size: 1.2rem; }
    }

    &__subtitle {
        font-size: 0.83rem;
        color: #94A3B8;
        margin: 0;
    }

    &__body {
        background: #ffffff;
        border-radius: 12px;
        border: 1px solid rgba(0, 0, 0, 0.06);
        box-shadow: 0 1px 3px rgba(0,0,0,0.06), 0 4px 16px rgba(0,0,0,0.04);
        overflow: hidden;
    }
}

.page-btn {
    display: inline-flex;
    align-items: center;
    gap: 8px;
    padding: 9px 20px;
    background: #0882a0;
    color: #ffffff;
    border: none;
    border-radius: 8px;
    font-size: 0.875rem;
    font-weight: 600;
    letter-spacing: 0.2px;
    cursor: pointer;
    transition: background 0.15s, box-shadow 0.15s, transform 0.1s;
    box-shadow: 0 1px 3px rgba(8,130,160,0.25), 0 4px 12px rgba(8,130,160,0.15);
    white-space: nowrap;

    &:hover  { background: #07718c; box-shadow: 0 2px 6px rgba(8,130,160,0.3), 0 6px 16px rgba(8,130,160,0.2); }
    &:active { transform: scale(0.98); }

    &__icon { font-size: 0.72rem; }
}
</style>
