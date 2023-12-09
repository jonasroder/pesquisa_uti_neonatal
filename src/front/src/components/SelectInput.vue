<script setup>
import { ref, watch, defineProps, defineEmits, onMounted } from 'vue';
import {serviceAuthenticateTeste} from "@/service/autocomplete";

const props = defineProps({
    modelValue: [String, Number, Array],
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
    },
    items: {
        type: Array,
        default: () => []
    },
    idColumn: {
        type: String,
        default: ""
    },
    descColumn: {
        type: String,
        default: ""
    },
    tableName: {
        type: String,
        default: ""
    },
    whereClause: {
        type: String,
        default: ""
    },
    is_active: {
        type: Boolean,
        default: false
    },
    multiple: {
        type: Boolean,
        default: false
    }
});

const items = ref(props.items);



const getOptionsAutocomplete = async () => {
    if(items.value.length === 0 && props.idColumn && props.descColumn && props.tableName){
        const params = {
            "idColumn"   : props.idColumn,
            "descColumn" : props.descColumn,
            "tableName"  : props.tableName,
            "is_active"   : props.is_active,
            "whereClause": props.whereClause
        }

        const response = await serviceAuthenticateTeste(params);
        if (response) {
            items.value = response.map(item => ({ value: item.value, label: item.label }));
        }
    }
}


onMounted(getOptionsAutocomplete);
watch([() => props.idColumn, () => props.descColumn, () => props.tableName, () => props.is_active, () => props.whereClause], getOptionsAutocomplete);

const emits = defineEmits(['update:modelValue', 'blur', 'focus', 'clear']);
const internalValue = ref(props.modelValue);



watch(() => props.modelValue, (newValue) => {
    internalValue.value = newValue;
});



const updateModel = (item) => {
    internalValue.value = item;
    emits('update:modelValue', item);
};



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



const defaultRules = {
    required: value => !!value || 'Campo obrigatório',
    email: value => {
        const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return pattern.test(value) || 'E-mail inválido';
    },
    counter: value => value.length <= 20 || 'Máximo de 20 caracteres'
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
    <v-col
        :cols="cols"
        :sm="sm"
        :md="md"
        :lg="lg"
        :xl="xl"
    >
        <v-autocomplete
            density="compact"
            :label="label"
            :prepend-icon="prependIcon"
            :append-icon="appendIcon"
            :clearable="clearable"
            :type="type"
            :placeholder="placeholder"
            :disabled="disabled"
            :readonly="readonly"
            :rules="combinedRules"
            :variant="variant"
            @update:modelValue="updateModel"
            @input="onInput"
            @blur="onBlur"
            @focus="onFocus"
            color="primary"
            @click:clear="onClear"
            :items="items"
            item-value="value"
            item-title="label"
            :multiple="multiple"
            :chips="multiple"
           />
    </v-col>
</template>