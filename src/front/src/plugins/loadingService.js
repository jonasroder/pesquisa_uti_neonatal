import { ref } from 'vue';

const isLoading = ref(false);

const show = () => {
	isLoading.value = true;
};

const hide = () => {
	isLoading.value = false;
};

export const loading = {
	show,
	hide,
	state: isLoading
};
