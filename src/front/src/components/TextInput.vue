<script setup>
    import { ref, watch, defineProps, defineEmits } from 'vue';

    const props = defineProps({
        value: [String, Number],
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
        sm: [Number, String],
        md: [Number, String],
        lg: [Number, String],
        xl: [Number, String]
    });

    const emits = defineEmits(['input', 'change', 'blur', 'focus', 'clear']);

    const internalValue = ref(props.value);

    watch(() => props.value, (newValue) => {
        internalValue.value = newValue;
    });

    const onInput = (value) => {
        emits('input', value);
    };

    const onChange = (value) => {
        emits('change', value);
    };

    const onBlur = (event) => {
        emits('blur', event);
    };

    const onFocus = (event) => {
        emits('focus', event);
    };

    const onClear = () => {
        emits('clear');
    };
</script>


<template>
    <v-col :cols="cols" :sm="sm" :md="md" :lg="lg" :xl="xl">
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
            @change="onChange"
            @blur="onBlur"
            @focus="onFocus"
            @click:clear="onClear"
        ></v-text-field>
    </v-col>
</template>

