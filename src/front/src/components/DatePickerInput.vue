<script setup>
import {ref, watch, defineProps, defineEmits} from 'vue';
import {VDatePicker} from 'vuetify/components';

const props = defineProps({
    modelValue: Date,
    label: String,
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
    sm: [Number, String],
    md: [Number, String],
    lg: [Number, String],
    xl: [Number, String]
});

const emits = defineEmits([
    'update:modelValue',
    'input',
    'change',
    'blur',
    'focus',
    'click',
    'keydown',
    'keyup'
]);

const internalValue = ref(props.modelValue);

watch(() => props.modelValue, (newValue) => {
    internalValue.value = newValue;
});

const onInput = (value) => {
    emits('input', value);
    emits('update:modelValue', value);
};

const onChange = (event) => {
    emits('change', event);
};

const onBlur = (event) => {
    emits('blur', event);
};

const onFocus = (event) => {
    emits('focus', event);
};

const onClick = (event) => {
    emits('click', event);
};

const onKeydown = (event) => {
    emits('keydown', event);
};

const onKeyup = (event) => {
    emits('keyup', event);
};
</script>


<template>
    <v-col :cols="cols" :sm="sm" :md="md" :lg="lg" :xl="xl">
        <v-date-picker
            v-model="internalValue"
            :label="label"
            :disabled="disabled"
            :readonly="readonly"
            :rules="rules"
            :variant="variant"
            @input="onInput"
            @change="onChange"
            @blur="onBlur"
            @focus="onFocus"
            @click="onClick"
            @keydown="onKeydown"
            @keyup="onKeyup"
        ></v-date-picker>
    </v-col>
</template>

