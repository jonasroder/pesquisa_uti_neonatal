<script setup>
import {ref, watch, defineProps, defineEmits} from 'vue';

const props = defineProps({
    modelValue: [String, Number],
    label: String,
    prependIcon: String,
    appendIcon: String,
    appendInnerIcon: String,
    prependInnerIcon: String,
    placeholder: String,
    disabled: Boolean,
    readonly: Boolean,
    rules: Array,
    clearable: {
        type: Boolean,
        default: true
    },
    type: {
        type: String,
        default: 'text'
    },
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

const onPrependIconClick = () => {
    emits('click:prepend-icon');
};

const onAppendIconClick = () => {
    emits('click:append-icon');
};

const onPrependInnerIconClick = () => {
    emits('click:prepend-inner-icon');
};

const onAppendInnerIconClick = () => {
    emits('click:append-inner-icon');
};

const defaultRules = {
    required: value => !!value || 'Campo obrigatório',
    email: value => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || 'E-mail inválido';
    },
    counter: value => value.length <= 20 || 'Máximo de 20 caracteres',
    cep: value => {
        const pattern = /^\d{5}-\d{3}$|^\d{8}$/; // Padrão com e sem hífen
        return pattern.test(value) || 'CEP inválido';
    }
};

const combinedRules = ref([
    ...(props.rules || []).map(ruleName => {
        if (defaultRules[ruleName]) {
            return defaultRules[ruleName];
        }
        console.warn(`Regra ${ruleName} não definida.`);
        return null;
    }).filter(rule => rule !== null)
]);

</script>

<template>
    <v-col :cols="cols || 12" :sm="sm" :md="md" :lg="lg" :xl="xl">
        <v-text-field
            density="compact"
            v-model="internalValue"
            :label="label"
            :prepend-icon="prependIcon"
            :append-icon="appendIcon"
            :prepend-inner-icon="prependInnerIcon"
            :append-inner-icon="appendInnerIcon"
            :clearable="clearable"
            :type="type"
            :placeholder="placeholder"
            :disabled="disabled"
            :readonly="readonly"
            :rules="combinedRules"
            :variant="variant"
            color="primary"
            @input="onInput"
            @blur="onBlur"
            @focus="onFocus"
            @click:clear="onClear"
            @click:append="onAppendIconClick"
            @click:prepend="onPrependIconClick"
            @click:appendInner="onAppendInnerIconClick"
            @click:prependInner="onPrependInnerIconClick"
        ></v-text-field>
    </v-col>
</template>




