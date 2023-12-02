<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue';

const props = defineProps({
    modelValue: [String, Number], // Alterado de 'value' para 'modelValue'
    label: String,
    prependIcon: String,
    appendIcon: String,
    clearable: {
        type: Boolean,
        default: true
    },
    type: {
        type: String,
        default: 'text'
    },
    placeholder: String,
    disabled: Boolean,
    readonly: Boolean,
    rules: Array,
    variant: {
        type: String,
        default: 'outlined'
    },
    cols: {
        type: [Number, String],
        default: 12
    },
    sm: {
        type: [Number, String],
        default: '12'
    },
    md: {
        type: [Number, String],
        default: '6'
    },
    lg: {
        type: [Number, String],
        default: '3'
    },
    xl: {
        type: [Number, String],
        default: '3'
    }
});

const emits = defineEmits(['update:modelValue', 'blur', 'focus', 'clear']);

const internalValue = ref(props.modelValue);

watch(() => props.modelValue, (newValue) => {
    internalValue.value = newValue;
});

const onInput = (event) => {
    const value = event.target.value;
    internalValue.value = value;
    emits('update:modelValue', value);
};

const onBlur = (event) => {
    emits('blur', event);
};

const onFocus = (event) => {
    emits('focus', event);
};

const onClear = () => {
    internalValue.value = '';
    emits('update:modelValue', '');
    emits('clear');
};
</script>

<template>
    <v-col
        :cols="cols || 12"
        :sm="sm"
        :md="md"
        :lg="lg"
        :xl="xl"
    >
        <v-text-field
            density="compact"
            v-model="internalValue"
            :label="label"
            :prepend-icon="prependIcon"
            :append-icon="appendIcon"
            :clearable="clearable"
            :type="type"
            :placeholder="placeholder"
            :disabled="disabled"
            :readonly="readonly"
            :rules="rules"
            :variant="variant"
            @input="onInput"
            @blur="onBlur"
            @focus="onFocus"
            @click:clear="onClear"
        ></v-text-field>
    </v-col>
</template>
